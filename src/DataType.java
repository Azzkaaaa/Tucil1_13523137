import java.util.ArrayList;
import java.util.List;

public class DataType {
    private final int jumlahPiece;
    private final int panjangPapan;
    private final int lebarPapan;
    private final String caseType;
    private final char[][] board;
    private final List<Piece> pieces;

    /* ========== CONSTRUCTOR ========= */
    public DataType(int jumlahPiece, int panjangPapan, int lebarPapan, String caseType) {
        this.jumlahPiece = jumlahPiece;
        this.panjangPapan = panjangPapan;
        this.lebarPapan = lebarPapan;
        this.caseType = caseType;
        this.board = new char[lebarPapan][panjangPapan];
        this.pieces = new ArrayList<>();
    }

    /* ========== Methods ========= */
    public int getJumlahPiece() {
        return jumlahPiece;
    }
    public int getPanjangPapan() {
        return panjangPapan;
    }
    public int getLebarPapan() {
        return lebarPapan;
    }
    public String getCaseType() {
        return caseType;
    }
    public char[][] getBoard() {
        return board;
    }

    public void addPiece(Piece p) {
        this.pieces.add(p);
    }

    public List<Piece> getPieces() {
        return this.pieces;
    }

    public void setElmtBoard(int x, int y, char c){
        this.board[x][y] = c;
    }
}
