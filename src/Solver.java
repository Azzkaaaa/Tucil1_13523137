
import java.util.ArrayList;
import java.util.List;

public class Solver {
    private DataType data;

    public Solver(DataType data){
        this.data = data;
    }
    /* ========== Methods ========= */
    /* ========== Solve ========= */
    public void solve(int i){

    }

    /* ========== Rotate ========= */
    public void rotate90(Piece piece){
        List<Point> oldCoords = piece.getCoords();
        List<Point> newCoords = new ArrayList<>();

        for (Point p : oldCoords){
            int oldX = p.getX();
            int oldY = p.getY();

            int newX = oldY;
            int newY = -oldX;
            newCoords.add(new Point(newX, newY));
        }

        piece.setcoord(newCoords);
        normalize(piece);
    }

    public void rotate180(Piece piece){
        rotate90(piece);
        rotate90(piece);
    }

    public void rotate270(Piece piece){
        rotate180(piece);
        rotate90(piece);
    }

    /* ========== Mirror ========= */
    public void mirrorHorizontal(Piece piece){
        List<Point> oldCoords = piece.getCoords();
        List<Point> newCoords = new ArrayList<>();

        for (Point p : oldCoords){
            int oldX = p.getX();
            int oldY = p.getY();

            int newX = -oldX;
            int newY = oldY;
            newCoords.add(new Point(newX, newY));
        }

        piece.setcoord(newCoords);
        normalize(piece);
    }

    public void mirrorVertical(Piece piece){
        List<Point> oldCoords = piece.getCoords();
        List<Point> newCoords = new ArrayList<>();

        for (Point p : oldCoords){
            int oldX = p.getX();
            int oldY = p.getY();

            int newX = oldX;
            int newY = -oldY;
            newCoords.add(new Point(newX, newY));
        }

        piece.setcoord(newCoords);
        normalize(piece);
    }
    
    /* ========== Normalize ========= */

    private void normalize(Piece piece){
        List<Point> Coords = piece.getCoords();

        int minX = Integer.MAX_VALUE;
        int minY = Integer.MAX_VALUE;

        for (Point p : Coords){
            if (p.getX() < minX){
                minX = p.getX();
            }
            if (p.getY() < minY){
                minY = p.getY();
            }
        }

        for (Point p : Coords){
            p.setX(p.getX() - minX);
            p.setY(p.getY() - minY);
        }
    }

    public boolean canPlace(Piece piece, int i, int j){
        int row = data.getLebarPapan();
        int col = data.getPanjangPapan();
        char[][] board = data.getBoard();

        for (Point coord : piece.getCoords()){
            int coordX = coord.getX();
            int coordY = coord.getY();

            int boardX = coordX + i;
            int boardY = coordY + j;

            if (boardX < 0 || boardX >= row || boardY < 0 || boardY >= col){
                return false;
            }

            if (board[boardX][boardY] != '\u0000' && board[boardX][boardY] != ' '){
                return false;
            }
        }

        return true;
    }

    public void placePiece(Piece piece, int i, int j){
        char charID = piece.getId();
        char[][] board = data.getBoard();

        for(Point coord : piece.getCoords()){
            int coordX = coord.getX();
            int coordY = coord.getY();

            int boardX = coordX + i;
            int boardY = coordY + j;

            board[boardX][boardY] = charID;
        }
    }

    public void removePiece(Piece piece, int i, int j){
        char[][] board = data.getBoard();

        for (Point coord : piece.getCoords()){
            int coordX = coord.getX();
            int coordY = coord.getY();

            int boardX = coordX + i;
            int boardY = coordY + j;
            board[boardX][boardY] = ' ';
        }
    }

    /* ========== Healper ========= */
    public List<Piece> transAll(Piece original){
        List<Piece> result = new ArrayList<>();

        Piece rot0 = copyPiece(original);
        normalize(rot0);
        result.add(rot0);

        Piece rot90 = copyPiece(original);
        rotate90(rot90);
        result.add(rot90);

        Piece rot180 = copyPiece(original);
        rotate180(rot180);
        result.add(rot180);

        Piece rot270 = copyPiece(original);
        rotate270(rot270);
        result.add(rot270);

        Piece mirrHorizontal = copyPiece(original);
        mirrorHorizontal(mirrHorizontal);
        result.add(mirrHorizontal);

        Piece mirrVertical = copyPiece(original);
        mirrorVertical(mirrVertical);
        result.add(mirrVertical);

        return result;
    }

    public Piece copyPiece(Piece original){
        Piece copy = new Piece(original.getId());

        for (Point p : original.getCoords()){
            copy.addCoord(p.getX(), p.getY());
        }

        return copy;
    }
}
