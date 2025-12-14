package web;

import static spark.Spark.*;

import model.GameModel;
import controller.GameController;

public class WebServer {

    public static void main(String[] args) {
        //everyone say thank you to stack overflow for showing me how to do this 
        port(Integer.parseInt(System.getenv().getOrDefault("PORT", "4567")));

        GameModel model = new GameModel();
        GameController controller = new GameController(model);

        get("/", (req, res) -> {
            res.type("text/html");
            return HTMLRenderer.renderBoard(model);
        });

        get("/flip/:r/:c", (req, res) -> {
            controller.handleCardClick(
                Integer.parseInt(req.params("r")),
                Integer.parseInt(req.params("c"))
            );
            res.redirect("/");
            return null;
        });

        get("/resolve", (req, res) -> {
            controller.resolveMismatch();
            res.redirect("/");
            return null;
        });

        get("/reset", (req, res) -> {
            controller.resetGame();
            res.redirect("/");
            return null;
        });
    }
}
