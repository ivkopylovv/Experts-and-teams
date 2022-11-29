<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Profile</title>
    <%@include file="../templates/meta.jsp" %>
    <script type="module" src="../js/page-script/profile.mjs"></script>
</head>
<body>
<%@include file="../templates/header.jsp" %>
<div class="body flex flex-col items-center justify-center">
    <h1 class="mx-auto text-lg">${user.getName()}</h1>
</div>
</body>
</html>
