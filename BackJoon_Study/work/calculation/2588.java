import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int A = scan.nextInt();
        int B = scan.nextInt();

        // B의 1의 자리, 10의 자리, 100의 자리 추출
        int b1 = B % 10;        // 1의 자리
        int b2 = (B / 10) % 10; // 10의 자리
        int b3 = B / 100;       // 100의 자리

        System.out.println(A * b1);   // (4)
        System.out.println(A * b2);   // (5)
        System.out.println(A * b3);   // (6)
        System.out.println(A * B);    // 최종 결과
/*  */
        scan.close();
    }
}
