package UDP;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketException;

/**
 * Created by Faustine on 2014/4/22.
 */
public class SingleUDPSocket {

    public void send(String ip,int port ,int timeout, String sendStr){
        InetSocketAddress address = new InetSocketAddress(ip,port) ;
        DatagramSocket sc = null ;
        try {
            sc = new DatagramSocket(address) ;
            sc.connect(address);
            sc.setSoTimeout(timeout);
            byte[] bytes = new byte[8192] ;

            bytes = sendStr.getBytes() ;
            DatagramPacket dp = new DatagramPacket(bytes,bytes.length) ;
            sc.send(dp);

        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            sc.close();
        }
    }

    public byte[] receive(String ip,int port,int timeout){
        InetSocketAddress address = new InetSocketAddress(ip,port) ;
        DatagramSocket sc;
        byte[] bytes = null ;
        try{
            sc = new DatagramSocket(address) ;
            sc.connect(address);

            bytes = new byte[8192] ;
            DatagramPacket dp = new DatagramPacket(bytes,bytes.length) ;
            sc.receive(dp);
            sc.close();
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return bytes ;
    }
}
