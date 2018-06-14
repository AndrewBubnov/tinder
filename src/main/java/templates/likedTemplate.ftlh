<html>
<head>
    <title>Welcome page</title>
    <style>
        input{
            width:98px;
            margin: 10px;
        }
    </style>
</head>
<body>
<#list liked as i>
<h2>${i.name}</h2>
<img src="${i.url}">
</#list>

</body>
</html>