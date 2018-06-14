package servlets;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

public class MessagesServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_28);
        cfg.setDirectoryForTemplateLoading(new File("./src/main/java/templates"));
        cfg.setDefaultEncoding("UTF-8");
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        cfg.setLogTemplateExceptions(false);
        cfg.setWrapUncheckedExceptions(true);
        Map<String, Object> model = new HashMap<>();
        int userId = Integer.parseInt((String)getServletContext().getAttribute("userId"));
        String message = "";
        switch (userId){
            case 2045 :
                message = "Hi! I am Anna! You look handsome!";
                break;
                case 1201 :
                message = "Hey! I am Victoria! How are you?";
                break;
                case 2326 :
                message = "Hello. My name is Johanna";
                break;
                case 2390 :
                message = "Hey there! I'm Mary, and you?";
                break;
                case 122 :
                message = "I'm Kristina. How is it going?";
                break;
                case 7965 :
                message = "Hi! I'm Sandy! How are you doing?";
                break;
                case 7734 :
                message = "Hello. My name is Mia.";
        }
        model.put("message", message);

        Template template = cfg.getTemplate("messagesTemplate.ftlh");
        Writer out = resp.getWriter();


        try {
            template.process(model, out);
        } catch (TemplateException e) {
            e.printStackTrace();
        }
    }
}
