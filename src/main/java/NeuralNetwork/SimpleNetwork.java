package NeuralNetwork;

import PokerClasses.AllCardsEnum;
import org.apache.commons.io.FilenameUtils;
import org.encog.engine.network.activation.ActivationSigmoid;
import org.encog.ml.data.MLDataSet;
import org.encog.ml.data.basic.BasicMLDataSet;
import org.encog.neural.networks.BasicNetwork;
import org.encog.neural.networks.layers.BasicLayer;
import org.encog.neural.networks.training.propagation.resilient.ResilientPropagation;

import java.util.Map;

public class SimpleNetwork {

    public BasicNetwork CreateNetwork(Map<String,double[]> trainingSet)
    {
        int inputNum = trainingSet.size();
        BasicNetwork network = new BasicNetwork();

        network.addLayer(new BasicLayer(null,true,inputNum));
        network.addLayer(new BasicLayer(new ActivationSigmoid(),true,inputNum));
        network.addLayer(new BasicLayer(new ActivationSigmoid(),false,1));

        network.getStructure().finalizeStructure();
        network.reset();

        double[][]  input =new double[trainingSet.keySet().size()][];
        double[][] output = new double[trainingSet.keySet().size()][];

        int i = 0;
        for(Map.Entry<String,double[]> entry: trainingSet.entrySet())
        {
            input[i] = entry.getValue();
            output[i] =new double[]{AllCardsEnum.valueOf(FilenameUtils.removeExtension((entry.getKey()))).getValue()};
            i++;
        }



        BasicMLDataSet set = new BasicMLDataSet(input,output);
        ResilientPropagation train = new ResilientPropagation(network,set);

        int epoch = 1;
        do{
           train.iteration();
           epoch++;

        }while(train.getError() > 0.1 && epoch <1000);


        return network;
    }
}
