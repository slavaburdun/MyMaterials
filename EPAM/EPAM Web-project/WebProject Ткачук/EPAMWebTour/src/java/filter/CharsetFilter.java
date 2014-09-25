package filter;

import java.io.IOException;
import javax.servlet.*;

/**
 * This class represents an implementation of Filter interface.The main task of
 * this class is getting request encoding and setting it to UTF-8.All parameters
 * requred for this class are written in WEB-INF/web.xml (filter mapping etc).
 *
 * @author kelebra
 * @see Filter
 * @since 1.5
 */
public class CharsetFilter implements Filter {
    /*
     * Encoding parameter
     */

    private String encoding = "utf-8";

    /**
     * Sets encoding and passes request, responce to the next filter in chain.
     * @param request
     * @param response
     * @param filterChain chain of filters
     * @throws IOException
     * @throws ServletException 
     */
    
    @Override
    public void doFilter(ServletRequest request,
            ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        request.setCharacterEncoding(encoding);
        filterChain.doFilter(request, response);
    }

    /**
     * Gets patameter from file, mentioned above, and sets encoding.
     * @param filterConfig
     * @throws ServletException 
     */
    @Override
    public void init(FilterConfig filterConfig)
            throws ServletException {
        String encodingParam = filterConfig.getInitParameter("encoding");
        if (encodingParam != null) {
            encoding = encodingParam;
        }
    }

    @Override
    public void destroy() {
        // nothing todo
    }
}
