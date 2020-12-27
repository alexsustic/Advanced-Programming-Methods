package view;

public abstract class Command {
    private final String key;
    private final String description;

    Command(String k, String d){this.key = k; this.description = d;}

    public abstract void execute();

    public String getKey(){return this.key;}

    public String getDescription(){return this.description;}
}
