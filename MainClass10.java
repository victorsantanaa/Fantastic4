import java.util.Scanner;

public class MainClass10 {
    public static void main(String args[]) {
        Scanner _key = new Scanner(System.in);
        double a;
        double b;
        String t1;
        String t2;
        t1 = "abc";
        t2 = 12;
        a = _key.nextDouble();
        b = _key.nextDouble();
        a = 1 + 2 * 3 / b;
        b = 10.1;
        if (a > b) {
            System.out.println(a);
        } else {
            System.out.println(b);
        }

    }
}