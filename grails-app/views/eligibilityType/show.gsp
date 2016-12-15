
<%@ page import="rdmdt.EligibilityType" %>
<!DOCTYPE html>
<html>

<head>
	<meta name="layout" content="main" />
	<h2><center>Show Eligibility Type</center></h2>
</head>

<body>

<section id="show-eligibilityType" class="first">

	<table class="table">
		<tbody>
		
			<tr class="prop">
				<td valign="top" class="name"><g:message code="eligibilityType.eligibilityTypeName.label" default="Eligibility Type Name" /></td>
				
				<td valign="top" class="value">${fieldValue(bean: eligibilityTypeInstance, field: "eligibilityTypeName")}</td>
				
			</tr>
		
		</tbody>
	</table>
</section>

</body>

</html>
