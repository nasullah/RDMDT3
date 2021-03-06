<%@ page import="rdmdt.FamilyMembersAffectedType" %>
<!DOCTYPE html>
<html>

<head>
	<meta name="layout" content="main" />
	<h2><center>Create Family Members Affected Type</center></h2>
</head>

<body>

	<section id="create-familyMembersAffectedType" class="first">

		<g:hasErrors bean="${familyMembersAffectedTypeInstance}">
		<div class="alert alert-danger">
			<g:renderErrors bean="${familyMembersAffectedTypeInstance}" as="list" />
		</div>
		</g:hasErrors>

		<g:form action="save" class="form-horizontal" role="form" >
			<g:render template="form"/>

			<div class="form-actions margin-top-medium">
				<g:submitButton name="create" class="btn btn-primary" value="Save" />
	            <button class="btn" type="reset"><g:message code="default.button.reset.label" default="Reset" /></button>
			</div>
		</g:form>

	</section>

</body>

</html>
