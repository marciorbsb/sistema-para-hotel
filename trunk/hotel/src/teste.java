import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;


public class teste {
    
	public static void main(String[] args) throws ParseException{
	
	String strDate = "2011-12-31 00:00:00";
    
    /*
     * To convert String to java.sql.Date, use
     * Date (long date) constructor.
     *
     * It creates java.sql.Date object from the milliseconds provided.
     */

    //first convert string to java.util.Date object using SimpleDateFormat
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
    java.util.Date date = sdf.parse(strDate);
   
    java.sql.Date sqlDate = new Date(date.getTime());
   
    System.out.println("String converted to java.sql.Date :" + sqlDate);
	}
}
