<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.ejbctl.entities.Emprunt" %>

<html>
<head>
    <title>Emprunts en cours</title>
</head>
<body>
<h2>Mes Emprunts</h2>
<ul>
    <%
        List<Emprunt> emprunts = (List<Emprunt>) request.getAttribute("empruntList");
        for (Emprunt emprunt : emprunts) {
    %>
    <li>
        <%= emprunt.getCd().getTitre() %> - Emprunté le : <%= emprunt.getDateEmprunt() %>
        <form action="RetourServlet" method="post">
            <input type="hidden" name="empruntId" value="<%= emprunt.getId() %>"/>
            <button type="submit">Retourner</button>
        </form>
    </li>
    <% } %>
</ul>
</body>
</html>
