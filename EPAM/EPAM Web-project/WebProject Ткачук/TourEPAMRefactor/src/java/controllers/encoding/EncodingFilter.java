package controllers.encoding;

import java.io.IOException;
import javax.servlet.*;

/**
 * Responsible for request encoding.
 * 
* @author kelebra
 * @version 1.0 July 27, 2012.
 * @see Filter
 * @see UTF-8
 * @see HttpServer
 * 
*/
public class EncodingFilter implements Filter {

    private String encoding;

    @Override
    public void init(FilterConfig config) throws ServletException {
        encoding = config.getInitParameter("requestEncoding");

        if (encoding == null) {
            encoding = "UTF-8";
        }
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain next)
            throws IOException, ServletException {
        if (null == request.getCharacterEncoding()) {
            request.setCharacterEncoding(encoding);
        }
        response.setContentType("text/html; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        next.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }
}