<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:if test="${pushError != null}">
    <div class="fixed top-16 right-0 p-2 w-80">
        <jsp:include page="/jsp/shared/error-alert.jsp">
            <jsp:param name="id" value="pushError"/>
            <jsp:param name="message" value="${pushError}"/>
        </jsp:include>
    </div>
</c:if>
