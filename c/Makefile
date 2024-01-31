CC = gcc
CFLAGS = -Wall -Werror -Iinclude # https://gcc.gnu.org/onlinedocs/gcc/Warning-Options.html
DEPS = config.h input.h job.h
OBJ = main.o job.o input.o
EXEC = produceJobs

all: $(EXEC) 

%.o: %.c $(DEPS) # respect header file dependencies
	$(CC) -c -o $@ $< $(CFLAGS)

$(EXEC): $(OBJ) 
	$(CC) -o $@ $^ $(CFLAGS)

.PHONY: clean
clean:
	rm -f $(OBJ) $(EXEC)
