package Commons.Json;

import Commons.StringUtil;
import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

/**
 * Created by Faustine on 2014/4/11.
 */
public class JsonValueProcessorImpl implements JsonValueProcessor {

    private String format = "yyyy-MM-dd HH:mm:ss";

    public JsonValueProcessorImpl(){}

    public JsonValueProcessorImpl(String format){
        this.format = format ;
    }

    @Override
    public Object processObjectValue(String s, Object o, JsonConfig jsonConfig) {
        if(StringUtil.isEmpty(o))
            return "" ;

        if(o instanceof java.util.Date){
            SimpleDateFormat sf = new SimpleDateFormat(format);
            return sf.format((java.util.Date) o);
        }else if(o instanceof  java.sql.Date){
            SimpleDateFormat sf = new SimpleDateFormat(format);
            return sf.format((java.sql.Date) o);
        }else if(o instanceof  Timestamp){
            SimpleDateFormat sf = new SimpleDateFormat(format);
            return sf.format((Timestamp) o);
        }


        return o.toString();
    }

    @Override
    public Object processArrayValue(Object o, JsonConfig jsonConfig) {
        String[] objs = {} ;

        if(o instanceof java.util.Date[]){
            SimpleDateFormat sf = new SimpleDateFormat(format) ;
            java.util.Date[] dates = (java.util.Date[])o;
            objs = new String[dates.length] ;
            for(int  i = 0 ; i < dates.length ; i ++){
                objs[i] = sf.format(dates[i]) ;
            }
        }

        if(o instanceof java.sql.Date[]){
            SimpleDateFormat sf = new SimpleDateFormat(format) ;
            java.sql.Date[] dates = (java.sql.Date[])o;
            objs = new String[dates.length] ;
            for(int  i = 0 ; i < dates.length ; i ++){
                objs[i] = sf.format(dates[i]) ;
            }
        }

        if(o instanceof Timestamp[]){
            SimpleDateFormat sf = new SimpleDateFormat(format) ;
            Timestamp[] dates = (Timestamp[])o;
            objs = new String[dates.length] ;
            for(int  i = 0 ; i < dates.length ; i ++){
                objs[i] = sf.format(dates[i]) ;
            }
        }


        return objs ;
    }


    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }
}
