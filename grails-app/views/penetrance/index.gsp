
<%@ page import="rdmdt.Penetrance" %>
<!DOCTYPE html>
<html>

<head>
	<meta name="layout" content="main" />
	<h2><center>Penetrance</center></h2>
</head>

<body>

<section id="index-penetrance" class="first">

	<table class="table table-bordered margin-top-medium">
		<thead>
			<tr>
			
				<g:sortableColumn property="penetranceName" title="${message(code: 'penetrance.penetranceName.label', default: 'Penetrance Name')}" />
			
			</tr>
		</thead>
		<tbody>
		<g:each in="${penetranceInstanceList}" status="i" var="penetranceInstance">
			<tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
			
				<td><g:link action="show" id="${penetranceInstance.id}">${fieldValue(bean: penetranceInstance, field: "penetranceName")}</g:link></td>
			
			</tr>
		</g:each>
		</tbody>
	</table>
	<div class="pagination">
		<g:paginate total="${penetranceInstanceCount}" />
	</div>
</section>

</body>

</html>
