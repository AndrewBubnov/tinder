package servlets;


import dao.LikedDAO;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;

import model.User;
import model.UserList;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.*;
import java.util.*;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class UsersServlet extends HttpServlet {
    private final Set<User> likedSet;
    private UserList userList = new UserList();
    private List<User> currentUserList = new ArrayList<>();
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
        Cookie[] cookies = req.getCookies();
        String login = "";
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("login")) {
                    login = cookie.getValue();
                }
            }
        }

        for (int i = 0; i < userList.get().size(); i++) {
            String name = userList.get().get(i).getName();
            User user = userList.get().get(i);
            if (!name.equalsIgnoreCase(login)){
               currentUserList.add(user);
            }
        }

        model.put("name", currentUserList.get(index).getName());
        model.put("url", currentUserList.get(index).getUrl());
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
               User likedUser = userList.get().get(index);
//               if (!likedDAO.getUsers().contains(likedUser)) {
//                   likedDAO.add(likedUser);
//               }
               likedSet.add(likedUser);
               indexRoll();
           }

            doGet(req, resp);
    }
    private void indexRoll(){
        if (index < currentUserList.size() - 1) {
            index++;
        } else index = 0;
    }
}
