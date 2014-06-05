import java.io.*;
import java.net.Socket;

/**
 * Created by Faustine on 2014/4/21.
 */
public class ClientClass {

    public static void  main(String[] args){
        Socket sc = null ;
        PrintWriter  pw = null ;
        BufferedReader br = null ;
        try {
            sc = new Socket("127.0.0.1", 9001);
            sc.setSoTimeout(2000);
            br = new BufferedReader(new InputStreamReader(sc.getInputStream())) ;
            pw = new PrintWriter(new OutputStreamWriter(sc.getOutputStream())) ;

            pw.println("hello");
            pw.flush();
            pw.println("EOF");
            pw.flush();
            System.out.print(br.read());

        }catch (IOException io){
            io.printStackTrace();
        }finally {
            pw.close();
            try {
                br.close();
                sc.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
