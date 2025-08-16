// examples/LoopTest.java

class LoopTest {
    public static void main(String[] a){
        System.out.println(new Looper().Run());
    }
}

class Looper {
    public int Run() {
        int i;
        i = 0;
        
        while (i < 10) {
            i = i + 1;
            
            if (i == 5) {
                //this should skip the number 5
                System.out.println(999); // Marker for continue
                continue;
            }
            
            if (i == 8) {
                //this should stop the loop
                System.out.println(888); // Marker for break
                break;
            }
            
            System.out.println(i);
        }
        return 0;
    }
}