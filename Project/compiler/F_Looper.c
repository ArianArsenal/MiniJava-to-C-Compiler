#include "F_Looper.h"

int F_Looper_Run(F_Looper* self) {
    int i;
    for (i = 0; (i < 10); i = (i + 1)) {
        printf("%d\n", i);
    }

    return 999;
}

F_Looper* new_F_Looper() {
    F_Looper* obj = (F_Looper*)malloc(sizeof(F_Looper));
    obj->vtable = (F_Looper_vtable*)malloc(sizeof(F_Looper_vtable));
    obj->vtable->Run = F_Looper_Run;
    return obj;
}

