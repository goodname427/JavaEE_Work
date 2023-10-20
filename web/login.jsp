<html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login</title>
    <script>
        let isLoginForm = true;
        function change() {
            console.log(1);
            let form = document.getElementById("loginOrRegister")
            form.action = isLoginForm ? "/JavaEE_Work_war_exploded/user/register" : "/JavaEE_Work_war_exploded/user/login";

            document.getElementById("btn_submit").value =  isLoginForm ? "注册" : "登录";
            document.getElementById("btn_change").value = isLoginForm ? "已有账号?现在登录" : "没有账号?注册一个"
            isLoginForm = !isLoginForm;
        }
    </script>
</head>
<body>
<form id = "loginOrRegister" action="/JavaEE_Work_war_exploded/user/login" method="post">
    用户名:<input type="text" name="username"><br>
    密 码:<input type="password" name="password"><br>
    <input id = "btn_submit" type="submit" value="登录">
    <input type="reset" value="重置">
    <input id = "btn_change" type="button" onclick="change()" value="没有账号?注册一个">
</form>
</body>
</html>
