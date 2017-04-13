<%@ page import="rdmdt.ReferralStatus" %>
<!DOCTYPE html>
<html>

<head>
    <meta name="layout" content="main" />
    <h2><center>Edit YesNoUnknown</center></h2>
</head>

<body>

<section id="edit-referralStatus" class="first">

    <g:hasErrors bean="${yesNoUnknownInstance}">
        <div class="alert alert-danger">
            <g:renderErrors bean="${yesNoUnknownInstance}" as="list" />
        </div>
    </g:hasErrors>

    <g:form method="post" class="form-horizontal" role="form" >
        <g:hiddenField name="id" value="${yesNoUnknownInstance?.id}" />
        <g:hiddenField name="version" value="${yesNoUnknownInstance?.version}" />
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
