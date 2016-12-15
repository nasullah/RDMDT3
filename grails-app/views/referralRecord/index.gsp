
<%@ page import="rdmdt.Patient; rdmdt.ReferralRecord" %>
<!DOCTYPE html>
<html>

<head>
	<meta name="layout" content="main" />
	<h2><center>List Applications</center></h2>
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

			<g:sortableColumn property="referralDate" title="${message(code: 'referralRecord.referralDate.label', default: 'Application Status')}" />


		</tr>
		</thead>
		<tbody>
		<g:each in="${referralRecordInstanceList}" status="i" var="referralRecordInstance">
			<tr class="${(i % 2) == 0 ? 'odd' : 'even'}">

			<td><g:link action="show" id="${referralRecordInstance.id}">${referralRecordInstance?.clinician?.forename} ${referralRecordInstance?.clinician?.surname}</td>

				<td>${fieldValue(bean: referralRecordInstance, field: "uniqueRef")}</td>

				<td>${rdmdt.Patient.findByReferralRecordAndIsProband(referralRecordInstance, true)?.givenName} ${rdmdt.Patient.findByReferralRecordAndIsProband(referralRecordInstance, true)?.familyName}</g:link></td>

				<td>${rdmdt.Patient.findByReferralRecordAndIsProband(referralRecordInstance, true)?.nhsNumber}</td>

				<td>${fieldValue(bean: referralRecordInstance, field: "referralStatus")}</td>

			</tr>
		</g:each>
		</tbody>
	</table>
	<div class="pagination">
		<g:paginate total="${referralRecordInstanceTotal == null ? ReferralRecord.count(): referralRecordInstanceTotal}" params="${filterParams}" />
		<a>Number of records: ${referralRecordInstanceTotal == null ? ReferralRecord.count(): referralRecordInstanceTotal}</a>
	</div>
</section>

</body>

</html>
