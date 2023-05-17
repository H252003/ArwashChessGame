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
        //returns name of winner in string
        //winner is the player in the opposite turn
        if (isWhiteTurn)
            return black_name.name;
        else
            return white_name.name;
    }
}


