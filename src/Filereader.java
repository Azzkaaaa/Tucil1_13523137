import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Filereader {
    public DataType readDataFile(String filePath) throws FileNotFoundException {
        Scanner sc = new Scanner(new File(filePath));

        int lebarPapan = sc.nextInt();
        int panjangPapan = sc.nextInt();
        int jumlahPiece = sc.nextInt();
        sc.nextLine();

        String caseType = sc.nextLine();

        List<Piece> pieces = new ArrayList<>(); 

        for (int i = 0; i < jumlahPiece; i++){
            Piece piece = readOnePiece(sc);
            pieces.add(piece);
        }

        sc.close();

        System.out.println("N = " + N + ", M = " + M + ", P = " + P);
        System.out.println("Case = " + caseType);
        for (Piece pc : pieces) {
            System.out.println("Piece " + pc.getId() + " coords = " + formatCoords(pc.getCoords()));
        }
    }

    public Piece readOnePiece(Scanner sc){

    }

    public void convertToCoords(){

    }

    public string formatCoords(List<int[]> coords){

    }
}
