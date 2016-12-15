
<%@ page import="rdmdt.Patient" %>
<!DOCTYPE html>
<html>

<head>
	<meta name="layout" content="main" />
	<g:set var="entityName" value="${message(code: 'patient.label', default: 'Patient')}" />
	<title><g:message code="default.show.label" args="[entityName]" /></title>
</head>

<body>

<section id="show-patient" class="first">

	<table class="table">
		<tbody>
		
			<tr class="prop">
				<td valign="top" class="name"><g:message code="patient.givenName.label" default="Given Name" /></td>
				
				<td valign="top" class="value">${fieldValue(bean: patientInstance, field: "givenName")}</td>
				
			</tr>
		
			<tr class="prop">
				<td valign="top" class="name"><g:message code="patient.familyName.label" default="Family Name" /></td>
				
				<td valign="top" class="value">${fieldValue(bean: patientInstance, field: "familyName")}</td>
				
			</tr>
		
			<tr class="prop">
				<td valign="top" class="name"><g:message code="patient.nhsNumber.label" default="Nhs Number" /></td>
				
				<td valign="top" class="value">${fieldValue(bean: patientInstance, field: "nhsNumber")}</td>
				
			</tr>
		
			<tr class="prop">
				<td valign="top" class="name"><g:message code="patient.mrn.label" default="Mrn" /></td>
				
				<td valign="top" class="value">${fieldValue(bean: patientInstance, field: "mrn")}</td>
				
			</tr>
		
			<tr class="prop">
				<td valign="top" class="name"><g:message code="patient.dateOfBirth.label" default="Date Of Birth" /></td>
				
				<td valign="top" class="value"><g:formatDate date="${patientInstance?.dateOfBirth}" /></td>
				
			</tr>
		
			<tr class="prop">
				<td valign="top" class="name"><g:message code="patient.gender.label" default="Gender" /></td>
				
				<td valign="top" class="value"><g:link controller="gender" action="show" id="${patientInstance?.gender?.id}">${patientInstance?.gender?.encodeAsHTML()}</g:link></td>
				
			</tr>
		
			<tr class="prop">
				<td valign="top" class="name"><g:message code="patient.ethnicity.label" default="Ethnicity" /></td>
				
				<td valign="top" class="value"><g:link controller="ethnicity" action="show" id="${patientInstance?.ethnicity?.id}">${patientInstance?.ethnicity?.encodeAsHTML()}</g:link></td>
				
			</tr>
		
			<tr class="prop">
				<td valign="top" class="name"><g:message code="patient.otherEthnicity.label" default="Other Ethnicity" /></td>
				
				<td valign="top" class="value">${fieldValue(bean: patientInstance, field: "otherEthnicity")}</td>
				
			</tr>
		
			<tr class="prop">
				<td valign="top" class="name"><g:message code="patient.availableForOAR.label" default="Available For OAR" /></td>
				
				<td valign="top" class="value"><g:formatBoolean boolean="${patientInstance?.availableForOAR}" /></td>
				
			</tr>
		
			<tr class="prop">
				<td valign="top" class="name"><g:message code="patient.isProband.label" default="Is Proband" /></td>
				
				<td valign="top" class="value"><g:formatBoolean boolean="${patientInstance?.isProband}" /></td>
				
			</tr>
		
			<tr class="prop">
				<td valign="top" class="name"><g:message code="patient.ege.label" default="Ege" /></td>
				
				<td valign="top" class="value">${fieldValue(bean: patientInstance, field: "ege")}</td>
				
			</tr>
		
			<tr class="prop">
				<td valign="top" class="name"><g:message code="patient.referralRecord.label" default="Referral Record" /></td>
				
				<td valign="top" class="value"><g:link controller="referralRecord" action="show" id="${patientInstance?.referralRecord?.id}">${patientInstance?.referralRecord?.encodeAsHTML()}</g:link></td>
				
			</tr>
		
			<tr class="prop">
				<td valign="top" class="name"><g:message code="patient.relationshipTo.label" default="Relationship To" /></td>
				
				<td valign="top" style="text-align: left;" class="value">
					<ul>
					<g:each in="${patientInstance.relationshipTo}" var="r">
						<li><g:link controller="relationship" action="show" id="${r.id}">${r?.encodeAsHTML()}</g:link></li>
					</g:each>
					</ul>
				</td>
				
			</tr>
		
		</tbody>
	</table>
</section>


<a class='btn btn-primary btn-xs' <g:link controller="relationship" action="create" params="['patient': patientInstance?.id]"><i class="glyphicon glyphicon-plus"></i> relationship</g:link>

</body>

</html>
