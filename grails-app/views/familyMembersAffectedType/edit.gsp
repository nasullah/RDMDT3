<%@ page import="rdmdt.FamilyMembersAffectedType" %>
<!DOCTYPE html>
<html>

<head>
	<meta name="layout" content="main" />
	<h2><center>Edit Family Members Affected Type</center></h2>
</head>

<body>

	<section id="edit-familyMembersAffectedType" class="first">

		<g:hasErrors bean="${familyMembersAffectedTypeInstance}">
		<div class="alert alert-danger">
			<g:renderErrors bean="${familyMembersAffectedTypeInstance}" as="list" />
		</div>
		</g:hasErrors>

		<g:form method="post" class="form-horizontal" role="form" >
			<g:hiddenField name="id" value="${familyMembersAffectedTypeInstance?.id}" />
			<g:hiddenField name="version" value="${familyMembersAffectedTypeInstance?.version}" />
			<g:hiddenField name="_method" value="PUT" />
			
			<g:render template="form"/>
			
			<div class="form-actions margin-top-medium">
				<g:actionSubmit class="btn btn-primary" action="update" value="${message(code: 'default.button.update.label', default: 'Update')}" />
	            <button class="btn" type="reset"><g:message code="default.button.reset.label" default="Reset" /></button>
			</div>
		</g:form>

	</section>

</body>

</html>
