
<%@ page import="rdmdt.Clinician" %>
<!DOCTYPE html>
<html>

<head>
	<meta name="layout" content="main" />
	<h2><center>Clinician List</center></h2>
	<asset:javascript src="fp.js"/>
	<asset:stylesheet src="fp.css"/>
</head>

<body>

<hr/>

<filterpane:filterButton text="Filter This List" />
<filterpane:filterPane domain="rdmdt.Clinician"
					   associatedProperties="departmentName.departmentName, centreName.centreName"
					   excludeProperties="professionalTitle, email, telephone, roleTypeOther, address, postcode"/>
<hr/>

<p>Number of records: ${clinicianInstanceTotal == null ? Clinician.count(): clinicianInstanceTotal}</p>

<hr/>
<section id="list-clinician" class="first">

	<table class="table table-bordered margin-top-medium">
		<thead>
		<tr>

			<th>Name</th>

			<g:sortableColumn property="centre" title="${message(code: 'clinician.centreName.label', default: 'Main Centre')}" />

			<g:sortableColumn property="departmentName" title="${message(code: 'clinician.departmentName.label', default: 'Department')}" />

			<g:sortableColumn property="roleType" title="${message(code: 'clinician.roleType.label', default: 'Role')}" />

			<th>Action</th>

		</tr>
		</thead>
		<tbody>
		<g:each in="${clinicianInstanceList}" status="i" var="clinicianInstance">
			<tr class="${(i % 2) == 0 ? 'odd' : 'even'}">

				<td><g:link action="show" id="${clinicianInstance.id}" style="color: black">${clinicianInstance?.forename} ${clinicianInstance?.surname}</g:link></td>

				<td>${fieldValue(bean: clinicianInstance, field: "centreName")}</td>

				<td>${fieldValue(bean: clinicianInstance, field: "departmentName")}</td>

				<td>${fieldValue(bean: clinicianInstance, field: "roleType")}</td>

				<td><a class='btn btn-success btn-xs' <g:link action="show" id="${clinicianInstance.id}"><i class="glyphicon glyphicon-open"></i> View Clinician</g:link></td>

			</tr>
		</g:each>
		</tbody>
	</table>
	<div class="pagination">
		<g:paginate total="${clinicianInstanceTotal == null ? Clinician.count(): clinicianInstanceTotal}" params="${filterParams}" />
		%{--<g:paginate total="${clinicianInstanceCount}" />--}%
	</div>
</section>

</body>

</html>
