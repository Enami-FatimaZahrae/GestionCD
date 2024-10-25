<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.ejbctl.entities.CD" %>

<html>
<head>
    <title>Liste des CD disponibles</title>
</head>
<body>
<h2>Liste des CD disponibles</h2>
<ul>
    <%
        List<CD> cds = (List<CD>) request.getAttribute("cdList");
        for (CD cd : cds) {
    %>
    <li>
        <%= cd.getTitre() %> - <%= cd.getAuteur() %>
        <form action="EmpruntServlet" method="post">
            <input type="hidden" name="cdId" value="<%= cd.getId() %>"/>
            <button type="submit">Emprunter</button>
        </form>
    </li>
    <% } %>
</ul>
</body>
</html>
