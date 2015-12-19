Amogh Lale
alale1@binghamton.edu

Running  on remote.cs.binghamton.edu
(It takes approximately 1 minute to generate output)

Features:
1. I used Item based similarity to predict rating. In that, I compute item similarity using Adjusted Cosine Similarity  and then I calculate weighted sum to predict final rating.
2. Item based similarity provide better quality and high performance than user based prediction algorithm.
3. I have taken threshold similarity as 0.3, which means, if item similarity of 2 items is greater than 0.3 then they are said to be similar and hence, are added to Hashmap.
4. Cold start problem handled

Steps to compile and run:
make
java Driver

Results:

remote04:~/coSys> javac *.java

remote04:~/RecoSys> ls

Driver.class	    FileProcessorInterface.class  PredictionAlgo.class	  PredictionAlgo.java

Driver.java	    FileProcessorInterface.java   PredictionAlgoInterface.class  train_all_txt.txt

FileProcessor.class  FileProcessor.java	  PredictionAlgoInterface.java  

remote04:~/RecoSys> java Driver

Total time required is: 58295

remote04:~/RecoSys> ls

Driver.class  FileProcessor.class	    FileProcessorInterface.java  output.txt	      PredictionAlgoInterface.class  PredictionAlgo.java

Driver.java   FileProcessorInterface.class  FileProcessor.java	PredictionAlgo.class  PredictionAlgoInterface.java   train_all_txt.txt

References:
[1] Badrul Sarwar, George Karypis, Joseph Konstan, and John Riedl. Item –based Collaborative Filtering Recommendation Algorithms
[2] Paul Resnick and Hal R. Varian, Guest Editors. Recommender Systems
[3] John S. Breese, David Heckerman Carl Kadie. Empirical Analysis of Predictive Algorithms for Collaborative Filtering
[4] http://stackoverflow.com/questions/7899525/how-to-split-a-string-by-space
[5]http://stackoverflow.com/questions/1746501/can-someone-give-an-example-of-cosine-similarity-in-a-very-simple-graphical-wa
http://stackoverflow.com/questions/2503363/how-can-i-get-a-first-element-from-a-sorted-list
http://stackoverflow.com/questions/8235076/how-to-round-double-to-nearest-whole-number-and-then-convert-to-a-float
   
