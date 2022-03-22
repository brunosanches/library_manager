package com.ensta.librarymanager.servlet;

import com.ensta.librarymanager.exception.ServiceException;
import com.ensta.librarymanager.model.Livre;
import com.ensta.librarymanager.model.Membre;
import com.ensta.librarymanager.service.EmpruntService;
import com.ensta.librarymanager.service.LivreService;
import com.ensta.librarymanager.service.MembreService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@WebServlet("/emprunt_add")
public class EmpruntAddServlet extends HttpServlet {
    EmpruntService empruntService = EmpruntService.getInstance();
    LivreService livreService = LivreService.getInstance();
    MembreService membreService = MembreService.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        List<Livre> livreList;
        List<Membre> membreList;

        try {
            livreList = livreService.getListDispo();
            membreList = membreService.getListMembreEmpruntPossible();

            request.setAttribute("livreList", livreList);
            request.setAttribute("membreList", membreList);
        } catch (ServiceException e) {
            e.printStackTrace();
            throw new ServletException(e.getMessage());
        }

        this.getServletContext().getRequestDispatcher("/WEB-INF/View/emprunt_add.jsp")
                .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        int idMembre = Integer.parseInt(request.getParameter("idMembre"));
        int idLivre = Integer.parseInt(request.getParameter("idLivre"));

        try {
            empruntService.create(idMembre, idLivre, LocalDate.now());
            response.sendRedirect("emprunt_list");
        } catch (ServiceException e) {
            e.printStackTrace();
            throw new ServletException(e.getMessage());
        }
    }
}
