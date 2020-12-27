package repository;

import entities.Animals;

public class Repository implements Repo_Interface {
    private Animals[] animals;
    private int size = 0;

    public Repository() {
        this.animals = new Animals[20];

    }

    ;

    @Override
    public void add(Animals a) throws Exception {
        if(a.getAge() < 0)
        {
            throw new Exception("The age can't be a negative number");
        }
        if(a.getId() < 0)
        {
            throw new Exception("The ID must be a positive number");
        }
        for (int i = 0; i < size; i++) {
            if (a.getId() == this.animals[i].getId()) {
                throw new Exception("This animal already exists !");
            }
        }
        this.animals[this.size] = a;
        this.size++;

    }

    @Override
    public void remove(int id) throws Exception{
        int ok = 0;
        if(id < 0)
        {
            throw new Exception("This ID must be a positive number !");
        }
        for (int i = 0; i < size; i++) {
            if (this.animals[i].getId() == id) {
                ok = 1;
                for (int j = i; j <= this.size - 1; j++) {
                    this.animals[j] = this.animals[j + 1];

                }
                this.size -- ;
                break;
            }

        }
        if(ok == 0)
        {
            throw new Exception("There is no animal with this id !");
        }
    }

    @Override
    public Animals[] returnAnimals() {
        return this.animals;
    }

    @Override
    public int returnSize() {
        return this.size;
    }


}
