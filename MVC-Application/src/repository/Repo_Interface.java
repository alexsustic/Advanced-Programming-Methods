package repository;

import entities.Animals;

public interface Repo_Interface {
    void add(Animals a) throws Exception;
    void remove(int id) throws Exception;
    public Animals[] returnAnimals();
    public int returnSize();
}
