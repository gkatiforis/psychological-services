<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>

    <%@include file="generic/meta.jsp" %>

    <title>Login page</title>

    <%@include file="generic/head.jsp" %>

    <link href="<c:url value='/static/css/advisedPerson.css' />" rel="stylesheet"></link>

</head>

<body>


<div class="generic-container" >



    <c:set var="loggedinuser" value="${loggedinuser}" scope="request"/>
    <c:import url="generic/navbar.jsp"/>



    <div class="container">
        <div class="row profile">
            <div class="col-md-3">
                <div class="profile-sidebar">
                    <!-- SIDEBAR USERPIC -->
                    <div class="profile-userpic">
                        <img width="100" height="100" src="../../static/img/user.jpg" class="img-responsive" alt="">
                    </div>
                    <!-- END SIDEBAR USERPIC -->
                    <!-- SIDEBAR USER TITLE -->
                    <div class="profile-usertitle">
                        <div class="profile-usertitle-name">
                            ${advisedPerson.first_name}${advisedPerson.last_name}
                        </div>
                        <%--<div class="profile-usertitle-job">--%>
                        <%--</div>--%>
                    </div>
                    <!-- END SIDEBAR USER TITLE -->
                    <!-- SIDEBAR BUTTONS -->


                    <!-- END SIDEBAR BUTTONS -->
                    <!-- SIDEBAR MENU -->

                    <c:if test="${loggedinuser.id == advisedPerson.id}">

                        <div class="profile-usermenu">
                            <ul class="nav">
                                <li class="active" id="personal">
                                    <a href="#">
                                        <i class="glyphicon glyphicon-home"></i>
                                        Προσωπικά στοιχεία </a>
                                </li>
                                <li>
                                    <a href="#">
                                        <i class="glyphicon glyphicon-user"></i>
                                        Ρυθμίσεις λογαριασμού </a>
                                </li>
                                <%--<li>--%>
                                    <%--<a href="#" target="_blank">--%>
                                        <%--<i class="glyphicon glyphicon-ok"></i>--%>
                                        <%--Tasks </a>--%>
                                <%--</li>--%>
                                <%--<li>--%>
                                    <%--<a href="#">--%>
                                        <%--<i class="glyphicon glyphicon-flag"></i>--%>
                                        <%--Help </a>--%>
                                <%--</li>--%>
                            </ul>
                        </div>

                    </c:if>

                    <!-- END MENU -->
                </div>
            </div>
            <div class="col-md-9">
                <div class="profile-content">

                    <div class="card pcard">
                        <div class="card-body">
                            <h4 class="card-title ">Περιγραφή
                            </h4>
                            <hr>
                            <%--<h6 class="card-subtitle mb-2 text-muted"></h6>--%>
                            <p class="card-text">${advisedPerson.description}</p>
                            <%--<a href="#" class="card-link ">more. . . </a>--%>
                            <%--<a href="#" class="card-link">Another link</a>--%>
                        </div>
                    </div>
                    <div class="card pcard">
                        <div class="card-body">
                            <h4 class="card-title ">Παθήσεις

                                <c:if test="${loggedinuser.id == advisedPerson.id}">
                                    <span id="addSkills"  class="editButton"></span>
                                    <%--<span id="removeDisorders"  class="remove"></span>--%>
                                </c:if>


                            </h4>
                            <hr>
                            <%--<h6 class="card-subtitle mb-2 text-muted"></h6>--%>
                            <p class="card-text" id="skillsPanel"></p>
                            <%--<a href="#" class="card-link ">more. . . </a>--%>
                            <%--<a href="#" class="card-link">Another link</a>--%>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div id="editProfileModal" class="modal fade" role="dialog">
            <div class="modal-dialog">

                <form method="POST" action="" name="saveAdvisedPersonForm"  class="" id="saveAdvisedPersonForm">

                    <!-- Modal content-->
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal">&times;</button>
                            <h4 class="modal-title">Προφίλ</h4>
                        </div>
                        <div class="modal-body">



                            <h4>Προσωπικά στοιχεία</h4>


                            <div class="row">


                                <div class="col-md-12">
                                    <div class="form-group">

                                        <input type="text"  id="first_name" name="first_name" value="${advisedPerson.first_name}" placeholder="Όνομα" class="form-control input-sm" />
                                    </div>
                                </div>


                                <div class="col-md-12">
                                    <div class="form-group">
                                        <input type="text"  id="last_name" name="last_name" value="${advisedPerson.last_name}" placeholder="Επώνυμο"  class="form-control input-sm" />
                                    </div>

                                </div>



                                <div class="col-md-12">
                                    <div class="form-group">
                                        <textarea class="form-control" rows="5" id="description" name="description"  placeholder="Description"  >${advisedPerson.description}</textarea>
                                    </div>
                                </div>






                            </div>



                        </div>
                        <div class="modal-footer">
                            <input type="hidden"  id="id" name="id"  value="${advisedPerson.id}" class="form-control input-sm" />
                            <input type="hidden"  id="loggedinUserId" name="loggedinUserId"  value="${loggedinUser.id}" class="form-control input-sm" />
                            <input type="hidden" id="csrfToken" value="${_csrf.token}"/>
                            <input type="hidden" id="csrfHeader" value="${_csrf.headerName}"/>

                            <button type="button" class="btn btn-default" data-dismiss="modal">Κλείσιμο</button>
                            <button type="submit" id="saveProfile" class="btn btn-success" >Αποθήκευση</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>


    <div id="editSkillsModal" class="modal fade" role="dialog">
        <div class="modal-dialog">

            <form method="POST" action="saveSkills" name="saveSkillsForm"  class="" id="saveSkillsForm">
                <!-- Modal content-->
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                        <h4 class="modal-title">Προσθήκη</h4>
                    </div>
                    <div class="modal-body">

                        <label for="id_label_multiple_skills">
                            Επιλέξτε παθήσης
                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            <select class="js-example-basic-multiple js-states form-control" id="id_label_multiple_skills" multiple="multiple" name="disorder"  style="width: 95%"></select>
                        </label>
                    </div>
                    <div class="modal-footer">
                        <%--<button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>--%>
                        <button type="button" id="saveSkillsButton" class="btn btn-success" >Προσθήκη</button>
                    </div>
                </div>

            </form>
        </div>
    </div>


    <div id="removeSkillsModal" class="modal fade" role="dialog">
        <div class="modal-dialog">

            <form method="POST" action="removeSkills" name="removeSkillsForm"  class="" id="removeSkillsForm">
                <!-- Modal content-->
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                        <h4 class="modal-title">Διαγραφή παθήσεων</h4>
                    </div>
                    <div class="modal-body">

                        <div id="removeskillsPanel">

                        </div>
                    </div>
                    <div class="modal-footer">
                        <%--<button type="button" class="btn btn-default" data-dismiss="modal">Άκυρο</button>--%>
                        <button type="submit" id="removeSkillsButton" class="btn btn-danger" >Διαγραφή</button>
                    </div>
                </div>

            </form>
        </div>
    </div>



</div>

<%@include file="generic/footer.jsp" %>
<script src="<c:url value='/static/js/advisedPerson.js' />"></script>
</body>
</html>