
<%@ page import="rdmdt.User" %>

<!DOCTYPE html>
<html>

<head>
    <meta name="layout" content="main" />
    <g:set var="entityName" value="${message(code: 'participant.label', default: 'Participant')}" />
    <title>User Activity Log</title>

</head>

<body>

<hr/>

<div class="row">
    <div class="col-lg-6">
        <g:form controller="index" action="listAuditLogData">
            <div class="input-group">
                <g:select class="form-control" id="user" name="user.id" from="${User.list().sort{it.username}}" optionKey="id" required=""  value="" noSelection="['':'- Choose User -']" />
                <div class="input-group-btn">
                    <button type="submit" class="btn btn-success" ><span class="glyphicon glyphicon-search"></span> Find User Activity Log</button>
                </div>
            </div>
        </g:form>
    </div>
</div>

<hr/>

<section id="list-participant" class="first">

    <table class="table table-bordered margin-top-medium">
        <thead>
        <tr>
            <g:sortableColumn property="actor" title="Username" />

            <g:sortableColumn property="eventName" title="Event Name" />

            <g:sortableColumn property="dateCreated" title="Date & Time" />

            <g:sortableColumn property="className" title="Table" />

            <g:sortableColumn property="persistedObjectId" title="Record ID" />

            <g:sortableColumn property="oldValue" title="Old Value" />

            <g:sortableColumn property="newValue" title="New Value" />

        </tr>
        </thead>
        <tbody>
        <g:each in="${listAuditLogData}" status="i" var="auditLogInstance">
            <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">

                <td>${fieldValue(bean: auditLogInstance, field: "actor")}</td>

                <td>${fieldValue(bean: auditLogInstance, field: "eventName")}</td>

                <td><g:formatDate date="${auditLogInstance.dateCreated}" /></td>

                <td>${fieldValue(bean: auditLogInstance, field: "className")}</td>

                <td>${fieldValue(bean: auditLogInstance, field: "persistedObjectId")}</td>

                <td>${fieldValue(bean: auditLogInstance, field: "oldValue")}</td>

                <td>${fieldValue(bean: auditLogInstance, field: "newValue")}</td>

            </tr>
        </g:each>
        </tbody>
    </table>

</section>

</body>

</html>
