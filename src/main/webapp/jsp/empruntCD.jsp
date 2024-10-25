<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.ejbctl.entities.CD" %>
<%@ page import="com.example.ejbctl.ejb.CDService" %>
<%@ page import="javax.naming.InitialContext" %>

<%
    // Récupération du service CD via JNDI
    CDService cdService = (CDService) new InitialContext().lookup("java:module/CDService");
    List<CD> cdsDisponibles = cdService.listerTousLesCDs(); // Tous les CD
    List<CD> cdsEmpruntes = (List<CD>) session.getAttribute("cdsEmpruntes"); // CD déjà empruntés par l'utilisateur
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Emprunter des CD</title>
    <link rel="stylesheet" href="styles.css">
</head>
<body>

<h1>Liste des CDs Disponibles</h1>
<table>
    <thead>
    <tr>
        <th>Titre</th>
        <th>Auteur</th>
        <th>Action</th>
    </tr>
    </thead>
    <tbody>
    <% for (CD cd : cdsDisponibles) {
        if (cd.isDisponible()) { %>
    <tr>
        <td><%= cd.getTitre() %></td>
        <td><%= cd.getAuteur() %></td>
        <td>
            <form action="EmpruntServlet" method="post">
                <input type="hidden" name="cdId" value="<%= cd.getId() %>">
                <button type="submit">Emprunter</button>
            </form>
        </td>
    </tr>
    <%     } // Vérifie que le CD est disponible
    } %>
    </tbody>
</table>

<h2>Vos CDs Empruntés</h2>
<table>
    <thead>
    <tr>
        <th>Titre</th>
        <th>Auteur</th>
        <th>Action</th>
    </tr>
    </thead>
    <tbody>
    <% if (cdsEmpruntes != null) {
        for (CD cd : cdsEmpruntes) { %>
    <tr>
        <td><%= cd.getTitre() %></td>
        <td><%= cd.getAuteur() %></td>
        <td>
            <form action="RetourServlet" method="post">
                <input type="hidden" name="empruntId" value="<%= cd.getId() %>">
                <button type="submit">Retourner</button>
            </form>
        </td>
    </tr>
    <%     }
    } else { %>
    <tr>
        <td colspan="3">Vous n'avez emprunté aucun CD.</td>
    </tr>
    <% } %>
    </tbody>
</table>

</body>
</html>
