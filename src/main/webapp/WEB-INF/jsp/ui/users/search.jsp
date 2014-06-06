<%@ include file="../../common/header.jsp" %>
<div>
    <h1>TODO</h1>
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

</div>
<%@ include file="../../common/footer.jsp" %>
