import model.User;
import model.UserList;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import servlets.*;


import java.util.ArrayList;
import java.util.HashSet;

import java.util.List;
import java.util.Set;

public class ServerApp {
       public static void main(String[] args) throws Exception {
           //Database base = new DataBaseHashMap();
           Set<User> likedSet = new HashSet<>();
           List<User> allUsersList = new UserList().get();
           List<User> currentUserList = new ArrayList<>();
        new Server(8081) {{
            setHandler(new ServletContextHandler() {{
                           addServlet(new ServletHolder(new UsersServlet(allUsersList, likedSet, currentUserList)) ,"/users");
                           addServlet(new ServletHolder(new LikedServlet(likedSet, currentUserList)) ,"/liked");
                           addServlet(new ServletHolder(new MessagesServlet(allUsersList)) ,"/messages/*");
                           addServlet(new ServletHolder(new StaticServlet()) ,"/assets/*");
                           addServlet(new ServletHolder(new ImageServlet()) ,"/images/*");
                           addServlet(new ServletHolder(new LoginServlet(allUsersList)) ,"/login");
                           addServlet(new ServletHolder(new LogOutServlet(allUsersList)) ,"/logout");
                           addFilter(LogFilter.class, "/*", null);
                       }}
            );
            start();
            join();
        }};
    }
}