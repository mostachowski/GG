package NeuralNetwork;

import java.util.Map;

public interface IBackPropagation {
    void BackPropagate();
    double F(double x);
    void ForwardPropagate(double[] pattern, String output);
    double GetError();
    void InitializeNetwork(Map<String,double[]> TrainingSet);
    BackPropagationResult Recoginze(double[] input);
}
