package KI;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.PrintWriter;

public class SaveClientDataServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Client client = new Client();

        client.setFirstName(req.getParameter("firstname"));
        client.setLastName(req.getParameter("lastname"));
        String ageString = req.getParameter("age");
        client.setAge(Integer.parseInt(ageString));
        client.setRegion(req.getParameter("region"));
        client.setSex(req.getParameter("sex"));



        // WCZEŚNIEJSZA WERSJA - z argumentem String dataSource w saveClientData
//        ClientDataDAO dao = new ClientDataDAOImpl();
//        try {
//            dao.saveClientData(client, getServletContext().getInitParameter("dataSource"));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        ClientDataDAO dao = new ClientDataDAOImpl();
        try {
            InitialContext initCtx = new InitialContext();
            Context context = (Context) initCtx.lookup("java:comp/env");
            DataSource ds = (DataSource) context.lookup(getServletContext().getInitParameter("dataSource"));
            dao.saveClientData(client, ds);
        } catch (Exception e) {
            e.printStackTrace();
        }

        PrintWriter out = resp.getWriter();
        out.println("<html><head><title>Sukces</title></head>");
        out.println("<body");
        out.println("<h3>Pomyslnie dodano uzytkownika.<h3>");
        out.println("<a href=\"userForm.html\">Dodaj kolejnego uzytkownika</a>");


    }
}







// OLD:
//package KI;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//public class SaveClientDataServlet extends HttpServlet {
//
//
//
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        super.doPost(req, resp);
//        Client client = new Client();
//        client.setFirstName(req.getParameter("firstName"));
//        client.setLastName(req.getParameter("lastName"));
//        String ageString = req.getParameter("age");
//        client.setAge(Integer.parseInt(ageString));
//        client.setRegion(req.getParameter("region"));
//        client.setSex(req.getParameter("sex"));
//
////         Gdyby Age był typu String:
////         client.setAge(req.getParameter("age"));
//
//        ClientDataDAO dao = new ClientDataDAOImpl();
//
//        try {
//            dao.saveClientData(client, getServletContext().getInitParameter("dataSource"));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//
//    }
//}
