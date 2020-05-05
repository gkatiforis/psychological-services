<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>

    <%@include file="generic/meta.jsp" %>

    <title>Login page</title>

    <%@include file="generic/head.jsp" %>

    <link href="<c:url value='/static/css/expertProfile.css' />" rel="stylesheet"></link>

    <link rel="stylesheet" href="<c:url value='/static/css/lib/jquery.rateyo.min.css' />">
    <!-- Latest compiled and minified JavaScript -->
    <script src="<c:url value='/static/js/lib/jquery.rateyo.min.js' />"></script>
    <%--<link href="<c:url value='/static/css/app.css' />" rel="stylesheet"></link>--%>
    <%--<link rel="stylesheet" type="text/css" href="//cdnjs.cloudflare.com/Respones/libs/font-awesome/4.2.0/css/font-awesome.css" />--%>



    <%--<!-- CSS -->--%>
    <%--<link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Roboto:400,100,300,500">--%>
    <%--<link rel="stylesheet" href="<c:url value='/static/css/font-awesome.min.css' />">--%>
    <%--<link rel="stylesheet" href="<c:url value='/static/css/form-elements.css' />">--%>
    <%--<link rel="stylesheet" href="<c:url value='/static/css/login.css' />">--%>
    <%--<script src="<c:url value='/static/js/select2.full.min.js'/>"></script>--%>
    <%--<link href="<c:url value='/static/css/select2.min.css' />" rel="stylesheet"></link>--%>
    <%--<script src="<c:url value='/static/js/jquery-2.2.4.js' />"></script>--%>
    <%--<script src="<c:url value='/static/js/jquery.validate.js' />"></script>--%>

    <%--<script src="<c:url value='/static/js/register.js' />"></script>--%>


</head>

<body>


