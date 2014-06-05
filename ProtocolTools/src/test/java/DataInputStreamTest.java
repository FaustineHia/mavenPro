import java.io.*;

/**
 * Created by Faustine on 2014/4/21.
 */
public class DataInputStreamTest {

    public static void main(String[] args){

        try {
            DataOutputStream da = new DataOutputStream(new FileOutputStream(args[0])) ;
            Person[] persons = new Person[]{
               new Person("faustine",23) ,
               new Person("tom",24)
            } ;

            for(Person person : persons){
                da.writeUTF(person.getName());
                da.writeInt(person.getAge());
            }

            da.flush();
            da.close();

            DataInputStream di = new DataInputStream(new FileInputStream(args[0])) ;
            for(int i = 0 ; i < 2 ; i ++){
                //**********warnning : read order and write order must be same  ***********//
               String name = di.readUTF() ;
               int age = di.readInt() ;
               persons[i] = new Person(name,age) ;
            }

            di.close();

            for(int  i = 0 ; i < 2 ; i++){
                System.out.println("name---age:" + persons[i].getName()  + "-----" + persons[i].getAge()) ;
            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
