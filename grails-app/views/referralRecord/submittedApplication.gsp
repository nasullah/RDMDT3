
<%@ page import="rdmdt.Patient; rdmdt.ReferralRecord" %>
<!DOCTYPE html>
<html>

<head>
    <meta name="layout" content="main" />
    <h2><center>Submitted Applications List</center></h2>
</head>

<body>

<section id="index-referralRecord" class="first">

    <table class="table table-bordered margin-top-medium">
        <thead>
        <tr>

            <th>Applicant Name</th>

            <g:sortableColumn property="yourRef" title="${message(code: 'referralRecord.yourRef.label', default: 'Unique Ref')}" />

            <th>Proband Name</th>

            <th>Proband NHS Number</th>

            <th>Submitted Date</th>

            <g:sortableColumn property="referralStatus" title="${message(code: 'referralRecord.referralStatus.label', default: 'Application Status')}" />

            <th>Action</th>


        </tr>
        </thead>
        <tbody>
        <g:each in="${referralRecordInstanceList}" status="i" var="referralRecordInstance">
            <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">

            <td ><g:link action="show" id="${referralRecordInstance.id}" style="color: black">${referralRecordInstance?.clinician?.forename} ${referralRecordInstance?.clinician?.surname}</td>

                <td>${fieldValue(bean: referralRecordInstance, field: "uniqueRef")}</td>

                <td>${rdmdt.Patient.findByReferralRecordAndIsProband(referralRecordInstance, true)?.givenName} ${rdmdt.Patient.findByReferralRecordAndIsProband(referralRecordInstance, true)?.familyName}</g:link></td>

                <td>${rdmdt.Patient.findByReferralRecordAndIsProband(referralRecordInstance, true)?.nhsNumber}</td>

                <td>${fieldValue(bean: referralRecordInstance, field: "submittedDate")}</td>

                <td>${fieldValue(bean: referralRecordInstance, field: "referralStatus")}</td>

                <td><a class='btn btn-success btn-xs' <g:link action="show" id="${referralRecordInstance.id}"><i class="glyphicon glyphicon-open"></i> View Application</g:link></td>

            </tr>
        </g:each>
        </tbody>
    </table>
</section>

</body>

</html>