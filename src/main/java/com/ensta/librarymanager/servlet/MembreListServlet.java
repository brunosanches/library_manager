package com.ensta.librarymanager.servlet;

import com.ensta.librarymanager.exception.ServiceException;
import com.ensta.librarymanager.model.Membre;
import com.ensta.librarymanager.service.MembreService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/membre_list")
public class MembreListServlet extends HttpServlet {
    MembreService membreService = MembreService.getInstance();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Membre> membreList = new ArrayList<>();
        try {
            membreList = membreService.getList();
        } catch (ServiceException e) {
            e.printStackTrace();
        }

        request.setAttribute("membreList", membreList);
        this.getServletContext().getRequestDispatcher("/WEB-INF/View/membre_list.jsp")
                .forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        doGet(request, response);
    }
}
