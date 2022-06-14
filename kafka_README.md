**Apache Kafka -**
1.  **Scala and Java** -> former LinkedIn data engineers -> 2011 -> open source as -> **highly scalable messaging system.**
2. Data and logs involved in today’s complex systems must be processed, reprocessed, analyzed and handled - often in real-time. 
3. And that's why Apache Kafka is playing a significant role in the message streaming landscape. 
4. provide the ability to store, process, and reprocess streaming data. 
 
 **Apache Kafka is a publish-subscribe based durable messaging system. A messaging system sends messages between processes, applications, and servers.
Durable message system**
                                        Apache KAfka
                                    ------CLUSter----------  
                                          BROKER, Broker....
    Application ->                         TOPIC              
    (Producer,Prod..)                   partitions              ---> Application  (Consumer, Consumer, Consumer)

1.  Applications connect to this system and transfer a record onto the topic. 
2.  A record can include any kind of information;
3.   for example, information about an event that has happened on a website, or an event that is supposed to trigger an event. 
4.   Another application may connect to the system and process or re-process records from a topic. 
5.   The data sent is stored until a specified retention period has passed by.
6. Records are byte arrays that can store any object in any format. A record has four attributes
7. key and value are mandatory, 
8. and the other attributes, timestamp, and headers are optional.
9.  The value can be whatever needs to be sent, for example, JSON or plain text. 
HOW KAFKA stores data
https://medium.com/@abhisheksharma_59226/how-kafka-stores-data-37ee611c89a2
These are four main parts in a Kafka system:

    **Broker**: Handles all requests from clients (produce, consume, and metadata) and keeps data replicated within the cluster. 
                There can be one or more brokers in a cluster.
    **Zookeeper**: Keeps the state of the cluster (brokers, topics, users).
    **Producer**: Sends records to a broker.
    **Consumer**: Consumes batches of records from the broker.
1.  A Kafka cluster consists of one or more servers (Kafka brokers) running Kafka. 
2.  Producers are processes that push records into Kafka topics within the broker. 
3.  A consumer pulls records off a Kafka topic.

1.  Running a single Kafka broker is possible 
2.  but it doesn’t give all the benefits that Kafka in a cluster can give, for example, data replication. 

**ZOOKEEPER**

1.  Management of the brokers in the cluster is performed by Zookeeper. There may be multiple Zookeepers in a cluster, in fact the 
2.  recommendation is three to five, keeping an odd number so that there is always a majority and the number as low as possible to conserve overhead resources. 

** Kafka Topic**

A Topic is a category/feed name to which records are stored and published.

As said before, all Kafka records are organized into topics. 
Producer applications write data to topics and consumer applications read from topics. 
Records published to the cluster stay in the cluster until a configurable retention period has passed by. 

 **Kafka topic partition**

1.  Kafka topics are divided into a number of partitions, which contain records in an unchangeable sequence. 
2.  Each record in a partition is assigned and identified by its unique offset. 
3.  A topic can also have multiple partition logs. This allows multiple consumers to read from a topic in parallel.
4.  Partitions allow topics to be parallelized by splitting the data into a particular topic across multiple brokers.
5.  In Kafka, replication is implemented at the partition level. 
6.  The redundant unit of a topic partition is called a replica.
7.  Every partition (replica) has one server acting as a leader and the rest of them as followers. 
8.  The leader replica handles all read-write requests for the specific partition and the followers replicate the leader.
9.   If the lead server fails, one of the follower servers becomes the leader by default.
10.   When a producer publishes a record to a topic, it is published to its leader.
11.    The leader appends the record to its commit log and increments its record offset. 
12.    Kafka only exposes a record to a consumer after it has been committed and each piece of data that comes in will be stacked on the cluster.

 **Consumers and consumer groups**

1. Consumers can read messages starting from a specific offset and are allowed to read from any offset point they choose. 
2. This allows consumers to join the cluster at any point in time. 

https://www.cloudkarafka.com/blog/part1-kafka-for-beginners-what-is-apache-kafka.html



e.g: **
 Apache Kafka Examples
Website activity tracking **

1. According to the creators of Apache Kafka, the original use case for Kafka was to track website activity 
2. - including page views, searches, uploads or other actions users may take. This kind of activity tracking 
3. often requires a very high volume of throughput because messages are generated for each user action. 

1. Users can click around, sign in, write blog articles, upload images to articles and publish those articles.
2.  When an event happens in the blog (e.g when someone logs in, when someone presses a button 
3.  or when someone uploads an image to the article) a tracking event and 
4.  information about the event will be placed into a record, and the record will be placed on a specified Kafka topic. 
5.  One topic is named "click" and one is named "upload". 

 1. Partitioning setup is based on the user's id. A user with id 0, will map to partition 0, 
 2. and the user with id 1 to partition 1, etc.
 3.  The "click" topic will be split up into three partitions (three users) on two different machines.

1. A user with user-id 0 clicks on a button on the website.
2.   The web application publishes a record to partition 0 in the topic "click".
3.    The record is appended to its commit log and the message offset is incremented.
4.    The consumer can pull messages from the click-topic and show monitoring usage in real-time, 
5.    or it can replay previously consumed messages by setting the offset to an earlier one.

 **Kafka as a Database**

Apache Kafka has another interesting feature not found in RabbitMQ - log compaction. Log compaction ensures that Kafka always retains the last known value for each record key. Kafka simply keeps the latest version of a record and deletes the older versions with the same key.

An example of log compaction use is when displaying the latest status of a cluster among thousands of clusters running. The current status of the cluster is written into Kafka and the topic is configured to compact the records. When this topic is consumed, it displays the latest status first and then a continuous stream of new statuses. 

