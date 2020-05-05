<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>

    <%@include file="generic/meta.jsp" %>

    <title>Login page</title>

    <%@include file="generic/head.jsp" %>



    <link href="<c:url value='/static/css/book.css' />" rel="stylesheet"></link>

    <link href="<c:url value='/static/css/lib/fullcalendar.css' />" rel="stylesheet"></link>


    <%--<link href="<c:url value='/static/css/expertProfile.css' />" rel="stylesheet"></link>--%>

    <!-- Latest compiled and minified CSS -->

    <%--<link rel="stylesheet" href="  https://cdnjs.cloudflare.com/ajax/libs/bootstrap-slider/10.0.0/css/bootstrap-slider.min.css">--%>
    <%--<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/rateYo/2.3.2/jquery.rateyo.min.css">--%>
    <%--<!-- Latest compiled and minified JavaScript -->--%>
    <%--<script src="https://cdnjs.cloudflare.com/ajax/libs/rateYo/2.3.2/jquery.rateyo.min.js"></script>--%>
    <%--<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-slider/10.0.0/bootstrap-slider.min.js"></script>--%>
</head>

<body>

<div class="generic-container">



    <c:set var="loggedinuser" value="${loggedinuser}" scope="request"/>
    <c:import url="generic/navbar.jsp"/>


        <div id="mainWrapper">

                <!--   Big container   -->
                <div class="container">
                    <div class="row">
                        <div class="col-md-12">
                            <!-- Wizard container -->
                            <div class="wizard-container">
                                <div class="card wizard-card" data-color="red" id="wizard">
                                    <form action="" method="">
                                        <!--        You can switch " data-color="blue" "  with one of the next bright colors: "green", "orange", "red", "purple"             -->
                                        <input type="hidden"  id="therapistId" name="therapistId"  value="${therapist.id}" class="form-control input-sm" />
                                        <input type="hidden"  id="loggedinuserId" name="loggedinuserId"  value="${loggedinuser.id}" class="form-control input-sm" />
                                        <input type="hidden" id="csrfToken" value="${_csrf.token}"/>
                                        <input type="hidden" id="csrfHeader" value="${_csrf.headerName}"/>
                                        <%--<h5 class="text-center">--%>
                                            <%--Κλείσε ραντεβού με ${therapist.last_name} ${therapist.first_name}--%>
                                        <%--</h5>--%>
                                        <div class="wizard-navigation">
                                            <ul>

                                                <li class="" id="li1"><a  href="#service" data-toggle="tab">1. Επιλογή υπηρεσίας</a></li>
                                                <li class="disabled" id="li2"><a  href="#details" data-toggle="tab">2. Ημερομηνία και ώρα</a></li>
                                                <li class="disabled" id="li3"><a    href="#captain" data-toggle="tab">3. Συνεδρία </a></li>
                                                <%--<li class="disabled" id="li3"><a   href="#description" data-toggle="tab">3. Τα στοιχεία σου</a></li>--%>

                                                <%--<li><a href="#description" data-toggle="tab">Πληρωμή</a></li>--%>
                                            </ul>
                                        </div>

                                        <div class="tab-content">
                                            <div class="tab-pane" id="service">
                                                <div class="row">
                                                    <%--<div class="col-sm-12">--%>
                                                    <%--<h4 class="info-text"> Let's start with the basic details.</h4>--%>
                                                    <%--</div>--%>

                                                    <div class="row">

                                                        <%--<c:out value="${hours.working_time}"/>--%>

                                                            <c:set var = "display"  value = "display:none;"/>
                                                            <c:if test="${not empty userOrders}">
                                                                <c:set var = "display"  value = ""/>
                                                            </c:if>
                                                        <div id="servicePanel" class="col-md-12" style="<c:out value = "${display}"/> margin-left: 50px;" >


                                                                <h3>Επέλεξε υπηρεσία</h3>
                                                                <%--<p>Επέλεξε την υπηρεσία που σου ταιριάζει </p>--%>
                                                                <div class="bs-example">
                                                                    <div class="list-group" >
                                                                        <c:forEach items="${userOrders}" var="order">

                                                                            <div class="list-group-item" id="order${order.id}">
                                                                                    <%--active--%>


                                                                                <h3 class="list-group-item-heading">
                                                                                  <input class="orderRadio" type="radio" name="orderRadio" id="${order.id}"> ${order.service.conferenceCount} ${order.service.serviceType.title}
                                                                                </h3>

                                                                                        <c:if test="${order.service.conferenceCount > 1}">
                                                                                            <h5>
                                                                                             ${order.conferenceCountUsed+1}η  συνεδρία
                                                                                            </h5>

                                                                                        </c:if>

                                                                            </div>
                                                                        </c:forEach>
                                                                    </div>
                                                                </div>

                                                            <div id="chooseOrderButton" style="display: none;" >
                                                                <input type='button'   class='btn btn-next btn-fill btn-success btn-wd' name='next' value='Επόμενο' />
                                                                <%--<button type="button" id="chooseOrderButton" class="btn btn-success" style="display:none; margin-left:20px; margin-right:5px"> Συνέχεια</button>--%>
                                                            </div>
                                                            <div><a id="showServicies" href="#">Άλλες υπηρεσίες από ${therapist.last_name} ${therapist.first_name};</a></div>
                                                            </div>


                                                            <c:set var = "display"  value = "display:none;"/>
                                                            <c:if test="${empty userOrders}">
                                                                <c:set var = "display"  value = ""/>
                                                            </c:if>
                                                            <div id="buyServicePanel" class="col-md-12" style="<c:out value = "${display}"/> margin-left: 50px;" >


                                                                <h3>Διαθέσιμες υπηρεσίες από ${therapist.last_name} ${therapist.first_name}</h3>
                                                                <p>Επέλεξε την υπηρεσία που σου ταιριάζει </p>
                                                                <div class="bs-example">
                                                                    <div class="list-group" >
                                                                        <c:forEach items="${therapistServicies}" var="service">

                                                                            <div class="list-group-item" id="service${service.id}">

                                                                                <h3 class="list-group-item-heading">${service.conferenceCount} ${service.serviceType.title}
                                                                                    <c:if test="${loggedinuser.userProfiles.iterator().next().id == 1}">
                                                                                        <button type="button" class="btn btn-success buyService" name="${service.id}" style="margin-left:20px; margin-right:5px"> Αγορά</button>
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

                                                    </div>

                                                </div>
                                            </div>
                                            <div class="tab-pane" id="details">
                                                <div class="row">
                                                    <%--<div class="col-sm-12">--%>
                                                        <%--<h4 class="info-text"> Let's start with the basic details.</h4>--%>
                                                    <%--</div>--%>

                                                    <div class="row">

                                                        <%--<c:out value="${hours.working_time}"/>--%>

                                                        <div id="calendarPanel" class="col-md-8 col-md-offset-2" >
                                                             <div id='calendar' style="margin:30px;"></div>

                                                        </div>
                                                            <div class="col-md-4" id="hoursPanel" style="display:none; visibility: hidden;">
                                                                <div  style="margin-top: 40px; ">
                                                                   <b> Διαθέσιμες ώρες για <span id="dateOutput"></span> </b>
                                                                </div>

                                                                <div id="freeHoursPanel">
                                                                    <%--<div class='radio'>--%>
                                                                        <%--<label><input type='radio' name='optradio'>vxcvc</label>--%>
                                                                    <%--</div>--%>
                                                                </div>

                                                                <div id="panel1Next" style="display: none;" >

                                                                    <input type='button'  class='btn btn-next btn-fill btn-danger btn-wd' name='next' value='Επόμενο' />

                                                                </div>

                                                            </div>
                                                    </div>

                                                </div>
                                            </div>
                                            <div class="tab-pane" id="captain">


                                                <div class="row">
                                                    <div class="col-sm-12">

                                                        <div class="col-sm-5">

                                                            <h4 class="info-text">Επιλέξτε τον τύπο κλήσης </h4>


                                                            <div id="1" class="choice conferenceType" data-toggle="wizard-radio" rel="tooltip" title="Βίντεο κλήση">

                                                                <input type="radio" name="job" value="Design">
                                                                <div class="icon">
                                                                    <img style="padding-top: 10px;" width="90" height="90" src="../../static/img/video.png"/>
                                                                </div>
                                                                <h6><b>Βίντεο κλήση</b></h6>
                                                            </div>
                                                        <%--</div>--%>
                                                        <%--<div class="col-sm-4">--%>
                                                            <div id="2" class="choice conferenceType" data-toggle="wizard-radio" rel="tooltip" title="Φωνητική κλήση">
                                                                <input type="radio" name="job" value="Code">
                                                                <div class="icon">

                                                                    <img width="110" height="110" src="../../static/img/voice.png"/>
                                                                </div>
                                                                <h6><b>Φωνητική κλήση</b></h6>
                                                            </div>
                                                        </div>
                                                        <div class="col-sm-7">
                                                            <div class="form-group">

                                                                    <h4 class="">Περιγράψτε συνοπτικά το πρόβλημα σας (προαιρετικό)</h4>



                                                                <textarea class="form-controll" style="width: 100%;height: 30%;" rows="5" id="description" name="description" placeholder="Description"  ></textarea>
                                                                <div class="pull-right">
                                                                <input type='button' class='btn btn-previous btn-fill btn-default btn-wd' name='previous' value='Προηγούμενο' />

                                                                    <input type='button' id="panel2Next" style="" disabled class='btn  btn-fill btn-danger btn-wd' name='next' value='Ολοκλήρωση' />

                                                                </div>

                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="tab-pane">
                                                <%--<div class="row">--%>
                                                    <%--<h4 class="info-text"> Drop us a small description.</h4>--%>
                                                    <%--<div class="col-sm-6 col-sm-offset-1">--%>
                                                        <%--<div class="form-group">--%>
                                                            <%--<label>Room description</label>--%>
                                                            <%--<textarea class="form-control" placeholder="" rows="6"></textarea>--%>
                                                        <%--</div>--%>
                                                    <%--</div>--%>
                                                    <%--<div class="col-sm-4">--%>
                                                        <%--<div class="form-group">--%>
                                                            <%--<label class="control-label">Example</label>--%>
                                                            <%--<p class="description">"The room really nice name is recognized as being a really awesome room. We use it every sunday when we go fishing and we catch a lot. It has some kind of magic shield around it."</p>--%>
                                                        <%--</div>--%>
                                                    <%--</div>--%>
                                                <%--</div>--%>
                                            </div>
                                        </div>
                                        <%--<div class="wizard-footer">--%>
                                            <%--<div class="pull-right">--%>
                                                <%--&lt;%&ndash;<input type='button' class='btn btn-next btn-fill btn-danger btn-wd' name='next' value='Επόμενο' />&ndash;%&gt;--%>
                                                <%--&lt;%&ndash;<input type='button' class='btn btn-finish btn-fill btn-danger btn-wd' name='finish' value='Finish' />&ndash;%&gt;--%>
                                            <%--</div>--%>
                                            <%--<div class="pull-left">--%>
                                                <%--<input type='button' class='btn btn-previous btn-fill btn-default btn-wd' name='previous' value='Προηγούμενο' />--%>

                                                <%--<div class="footer-checkbox">--%>
                                                    <%--<div class="col-sm-12">--%>

                                                    <%--</div>--%>
                                                <%--</div>--%>
                                            <%--</div>--%>
                                            <%--<div class="clearfix"></div>--%>
                                        <%--</div>--%>
                                    </form>
                                </div>
                            </div> <!-- wizard container -->
                        </div>
                    </div> <!-- row -->
                </div> <!--  big container -->



</div>

<%@include file="generic/footer.jsp" %>
        <script src="<c:url value='/static/js/lib/wizard.js' />"></script>
        <script src="<c:url value='/static/js/lib/fullcalendar.js' />"></script>
        <script src='<c:url value='/static/js/lib/el.js' />'></script>
        <script src="<c:url value='/static/js/book.js' />"></script>
</body>
</html>