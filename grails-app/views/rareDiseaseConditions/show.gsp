
<%@ page import="rdmdt.RareDiseaseConditions" %>
<!DOCTYPE html>
<html>

<head>
	<meta name="layout" content="main" />
	<g:set var="entityName" value="${message(code: 'rareDiseaseConditions.label', default: 'RareDiseaseConditions')}" />
	<title><g:message code="default.show.label" args="[entityName]" /></title>
</head>

<body>

<section id="show-rareDiseaseConditions" class="first">

	<table class="table">
		<tbody>
		
			<tr class="prop">
				<td valign="top" class="name"><g:message code="rareDiseaseConditions.originalId.label" default="Original Id" /></td>
				
				<td valign="top" class="value">${fieldValue(bean: rareDiseaseConditionsInstance, field: "originalId")}</td>
				
			</tr>
		
			<tr class="prop">
				<td valign="top" class="name"><g:message code="rareDiseaseConditions.diseaseGroup.label" default="Disease Group" /></td>
				
				<td valign="top" class="value">${fieldValue(bean: rareDiseaseConditionsInstance, field: "diseaseGroup")}</td>
				
			</tr>
		
			<tr class="prop">
				<td valign="top" class="name"><g:message code="rareDiseaseConditions.diseaseSubgroup.label" default="Disease Subgroup" /></td>
				
				<td valign="top" class="value">${fieldValue(bean: rareDiseaseConditionsInstance, field: "diseaseSubgroup")}</td>
				
			</tr>
		
			<tr class="prop">
				<td valign="top" class="name"><g:message code="rareDiseaseConditions.diseaseName.label" default="Disease Name" /></td>
				
				<td valign="top" class="value">${fieldValue(bean: rareDiseaseConditionsInstance, field: "diseaseName")}</td>
				
			</tr>
		
		</tbody>
	</table>
</section>

</body>

</html>
