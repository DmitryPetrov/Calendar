
package view;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Map;

import model.Day;

public class HtmlTableCreator {

    public String getMonthTable(Map<Integer, Day> month,
            String dayAttribute) {
        if(month.isEmpty()) {
            return createEmptyTable();
        }
        
        
        String ico = chooseIco(dayAttribute);
        Day day;
        boolean firstDay = true;
        int dayInMonth = 0;
        StringBuilder table = new StringBuilder("<table>");
        table.append("\n<tr>");

        for (int i = 1; i <= 31; i++) {
            if (month.containsKey(i)) {
                day = month.remove(i);

                if (firstDay == true) {
                    dayInMonth = day.getDate()
                            .getActualMaximum(Calendar.DAY_OF_MONTH);

                    printDaysOfLastMonthOnThisWeek(table, day);
                    printUnrecordedDaysOfBeginingThisMonth(table, day);

                    firstDay = false;
                }

                printRecordedDay(table, i, day, dayAttribute, ico);
            } else {              
                if (firstDay == false) {
                    printUnrecordedDay(table, i);
                }
            }

            if (i == (dayInMonth)) {
                while (dayCounter != 0) {
                    emptyCell(table);
                }
                break;
            }
        }
        int lastTR = table.lastIndexOf("<tr>");
        table.delete(lastTR, lastTR + 4);
        table.append("</table>");

        return table.toString();
    }

    private void printDaysOfLastMonthOnThisWeek(StringBuilder table, Day day) {
        int intYear = day.getDate().get(Calendar.YEAR);
        int intMonth = day.getDate().get(Calendar.MONTH);
        Calendar date = new GregorianCalendar(intYear, intMonth, 1);
        int lastDaysOfLastMonth = date.get(Calendar.DAY_OF_WEEK);

        lastDaysOfLastMonth--;

        for (int j = 1; j < lastDaysOfLastMonth; j++) {
            emptyCell(table);
        }
    }

    private void printUnrecordedDaysOfBeginingThisMonth(StringBuilder table,
            Day day) {
        int UnrecordedDaysOfBeginingThisMonth =
                day.getDate().get(Calendar.DAY_OF_MONTH);

        for (int j = 1; j < UnrecordedDaysOfBeginingThisMonth; j++) {
            printUnrecordedDay(table, j);
        }
    }

    private void printRecordedDay(StringBuilder table, int i, Day day,
            String dayAttribute, String ico) {
        String space = "&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp";

        table.append(
                "\n\t<td class=\"" + day.getMoodString() + "\" title=\"It was "
                        + day.getMoodString().toLowerCase() + " day.\">");
        table.append(i);

        if (checkDayAttribute(day, dayAttribute)) {
            table.append(space + ico);
        }

        table.append("</td>");
        dayInWeekCounter(table);
    }

    private void printUnrecordedDay(StringBuilder table, int i) {
        table.append("\n\t<td>");
        table.append(i);
        table.append("</td>");
        dayInWeekCounter(table);
    }

    private void emptyCell(StringBuilder table) {
        table.append("\n\t<td>");
        table.append(".");
        table.append("</td>");
        dayInWeekCounter(table);
    }

    private int dayCounter = 0;

    private void dayInWeekCounter(StringBuilder table) {
        dayCounter++;

        if (dayCounter == 7) {
            table.append("\n</tr>");
            table.append("\n<tr>");
            dayCounter = 0;

        }
    }

    private boolean checkDayAttribute(Day day, String dayAttribute) {
        boolean bool = false;

        switch (dayAttribute) {
        case "useful":
            bool = day.getUseful();
            break;
        case "work":
            bool = day.getWork();
            break;
        case "study":
            bool = day.getStudy();
            break;
        case "learn_language":
            bool = day.getLearnLanguag();
            break;
        case "sport":
            bool = day.getSport();
            break;
        case "alcohol":
            bool = day.getAlcohol();
            break;
        case "smoke":
            bool = day.getSmoke();
            break;
        }
        return bool;
    }

    private String chooseIco(String dayAttribute) {
        String ico;

        switch (dayAttribute) {
        case "useful":
            ico = "<i class=\"fa fa-list-ol fa-lg\"></i>";
            break;
        case "work":
            ico = "<i class=\"fa fa-industry fa-lg\"></i>";
            break;
        case "study":
            ico = "<i class=\"fa fa-graduation-cap fa-lg\"></i>";
            break;
        case "learn_language":
            ico = "<i class=\"fa fa-language fa-lg\"></i>";
            break;
        case "sport":
            ico = "<i class=\"fa fa-futbol-o fa-lg\"></i>";
            break;
        case "alcohol":
            ico = "<i class=\"fa fa-glass fa-lg\"></i>";
            break;
        case "smoke":
            ico = "<i class=\"fa fa-fire fa-lg\"></i>";
            break;
        default:
            ico = "";
        }
        return ico;
    }
    
    private String createEmptyTable() {
        StringBuilder table = new StringBuilder("<table>");
        table.append("\n<tr>");
        
        for(int i = 0; i < 5; i++) {
            for(int j = 0; j < 7; j++) {
                emptyCell(table);
            }
        }
        
        int lastTR = table.lastIndexOf("<tr>");
        table.delete(lastTR, lastTR + 4);
        table.append("</table>");

        return table.toString();
    }
}
