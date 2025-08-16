#ifndef F_LOOPER_H
#define F_LOOPER_H

#include "runtime.h"

typedef struct F_Looper F_Looper;

typedef struct F_Looper_vtable F_Looper_vtable;

struct F_Looper_vtable {
    int (*Run)(F_Looper* self);
};

struct F_Looper {
    F_Looper_vtable* vtable;
};

F_Looper* new_F_Looper();

#endif // F_LOOPER_H
