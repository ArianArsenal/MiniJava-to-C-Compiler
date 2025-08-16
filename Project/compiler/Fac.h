#ifndef FAC_H
#define FAC_H

#include "runtime.h"

typedef struct Fac Fac;

typedef struct Fac_vtable Fac_vtable;

struct Fac_vtable {
    int (*ComputeFac)(Fac* self, int num);
};

struct Fac {
    Fac_vtable* vtable;
};

Fac* new_Fac();

#endif // FAC_H
