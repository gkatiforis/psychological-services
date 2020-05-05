
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html lang="en" ng-app="angchat">
<head>
    <meta charset="utf-8">
    <title>Chatter | Chat Room</title>
    <meta http-equiv="Cache-Control" content="no-store, no-cache, must-revalidate, max-age=0">
    <link href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="<c:url value='/static/css/lib/form-elements.css' />">

    <link href="../../static/css/chat.css" rel="stylesheet">

</head>



<body>


<c:set var="loggedinuser" value="${loggedinuser}" scope="request"/>
<c:import url="generic/navbar.jsp"/>

<div class="main_section" >
    <div class="container">
        <div class="chat_container" style="margin-top: 10px;">
            <div class="col-sm-3 chat_sidebar">
                <div class="row">
                    <div id="custom-search-input">
                        <div class="input-group col-md-12">
                            <!--<input type="text" class="  search-query form-control" placeholder="Conversation" />-->
                            <!--<button class="btn btn-danger" type="button">-->
                                <!--<span class=" glyphicon glyphicon-search"></span>-->
                            <!--</button>-->
                            <b> Συνομιλίες</b>
                        </div>
                    </div>
                    <!--<div class="dropdown all_conversation">-->
                        <!--<button class="dropdown-toggle" type="button" id="dropdownMenu2" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">-->
                            <!--<i class="fa fa-weixin" aria-hidden="true"></i>-->
                            <!--All Conversations-->
                            <!--<span class="caret pull-right"></span>-->
                        <!--</button>-->
                        <!--<ul class="dropdown-menu" aria-labelledby="dropdownMenu2">-->
                            <!--<li><a href="#"> All Conversation </a>  <ul class="sub_menu_ list-unstyled">-->
                                <!--<li><a href="#"> All Conversation </a> </li>-->
                                <!--<li><a href="#">Another action</a></li>-->
                                <!--<li><a href="#">Something else here</a></li>-->
                                <!--<li><a href="#">Separated link</a></li>-->
                            <!--</ul>-->
                            <!--</li>-->
                            <!--<li><a href="#">Another action</a></li>-->
                            <!--<li><a href="#">Something else here</a></li>-->
                            <!--<li><a href="#">Separated link</a></li>-->
                        <!--</ul>-->
                    <!--</div>-->


                    <div class="member_list" ng-controller="ConversationCtrl">

                        <input type="hidden" id="selectedConv" value="${selectedConversationId}"  />
                        <ul class="list-unstyled">

                            <c:forEach items="${conversations}" var="conversation">
                            <li class="left clearfix convrs" data-ng-click="ShowId($event)" id="${conversation.id}">
                     <span class="chat-img pull-left">
                     <img src="../../static/img/user.jpg" alt="User Avatar" class="img-circle">
                     </span>







                                <div  class="chat-body clearfix "  >
                                    <div class="header_sec">
                                        <strong class="primary-font">${conversation.users.iterator().next().first_name}</strong>
                                        <%--<strong class="pull-right">09:45AM</strong>--%>
                                    </div>
                                    <div class="contact_sec">
                                        <%--<strong class="primary-font">(123) 123-456</strong> <span class="badge pull-right">3</span>--%>
                                    </div>
                                </div>



                            </li>
                            </c:forEach>
                        </ul>
                    </div></div>
            </div>
            <!--chat_sidebar-->


            <div class="col-sm-9 message_section">
                <div class="row">
                    <div class="new_message_head">
                        <%--<div class="pull-left"><button><i class="fa fa-plus-square-o" aria-hidden="true"></i> New Message</button></div><div class="pull-right"><div class="dropdown">--%>
                        <%--<button class="dropdown-toggle" type="button" id="dropdownMenu1" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">--%>
                            <%--<i class="fa fa-cogs" aria-hidden="true"></i>  Setting--%>
                            <%--<span class="caret"></span>--%>
                        <%--</button>--%>
                        <%--<ul class="dropdown-menu dropdown-menu-right" aria-labelledby="dropdownMenu1">--%>
                            <%--<li><a href="#">Action</a></li>--%>
                            <%--<li><a href="#">Profile</a></li>--%>
                            <%--<li><a href="#">Logout</a></li>--%>
                        <%--</ul>--%>
                    <%--</div></div>--%>
                    </div><!--new_message_head-->

                    <div class="chat_area" id="autoscroll" ng-controller="ChatCtrl">



                            <div class="col-md-12"   >
                                <ul  class="list-unstyled"  id="chats">
                            <li class="left clearfix admin_chat chat {{chat.direction}}" ng-repeat="chat in chats" ng-class="{'broadcast': chat.broadcast}">
                     <span class="chat-img1 {{chat.pull}}" style="margin-right: 15px;">
                     <img src="../../static/img/user.jpg" alt="User Avatar" class="img-circle">
                     </span>




                                <div class="chat-body1 clearfix" style=" background: white">
                                    <p class="messageItem" style=" padding-left: 15px;  padding: 20px; border-radius: 10px;">
                                       <b> {{chat.message}}</b> <br>
                                        <span class="chat_time pull-right" style="font-size: 10px">  {{chat.sendDate | date:"dd/MM HH:mm"}}  </span>
                                        <%--{{chat.from}}--%>
                                    </p>

                                </div>
                            </li>

                                </ul>
