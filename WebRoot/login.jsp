<%@ page language="java" import="java.util.*,pojo.*" pageEncoding="utf-8"  isELIgnored="false"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
User u = (User)session.getAttribute("user");
session.setAttribute("user",u);
%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
 <head>
  <meta charset="UTF-8">
  <title>欢迎您<%=u.getUgrade() %>级用户<%=u.getUname() %></title>
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/meyer-reset/2.0/reset.min.css">
  <link rel="shortcut icon" href="head_image/favicon.ico" type="image/x-icon" />  	
  <link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css'>
   <link rel="stylesheet" href="css/style.css">
</head>
<script>
	 function fmtDate(inputTime) {
        var date = new Date(inputTime);
        var y = date.getFullYear();
        var m = date.getMonth() + 1;
        m = m < 10 ? ('0' + m) : m;
        var d = date.getDate();
        d = d < 10 ? ('0' + d) : d;
        var h = date.getHours();
        h = h < 10 ? ('0' + h) : h;
        var minute = date.getMinutes();
        var second = date.getSeconds();
        minute = minute < 10 ? ('0' + minute) : minute;
        second = second < 10 ? ('0' + second) : second;
        return y + '-' + m + '-' + d + ' ' + h + ':' + minute + ':' + second;
    }
			var times;
		function findms(id,image,name,bir){
				clearInterval(times);
				$("#hhhh").find("li").remove();
				var userid = <%=u.getUserid()%>;
				var userids = id;
				$(".chat").show();
				$("#headimg").attr('src',image);
				$(".chat-with").html(name);
				$(".chat-num-messages").html("生日："+bir);
				$("input:hidden[name='ycid']").val(userids);
				$("input:hidden[name='img']").val(image);
				times = setInterval(answer, 2000);
			}
			function answer(){	
			    var img = $("input:hidden[name='img']").val();	
        		var userid = $("input:hidden[name='userid']").val();
    			var userids = $("input:hidden[name='ycid']").val();
    			var name = $(".chat-with").html();
    		$.ajax({
       			url:"LoginServlet?action=answer",
       			type:"post",
       			data:{"userid":userid,"userids":userids},
       			dataType:"json",
       			success:function(data){
       				if(data != "error"){
       					$.each(data,function(i,result){
								$("#hhhh").append("<li><div class='message-data'><img style='width: 50px;height: 50px;border-radius: 50%;top: calc(-100px/2);right: calc(50% - 50px);' src='"+img+"' alt='avatar' /><span class='message-data-name'>"+name+"</span><span class='message-data-time'>"+fmtDate(result['chatDate'])+"</span></div><div style='word-wrap: break-word; word-break: normal;' class='message my-message'>"+result['message']+"</div></li>");
						});
       					$("#hhhh").append($(".chatleft").clone());
       					}
       				}
       			});
        	}
		</script>
<body>
    <div class="container clearfix">
    <div class="people-list" id="people-list">
      <div class="search">
        <input type="text" placeholder="search" />
        <i class="fa fa-search"></i>
      </div>
      <ul class="list">
        <c:forEach items="${ulist}" var="user">
        <li class="clearfix">
          <img onclick="findms(${user.getUserid()},'${user.getUimage()}','${user.getUname()}','${user.getUbirthday()}')" style="width: 50px;height: 50px;border-radius: 50%;top: calc(-100px/2);left: calc(50% - 50px);" src="${user.getUimage() }" alt="avatar" />
          <div class="about">
            <div class="name"> ${user.getUname()}</div>
            <div class="status">
              <i class="fa fa-circle online"></i> 
            </div>
          </div>
        </li>
        </c:forEach>
      </ul>
    </div>
    
    <div style="display:none" class="chat">
      <div class="chat-header clearfix" >
      <input type="hidden" name="img" value="">
      <input type="hidden" name="ycid" value="">
      <input type="hidden" name="userid" value="<%=u.getUserid() %>">
        <img id="headimg" src=""  alt="avatar" />
        <div class="chat-about">
          <div class="chat-with"><%=u.getUname() %></div>
          <div class="chat-num-messages">生日：<%=u.getUbirthday() %></div>
        </div>
        <i class="fa fa-star"></i>
      </div> <!-- end chat-header -->
      
      <div class="chat-history">
        <ul id="hhhh">
        </ul>
      </div> <!-- end chat-history -->
      
      <div class="chat-message clearfix">
        <textarea name="message-to-send" id="message-to-send" placeholder ="Type your message" rows="3"></textarea>
                
        <i class="fa fa-file-o"></i> &nbsp;&nbsp;&nbsp;
        <i class="fa fa-file-image-o"></i>
        
        <button>Send</button>

      </div> <!-- end chat-message -->
      
    </div> <!-- end chat -->
  	 </div> <!-- end container -->
<script id="message-template" type="text/x-handlebars-template">
  <li class="clearfix">
    				<div class="message-data align-right">
      					<span class="message-data-time" >{{time}}</span> &nbsp; &nbsp;
      					<span class="message-data-name" ><%=u.getUname()%></span>         <img style="width: 50px;height: 50px;border-radius: 50%;top: calc(-100px/2);left: calc(50% - 50px);" src="<%=u.getUimage() %>" alt="avatar" />
    				</div>
    				<div style="word-wrap: break-word; word-break: normal;" class="message other-message float-right">
      					{{messageOutput}}
    				</div>
  				</li>
</script>
  <script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>
 <script src='http://cdnjs.cloudflare.com/ajax/libs/handlebars.js/3.0.0/handlebars.min.js'></script>
 <script src='http://cdnjs.cloudflare.com/ajax/libs/list.js/1.1.1/list.min.js'></script>
  <script  src="js/index.js"></script>
    </body>
</html>
