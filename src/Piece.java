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

    /* ========== Getter ========= */
    public char getId() {
        return this.id;
    }

    public List<Point> getCoords() {
        return this.coords;
    }
    
    /* ========== Setter ========= */
    public void addCoord(int x, int y) {
        this.coords.add(new Point(x, y));
    }

    public void setcoord(List<Point> newCoord){
        this.coords = newCoord;
    }
}
