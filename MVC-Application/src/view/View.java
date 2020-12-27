package view;

import controller.Service;
import entities.Animals;
import entities.Fish;
import entities.Turtle;

import java.util.Scanner;

public class View {
    private Service serv;
    public View(Service s){ this.serv=s;}
    public void printMenu(){
        System.out.println("1. Add a new fish");
        System.out.println("2. Add a new turtle");
        System.out.println("3. Remove an animal");
        System.out.println("4. Display all the animals");
        System.out.println("5. Display all the animals older than 1 year (12 months)");
        System.out.println("6. Exit");
        System.out.println("\n");
    }

    public void addFish(){
        String species;
        Scanner s = new Scanner(System.in);
        System.out.println("The id: ");
        int id = Integer.parseInt(s.nextLine());
        System.out.println("The name of the species: ");
        species = s.nextLine();
        System.out.println("The age of the fish (months): ");
        int age = Integer.parseInt(s.nextLine());

        Animals newFish = new Fish(id,species,age);
        try{
            this.serv.add(newFish);
        }
        catch(Exception ex){
            System.out.println(ex);;
        }


    }

    public void addTurtle(){
        int id, age;
        String species;
        Scanner s = new Scanner(System.in);
        System.out.println("The id: ");
        id = Integer.parseInt(s.nextLine());
        System.out.println("The name of the species: ");
        species = s.nextLine();
        System.out.println("The age of the turtle (months): ");
        age = Integer.parseInt(s.nextLine());
        Animals newFish = new Turtle(id,species,age);
        try{
            this.serv.add(newFish);
        }
        catch(Exception ex){
            System.out.println(ex);
        }


    }

    public void deleteAnimal(){
        int id;
        System.out.println("The id: ");
        Scanner s = new Scanner(System.in);
        id = Integer.parseInt(s.nextLine());
        try{
            this.serv.remove(id);
        } catch(Exception ex)
        {
            System.out.println(ex);
        }
    }

    public void displayAllAnimals(){
        Animals[] animals = new Animals[20];
        animals = this.serv.returnAnimals();
        for(int i = 0; i< animals.length; i++){
            if(animals[i] != null) {
                System.out.println(animals[i]);
            }
        }
        System.out.println("\n");


    }

    public void displayAnimalsOlder(){
        Animals[] animals = new Animals[20];
        animals = this.serv.returnAnimalsMinimum1Year();
        for(int i = 0; i< animals.length; i++){
            if(animals[i] != null) {
                System.out.println(animals[i]);
            }
        }
        System.out.println("\n");
    }

    public void start(){
        int command = 1; // default
        Scanner s = new Scanner(System.in);
        this.printMenu();

        int out =0;
        while(true && out == 0){
            System.out.println("Choose one option from those mentioned above: ");
            try {
                command = Integer.parseInt(s.nextLine());
                if(command <= 0 || command >6) {
                    System.out.println("This command doesn't exist!");
                }
                switch (command) {
                    case 1:
                        this.addFish();
                        break;
                    case 2:
                        this.addTurtle();
                        break;
                    case 3:
                        this.deleteAnimal();
                        break;
                    case 4:
                        this.displayAllAnimals();
                        break;
                    case 5:
                        this.displayAnimalsOlder();
                        break;
                    case 6:
                        out = 1;
                        break;

                }
            } catch(Exception ex)
            {
                System.out.println("This command doesn't exist!");
            }

        }
    }


}
