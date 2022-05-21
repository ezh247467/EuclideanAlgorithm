javafile = EuclideanAlgorithm_Practice
$(javafile).class : $(javafile).java
	javac $(javafile).java
run : $(javafile).class
	java $(javafile)