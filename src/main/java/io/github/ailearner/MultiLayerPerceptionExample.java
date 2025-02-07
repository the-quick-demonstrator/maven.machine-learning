package io.github.ailearner;

import io.github.ailearner.utils.FileHandler;
import io.github.ailearner.utils.NNTrainingDataHelper;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.factory.Nd4j;
import org.nd4j.linalg.indexing.NDArrayIndex;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.function.Function;
import java.util.stream.IntStream;

public class MultiLayerPerceptionExample {
    /**
     * Create our X-input and Y-output vectors for Training;
     *
     * @param file_path the file to read data from;
     * @return a Pair consisting of X,Y values;
     */
    public Pair<INDArray, INDArray> prepareTrainingData(String file_path) {
        final int blockSize = 3;
        final LinkedList<INDArray> tensorX = new LinkedList<>();
        final LinkedList<Integer> tensorY = new LinkedList<>();
        final INDArray xContext = Nd4j.zeros(1, 3);
        final FileHandler fh = new FileHandler();
        // BagOfWordsNN.charToIdxMap.get(letter) -- prefer this expression
        final Function<String, Integer> charMap_to_IdxSupplier = (letter) -> new NNTrainingDataHelper().createCharToIdxMap().get(letter);


        // read all words from names.txt
        fh.readWordsFromFile(file_path).forEach((word) -> {
            word = word + "."; // add END_TOKEN
             System.out.println("=============");
             System.out.printf("Word: %s\n", word);
            final String[] lettersOfCurrentWord = word.split("");
            IntStream.range(0, (lettersOfCurrentWord.length)).forEach(chxInWord -> {
                 System.out.printf("Given: %s --> Expect: %s\n", xContext, charMap_to_IdxSupplier.apply(lettersOfCurrentWord[chxInWord]));
                int idx = charMap_to_IdxSupplier.apply(lettersOfCurrentWord[chxInWord]);
                tensorX.add(xContext.dup()); // add dup to avoid changing underlying array later in program
                tensorY.add(charMap_to_IdxSupplier.apply(lettersOfCurrentWord[chxInWord]));
                // update context vector;
                xContext.putiRowVector(xContext
                        .get(NDArrayIndex.indices(-1))
                        .get(NDArrayIndex.indices(1))
                        .putScalar(new int[]{0, blockSize - 1}, idx)
                );
            });
            // reset context after END_TOKEN rec'd;
            xContext.putiRowVector(Nd4j.zeros(1, 3));
        });
        return null;
    }

    void train() {
        //Prepare Training Data
        prepareTrainingData("file/path/names.txt");
        // this.run(training = true, sampleSize = 10000); // if training==true, sampleSize = epochs;
        // loss function and backpropagation
        // loss = Nd4j.nn().lossFunction(layer2, vectorY);
        // Nd4j.loss().softmaxCrossEntropy(null,vectorY, 0.1);

        /**
         * Goal: For the given context we want to predict the next Y.
         *
         * Forward Pass:
         * 1. Take C[X] and embed (fully-connected) in Layer1 (*hyper parameter); Or, C[X[i]];
         *  Create a vector of embeddings:
         *              In Bengio et. 2003, the designers uses 3 word embeddings.
         *              (https://www.jmlr.org/papers/volume3/bengio03a/bengio03a.pdf)
         *              (https://github.com/karpathy/makemore/blob/master/makemore.py)
         *
         *              If we take all the data set (as potential samples of f(x)),
         *              Then the lookup matrix, C, looks like:
         *                  C.shape() = (len(all_chars_x)+'.', 1) or ('a,...,z,<.>', 1)
         *              And, the embedding vector for the given context looks like: C[X];
         *              If we want a specific char, say 'a', then the embedding vector looks like: C[X[i]] = C['a'];
         *
         *
         * 2. Take Layer2=tanh(Layer1), fully-connected (matrix) to C,
         *  Take Layer2=tanh(Layer1), fully-connected (matrix) to C for non-linearity;
         *
         *
         *
         * 3. Take Softmax(Layer2) -> tokenOut; Or, yield(): sample/expected output; Or, P(w_i | context);
         *
         *
         * **Note:
         *  Context comes in as stream. Y is the next expected char in the stream. Aka, MultiHeadAttention.
         *  Hyper Parameters: Train at different values to reduce loss;
         *  We use random vector inits to help avoid linearity.
         */

        Integer contextLength = 3; // amount of chars used to predict next one;
        Integer numberOfNeurons = 100; // we want to fully connect all first layer input neurons to this;
        Integer dimsToSqueeze = 2; // squeeze the first layer input to this dimension;

        // get all chars from the names.txt file;
        ArrayList allCharsX = null; //mlp.prepareTrainingData("/file/path/names.txt");

        // "first layer input" - input to model/neural-net
        // is a vectorX with dims: [len(all_chars_x), hyper_parameter]
        // and, should start with as zero tensor; then we will fill it with the context;

        INDArray vectorX = Nd4j.zeros(allCharsX.size(), dimsToSqueeze);

        // first layer output vector: in(contextLength) => out(numberOfSecondLayerInputNeurons)
        INDArray vectorY = Nd4j.rand(contextLength, numberOfNeurons);

        // embedding vector (first layer of our neural net)
        INDArray C = Nd4j.create(1);

        // get the embedding vector for the given context;
        // Nd4j.create(allCharsX.size()).putScalar(index, 1).mmul(C); //F.one_hot(vectorX, num_classes=len(all_chars_x)).mmuli(C);
        // INDArray emb_cX = vectorX.mmuli(C);
        INDArray emb_cX = C.get(vectorX);

        // second layer of our neural net, fully connected to C[X[i]];
        INDArray layer1 = Nd4j.rand((int) vectorX.shape()[0], numberOfNeurons);
        INDArray layer2 = Nd4j.nn().tanh(layer1); // fully connected to C;

        // softmax output
        INDArray tokenOut = Nd4j.nn().softmax(layer2); // P(w_i | context);

        /**
         * Training:
         * We need to calc the Negative Log Likelihood. # Manual Impl
         * This is: the prob(x|i)/prob(x), i.e, prob of "x" follows "i" divided by gaussian prob of any letter follow "x".
         * This is easy in works in our case since we are only predicting the next English letter given some other English Letter.
         * Does not scale if {x: x in A} is a large array.
         * <p>
         * OR
         * <p>
         * We need to calc the cross_entropy. # Handles large inputs better
         * <p>
         * Backpropagation:
         * Do gradient descent calculus.
         * <p>
         * //Mini Batching??
         * <p>
         * //Sample
         * /*
         * * Start with seed for predictable results during testing and sampling; do not release into wild with seed.
         * * Forward Pass
         * * Return Sample
         */

    }

    void sample(Integer sampleSize) {
        // Sample from the model
        // this.run(training = false, sampleSize); // if training == false, sampleSize = blockSize;
    }


}
