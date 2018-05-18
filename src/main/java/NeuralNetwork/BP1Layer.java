package NeuralNetwork;

import org.encog.neural.networks.training.LearningRate;

import java.lang.reflect.Array;
import java.util.Map;
import java.util.Random;


public class BP1Layer implements IBackPropagation{

    private int PreInputNum;
    private int OutputNum;
    private PreInput[] PreInputLayer;
    private Output[] OutputLayer;


private double learningRate = 0.2;

    public BP1Layer(int preInputNum, int outputNum)
    {
        PreInputNum = preInputNum;
        OutputNum = outputNum;

        PreInputLayer = new PreInput[PreInputNum];
        for(int i = 0;i<PreInputNum;i++)
        {
            PreInputLayer[i] = new PreInput();
        }


        OutputLayer = new Output[outputNum];
        for(int i = 0;i<OutputNum;i++)
        {
            OutputLayer[i] = new Output();
        }

    }


    public void BackPropagate() {

        for (int j = 0; j<OutputNum;j++)
        {
            for (int i = 0;i<PreInputNum;i++)
            {
                PreInputLayer[i].Weights[j] += learningRate * (OutputLayer[j].Error) * PreInputLayer[i].Value;
            }
        }
    }

    public double F(double x) {
        return (1/(1 + Math.exp(-x)));
    }

    public void ForwardPropagate(double[] pattern, String output) {
        int i, j;
        double total = 0.0;

        //Apply input to the network
        for (i = 0; i < PreInputNum; i++)
        {
            PreInputLayer[i].Value = pattern[i];
        }

        //Calculate The First(Output) Layer's Inputs, Outputs, Targets and Errors
        for (i = 0; i < OutputNum; i++)
        {
            total = 0.0;
            for (j = 0; j < PreInputNum; j++)
            {
                total += PreInputLayer[j].Value * PreInputLayer[j].Weights[i];
            }
            OutputLayer[i].InputSum = total;
            OutputLayer[i].Output = F(total);
            OutputLayer[i].Target = OutputLayer[i].Value == output ? 1.0 : 0.0;
            OutputLayer[i].Error = (OutputLayer[i].Target - OutputLayer[i].Output) * (OutputLayer[i].Output) * (1 - OutputLayer[i].Output);
        }
    }

    public double GetError() {

        double total = 0.0;
        for (int j = 0;j<OutputNum;j++)
        {
            total +=Math.pow(OutputLayer[j].Target - OutputLayer[j].Output,2)/2;
        }
        return total;
    }

    public void InitializeNetwork(Map<String, double[]> TrainingSet) {

        Random rand = new Random();
        for (int i = 0; i<PreInputNum;i++)
        {
            PreInputLayer[i].Weights = new Double[OutputNum];
            for(int j =0;j<OutputNum;j++)
            {
                PreInputLayer[i].Weights[j] = 0.01 +(((double) rand.nextInt(3))/100 );
            }
        }

        int k = 0;
        for(Map.Entry<String,double[]> entry: TrainingSet.entrySet())
        {
           OutputLayer[k++].Value = entry.getKey();
        }
    }

    public BackPropagationResult Recoginze(double[] input)
    {
        BackPropagationResult result = new BackPropagationResult();
      int i,j;
      double total  = 0.0;
      double lastError = 9999;
      double max = -1;
      for (i = 0;i<PreInputNum;i++)
      {
          PreInputLayer[i].Value = input[i];
      }
      for (i = 0;i < OutputNum; i++)
      {
          total = 0.0;
          for (j = 0;j<PreInputNum; j++)
          {
              total +=PreInputLayer[j].Value * PreInputLayer[j].Weights[i];
          }
          OutputLayer[i].InputSum = total;
          OutputLayer[i].Output = F(total);
          if (OutputLayer[i].Error <lastError)
          {
                lastError = OutputLayer[i].Error;
                result.MatchedHigh = OutputLayer[i].Value;
                result.OutputValueHight = OutputLayer[i].Output;
          }
      }
      return result;
    }
}
