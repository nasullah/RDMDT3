
<%@ page import="rdmdt.AttachedEvidence" %>
<!DOCTYPE html>
<html>

<head>
	<meta name="layout" content="main" />
	<h2><center>Show Attached Evidence</center></h2>
</head>

<body>

<section id="show-attachedEvidence" class="first">

	<table class="table">
		<tbody>
		
			<tr class="prop">
				<td valign="top" class="name"><g:message code="attachedEvidence.referralRecord.label" default="Application" /></td>
				
				<td valign="top" class="value"><g:link controller="referralRecord" action="show" id="${attachedEvidenceInstance?.referralRecord?.id}">${attachedEvidenceInstance?.referralRecord?.encodeAsHTML()}</g:link></td>
				
			</tr>
		
			<tr class="prop">
				<td valign="top" class="name"><g:message code="attachedEvidence.addedOn.label" default="Added On" /></td>
				
				<td valign="top" class="value"><g:formatDate format="yyyy-MM-dd" date="${attachedEvidenceInstance?.addedOn}" /></td>
				
			</tr>
		
			<tr class="prop">
				<td valign="top" class="name"><g:message code="attachedEvidence.type.label" default="Type" /></td>
				
				<td valign="top" class="value">${attachedEvidenceInstance?.type}</td>
				
			</tr>
		
			<tr class="prop">
				<td valign="top" class="name"><g:message code="attachedEvidence.content.label" default="Content" /></td>

				<td valign="top" class="value"><g:link action="download" id="${attachedEvidenceInstance.id}">${attachedEvidenceInstance.content}</g:link></td>
				
			</tr>
		
		</tbody>
	</table>
</section>

</body>

</html>
