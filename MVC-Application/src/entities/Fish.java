package entities;

public class Fish implements Animals {
    private String species;
    private int age;
    private int id;

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    @Override
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Fish(int id, String species, int age){this.species = species; this.id = id; this.age = age; }

    @Override
    public String toString(){
        return "Fish || ID: " +this.id + " || Species: " + this.species + " || Age: " + this.age;
    }
}
