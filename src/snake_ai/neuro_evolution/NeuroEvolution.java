package snake_ai.neuro_evolution;

import org.encog.engine.network.activation.ActivationSigmoid;
import org.encog.neural.networks.BasicNetwork;
import org.encog.neural.networks.layers.BasicLayer;

import java.util.ArrayList;

/**
 * Created by apolol92 on 26.01.2016.
 */
public class NeuroEvolution {
    /**
     * This is the list with all individuums
     */
    public ArrayList<NeuroNetwork> neuroNetworks;
    /**
     * The maximal population_size
     */
    private int population_size;

    /**
     * Create a NeuroEvolution
     * @param population_size, set the population size
     * @param layer_amount, how many layers should be each individuum contains?
     * @param input_size, how many neurons should be in the input layer of each individuum?
     * @param hidden_amount, how many hidden layers should be in the individuum?
     * @param hidden_size, how many neurons should be in each hidden layer?
     * @param output_size, how many neurons should be in the output layer of each individuum?
     */
    public NeuroEvolution(int population_size, int layer_amount, int input_size, int hidden_amount, int hidden_size, int output_size) {
        this.neuroNetworks = new ArrayList<NeuroNetwork>();
        this.population_size = population_size;
        for(int i = 0; i < population_size; i++) {
            NeuroNetwork basicNetwork = new NeuroNetwork();
            basicNetwork.addLayer(new BasicLayer(new ActivationSigmoid(),true,input_size));
            for(int d = 0; d < hidden_amount; d++) {
                basicNetwork.addLayer(new BasicLayer(new ActivationSigmoid(),true,hidden_size));
            }
            basicNetwork.addLayer(new BasicLayer(new ActivationSigmoid(),true,output_size));
            basicNetwork.getStructure().finalizeStructure();
            basicNetwork.reset();
            this.neuroNetworks.add(basicNetwork);
        }

    }

    /**
     * Selection.. only survice top n
     * @param n, top 10 for example
     */
    public void surviveTop(int n) {
        InsertionSort(this.neuroNetworks);
        for(int i = this.neuroNetworks.size()-1;i>=n; i--) {
            this.neuroNetworks.remove(i);
        }
    }

    /**
     * Repopulate the population with the 2 best survived individuums
     */
    public void repopulateWithTop2() {
        int i = this.neuroNetworks.size();
        for(;i<population_size;i++) {
            this.neuroNetworks.add(i,this.neuroNetworks.get(0).crossoverAndMutation(this.neuroNetworks.get(1)));
        }
    }

    /**
     * Just an insertion sort algorithms
     * @param networks, will be sorted
     */
    public void InsertionSort( ArrayList<NeuroNetwork> networks)
    {
        /**
         * Large values at the list beginning
         */
        int j;
        int i;
        NeuroNetwork keyNetwork;
        for(j = 1; j < networks.size(); j++) {
            keyNetwork = networks.get(j);
            for(i = j-1; (i>=0) && (networks.get(i).fitness<keyNetwork.fitness);i--) {
                networks.add(i+1,networks.get(i));
            }
            networks.add(i+1,keyNetwork);
        }
    }





}
