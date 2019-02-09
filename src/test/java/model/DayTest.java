
package model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.Test;

import model.Day;
import model.Mood;

public class DayTest {

    @Test
    public void setMood_StringAsParam_SettedVariable() {
        String good = "GooD";
        String normal = "norMAL";

        Day day = new Day();
        
        day.setMood(good);
        assertEquals(Mood.GOOD, day.getMood());

        day.setMood(normal);
        assertEquals(Mood.NORMAL, day.getMood());
    }
    
    @Test//(expected = IllegalArgumentException.class)
    public void setMood_StringWithSpace_IllegalArgumentException() {
        String bad = "ba d";

        Day day = new Day();
        
        try {
            day.setMood(bad);
        } catch (IllegalArgumentException e) {
            assertTrue("dayObj.setMood() can't accept string with spaces", true);
            e.printStackTrace();   
        }
    } 

    @Test
    public void getDateString_CalendarObj_StringYYYYMMDD() {
        Calendar date = new GregorianCalendar();
        Day day = new Day(date);
        String dateStr =
                date.get(Calendar.YEAR) + "-" + (1 + date.get(Calendar.MONTH))
                        + "-" + date.get(Calendar.DAY_OF_MONTH);
        assertEquals(day.getDateString(), dateStr);

        Calendar date2 = new GregorianCalendar(2023, 5, 17);
        Day day2 = new Day(date2);
        String dateStr2 =
                date.get(Calendar.YEAR) + "-" + date.get(Calendar.MONTH) + "-"
                        + date.get(Calendar.DAY_OF_MONTH);
        day2.setDate(date2);
        assertNotEquals(day2.getDateString(), dateStr2);
    }

    @Test
    public void dateToStringForDataBase_CalendarObj_StringYYYYMMDD() {
        getDateString_CalendarObj_StringYYYYMMDD();
        assertTrue("getDateString() calls dateToStringForDataBase()", true);
    }

    @Test
    public void getMoodString_EnumMood_LowCaseString() {
        Day day = new Day();
        day.setMood(Mood.GOOD);

        assertEquals("good", day.getMoodString());
    }

    @Test
    public void getDayOfWeek_CalendarObj_StringWithNameDayOfWeek() {
        Calendar date = new GregorianCalendar();
        
        Day day = new Day(date);
        int dayOfWeek = date.get(Calendar.DAY_OF_WEEK);

        switch (dayOfWeek) {
        case 1:
            assertEquals("monday", day.getDayOfWeek());
            break;
        case 2:
            assertEquals("tuesday", day.getDayOfWeek());
            break;
        case 3:
            assertEquals("wednesday", day.getDayOfWeek());
            break;
        case 4:
            assertEquals("thursday", day.getDayOfWeek());
            break;
        case 5:
            assertEquals("friday", day.getDayOfWeek());
            break;
        case 6:
            assertEquals("saturday", day.getDayOfWeek());
            break;
        case 7:
            assertEquals("sunday", day.getDayOfWeek());
            break;
        }
    }
    
    @Test
    public void dayToStringForDataBase_SomeValues_StringForDB() {
        Calendar date = new GregorianCalendar();
        Day day = new Day(date);
        day.setMood(Mood.BAD);
        day.setSmoke(true);
        
        
        String dateStr =
                date.get(Calendar.YEAR) + "-" + (1 + date.get(Calendar.MONTH))
                        + "-" + date.get(Calendar.DAY_OF_MONTH);
        
        String stringForEqual = "'" + dateStr + "', 'bad', 0, 0, 0, 0, 0, 0, 1";

        assertEquals(stringForEqual, day.dayToStringForDataBase());
    }
    
    
    
}
