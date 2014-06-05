import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Faustine on 2014/4/21.
 */
public class ServerClass {

    public static void main(String[] args) throws Exception {
        ServerSocket sc = new ServerSocket(9001) ;
        Socket socket = sc.accept() ;

        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream())) ;
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream())) ;

        while(true){
            String str = in.readLine() ;
            if("EOF".equals(str))
                break ;

            Thread.sleep(20000);
            System.out.println("receive:" + str) ;
            pw.print("Receive success!");
            pw.flush();
        }
        in.close();
        pw.close();
        socket.close();
        sc.close();
    }
}
