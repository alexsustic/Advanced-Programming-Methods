package entities;

public class Turtle implements Animals{
    private int id;
    private String species;
    private int age;

    public Turtle(int id, String species, int age ){this.id = id; this.species = species; this.age = age;}

    @Override
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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
    public String toString(){
        return "Turtle || ID: " +this.id + " || Species: " + this.species + " || Age: " + this.age;
    }
}
