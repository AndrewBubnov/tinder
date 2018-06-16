package servlets;
import org.apache.commons.io.FileUtils;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


public class StaticServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String url = req.getPathInfo();
        url = url.substring(1,url.length());
        String out = FileUtils.readFileToString(new File(url),"UTF-8");
        byte[] buffer = out.getBytes();
        ServletOutputStream outputStream = resp.getOutputStream();
        outputStream.write(buffer);
        outputStream.close();
//        String url = req.getPathInfo();
//        if (url!=null) {
//            // input
//            Path in = Paths.get("/src/main/java/resources/static", url);
//            // set the type for downloading ability instead of plain show in the browser window.
//            resp.setContentType("application/octet-stream");
//            resp.setHeader("Content-Disposition",String.format("attachment; filename=\"%s\"", in.getFileName().toString()));
//            // move content from the FileInputStream to ServletOutputStream
//            Files.copy(in, resp.getOutputStream());
//        } else {
//            resp.getWriter().print("you should pass the file name after slash");
//        }

    }
}
