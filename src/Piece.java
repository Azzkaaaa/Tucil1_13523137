import java.util.ArrayList;
import java.util.List;

public class Piece {
    private char id;
    private List<Point> coords;  

    /* ========== CONSTRUCTOR ========= */
    public Piece(char id) {
        this.id = id;
        this.coords = new ArrayList<>();
    }

    /* ========== Methods ========= */
    public char getId() {
        return id;
    }

    public List<Point> getCoords() {
        return coords;
    }

    public void addCoord(Point p) {
        coords.add(p);
    }

    public void rotate(){

    }

    public void flip(){
        
    }
}
