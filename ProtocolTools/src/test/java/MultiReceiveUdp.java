import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.UnknownHostException;

/**
 * Created by Faustine on 2014/4/22.
 */
public class MultiReceiveUdp {

    public static void main(String[] args){

        try {
            InetAddress address = InetAddress.getByName("224.0.0.1") ;
            MulticastSocket sc = new MulticastSocket(3214) ;
            sc.joinGroup(address);
            byte[] bt = new byte[8192] ;
            while(true) {
                DatagramPacket dp = new DatagramPacket(bt, bt.length);
                sc.receive(dp);
                System.out.println(new String(dp.getData(),0,dp.getLength()));
                System.out.println(new String(bt,0,bt.length)) ;
            }


        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
