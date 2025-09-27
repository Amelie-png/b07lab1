import java.io.*;
import java.util.*;

public class Driver {
    public static void main(String [] args) {
        Polynomial p = new Polynomial();
        System.out.println(p.evaluate(3));
        double [] c1 = {-2, 1, 1};
        int [] e1 = {1, 0, 2};
        Polynomial p1 = new Polynomial(c1, e1);
        double [] c2 = {-1, 5};
        int [] e2 = {1, 0};
        Polynomial p2 = new Polynomial(c2, e2);
        Polynomial s = p1.add(p2);
        System.out.println("s(5) = " + s.evaluate(5));
        if(p1.hasRoot(1))
            System.out.println("1 is a root of p1");
        else
            System.out.println("1 is not a root of p1");
        Polynomial m = p1.multiply(p2);
        System.out.println("m(-1) = " + m.evaluate(-1));
        if(m.hasRoot(2))
            System.out.println("2 is a root of m");
        else
            System.out.println("2 is not a root of m");

        //File I/O
        File f1 = new File("poly1.txt");
        File f2 = new File("poly2.txt");
        File f3 = new File("poly3.txt");
        Polynomial t1 = new Polynomial(f1);
        Polynomial t2 = new Polynomial(f2);
        Polynomial t3 = new Polynomial(f3);
        t1.saveToFile("poly.txt");
        Scanner input = null;
        try {
            input = new Scanner(new File("poly.txt"));
        } catch (FileNotFoundException e) {
        }
        System.out.println(input.next());
        t2.saveToFile("poly.txt");
        try {
            input = new Scanner(new File("poly.txt"));
        } catch (FileNotFoundException e) {
        }
        System.out.println(input.next());
        t3.saveToFile("poly.txt");
        try {
            input = new Scanner(new File("poly.txt"));
        } catch (FileNotFoundException e) {
        }
        System.out.println(input.next());
        m.saveToFile("m.txt");
        try {
            input = new Scanner(new File("m.txt"));
        } catch (FileNotFoundException e) {
        }
        System.out.println(input.next());
    }
}