public class Point {

    /* ========== ATTRIBUTES ========== */
    private int X;
    private int Y;

    /* ============ GETTER ============ */
    public int getX() {
        return this.X;
    }

    public int getY() {
        return this.Y;
    }

    /* ========== CONSTRUCTOR ========= */
    public Point(int x, int y) {
        this.X = x;
        this.Y = y;
    }

    /* ============ Setter ============ */
    public void setX(int newX){
        this.X = newX;
    }

    public void setY(int newY){
        this.Y = newY;
    }
}