package pl.edu.pjwstk.jaz;

import org.springframework.http.HttpStatus;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ExampleFilter extends HttpFilter {
    private final UserSession userSession;

    public ExampleFilter(UserSession userSession) {
        this.userSession = userSession;
    }

    @Override
    protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {

        if (isUserLogged() || isSiteAllowed(request)) {
            chain.doFilter(request, response);
        } else {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
        }

        super.doFilter(request, response, chain);
    }

    private boolean isSiteAllowed(HttpServletRequest request) {
        return request.getContextPath().equals("/api/auth0");
    }

    private boolean isUserLogged() {  // tutaj sprawdzać czy użytkownik jest zalogowany
        return userSession.isLoggedIn();
    }
}