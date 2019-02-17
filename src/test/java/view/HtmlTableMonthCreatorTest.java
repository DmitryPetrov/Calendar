package view;

import static org.junit.Assert.assertEquals;
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
        
        boolean resultRegExrTest = regularExpressionsTest(table);
        assertTrue(resultRegExrTest);
    }
    
    @Test
    public void getMonthTable_FullMap_HtmlTable() {
        Map<Integer, Day> month = new HashMap<Integer, Day>();
        month = fillMapFebruary2010(month);
        
        HtmlTableMonthCreator creator = new HtmlTableMonthCreator();
        String table = creator.getMonthTable(month, "useful");
        
        boolean resultRegExrTest = regularExpressionsTest(table);
        //assertTrue(resultRegExrTest);
 
        assertEquals(getFilledTableFebruary2010(), table);
    }
    
    @Test
    public void getMonthTable_NotFullMap_HtmlTable() {
        HtmlTableMonthCreator creator = new HtmlTableMonthCreator();
        Map<Integer, Day> month = new HashMap<Integer, Day>();
        month = fillMapJanuar2022(month);
        String table = creator.getMonthTable(month, "useful");
        
        boolean resultRegExrTest = regularExpressionsTest(table);
        //assertTrue(resultRegExrTest);
        
        assertEquals(getFilledTableJanuar2022(), table);
    }
    
    
    
    private boolean regularExpressionsTest(String table) {
        String regex = "^<table>(\n<tr>(\n\t<td*[^>]>*[^<]</td>){7}\n</tr>){4,6}\n</table>$";
        
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
    
    private String getFilledTableFebruary2010() {
        String table = "<table>" + 
                "\n<tr>" + 
                "\n\t<td class=\"good\" title=\"It was good day.\">1&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp<i class=\"fa fa-list-ol fa-lg\"></i></td>" + 
                "\n\t<td class=\"good\" title=\"It was good day.\">2&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp<i class=\"fa fa-list-ol fa-lg\"></i></td>" + 
                "\n\t<td class=\"good\" title=\"It was good day.\">3&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp<i class=\"fa fa-list-ol fa-lg\"></i></td>" + 
                "\n\t<td class=\"good\" title=\"It was good day.\">4&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp<i class=\"fa fa-list-ol fa-lg\"></i></td>" + 
                "\n\t<td class=\"good\" title=\"It was good day.\">5&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp<i class=\"fa fa-list-ol fa-lg\"></i></td>" + 
                "\n\t<td class=\"good\" title=\"It was good day.\">6&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp<i class=\"fa fa-list-ol fa-lg\"></i></td>" + 
                "\n\t<td class=\"good\" title=\"It was good day.\">7&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp<i class=\"fa fa-list-ol fa-lg\"></i></td>" + 
                "\n</tr>" + 
                "\n<tr>" + 
                "\n\t<td class=\"good\" title=\"It was good day.\">8&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp<i class=\"fa fa-list-ol fa-lg\"></i></td>" + 
                "\n\t<td class=\"good\" title=\"It was good day.\">9&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp<i class=\"fa fa-list-ol fa-lg\"></i></td>" + 
                "\n\t<td class=\"good\" title=\"It was good day.\">10&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp<i class=\"fa fa-list-ol fa-lg\"></i></td>" + 
                "\n\t<td class=\"good\" title=\"It was good day.\">11&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp<i class=\"fa fa-list-ol fa-lg\"></i></td>" + 
                "\n\t<td class=\"good\" title=\"It was good day.\">12&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp<i class=\"fa fa-list-ol fa-lg\"></i></td>" + 
                "\n\t<td class=\"good\" title=\"It was good day.\">13&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp<i class=\"fa fa-list-ol fa-lg\"></i></td>" + 
                "\n\t<td class=\"good\" title=\"It was good day.\">14&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp<i class=\"fa fa-list-ol fa-lg\"></i></td>" + 
                "\n</tr>" + 
                "\n<tr>" + 
                "\n\t<td class=\"good\" title=\"It was good day.\">15&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp<i class=\"fa fa-list-ol fa-lg\"></i></td>" + 
                "\n\t<td class=\"good\" title=\"It was good day.\">16&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp<i class=\"fa fa-list-ol fa-lg\"></i></td>" + 
                "\n\t<td class=\"good\" title=\"It was good day.\">17&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp<i class=\"fa fa-list-ol fa-lg\"></i></td>" + 
                "\n\t<td class=\"good\" title=\"It was good day.\">18&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp<i class=\"fa fa-list-ol fa-lg\"></i></td>" + 
                "\n\t<td class=\"good\" title=\"It was good day.\">19&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp<i class=\"fa fa-list-ol fa-lg\"></i></td>" + 
                "\n\t<td class=\"good\" title=\"It was good day.\">20&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp<i class=\"fa fa-list-ol fa-lg\"></i></td>" + 
                "\n\t<td class=\"good\" title=\"It was good day.\">21&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp<i class=\"fa fa-list-ol fa-lg\"></i></td>" + 
                "\n</tr>" + 
                "\n<tr>" + 
                "\n\t<td class=\"good\" title=\"It was good day.\">22&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp<i class=\"fa fa-list-ol fa-lg\"></i></td>" + 
                "\n\t<td class=\"good\" title=\"It was good day.\">23&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp<i class=\"fa fa-list-ol fa-lg\"></i></td>" + 
                "\n\t<td class=\"good\" title=\"It was good day.\">24&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp<i class=\"fa fa-list-ol fa-lg\"></i></td>" + 
                "\n\t<td class=\"good\" title=\"It was good day.\">25&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp<i class=\"fa fa-list-ol fa-lg\"></i></td>" + 
                "\n\t<td class=\"good\" title=\"It was good day.\">26&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp<i class=\"fa fa-list-ol fa-lg\"></i></td>" + 
                "\n\t<td class=\"good\" title=\"It was good day.\">27&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp<i class=\"fa fa-list-ol fa-lg\"></i></td>" + 
                "\n\t<td class=\"good\" title=\"It was good day.\">28&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp<i class=\"fa fa-list-ol fa-lg\"></i></td>" + 
                "\n</tr>" + 
                "\n</table>";
        return table;
    }
    
    private String getFilledTableJanuar2022() {
        String table = "<table>" + 
                "\n<tr>" + 
                "\n\t<td>.</td>" + 
                "\n\t<td>.</td>" + 
                "\n\t<td>.</td>" + 
                "\n\t<td>.</td>" + 
                "\n\t<td>.</td>" + 
                "\n\t<td>1</td>" + 
                "\n\t<td>2</td>" + 
                "\n</tr>" + 
                "\n<tr>" + 
                "\n\t<td>3</td>" + 
                "\n\t<td class=\"good\" title=\"It was good day.\">4&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp<i class=\"fa fa-list-ol fa-lg\"></i></td>" + 
                "\n\t<td>5</td>" + 
                "\n\t<td>6</td>" + 
                "\n\t<td>7</td>" + 
                "\n\t<td class=\"good\" title=\"It was good day.\">8&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp<i class=\"fa fa-list-ol fa-lg\"></i></td>" + 
                "\n\t<td>9</td>" + 
                "\n</tr>" + 
                "\n<tr>" + 
                "\n\t<td>10</td>" + 
                "\n\t<td>11</td>" + 
                "\n\t<td class=\"good\" title=\"It was good day.\">12&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp<i class=\"fa fa-list-ol fa-lg\"></i></td>" + 
                "\n\t<td>13</td>" + 
                "\n\t<td>14</td>" + 
                "\n\t<td>15</td>" + 
                "\n\t<td class=\"good\" title=\"It was good day.\">16&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp<i class=\"fa fa-list-ol fa-lg\"></i></td>" + 
                "\n</tr>" + 
                "\n<tr>" + 
                "\n\t<td>17</td>" + 
                "\n\t<td>18</td>" + 
                "\n\t<td>19</td>" + 
                "\n\t<td class=\"good\" title=\"It was good day.\">20&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp<i class=\"fa fa-list-ol fa-lg\"></i></td>" + 
                "\n\t<td>21</td>" + 
                "\n\t<td>22</td>" + 
                "\n\t<td>23</td>" + 
                "\n</tr>" + 
                "\n<tr>" + 
                "\n\t<td class=\"good\" title=\"It was good day.\">24&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp<i class=\"fa fa-list-ol fa-lg\"></i></td>" + 
                "\n\t<td>25</td>" + 
                "\n\t<td>26</td>" + 
                "\n\t<td>27</td>" + 
                "\n\t<td class=\"good\" title=\"It was good day.\">28&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp<i class=\"fa fa-list-ol fa-lg\"></i></td>" + 
                "\n\t<td>29</td>" + 
                "\n\t<td>30</td>" + 
                "\n</tr>" + 
                "\n<tr>" + 
                "\n\t<td>31</td>" + 
                "\n\t<td>.</td>" + 
                "\n\t<td>.</td>" + 
                "\n\t<td>.</td>" + 
                "\n\t<td>.</td>" + 
                "\n\t<td>.</td>" + 
                "\n\t<td>.</td>" + 
                "\n</tr>" + 
                "\n</table>";
        return table;
    }
}
