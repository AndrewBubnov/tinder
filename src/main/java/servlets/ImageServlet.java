package servlets;

import org.apache.commons.io.FileUtils;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class ImageServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String url = req.getPathInfo();
        url = url.substring(1,url.length());
        File file = new File(url);
        BufferedImage image = ImageIO.read(file);
        ImageIO.write(image, "JPG", resp.getOutputStream());
    }
}
