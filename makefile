file = EuclideanAlgorithm_Practice
$(file).class: $(file).java
	javac $(file).java
java: $(file).class
	java $(file)
py: $(file).py
	python3 $(file).py
$(file).out: $(file).cpp
	g++ $(file).cpp -o $(file).out
cpp: $(file).out
	./$(file).out