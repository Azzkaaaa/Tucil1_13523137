import java.util.ArrayList;
import java.util.List;

public class Piece {
    private final char id;
    private final List<Point> coords;
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
    
    public void addCoord(int x, int y) {
        this.coords.add(new Point(x, y));
    }
}
