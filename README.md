# GRAPH 
##### The task description
Create the graph library. It should support 2 types of graphs - directed and undirected with 3 operations:
* addVertex - adds a vertex to the graph
* addEdge - adds an edge to the graph
* getPath - returns a list of edges between 2 vertices (path doesn't have to be optimal)    

Vertices should be of a user defined type.

Questions to be ready to answer (don't have to implement):
* add weighted edges support in your lib.
* add traverse function that will take a user defined function and apply it on every vertex of the graph.
* make your graph thread safe.  

##### Prerequisites 
* installed java
* installed maven

```
java -version
java version "14.0.1" 2020-04-14
Java(TM) SE Runtime Environment (build 14.0.1+7)
Java HotSpot(TM) 64-Bit Server VM (build 14.0.1+7, mixed mode, sharing)
```

```
mvn -version
Apache Maven 3.6.3 (NON-CANONICAL_2019-11-27T20:26:29Z_root)
Maven home: /opt/maven
Java version: 14.0.1, vendor: Oracle Corporation, runtime: /usr/lib/jvm/java-14-jdk
Default locale: en_US, platform encoding: UTF-8
OS name: "linux", version: "5.6.13-arch1-1", arch: "amd64", family: "unix"
```

##### Build
Run the following maven command in the project root. Check out the 'target' package and get your graph-x.x.x.jar library.
```
mvn clean package
```
