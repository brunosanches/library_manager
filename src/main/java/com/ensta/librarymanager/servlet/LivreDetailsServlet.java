package com.ensta.librarymanager.servlet;

import com.ensta.librarymanager.exception.ServiceException;
import com.ensta.librarymanager.model.Emprunt;
import com.ensta.librarymanager.model.Livre;
import com.ensta.librarymanager.model.Membre;
import com.ensta.librarymanager.service.EmpruntService;
import com.ensta.librarymanager.service.LivreService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/livre_details")
public class LivreDetailsServlet extends HttpServlet {
    LivreService livreService = LivreService.getInstance();
    EmpruntService empruntService = EmpruntService.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        Livre livre = null;
        List<Emprunt> empruntList = new ArrayList<>();
        try {
            livre = livreService.getById(id);
            empruntList = empruntService.getListCurrentByLivre(id);
        } catch (ServiceException e) {
            e.printStackTrace();
        }

        request.setAttribute("livre", livre);
        request.setAttribute("empruntList", empruntList);

        this.getServletContext().getRequestDispatcher("/WEB-INF/View/livre_details.jsp")
                .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));

        try {
            Livre livre = livreService.getById(id);
            livre.setTitre(request.getParameter("titre"));
            livre.setAuteur(request.getParameter("auteur"));
            livre.setIsbn(request.getParameter("isbn"));

            livreService.update(livre);
        } catch (ServiceException e) {
            e.printStackTrace();
            throw new ServletException(e.getMessage());
        }

        doGet(request, response);
    }

}
