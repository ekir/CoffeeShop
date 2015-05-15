/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.annotation.Resource;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 *
 * @author ekir
 */
@WebServlet(urlPatterns = {"/MainServlet"})
public class MainServlet extends HttpServlet {
    // Use lookup instead of name
    // http://stackoverflow.com/questions/4168376/glassfish-resource-injection-not-working
    @Resource(lookup="jdbc/sample") private javax.sql.DataSource myDB;
    
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            /*
            Connection connection;
            try {
            connection=myDB.getConnection();
            
            
                try {
                PreparedStatement getProductList=connection.prepareStatement("SELECT * FROM product");
                ResultSet rs = getProductList.executeQuery("SELECT * FROM customer");
                while(rs.next()) {
                        String name=rs.getString("NAME");
                        out.println(name);
                }
                } catch (Exception e) {
                    out.println("query"+e.getMessage());
                }
            
            } catch(Exception e) {
                out.println(e.getMessage()+"connection failed ");
            }*/
            
            //try {
            /*
            InitialContext context = new InitialContext();
            DataSource DS = (DataSource)context.lookup("jdbc/sample");
            */
            Connection conn = null;
            Statement stmt = null;
            ResultSet rset = null;
            try {
            //conn = DS.getConnection();
            conn = myDB.getConnection();
            stmt = conn.createStatement();
            rset = stmt.executeQuery("Select * from CUSTOMER");
            while(rset.next()) {
                out.println(rset.getString("name"));
            }
            } catch (SQLException e) {
                out.println("sql exception"+e.getMessage());
            }
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet MainServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet MainServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
            //} catch(NamingException e) {
//out.println("naming exception"+e.getMessage());
            //}
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
