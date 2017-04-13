
<%@ page import="rdmdt.YesNoUnknown" %>
<!DOCTYPE html>
<html>

<head>
    <meta name="layout" content="main" />
    <h2><center>Yes No Unknown</center></h2>
</head>

<body>

<section id="index-referralStatus" class="first">

    <table class="table table-bordered margin-top-medium">
        <thead>
        <tr>

            <g:sortableColumn property="yesNoUnknownName" title="${message(code: 'referralStatus.yesNoUnknownName.label', default: 'Yes No Unknown')}" />

        </tr>
        </thead>
        <tbody>
        <g:each in="${yesNoUnknownInstanceList}" status="i" var="yesNoUnknownInstance">
            <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">

                <td><g:link action="show" id="${yesNoUnknownInstance.id}">${fieldValue(bean: yesNoUnknownInstance, field: "yesNoUnknownName")}</g:link></td>

            </tr>
        </g:each>
        </tbody>
    </table>
    <div class="pagination">
        <g:paginate total="${yesNoUnknownInstanceCount}" />
    </div>
</section>

</body>

</html>
