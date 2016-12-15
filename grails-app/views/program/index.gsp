
<%@ page import="rdmdt.Program" %>
<!DOCTYPE html>
<html>

<head>
	<meta name="layout" content="main" />
	<h2><center>Program</center></h2>
</head>

<body>

<section id="index-program" class="first">

	<table class="table table-bordered margin-top-medium">
		<thead>
			<tr>
			
				<g:sortableColumn property="name" title="${message(code: 'program.name.label', default: 'Name')}" />

				<g:sortableColumn property="description" title="${message(code: 'program.description.label', default: 'Description')}" />
			
			</tr>
		</thead>
		<tbody>
		<g:each in="${programInstanceList}" status="i" var="programInstance">
			<tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
			
				<td><g:link action="show" id="${programInstance.id}">${fieldValue(bean: programInstance, field: "name")}</g:link></td>

				<td>${fieldValue(bean: programInstance, field: "description")}</td>
			
			</tr>
		</g:each>
		</tbody>
	</table>
	<div class="pagination">
		<g:paginate total="${programInstanceCount}" />
	</div>
</section>

</body>

</html>
