package NeuralNetwork;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class NeuralNetworkClass {

    private double maximumError = 1.0;
    private int maximumIteration  = 200;
    Map<String,double[]> TrainingSet ;
    private IBackPropagation neuralNet;



   public NeuralNetworkClass(IBackPropagation backPro, Map<String,double[]> trainingSet)
   {
       neuralNet = backPro;
       TrainingSet = trainingSet;
       neuralNet.InitializeNetwork(TrainingSet);
   }

    public boolean Train()
    {
        for(Map.Entry<String,double[]> entry: TrainingSet.entrySet()) {
            double currentError = 0;
            int currentIteration = 0;
            do {


                neuralNet.ForwardPropagate(entry.getValue(), entry.getKey());
                neuralNet.BackPropagate();
                currentError += neuralNet.GetError();

                currentIteration++;

            } while (currentError > maximumError && currentIteration < maximumIteration);
        }

        return true;
    }

    public BackPropagationResult Recognize (double[] input )
    {
        return neuralNet.Recoginze(input);
    }
}
