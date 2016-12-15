<%@ page import="rdmdt.Penetrance" %>
<!DOCTYPE html>
<html>

<head>
	<meta name="layout" content="main" />
	<h2><center>Edit Penetrance</center></h2>
</head>

<body>

	<section id="edit-penetrance" class="first">

		<g:hasErrors bean="${penetranceInstance}">
		<div class="alert alert-danger">
			<g:renderErrors bean="${penetranceInstance}" as="list" />
		</div>
		</g:hasErrors>

		<g:form method="post" class="form-horizontal" role="form" >
			<g:hiddenField name="id" value="${penetranceInstance?.id}" />
			<g:hiddenField name="version" value="${penetranceInstance?.version}" />
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
