
<%@ page import="rdmdt.Ethnicity" %>
<!DOCTYPE html>
<html>

<head>
	<meta name="layout" content="main" />
	<h2><center>Ethnicity</center></h2>
</head>

<body>

<section id="index-ethnicity" class="first">

	<table class="table table-bordered margin-top-medium">
		<thead>
			<tr>
			
				<g:sortableColumn property="ethnicityName" title="${message(code: 'ethnicity.ethnicityName.label', default: 'Ethnicity Name')}" />
			
			</tr>
		</thead>
		<tbody>
		<g:each in="${ethnicityInstanceList}" status="i" var="ethnicityInstance">
			<tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
			
				<td><g:link action="show" id="${ethnicityInstance.id}">${fieldValue(bean: ethnicityInstance, field: "ethnicityName")}</g:link></td>
			
			</tr>
		</g:each>
		</tbody>
	</table>
	<div class="pagination">
		<g:paginate total="${ethnicityInstanceCount}" />
	</div>
</section>

</body>

</html>
