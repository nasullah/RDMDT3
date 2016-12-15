
<%@ page import="rdmdt.Department" %>
<!DOCTYPE html>
<html>

<head>
	<meta name="layout" content="main" />
	<h2><center>Department</center></h2>
</head>

<body>

<section id="index-department" class="first">

	<table class="table table-bordered margin-top-medium">
		<thead>
			<tr>
			
				<g:sortableColumn property="departmentName" title="${message(code: 'department.departmentName.label', default: 'Department Name')}" />
			
			</tr>
		</thead>
		<tbody>
		<g:each in="${departmentInstanceList}" status="i" var="departmentInstance">
			<tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
			
				<td><g:link action="show" id="${departmentInstance.id}">${fieldValue(bean: departmentInstance, field: "departmentName")}</g:link></td>
			
			</tr>
		</g:each>
		</tbody>
	</table>
	<div class="pagination">
		<g:paginate total="${departmentInstanceCount}" />
	</div>
</section>

</body>

</html>
