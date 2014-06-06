<%@ include file="../../common/header.jsp" %>
<c:if test="${saved == 'success'}">
    <p class="success">User Created Successfully</p>
</c:if>
<c:if test="${saved == 'error'}">
    <p class="error">User already exist</p>
</c:if>
<c:if test="${deleted == 'success'}">
    <p class="success">User Deleted Successfully</p>
</c:if>
<div class="link">
    <form:form modelAttribute="user" action="users" method="POST">
        <form:label path="userName">User Name</form:label><form:input path="userName"/>
        <button type="submit" id="search">Search User</button>
    </form:form>
</div>
<div class="link">
    <a href="/ui/users/create">
        <button type="submit">Create User</button>
    </a>

</div>
<div>
    <table>
        <tr>
            <th>View user</th>
            <th>User Name</th>
            <th>First Name</th>
            <th>Last Name</th>
            <th colspan="2">Actions</th>
            <th>API</th>
        </tr>
        <c:if test="${empty usersS}">
        <c:if test="${empty users}">
            <tr>
                <td colspan="4">No results found!</td>
            </tr>
        </c:if>
        <c:forEach var="current" items="${users}">
            <tr>
                <td><a href="users/${current.userName}">View</a></td>
                <td>${current.userName}</td>
                <td>${current.firstName}</td>
                <td>${current.lastName}</td>
                <td><a href="users/${current.userName}/edit">Edit</a></td>
                <td><a href="users/${current.userName}/delete">Delete</a></td>
                <td><a href="/api/users/${current.userName}">json</a></td>
            </tr>
        </c:forEach>
    </table>
    </c:if>
    </c test="${not empty usersS}">
    <c:forEach var="current" items="${usersS}">
        <tr>
            <td><a href="users/${current.userName}">View</a></td>
            <td>${current.userName}</td>
            <td>${current.firstName}</td>
            <td>${current.lastName}</td>
            <td><a href="users/${current.userName}/edit">Edit</a></td>
            <td><a href="users/${current.userName}/delete">Delete</a></td>
            <td><a href="/api/users/${current.userName}">json</a></td>
        </tr>
    </c:forEach>
    </table>

</div>
<%@ include file="../../common/footer.jsp" %>
