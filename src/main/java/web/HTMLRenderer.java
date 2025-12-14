package web;

import model.GameModel;
import model.Card;
import model.Player;

public class HTMLRenderer {

    public static String renderBoard(GameModel model) {
        StringBuilder html = new StringBuilder();

        html.append("<html><head>");
        html.append("<meta charset='UTF-8'>");

        if (model.isMismatchPending()) {
            html.append("<meta http-equiv='refresh' content='2;URL=/resolve'>");
        }

        //make it look nice, modeled after static html css page 
        html.append("<style>");
        html.append("body {");
        html.append("font-family: Arial, sans-serif;");
        html.append("background: linear-gradient(135deg, #71A754, #E7E7E7);");
        html.append("display: flex;");
        html.append("justify-content: center;");
        html.append("align-items: center;");
        html.append("height: 100vh;");
        html.append("}");
        html.append(".container {");
        html.append("background: white;");
        html.append("padding: 20px 30px;");
        html.append("border-radius: 12px;");
        html.append("box-shadow: 0 8px 20px rgba(0,0,0,0.2);");
        html.append("text-align: center;");
        html.append("}");
        html.append("table {");
        html.append("margin: 20px auto;");
        html.append("border-collapse: collapse;");
        html.append("}");
        html.append("td {");
        html.append("width: 80px;");
        html.append("height: 80px;");
        html.append("font-size: 40px;");
        html.append("text-align: center;");
        html.append("border-radius: 10px;");
        html.append("border: 2px solid #333;");
        html.append("background: #E7E7E7;");
        html.append("}");
        html.append("a {");
        html.append("text-decoration: none;");
        html.append("display: block;");
        html.append("width: 100%;");
        html.append("height: 100%;");
        html.append("}");
        html.append(".score { margin-top: 10px; }");
        html.append("button {");
        html.append("margin-top: 15px;");
        html.append("padding: 10px 20px;");
        html.append("font-size: 16px;");
        html.append("border-radius: 8px;");
        html.append("border: none;");
        html.append("cursor: pointer;");
        html.append("background-color: #71A754;");
        html.append("color: white;");
        html.append("}");
        html.append("</style>");

        html.append("</head><body>");
        html.append("<div class='container'>");

        html.append("<h2>Matching Game</h2>");

        if (model.isGameOver()) {
            Player p1 = model.getPlayers()[0];
            Player p2 = model.getPlayers()[1];

            html.append("<h3>Game Over!</h3>");
            html.append("<p>");
            html.append(p1.getName()).append(": ").append(p1.getScore());
            html.append(" | ");
            html.append(p2.getName()).append(": ").append(p2.getScore());
            html.append("</p>");

            if (p1.getScore() > p2.getScore()) {
                html.append("<h3>Player 1 Wins!</h3>");
            } else if (p2.getScore() > p1.getScore()) {
                html.append("<h3>Player 2 Wins!</h3>");
            } else {
                html.append("<h3>It's a tie!</h3>");
            }
        } else {
            html.append("<h3>Current Turn: ");
            html.append(model.getCurrentPlayer().getName());
            html.append("</h3>");
        }

        html.append("<table>");
        for (int r = 0; r < GameModel.ROWS; r++) {
            html.append("<tr>");
            for (int c = 0; c < GameModel.COLS; c++) {
                Card card = model.getCard(r, c);
                html.append("<td>");
                if (card.isFaceUp()) {
                    html.append(card.getEmoji());
                } else {
                    html.append("<a href='/flip/")
                        .append(r).append("/").append(c)
                        .append("'>⬜</a>");
                }
                html.append("</td>");
            }
            html.append("</tr>");
        }
        html.append("</table>");

        html.append("<div class='score'>");
        for (Player p : model.getPlayers()) {
            html.append(p.getName())
                .append(": ")
                .append(p.getScore())
                .append(" ");
        }
        html.append("</div>");

        html.append("<form action='/reset'><button>New Game</button></form>");

        html.append("</div></body></html>");
        return html.toString();
    }
}
