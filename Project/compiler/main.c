#include "runtime.h"
#include "F_Looper.h"

int main() {
        printf("%d\n", new_F_Looper()->vtable->Run(new_F_Looper()));
    return 0;
}
