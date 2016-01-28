package snake_ai.neuro_evolution;

import org.encog.engine.network.activation.ActivationSigmoid;
import org.encog.neural.networks.BasicNetwork;
import org.encog.neural.networks.layers.BasicLayer;

import java.util.Random;

/**
 * Created by apolol92 on 26.01.2016.
 * Represents a individuum..
 */
public class NeuroNetwork extends BasicNetwork {
    /**
     * how fit is this individuum?
     */
    public int fitness;


    /**
     * Do a crossover with n2 and finally maybe mutate
     * @param n2, other individuum
     * @return new individuum
     */
    public NeuroNetwork crossoverAndMutation(NeuroNetwork n2) {
        NeuroNetwork child = new NeuroNetwork();
        int layer_amount = n2.getLayerCount();
        int input_size = n2.getInputCount();
        int hidden_amount = layer_amount-2;
        int output_size = n2.getOutputCount();
        int hidden_size = n2.getFlat().getNeuronCount()-input_size-output_size;
        child.addLayer(new BasicLayer(new ActivationSigmoid(),true,input_size));
        for(int d = 0; d < hidden_amount; d++) {
            child.addLayer(new BasicLayer(new ActivationSigmoid(),true,hidden_size));
        }
        child.addLayer(new BasicLayer(new ActivationSigmoid(), true, output_size));
        child.getStructure().finalizeStructure();
        child.reset();
        for(int i = 0; i < this.getFlat().getNeuronCount(); i++) {
            Random rnd = new Random();
            int r = rnd.nextInt(10);
            if(r<4) {
                //crossover this
                child.getFlat().getWeights()[i] = this.getFlat().getWeights()[i];
            }
            else if(r>5) {
                child.getFlat().getWeights()[i] = n2.getFlat().getWeights()[i];
            }
        }
        return child;
    }


}
