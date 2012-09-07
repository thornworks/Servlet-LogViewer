<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Log Viewer</title>
    </head>
    <body>
        <h2>Java Servlet Log Viewer</h2>
        <p>The contents of the log folder are exposed by this application. The path to this log folder will
        depend on the deployment environment and the servlet container. To integrate this into your application
        or to run this app as a stand-along web application in your container, you may need to adjust the <strong>basePath parameter</strong>
        in the web.xml file.</p>
        <p><strong>For appfog users:</strong><br/>
            Each Java application you run is deployed within a separate context. This is desirable since every time an application is updated, the container (Tomcat) is restarted.
            In such an environment, there is no point in running this LogViewer as a separate application. Instead, you should add the two source files to your project and modify the web.xml file accordingly.
            Be sure to include the init parameter so the LogViewer knows which folder to search for log files.
            Note that there are no security measures implemented yet. 
            
        </p>
        <h2><a href="/View">See Logs</a></h2>
        <h2>Source Code - download from github</h2>
        <a href="https://github.com/thornworks/Servlet-LogViewer">Download from Github (available soon)</a>
    </body>
</html>
