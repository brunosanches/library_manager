package com.ensta.librarymanager.servlet;

import com.ensta.librarymanager.exception.ServiceException;
import com.ensta.librarymanager.service.MembreService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/membre_add")
public class MembreAddServlet extends HttpServlet {
    MembreService membreService = MembreService.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        this.getServletContext().getRequestDispatcher("/WEB-INF/View/membre_add.jsp").
                forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String nom = request.getParameter("nom");
        String prenom = request.getParameter("prenom");
        String adresse = request.getParameter("adresse");
        String email = request.getParameter("emila");
        String telephone = request.getParameter("telephone");

        try {
            membreService.create(nom, prenom, adresse, email, telephone);
        } catch (ServiceException e) {
            e.printStackTrace();
        }

        doGet(request, response);
    }
}
