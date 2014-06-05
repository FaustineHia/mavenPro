package Commons;

import java.io.IOException;
import java.util.Properties;

/**
 * Created by Faustine on 14-4-4.
 */
public class PropertyUtil {

    private Properties proerty ;

    /**
     * single pattern
     */
    private static PropertyUtil instance = new PropertyUtil();

    private PropertyUtil(){}

    public static PropertyUtil getInstance(){
        return instance ;
    }

    public  String getWebClassesPath(){
        return PropertyUtil.class.getProtectionDomain().getCodeSource().getLocation().getPath();
    }

    public  String getClassesPath() throws IllegalAccessException {
       String path = getWebClassesPath() ;
        if(path.indexOf("classes") != -1){
            path = path.substring(0,path.indexOf("classes") + 8);
        }else{
            throw new IllegalAccessException("getClassesPath error!");
        }
        return  path ;
    }

    public Properties getProperties(String path) throws IllegalAccessException {
        try {
            proerty = CommonUtil.loadPropertyFile(path) ;
        } catch (IOException e) {
            e.printStackTrace();
            throw new IllegalAccessException("load Properties files error!");
        }
        return proerty ;
    }



}
