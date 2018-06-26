package servlets;

import dao.Database;
import dao.LikedDAO;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;

import model.User;
import model.UserList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class LikedServlet extends HttpServlet {
    private final Set<User> likedSet;

    public LikedServlet(Set<User> likedSet) {
        this.likedSet = likedSet;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_28);
        cfg.setDirectoryForTemplateLoading(new File("./src/main/java/resources/static/html/"));
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        cfg.setLogTemplateExceptions(false);
        cfg.setWrapUncheckedExceptions(true);
        resp.setContentType("text/html; charset=utf-8");
        Map<String, Object> model = new HashMap<>();
        Template template = cfg.getTemplate("people-list.html");
        Writer out = resp.getWriter();
        LikedDAO likedDAO = new LikedDAO();
        List<User> likedList = likedDAO.getUsers();
        model.put("liked", likedSet);

        try {
            template.process(model, out);
        } catch (TemplateException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("chat").equals("Chat")){
            UserList userList = new UserList();
            String userId = req.getParameter("userId");
            String name = req.getParameter("name");
            //userId = userId.charAt(0) + userId.substring(2);
            getServletContext().setAttribute("userId", userId);
            resp.sendRedirect("/messages/" + userId);
        }
    }
}
