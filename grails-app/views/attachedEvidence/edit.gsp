<%@ page import="rdmdt.AttachedEvidence" %>
<!DOCTYPE html>
<html>

<head>
	<meta name="layout" content="main" />
	<h2><center>Edit Attached Evidence</center></h2>
</head>

<body>

	<section id="edit-attachedEvidence" class="first">

		<g:hasErrors bean="${attachedEvidenceInstance}">
		<div class="alert alert-danger">
			<g:renderErrors bean="${attachedEvidenceInstance}" as="list" />
		</div>
		</g:hasErrors>

		<g:form method="post" class="form-horizontal" role="form" >
			<g:hiddenField name="id" value="${attachedEvidenceInstance?.id}" />
			<g:hiddenField name="version" value="${attachedEvidenceInstance?.version}" />
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
