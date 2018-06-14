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
<h3>Welcome, ${user}!</h3>
<h2>${name}</h2>
<img src="${url}">
<br><br>
<form action = "users" method="post">
        <input type="submit" name="direction" value="Previous">
        <input type="submit" name="answer" value="Like">
        <input type="submit" name="answer" value="Don't like">
        <input type="submit" name="direction" value="Next">
</form>

</body>
</html>