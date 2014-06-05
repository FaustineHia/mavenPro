package Commons.XML;

import com.sun.deploy.xml.XMLParser;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import java.util.*;

/**
 * Created by Faustine on 2014/4/16.
 */
public class XMLBase {


    public static Document parseXml(String xmlStr){
        Document document = null ;
        String versionXml = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>" ;

        if(versionXml.indexOf("<?xml") < 0 )
            xmlStr = versionXml + xmlStr ;

        try {
            document = DocumentHelper.parseText(xmlStr) ;
        } catch (DocumentException e) {
            e.printStackTrace();
        }

        return document ;
    }

    public static String parseMap2Xml(Map map,String rootName){
        Document document = DocumentHelper.createDocument() ;
        document.addElement(rootName) ;

        Element root = document.getRootElement() ;

        Iterator iterator = map.keySet().iterator() ;
        while(iterator.hasNext()){
            String key = (String) iterator.next() ;
            Element elm = root.addElement(key) ;
            elm.setText(map.get(key).toString());
        }
        String xml = document.asXML().substring(39);
        return xml;
    }

    public static String parseList2Xml(List list ,String RootName , String NodeName){
        Document document = DocumentHelper.createDocument() ;
        Element root = document.addElement(RootName) ;
        for(int i = 0 ; i < list.size() ; i ++){
            Element node = root.addElement(NodeName) ;
            Map  map =  (Map)list.get(i) ;
            Iterator entIt = map.entrySet().iterator() ;

            while(entIt.hasNext()){
                Map.Entry entry = (Map.Entry) entIt.next() ;
                node.addAttribute(entry.getKey().toString(),entry.getValue().toString());
            }
        }

        String xml = document.asXML().substring(39) ;
        return xml ;
    }

    public static List parseXml2List(String xml){
        Document document = parseXml(xml) ;
        List list = new ArrayList() ;
        Element root = document.getRootElement() ;
        Iterator it = root.elementIterator() ;
       while(it.hasNext()){
            Map map = new HashMap() ;
            Element el = (Element) it.next() ;
            map.put(el.getName(),el.getData()) ;

            list.add(map) ;
       }
       return list ;
    }

    public static void main(String[] args){
        XMLBase.parseMap2Xml(new HashMap(),"heoo");
    }

}
