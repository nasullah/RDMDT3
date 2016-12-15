
<%@ page import="rdmdt.ReferralStatus" %>
<!DOCTYPE html>
<html>

<head>
	<meta name="layout" content="main" />
	<h2><center>Show Application Status</center></h2>
</head>

<body>

<section id="show-referralStatus" class="first">

	<table class="table">
		<tbody>
		
			<tr class="prop">
				<td valign="top" class="name"><g:message code="referralStatus.referralStatusName.label" default="Referral Status Name" /></td>
				
				<td valign="top" class="value">${fieldValue(bean: referralStatusInstance, field: "referralStatusName")}</td>
				
			</tr>
		
		</tbody>
	</table>
</section>

</body>

</html>
