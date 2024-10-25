package com.example.ejbctl.servlets;

import com.example.ejbctl.ejb.EmpruntService;
import com.example.ejbctl.entities.CD;
import com.example.ejbctl.entities.Utilisateur;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/EmpruntServlet")
public class EmpruntServlet extends HttpServlet {

    @EJB
    private EmpruntService empruntService;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String cdId = request.getParameter("cdId");
        Long utilisateur = (Long) request.getSession().getAttribute("utilisateur"); // On suppose que l'utilisateur est connecté

        if (cdId != null && utilisateur != null) {
            try {
                CD cd = empruntService.emprunterCD(Long.valueOf(Integer.parseInt(cdId)), utilisateur);
                if (cd != null) {
                    response.sendRedirect("empruntsUtilisateur.jsp");
                } else {
                    response.sendError(HttpServletResponse.SC_BAD_REQUEST, "CD non disponible pour emprunt.");
                }
            } catch (Exception e) {
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erreur d'emprunt.");
            }
        } else {
            response.sendRedirect("login.jsp");
        }
    }
}
