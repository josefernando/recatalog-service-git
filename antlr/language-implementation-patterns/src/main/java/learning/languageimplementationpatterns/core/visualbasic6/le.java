package learning.languageimplementationpatterns.core.visualbasic6;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class le {

public static void main(String[] args) {

    File file = new File("C:\\Users\\josez\\AppData\\Local\\Temp\\mlv000.frm10721001291333954523.tmp");

    try {

        Scanner sc = new Scanner(file);

        while (sc.hasNextLine()) {
            String i = sc.nextLine();
            System.out.println(i);
        }
        sc.close();
    } 
    catch (FileNotFoundException e) {
        e.printStackTrace();
    }
 }
}
