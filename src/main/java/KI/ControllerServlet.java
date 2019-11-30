package KI;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class ControllerServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String n = req.getParameter("name");
        PrintWriter pw = resp.getWriter();
        pw.println("<HTML><HEAD>");
        pw.println("<TITLE>witamy</TITLE>");
        pw.println("</HEAD><BODY>");

        pw.println("<H3>Hello ");

        if (n == null){
            pw.println("world!");
        } else {
            pw.println(n + "!");
        }
        pw.println("</H3>");

        pw.println("<a href=\"makeForm.html\">Wybór marki</a><br><br>");
        pw.println("<a href=\"userForm.html\">Rejestracja uzytkownika</a>");

        pw.println("</BODY></HTML>");

    }


}
