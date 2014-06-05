import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggerFactory;

/**
 * Created by Faustine on 2014/5/6.
 */
public class Log4jTest {

    private static Logger logger = Logger.getLogger(Log4jTest.class) ;

    public static void main(String[] args){
        logger.info("Hello!!");
    }
}
