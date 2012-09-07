/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package thornworks;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *  Erases the contents of a log file.
 * @author LaSpina
 */
public class LogWiper extends HttpServlet {

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            String filename = request.getParameter("file");
            if(filename!=null && filename.length()>0) {
                File log = new File(LogViewServlet.logFolder, filename);
                wipe(log);
                response.sendRedirect("/View");
            }
            else {
                throw new FileNotFoundException("The file parameter was either missing or empty.");
            }
            
        } 
        catch(FileNotFoundException fnf) {
                PrintWriter out = response.getWriter();
                out.println("<html><head>");
                out.println("<title>Servlet LogWiper Error</title>");            
                out.println("</head>");
                out.println("<body>");
                out.print("<h2>Error: Could not find file.</h2>");
                out.println(fnf);
                out.println("</body></html>");
                out.close();
        }
    }

    private boolean wipe(File logFile) throws FileNotFoundException {
        PrintWriter pw = new PrintWriter(logFile);
        java.util.Date now = new java.util.Date();
        pw.print("Log contents were wiped clean on ");
        pw.println(now);
        pw.close();
        //http://foglog.rs.af.cm/localhost.2012-09-03.log
        return true;
    }
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
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
     * Handles the HTTP
     * <code>POST</code> method.
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