<div class="generic-container" >



    <c:set var="loggedinuser" value="${loggedinuser}" scope="request"/>
    <c:import url="generic/navbar.jsp"/>



    <div class="rela-block container" >

            <div class="rela-block profile-card">


                <c:if test="${loggedinuser.id == therapist.id}">
                    <div class="editButton" id="">

                    </div>
                </c:if>


                    <div class="profile-pic" id="profile_pic"></div>
                        <div class="rela-block profile-name-container">
                            <div class="rela-block user-name" id="user_name">${therapist.last_name} ${therapist.first_name}</div>
                    <div class="rela-block user-desc" id="user_spec">${therapist.specialisation}</div>
                </div>


                <div class="row rela-block profile-name-container">
                    <div class="row">
                        <div class="col-sm-4">

                            <div class="text-center">
                            0 κριτικές
                                <span class="rateYo" id="rateYo_therapist" style="margin-left: 50px;"> </span>
                            </div>

                        </div>
                        <div class="col-sm-4">

                            <c:if test="${loggedinuser.userProfiles.iterator().next().id == 1}">
                                <a href="/book-${therapist.id}" target="_blank">
                                    <button type="button" class="btn btn-success" style="margin-left:20px; margin-right:5px"> Κλείσε <br> ραντεβού</button>
                                </a>
                            </c:if>


                        </div>
                        <div class="col-sm-4">30€ /ώρα</div>
                    </div>
                </div>
                    <hr>
                <p class="card-text" id="user_description">${therapist.description}</p>
                <%--<a href="#" class="card-link ">more. . . </a>--%>


           </div>



        <div class="card pcard">
                <div class="card-body">
                    <h4 class="card-title">Βιογραφικό </h4>
                    <hr>
                    <%--<h6 class="card-subtitle mb-2 text-muted"></h6>--%>
                    <p class="card-text" id="user_bio">${therapist.bio}</p>
                    <%--<a href="#" class="card-link ">more. . . </a>--%>
                    <%--<a href="#" class="card-link">Another link</a>--%>
                </div>
            </div>

        <div class="card pcard">
            <div class="card-body">
                <h4 class="card-title ">Υπηρεσίες

                    <c:if test="${loggedinuser.id == therapist.id}">
                        <span id="addService" class="addDisButton"></span>
                    </c:if>


                </h4>
                <hr>
                <%--<h6 class="card-subtitle mb-2 text-muted"></h6>--%>
                <div class="card-text" id="servicePanel" style="padding-bottom: 30px;">

                    <%--<span class="removeDisButton"></span>--%>

                    <c:if test="${empty therapistServicies}">
                        <p class="text-center">Δεν έχετε προσθέσει υπηρεσίες.</p>
                    </c:if>
                            <div class="bs-example">
                                <div class="list-group" >
                                    <c:forEach items="${therapistServicies}" var="service">

                                            <div class="list-group-item" id="service${service.id}">
                                                <%--active--%>
                                                    <c:if test="${loggedinuser.id == therapist.id}">

                                                        <div style="padding-bottom: 30px;"> <span class="removeService" name="${service.id}"></span></div>


                                                    </c:if>

                                                <h3 class="list-group-item-heading">${service.conferenceCount} ${service.serviceType.title}
                                                    <c:if test="${loggedinuser.userProfiles.iterator().next().id == 1}">
                                                      <%--<button type="button" class="btn btn-success" style="margin-left:20px; margin-right:5px"> Αγορά</button>--%>
                                                    </c:if>
                                                </h3>
                                                    <h4>  Κόστος: ${service.amount}€

                                                    </h4>
                                                <p class="list-group-item-text">${service.descr}</p>
                                            </div>
                                    </c:forEach>
                                </div>
                            </div>
                </div>
                <%--<a href="#" class="card-link ">more. . . </a>--%>
                <%--<a href="#" class="card-link">Another link</a>--%>
            </div>
        </div>

        <div class="card pcard">
            <div class="card-body">
                <h4 class="card-title ">Εξειδικεύσεις

                    <c:if test="${loggedinuser.id == therapist.id}">
                        <span id="addSpec"  class="addDisButton"></span> <span id="removeSpec"  class="removeDisButton"></span>
                    </c:if>


                </h4>
                <hr>
                <%--<h6 class="card-subtitle mb-2 text-muted"></h6>--%>
                <p class="card-text" id="specPanel"></p>
                <%--<a href="#" class="card-link ">more. . . </a>--%>
                <%--<a href="#" class="card-link">Another link</a>--%>
            </div>
        </div>

        <div class="card pcard">
                <div class="card-body">
                    <h4 class="card-title ">Παθήσεις

                        <c:if test="${loggedinuser.id == therapist.id}">
                            <span id="addSkills"  class="addDisButton"></span> <span id="removeSkills"  class="removeDisButton"></span>
                        </c:if>
                    </h4>
                    <hr>
                    <%--<h6 class="card-subtitle mb-2 text-muted"></h6>--%>
                    <p class="card-text" id="skillsPanel"></p>
                    <%--<a href="#" class="card-link ">more. . . </a>--%>
                    <%--<a href="#" class="card-link">Another link</a>--%>
                </div>
            </div>

        <div class="card pcard" >
            <div class="card-body" id="hoursBody">
                <h4 class="card-title ">Ωράριο

                    <c:if test="${loggedinuser.id == therapist.id}">
                        <span class="editedWorkingHousButton"></span>
                    </c:if>

                </h4>
                <hr>

                <c:choose>
                    <c:when test="${empty therapistWorkingHours}">
                        <p class="text-center">Δεν έχετε ορίσει ωράριο.</p>
                    </c:when>
                    <c:otherwise>
                        <div style="margin-left: 5px;" class="row">

                                <%--<c:out value="${hours.working_time}"/>--%>


                            <div style="min-width: 90px;  padding-top: 15px;" class=" col-md-1 ">
                                <b>Δευτερα</b>

                                <c:forEach items="${workingHours}" var="hours">
                                    <c:if test="${hours.dayId == 1}">



                                        <c:set var="contains" value="false" />
                                        <c:forEach items="${therapistWorkingHours}" var="thours">
                                            <c:if test="${thours.id == hours.id}">
                                                <c:set var="contains" value="true" />
                                            </c:if>
                                        </c:forEach>

                                        <c:choose>
                                            <c:when test="${true == contains}">
                                                <div><c:out value="${hours.workingTime}"/></div>
                                            </c:when>

                                        </c:choose>

                                    </c:if>
                                </c:forEach>
                            </div>
                            <div style="min-width: 90px;  padding-top: 15px;" class=" col-md-1">
                                <b>Τρίτη</b>
                                <c:forEach items="${workingHours}" var="hours">
                                    <c:if test="${hours.dayId == 2}">


                                        <c:set var="contains" value="false" />
                                        <c:forEach items="${therapistWorkingHours}" var="thours">
                                            <c:if test="${thours.id == hours.id}">
                                                <c:set var="contains" value="true" />
                                            </c:if>
                                        </c:forEach>

                                        <c:choose>
                                            <c:when test="${true == contains}">
                                                <div><c:out value="${hours.workingTime}"/></div>
                                            </c:when>

                                        </c:choose>

                                    </c:if>
                                </c:forEach>
                            </div>

                            <div style="min-width: 90px;  padding-top: 15px;" class="col-md-1 ">
                                <b>Τετάρτη</b>
                                <c:forEach items="${workingHours}" var="hours">
                                    <c:if test="${hours.dayId == 3}">



                                        <c:set var="contains" value="false" />
                                        <c:forEach items="${therapistWorkingHours}" var="thours">
                                            <c:if test="${thours.id == hours.id}">
                                                <c:set var="contains" value="true" />
                                            </c:if>
                                        </c:forEach>

                                        <c:choose>
                                            <c:when test="${true == contains}">
                                                <div> <c:out value="${hours.workingTime}"/></div>
                                            </c:when>

                                        </c:choose>

                                    </c:if>
                                </c:forEach>
                            </div>
                            <div style="min-width: 90px;  padding-top: 15px;" class="col-md-1">
                                <b>Πέμπτη</b>
                                <c:forEach items="${workingHours}" var="hours">
                                    <c:if test="${hours.dayId == 4}">



                                        <c:set var="contains" value="false" />
                                        <c:forEach items="${therapistWorkingHours}" var="thours">
                                            <c:if test="${thours.id == hours.id}">
                                                <c:set var="contains" value="true" />
                                            </c:if>
                                        </c:forEach>

                                        <c:choose>
                                            <c:when test="${true == contains}">
                                                <div>  <c:out value="${hours.workingTime}"/></div>
                                            </c:when>

                                        </c:choose>

                                    </c:if>
                                </c:forEach>
                            </div>

                            <div style="min-width: 120px;  padding-top: 15px;" class="col-md-1 ">
                                <b>Παρασκευή</b>
                                <c:forEach items="${workingHours}" var="hours">
                                    <c:if test="${hours.dayId == 5}">



                                        <c:set var="contains" value="false" />
                                        <c:forEach items="${therapistWorkingHours}" var="thours">
                                            <c:if test="${thours.id == hours.id}">
                                                <c:set var="contains" value="true" />
                                            </c:if>
                                        </c:forEach>

                                        <c:choose>
                                            <c:when test="${true == contains}">
                                                <div> <c:out value="${hours.workingTime}"/></div>
                                            </c:when>

                                        </c:choose>

                                    </c:if>
                                </c:forEach>
                            </div>
                            <div style="min-width: 90px;  padding-top: 15px;;" class="col-md-1 ">
                                <b>Σάββατο</b>
                                <c:forEach items="${workingHours}" var="hours">
                                    <c:if test="${hours.dayId == 6}">



                                        <c:set var="contains" value="false" />
                                        <c:forEach items="${therapistWorkingHours}" var="thours">
                                            <c:if test="${thours.id == hours.id}">
                                                <c:set var="contains" value="true" />
                                            </c:if>
                                        </c:forEach>

                                        <c:choose>
                                            <c:when test="${true == contains}">
                                                <div> <c:out value="${hours.workingTime}"/></div>
                                            </c:when>

                                        </c:choose>

                                    </c:if>
                                </c:forEach>
                            </div>
                            <div style="min-width: 90px;  padding-top: 15px;" class=" col-md-1 ">
                                <b>Κυριακή</b>
                                <c:forEach items="${workingHours}" var="hours">
                                    <c:if test="${hours.dayId == 7}">



                                        <c:set var="contains" value="false" />
                                        <c:forEach items="${therapistWorkingHours}" var="thours">
                                            <c:if test="${thours.id == hours.id}">
                                                <c:set var="contains" value="true" />
                                            </c:if>
                                        </c:forEach>

                                        <c:choose>
                                            <c:when test="${true == contains}">
                                                <div> <c:out value="${hours.workingTime}"/></div>
                                            </c:when>
                                        </c:choose>

                                    </c:if>
                                </c:forEach>
                            </div>

                        </div>
                    </c:otherwise>
                </c:choose>

        </div>
            <div class="text-center">  <button id="showHoursBtn">Περισσότερα </button></div>

        </div>

        <div class="card pcard">
                <div class="card-body">
                    <h4 class="card-title">Βαθμολογίες</h4>
                    <hr>
                    <%--<h6 class="card-subtitle mb-2 text-muted"></h6>--%>
                    <p class="card-text"></p>
                    <%--<a href="#" class="card-link ">more. . . </a>--%>
                    <%--<a href="#" class="card-link">Another link</a>--%>
                </div>
            </div>

      </div>


    <div id="addServiceModal" class="modal fade" role="dialog">
        <div class="modal-dialog">

            <form method="POST" action="" name="addServiceForm"  class="" id="addServiceForm">

                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                        <h4 class="modal-title">Προσθήκη</h4>
                    </div>
                    <div class="modal-body">

                        <div>

                            <div class="row">


                                <div class="col-md-12">
                                    <b>Πόσες συνεδρίες περιλαμβάνει η υπηρεσία;</b>
                                    <div class="form-group">

                                        <label class="radio-inline"><input type="radio" name="numPanel" value="1"  > 1 συνεδρία</label>
                                        <label class="radio-inline"><input type="radio" name="numPanel" value="2" >Πολλαπλές συνεδρίες</label>

                                    </div>
                                </div>



                                <div id="addServicePanel" style="display:none;">

                                <div id="numConfPanel" style="display:none;">

                                        <div class="col-md-12">
                                            <div class="form-group">
                                                <b>  Αριθμός συνεδρίων:</b>
                                            </div>
                                        </div>

                                        <div class="col-md-12">
                                            <div class="form-group">
                                                <input type="text"  id="conferenceCount" name="conferenceCount" placeholder="" class=" form-control input-sm" />
                                            </div>
                                        </div>
                                        <div class="col-md-9 col-md-offset-2"></div>




                                </div>

                                    <div class="col-md-12">
                                        <div class="form-group">

                                            <b>Τίτλος</b><br>
                                            <select id="serviceType" name="serviceType" class="selectpicker">

                                                <c:forEach var="item" items="${serviceTypes}">


                                                        <option value="${item.id}">${item.title}</option>


                                                </c:forEach>
                                            </select>
                                            <%--<input type="text"  id="title" name="title" placeholder="πχ Οικογενειακή συνεδρία..." class="form-control input-sm" />--%>
                                        </div>
                                    </div>

                                <div class="col-md-12">
                                    <div class="form-group">
                                        <b>Περιγραφή</b>
                                        <textarea class="form-control" rows="2" id="descr" name="descr" placeholder=""  ></textarea>
                                    </div>
                                </div>


                                <div class="row" style="margin-left: 7px;">
                                <div class="col-md-12">
                                    <b>Κόστος υπηρεσίας</b>
                                </div>

                                    <div class="col-md-12">

                                        Επιλξτε το παρακάτω κουτάκι αν η υπηρεσία αποτελεί προσφορά

                                    </div>
                                    <div class="col-md-12">
                                        <div class="form-group">

                                            <div class="checkbox" style="margin-left: 7px;">

                                                <label><input type="checkbox"  id="isSpecialOffer" name="isSpecialOffer">  Είναι προσφορά</label>
                                            </div>
                                        </div>
                                    </div>
                                <div class="col-md-3">
                                    Τελικό κόστος
                                    <input type="text"  id="amount" name="amount" placeholder="πχ 30€" class="form-control  input-sm" />
                                </div>
                                <div class="col-md-4">
                                    Επιπλέον κόστος*<br>
                                    <div style="padding-bottom:15px;padding-top:15px;"><span id="plusCost" >0</span> €</div>
                                </div>
                                <div class="col-md-4">
                                    Κέρδος<br>
                                  <div style="padding-bottom:15px;padding-top:15px;"><span id="profit" >0</span> €</div>
                                </div>

                                </div>



                                </div>

                            </div>
                        </div>


                    </div>
                    <div class="modal-footer">
                        <%--<button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>--%>
                        <button type="submit" id="addServiceButton" class="btn btn-success" >Προσθήκη</button>
                    </div>
                </div>

            </form>
        </div>
    </div>

    <div id="editProfileModal" class="modal fade" role="dialog">
        <div class="modal-dialog">

            <form method="POST" action="saveExpertProfile" name="saveExpertProfileForm"  class="" id="saveExpertProfileForm">

            <!-- Modal content-->
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <h4 class="modal-title">Προφίλ</h4>
                </div>
                <div class="modal-body">



                    <h4>Προσωπικά στοιχεία</h4>
                    <div>

                        <div class="row">


                                    <div class="col-md-12">
                                        <div class="form-group">

                                        <input type="text"  id="first_name" name="first_name" placeholder="Όνομα" class="form-control input-sm" />
                                    </div>
                                </div>


                                    <div class="col-md-12">
                                        <div class="form-group">
                                        <input type="text"  id="last_name" name="last_name" placeholder="Επώνυμο"  class="form-control input-sm" />
                                    </div>

                                </div>

                                    <div class="col-md-12">
                                        <div class="form-group">
                                        <input type="text"  id="specialisation" name="specialisation" placeholder="specialisation"  class="form-control input-sm" />
                                    </div>
                                </div>


                                    <div class="col-md-12">
                                        <div class="form-group">
                                        <textarea class="form-control" rows="5" id="description" name="description" placeholder="Description"  ></textarea>
                                    </div>
                                </div>






                        </div>
                    </div>


                    <%--<h4>Επαγγελματική εμπειρία</h4>--%>
                    <%--<div class="row">--%>
                        <%--<div class="form-group col-md-12">--%>


                            <%--<div class="col-md-12">--%>
                                <%--<div class="form-group">--%>
                                    <%--<textarea class="form-control" rows="5" id="exp" name="exp" placeholder=""  ></textarea>--%>
                                <%--</div>--%>
                            <%--</div>--%>


                        <%--</div>--%>



                    <%--</div>--%>


                    <h4>Βιογραφικό</h4>
                    <div class="row">
                        <div class="form-group col-md-12">


                            <div class="col-md-12">
                                <div class="form-group">
                                    <textarea class="form-control" rows="5" id="bio" name="bio" placeholder=""  ></textarea>
                                </div>
                            </div>


                        </div>
                    </div>

                    <%--<h4>Παθήσεις</h4>--%>
                    <%--<div class="row">--%>
                        <%--<div class="form-group col-md-12">--%>


                            <%--<div class="col-md-12">--%>
                                <%--<div class="form-group">--%>
                                    <%--<textarea class="form-control" rows="5" id="bio" name="bio" placeholder=""  ></textarea>--%>
                                <%--</div>--%>
                            <%--</div>--%>


                        <%--</div>--%>
                    <%--</div>--%>

                </div>
                <div class="modal-footer">
                    <input type="hidden"  id="id" name="id"  value="${therapist.id}" class="form-control input-sm" />
                    <input type="hidden" id="csrfToken" value="${_csrf.token}"/>
                    <input type="hidden" id="csrfHeader" value="${_csrf.headerName}"/>

                    <button type="button" class="btn btn-default" data-dismiss="modal">Κλείσιμο</button>
                    <button type="submit" id="saveExpertProfileButton" class="btn btn-success" >Αποθήκευση</button>
                </div>
            </div>
            </form>
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


    <div id="editSpecModal" class="modal fade" role="dialog">
        <div class="modal-dialog">

            <form method="POST" action="saveSpec" name="saveSpecForm"  class="" id="saveSpecForm">
                <!-- Modal content-->
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                        <h4 class="modal-title">Προσθήκη</h4>
                    </div>
                    <div class="modal-body">

                        <label for="id_label_multiple_skills">
                            Επιλέξτε εξειδικεύσεις
                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            <select class="js-example-basic-multiple js-states form-control" id="id_label_multiple_spec" multiple="multiple" name="specialite"  style="width: 95%"></select>
                        </label>
                    </div>
                    <div class="modal-footer">
                        <%--<button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>--%>
                        <button type="submit" id="saveSpecButton" class="btn btn-success" >Προσθήκη</button>
                    </div>
                </div>

            </form>
        </div>
    </div>


    <div id="removeSpecModal" class="modal fade" role="dialog">
        <div class="modal-dialog">

            <form method="POST" action="removeSpec" name="removeSpecForm"  class="" id="removeSpecForm">
                <!-- Modal content-->
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                        <h4 class="modal-title">Διαγραφή εξειδικεύσεων</h4>
                    </div>
                    <div class="modal-body">

                        <div id="removeSpecPanel">

                        </div>
                    </div>
                    <div class="modal-footer">
                        <%--<button type="button" class="btn btn-default" data-dismiss="modal">Άκυρο</button>--%>
                        <button type="submit" id="removeSpecButton" class="btn btn-danger" >Διαγραφή</button>
                    </div>
                </div>

            </form>
        </div>
    </div>


    <div id="workingHoursModal" class="modal fade" role="dialog" >
        <div class="modal-dialog">

            <form method="POST" action="workingHours" name="workingHoursForm"  class="" id="workingHoursForm">
                <!-- Modal content-->
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                        <h4 class="modal-title">Επεξεργασία ωραρίου</h4>
                    </div>
                    <div class="modal-body">

                        <div class="row">

                            <%--<c:out value="${hours.working_time}"/>--%>

                            <div class="col-sm-6 col-md-6 col-lg-3">
                               <b>Δευτερα</b>

                                <c:forEach items="${workingHours}" var="hours">
                                    <c:if test="${hours.dayId == 1}">

                                        <div class="checkbox">

                                            <c:set var="contains" value="false" />
                                            <c:forEach items="${therapistWorkingHours}" var="thours">
                                                <c:if test="${thours.id == hours.id}">
                                                    <c:set var="contains" value="true" />
                                                </c:if>
                                            </c:forEach>

                                                <c:choose>
                                                    <c:when test="${true == contains}">
                                                        <label><input type="checkbox" value="${hours.id}" name="wh" checked>  <c:out value="${hours.workingTime}"/></label>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <label><input type="checkbox" value="${hours.id}" name="wh">  <c:out value="${hours.workingTime}"/></label>
                                                    </c:otherwise>
                                                </c:choose>
                                        </div>
                                    </c:if>
                                </c:forEach>
                            </div>

                            <div class="col-sm-6 col-md-6 col-lg-3">
                                <b>Τρίτη</b>
                                <c:forEach items="${workingHours}" var="hours">
                                    <c:if test="${hours.dayId == 2}">

                                        <div class="checkbox">

                                            <c:set var="contains" value="false" />
                                            <c:forEach items="${therapistWorkingHours}" var="thours">
                                                <c:if test="${thours.id == hours.id}">
                                                    <c:set var="contains" value="true" />
                                                </c:if>
                                            </c:forEach>

                                            <c:choose>
                                                <c:when test="${true == contains}">
                                                    <label><input type="checkbox" value="${hours.id}" name="wh" checked>  <c:out value="${hours.workingTime}"/></label>
                                                </c:when>
                                                <c:otherwise>
                                                    <label><input type="checkbox" value="${hours.id}" name="wh">  <c:out value="${hours.workingTime}"/></label>
                                                </c:otherwise>
                                            </c:choose>
                                        </div>
                                    </c:if>
                                </c:forEach>
                            </div>
                            <div class="col-sm-6 col-md-6 col-lg-3">
                                <b>Τετάρτη</b>
                                <c:forEach items="${workingHours}" var="hours">
                                    <c:if test="${hours.dayId == 3}">

                                        <div class="checkbox">

                                            <c:set var="contains" value="false" />
                                            <c:forEach items="${therapistWorkingHours}" var="thours">
                                                <c:if test="${thours.id == hours.id}">
                                                    <c:set var="contains" value="true" />
                                                </c:if>
                                            </c:forEach>

                                            <c:choose>
                                                <c:when test="${true == contains}">
                                                    <label><input type="checkbox" value="${hours.id}" name="wh" checked>  <c:out value="${hours.workingTime}"/></label>
                                                </c:when>
                                                <c:otherwise>
                                                    <label><input type="checkbox" value="${hours.id}" name="wh">  <c:out value="${hours.workingTime}"/></label>
                                                </c:otherwise>
                                            </c:choose>
                                        </div>
                                    </c:if>
                                </c:forEach>
                            </div>
                            <div class="col-sm-6 col-md-6 col-lg-3">
                                <b>Πέμπτη</b>
                                <c:forEach items="${workingHours}" var="hours">
                                    <c:if test="${hours.dayId == 4}">

                                        <div class="checkbox">

                                            <c:set var="contains" value="false" />
                                            <c:forEach items="${therapistWorkingHours}" var="thours">
                                                <c:if test="${thours.id == hours.id}">
                                                    <c:set var="contains" value="true" />
                                                </c:if>
                                            </c:forEach>

                                            <c:choose>
                                                <c:when test="${true == contains}">
                                                    <label><input type="checkbox" value="${hours.id}" name="wh" checked>  <c:out value="${hours.workingTime}"/></label>
                                                </c:when>
                                                <c:otherwise>
                                                    <label><input type="checkbox" value="${hours.id}" name="wh">  <c:out value="${hours.workingTime}"/></label>
                                                </c:otherwise>
                                            </c:choose>
                                        </div>
                                    </c:if>
                                </c:forEach>
                            </div>
                        </div>

                        <div class="row">

                            <div class="col-sm-6 col-md-6 col-lg-3">
                                <b>Παρασκευή</b>
                                <c:forEach items="${workingHours}" var="hours">
                                    <c:if test="${hours.dayId == 5}">

                                        <div class="checkbox">

                                            <c:set var="contains" value="false" />
                                            <c:forEach items="${therapistWorkingHours}" var="thours">
                                                <c:if test="${thours.id == hours.id}">
                                                    <c:set var="contains" value="true" />
                                                </c:if>
                                            </c:forEach>

                                            <c:choose>
                                                <c:when test="${true == contains}">
                                                    <label><input type="checkbox" value="${hours.id}" name="wh" checked>  <c:out value="${hours.workingTime}"/></label>
                                                </c:when>
                                                <c:otherwise>
                                                    <label><input type="checkbox" value="${hours.id}" name="wh">  <c:out value="${hours.workingTime}"/></label>
                                                </c:otherwise>
                                            </c:choose>
                                        </div>
                                    </c:if>
                                </c:forEach>
                            </div>
                            <div class="col-sm-6 col-md-6 col-lg-3">
                                <b>Σάββατο</b>
                                <c:forEach items="${workingHours}" var="hours">
                                    <c:if test="${hours.dayId == 6}">

                                        <div class="checkbox">

                                            <c:set var="contains" value="false" />
                                            <c:forEach items="${therapistWorkingHours}" var="thours">
                                                <c:if test="${thours.id == hours.id}">
                                                    <c:set var="contains" value="true" />
                                                </c:if>
                                            </c:forEach>

                                            <c:choose>
                                                <c:when test="${true == contains}">
                                                    <label><input type="checkbox" value="${hours.id}" name="wh" checked>  <c:out value="${hours.workingTime}"/></label>
                                                </c:when>
                                                <c:otherwise>
                                                    <label><input type="checkbox" value="${hours.id}" name="wh">  <c:out value="${hours.workingTime}"/></label>
                                                </c:otherwise>
                                            </c:choose>
                                        </div>
                                    </c:if>
                                </c:forEach>
                            </div>
                            <div class="col-sm-6 col-md-6 col-lg-3">
                               <b>Κυριακή</b>
                                <c:forEach items="${workingHours}" var="hours">
                                    <c:if test="${hours.dayId == 7}">

                                        <div class="checkbox">

                                            <c:set var="contains" value="false" />
                                            <c:forEach items="${therapistWorkingHours}" var="thours">
                                                <c:if test="${thours.id == hours.id}">
                                                    <c:set var="contains" value="true" />
                                                </c:if>
                                            </c:forEach>

                                            <c:choose>
                                                <c:when test="${true == contains}">
                                                    <label><input type="checkbox" value="${hours.id}" name="wh" checked>  <c:out value="${hours.workingTime}"/></label>
                                                </c:when>
                                                <c:otherwise>
                                                    <label><input type="checkbox" value="${hours.id}" name="wh">  <c:out value="${hours.workingTime}"/></label>
                                                </c:otherwise>
                                            </c:choose>
                                        </div>
                                    </c:if>
                                </c:forEach>
                            </div>
                        </div>




                    </div>
                    <div class="modal-footer">
                        <%--<button type="button" class="btn btn-default" data-dismiss="modal">Άκυρο</button>--%>
                        <button type="submit" id="saveworkingHoursButton" class="btn btn-success" >Αποθήκευση</button>
                    </div>
                </div>

            </form>
        </div>
    </div>




</div>


<%@include file="generic/footer.jsp" %>
<script src="<c:url value='/static/js/expertProfile.js' />"></script>
</body>
</html>