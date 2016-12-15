
<%@ page import="rdmdt.Consanguinity" %>
<!DOCTYPE html>
<html>

<head>
	<meta name="layout" content="main" />
	<h2><center>Consanguinity</center></h2>
</head>

<body>

<section id="index-consanguinity" class="first">

	<table class="table table-bordered margin-top-medium">
		<thead>
			<tr>
			
				<g:sortableColumn property="consanguinityEvidence" title="${message(code: 'consanguinity.consanguinityEvidence.label', default: 'Consanguinity Evidence')}" />
			
			</tr>
		</thead>
		<tbody>
		<g:each in="${consanguinityInstanceList}" status="i" var="consanguinityInstance">
			<tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
			
				<td><g:link action="show" id="${consanguinityInstance.id}">${fieldValue(bean: consanguinityInstance, field: "consanguinityEvidence")}</g:link></td>
			
			</tr>
		</g:each>
		</tbody>
	</table>
	<div class="pagination">
		<g:paginate total="${consanguinityInstanceCount}" />
	</div>
</section>

</body>

</html>
