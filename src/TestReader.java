import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TestReader {
    /* ========== Methods ========= */
    public DataType readDataFile(String filePath) throws FileNotFoundException {
        Scanner sc = new Scanner(new File(filePath));
        List<String> lines = new ArrayList<>();
        while (sc.hasNextLine()) {
            lines.add(sc.nextLine());
        }
        sc.close();
        String[] firstLine = lines.get(0).split(" ");
        int lebarPapan = Integer.parseInt(firstLine[0]);
        int panjangPapan = Integer.parseInt(firstLine[1]);
        int jumlahPiece = Integer.parseInt(firstLine[2]);

        String caseType = lines.get(1);
        
        DataType dt = new DataType(jumlahPiece, panjangPapan, lebarPapan, caseType);

        int index = 2; 
        for (int i = 0; i < jumlahPiece; i++) {
            PieceResult pr = readOnePiece(lines, index);
            dt.addPiece(pr.piece);
            index += pr.lineCount; 
        }

        System.out.println("M = " + lebarPapan + ", N = " + panjangPapan + ", P = " + jumlahPiece);
        System.out.println("Case = " + caseType);
        for (Piece pc : dt.getPieces()) {
            System.out.println("Piece " + pc.getId() + " coords = " + formatCoords(pc.getCoords()));
        }

        return dt;
    }

    /* ========== CONSTRUCTOR Healper ========= */
    private static class PieceResult {
        Piece piece;
        int lineCount;
    
        public PieceResult(Piece piece, int lineCount) {
            this.piece = piece;
            this.lineCount = lineCount;
        }
    }

    private PieceResult readOnePiece(List<String> lines, int startIndex) {
        String firstLine = lines.get(startIndex);
        int fnsi = 0;
        while (fnsi < firstLine.length() && firstLine.charAt(fnsi) == ' ') {
            fnsi++;
        }
        if (fnsi >= firstLine.length()) {
            return new PieceResult(new Piece(' '), 1);
        }
        char pieceId = firstLine.charAt(fnsi);
    
        Piece piece = new Piece(pieceId);
        int lineCount = 0, y = 0, idx = startIndex;
    
        while (idx < lines.size()) {
            String line = lines.get(idx);
            if (line.trim().isEmpty()) {
                break;
            }
            int pos = 0;
            while (pos < line.length() && line.charAt(pos) == ' ') {
                pos++;
            }
            if (pos < line.length() && line.charAt(pos) == pieceId) {
                convertLineToCoords(line, piece, y);
                y++;
                idx++;
                lineCount++;
            } else {
                break;
            }
        }
    
        return new PieceResult(piece, lineCount);
    }
    
    
    /* ========== Healper ========= */
    private void convertLineToCoords(String line, Piece piece, int y) {
        for (int x = 0; x < line.length(); x++) {
            char c = line.charAt(x);
            if (c != ' ') {
                piece.addCoord(x, y);
            }
        }
    }


    private String formatCoords(List<Point> coords) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (Point p : coords) {
            sb.append("(").append(p.getX()).append(",").append(p.getY()).append(") ");
        }
        sb.append("]");
        return sb.toString();
    }
    
}
