package servlets;


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
import java.util.HashMap;

import java.util.Map;
import java.util.Set;

public class UsersServlet extends HttpServlet {
    private final Set<User> likedSet;
    private UserList userList = new UserList();
    private int index = 0;

    public UsersServlet(Set<User> likedSet) {
        this.likedSet = likedSet;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_28);
        //cfg.setDirectoryForTemplateLoading(new File("./src/main/java/templates"));
        cfg.setDirectoryForTemplateLoading(new File("./src/main/java/resources/static/html/"));
        cfg.setDefaultEncoding("UTF-8");
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        cfg.setLogTemplateExceptions(false);
        cfg.setWrapUncheckedExceptions(true);
        Map<String, Object> model = new HashMap<>();
        model.put("user", "Andrew");

        model.put("name", userList.get().get(index).getName());
        model.put("url", userList.get().get(index).getUrl());
        model.put("id", userList.get().get(index).getId());

        Template template = cfg.getTemplate("like-page.html");
        Writer out = resp.getWriter();

        try {
            template.process(model, out);
        } catch (TemplateException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

            if (req.getParameter("answer") != null && req.getParameter("answer").equals("Next")) {
                indexRoll();
            }

           if (req.getParameter("answer") != null && req.getParameter("answer").equals("Like")) {

               LikedDAO likedDAO = new LikedDAO();
               likedDAO.add(userList.get().get(index));
               likedSet.add(userList.get().get(index));
               indexRoll();
           }

            doGet(req, resp);
    }
    private void indexRoll(){
        if (index < userList.get().size() - 1) {
            index++;
        } else index = 0;
    }
}
