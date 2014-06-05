package Commons.Json;

import java.io.PrintStream;
import java.io.PrintWriter;

/**
 * Created by Faustine on 14-4-10.
 */
public class TypeCastException extends RuntimeException {

    Throwable nested ;

     public TypeCastException(){
        this.nested  = null ;
    }

    public TypeCastException(String msg){
        super(msg) ;
        this.nested = null ;
    }
    public TypeCastException(String msg ,Throwable nested){
        super(msg);
        this.nested = null ;
        this.nested = nested ;
    }

    public TypeCastException(Throwable nested){
        this.nested = null ;
        this.nested = nested ;
    }

    public String getMessage(){
        return super.getMessage() + "(" + nested.getMessage() + ")" ;
    }

    public String getNonNestedMessage(){
        return super.getMessage() ;
    }

    public Throwable getNested(){
        if(nested == null)
            return this ;
        return nested ;
    }

    public void printStackTrace(){
        super.printStackTrace();
        if(nested !=null)
            nested.printStackTrace();
    }

    public void printStackTrace(PrintStream ps){
        super.printStackTrace(ps);
        if(nested !=null)
            nested.printStackTrace(ps);
    }
    public void printStackTrace(PrintWriter pw){
        super.printStackTrace(pw);
        if(nested !=null)
            nested.printStackTrace(pw);
    }
}
