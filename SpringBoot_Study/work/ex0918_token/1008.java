import java.io.IOException;
import java.util.Scanner;

class Main {
    public static void main(String[] args) throws IOException {
        // 코드 작성
        Scanner scan = new Scanner(System.in);
        double A = scan.nextInt();
        double B = scan.nextInt();
        System.out.println(A / B);
        scan.close();
    }
}