package com.ensta.librarymanager.servlet;

import com.ensta.librarymanager.exception.ServiceException;
import com.ensta.librarymanager.model.Abonnement;
import com.ensta.librarymanager.model.Emprunt;
import com.ensta.librarymanager.model.Membre;
import com.ensta.librarymanager.service.EmpruntService;
import com.ensta.librarymanager.service.MembreService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/membre_details")
public class MembreDetailsServlet extends HttpServlet {
    MembreService membreService = MembreService.getInstance();
    EmpruntService empruntService = EmpruntService.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Membre membre = null;
        List<Emprunt> empruntList = new ArrayList<>();
        try {
            membre = membreService.getById(id);
            empruntList = empruntService.getListCurrentByMembre(id);
        } catch (ServiceException e) {
            e.printStackTrace();
        }

        request.setAttribute("membre", membre);
        request.setAttribute("empruntList", empruntList);

        this.getServletContext().getRequestDispatcher("/WEB-INF/View/membre_details.jsp")
                .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        Membre membre = null;

        try {
            MembreService membreService = MembreService.getInstance();
            membre = membreService.getById(id);

            membre.setNom(request.getParameter("nom"));
            membre.setPrenom(request.getParameter("prenom"));
            membre.setAbonnement(Abonnement.valueOf(request.getParameter("abonnement")));
            membre.setAdresse(request.getParameter("adresse"));
            membre.setEmail(request.getParameter("email"));
            membre.setTelephone(request.getParameter("telephone"));

            membreService.update(membre);
        } catch (ServiceException e) {
            e.printStackTrace();
        }

        doGet(request, response);
    }
}
