import java.util.Scanner;

public class MainClass10 {
    public static void main(String args[]) {
        Scanner _key = new Scanner(System.in);
        double a;
        double d;
        String t1;
        String t2;
        a = 10;
        d = 11.8;
        t1 = "abc";
        System.out.println(t1);
        a = Double.parseDouble(_key.nextLine());
        d = Double.parseDouble(_key.nextLine());
        if (a > d) {
            System.out.println(a);
        } else {
            System.out.println(d);
        }

    }
}