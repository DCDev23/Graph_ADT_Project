# Graph ADT Project
This project models students as nodes in a graph-based data structure, based on their academic performance, and uses 
the K-Means and Closeness Centrality algorithms to help identify social-economic features that have a significant impact
on student performance.

To run this project, jdk-17 installation is required. A runnable JAR file is included in the dist folder to start the application.

Use the following cmd line arguments to run the JAR file:

set PATH="path_to_jdk17_bin";%PATH%

java -jar StudentDataGUI.jar

# Overview
Students were modelled as nodes in a graph based on their academic performance.

Each student has five social-economic factors (features) that may have an impact on student performance.

The edges are the number of common features between two students.

By modeling this data as a graph, K-Means algorithm can be used to identify clusters that have unique trends or patterns and may require further investigation. 

K-Means algorithm can also help identify features that have a significant impact on student performance based on the frequency of features across clusters.

Closeness centrality can help identify key students of interest, identifying the most prevalent  features for these students can help identify the social-economic factors that are strongly impacting student performance.

# Screenshots

![image](https://github.com/DCDev23/Graph_ADT_Project/assets/140951773/1c1fdb67-4157-4b0f-905e-b6fcec61a33e)

GUI for editing student data, generating the graph data structures and visualizing graph algorithms

![image](https://github.com/DCDev23/Graph_ADT_Project/assets/140951773/b515163e-979f-4244-8458-4359d92c18c8)

Closeness Centrality visualization

![image](https://github.com/DCDev23/Graph_ADT_Project/assets/140951773/ee4ee3d7-7ef4-4cdd-8763-7656c7a9645e)

K-Means algorithm visualization



