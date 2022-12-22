<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <%@include file="/jsp/shared/meta.jsp" %>
    <title>Dashboard</title>
</head>
<body>
<%@include file="/jsp/shared/header.jsp" %>
<div class="body flex flex-col items-center justify-center">
    <%@include file="/jsp/pages/admin-dashboard/admin-dashboard-table.jsp" %>
</div>
<%@include file="/jsp/pages/admin-dashboard/admin-dashboard-edit-user-modal.jsp" %>
<%@include file="/jsp/pages/admin-dashboard/admin-dashboard-confirm-delete-modal.jsp" %>
<%@include file="/jsp/templates/skll-input.jsp" %>
</body>
</html>
