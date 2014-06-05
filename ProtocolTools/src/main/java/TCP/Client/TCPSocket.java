package TCP.Client;

import javax.xml.crypto.Data;
import java.io.*;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

/**
 * Created by Faustine on 2014/4/21.
 */
public class TCPSocket {

    private  String ip = null ;
    private Integer port = null ;
    private Integer timeout = 2000 ;
    private Socket socket ;
    private OutputStream output;
    private InputStream input ;

    public TCPSocket(){
        socket = new Socket() ;
    }

    public TCPSocket(String ip, Integer port, Integer timeout){
        this.ip = ip ;
        this.port = port ;
        this.timeout = timeout ;

        socket = new Socket() ;
        SocketAddress sadd = new InetSocketAddress(ip , port) ;
        try {
            socket.connect(sadd ,timeout);
            socket.setSoTimeout(timeout);
            socket.setReuseAddress(false);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * connect
     * @throws IOException
     */
    public void connect() throws IOException {
        socket.connect(new InetSocketAddress(ip,port),timeout);
    }

    public void connect(String  ip , Integer port) throws IOException {
        socket.connect(new InetSocketAddress(ip,port),timeout);
    }

    public void connect(String ip ,Integer port ,Integer timeout) throws IOException {
        socket.connect(new InetSocketAddress(ip,port),timeout);
    }


    public void close() throws IOException {
        socket.close();
    }

    /**
     * send and receive
     * @return
     */
    public int sendAndReceive(byte[] sendMsg , byte[] receiveMsg) throws IOException {
        send(sendMsg) ;
        return receive(receiveMsg) ;
    }

    public void send(byte[] sendMsg) throws IOException {
        output = socket.getOutputStream() ;
        output.write(sendMsg);
    }

    public int receive(byte[] receiveMsg) throws IOException {
        input = socket.getInputStream();
        DataInputStream data = new DataInputStream(input);
//        ByteArrayOutputStream bte = new ByteArrayOutputStream() ;

        int len = -1;

        len = data.read(receiveMsg);

        return  len ;
    }

    public boolean isClosed(){
        if(socket == null ) return false ;
        return socket.isClosed() ;
    }
}
