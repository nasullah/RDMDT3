
<%@ page import="rdmdt.AttachedEvidence" %>
<!DOCTYPE html>
<html>

<head>
	<meta name="layout" content="main" />
	<h2><center>Attached Evidence List</center></h2>
</head>

<body>

<section id="list-attachedEvidence" class="first">

	<table class="table table-bordered margin-top-medium">
		<thead>
			<tr>
			
				<th><g:message code="attachedEvidence.referralRecord.label" default="Application" /></th>
			
				<g:sortableColumn property="addedOn" title="${message(code: 'attachedEvidence.addedOn.label', default: 'Added On')}" />
			
				<th><g:message code="attachedEvidence.type.label" default="Type" /></th>
			
				<g:sortableColumn property="content" title="${message(code: 'attachedEvidence.content.label', default: 'Content')}" />
			
			</tr>
		</thead>
		<tbody>
		<g:each in="${attachedEvidenceInstanceList}" status="i" var="attachedEvidenceInstance">
			<tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
			
				<td><g:link action="show" id="${attachedEvidenceInstance.id}">${fieldValue(bean: attachedEvidenceInstance, field: "referralRecord")}</g:link></td>
			
				<td><g:formatDate date="${attachedEvidenceInstance.addedOn}" /></td>
			
				<td>${fieldValue(bean: attachedEvidenceInstance, field: "type")}</td>
			
				<td>${fieldValue(bean: attachedEvidenceInstance, field: "content")}</td>
			
			</tr>
		</g:each>
		</tbody>
	</table>
	<div class="pagination">
		<g:paginate total="${attachedEvidenceInstanceCount}" />
	</div>
</section>

</body>

</html>
