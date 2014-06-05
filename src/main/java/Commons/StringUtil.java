package Commons;

import java.util.Collection;
import java.util.Map;

/**
 * Created by Faustine on 14-4-4.
 */
public class StringUtil {

    public static String toBeString(Object obj){
        if(obj!=null)
            return obj.toString() ;
        else
            return "" ;
    }

    /*********** default spilt by ',' ***********/
    public static String[] spilt(String str , String subStr){
        if( str == null)
            str = "" ;
        if(subStr == null)
            str = "," ;

        return str.split(subStr) ;
    }

    public static boolean isEmpty(Object obj){

        if(obj == null)
            return true ;
        if(obj instanceof String)
            return "".equals(obj);

        if(obj instanceof Collection){
            if(((Collection) obj).size()==0)
                return true ;
        }else if(obj instanceof Map)
            if(((Map) obj).size()==0)
                return true ;

        return false ;
    }

    public static void main(String[] args){
        System.out.print(StringUtil.class.getProtectionDomain().getCodeSource().getLocation().toString());
    }

}
