<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>

    <%@include file="generic/meta.jsp" %>

    <title>Login page</title>

    <%@include file="generic/head.jsp" %>



    <link href="<c:url value='/static/css/conferences.css' />" rel="stylesheet"></link>
    <link href="<c:url value='/static/css/lib/fullcalendar.css' />" rel="stylesheet"></link>

</head>

<body>

<div class="generic-container">


    <c:set var="loggedinuser" value="${loggedinuser}" scope="request"/>
    <c:import url="generic/navbar.jsp"/>




    <div id="mainWrapper">

        <div class="row">

            <div class="col-sm-12 col-md-12 col-lg-12">

                <form action="" method="">

                    <input type="hidden"  id="loggedinUserId" name="loggedinUserId"  value="${loggedinuser.id}" class="form-control input-sm" />
                    <input type="hidden" id="csrfToken" value="${_csrf.token}"/>
                    <input type="hidden" id="csrfHeader" value="${_csrf.headerName}"/>



                    <div class="container">
                        <div class="row" style="margin-bottom: 0px;">
                            <h2><img width="50" height="50" src="../../static/img/confere.png"> <span style="margin-left: 3px; margin-top: 3px;">Οι συνεδρίες μου </span></h2>
                            <hr>
                        </div>



                        <div class="row" style="margin-bottom: 10px;">
                            <div id="calendarPanel" class="col-md-7 col-md-offset-2" >
                                <div id='calendar' style="margin:30px;"></div>

                            </div>
                        </div>

                        <div class="row" style="margin-bottom: 50px;">
                            <ul class="nav nav-tabs">
                                <li id="confActiveButton" class="active"><a href="javascript:;">Επερχόμενες συνεδρίες</a></li>
                                <li id="confHistoryButton"><a href="javascript:;">Ολοκληρωμένες συνεδρίες</a></li>

                            </ul>
                        </div>



                        <div class="row">
                            <div class="col-md-12">
                                <div class="bs-example" style="margin-top: 20px;">
                                    <div class="list-group ordersList" >




                                    </div>
                                </div>
                            </div>

                            </div>
                        </div>

                        <%--<c:if test="${loggedinuser.userProfiles.iterator().next().id == 1}">--%>
                            <%--<div class="row" style="margin-bottom: 50px;">--%>
                                <%--<h2><img width="50" height="50" src="../../static/img/serviceIcon.png"> <span style="margin-left: 3px; margin-top: 3px;">Οι θεραπείες μου </span> </h2>--%>

                                <%--<c:if test="${empty userOrders}">--%>
                                    <%--<p class="text-center"> <a href="/search">Αναζητήστε εναν ψυχολόγο</a></p>--%>
                                <%--</c:if>--%>
                                <%--<div class="bs-example" style="margin-top: 20px;">--%>
                                    <%--<div class="list-group" >--%>
                                        <%--<c:forEach items="${userOrders}" var="order">--%>

                                            <%--<div class="list-group-item" id="order${order.id}">--%>
                                                    <%--&lt;%&ndash;active&ndash;%&gt;--%>


                                                <%--<h3 class="list-group-item-heading">--%>
                                                        <%--${order.therapist.last_name} ${order.therapist.first_name} | ${order.service.conferenceCount} ${order.service.serviceType.title}--%>
                                                <%--</h3>--%>

                                                <%--<c:if test="${order.service.conferenceCount > order.conferenceCountUsed}">--%>
                                                    <%--<h4 style="margin-top: 7px;"> διαθέσιμες συνεδρίες</h4>--%>


                                                    <%--<div class="progress">--%>
                                                        <%--<div class="progress-bar progress-bar-success" role="progressbar" aria-valuenow="${order.conferenceCountUsed}"--%>
                                                             <%--aria-valuemin="1" aria-valuemax="${order.service.conferenceCount}" style="width:${order.conferenceCountUsed / order.service.conferenceCount * 100}%">--%>
                                                              <%--Συνεδρία ${order.conferenceCountUsed}η από ${order.service.conferenceCount}--%>
                                                        <%--</div>--%>
                                                    <%--</div>--%>

                                                    <%--<div style="margin-top: 7px;">--%>

                                                        <%--<c:if test="${order.orderStatus.id == 1}">--%>
                                                        <%--<a href="/book-${order.therapist.id}" class="btn btn-next">--%>
                                                            <%--Κλείσε το επόμενο ραντεβού</a>--%>
                                                        <%--</c:if>--%>
                                                        <%--<a class="cancelOrderButton" name="${order.id}">Ακύρωση υπηρεσίας</a>--%>
                                                    <%--</div>--%>
                                                <%--</c:if>--%>

                                            <%--</div>--%>
                                        <%--</c:forEach>--%>
                                    <%--</div>--%>
                                <%--</div>--%>
                            <%--</div>--%>
                        <%--</c:if>--%>
                    </div>




                </form>


            </div>
        </div>


    </div>


    <div class="modal fade" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel" aria-hidden="true" id="confirmCompleteModal">
        <div class="modal-dialog modal-sm">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title">Επιβεβαίωση</h4>
                </div>
                <div class="modal-body">
                    Ορισμός συνεδρίας ως ολοκληρωμένη;
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default modal-btn-si" >Ναι</button>
                    <button type="button" class="btn btn-default modal-btn-no" >Οχι</button>
                </div>
            </div>
        </div>
    </div>


    <div class="modal fade" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel" aria-hidden="true" id="confirmCancelModal">
        <div class="modal-dialog modal-sm">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" >Επιβεβαίωση</h4>
                </div>
                <div class="modal-body">
                   Αναβολή συνεδρίας;
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default modal-btn-si">Ναι</button>
                    <button type="button" class="btn btn-default modal-btn-no" >Οχι</button>
                </div>
            </div>
        </div>
    </div>

    <div class="modal fade" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel" aria-hidden="true" id="confirmCancelOrderModal">
        <div class="modal-dialog modal-sm">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" >Επιβεβαίωση</h4>
                </div>
                <div class="modal-body">
                    Ακύρωση υπηρεσίας;
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default modal-btn-si">Ναι</button>
                    <button type="button" class="btn btn-default modal-btn-no" >Οχι</button>
                </div>
            </div>
        </div>
    </div>

</div>


<%@include file="generic/footer.jsp" %>
<script src="<c:url value='/static/js/lib/fullcalendar.js' />"></script>
<script src='<c:url value='/static/js/lib/el.js' />'></script>
        <script src="<c:url value='/static/js/conferences.js' />"></script>
</body>
</html>