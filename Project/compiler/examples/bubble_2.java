// examples/bubble_2.java

class BubbleSort2 {
    public static void main(String[] a){
	    System.out.println(new BBS2().Start(10));
    }
}

// This class performs a bubble sort on an integer array.
class BBS2 {
    int[] number;
    int size;

    public int Start(int sz){
        int ignore;
        ignore = this.Init(sz);

        System.out.println(99999); // Unsorted
        ignore = this.Print();

        ignore = this.Sort();

        System.out.println(88888); // Sorted
        ignore = this.Print();
        
        return 0;
    }

    // Sorts the array using bubble sort.
    public int Sort(){
        int i;
        int j;
        int temp;
        int outer_limit;
        int inner_limit;

        i = size - 1;
        outer_limit = 0 - 1;
        while (outer_limit < i) {
            j = 1;
            inner_limit = i + 1;
            while (j < inner_limit){
                if (number[j] < number[j-1]) {
                    temp = number[j-1];
                    number[j-1] = number[j];
                    number[j] = temp;
                }
                else {
                    // Do nothing
                }
                j = j + 1;
            }
            i = i - 1;
        }
        return 0;
    }

    // Prints the elements of the array.
    public int Print(){
        int j;
        j = 0;
        while (j < size) {
            System.out.println(number[j]);
            j = j + 1;
        }
        return 0;
    }

    // Initializes the array with sample numbers.
    public int Init(int sz){
        size = sz;
        number = new int[sz];
        
        number[0] = 20;
        number[1] = 7; 
        number[2] = 12;
        number[3] = 18;
        number[4] = 2; 
        number[5] = 11;
        number[6] = 6; 
        number[7] = 9; 
        number[8] = 19; 
        number[9] = 5;
        
        return 0;	
    }
}