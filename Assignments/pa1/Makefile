#-------------------------------------
# A Makefile with macros
#-------------------------------------

JAVASRC   = Run.java Recursion.java
SOURCES   = README Makefile $(JAVASRC)
MAINCLASS = Run
CLASSES   = Run.class Recursion.class 
JARFILE   = Run

all: $(JARFILE)
$(JARFILE): $(CLASSES)
	echo Main-class: $(MAINCLASS) > Manifest
	jar cvfm $(JARFILE) Manifest $(CLASSES)
	rm Manifest
	chmod +x $(JARFILE)
$(CLASSES): $(JAVASRC)
	javac -Xlint $(JAVASRC)
clean:
	rm $(CLASSES) $(JARFILE)
	
