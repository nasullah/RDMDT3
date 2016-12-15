
<%@ page import="rdmdt.Clinician" %>
<!DOCTYPE html>
<html>

<head>
	<meta name="layout" content="main" />
	<h2><center>Your Profile</center></h2>

</head>

<body>

<section id="show-clinician" class="first">

	<table class="table">
		<tbody>

			<tr class="prop">
				<td valign="top" class="name"><g:message code="clinician.professionalTitle.label" default="Title" /></td>

				<td valign="top" class="value">${fieldValue(bean: clinicianInstance, field: "professionalTitle")}</td>

			</tr>

			<tr class="prop">
				<td valign="top" class="name"><g:message code="clinician.forename.label" default="Forename" /></td>
				
				<td valign="top" class="value">${fieldValue(bean: clinicianInstance, field: "forename")}</td>
				
			</tr>

			<tr class="prop">
				<td valign="top" class="name"><g:message code="clinician.surname.label" default="Surname" /></td>

				<td valign="top" class="value">${fieldValue(bean: clinicianInstance, field: "surname")}</td>

			</tr>
		
			<tr class="prop">
				<td valign="top" class="name"><g:message code="clinician.email.label" default="Email" /></td>
				
				<td valign="top" class="value">${fieldValue(bean: clinicianInstance, field: "email")}</td>
				
			</tr>
		
			<tr class="prop">
				<td valign="top" class="name"><g:message code="clinician.telephone.label" default="Telephone" /></td>
				
				<td valign="top" class="value">${fieldValue(bean: clinicianInstance, field: "telephone")}</td>
				
			</tr>

			<tr class="prop">
				<td valign="top" class="name"><g:message code="clinician.centre.label" default="Main Centre" /></td>

				<td valign="top" class="value">${fieldValue(bean: clinicianInstance, field: "centreName")}</td>

			</tr>
		
			<tr class="prop">
				<td valign="top" class="name"><g:message code="clinician.departmentName.label" default="Department" /></td>
				
				<td valign="top" class="value">${fieldValue(bean: clinicianInstance, field: "departmentName")}</td>
				
			</tr>

			<g:if test="${clinicianInstance?.departmentOther}">
				<tr class="prop">
					<td valign="top" class="name"><g:message code="clinician.departmentOther.label" default="Specify Department" /></td>

					<td valign="top" class="value">${fieldValue(bean: clinicianInstance, field: "departmentOther")}</td>

				</tr>
			</g:if>
		
			<tr class="prop">
				<td valign="top" class="name"><g:message code="clinician.roleType.label" default="Role" /></td>

				<td valign="top" class="value">${fieldValue(bean: clinicianInstance, field: "roleType")}</td>
				
			</tr>

			<g:if test="${clinicianInstance?.roleTypeOther}">
				<tr class="prop">
					<td valign="top" class="name"><g:message code="clinician.roleTypeOther.label" default="Specify Role" /></td>

					<td valign="top" class="value">${fieldValue(bean: clinicianInstance, field: "roleTypeOther")}</td>

				</tr>
			</g:if>

			<tr class="prop">
				<td valign="top" class="name"><g:message code="clinician.address.label" default="Correspondence address" /></td>

				<td valign="top" class="value">${fieldValue(bean: clinicianInstance, field: "address")}</td>

			</tr>

			<tr class="prop">
				<td valign="top" class="name"><g:message code="clinician.postcode.label" default="Postcode" /></td>

				<td valign="top" class="value">${fieldValue(bean: clinicianInstance, field: "postcode")}</td>

			</tr>
		
		</tbody>
	</table>
</section>

<hr/>

<p class="text-primary">Available Actions</p>

<a class='btn btn-primary btn-xs' <g:link controller="referralRecord" action="create" params="['clinician.id': clinicianInstance?.id]"><i class="glyphicon glyphicon-plus"></i> Add Application</g:link>

</body>

</html>
