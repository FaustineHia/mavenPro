package Commons.Json;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.FormatFlagsConversionMismatchException;
import java.util.Locale;
import java.util.logging.SimpleFormatter;

/**
 * Created by Faustine on 14-4-10.
 */
public class TypeCaseHelper {

    public static Object convert(Object obj ,String type , String format) {
        Locale locale = new Locale("zh","CN","");

        if(obj == null )
            return null ;

        if(obj.getClass().getName().equals(type))
            return obj ;

        if("Object".equals(type) || "java.lang.Object".equals(type))
            return obj ;

        String fromType ;
        // String convert to other
        if(obj instanceof String){
            fromType = "String" ;
            String objct = (String)obj ;

            if("String".equals(type) || "java.lang.String".equals(type))
                return objct ;

            if(objct.length() == 0 )
                return null ;

            if("Boolean".equals(type) || "java.lang.Boolean".equals(type)){
                if(objct.equalsIgnoreCase("TRUE"))
                    return new Boolean(true);
                else
                    return new Boolean(false);
            }

            if("Double".equals(type) || "java.lang.Double".equals(type)){
                try {
                    Number tmpNumber  = getNf(locale).parse(objct);
                    return new Double(tmpNumber.doubleValue());
                } catch (ParseException e) {
                    new TypeCastException("Could not convert " + objct +" to  " + type , e) ;
                }

            }

            if("Float".equals(type) || "java.lang.Float".equals(type)){
                try {
                    Number tmpNumber  = getNf(locale).parse(objct);
                    return new Float(tmpNumber.floatValue());
                } catch (ParseException e) {
                    new TypeCastException("Could not convert " + objct +" to  " + type , e) ;
                }

            }

            if("BigDecimal".equals(type) || "java.math.BigDecimal".equals(type)){
                try {
                    BigDecimal retBg = new BigDecimal(objct) ;
                    int length = objct.length() ;
                    int  iscale = objct.indexOf(".") ;

                    if(iscale > -1){
                        int begin = length - (iscale + 1) ;

                        // 5 is a roundmode
                        return retBg.setScale(begin ,5) ;
                    }else
                        return retBg.setScale(0,5) ;

                } catch (Exception e) {
                    new TypeCastException("Could not convert " + objct +" to  " + type , e) ;
                }
            }

            if("Long".equals(type) || "java.lang.Long".equals(type)){
                try {
                    NumberFormat nf = getNf(locale) ;
                    // set max decimal 0
                    nf.setMaximumFractionDigits(0);
                    Number tmpNumber  = nf.parse(objct);
                    return new Long(tmpNumber.longValue());
                } catch (ParseException e) {
                    new TypeCastException("Could not convert " + objct +" to  " + type , e) ;
                }

            }

            if("Integer".equals(type) || "java.lang.Integer".equals(type)){
                try {
                    NumberFormat nf = getNf(locale) ;
                    // set max decimal 0
                    nf.setMaximumFractionDigits(0);
                    Number tmpNumber  = nf.parse(objct);
                    return new Integer(tmpNumber.intValue());
                } catch (ParseException e) {
                    new TypeCastException("Could not convert " + objct +" to  " + type , e) ;
                }

            }

            if("Date".equals(type) || "java.sql.Date".equals(type)){
                if(format == null || format.length() == 0)
                    try {
                        Date.valueOf(objct) ;
                    } catch (Exception e) {
                        try{
                            DateFormat df ;
                            if(locale !=null)
                                df = DateFormat.getDateInstance(3 , locale) ;
                            else
                                df = DateFormat.getDateInstance(3) ;
                            java.util.Date  date = df.parse(objct) ;
                            return new Date(date.getTime()) ;

                        }catch(ParseException p){
                            new TypeCastException("Could not convert " + objct +" to  " + type , p) ;
                        }
                    }
                try {
                    SimpleDateFormat sm = new SimpleDateFormat(format) ;
                    java.util.Date date = sm.parse(objct) ;
                    return new Date(date.getTime()) ;
                }catch(ParseException p){
                new TypeCastException("Could not convert " + objct +" to  " + type , p) ;
            }

            }

            if("TimeStamp".equals(type) || "java.sql.TimeStamp".equals(type)){
                if(objct.length() ==10)
                    objct += "00:00:00" ;

                if(format == null || format.length() == 0)
                    try {
                        Date.valueOf(objct) ;
                    } catch (Exception e) {
                        try{
                            DateFormat df ;
                            if(locale !=null)
                                df = DateFormat.getDateTimeInstance(3,3,locale) ;
                            else
                                df = DateFormat.getDateTimeInstance(3,3) ;
                            java.util.Date  date = df.parse(objct) ;
                            return new Timestamp(date.getTime()) ;

                        }catch(ParseException p){
                            new TypeCastException("Could not convert " + objct +" to  " + type , p) ;
                        }
                    }
                try {
                    SimpleDateFormat sm = new SimpleDateFormat(format) ;
                    java.util.Date date = sm.parse(objct) ;
                    return new Timestamp(date.getTime()) ;
                }catch(ParseException p){
                    new TypeCastException("Could not convert " + objct +" to  " + type , p) ;
                }

            }

        }

        if(obj instanceof  BigDecimal){
            fromType = "BigDecimal" ;
            BigDecimal bigDecimal = (BigDecimal)obj ;
            if("String".equals(type) || "java.lang.String".equals(type))
                return getNf(locale).format(bigDecimal.doubleValue()) ;

            if("BigDecimal".equals(type) || "java.math.BigDecimal".equals(type))
                return obj ;

            if("Long".equals(type)|| "java.lang.Long".equals(type))
                return new Long(Math.round(bigDecimal.doubleValue())) ;
            if("Integer".equals(type) || "java.lang.Integer".equals(type))
                return new Integer((int) Math.round(bigDecimal.doubleValue())) ;
            if("Double".equals(type) || "java.lang.Double".equals(type))
                return new Double(bigDecimal.doubleValue()) ;
            if("Float".equals(type) || "java.lang.Float".equals(type))
                return new Float(bigDecimal.floatValue()) ;
            else
                throw new TypeCastException("Conversion from " + fromType + " to " + type
                        + " not currently supported");
        }
        if(obj instanceof  Double){
            fromType = "Double" ;
            Double dou = (Double)obj ;
            if("String".equals(type) || "java.lang.String".equals(type))
                return getNf(locale).format(dou.doubleValue()) ;

            if("Double".equals(type) || "java.lang.Double".equals(type))
                return obj ;

            if("Long".equals(type)|| "java.lang.Long".equals(type))
                return new Long(Math.round(dou.doubleValue())) ;
            if("Integer".equals(type) || "java.lang.Integer".equals(type))
                return new Integer((int) Math.round(dou.doubleValue())) ;
            if("BigDecimal".equals(type) || "java.math.BigDecimal".equals(type))
                return new BigDecimal(dou.toString()) ;
            if("Float".equals(type) || "java.lang.Float".equals(type))
                return new Float(dou.floatValue()) ;
            else
                throw new TypeCastException("Conversion from " + fromType + " to " + type
                        + " not currently supported");
        }

        if(obj instanceof  Float){
            fromType = "Float" ;
            Float flt = (Float)obj ;
            if("String".equals(type) || "java.lang.String".equals(type))
                return getNf(locale).format(flt.floatValue()) ;

            if("Float".equals(type) || "java.lang.Float".equals(type))
                return obj ;

            if("Long".equals(type)|| "java.lang.Long".equals(type))
                return new Long(Math.round(flt.doubleValue())) ;
            if("Integer".equals(type) || "java.lang.Integer".equals(type))
                return new Integer((int) Math.round(flt.doubleValue())) ;
            if("BigDecimal".equals(type) || "java.math.BigDecimal".equals(type))
                return new BigDecimal(flt.doubleValue()) ;
            if("Double".equals(type) || "java.lang.Double".equals(type))
                return new Float(flt.doubleValue()) ;
            else
                throw new TypeCastException("Conversion from " + fromType + " to " + type
                        + " not currently supported");
        }

        if(obj instanceof  Long){
            fromType = "Long" ;
            Long along = (Long)obj ;
            if("String".equals(type) || "java.lang.String".equals(type))
                return getNf(locale).format(along.doubleValue()) ;

            if("BigDecimal".equals(type) || "java.math.BigDecimal".equals(type))
                return  new BigDecimal(along.doubleValue());

            if("Long".equals(type)|| "java.lang.Long".equals(type))
                return obj ;
            if("Integer".equals(type) || "java.lang.Integer".equals(type))
                return new Integer(along.intValue()) ;
            if("Double".equals(type) || "java.lang.Double".equals(type))
                return new Double(along.doubleValue()) ;
            if("Float".equals(type) || "java.lang.Float".equals(type))
                return new Float(along.floatValue()) ;
            else
                throw new TypeCastException("Conversion from " + fromType + " to " + type
                        + " not currently supported");
        }

        if(obj instanceof  Integer){
            fromType = "Integer" ;
            Integer integer = (Integer)obj ;
            if("String".equals(type) || "java.lang.String".equals(type))
                return getNf(locale).format(integer.longValue()) ;

            if("BigDecimal".equals(type) || "java.math.BigDecimal".equals(type)){
                String str = integer.toString();
                BigDecimal retBig = new BigDecimal(integer.doubleValue());
                int iscale = str.indexOf(".");
                int keylen = str.length();
                if (iscale > -1) {
                    iscale = keylen - (iscale + 1);
                    return retBig.setScale(iscale, 5);
                } else {
                    return retBig.setScale(0, 5);
                }
            }

            if("Long".equals(type)|| "java.lang.Long".equals(type))
                return new Long(Math.round(integer.longValue())) ;
            if("Integer".equals(type) || "java.lang.Integer".equals(type))
                return obj ;
            if("Double".equals(type) || "java.lang.Double".equals(type))
                return new Double(integer.doubleValue()) ;
            if("Float".equals(type) || "java.lang.Float".equals(type))
                return new Float(integer.floatValue()) ;
            else
                throw new TypeCastException("Conversion from " + fromType + " to " + type
                        + " not currently supported");
        }

        if(obj instanceof  Date){
            fromType = "Date" ;
            Date date = (Date)obj ;
            if("String".equals(type) || "java.lang.String".equals(type)){
               if(format ==null || format.length()  == 0){
                   return obj.toString() ;
               }else{
                   SimpleDateFormat sm = new SimpleDateFormat(format) ;
                   return sm.format(new java.util.Date(date.getTime())) ;

               }

            }

            if("Date".equals(type) || "java.sql.Date".equals(type))
                return obj ;

            if("Time".equals(type) || "java.sql.Time".equals(type))
                throw new TypeCastException("Conversion from " + fromType + " to " + type
                        + " not currently supported");

            if("TimeStamp".equals(type) || "java.sql.TimeStamp".equals(type))
                return new Timestamp(date.getTime()) ;
            else
                throw new TypeCastException("Conversion from " + fromType + " to " + type
                        + " not currently supported");
        }
        if(obj instanceof  Timestamp){
            fromType = "Timestamp" ;
            Timestamp timestamp = (Timestamp)obj ;
            if("String".equals(type) || "java.lang.String".equals(type)){
                if(format == null || format.length() == 0){
                    return (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(timestamp)).toString() ;
                }else
                    return (new SimpleDateFormat(format).format(new java.util.Date(timestamp.getTime()))).toString() ;
            }

            if("Date".equals(type) || "java.sql.Date".equals(type))
                return new Date(timestamp.getTime()) ;

            if("Time".equals(type) || "java.sql.Time".equals(type))
               return new Time(timestamp.getTime()) ;

            if("TimeStamp".equals(type) || "java.sql.TimeStamp".equals(type))
                return obj ;
            else
                throw new TypeCastException("Conversion from " + fromType + " to " + type
                        + " not currently supported");
        }
        if(obj instanceof  Boolean){
            fromType = "Boolean" ;
            Boolean bool = (Boolean)obj ;
            if("String".equals(type) || "java.lang.String".equals(type))
                return bool.toString() ;

            if("Integer".equals(type) || "java.lang.Integer".equals(type)){
                if(bool.booleanValue()){
                    return new Integer(1) ;
                }else
                    return new Integer(0) ;
            }

            if("Boolean".equals(type) || "java.lang.Boolean".equals(type))
                return obj ;
            else
                throw new TypeCastException("Conversion from " + fromType + " to " + type
                        + " not currently supported");
        }

        return null ;
    }

