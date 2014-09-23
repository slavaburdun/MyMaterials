package com.example.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.example.model.BeerExpert;

public class BeerSelectAction extends Action
{

    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
    {

        // Cast the form to the application-specific form
        BeerSelectForm myForm = (BeerSelectForm) form;

        // Process the business logic
        BeerExpert be = new BeerExpert();
        List result = be.getBrands(myForm.getColor());

        // Forward to the Results view
        // (and store the data in the request-scope)
        request.setAttribute("styles", result);
        return mapping.findForward("show_results");
    }
}
