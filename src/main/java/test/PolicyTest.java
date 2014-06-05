package test;

import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by Faustine on 14-4-8.
 */
public class PolicyTest {

    public static void main(String[] args){
        FileWriter file ;

        try {
            file = new FileWriter("D:/test.txt");
            file.write("hi");
            file.flush();
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
