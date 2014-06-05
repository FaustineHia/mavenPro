package SNMP;

import org.snmp4j.CommunityTarget;
import org.snmp4j.PDU;
import org.snmp4j.Snmp;
import org.snmp4j.TransportMapping;
import org.snmp4j.event.ResponseEvent;
import org.snmp4j.mp.SnmpConstants;
import org.snmp4j.smi.*;
import org.snmp4j.transport.DefaultUdpTransportMapping;

import java.io.IOException;
import java.util.Vector;

/**
 * Created by Faustine on 2014/4/22.
 */
public class SNMPUtil {

    private Address address = null ;
    private Snmp snmp = null ;
    private String  ip = null ;
    private int port  ;

    public SNMPUtil(String ip,int port){
        this.ip = ip ;
        this.port = port ;

        init() ;
    }

    private void init(){

        try {
            address = GenericAddress.parse("udp:" + ip + "/" + port) ;
            TransportMapping map = new DefaultUdpTransportMapping() ;
            snmp = new Snmp(map) ;
            map.listen();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param oid
     * @param param "0x"
     */
    public  ResponseEvent sendPDU(String oid,int param ,int type) throws IOException {
        PDU pdu = new PDU() ;
        pdu.addOID(new VariableBinding(new OID(oid), new Integer32(param)));

        switch(type){
            case 1:
                pdu.setType(PDU.SET);
                break ;
            case 2 :
                pdu.setType(PDU.GET);
                break ;
        }

        CommunityTarget target = new CommunityTarget() ;
        target.setCommunity(new OctetString("public"));
        target.setAddress(address);
        target.setVersion(SnmpConstants.version2c);
        target.setRetries(2);
        target.setTimeout(2000);

        ResponseEvent response = snmp.send(pdu, target) ;
        return response ;

    }

    public void parseResponse(ResponseEvent response){
        if(response != null && response.getResponse() != null ){
            Vector<VariableBinding> vars = response.getResponse().getVariableBindings() ;
            for(int  i = 0 ; i < vars.size() ; i ++){
                VariableBinding vb = vars.elementAt(i) ;
                System.out.println(vb.getOid() + "-------------" + vb.getVariable().toString()) ;

            }
        }
    }
}
