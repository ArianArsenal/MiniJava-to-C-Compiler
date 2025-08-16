

class FinalTest {
    public static void main(String[] a){
        //it will print 0 at the end.
        System.out.println(new TestRunner().Start());
    }
}

class Calculator {
    public int add(int num1, int num2) {
        //add method
        System.out.println(100); 
        return num1 + num2;
    }
}

class ScientificCalculator extends Calculator {
    //Overrides the parent's add method
    public int add(int num1, int num2) {
        System.out.println(200); //overridden method was called
        return num1 + num2;
    }

    //new method specific to this class
    public int power(int base, int exp) {
        int i;
        int result;
        result = 1;
        for (i = 0; i < exp; i = i + 1) {
            result = result * base;
        }
        return result;
    }
}

class TestRunner {
    public int Start() {
        Calculator calc;
        ScientificCalculator sci_calc;
        int result;
        
        calc = new Calculator();
        sci_calc = new ScientificCalculator();

        //call the base method and print 100
        result = calc.add(10, 5);
        System.out.println(result); // Should print 15

        //call the overridden method and print 200
        result = sci_calc.add(20, 5); 
        System.out.println(result); //print 25

        //call the OVERRIDDEN method and print 200
        calc = sci_calc; 
        result = calc.add(30, 5);
        System.out.println(result); //print 35

        //power method
        result = sci_calc.power(2, 10);
        System.out.println(result); //print 1024

        return 0;
    }
}