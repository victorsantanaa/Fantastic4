import java.util.Scanner;

public class MainClass10 {
    public static void main(String args[]) {
        Scanner _key = new Scanner(System.in);
        double a;
        double b;
        String t1;
        a = _key.nextDouble();
        b = _key.nextDouble();
        a = 1 + 2 * 3 / b;
        b = 10;
        t1 = "abc";
        if (a > b) {
            System.out.println(a);
        } else {
            System.out.println(b);
        }

    }
}