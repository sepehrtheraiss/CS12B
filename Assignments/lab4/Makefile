#-------------------------------------
# Another Makefile with macros
#-------------------------------------

FLAGS     = -std=c99 -Wall
SOURCES   = charType.c 
OBJECTS   = charType.o 
EXEBIN    = charType
OUTPUT    = leak_check
all: $(EXEBIN)
$(EXEBIN): $(OBJECTS)
	gcc -o $(EXEBIN) $(OBJECTS)
$(OBJECTS): $(SOURCES)
	gcc -c $(FLAGS) $(SOURCES)
clean:
	rm -f $(EXEBIN) $(OBJECTS)
check:
	valgrind --leak-check=full $(EXEBIN) $(SOURCES) $(OUTPUT)
