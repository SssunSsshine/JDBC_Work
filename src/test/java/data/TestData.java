package data;

import entity.TourOperator;
import entity.Tourist;

import java.sql.Date;

public class TestData {
    public static final long ID_PETROVA = 22L;
    public static final long ID_MAMEDOV = 8L;
    public  static final long ID_TO = 4;
    public static final Tourist ALEX_TOURIST = new Tourist("Smith","Alexandra", null, Date.valueOf("1990-01-22"),"sdjjs@mail.com", "82919222233");
    public static final Tourist JAMES_TOURIST = new Tourist("Jude","James", "Geroldovich", Date.valueOf("1976-01-22"),null, "82919122233");

    public static final TourOperator TRAVEL_NICE_TO = new TourOperator("TravelNice", "TN", "111111", "89102223344", "dsds@dsf.com", "sddf");

    public static final TourOperator LETS_TRAVEL_TO = new TourOperator("LetsTravel", "LT", "111112", "89102223334", "lt@dsf.com", null);


}
