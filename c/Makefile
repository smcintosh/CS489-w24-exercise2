OBJECTS = \
	  input.o \
	  job.o \
	  main.o

DEPFILES = $(patsubst %.o, %.d, $(OBJECTS))

main: $(OBJECTS)

CC = gcc
CPPFLAGS = -MMD -Iinclude

-include $(wildcard *.d)

.PHONY: clean
clean:
	rm -f main $(OBJECTS) $(DEPFILES)
