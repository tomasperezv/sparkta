.. _examples:

SpaRkTA examples
****************

RabbitMQ: from Twitter to MongoDB
=================================

Example to take data in streaming from Twitter and ingesting it in RabbitMQ in order to test the SpaRkTA input.
To access to the Twitter API it is necessary to config the file::

    /opt/sds/sparkta/examples/data-generators/twitter-to-rabbit/src/main/resources/twitter4j.properties

Steps

* Run the RabbitMQ server where we want to read from. We will use Mongodb to write our aggregated data in the sparta
database::

    sudo service rabbitmq-server start

    sudo service mongod start

* Next we run SpaRkTA and send the policy. 
If you are using the sandbox, you may need to start a new ssh session ( **vagrant ssh** ).
This policy contains the configuration that tells SpaRkTA where to read,
where to write and how to transform the input data::

    cd /opt/sds/sparkta

    sudo sh bin/run

    curl -H "Content-Type: application/json" http://localhost:9090 --data
    @examples/data-generators/twitter-to-rabbit/twitter-policy.json

* There are two ways of testing it. Producing data directly into a RabbitMQ queue or producing data into a RabbitMQ
queue through a direct exchange (https://www.rabbitmq.com/tutorials/tutorial-four-java.html)

    - For producing data directly into a RabbitMQ queue run the class TwitterToRabbitMQSimple::

      cd /opt/sds/sparkta/examples/data-generators/twitter-to-rabbit/

      mvn clean package

      mvn exec:java -Dexec.mainClass="com.stratio.examples.twittertorabbit.TwitterToRabbitMQSimple"

    - For Producing data into a RabbitMQ queue through a direct exchange run the class TwitterToRabbitMQWithRouting
    with the routingKey you want to write the data as argument::

      cd /opt/sds/sparkta/examples/data-generators/twitter-to-rabbit/

      mvn clean package

      mvn exec:java -Dexec.mainClass="com.stratio.examples.twittertorabbit.TwitterTabbitMQWithRouting" -Dexec.args="routingKey3"

e-commerce to RabbitMQ and ElasticSearch
========================================

This example simulates an environment of an e-commerce architecture.
In one hand we have the logs generated by an apache server and in the other the orders requested in the web site.
We'll publish all this events in `RabbitMQ <https://www.rabbitmq.com>`__ and aggregate them with SpaRkTA which will
save the aggregated data in elasticsearch.

Steps

* First we need to start the RabbitMQ server where we will tell SpaRkTA to read from. And elasticsearch where SpaRkTA
will save the aggregated data::

    sudo service rabbitmq-server start

    sudo service elasticsearch start

* Next we run SpaRkTA and send the policy. This policy contains the configuration that tells SpaRkTA where to read,
where to write and how to transform the input data::

    cd /opt/sds/sparkta

    sudo sh bin/run

    curl -H "Content-Type: application/json" http://localhost:9090 --data
    @examples/data-generators/ecommerce/ecommerce-policy.json

* And last we need to run the data generators in two different shells. This generators will generate random data and
will write it into RabbitMQ. In a few seconds SpaRkTA will start to read the data and write it into elasticsearch::

    cd examples/data-generators/ecommerce

    mvn -PorderLines clean install benerator:generate

    mvn -PvisitLog clean install benerator:generate

