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

public class HtmlTableMonthCreatorTest {

    @Test
    public void getMonthTable_EmptyMap_SkeletonHtmlTable() {
        HtmlTableMonthCreator creator = new HtmlTableMonthCreator();
        String table = creator.getMonthTable(new HashMap<Integer, Day>(), "useful");
        
        boolean resultRegExrTest = regularExpressionsTestEmptyTable(table);
        assertTrue(resultRegExrTest);
    }

    private boolean regularExpressionsTestEmptyTable(String table) {
        String regex = "^<table>"
                            + "(\n<tr>"
                                + "(\n\t<td*[^>]>*[^<]</td>){7}"
                            + "\n</tr>){4,6}"
                        + "\n</table>$";
        
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(table);
        
        return matcher.find();
    }
    
    
    @Test
    public void getMonthTable_FullMap_HtmlTable() {
        Map<Integer, Day> month = new HashMap<Integer, Day>();
        month = fillMapFebruary2010(month);
        
        HtmlTableMonthCreator creator = new HtmlTableMonthCreator();
        String table = creator.getMonthTable(month, "useful");

        boolean resultRegExrTest = regularExpressionsTest(table);
        assertTrue(resultRegExrTest);
    }
    
    @Test
    public void getMonthTable_NotFullMap_HtmlTable() {
        HtmlTableMonthCreator creator = new HtmlTableMonthCreator();
        Map<Integer, Day> month = new HashMap<Integer, Day>();
        month = fillMapJanuar2022(month);
        String table = creator.getMonthTable(month, "useful");
        
        boolean resultRegExrTest = regularExpressionsTest(table);
        assertTrue(resultRegExrTest);
    }
    
    
    private boolean regularExpressionsTest(String table) {
        String regex = "^<table>"
                            + "(\n<tr>"
                                + "("
                                + "\n\t<td"
                                    + "(\\sclass=\"\\w+\""
                                    + "\\stitle=\"[Р-пр-џ\\s]+\")?>"
                                        + "(\\.)?"
                                        + "(\\d{1,2})?"
                                        + "((&nbsp){8}"
                                        + "<i"
                                            + "\\sclass=\"[a-z\\s-]+\""
                                            + "\\stitle=\"[Р-пр-џ\\s]+\">"
                                        + "</i>)?"
                                + "</td>"
                                + "){7}"
                            + "\n</tr>){4,6}"
                        + "\n</table>$";
        
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(table);
        
        return matcher.find();
    }
    
    private Map<Integer, Day> fillMapFebruary2010(Map<Integer, Day> month) {
        //4 week in month
        Calendar date;

        for(int i = 1; i <= 28; i++) {
            date = new GregorianCalendar(2010, 1, i);
            month.put(i, getDay(date));
        }
        return month;
    }
    
    private Map<Integer, Day> fillMapJanuar2022(Map<Integer, Day> month) {
        //6 week in month
        Calendar date;

        for(int i = 1; i <= 31; i++) {
            if((i % 4) == 0) {
                date = new GregorianCalendar(2022, 0, i);
                month.put(i, getDay(date));
            }
        }
        return month;
    }
    
    private Day getDay(Calendar date) {
        Day day = new Day();
        
        day.setDate(date);
        day.setMood(Mood.GOOD);
        day.setUseful(true);
        day.setWork(false);
        day.setStudy(true);
        day.setLearnLanguag(false);
        day.setSport(false);
        day.setAlcohol(true);
        day.setSmoke(false);
        
        return day;
    }
}
