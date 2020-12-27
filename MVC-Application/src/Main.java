import controller.Service;
import entities.Animals;
import entities.Fish;
import repository.Repo_Interface;
import repository.Repository;
import view.View;

public class Main {

    public static void main(String[] args) {
        Repo_Interface repo = new Repository();
        Service serv = new Service(repo);
        View view = new View(serv);
        view.start();
    }
}


