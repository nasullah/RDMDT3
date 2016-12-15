
<%@ page import="rdmdt.Patient" %>
<!DOCTYPE html>
<html>

<head>
	<meta name="layout" content="main" />
	<g:set var="entityName" value="${message(code: 'patient.label', default: 'Patient')}" />
	<title><g:message code="default.list.label" args="[entityName]" /></title>
</head>

<body>

<section id="list-patient" class="first">

	<table class="table table-bordered margin-top-medium">
		<thead>
			<tr>
			
				<g:sortableColumn property="givenName" title="${message(code: 'patient.givenName.label', default: 'Given Name')}" />
			
				<g:sortableColumn property="familyName" title="${message(code: 'patient.familyName.label', default: 'Family Name')}" />
			
				<g:sortableColumn property="nhsNumber" title="${message(code: 'patient.nhsNumber.label', default: 'Nhs Number')}" />
			
				<g:sortableColumn property="mrn" title="${message(code: 'patient.mrn.label', default: 'Mrn')}" />
			
				<g:sortableColumn property="dateOfBirth" title="${message(code: 'patient.dateOfBirth.label', default: 'Date Of Birth')}" />
			
				<th><g:message code="patient.gender.label" default="Gender" /></th>
			
			</tr>
		</thead>
		<tbody>
		<g:each in="${patientInstanceList}" status="i" var="patientInstance">
			<tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
			
				<td><g:link action="show" id="${patientInstance.id}">${fieldValue(bean: patientInstance, field: "givenName")}</g:link></td>
			
				<td>${fieldValue(bean: patientInstance, field: "familyName")}</td>
			
				<td>${fieldValue(bean: patientInstance, field: "nhsNumber")}</td>
			
				<td>${fieldValue(bean: patientInstance, field: "mrn")}</td>
			
				<td><g:formatDate date="${patientInstance.dateOfBirth}" /></td>
			
				<td>${fieldValue(bean: patientInstance, field: "gender")}</td>
			
			</tr>
		</g:each>
		</tbody>
	</table>
	<div class="pagination">
		<g:paginate total="${patientInstanceCount}" />
	</div>
</section>

</body>

</html>
