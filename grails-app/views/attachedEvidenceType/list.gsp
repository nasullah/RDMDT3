
<%@ page import="rdmdt.AttachedEvidenceType" %>
<!DOCTYPE html>
<html>

<head>
	<meta name="layout" content="main" />
	<h2><center>Attached Evidence Type List</center></h2>
</head>

<body>

<section id="list-attachedEvidenceType" class="first">

	<table class="table table-bordered margin-top-medium">
		<thead>
			<tr>
			
				<g:sortableColumn property="attachedEvidenceTypeName" title="${message(code: 'attachedEvidenceType.attachedEvidenceTypeName.label', default: 'Attached Evidence Type Name')}" />
			
			</tr>
		</thead>
		<tbody>
		<g:each in="${attachedEvidenceTypeInstanceList}" status="i" var="attachedEvidenceTypeInstance">
			<tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
			
				<td><g:link action="show" id="${attachedEvidenceTypeInstance.id}">${fieldValue(bean: attachedEvidenceTypeInstance, field: "attachedEvidenceTypeName")}</g:link></td>
			
			</tr>
		</g:each>
		</tbody>
	</table>
	<div class="pagination">
		<g:paginate total="${attachedEvidenceTypeInstanceCount}" />
	</div>
</section>

</body>

</html>
