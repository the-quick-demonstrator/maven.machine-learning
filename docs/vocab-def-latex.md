[//]: # (Rewrite with LaTex)

## What is a neural network?
A neural network is a layer or layers of interconnected neurons connected by synapses (the weights of the system) that work together to perform a specific computation on the input data. In short, information is passed through these layers, transformed, and abstracted, until it reaches its final layer; where a prediction is made. 

Each layer in a neural network typically has a number of neurons, where each neuron in a
  layer is connected to all the neurons in the previous and next layers. Each connection between neurons has a weight,
  which represents the strength of the connection and the influence that the output of one neuron has on the input of
  another neuron. The weights are learned during the training process to optimize the performance of the network on a
  specific task.
  
  - Input Layer: The first layer of the network, which takes the raw input data and feeds it into the network. This
      layer does not perform any computation; it simply passes the input data to the next layer.
  - Hidden Layer(s): The layers that come after the input layer and before the output layer. These layers perform
    computations on the input data, such as extracting features or making predictions. There can be one or multiple
    hidden layers in a neural network, depending on the complexity of the problem.
  - Output Layer:  The last layer of the network, which produces the final output of the network. This output could be
    a prediction, a feature representation or any other desired output.


| Type of Neural Network | Definition |
|------------------------|------------|
- <mark> Feedforward Networks </mark>: Are the simplest type of neural network. They consist of an input layer, one or more hidden layers, and an output layer. The information flows in one direction, from input to output, through the layers of neurons. They are used for tasks such as image classification and regression. In short, they are good for problems where the input data is independent of previous inputs.


- <mark> Recurrent Networks </mark>:  (RNNs) are a type of neural network that are well-suited for sequence data, such as time series data or natural language. RNNs have a feedback connection, which allows information to flow in a circular loop, and the network can maintain a kind of memory of the previous inputs. They are used for tasks such as language processing and speech recognition.

    -  <mark> Bidirectional Recurrent Neural Networks </mark>: (BRNNs) are a type of hybrid neural network that combines the strengths
        of both feedforward neural networks and RNNs. BRNNs use two recurrent layers, one processing the input sequence in
        the forward direction and the other processing it in the backward direction. They are commonly used in tasks such
        as natural language processing and speech recognition
    - <mark> Convolutional Networks </mark>: (CNNs) are a type of neural network that are particularly well-suited for image processing tasks. CNNs use convolutional layers, which scan the image with a small window, or kernel, and apply a mathematical operation to the window. This allows the network to learn spatial hierarchies of features, such as edges and shapes, in the image. They are used for tasks such as image classification, object detection and segmentation In short, they are good for problems where the input data is sequential.


- <mark> U-Net Networks </mark>: A type of hybrid neural network that combines the strengths of both CNNs and RNNs. It is an
      encoder-decoder network that uses convolutional layers to extract features from the input, and recurrent layers to
      propagate those features through the network. They are commonly used in image segmentation tasks such as
      biomedical image segmentation.


- <mark> Inception Networks </mark>: Are a variation of CNNs that were introduced to address the problem of computational
      efficiency in very deep networks. Inception networks use a combination of convolutional, pooling, and
      dimensionality reduction layers to extract features from the input image. They are commonly used in computer
      vision tasks such as image classification and object detection.


- <mark> Generative Adversarial Networks (GANs) </mark>:  are a type of neural network that consist of two parts: a generator network
  and a discriminator network. The generator network creates new data, while the discriminator network tries to
  distinguish the generated data from real data. The two networks are trained together in an adversarial process, where
  the generator tries to create data that can fool the discriminator, and the discriminator tries to correctly identify
  the generated data. They are used for tasks such as image generation and style transfer.

- <mark> Autoencoder Networks </mark>: A type of neural network that are used for unsupervised learning tasks such as dimensionality
  reduction, de-noising and anomaly detection. An autoencoder consists of an encoder that maps the input data to a
  hidden representation, and a decoder that maps the hidden representation back to the original data. The network is
  trained to minimize the difference between the input and the output, forcing the hidden representation to learn a
  compact and informative representation of the input data.

- <mark> Transformer Networks </mark>: A type of neural networks used for tasks such as natural language processing, specifically
  language understanding and generation. They are based on self-attention mechanisms and are known for their ability to
  process sequential data, like text, without the need for recurrence or convolution.

- <mark> Residual Neural Networks </mark>: (ResNets) are a variation of feedforward neural networks that were introduced to address the
  problem of vanishing gradients in very deep networks. ResNets add so-called "shortcut connections" between layers,
  allowing the gradients to flow more easily through the network during training. They are commonly used in computer
  vision tasks such as object detection and image classification.

Problems within neural networks:

- Vanishing gradients: As the number of layers in a network increases, the gradients (the values that indicate how the
  parameters of the network should be updated during training) can become very small, making it difficult for the
  network to learn.
- Exploding gradients: On the other hand, the gradients can also become very large, making the training process
  unstable.
- Computational efficiency: As the number of layers in a network increases, the computational cost of training and
  running the network also increases, making it infeasible to use very deep networks on large datasets or on devices
  with limited computational resources.

## Functions, what are they?

In the context of neural networks, a function refers to a mathematical operation that is performed on the input data to produce an output. There are several types of functions that are commonly used in neural networks.

- Activation function: Activation functions are used to introduce non-linearity in the network and make it possible to learn more complex representations of the input data. <mark>Non-linearity</mark> refers to the property of a function or a system that does not follow a linear relationship between its inputs and outputs. In the context of neural networks, non-linearity allows the network to learn more complex and expressive representations of the input data. Without non-linearity, a neural network with multiple layers would be equivalent to a single-layer linear model, and would not be able to learn complex patterns in the input data. An activation function is applied to the output of each neuron in a layer to introduce non-linearity in the network. The non-linearity allows the network to learn more complex and expressive representations of the input data. Some common activation functions are the sigmoid function, the ReLU (rectified linear unit) function, and the tanh (hyperbolic tangent) function.
  - Sigmoid: The sigmoid function maps any real-valued number to a value between 0 and 1. It is often used in the output layer of a neural network when the task is binary classification. Strengths: Sigmoid function maps the input to a probability value, making it useful for binary classification tasks. Weaknesses: it's output saturates at large positive and negative inputs, causing the gradients to become very small and making it difficult to train the network. 
  - ReLU (Rectified Linear Unit): The ReLU function maps any negative input value to 0 and any positive input value to itself. It is often used in the hidden layers of a neural network. Strengths: ReLU function is computationally efficient and does not saturate, allowing gradients to propagate well and making it easy to train the network. Weakness: it can suffer from "dying ReLU" problem where a neuron's output is always 0, causing the gradients to become 0 and making it difficult to train that neuron.
  - tanh (hyperbolic tangent): It is similar to the sigmoid function, but it is centered around 0 and has a slightly different shape. The tanh function is commonly used in hidden layers of a neural network and it is a popular choice for tasks such as image classification and language processing. The tanh function maps input values to the range of -1 to 1, it is commonly used in hidden layers of a neural network. Similar to sigmoid function, it has smooth gradients but it can suffer from saturating activations. Also, the tanh function is sensitive to the scale of the input, which means that it needs to be normalized before being applied, otherwise it could result in a slow or non-convergent training.

In summary, the hyperbolic tangent (tanh) function is a type of activation function that maps input values to the range of -1 to 1, it is a popular choice for tasks such as image classification and language processing, it has smooth gradients, it can break symmetry and help alleviate the vanishing gradient problem, but it can suffer from saturating activations, and it is sensitive to the scale of the input.
- Loss function: Loss functions are used to measure how well the network is performing. A loss function is used to measure the difference between the predicted output of the network and the true output. The loss function provides a scalar value that indicates how well the network is performing on a given input. The goal of training a neural network is to minimize the value of the loss function. Some common loss functions are the mean squared error, the cross-entropy loss, and the hinge loss.
- Optimization functions are used to adjust the network's parameters during training to minimize the value of the loss function. Optimization function: An optimization function is used to adjust the parameters of the network during training. The goal of the optimization function is to find the set of parameters that minimize the value of the loss function. Some common optimization functions are the stochastic gradient descent (SGD), Adam, and Adagrad.

# Machine Learning Types
- Unsupervised
  - Examples of unsupervised learning algorithms include:
    - Clustering algorithms such as K-Means and Hierarchical Clustering 
    - Association rule learning such as Apriori and Eclat 
    - Principal component analysis (PCA) and Linear Discriminant Analysis (LDA)
    - Autoencoders 
    - Generative Adversarial Networks (GANs)
- Supervised