<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>

    <%@include file="generic/meta.jsp" %>

    <title>Login page</title>

    <%@include file="generic/head.jsp" %>

    <link href="<c:url value='/static/css/liveconference.css' />" rel="stylesheet"></link>

</head>

<body>


<div class="generic-container" >

    <c:set var="loggedinuser" value="${loggedinuser}" scope="request"/>
    <c:import url="generic/navbar.jsp"/>

    <div class="container">
        <input type="hidden" id="csrfToken" value="${_csrf.token}"/>
        <input type="hidden" id="csrfHeader" value="${_csrf.headerName}"/>
        <input type="hidden"  id="jwt" name="jwt"  value="${jwt}"/>
        <input type="hidden" id="loggedinuserId" value="${loggedinuser.id}"/>
        <input type="hidden" id="conferenceId" value="${conference.id}"/>

        <div style="margin-top: 10px;">
            <div class="alert alert-info text-center" id="welocomeAlert">
                <strong>Καλωσόρισες! </strong> Όταν είσαι έτοιμος πάτα "Έναρξη συνεδρίας"
            </div>

            <div class="alert alert-warning text-center" id="watingAlert" style="display: none;">
                Αναμονή για τον συνομιλητη σου. . .
            </div>

            <div class="alert alert-success text-center" id="success-alert" id="conStartAlert" style="display: none;">
                <button type="button" class="close" data-dismiss="alert">x</button>
                          Η συνεδρία εχει ξεκινήσει!
            </div>
        </div>

        <div class="row" style="margin-left: 25px;">
            <div class="col-md-2  text-center" >
                <button class="btn btn-success " id="startConference" style='margin:10px; padding: 13px;'>
                    <img src='../../static/img/video2.png' width='20' style="margin-right: 5px; margin-bottom: 5px;"/>
                    Έναρξη συνεδρίας
                </button>
                <button class="btn btn-danger " id="terminateConference" style='margin:10px; padding: 13px; display: none;'>
                    <img src='../../static/img/video2.png' width='20' style="margin-right: 5px; margin-bottom: 5px;"/>
                    Τερματισμός συνεδρίας
                </button>
            </div>
        </div>

        <div class="row" style="margin-left: 25px;">

            <div class=" col-md-12">


                <div class="card pcard videoCard col-md-6">
                    <div class="card-body">
                        <h4 class="card-title ">

                            <c:choose>
                                <c:when test="${loggedinuser.id == conference.therapist.id}">
                                    ${conference.advisedPerson.first_name}
                                    ${conference.advisedPerson.last_name}
                                </c:when>
                                <c:otherwise>
                                    ${conference.therapist.first_name}
                                    ${conference.therapist.last_name}
                                </c:otherwise>
                            </c:choose>


                        </h4>
                        <hr>
                        <div  id="remote-media-div" >


                        </div>

                    </div>
                </div>

                <div class="card pcard videoCard col-md-6">
                    <div class="card-body">
                        <h4 class="card-title ">

                            <c:choose>
                                <c:when test="${loggedinuser.id == conference.advisedPerson.id}">
                                    ${conference.advisedPerson.first_name}
                                    ${conference.advisedPerson.last_name}
                                </c:when>
                                <c:otherwise>
                                    ${conference.therapist.first_name}
                                    ${conference.therapist.last_name}
                                </c:otherwise>
                            </c:choose>


                        </h4>
                        <hr>
                        <div id="local-media-ctr" >


                        </div>


                    </div>
                </div>



            </div>
        </div>



        <%--<div class="row">--%>

            <%--<div class="card pcard detailCard col-md-12">--%>
                <%--<div class="card-body">--%>
                    <%--<h4 class="card-title ">Στοιχεία συνεδρίας  </h4>--%>
                    <%--<hr>--%>
                    <%--<div  id="conferenceDetails" >--%>


                    <%--</div>--%>
                <%--</div>--%>
            <%--</div>--%>

        <%--</div>--%>

        <div class="row">
            <div class="card pcard detailCard col-md-12">
                <div class="card-body">
                    <h4 class="card-title ">Σημείωση ασθενή  </h4>
                    <hr>
                    <div  id="personDescr">

                        <c:choose>
                            <c:when test="${empty conference.userDesc}">
                             <div class="text-center">Ο ασθενής δεν εχει δώσει καμία σημείωση</div>
                            </c:when>
                            <c:otherwise>
                                ${conference.userDesc}
                            </c:otherwise>
                        </c:choose>

                    </div>
                </div>
            </div>
        </div>

        </div>

    </div>





</div>

<%@include file="generic/footer.jsp" %>
<%--<script src="//Respones.googleapis.com/Respones/libs/jquery/2.1.4/jquery.min.js"></script>--%>
<%--<script src="//media.twiliocdn.com/sdk/js/video/v1/twilio-video.min.js"></script>--%>
<%--<script src="index.js"></script>--%>
<%--<script src="<c:url value='/static/js/advisedPerson.js' />"></script>--%>.
<script src="<c:url value='/static/js/lib/twilio-video.min.js' />"></script>
<script src="<c:url value='/static/js/liveconference.js' />"></script>
</body>
</html>