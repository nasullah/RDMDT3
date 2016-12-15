
<%@ page import="rdmdt.YesNoUnknown" %>
<!DOCTYPE html>
<html>

<head>
    <meta name="layout" content="main" />
    <h2><center>Show Yes No Unknown</center></h2>
</head>

<body>

<section id="show-referralStatus" class="first">

    <table class="table">
        <tbody>

        <tr class="prop">
            <td valign="top" class="name"><g:message code="referralStatus.yesNoUnknown.label" default="Name" /></td>

            <td valign="top" class="value">${fieldValue(bean: yesNoUnknownInstance, field: "yesNoUnknownName")}</td>

        </tr>

        </tbody>
    </table>
</section>

</body>

</html>