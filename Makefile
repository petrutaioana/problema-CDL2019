JC = javac
JR = java
F = find
RM = rm -rf

build:
	@$(JC) Solution.java
	@$(JC) Input.java

run: Solution.class Input.class
	@$(JR) Solution $(var)

clean:
	@$(RM) *.class

