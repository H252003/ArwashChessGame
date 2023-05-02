import javax.swing.*;

public class square extends JButton {
    public square (Piece p) {
        piece = p ;


}
public square (){

}
    public Piece piece ;


    @Override
    public Icon getIcon() {
        if(piece != null)
        return piece.icon;
        return null;
    }
}