    private static NumberFormat getNf(Locale locale){
        NumberFormat nf = null ;
        if(locale == null)
            nf = NumberFormat.getNumberInstance();
        else
            nf = NumberFormat.getNumberInstance(locale) ;

        nf.setGroupingUsed(false );
        return nf ;
    }

    public static Integer convert2Integer(Object obj){
        return (Integer)convert(obj ,"Integer" ,null) ;
    }

    public static Long convert2Long(Object obj){
        return (Long)convert(obj ,"Long" ,null) ;
    }
    public static Float convert2Float(Object obj){
        return (Float)convert(obj ,"Float" ,null) ;
    }
    public static Double convert2Double(Object obj){
        return (Double)convert(obj ,"Double" ,null) ;
    }
    public static String convert2String(Object obj){
        return (String)convert(obj ,"String" ,null) ;
    }

    public static String convert2String(Object obj, String defaultValue) {
        Object s = convert(obj, "String", null);
        if (s != null)
            return (String) s;
        else
            return "";
    }
    public static boolean convert2Boolean(Object obj){
        return (Boolean)convert(obj ,"Boolean" ,null) ;
    }
    public static BigDecimal convert2BigDecimal(Object obj){
        return (BigDecimal)convert(obj ,"BigDecimal" ,null) ;
    } public static Date convert2Date(Object obj,String format){
        return (Date)convert(obj ,"Date" ,format) ;
    }
    public static Timestamp convert2TimeStamp(Object obj,String format){
        return (Timestamp)convert(obj ,"TimeStamp" ,null) ;
    }

}
