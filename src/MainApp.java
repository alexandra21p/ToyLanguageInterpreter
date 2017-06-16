import controller.Controller;
import repository.RepoInterface;
import repository.Repository;
import view.UI;

/**
 * Created by Alexandra on 02/11/2015.
 */
public class MainApp {
    public static void main(String[] args){
        RepoInterface repo = new Repository();
        //repo.savePrg();
        Controller ctrl = new Controller(repo);

        UI view = new UI(ctrl);
        view.runUI();

    }
}
