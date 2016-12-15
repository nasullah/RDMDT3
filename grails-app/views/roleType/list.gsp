
<%@ page import="rdmdt.RoleType" %>
<!DOCTYPE html>
<html>

<head>
	<meta name="layout" content="main" />
	<h2><center>Role Type List</center></h2>
</head>

<body>

<section id="list-roleType" class="first">

	<table class="table table-bordered margin-top-medium">
		<thead>
			<tr>
			
				<g:sortableColumn property="roleTypeName" title="${message(code: 'roleType.roleTypeName.label', default: 'Role Type Name')}" />
			
			</tr>
		</thead>
		<tbody>
		<g:each in="${roleTypeInstanceList}" status="i" var="roleTypeInstance">
			<tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
			
				<td><g:link action="show" id="${roleTypeInstance.id}">${fieldValue(bean: roleTypeInstance, field: "roleTypeName")}</g:link></td>
			
			</tr>
		</g:each>
		</tbody>
	</table>
	<div class="pagination">
		<g:paginate total="${roleTypeInstanceCount}" />
	</div>
</section>

</body>

</html>
