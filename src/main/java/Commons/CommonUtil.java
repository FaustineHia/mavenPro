package Commons;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.UUID;

/**
 * Created by Faustine on 14-4-8.
 */
public class CommonUtil {

    public static String getSerialNo(){
        UUID id = UUID.randomUUID() ;
        return id.toString().replaceAll("-","");
    }

    public static Properties loadFileFromStream(String path) throws IOException {
        InputStream in =  CommonUtil.class.getClassLoader().getResourceAsStream(path) ;
        Properties p = new Properties() ;
        p.load(in);
        return p ;
    }

    public static Properties loadPropertyFile(String path) throws IOException {
        InputStream in = null;
        try {
            in = new FileInputStream(path);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Properties p = new Properties() ;
        p.load(in);
        return p ;
    }
}
