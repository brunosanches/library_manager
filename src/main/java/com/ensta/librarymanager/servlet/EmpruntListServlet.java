package com.ensta.librarymanager.servlet;

import com.ensta.librarymanager.exception.ServiceException;
import com.ensta.librarymanager.model.Emprunt;
import com.ensta.librarymanager.service.EmpruntService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/emprunt_list")
public class EmpruntListServlet extends HttpServlet {
    EmpruntService empruntService = EmpruntService.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        List<Emprunt> empruntList;
        try {
            if (request.getParameterMap().containsKey("show") && request.getParameter("show").equals("all")) {
                empruntList = empruntService.getList();
            }
            else {
                empruntList = empruntService.getListCurrent();
            }
            request.setAttribute("empruntList", empruntList);
        } catch (ServiceException e) {
            e.printStackTrace();
            throw new ServletException(e.getMessage());
        }
        this.getServletContext().getRequestDispatcher("/WEB-INF/View/emprunt_list.jsp")
                .forward(request, response);
    }

}
