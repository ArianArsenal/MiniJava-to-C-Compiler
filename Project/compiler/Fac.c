#include "Fac.h"

int Fac_ComputeFac(Fac* self, int num) {
    int num_aux;
    if ((num < 1))     num_aux = 1;
 else     num_aux = (num * (self->vtable->ComputeFac(self, (num - 1))));

    return num_aux;
}

Fac* new_Fac() {
    Fac* obj = (Fac*)malloc(sizeof(Fac));
    obj->vtable = (Fac_vtable*)malloc(sizeof(Fac_vtable));
    obj->vtable->ComputeFac = Fac_ComputeFac;
    return obj;
}

