
<%@ page import="rdmdt.AttachedEvidenceType" %>
<!DOCTYPE html>
<html>

<head>
	<meta name="layout" content="main" />
	<h2><center>Show Attached Evidence Type</center></h2>
</head>

<body>

<section id="show-attachedEvidenceType" class="first">

	<table class="table">
		<tbody>
		
			<tr class="prop">
				<td valign="top" class="name"><g:message code="attachedEvidenceType.attachedEvidenceTypeName.label" default="Attached Evidence Type Name" /></td>
				
				<td valign="top" class="value">${fieldValue(bean: attachedEvidenceTypeInstance, field: "attachedEvidenceTypeName")}</td>
				
			</tr>
		
		</tbody>
	</table>
</section>

</body>

</html>
