import javax.swing.*;

public class NameLabel {
    public String color;
    public String name;

    public NameLabel() {
    }
    public NameLabel(JLabel name, String Name, String color){
        name.setText(Name);
        this.color=color;
        this.name = Name;

    }
    public static String setWinner(NameLabel white_name, NameLabel black_name, boolean isWhiteTurn){
        if (white_name.color.equals("white") && isWhiteTurn)
            return black_name.name;
        if (black_name.color.equals("black") && !isWhiteTurn)
            return white_name.name;
        return "No Winner";

    }
}