</div>


                    </div><!--chat_area-->
                    <div class="message_write">

                        <form role="form" id="chatForm" ng-controller="FormCtrl" ng-submit="sendMessage(chat)">
                            <div class="alert alert-info notification" id="joinedChat" ng-if="latestUser" ng-animate="{enter: 'fade', leave: 'fade'}">
                                {{latestUser}} has joined the chat!
                            </div>
                            <div class="form-group">
                                <!--<label for="to">Send To</label>-->
                                <%--<input type="text" id="conversationId" name="conversationId" ng-model="chat.conversationId" />--%>
                                <%--<input type="text" id="conversationId" name="conversationId"  ng-model="chat.conversationId" />--%>
                                <!--<input  class="form-control"  style="display: none;" type="text" id="to" name="to" value="george" ng-model="chat.to">-->
                                <!--<select class="form-control" id="to" name="to" ng-model="chat.to">-->
                                    <!--<option value="">Select Recipient</option>-->
                                    <!--<option value="all">All Users</option>-->
                                    <!--<option ng-repeat="user in users" value="{{user}}">{{capitalize(user)}}</option>-->
                                <!--</select>-->
                            </div>
                            <div class="form-group">

                                <textarea class="form-control focus-on-click" name="message" placeholder="Μήνυμα. . ." rows="3" ng-model="chat.message"></textarea>
                            </div>

                        <button type="submit" ng-focus="focus-on-click" name="send" class="btn btn-next " value="" >
                            <span class="glyphicon glyphicon-envelope" style="margin-right:5px;"></span>Αποστολή</button>
                        </form>



                        <%--<div class="row">--%>
                            <%--<div class="col-md-12">--%>
                                <%--<div class="pull-right" ng-controller="RandomFactCtrl">{{fact}}</div>--%>
                            <%--</div>--%>
                        <%--</div>--%>
                        <!--<textarea class="form-control" placeholder="type a message"></textarea>-->
                        <!--<div class="clearfix"></div>-->
                        <!--<div class="chat_bottom"><a href="#" class="pull-left upload_btn"><i class="fa fa-cloud-upload" aria-hidden="true"></i>-->
                            <!--Add Files</a>-->
                            <!--<a href="#" class="pull-right btn btn-success">-->
                                <!--Send</a></div>-->
                    </div>
                </div>
            </div> <!--message_section-->
        </div>
    </div>
</div>

<script src="../../static/js/lib/jquery-2.2.4.js"></script>

<script src="../../static/js/chat/angular.min.js"></script>
<script src="../../static/js/lib/scrollglue.js"></script>
<script src="../../static/js/chat/sockjs.js"></script>
<script src="../../static/js/chat/stomp.js"></script>

<script src="../../static/js/chat/app.js"></script>
<script src="../../static/js/chat/chatCtrl.js"></script>
<script src="../../static/js/chat/formCtrl.js"></script>


<%--<script src="../../static/js/chat/chat.js"></script>--%>

<%@include file="generic/footer.jsp" %>

</body>

</html>







