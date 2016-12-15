
<%@ page import="rdmdt.Maternal" %>
<!DOCTYPE html>
<html>

<head>
	<meta name="layout" content="main" />
	<g:set var="entityName" value="${message(code: 'maternal.label', default: 'Maternal')}" />
	<title><g:message code="default.show.label" args="[entityName]" /></title>
</head>

<body>

<section id="show-maternal" class="first">

	<table class="table">
		<tbody>
		
			<tr class="prop">
				<td valign="top" class="name"><g:message code="maternal.breastAndOrOvarianCancer.label" default="Breast And Or Ovarian Cancer" /></td>
				
				<td valign="top" class="value"><g:formatBoolean boolean="${maternalInstance?.breastAndOrOvarianCancer}" /></td>
				
			</tr>
		
			<tr class="prop">
				<td valign="top" class="name"><g:message code="maternal.colorectalCancer.label" default="Colorectal Cancer" /></td>
				
				<td valign="top" class="value"><g:formatBoolean boolean="${maternalInstance?.colorectalCancer}" /></td>
				
			</tr>
		
			<tr class="prop">
				<td valign="top" class="name"><g:message code="maternal.ischaemicHeartDiseaseOrStroke.label" default="Ischaemic Heart Disease Or Stroke" /></td>
				
				<td valign="top" class="value"><g:formatBoolean boolean="${maternalInstance?.ischaemicHeartDiseaseOrStroke}" /></td>
				
			</tr>
		
			<tr class="prop">
				<td valign="top" class="name"><g:message code="maternal.endocrineTumours.label" default="Endocrine Tumours" /></td>
				
				<td valign="top" class="value"><g:formatBoolean boolean="${maternalInstance?.endocrineTumours}" /></td>
				
			</tr>
		
			<tr class="prop">
				<td valign="top" class="name"><g:message code="maternal.referralRecord.label" default="Referral Record" /></td>
				
				<td valign="top" class="value"><g:link controller="referralRecord" action="show" id="${maternalInstance?.referralRecord?.id}">${maternalInstance?.referralRecord?.encodeAsHTML()}</g:link></td>
				
			</tr>
		
		</tbody>
	</table>
</section>

</body>

</html>
