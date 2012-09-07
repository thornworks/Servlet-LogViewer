package thornworks;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * This Servlet displays a list of all log files found in the log directory. Each file is rendered as a link
 * for easy viewing or saving.
 * @author LaSpina
 */
public class LogViewServlet extends HttpServlet {

    static File logFolder;
    File tomcatRoot;
    
    /**
     * Files are referenced relative to the running path of this application. When running under Tomcat,
     * this would normally be the Tomcat bin directory. In the case of appFog, the running directory is one above
     * this, namely Catalina base.
     * This method is used to initialize the tomcatRoot and logFolder values since these may need to change
     * depending on the environment the application is running in. These values can be read from the
     * web.xml file instead of storing these values here.
     */
    public void init() {                                        
        String basePath = this.getServletConfig().getInitParameter("basePath");
        System.out.println("Loaded init parameter: " + basePath);
        tomcatRoot = new File(basePath);
        logFolder = new File(tomcatRoot, "logs");
    }
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
        response.setContentType("text/html;charset=UTF-8");
        
        PrintWriter out = response.getWriter();
        try {
            out.println("<html>");
            out.println("<head><meta http-equiv=\"Expires\" content=\"0\" />");
            out.println("<title>Java Servlet Log Viewer</title>");            
            out.println("</head>");
            out.println("<body><h2>Java Log Viewer</h2>");
            out.println("<p>Note that you will need to refresh your browser when viewing the log files to prevent it from displaying cached content.</p>");
            out.println(listFiles());
            out.println("</body></html>");
        } finally {            
            out.close();
        }
    }
    
    public String listFiles() {
        File webAppPath = new File(tomcatRoot, "webapps");
        String[] fileList = webAppPath.list();
        StringBuilder sb = new StringBuilder();
        //sb.append("<h3>Running Web Applications path=");
        try {
            /*sb.append(webAppPath.getCanonicalPath());
            sb.append("</h3>");
            if(fileList!=null) {
                for(String f : fileList) {
                    sb.append(f);
                    sb.append("<br/>");
                }
            }  */
            sb.append("<h2>Available Log files - ");
            if(logFolder.exists())
                sb.append(logFolder.getCanonicalPath());
            sb.append("</h2>");
            char quote = '\"';
            fileList = logFolder.list();
            if(fileList!=null) {
                for(String f : fileList) {
                    File logFile = new File(logFolder,f);
                    if(logFile.length()>0) {
                        sb.append("<a href=");
                        sb.append(quote);
                        sb.append(f);
                        sb.append(quote);
                        sb.append(">");
                        sb.append(f);
                        sb.append("</a>");
                        sb.append(" : " + logFile.length());
                        sb.append(" Bytes ");                        
                        sb.append("[ ");
                        sb.append("<a href=");
                        sb.append(quote);
                        sb.append("/Wipe?file=");
                        sb.append(f);
                        sb.append(quote);
                        sb.append(">Wipe");
                        sb.append("</a> ] <br/>");
                    }
                }
            }
        }
        catch(IOException ioe) { 
            //sb.append(ioe.toString());
        }
        return sb.toString();
    }
    
    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "LogView renders a list of all available log files as links and also displays a list of running web applications within the same container context.";
    }// </editor-fold>
}
