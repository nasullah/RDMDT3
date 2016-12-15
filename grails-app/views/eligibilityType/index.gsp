
<%@ page import="rdmdt.EligibilityType" %>
<!DOCTYPE html>
<html>

<head>
	<meta name="layout" content="main" />
	<h2><center>Eligibility Type</center></h2>
</head>

<body>

<section id="index-eligibilityType" class="first">

	<table class="table table-bordered margin-top-medium">
		<thead>
			<tr>
			
				<g:sortableColumn property="eligibilityTypeName" title="${message(code: 'eligibilityType.eligibilityTypeName.label', default: 'Eligibility Type Name')}" />
			
			</tr>
		</thead>
		<tbody>
		<g:each in="${eligibilityTypeInstanceList}" status="i" var="eligibilityTypeInstance">
			<tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
			
				<td><g:link action="show" id="${eligibilityTypeInstance.id}">${fieldValue(bean: eligibilityTypeInstance, field: "eligibilityTypeName")}</g:link></td>
			
			</tr>
		</g:each>
		</tbody>
	</table>
	<div class="pagination">
		<g:paginate total="${eligibilityTypeInstanceCount}" />
	</div>
</section>

</body>

</html>
