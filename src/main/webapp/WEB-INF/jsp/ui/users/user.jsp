<%@ include file="../../common/header.jsp" %>
<c:if test="${saved == 'success'}">
    <p class="success">User Updated Successfully</p>
</c:if>
</div>
<table>
    <tr>
        <th>User Name</th>
        <th>First Name</th>
        <th>Last Name</th>
        <th>Created on</th>
        <th colspan="2">Actions</th>
        <th>API</th>
    </tr>
    <c:if test="${empty user}">
        <tr>
            <td colspan="4">No results found!</td>
        </tr>
    </c:if>

    <tr>
        <td>${user.userName}</td>
        <td>${user.firstName}</td>
        <td>${user.lastName}</td>
        <td>${user.createdOn}</td>
        <td><a href="${user.userName}/edit">Edit</a></td>
        <td><a href="${user.userName}/delete">Delete</a></td>
        <td><a href="/api/users/${user.userName}">json</a></td>
    </tr>

</table>


<%@ include file="../../common/footer.jsp" %>
