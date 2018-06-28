package servlets;


import dao.LikedDAO;
import dao.UserDAO;
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
    private List<User> allUsersList;
    private final Set<User> likedSet;
    //private UserList userList = new UserList();
    private List<User> currentUserList = new ArrayList<>();
    private int index = 0;

    public UsersServlet(List<User> allUsersList, Set<User> likedSet, List<User> currentUserList) {
        this.allUsersList = allUsersList;
        this.likedSet = likedSet;
        this.currentUserList = currentUserList;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_28);
        cfg.setDirectoryForTemplateLoading(new File("./src/main/java/resources/static/html/"));
        cfg.setDefaultEncoding("UTF-8");
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        cfg.setLogTemplateExceptions(false);
        cfg.setWrapUncheckedExceptions(true);
        Map<String, Object> model = new HashMap<>();
        Cookie[] cookies = req.getCookies();

        int id = 0;
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("id")) {
                    id = Integer.parseInt(cookie.getValue());
                }
            }
        }
        UserDAO userDAO = new UserDAO();
        String login = userDAO.getLoginById(id);

//        currentUserList.clear();


        if (currentUserList.size() < allUsersList.size() - 1) {
            for (int i = 0; i < allUsersList.size(); i++) {
                String name = allUsersList.get(i).getName();
                User user = allUsersList.get(i);

                if (!name.equalsIgnoreCase(login)) {
                    currentUserList.add(user);
                }
            }
        }
        model.put("name", currentUserList.get(index).getName());
        model.put("url", currentUserList.get(index).getUrl());
        model.put("id", allUsersList.get(index).getId());
        model.put("login", login);

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

        if (req.getParameter("answer") != null && req.getParameter("answer").equals("Log out")) {
            likedSet.clear();
            currentUserList.clear();
            index = 0;
            resp.sendRedirect("/logout");
            return;
        }


        if (req.getParameter("answer") != null && req.getParameter("answer").equals("Like")) {
               LikedDAO likedDAO = new LikedDAO();
               User likedUser = currentUserList.get(index);

//               if (!likedDAO.getUsers().contains(likedUser)) {
//                   likedDAO.add(likedUser);
//               }
               likedSet.add(likedUser);
           }

        if (index < currentUserList.size() - 1) {
            index++;
        } else {
            //currentUserList.clear();
            index = 0;
            resp.sendRedirect("/liked");
        }
            doGet(req, resp);
    }
}
