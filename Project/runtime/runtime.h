#ifndef RUNTIME_H
#define RUNTIME_H

#include <stdlib.h>
#include <stdio.h>
#include <stdbool.h>

//custom struct to represent Mini-Java's int[]
typedef struct {
    int length;
    int* data;
} int_array;

//function to create and initialize a new int_array
int_array* create_int_array(int size);

#endif //RUNTIME_H