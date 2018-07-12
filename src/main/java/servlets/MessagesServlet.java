package servlets;

import dao.MessageDAO;
import dao.UserDAO;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import model.Message;
import model.User;
import model.UserList;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MessagesServlet extends HttpServlet {
    private String message;
    private List<User> allUsersList;

    public MessagesServlet(List<User> allUsersList) {
        this.allUsersList = allUsersList;
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
        Template template = cfg.getTemplate("chat.ftlh");
        int userId = Integer.parseInt((String)getServletContext().getAttribute("userId"));

        User user = new User();
        for (User userOfList : allUsersList){
            if (userOfList.getId() == userId){
               user = userOfList;
            }
        }

        String name = user.getName();
        String url = user.getUrl();

        model.put("name", name);
        model.put("url", url);
        Writer out = resp.getWriter();


        Cookie[] cookies = req.getCookies();
        int loginId = 0;
        for (Cookie cookie : cookies){
            if (cookie.getName().equals("id")){
                loginId = Integer.parseInt(cookie.getValue());
                break;
            }
        }
        UserDAO userDAO = new UserDAO();
        String login = userDAO.getLoginById(loginId);
        MessageDAO messageDAO = new MessageDAO();
        if (message != null){
            messageDAO.addMessage(user, login, message);
            message = null;
        }
        model.put("login", login);
        model.put("loginUrl", userDAO.getUrlById(loginId));

        List<Message> outcomingMessageList = messageDAO.getMessages(loginId, userDAO.getIdByLogin(name), userId);
        List<Message> incomingMessageList = messageDAO.getMessages(userDAO.getIdByLogin(name), loginId, userId);

        model.put("outList", outcomingMessageList);
        model.put("inList", incomingMessageList);

        try {
            template.process(model, out);
        } catch (TemplateException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        message = req.getParameter("message");
        doGet(req, resp);

    }
}
