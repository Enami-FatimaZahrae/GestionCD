package com.example.ejbctl.servlets;

import com.example.ejbctl.ejb.EmpruntService;
import com.example.ejbctl.entities.Utilisateur;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/RetourServlet")
public class RetourServlet extends HttpServlet {

    @EJB
    private EmpruntService empruntService;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String empruntId = request.getParameter("empruntId");
        Utilisateur utilisateur = (Utilisateur) request.getSession().getAttribute("utilisateur");

        if (empruntId != null && utilisateur != null) {
            try {
                empruntService.retournerCD(Integer.parseInt(empruntId), utilisateur);
                response.sendRedirect("empruntsUtilisateur.jsp");
            } catch (Exception e) {
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erreur lors du retour du CD.");
            }
        } else {
            response.sendRedirect("login.jsp");
        }
    }
}
