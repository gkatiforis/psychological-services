<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>

    <%@include file="generic/meta.jsp" %>

    <title>Login page</title>

    <%@include file="generic/head.jsp" %>

    <link href="<c:url value='/static/css/search.css' />" rel="stylesheet"></link>
    <%--<link href="<c:url value='/static/css/expertProfile.css' />" rel="stylesheet"></link>--%>

    <!-- Latest compiled and minified CSS -->

    <link rel="stylesheet" href="  https://cdnjs.cloudflare.com/ajax/libs/bootstrap-slider/10.0.0/css/bootstrap-slider.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/rateYo/2.3.2/jquery.rateyo.min.css">
    <!-- Latest compiled and minified JavaScript -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/rateYo/2.3.2/jquery.rateyo.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-slider/10.0.0/bootstrap-slider.min.js"></script>
</head>

<body>

<div class="generic-container">



    <c:set var="loggedinuser" value="${loggedinuser}" scope="request"/>
    <c:import url="generic/navbar.jsp"/>


        <div id="mainWrapper">

            <div class="row">

                <div class="col-sm-12 col-md-12 col-lg-12">

                    <form method="POST" action="" name="searchForm"  class="f1" id="searchForm">
                        <input type="hidden" id="csrfToken" value="${_csrf.token}"/>
                        <input type="hidden" id="csrfHeader" value="${_csrf.headerName}"/>
                        <%--<div class="row">--%>
                            <%--<div class="col-sm-3 col-md-4 col-lg-4" > </div>--%>
                            <%--<div class="col-sm-8 col-md-7 col-lg-4">--%>

                                <%--<div class="form-group">--%>
                                    <%--<h3 ></h3>--%>
                                <%--</div>--%>
                            <%--</div>--%>
                            <%--<div class="col-sm-1 col-md-1 col-lg-4"> </div>--%>
                        <%--</div>--%>

                        <div class="row" style="">

                            <div class="col-sm-1 col-md-1 col-lg-2">
                            </div>
                            <div class="col-sm-2 col-md-3 col-lg-2">
                            </div>
                            <div class="col-sm-8 col-md-7 col-lg-6">


                            </div>
                            <div class="col-sm-1 col-md-1 col-lg-4"> </div>
                        </div>


                    <div class="row">
                        <div class="col-sm-1 col-md-1 col-lg-2" > </div>
                        <div class="col-sm-2 col-md-3 col-lg-2 side-card">

                            <div class="side-menu">
                                <div class="side-title">
                                    Φίλτρα
                                </div>
                                <div class="side-body" >

                                    <div class="checkbox">
                                        <label><input type="checkbox" name="free" value="1">  Πρώτη συνεδρεία δωρεάν</label>
                                    </div>
                                    <div class="checkbox">
                                        <label><input type="checkbox" name="offers" value="1">Έχει προσφορές</label>
                                    </div>
                                </div>
                            </div>
                            <hr>
                            <div class="side-menu">
                                <div class="side-title">
                                    Κριτικές

                                </div>
                                <div class="side-body" >
                                    <div class="checkbox">
                                        <label><input type="checkbox" name="rating" value="1">  Από 4 αστέρια και άνω</label>
                                    </div>
                                </div>
                            </div>

                            <hr>
                            <div class="side-menu">
                                <div class="side-title">
                                    Τιμή/Ώρα
                                </div>
                                <div class="side-body" >
                                    <div class="btn-group">
                                        <div class="radio">
                                            <label><input type="radio" name="price" value="1">Κάτω από 30€</label>
                                        </div>
                                        <div class="radio">
                                            <label><input type="radio" name="price" value="1">Από 30€ και άνω</label>
                                        </div>

                                    </div>

                                </div>
                            </div>

                            <hr>


                            <div class="side-menu">
                                <div class="side-title">
                                    Φύλο

                                </div>
                                <div class="side-body" >
                                    <div class="btn-group">
                                        <button type="button" class="btn btn-default" name="male" value="1" >Άντρας</button>
                                        <button type="button" class="btn btn-default" name="female" value="1">Γυναίκα</button>
                                    </div>
                                </div>
                            </div>


                            <hr>
                            <div class="side-menu">
                                <div class="side-title">
                                    Έτοι εμπειρίας
                                </div>
                                <div class="side-body" >

                                    <input id="ex1" data-slider-id='ex1Slider' type="text" data-slider-min="0" data-slider-max="20" data-slider-step="1" data-slider-value="0" name="exp"/>
                                </div>
                            </div>

                            <div class="side-menu text-center">
                                <div class="side-body text-center" >

                                    <div class="f1-buttons text-center">
                                        <button type="submit" id="searchFilterButton" class="btn btn-next text-center" style="margin-top: 20px;">
                                            <span class="glyphicon glyphicon-refresh"></span> Ανανέωση
                                        </button>
                                    </div>
                                </div>
                            </div>







                            <%--exei h den exei prosforaes--%>

                            <%--exei h oxi try 1 sunderia--%>



                         </div>

                        <div class="col-sm-8 col-md-7 col-lg-6">

                            <div class="form-group">
                                <%--<hr3>Πατήστε για να επιλέξετε την πάθηση:</hr3>--%>


                                <div class="card pcardSearch">
                                    <div class="card-body">


                                        <%--<h6 class="card-subtitle mb-2 text-muted"></h6>--%>
                                        <%--<h3 style="padding-left: 10px;padding-top: 10px;">Αναζήτηση</h3>--%>
                                        <div class="panel-body"><select class="js-example-basic-multiple js-states form-control" id="id_label_multiple" multiple="multiple" name="disorder"  style="width: 100%"></select>
                                            <div class="f1-buttons">
                                                <button type="submit" id="searchButton" class="btn btn-next" style="margin-top: 20px;">
                                                    <span class="glyphicon glyphicon-search"></span> Αναζήτηση
                                                </button>
                                            </div>
                                        </div>


                                        <%--<a href="#" class="card-link ">more. . . </a>--%>
                                        <%--<a href="#" class="card-link">Another link</a>--%>
                                    </div>
                                </div>




                            </div>

                                <div class="panel-body">

                                    <div class="table-container">
                                        <table class="table table-filter" id="therapistList">
                                            <tbody>
                                            <%--<tr  data-status="pagado">--%>

                                                <%--<td>--%>
                                                    <%--<div class="media">--%>
                                                        <%--<a href="#" class="pull-left">--%>
                                                            <%--<img src="https://s3.amazonaws.com/uifaces/faces/twitter/fffabs/128.jpg" class="media-photo">--%>
                                                        <%--</a>--%>
                                                        <%--<div class="media-body">--%>

                                                            <%--<h4 class="title">--%>
                                                                <%--<span class="media-meta" id="name"> </span>--%>
                                                                <%--<span class="media-meta" id="smallDesc"> </span>--%>
                                                                <%--<span class="pull-right pagado" id="price"></span>--%>
                                                            <%--</h4>--%>
                                                            <%--<p class="summary" id="desc">--%>
                                                            <%--</p>--%>

                                                            <%--<hr>--%>


                                                            <%--<div class="row">--%>

                                                                <%--<div  class="col-sm-12 col-md-12 col-lg-12">--%>

                                                                    <%--<div class="col-sm-4 col-md-4 col-lg-4">--%>

                                                                        <%--<span>--%>

                                                                            <%--12 κριτικές 4.9--%>
                                                                         <%--<div id="rateYo"></div>--%>
                                                                         <%--</span>--%>
                                                                    <%--</div>--%>

                                                                    <%--<div class="col-sm-8 col-md-8 col-lg-8">--%>





                                                                        <%--<div style="padding-top: 20px;">--%>
                                                                             <%--<span class="tags tags--postTags tags--light"><a class="link" href="https://medium.com/tag/python?source=related" title="Go to Python" aria-label="Go to Python" data-action-source="related">Python</a>--%>
                                                                              <%--</span>--%>

                                                                        <%--</div>--%>
                                                                    <%--</div>--%>

                                                                <%--</div>--%>

                                                            <%--</div>--%>
                                                        <%--</div>--%>
                                                    <%--</div>--%>
                                                <%--</td>--%>
                                            <%--</tr>--%>

                                            </tbody>
                                        </table>
                                    </div>
                                </div>


                        </div>
                        <div class="col-sm-1 col-md-1 col-lg-2"> </div>
                    </div>


                    </form>


                </div>
            </div>


        </div>
</div>

<%@include file="generic/footer.jsp" %>
<script src="<c:url value='/static/js/search.js' />"></script>
</body>
</html>