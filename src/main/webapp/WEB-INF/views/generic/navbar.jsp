<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<div style="margin-bottom: 85px;">


<link href="<c:url value='/static/css/navbar.css' />" rel="stylesheet"></link>
<nav class="navbar navbar-findcond navbar-fixed-top" >
    <div class="navContainer">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand logo" href="/search">  <img src="../../../static/img/logo.png" width="50" height="50" class="media-photo">Ψυχολογία</a>
        </div>

        <div class="collapse navbar-collapse" id="navbar">


            <ul class="nav navbar-nav navbar-right" style="margin-top: 12px">
                <%--<li class="dropdown">--%>
                    <%--<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false"><i class="fa fa-fw fa-bell-o"></i> Bildirimler <span class="badge">0</span></a>--%>
                    <%--<ul class="dropdown-menu" role="menu">--%>
                        <%--<li><a href="#"><i class="fa fa-fw fa-tag"></i> <span class="badge">Music</span> sayfası <span class="badge">Video</span> sayfasında etiketlendi</a></li>--%>
                        <%--<li><a href="#"><i class="fa fa-fw fa-thumbs-o-up"></i> <span class="badge">Music</span> sayfasında iletiniz beğenildi</a></li>--%>
                        <%--<li><a href="#"><i class="fa fa-fw fa-thumbs-o-up"></i> <span class="badge">Video</span> sayfasında iletiniz beğenildi</a></li>--%>
                        <%--<li><a href="#"><i class="fa fa-fw fa-thumbs-o-up"></i> <span class="badge">Game</span> sayfasında iletiniz beğenildi</a></li>--%>
                    <%--</ul>--%>
                <%--</li>--%>
                <li class="active"><a class="underlined" href="/search">Αναζήτηση ψυχολόγου<span class="sr-only">(current)</span></a></li>



                    <c:choose>
                        <c:when test="${not empty loggedinuser}">
                            <li class=""><a class="underlined" href="/myConferences">Οι Συνεδρίες μου<span class="sr-only"></span></a></li>
                            <li class="active">
                                <a class="underlined" href="/chat" role="button" aria-haspopup="true" aria-expanded="false"> <img width="30" height="30" src="https://lh5.ggpht.com/SH0GFeQzs7w6RZoQ5PIxndvUPvoB1PB8eW_p28oeiRzw8P0MOThX_n_6H0iuJ1LKD9FT=w300"></img></a>

                            </li>
                            <li class="dropdown"  id="notificationButton">
                                <a class="underlined" href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false"> <img width="30" height="30" src="../../../static/img/notification.png"></img><span class="badge badge-notify" id="notifNum"></span></a>
                                <ul class="dropdown-menu notify-drop">
                                    <div class="notify-drop-title">
                                        <div class="row">
                                            <div class="col-md-6 col-sm-6 col-xs-6">Ειδοποιήσεις</div>
                                            <div class="col-md-6 col-sm-6 col-xs-6 text-right"><a href="" class="rIcon allRead" data-tooltip="tooltip" data-placement="bottom" title=""></a></div>
                                        </div>
                                    </div>
                                    <!-- end notify title -->
                                    <!-- notify content -->
                                    <div class="drop-content" id="notificationPanel">

                                    </div>
                                    <div class="notify-drop-footer text-center">
                                            <%--<a href=""><i class="fa fa-eye"></i> Tümünü Göster</a>--%>
                                    </div>
                                </ul>
                            </li>
                                <li class=""><a  class="underlined"  href="#"> <img width="30" height="30" src="https://vignette.wikia.nocookie.net/restaurant-story-2/images/6/6b/Coin-icon.png/revision/latest?cb=20150508155053 ">${loggedinuser.amount}€<span class="sr-only"></span></a></li>

                                <li class="dropdown">
                                    <a class="underlined"  href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false"> <img width="40" height="40" src="../../../static/img/user.jpg"><c:out value="${loggedinuser.last_name } ${loggedinuser.first_name }" />  <span class="caret"></span></a>
                                    <ul class="dropdown-menu" role="menu">
                                        <li><a class="underlined"href="/myprofile">Το προφίλ μου</a></li>
                                        <li><a class="underlined"href="/logout">Αποσύνδεση</a></li>

                                    </ul>
                                </li>
                        </c:when>
                        <c:otherwise>
                            <li class=""><a class="underlined" href="/login">Σύνδεση<span class="sr-only">(current)</span></a></li>
                            <li class=""><a class="underlined" href="/register">Εγγραφή<span class="sr-only">(current)</span></a></li>
                        </c:otherwise>
                    </c:choose>
            </ul>

        </div>
    </div>
</nav>
</div>
