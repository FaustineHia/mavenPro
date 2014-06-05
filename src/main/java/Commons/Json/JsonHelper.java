package Commons.Json;

import Commons.StringUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

/**
 * Created by Faustine on 14-4-10.
 */
public class JsonHelper {

    public  static String encodeObject2Json(Object obj){
        String jsonString = "[]" ;

        if(!StringUtil.isEmpty(obj)){
            if(obj instanceof ArrayList){
                jsonString = JSONArray.fromObject(obj).toString() ;
            }else if(obj instanceof Vector){
                jsonString = JSONArray.fromObject(obj).toString() ;
            }else {
                JSONObject jsonObj = JSONObject.fromObject(obj) ;
                jsonString = jsonObj.toString() ;
            }

        }
        return jsonString ;
    }

    public  static String encodeObject2Json(Object obj , String formart){
        String jsonString = "[]" ;

        if(!StringUtil.isEmpty(obj)){

            JsonConfig jcfg = new JsonConfig() ;

            jcfg.registerJsonValueProcessor(java.util.Date.class,new JsonValueProcessorImpl(formart));
            jcfg.registerJsonValueProcessor(java.sql.Date.class,new JsonValueProcessorImpl(formart));
            jcfg.registerJsonValueProcessor(Timestamp.class,new JsonValueProcessorImpl(formart));


            if(obj instanceof ArrayList){
                jsonString = JSONArray.fromObject(obj,jcfg).toString() ;
            }else if(obj instanceof Vector){
                jsonString = JSONArray.fromObject(obj,jcfg).toString() ;
            }else {
                JSONObject jsonObj = JSONObject.fromObject(obj,jcfg) ;
                jsonString = jsonObj.toString() ;
            }

        }
        return jsonString ;

    }

    public  static Object parseJson2Object(String json){
        Map obj = new HashMap() ;
        if(StringUtil.isEmpty(json))
            return obj ;

        obj = (Map)JSONObject.toBean(JSONObject.fromObject(json),Map.class) ;
        return obj ;
    }
}
