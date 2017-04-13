
<%@ page import="rdmdt.Patient; rdmdt.ReferralRecord" %>
<!DOCTYPE html>
<html>

<head>
	<meta name="layout" content="main" />
	<h2><center>List Applications</center></h2>
	<asset:javascript src="fp.js"/>
	<asset:stylesheet src="fp.css"/>
</head>

<body>

<g:hiddenField id="month" name="month" value="${month}" />
<g:hiddenField id="year" name="year" value="${year}" />

<filterpane:filterButton text="Filter This List" />
<filterpane:filterPane domain="rdmdt.ReferralRecord"
					   filterPropertyValues="${[meetingDate: [precision: 'day'], submittedDate: [precision: 'day'], approvedDate: [precision: 'day']]}"
					   associatedProperties="clinician.forename, clinician.surname, patients.givenName, patients.familyName, patients.nhsNumber,
											 patients.ege, patients.egeUnit.egeUnitName, clinicalDetails.clinicalDetailsName,
											 unrelatedClinicalFeatures.unrelatedClinicalFeatures, paternal.breastAndOrOvarianCancer,
											 paternal.colorectalCancer, paternal.ischaemicHeartDiseaseOrStroke, paternal.endocrineTumours,
											 maternal.breastAndOrOvarianCancer, maternal.colorectalCancer, maternal.ischaemicHeartDiseaseOrStroke,
											 maternal.endocrineTumours, extraTests.testName, referralStatus.referralStatusName"
					   excludeProperties="causativeVariantAffect, knownGeneVariant, geneticTestingOnProband, otherTestingOnProband, pedigree, furtherDetailsOfHistory, numberOfSamplesForSeq,
										  identityOfFamilyMembersSamplesForSeq, approvedIdentityOfFamilyMembersSamplesForSeq, samplesForSeqDetails, consanguinityEvidence, consanguinityEvidenceDetails,
										  penetranceDetails, note, reviewDetails, isAnySampleFromDeceasedIndividuals, isAnySampleFromDeceasedIndividualsDetails, ageOfSymptoms, arrayCGH,
										  arrayCGHDetails, anyIndividualsForSeqOutOfAreaDetails, otherFamilyMembersAffectedDetails, eligibilityDetails, consentPatientOrFamily, approvalDetails,
										  notApprovedDetails, adminNote"/>

<hr/>

<p>Number of records: ${referralRecordInstanceTotal == null ? ReferralRecord.count(): referralRecordInstanceTotal}</p>

<hr/>

<section id="list-referralRecord" class="first">

	<table class="table table-bordered margin-top-medium">
		<thead>
		<tr>

			<th>Applicant Name</th>

			<g:sortableColumn property="yourRef" title="${message(code: 'referralRecord.yourRef.label', default: 'Unique Ref')}" />

			<th>Proband Name</th>

			<th>Proband NHS Number</th>

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

				<td>${fieldValue(bean: referralRecordInstance, field: "referralStatus")}</td>

				<td><a class='btn btn-success btn-xs' <g:link action="show" id="${referralRecordInstance.id}"><i class="glyphicon glyphicon-open"></i> View Application</g:link></td>

			</tr>
		</g:each>
		</tbody>
	</table>
	<g:if test="${!month && !year}">
		<div class="pagination">
			<g:paginate total="${referralRecordInstanceTotal == null ? ReferralRecord.count(): referralRecordInstanceTotal}" params="${filterParams}" />
		</div>
	</g:if>
</section>

</body>

</html>
