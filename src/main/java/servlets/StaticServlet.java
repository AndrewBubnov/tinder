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
    }
}
