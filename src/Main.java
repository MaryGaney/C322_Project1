import model.GameModel;
import controller.GameController;
import view.ConsoleView;

public class Main {
    public static void main(String[] args) {
        GameModel model = new GameModel();
        GameController controller = new GameController(model);
        ConsoleView view = new ConsoleView(model);

        //temp testing
        view.render();
        controller.handleCardClick(0,0);
        controller.handleCardClick(0,1);
        view.render();
    }
}
