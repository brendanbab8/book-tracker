JFLAGS = -g
JC = javac
RUN = java
.SUFFIXES: .java .class
.java.class:
				$(JC) $(JFLAGS) $*.java

CLASSES = \
				Book.java \
				Main.java \
				Shelf.java \
				Library.java \
				
default: classes

classes: $(CLASSES:.java=.class)

clean:
	$(RM) *.class

run:
	$(RUN) Main