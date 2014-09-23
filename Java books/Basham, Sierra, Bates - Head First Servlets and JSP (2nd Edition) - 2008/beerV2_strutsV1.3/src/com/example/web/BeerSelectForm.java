package com.example.web;

// Struts imports
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMessage;

// Servlet imports
import javax.servlet.http.HttpServletRequest;

public class BeerSelectForm extends ActionForm
{
    private String color;

    public void setColor(String color)
    {
        this.color = color;
    }

    public String getColor()
    {
        return color;
    }

    private static final String VALID_COLORS = "amber,dark,light,brown";

    public ActionErrors validate(ActionMapping mapping,
            HttpServletRequest request)
    {
        ActionErrors errors = new ActionErrors();

        if ( VALID_COLORS.indexOf(color) == -1 )
        {
            errors.add("color", new ActionMessage("error.colorField.notValid"));
        }

        return errors;
    }
}
