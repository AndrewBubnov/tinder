<html>
<head>
    <title>Liked page</title>
    <style>
        input{
            width:98px;
            margin: 10px;
        }
    </style>
</head>
<body>

<#list liked as i>
<h2>${i.name}, (id = ${i.id})</h2>
<img src="${i.url}">
<a href="/messages/${i.url}">chat</a>

<#--<form action = "liked" method="post">
    <input type="submit" name="chat" value="Chat">
    <input type="hidden" name="userId" value="${i.id}">

</form>-->
</#list>
</body>
</html>