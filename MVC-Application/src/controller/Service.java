package controller;

import entities.Animals;
import repository.Repo_Interface;

public class Service  {
    private Repo_Interface repo;

    public Service(Repo_Interface r) {
        this.repo = r;
    }

    public void add(Animals a) throws Exception {
        this.repo.add(a);
    }

    public void remove(int id) throws Exception {
        this.repo.remove(id);
    }

    public Animals[] returnAnimalsMinimum1Year(){

        Animals [] animals_MinimumAge= new Animals[20];
        Animals[] animals = new Animals[20];
        animals = this.repo.returnAnimals();
        int size = this.repo.returnSize();
        int j=0;
        for(int i = 0; i< size; i++){
            if(animals[i].getAge() >= 12){
                animals_MinimumAge[j] = animals[i];
                j++;
            }
        }
     return animals_MinimumAge;
    }

    public Animals[] returnAnimals(){
        return this.repo.returnAnimals();
    }
}
