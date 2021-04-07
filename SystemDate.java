import java.text.SimpleDateFormat;
import java.util.Date;

public class SystemDate extends Day{
    private static SystemDate instance;

    public SystemDate(String sDay) {super(sDay);}

    public static SystemDate getInstance() { return instance; }

    public static void createTheInstance() {
        if (instance==null){
            SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
            Date d = new Date();
            instance = new SystemDate(f.format(d));
        }
        else 
            System.out.println("Cannot create one more system date instance.");
    }

    static public void setSystemDate(String sDay) {
        instance = new SystemDate(sDay);
    }
}
