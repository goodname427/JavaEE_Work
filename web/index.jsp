<html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login</title>
    <script>
        function onChange() {
            let url = "/JavaEE_Work_war_exploded/tool/operate?";
            url += 'op1=' + document.getElementById('op1').value;
            url += '&op2=' + document.getElementById('op2').value;
            url += '&operator=%2B'
            let xhr = new XMLHttpRequest();  //这里没有考虑IE浏览器，如果需要择if判断加
            xhr.open('GET', url, true);
            xhr.send();//这里要是没有参数传，就写null
            xhr.onreadystatechange = function () {
                if (xhr.status === 200 && xhr.readyState === 4) {
                    let info = xhr.responseText;
                    let o = eval("(" + info + ")");
                    console.log(o);
                    document.getElementById('result').innerText = o['result'];
                    document.getElementById('msg').innerText = o['msg'];
                }
            }
        }
    </script>
</head>
<body>
<input id="op1" type="text" size="10px" onchange="onChange()" value="1"/>
<label>+</label>
<input id="op2" type="text" size="10px" onchange="onChange()" value="1"/>
<label>=</label>
<label id="result">2</label>
<br>
<label id="msg"></label>
</body>
</html>
