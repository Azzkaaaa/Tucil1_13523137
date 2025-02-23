import java.io.File;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Boolean valid = false;
        TestReader test = new TestReader();
        DataType dt = null;
        Scanner scanFile = new Scanner(System.in);        
        
        while(!valid){
            System.out.print("Masukkan nama file: ");
            String testName = scanFile.nextLine();

            File file = new File("test/" + testName);

            if (!file.exists()){
                System.out.println("File tidak ditemukan, COba lagi!..");
            }else {
                try {
                    dt = test.readDataFile(testName);
                    valid = true;
                } catch (Exception e) {
                    System.out.println("Terjadi kesalahan: " + e.getMessage());
                    System.out.println("Silakan coba lagi.\n");
                }
            }
        }
        


        Solver solver = new Solver(dt);
        long startTime = System.currentTimeMillis();
        boolean solved = solver.solve(0);
        long endTime = System.currentTimeMillis();
        long elapsed = endTime - startTime;
    
        if (solved) {
            System.out.println("Solusi Ditemukan!");
            solver.printSol();
            System.out.println("Waktu: " + elapsed + " ms");
            System.out.println("Banyak iterasi: " + solver.getIteration());
    
            // Tanyakan user, misal:
            System.out.print("Simpan solusi ke file (ya/tidak)? ");
            Scanner sc = new Scanner(System.in);
            String ans = sc.nextLine();
            if (ans.equalsIgnoreCase("ya")) {
                // misal nama file: "solusi.txt"
                String filename = sc.nextLine();
                solver.saveSol(filename, elapsed);
                System.out.println("Solusi di simpan di: " + "save/" + filename);
            }
        } else {
            System.out.println("Tidak ditemukan solusi.");
        }
    }
}

