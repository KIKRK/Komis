package KI;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class ReadClientDataServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter pw = resp.getWriter();
//        pw.println("Tutaj będą się wyświetlać użytkownicy");
        try {
            InitialContext initCtx = new InitialContext();
            Context context = (Context) initCtx.lookup("java:comp/env");
            DataSource ds = (DataSource) context.lookup(getServletContext().getInitParameter("dataSource"));
            ClientDataDAO dao = new ClientDataDAOImpl();
            List clients = dao.readClientsData(ds);
            pw.println("<HTML><HEAD>");
            pw.println("<TITLE>Lista uzytkownikow</TITLE>");
            pw.println("</HEAD><BODY>");
            pw.print("<table>");

            pw.print("<tr>");
            pw.print("<td><b>" + "Imie" + "</td><b>");
            pw.print("<td><b>" + "Nazwisko" + "</td><b>");
            pw.print("<td><b>" + "Wiek" + "</td><b>");
            pw.print("<td><b>" + "Region" + "</td><b>");
            pw.print("<td><b>" + "Plec" + "</td><b>");
            pw.println("</tr>");


            for (Object o : clients) {
                Client c = (Client)o;
                pw.print("<tr>");
                pw.print("<td>" + c.getFirstName() + "</td>");
                pw.print("<td>" + c.getLastName() + "</td>");
                pw.print("<td>" + c.getAge() + "</td>");
                pw.print("<td>" + c.getRegion() + "</td>");
                pw.print("<td>" + c.getSex() + "</td>");
                pw.println("</tr>");
            }
            pw.println("</table>");
            pw.println("</BODY></HTML>");

//            <table>
//<tr>
//<td>k1<td>
//<td>k2<td>
//</tr>
//</table>!



        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

