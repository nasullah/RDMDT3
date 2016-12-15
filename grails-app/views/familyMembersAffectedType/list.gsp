
<%@ page import="rdmdt.FamilyMembersAffectedType" %>
<!DOCTYPE html>
<html>

<head>
	<meta name="layout" content="main" />
	<h2><center>Family Members Affected Type List</center></h2>
</head>

<body>

<section id="list-familyMembersAffectedType" class="first">

	<table class="table table-bordered margin-top-medium">
		<thead>
			<tr>
			
				<g:sortableColumn property="familyMembersAffectedTypeName" title="${message(code: 'familyMembersAffectedType.familyMembersAffectedTypeName.label', default: 'Family Members Affected Type Name')}" />
			
			</tr>
		</thead>
		<tbody>
		<g:each in="${familyMembersAffectedTypeInstanceList}" status="i" var="familyMembersAffectedTypeInstance">
			<tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
			
				<td><g:link action="show" id="${familyMembersAffectedTypeInstance.id}">${fieldValue(bean: familyMembersAffectedTypeInstance, field: "familyMembersAffectedTypeName")}</g:link></td>
			
			</tr>
		</g:each>
		</tbody>
	</table>
	<div class="pagination">
		<g:paginate total="${familyMembersAffectedTypeInstanceCount}" />
	</div>
</section>

</body>

</html>
