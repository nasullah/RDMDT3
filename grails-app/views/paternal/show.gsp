
<%@ page import="rdmdt.Paternal" %>
<!DOCTYPE html>
<html>

<head>
	<meta name="layout" content="main" />
	<g:set var="entityName" value="${message(code: 'paternal.label', default: 'Paternal')}" />
	<title><g:message code="default.show.label" args="[entityName]" /></title>
</head>

<body>

<section id="show-paternal" class="first">

	<table class="table">
		<tbody>
		
			<tr class="prop">
				<td valign="top" class="name"><g:message code="paternal.breastAndOrOvarianCancer.label" default="Breast And Or Ovarian Cancer" /></td>
				
				<td valign="top" class="value"><g:formatBoolean boolean="${paternalInstance?.breastAndOrOvarianCancer}" /></td>
				
			</tr>
		
			<tr class="prop">
				<td valign="top" class="name"><g:message code="paternal.colorectalCancer.label" default="Colorectal Cancer" /></td>
				
				<td valign="top" class="value"><g:formatBoolean boolean="${paternalInstance?.colorectalCancer}" /></td>
				
			</tr>
		
			<tr class="prop">
				<td valign="top" class="name"><g:message code="paternal.ischaemicHeartDiseaseOrStroke.label" default="Ischaemic Heart Disease Or Stroke" /></td>
				
				<td valign="top" class="value"><g:formatBoolean boolean="${paternalInstance?.ischaemicHeartDiseaseOrStroke}" /></td>
				
			</tr>
		
			<tr class="prop">
				<td valign="top" class="name"><g:message code="paternal.endocrineTumours.label" default="Endocrine Tumours" /></td>
				
				<td valign="top" class="value"><g:formatBoolean boolean="${paternalInstance?.endocrineTumours}" /></td>
				
			</tr>
		
			<tr class="prop">
				<td valign="top" class="name"><g:message code="paternal.referralRecord.label" default="Referral Record" /></td>
				
				<td valign="top" class="value"><g:link controller="referralRecord" action="show" id="${paternalInstance?.referralRecord?.id}">${paternalInstance?.referralRecord?.encodeAsHTML()}</g:link></td>
				
			</tr>
		
		</tbody>
	</table>
</section>

</body>

</html>
