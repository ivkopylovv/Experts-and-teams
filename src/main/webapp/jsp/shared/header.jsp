<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<header class="bg-emerald-500 py-4 text-white fixed top-0 left-0 right-0">
    <div class="container mx-auto xl:max-w-7xl flex items-center justify-between">
        <div class="text-lg font-medium">Experts and Teams</div>
        <nav class="flex items-center space-x-2 text-sm">
            <c:forEach items="${links}" var="link">
                <a class="router_link font-medium hover:underline" href="${link.getHref()}">${link.getName()}</a>
            </c:forEach>
        </nav>
    </div>
</header>