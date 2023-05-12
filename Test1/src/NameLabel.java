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
    public static String setWinner(NameLabel name1, NameLabel name2, boolean turn){
        if (name1.color == "white" && turn)
            return name2.name;
        if (name2.color == "black" && !turn)
            return name1.name;
        return "No Winner";

    }
}


