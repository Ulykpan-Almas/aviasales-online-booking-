<%@ page import="kz.javaee.db.Users" %>
<%@ page import="kz.javaee.db.Airlines" %><%
Users currentUser =(Users)request.getSession().getAttribute("CURRENT_USER");
Users admin = (Users)request.getSession().getAttribute("admin");

boolean ONLINE = false;
boolean Admin = false;
if(currentUser!=null){
    ONLINE=true;
    if (admin!=null){
        Admin=true;
    }
}

%>