
package view;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Map;

import model.Day;

//ВНИМАНИЕ ГОВНОКОД
public class HtmlTableYearCreator {

    public String getYearTable(Map<Calendar, Map<Integer, Day>> year,
            String dayAttribute) {

        StringBuilder table = new StringBuilder();
        table.append("<table>");

        int numYear =
                year.keySet().toArray(new GregorianCalendar[year.size()])[0]
                        .get(Calendar.YEAR);

        for (int i = 0; i < 12; i++) {

            String monthName = getMonthName(i);

            Calendar monthOfYear = new GregorianCalendar(numYear, i, 1);
            Map<Integer, Day> month = year.remove(monthOfYear);
            int dayOfMonth =
                    monthOfYear.getActualMaximum(Calendar.DAY_OF_MONTH);

            if (month.isEmpty()) {

                table.append(createEmptyMonthTable(monthName, dayOfMonth));
            } else {
                table.append(getMonthTable(month, monthName, dayAttribute,
                        dayOfMonth));
            }
        }
        table.append("</table>");
        return table.toString();
    }

    private StringBuilder getMonthTable(Map<Integer, Day> month,
            String monthName, String dayAttribute, int dayInMonth) {
        Day day;
        boolean firstDay = true;
        StringBuilder table = new StringBuilder("<tr>");
        table.append("\n\t<td class=\"monthName\">");
        table.append("<b>" + monthName + "</b>");
        table.append("</td>");

        for (int i = 1; i <= dayInMonth; i++) {
            if (month.containsKey(i)) {
                day = month.get(i);

                if (firstDay == true) {
                    printUnrecordedDaysOfBeginingThisMonth(table, day);
                    firstDay = false;
                }

                printRecordedDay(table, i, day, dayAttribute);
            } else {
                if (firstDay == false) {
                    printUnrecordedDay(table, i);
                }
            }
        }
        table.append("\n</tr>");

        return table;
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
            String dayAttribute) {

        table.append("\n\t<td class=\"" + day.getMoodString() + "\" "
                + "title=\"У вас было " + translateMood(day) + " настроение\">");

        if (checkDayAttribute(day, dayAttribute)) {
            table.append("<b>" + i + "</b>");
        } else {
            table.append("<small>" + i + "</small>");
        }

        table.append("</td>");
    }

    private String translateMood(Day day) {
        String moodEng = day.getMoodString().toLowerCase();
        String moodRus = "";

        switch (moodEng) {
        case "good":
            moodRus = "хорошее";
            break;
        case "normal":
            moodRus = "нормальное";
            break;
        case "bad":
            moodRus = "плохое";
            break;
        }

        return moodRus;
    }

    private void printUnrecordedDay(StringBuilder table, int i) {
        table.append("\n\t<td>");
        table.append("<small>" + i + "</small>");
        table.append("</td>");
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

    private StringBuilder createEmptyMonthTable(String monthName,
            int dayOfMonth) {
        StringBuilder table = new StringBuilder("\n<tr>");

        table.append("\n\t<td class=\"monthName\">");
        table.append("<b>" + monthName + "</b>");
        table.append("</td>");

        for (int i = 1; i <= dayOfMonth; i++) {
            table.append("\n\t<td>");
            table.append("<small>" + i + "</small>");
            table.append("</td>");
        }

        table.append("\n</tr>");

        return table;
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
}
