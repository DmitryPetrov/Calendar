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

}
