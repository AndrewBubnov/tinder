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
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        if (req.getRequestURI().equals("/login") || req.getRequestURI().startsWith("/assets/") || req.getRequestURI().startsWith("/images/*")) {

            chain.doFilter(request, response);
        } else {
            boolean loginPresent = false;
            Cookie[] cookieArray = req.getCookies();
            if (cookieArray != null) {
                for (Cookie cookie : cookieArray) {
                    if (cookie.getName().equals("id")) {
                        loginPresent = true;
                        break;
                    }
                }
            }

            if (loginPresent) {
                chain.doFilter(request, response);
            } else {
                resp.sendRedirect("/login");
            }
        }
    }

    @Override
    public void destroy(){
    }
}

