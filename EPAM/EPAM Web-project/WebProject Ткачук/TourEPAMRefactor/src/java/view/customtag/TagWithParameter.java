package view.customtag;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

public class TagWithParameter extends TagSupport {

    private String input;

    @Override
    public int doStartTag() throws JspException {
        try {
            JspWriter out = pageContext.getOut();
            out.println(input);
        } catch (IOException ex) {
            Logger.getLogger(TagWithParameter.class.getName()).log(Level.SEVERE, null, ex);
        }
        return SKIP_BODY;
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }
}
