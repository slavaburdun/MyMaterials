package customtag;

import java.io.IOException;
import java.io.StringWriter;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class Title extends SimpleTagSupport {

    private String title;

    public void setTitle(String title) {
        this.title = title;
    }
    StringWriter sw = new StringWriter();

    @Override
    public void doTag() throws JspException, IOException {
        {
            if (title != null) {
                JspWriter out = getJspContext().getOut();
                out.println("<title>");
                out.println(title);
                out.println("</title>");
            } else {
                getJspBody().invoke(sw);
                getJspContext().getOut().println(sw.toString());
            }
        }
    }
}