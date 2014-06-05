package UDP;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.UnknownHostException;

/**
 * Created by Faustine on 2014/4/21.
 */
public class MultiUDPSocket extends IOSocket{

    @Override
    public void send(String ip, int port, int timeout, String str) {
        MulticastSocket sc = null ;
        InetAddress address ;
        try {
            address = InetAddress.getByName(ip) ;
            sc.joinGroup(address);

            byte[] bytes = new byte[8192] ;
            bytes = str.getBytes() ;
            DatagramPacket dp = new DatagramPacket(bytes,bytes.length) ;
            sc.send(dp);
            sc.leaveGroup(address);

        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {

            sc.close();
        }
    }

    @Override
    public byte[] receive(String ip, int port, int timeout) {
        MulticastSocket sc = null ;
        InetAddress address ;
        byte[] bytes = null ;
        try {
            address = InetAddress.getByName(ip) ;
            sc.joinGroup(address);

            bytes = new byte[8192] ;
            DatagramPacket dp = new DatagramPacket(bytes,bytes.length) ;
            sc.receive(dp);
            sc.leaveGroup(address);

        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {

            sc.close();
        }
        return bytes;
    }

}
