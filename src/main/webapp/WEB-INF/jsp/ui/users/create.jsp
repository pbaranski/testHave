<%@ include file="../../common/header.jsp" %>

<div class="link">
    <form:form modelAttribute="user" action="create" method="post">
        <form:label path="userName">User Name</form:label><form:input path="userName"/>
        <form:label path="firstName">First Name</form:label><form:input path="firstName"/>
        <form:label path="lastName">Last Name</form:label><form:input path="lastName"/>
        <form:label path="password">Password</form:label><form:password path="password"/>
        <button type="submit" id="save">Save User</button>
    </form:form>
</div>
<%@ include file="../../common/footer.jsp" %>
