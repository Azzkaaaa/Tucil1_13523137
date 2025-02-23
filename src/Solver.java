
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class Solver {
    private DataType data;
    private long iteration = 0;
    private boolean solFound = false;

    /* ========== Constructor ========= */
    public Solver(DataType data){
        this.data = data;
    }

    /* ========== Getter ========= */
    public long getIteration(){
        return iteration;
    }

    public boolean  getsolFound(){
        return solFound;
    }
    /* ========== Methods ========= */
    /* ========== Print ========= */
    public void printSol(){
        char[][] board = data.getBoard();
        int row = data.getLebarPapan();
        int col = data.getPanjangPapan();

        for (int i = 0; i < row; i++){
            for (int j = 0; j < col; j++){
                char pieceID = board[i][j];
                System.out.print(getColorForPiece(pieceID) + pieceID + "\u001B[0m ");
            }
            System.out.println();
        }
    }
    /* ========== Color ========= */
    private String getColorForPiece(char pieceId) {
        switch (pieceId) {
            case 'A': return "\u001B[31m"; 
            case 'B': return "\u001B[32m"; 
            case 'C': return "\u001B[33m"; 
            case 'D': return "\u001B[34m"; 
            case 'E': return "\u001B[35m"; 
            case 'F': return "\u001B[36m"; 
            case 'G': return "\u001B[91m"; 
            case 'H': return "\u001B[92m"; 
            case 'I': return "\u001B[93m"; 
            case 'J': return "\u001B[94m"; 
            case 'K': return "\u001B[95m"; 
            case 'L': return "\u001B[96m"; 
            case 'M': return "\u001B[37m"; 
            case 'N': return "\u001B[90m"; 
            case 'O': return "\u001B[1;31m"; 
            case 'P': return "\u001B[1;32m";
            case 'Q': return "\u001B[1;33m";
            case 'R': return "\u001B[1;34m";
            case 'S': return "\u001B[1;35m";
            case 'T': return "\u001B[1;36m";
            case 'U': return "\u001B[1;91m";
            case 'V': return "\u001B[1;92m";
            case 'W': return "\u001B[1;93m";
            case 'X': return "\u001B[1;94m";
            case 'Y': return "\u001B[1;95m";
            case 'Z': return "\u001B[1;96m";
            default:  return "\u001B[0m";  
        }
    }
    /* ========== Save ========= */
    public void saveSol(String filename, long elapsed){
        if (!filename.endsWith(".txt")) {
            filename += ".txt";
        }
        String path = "save/" + filename;

        try (PrintWriter pw = new PrintWriter(new FileWriter(path))){
            char[][] board = data.getBoard();
            int row = data.getLebarPapan();
            int col = data.getPanjangPapan();

            for (int i = 0; i < row; i++){
                for (int j = 0; j < col; j++){
                    pw.print(board[i][j]);
                }
                pw.println();
            }
        } catch(IOException e){
            e.printStackTrace();
        }

    }
    /* ========== Solve ========= */
    public boolean solve(int pieceIndex){
        if (pieceIndex == data.getPieces().size()){
            if (isBoardFull()){
                solFound = true;
                return true;
            }
            return false;
        }

        Piece original = data.getPieces().get(pieceIndex);

        List<Piece> transform = transAll(original);

        for (Piece trans : transform){
            for (int i = 0; i < data.getLebarPapan(); i++){
                for (int j = 0; j < data.getPanjangPapan(); j++){
                    if (canPlace(trans, i, j)){
                        iteration++;
                        placePiece(trans, i, j);
                        if (solve(pieceIndex + 1)){
                            return true;
                        }
                        removePiece(trans, i, j);
                    }
                }
            }
        }
        return false;
    }

    private boolean isBoardFull(){
        char[][] board = data.getBoard();

        for (int i = 0; i < data.getLebarPapan(); i++){
            for (int j = 0; j < data.getPanjangPapan(); j++){
                if (board[i][j] == ' '){
                    return false;
                }
            }
        }
        return true;
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

    /* ========== Placing ========= */
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
