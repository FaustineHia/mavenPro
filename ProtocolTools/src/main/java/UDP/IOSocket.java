package UDP;

/**
 * Created by Faustine on 2014/4/21.
 */
public abstract class IOSocket {

    public abstract void send(String ip ,int port,int timeout,String str) ;

    public abstract byte[] receive(String ip ,int port ,int timeout) ;
}
