package servlets;

import dao.LikedDAO;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import model.User;
import model.UserList;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Writer;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_28);
        //cfg.setDirectoryForTemplateLoading(new File("./src/main/java/templates"));
        cfg.setDirectoryForTemplateLoading(new File("./src/main/java/resources/static/html/"));
        cfg.setDefaultEncoding("UTF-8");
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        cfg.setLogTemplateExceptions(false);
        cfg.setWrapUncheckedExceptions(true);
        Map<String, Object> model = new HashMap<>();
        Template template = cfg.getTemplate("login.html");
        Writer out = resp.getWriter();

        try {
            template.process(model, out);
        } catch (TemplateException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String password = req.getParameter("password");
        String eMail = req.getParameter("email");

        if (eMail != null){

            if (Character.isUpperCase(eMail.charAt(0))){
                resp.sendRedirect("/login");
            }
            String[] arr = eMail.split("@");
            String login = arr[0];

            UserList userList = new UserList();
            List<String> loginList = userList.get().stream().
                    map(User::getName).
                    map(s -> Character.toLowerCase(s.charAt(0)) + s.substring(1)).
                    map(String::valueOf).
                    collect(Collectors.toList());
            if (!loginList.contains(login) || password == null || !password.equals("111")){
                 resp.sendRedirect("/login");
             }

            Cookie cookie = new Cookie("login", login);
            System.out.println("login = " + login);
            resp.addCookie(cookie);
            resp.sendRedirect("/users");
        }
        else resp.sendRedirect("/login");
    }
}

