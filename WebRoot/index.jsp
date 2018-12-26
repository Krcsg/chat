<!DOCTYPE html>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<html lang="en" class="limonkhan669@gmail.com">
<head>
  <meta content=charset="UTF-8">
  <title>小小星辰聊天网站</title>
  <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/5.0.0/normalize.min.css">
    <link rel="shortcut icon" href="head_image/favicon.ico" type="image/x-icon" />  	
    <link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css'>
    <link rel="stylesheet" href="css/style1.css">
    <link rel="stylesheet" href="js/jquery-3.2.1.js">
</head>
<body>
<html>
  <body>
     <canvas id="canvas"></canvas>
    <div id="login-box">
    <img class="profile"  src="head_image/18f880fe2e46c6933364384fd65822ea.jpg"  alt="User"/>
      <h2>Log in here</h2>
      <form name="myForm" action="LoginServlet?action=login2" method="post">
        <p>Name</p>
        <input type="text"  id="name" name="userName" placeholder="Enter Your Name" required/>
        <p>Password</p>
        <input type="password" id="psw" name="passWord" placeholder="*******" required/>
        <input type="button" id="sub"  value="Sign In"/><a href="#">Forget Password</a>
      </form>
    </div>
</html>
    <script  src="js/jquery-3.2.1.js"></script>
    <script>
    	$("#name").on('input',function(){  
   			var n = $("#name").val();
   			$.ajax({
   			url:"LoginServlet?action=image",
   			type:"post",
   			data:{"userName":n},
   			success:function(result){
   				if(result != null && result != ""){
   					$(".profile").attr("src",result); 
   				}
   			}
   			});
		}); 
		var times = 4;
		$("#sub").click(function(){  
   			var n = $("#name").val();
   			var p = $("#psw").val();
   			$.ajax({
   			url:"LoginServlet?action=login",
   			type:"post",
   			data:{
   				userName:n,
   				passWord:p
   				},
   			success:function(result){
   				re = parseInt(result);
   				if(re>0){
   					alert("用户名或者密码错误，您还有"+re+"次机会");
   				}else if(re<=0){
　　				$("#name").attr("disabled", true);
　　				$("#psw").attr("disabled", true);
					alert("帐号已经冻结，请联系管理员");
   				}else {
   					$("form[name='myForm']").submit();
   				}
   			}
   			});
		}); 
    </script>
</body>
</html>
