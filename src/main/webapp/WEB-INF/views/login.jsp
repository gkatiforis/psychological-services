
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>

    <%@include file="generic/meta.jsp" %>

    <title>Login page</title>

    <%@include file="generic/head.jsp" %>
    <script src="<c:url value='/static/js/register.js' />"></script>
    <link href="<c:url value='/static/css/login.css' />" rel="stylesheet"></link>

</head>

<body>
<div id="mainWrapper">
    <%--<%@include file="aheader.jsp" %>--%>


        <c:set var="loggedinuser" value="${loggedinuser}" scope="request"/>
        <c:import url="generic/navbar.jsp"/>

    <input type="hidden" id="command" value="${command}"/>



           <div class="login-container well" style = "display:none">

                <%--<div class="login-form">--%>

                    <div class="row">
                        <div class=" col-md-5 col-md-offset-4" style="max-width: 500px">
                            <c:url var="loginUrl" value="/login" />

                            <form role="form" action="${loginUrl}" method="post" class="f1">
                                <c:if test="${param.error != null}">
                                <div class="alert alert-danger">
                                    <p>Λάθος όνομα ή κωδικός χρήστη</p>
                                </div>
                                </c:if>
                                <c:if test="${param.logout != null}">
                                <div class="alert alert-success">
                                    <p>Έχετε αποσυνδεθεί επιτυχώς</p>
                                </div>
                                </c:if>

                                <h3>Συνδεση  </h3>
                                <br>
                                <br>

                                    <div class="form-group">

                                        <input type="text" id="username" name="ssoId" placeholder="Όνομα χρήστη" required class="f1-first-name form-control" id="f1-first-name">
                                    </div>
                                    <div class="form-group">

                                        <input type="password" id="password" name="password" placeholder="Κωδικός πρόσβασης"  class="f1-last-name form-control" id="f1-last-name">
                                    </div>

                                        <input type="hidden" name="${_csrf.parameterName}"  value="${_csrf.token}" />
                                        <div class="input-group input-sm">
                                            <div class="checkbox">
                                            <label><input type="checkbox" id="rememberme" name="remember-me"> Να με θυμάσε</label>
                                            </div>
                                        </div>
                                <div class="f1-buttons">
                                    <button type="submit" class="btn btn-next">Σύνδεση</button>
                                </div>
                                         <br>
                                        <hr>
                                        Δεν έχεις λογαρισμό;   <a id="registerUrl" href="#">Εγγραφή</a>

                            </form>
                        </div>
                    </div>

                <%--</div>--%>

        </div>



        <div class="register-container well"style = "display:none">

            <div class="row">
                <div class=" col-md-5 col-md-offset-4" style="max-width: 500px">

                    <form method="POST" action="" name="registerForm"  class="f1" id="registerForm">

                        <h2>Εγγραφή </h2>
                        <br>
                        <div class="form-group">
                            <input type="text" id="email" name="email" placeholder="Email"  class="f1-last-name form-control">
                        </div>
                        <div class="form-group">
                            <input type="text" id="ssoId" name="ssoId" placeholder="Όνομα χρήστη" required class="f1-first-name form-control" >
                        </div>
                        <div class="form-group">
                            <input type="password" id="rpassword" name="password" placeholder="Κωδικός πρόσβασης"  class="f1-last-name form-control" >
                        </div>
                        <div class="form-group">
                            <input type="password" id="confirmpassword" name="confirmpassword" placeholder="Επανάληψη κωδικόυ πρόσβασης"  class="f1-first-name form-control">
                        </div>



                        <div class="form-group">
                            <b> Είμαι:</b>
                                    <select id="userProfiles" name="userProfiles" class="selectpicker">

                                        <c:forEach var="item" items="${roles}">
                                            <c:if test="${item.id == 1}">
                                                <option value="${item.id}">Ασθενής</option>
                                            </c:if>
                                            <c:if test="${item.id == 2}">
                                                <option value="${item.id}">Ψυχολόγος</option>
                                            </c:if>
                                        </c:forEach>
                                    </select>
                                    <%--<form:select items="${roles}" multiple="true" itemValue="id" id="userProfiles" name="userProfiles" itemLabel="type" class="" />--%>
                        </div>
                        <div class="row">
                            <div class="form-actions floatRight">


                                <input type="hidden" id="csrfToken" value="${_csrf.token}"/>
                                <input type="hidden" id="csrfHeader" value="${_csrf.headerName}"/>
                                <!-- Result Container  -->
                                <div id="resultContainer" style="display: none;">
                                    <%--<hr/>--%>
                                    <%--<h4 style="color: green;">JSON Response From Server</h4>--%>
                                    <pre style="color: green;">
                                    <%--<code></code>--%>
                                   </pre>
                                </div>

                            </div>
                        </div>

                        <div class="f1-buttons">
                            <button type="submit" id="registerButton" class="btn btn-next">Εγγραφή</button>
                        </div>
                        <hr>
                        Έχεις λογαρισμό;   <a id="loginUrl" href="#">Σύνδεση</a>

                    </form>
                </div>
            </div>
        </div>

</div>

<%@include file="generic/footer.jsp" %>
</body>
</html>