#include "runtime.h"

//the function to create a new int_array
int_array* create_int_array(int size) {
    int_array* arr = (int_array*)malloc(sizeof(int_array));
    if (arr == NULL) {
        fprintf(stderr, "Fatal error: memory allocation failed for int_array struct.\n");
        exit(1);
    }
    arr->length = size;
    arr->data = (int*)calloc(size, sizeof(int));
    if (arr->data == NULL && size > 0) {
        fprintf(stderr, "Fatal error: memory allocation failed for int_array data.\n");
        exit(1);
    }
    return arr;
}