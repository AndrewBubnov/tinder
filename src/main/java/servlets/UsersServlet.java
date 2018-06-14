package servlets;


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
import java.io.File;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;

import java.util.Map;
import java.util.Set;

public class UsersServlet extends HttpServlet {
    private final Set<User> likedSet;
    UserList userList = new UserList();
    int index = 0;

    public UsersServlet(Set<User> likedSet) {
        this.likedSet = likedSet;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_28);
        cfg.setDirectoryForTemplateLoading(new File("./src/main/java/templates"));
        cfg.setDefaultEncoding("UTF-8");
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        cfg.setLogTemplateExceptions(false);
        cfg.setWrapUncheckedExceptions(true);
        Map<String, Object> model = new HashMap<>();
        model.put("user", "Andrew");

        model.put("name", userList.get().get(index).getName());
        model.put("url", userList.get().get(index).getUrl());
        Template template = cfg.getTemplate("usersTemplate.ftlh");
        Writer out = resp.getWriter();


        try {
            template.process(model, out);
        } catch (TemplateException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

            if (req.getParameter("direction") != null && req.getParameter("direction").equals("Next")) {
                if (index < userList.get().size() - 1) {
                    index++;
                } else index = 0;
            }
            if (req.getParameter("direction") != null && req.getParameter("direction").equals("Previous")) {
                if (index > 0) {
                    index--;
                } else index = userList.get().size() - 1;
            }
           if (req.getParameter("answer") != null && req.getParameter("answer").equals("Like")) {
                    likedSet.add(userList.get().get(index));
           }

            doGet(req, resp);
    }
}
