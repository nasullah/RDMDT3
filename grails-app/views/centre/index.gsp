
<%@ page import="rdmdt.Centre" %>
<!DOCTYPE html>
<html>

<head>
	<meta name="layout" content="main" />
	<h2><center>Centre</center></h2>
</head>

<body>

<section id="index-centre" class="first">

	<table class="table table-bordered margin-top-medium">
		<thead>
			<tr>
			
				<g:sortableColumn property="centreName" title="${message(code: 'centre.centreName.label', default: 'Centre Name')}" />
			
			</tr>
		</thead>
		<tbody>
		<g:each in="${centreInstanceList}" status="i" var="centreInstance">
			<tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
			
				<td><g:link action="show" id="${centreInstance.id}">${fieldValue(bean: centreInstance, field: "centreName")}</g:link></td>
			
			</tr>
		</g:each>
		</tbody>
	</table>
	<div class="pagination">
		<g:paginate total="${centreInstanceCount}" />
	</div>
</section>

</body>

</html>
