package view;

import static org.junit.Assert.assertTrue;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

import model.Day;
import model.Mood;

public class HtmlTableYearCreatorTest {
    
    @Test
    public void getMonthTable_EmptyMap_SkeletonHtmlTable() {
        
        Map<Calendar, Map<Integer, Day>> yearMap = new HashMap<Calendar, Map<Integer, Day>>();
        for(int i = 0; i < 12; i++) {
            Calendar month = new GregorianCalendar(2013, i, 1);
            Map<Integer, Day> monthMap = new HashMap<Integer, Day>();
            yearMap.put(month, monthMap);
        }

        HtmlTableYearCreator creator = new HtmlTableYearCreator();
        String table = creator.getYearTable(yearMap, "useful");

        boolean resultRegExrTest = regularExpressionsTestEmptyTable(table);
        assertTrue(resultRegExrTest);
    }
    
    private boolean regularExpressionsTestEmptyTable(String table) {
        String regex = "^<table>"
                            + "(\n<tr>"
                                + "\n\t<td\\sclass=\"monthName\"><b>\\w+<.b><.td>"
                                + "(\n\t<td><small>\\d{1,2}<.small><.td>){28,31}"
                            + "\n<.tr>){12}"
                      + "\n<.table>$";
        
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(table);
        
        return matcher.find();
    }
    
    
    @Test
    public void getMonthTable_FullMap_HtmlTable() {
        Map<Calendar, Map<Integer, Day>> yearMap = fillMap2010();
        
        HtmlTableYearCreator creator = new HtmlTableYearCreator();
        String table = creator.getYearTable(yearMap, "useful");

        regularExpressionsTest(table);
    }

    private void regularExpressionsTest(String table) {
        checkTableTag(table);
        
        for (int i = 0; i < 12; i++) {
            String regex = "\n<tr>"
                                + "\n\t<td\\sclass=\"monthName\">"
                                    + "<b>" + getMonthName(i) + "<.b>"
                                + "<.td>"
                                + "(\n\t<td(\\sclass=\"\\w+\"\\stitle=\"[Р-пр-џ\\s]+\")?>"
                                    + "(<small>\\d{1,2}<.small>)?"
                                    + "(<b>\\d{1,2}<.b>)?"
                                + "<.td>){28,31}"
                            + "\n<.tr>";
            
            Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(table);
            
            assertTrue(matcher.find());
        }
    }
    
    private void checkTableTag(String table) {
        assertTrue(table.startsWith("<table>"));
        assertTrue(table.endsWith("</table>"));
    }
    
    private String getMonthName(int monthNum) {
        String monthName = "Unknown Month";

        switch (monthNum) {
        case 0:
            monthName = "Januar";
            break;
        case 1:
            monthName = "February";
            break;
        case 2:
            monthName = "March";
            break;
        case 3:
            monthName = "April";
            break;
        case 4:
            monthName = "May";
            break;
        case 5:
            monthName = "June";
            break;
        case 6:
            monthName = "July";
            break;
        case 7:
            monthName = "August";
            break;
        case 8:
            monthName = "September";
            break;
        case 9:
            monthName = "October";
            break;
        case 10:
            monthName = "November";
            break;
        case 11:
            monthName = "December";
            break;
        default:
            break;
        }

        return monthName;
    }
    
    
    private Map<Calendar, Map<Integer, Day>> fillMap2010() {
        Map<Calendar, Map<Integer, Day>> yearMap = new HashMap<Calendar, Map<Integer, Day>>();

        
        for(int i = 0; i < 12; i++) {
            Calendar month = new GregorianCalendar(2010, i, 1);
            
            int dayOfMonth = month.getActualMaximum(Calendar.DAY_OF_MONTH);
            Map<Integer, Day> monthMap = new HashMap<Integer, Day>();
           
            for(int j = 1; j <= dayOfMonth; j++) {
                if((j % 3) == 0) {
                    Calendar date = new GregorianCalendar(2010, i, j);
                    monthMap.put(j, getDay(date));
                }
            }
            
            yearMap.put(month, monthMap);
        }
        return yearMap;
    }
    
    private Day getDay(Calendar date) {
        Day day = new Day();
        
        boolean randomBool = Math.random() < 0.5;
        
        day.setDate(date);
        day.setMood(Mood.GOOD);
        day.setUseful(randomBool);
        day.setWork(true);
        day.setStudy(true);
        day.setLearnLanguag(false);
        day.setSport(true);
        day.setAlcohol(true);
        day.setSmoke(false);
        
        return day;
    }
}
