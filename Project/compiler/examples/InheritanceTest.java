class InheritanceTest {
    public static void main(String[] a){
        // The return value of Start is ignored.
        System.out.println(new Runner().Start());
    }
}

//simple base class
class Animal {
    public int makeSound(){
        System.out.println(111); //Animal sound
        return 0;
    }
}

//class that extends Animal and overrides its method
class Dog extends Animal {
    public int makeSound(){
        System.out.println(222); //Dog sound (override)
        return 1;
    }

    public int fetch(){
        System.out.println(333); //Dog-specific method
        return 2;
    }
}

//class to run the test
class Runner {
    public int Start(){
        Animal myAnimal;
        Dog myDog;
        int ignore;

        myAnimal = new Animal();
        myDog = new Dog();

        //should print the animal sound (111)
        ignore = myAnimal.makeSound();

        //should print the dog sound (222)
        ignore = myDog.makeSound();

        //should print the dog-specific sound (333)
        ignore = myDog.fetch();

        //this demonstrates polymorphism. We treat a Dog as an Animal.
        //it should still print the dog sound (222) because of the v-table.
        myAnimal = myDog;
        ignore = myAnimal.makeSound();

        return 999;
    }
}