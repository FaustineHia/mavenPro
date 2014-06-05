import java.io.IOException;
import java.net.*;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Faustine on 2014/4/22.
 */
public class MultiSendUdp {

    public static void main(String[] args){

        try {
            InetAddress address = InetAddress.getByName("224.0.0.1") ;

            MulticastSocket msc = new MulticastSocket(3214) ;
            msc.joinGroup(address);

            long current = Calendar.getInstance().getTime().getTime();
            byte[] msg = new byte[8192] ;

            for(int i = 0 ; i < 5 ; i++ ){
                msg = ("hello" + current).getBytes() ;
                System.out.println(new String(msg,0,msg.length)) ;
                DatagramPacket dp = new DatagramPacket(msg,msg.length,address,3214) ;
                msc.send(dp);
                current = Calendar.getInstance().getTime().getTime();
            }

            msc.leaveGroup(address);
            msc.close();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
