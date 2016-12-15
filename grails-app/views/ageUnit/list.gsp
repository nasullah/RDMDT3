
<%@ page import="rdmdt.AgeUnit" %>
<!DOCTYPE html>
<html>

<head>
	<meta name="layout" content="main" />
	<h2><center>Age Unit List</center></h2>
</head>

<body>

<section id="list-ageUnit" class="first">

	<table class="table table-bordered margin-top-medium">
		<thead>
			<tr>
			
				<g:sortableColumn property="ageUnitName" title="${message(code: 'ageUnit.ageUnitName.label', default: 'Age Unit Name')}" />
			
			</tr>
		</thead>
		<tbody>
		<g:each in="${ageUnitInstanceList}" status="i" var="ageUnitInstance">
			<tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
			
				<td><g:link action="show" id="${ageUnitInstance.id}">${fieldValue(bean: ageUnitInstance, field: "ageUnitName")}</g:link></td>
			
			</tr>
		</g:each>
		</tbody>
	</table>
	<div class="pagination">
		<g:paginate total="${ageUnitInstanceCount}" />
	</div>
</section>

</body>

</html>
