
<%@ page import="rdmdt.Consanguinity" %>
<!DOCTYPE html>
<html>

<head>
	<meta name="layout" content="main" />
	<h2><center>Show Consanguinity</center></h2>
</head>

<body>

<section id="show-consanguinity" class="first">

	<table class="table">
		<tbody>
		
			<tr class="prop">
				<td valign="top" class="name"><g:message code="consanguinity.consanguinityEvidence.label" default="Consanguinity Evidence" /></td>
				
				<td valign="top" class="value">${fieldValue(bean: consanguinityInstance, field: "consanguinityEvidence")}</td>
				
			</tr>
		
		</tbody>
	</table>
</section>

</body>

</html>
