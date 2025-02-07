# Purpose:

This repo contains an API that is an abstraction of the Keras API.
This repo also contains code samples on model fitting two distinct forms of Machine Learning (ML): Supervised and
UnSupervised.

Additional resources related to ML can be located here:  [Cornell open-access archive](arXiv.org) and
the [IEEE Xplore Digital Library](IEEE Xplore Digital Library)

# How to use the API:

First define a model using the `ML_Model` class.

```python
myModel = ML(
    hidden_layers=[],  # keras.layers
    optimizer_function=None,  # keras.optimizers
    loss_function=None,  # keras.losses
    model_type=None,  # keras.models
    metrics=None,  # keras.metrics
)
```

Then compile the model and optionally train the model.

```python
import keras

myModel.compile_model()

x_data: keras.Input  # (for supervised learning),
y_data: keras.Input  # (for supervised learning),
myModel.train_model(
    training_data_set=x_data,
    expected_training_return_values=y_data,
    training_iterations=None  # number of epochs
)
```

Finally make predictions.

```python
predictions = myModel.predict()
```
