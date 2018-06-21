package servlets;

        import java.io.IOException;
        import javax.servlet.*;
        import javax.servlet.http.Cookie;
        import javax.servlet.http.HttpServletRequest;
        import javax.servlet.http.HttpServletResponse;
        import javax.servlet.http.HttpSession;


public class LogFilter implements Filter{

    @Override
    public void init (FilterConfig filterConfig) {
    }
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest)request;
        HttpServletResponse resp = (HttpServletResponse) response;
//        if (req.getServletPath().equals("/login")) chain.doFilter(request, response);
        Cookie[] cookieArray = req.getCookies();
        if (cookieArray != null) {
            for (Cookie cookie : cookieArray) {
                if (cookie.getName().equals("login")) {
                    chain.doFilter(request, response);
                }
            }
        }
        RequestDispatcher rd = req.getRequestDispatcher("/login");
        rd.include(request, response);
//        HttpServletRequest req = (HttpServletRequest) request;
//        HttpServletResponse resp = (HttpServletResponse) response;

//        HttpSession session = req.getSession(false);
//
//        if (session == null) {   //checking whether the session exists
//            RequestDispatcher rd = req.getRequestDispatcher("/login");
//            rd.include(req, resp);
//        } else {
//
//            chain.doFilter(req, resp);
//        }
    }

    @Override
    public void destroy(){
    }
}

