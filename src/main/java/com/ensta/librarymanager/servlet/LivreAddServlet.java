package com.ensta.librarymanager.servlet;

import com.ensta.librarymanager.exception.ServiceException;
import com.ensta.librarymanager.model.Livre;
import com.ensta.librarymanager.service.LivreService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/livre_add")
public class LivreAddServlet extends HttpServlet {
    LivreService livreService = LivreService.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        this.getServletContext().getRequestDispatcher("/WEB-INF/View/livre_add.jsp").
                forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        Livre livre = new Livre(-1,
                request.getParameter("titre"),
                request.getParameter("auteur"),
                request.getParameter("isbn"));

        try {
            livre = livreService.create(livre);
            response.sendRedirect("/TP3Ensta/livre_details?id=" + livre.getId());
        } catch (ServiceException e) {
            e.printStackTrace();
            throw new ServletException(e.getMessage());
        }

    }

}
