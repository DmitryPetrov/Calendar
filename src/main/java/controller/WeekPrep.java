
package controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.User;

/**
 * Servlet implementation class WeekPrep
 */
@WebServlet("/WeekPrep")
public class WeekPrep extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public WeekPrep() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        System.out.println("/Week");

        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute("user");

        String page = "/week.jsp";
        if (user == null) {
            page = "/index.html";
        } else {
            request.setAttribute("week_monday", createWeekHtmlTable("monday"));
            request.setAttribute("week_tuesday", createWeekHtmlTable("tuesday"));
            request.setAttribute("week_wednesday", createWeekHtmlTable("wednesday"));
            request.setAttribute("week_thursday", createWeekHtmlTable("thursday"));
            request.setAttribute("week_friday", createWeekHtmlTable("friday"));
            request.setAttribute("week_saturday", createWeekHtmlTable("saturday"));
            request.setAttribute("week_sunday", createWeekHtmlTable("sunday"));
        }

        RequestDispatcher dispatcher =
                getServletContext().getRequestDispatcher(page);
        dispatcher.forward(request, response);
    }

    private String createWeekHtmlTable(String dayOfWeek) {
        String table = "<table class=\"inner_table\">" + 
                "<tr><td>" + 
                "   <script>" + 
                "       document.write(\"<input type=\\\"hidden\\\" name=\\\"date\\\" value=\\\"\" " + 
                "       + (1900 + " + dayOfWeek + ".getYear()) + \"-\" + " + dayOfWeek + ".getMonth() + \"-\" + " + dayOfWeek + ".getDate() + \"\\\">\"); " + 
                "   </script>" + 
                "</td></tr>" + 
                "<tr><td class=\"inner_table_radio\" title=\"Ваше настроение в этот день\">" + 
                "   <p><input type=\"radio\" name=\"mood\" value=\"GOOD\"> GOOD" + 
                "   <p><input type=\"radio\" name=\"mood\" value=\"NORMAL\" checked> NORM" + 
                "   <p><input type=\"radio\" name=\"mood\" value=\"BAD\"> BAD</td></tr>" + 
                "<tr><td class=\"inner_table\" title=\"Этот день прошел продуктивно\">" + 
                "   <input type=\"checkbox\" name=\"useful\" value=\"true\">" + 
                "   <i class=\"fa fa-list-ol fa-lg\"></i></td></tr>" + 
                "<tr><td class=\"inner_table\" title=\"В этот день вы работали\">" + 
                "   <input type=\"checkbox\" name=\"work\" value=\"true\">" + 
                "   <i class=\"fa fa-industry fa-lg\"></i></td></tr>  " + 
                "<tr><td class=\"inner_table\" title=\"В этот день вы учились\">" + 
                "   <input type=\"checkbox\" name=\"study\" value=\"true\">" + 
                "   <i class=\"fa fa-graduation-cap fa-lg\"></i></td></tr>" + 
                "<tr><td class=\"inner_table\" title=\"В этот день вы практиковались в иностранных языках\">" + 
                "   <input type=\"checkbox\" name=\"learnLanguag\" value=\"true\">" + 
                "   <i class=\"fa fa-language fa-lg\"></i></td></tr>" + 
                "<tr><td class=\"inner_table\" title=\"В этот день вы занимались спортом\">" + 
                "   <input type=\"checkbox\" name=\"sport\" value=\"true\">" + 
                "   <i class=\"fa fa-futbol-o fa-lg\"></i></td></tr>" + 
                "<tr><td class=\"inner_table\" title=\"В этот день вы употребляли алкоголь\">" + 
                "   <input type=\"checkbox\" name=\"alcohol\" value=\"true\">" + 
                "   <i class=\"fa fa-glass fa-lg\"></i></td></tr>" + 
                "<tr><td class=\"inner_table\" title=\"В этот день вы курили\">" + 
                "   <input type=\"checkbox\" name=\"smoke\" value=\"true\">" + 
                "   <i class=\"fa fa-fire fa-lg\"></i></td></tr>" + 
                "<tr><td class=\"inner_table\">" + 
                "   <input class=\"result\" type=\"submit\" value=\"Submit\"></td></tr>" + 
                "</table>";
        return table;
    }
    
    
    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {

        doGet(request, response);
    }

}
