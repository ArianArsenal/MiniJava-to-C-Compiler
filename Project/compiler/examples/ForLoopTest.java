class ForLoopTest {
    public static void main(String[] a){
        System.out.println(new F_Looper().Run());
    }
}

class F_Looper {
    public int Run() {
        int i;
        //this for loop should print numbers 0 through 9
        for (i = 0; i < 10; i = i + 1) {
            System.out.println(i);
        }
        return 999;
    }
}