<!--<html lang="en" ng-app="angchat">-->
<!--<head>-->
    <!--<meta charset="utf-8">-->
    <!--<title>Chatter | Chat Room</title>-->
    <!--<meta http-equiv="Cache-Control" content="no-store, no-cache, must-revalidate, max-age=0">-->
    <!--<link href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css" rel="stylesheet">-->
    <!--&lt;!&ndash;<link href="assets/common/portfolio.css" rel="stylesheet">&ndash;&gt;-->
<!--</head>-->

<!--<body>-->


    <!--<div class="container">-->



        <!--<header class="container">-->
            <!--<div id="heading" class="masthead">-->
                <!--<div class="pull-right">-->
                    <!--<a href="/logout" data-bind="click: logout" class="btn">Logout<i class="icon-off"></i></a>-->
                <!--</div>-->
                <!--<h2>Chatter <small>Online Chat Room</small></h2>-->
                <!--<div ng-controller="ErrorCtrl">-->
                    <!--<div class="alert alert-danger notification">-->
                        <!--Whoops! An error occurred: {{error}}-->
                    <!--</div>-->
                <!--</div>-->
            <!--</div>-->
        <!--</header>-->

        <!--<section class="main container">-->
            <!--<div class="row">-->
                <!--<div class="col-md-12">-->
                    <!--<p class="lead">Type in the box below to start a chat!</p>-->

                <!--</div>-->

            <!--</div>-->

            <!--<div class="row">-->
                <!--<div class="col-md-12">-->

                    <!--<form role="form" id="chatForm" ng-controller="FormCtrl" ng-submit="sendMessage(chat)">-->
                        <!--<div class="alert alert-info notification" id="joinedChat" ng-if="latestUser" ng-animate="{enter: 'fade', leave: 'fade'}">-->
                            <!--{{latestUser}} has joined the chat!-->
                        <!--</div>-->
                        <!--<div class="form-group">-->
                            <!--<label for="to">Send To</label>-->
                            <!--<select class="form-control" id="to" name="to" ng-model="chat.to">-->
                                <!--<option value="">Select Recipient</option>-->
                                <!--<option value="all">All Users</option>-->
                                <!--<option ng-repeat="user in users" value="{{user}}">{{capitalize(user)}}</option>-->
                            <!--</select>-->
                        <!--</div>-->
                        <!--<div class="form-group">-->
                            <!--<label for="message">Message</label>-->
                            <!--<textarea class="form-control focus-on-click" name="message" placeholder="Message..." rows="3" ng-model="chat.message"></textarea>-->
                        <!--</div>-->
                        <!--<input type="submit" ng-focus="focus-on-click" name="send" class="btn btn-primary" value="Send.." />-->
                    <!--</form>-->

                <!--</div>-->

            <!--</div>-->

            <!--<div class="row">-->
                <!--<div class="col-md-12" id="chats" ng-controller="ChatCtrl">-->
                    <!--<div ng-repeat="chat in chats | reverse" class="chat {{chat.direction}}" ng-class="{'broadcast': chat.broadcast}">-->
          		<!--<span class="from">-->
		        	<!--{{chat.from}}-->
		       		<!--&lt;!&ndash;<small ng-if="chat.broadcast">BROADCAST</small>&ndash;&gt;-->
		          <!--</span>-->
                        <!--<span class="message">{{chat.message}}</span>-->
                    <!--</div>-->
                <!--</div>-->


            <!--</div>-->

            <!--<div class="row">-->
                <!--<div class="col-md-12">-->
                    <!--<div class="pull-right" ng-controller="RandomFactCtrl">{{fact}}</div>-->
                <!--</div>-->
            <!--</div>-->



        <!--</section>-->





     <!--</div>-->

    <!--<script src="../../static/js/chat/sockjs.js"></script>-->
    <!--<script src="../../static/js/chat/stomp.js"></script>-->


    <!--<script src="../../static/js/chat/angular.min.js"></script>-->
    <!--<script src="../../static/js/chat/app.js"></script>-->
    <!--<script src="../../static/js/chat/chatCtrl.js"></script>-->
    <!--<script src="../../static/js/chat/formCtrl.js"></script>-->


<!--</body>-->
<!--</html>-->