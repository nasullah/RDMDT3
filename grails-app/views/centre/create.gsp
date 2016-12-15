<%@ page import="rdmdt.Centre" %>
<!DOCTYPE html>
<html>

<head>
	<meta name="layout" content="main" />
	<g:set var="entityName" value="${message(code: 'centre.label', default: 'Centre')}" />
	<h2><center>Create Centre</center></h2>
</head>

<body>

	<section id="create-centre" class="first">

		<g:hasErrors bean="${centreInstance}">
		<div class="alert alert-danger">
			<g:renderErrors bean="${centreInstance}" as="list" />
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
