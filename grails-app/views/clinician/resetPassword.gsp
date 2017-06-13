<%@ page import="rdmdt.Clinician" %>
<!DOCTYPE html>
<html>

<head>
    <meta name="layout" content="main" />
    <h2><center>Reset Your Password</center></h2>
</head>

<body>

<section id="reset-password" class="first">

    <g:form action="resetPassword" class="form-horizontal" role="form" >

        <div class="row">
            <div class="col-lg-3">
                <label class="control-label">New Password</label>
                <input class="form-control" type="password" name="newPassword" required=""/>
                <label class="control-label">Confirm New Password</label>
                <input class="form-control" type="password" name="confirmNewPassword" required=""/>
            </div>
        </div>
        <br>
        <div class="form-actions margin-top-medium">
            <g:submitButton name="create" class="btn btn-primary" value="Save" />
            <button class="btn" type="reset"><g:message code="default.button.reset.label" default="Reset" /></button>
        </div>
    </g:form>

</section>

<hr/>

</body>

</html>