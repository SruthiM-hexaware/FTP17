import java.util.Date;
import java.util.Scanner;
import java.text.ParseException;
import java.text.SimpleDateFormat;
public class Neww
{
    public static void main(String[] args)
    {
        Date sd;
        Date date=new Date();
        System.out.println(date.toString());
    System.out.println("Enter Start Date in this format =>(yyyy-MM-dd)");
    String d = scanner.next();
    try {
      SimpleDateFormat start = new SimpleDateFormat("yyyy-MM-dd");
      strtDate = start.parse(sd);
    } catch (ParseException e) {
      System.out.println(e);
    }
    }
}
