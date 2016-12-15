<%@ page import="rdmdt.ReferralRecord" %>
<!DOCTYPE html>
<html>

<head>
	<meta name="layout" content="main" />
	<h2><center>Edit Application</center></h2>
</head>

<body>

	<section id="edit-referralRecord" class="first">

		<g:hasErrors bean="${referralRecordInstance}">
		<div class="alert alert-danger">
			<g:renderErrors bean="${referralRecordInstance}" as="list" />
		</div>
		</g:hasErrors>

		<g:uploadForm method="post" class="form-horizontal" role="form" onsubmit="window.onbeforeunload=null;">
			<g:hiddenField name="id" value="${referralRecordInstance?.id}" />
			<g:hiddenField name="version" value="${referralRecordInstance?.version}" />
			%{--<g:hiddenField name="_method" value="PUT" />--}%
			
			<g:render template="form"/>
			
			<div class="form-actions margin-top-medium">
				<g:actionSubmit class="btn btn-primary" action="update" value="${message(code: 'default.button.update.label', default: 'Update')}" />
	            <button class="btn" type="reset"><g:message code="default.button.reset.label" default="Reset" /></button>
			</div>
		</g:uploadForm>

	</section>

</body>

</html>
