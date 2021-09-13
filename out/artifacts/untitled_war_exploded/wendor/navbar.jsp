<%@include file="user.jsp"%>

<nav class="navbar navbar-expand-lg navbar-dark bg-primary" style="color:white;">
    <a class="navbar-brand" href="/findair" style="margin-left: 190px; color: white">
        <img src="logo/aviasales-4.svg" width="30" height="30" class="d-inline-block align-top" alt="" loading="lazy">
        aviasales</a>

    <a class="navbar-brand" href="/home2" style="margin-left: 500px; font-size: 17px; color: white; text-align: right">
        Home</a>

    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <%
                if (Admin){
            %>
            <li class="nav-item">
                <a class="nav-link" href="/addFlight">Add Airline</a>
            </li>
            <%
                }
            %>
            <%
                if(ONLINE){
            %>
            <li class="nav-item">
                <a class="nav-link" href="/profile"><%=currentUser.getFullName()%></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/home2">Home</a>
            </li>
            <li class="nav-item" style="text-align: end">
                <a class="nav-link" href="/logout">Logout</a>
            </li>


            <%
                }else{
            %>

            <li class="nav-item" style="color: white">
                <a class="nav-link" href="/home2">Flight</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/login">Login</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/register">Register</a>
            </li>
            <%
                }
            %>


        </ul>


    </div>
</nav>