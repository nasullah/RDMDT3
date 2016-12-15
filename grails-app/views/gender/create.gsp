<%@ page import="rdmdt.Gender" %>
<!DOCTYPE html>
<html>

<head>
	<meta name="layout" content="main" />
	<h2><center>Create Gender</center></h2>
</head>

<body>

	<section id="create-gender" class="first">

		<g:hasErrors bean="${genderInstance}">
		<div class="alert alert-danger">
			<g:renderErrors bean="${genderInstance}" as="list" />
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

<hr/>

</body>

</html>
