
<%@ page import="rdmdt.Program; rdmdt.ReferralStatus; rdmdt.Patient; rdmdt.ReferralRecord" %>
<!DOCTYPE html>
<html>

<head>
	<meta name="layout" content="main" />
	<h2><center>Application ${referralRecordInstance?.uniqueRef}</center></h2>
</head>

<body>

<g:if test="${currentClinician && (currentClinician == referralRecordInstance.assignedTo)}">
	<hr>
		<g:form action="updateReviewDetails" class="form-horizontal" role="form" >
			<div class="row">
				<div class="col-lg-6">
					<label for="reviewDetails" class="control-label">Add Your Review</label>
					<div>
						<g:textArea class="form-control" id="reviewDetails" name="reviewDetails" value="${referralRecordInstance?.reviewDetails}" rows="4" cols="40"/>
						<g:hiddenField name="referralRecord" value="${referralRecordInstance?.id}" />
					</div>
				</div>
			</div>
			<br>
			<div class="form-actions margin-top-medium">
				<g:submitButton name="saveButton" id="saveButton" class="btn btn-primary" value="Submit Your Review" />
				<button class="btn btn-primary" id="updateButton">Resubmit Your Review</button>
			</div>
		</g:form>

		<div class="form-actions margin-top-medium">
			<button type="button" class="btn btn-primary" id="editButton" onclick="hideEditButton()">Edit Review</button>
		</div>
	<hr>
</g:if>

<section id="show-referralRecord" class="first">

	<table class="table">
		<tbody>

			<g:if test="${referralRecordInstance?.referralStatus?.referralStatusName == 'Submitted'}">
				<tr class="prop" bgcolor="#ff7f50">
					<td valign="top" class="name"><strong>Application Status</strong></td>

					<td valign="top" class="value"><strong>${referralRecordInstance?.referralStatus} ${referralRecordInstance?.submittedDate}</strong></td>

				</tr>
			</g:if>
			<g:elseif test="${referralRecordInstance?.referralStatus?.referralStatusName == 'Approved' || referralRecordInstance?.referralStatus?.referralStatusName == 'Conditionally Approved'}">
				<tr class="prop" bgcolor="lime">
					<td valign="top" class="name"><strong>Application Status</strong></td>

					<td valign="top" class="value"><strong>${referralRecordInstance?.referralStatus} ${referralRecordInstance?.approvedDate}</strong></td>

				</tr>
			</g:elseif>
			<g:elseif test="${referralRecordInstance?.referralStatus?.referralStatusName == 'Withdrawn' || referralRecordInstance?.referralStatus?.referralStatusName == 'Not Approved'}">
				<tr class="prop" bgcolor="#f08080">
					<td valign="top" class="name"><strong>Application Status</strong></td>

					<td valign="top" class="value"><strong>${referralRecordInstance?.referralStatus}</strong></td>

				</tr>
			</g:elseif>
			<g:else>
				<tr class="prop" bgcolor="#e6e6fa">
					<td valign="top" class="name"><strong>Application Status</strong></td>

					<td valign="top" class="value"><strong>${referralRecordInstance?.referralStatus}</strong></td>

				</tr>
			</g:else>

			<g:if test="${referralRecordInstance?.referralStatus == ReferralStatus.findByReferralStatusName('Approved')}">
				<tr class="prop">
					<td valign="top" class="name">Number and identity of family members for sequencing</td>

					<td valign="top" class="value">${fieldValue(bean: referralRecordInstance, field: "approvedIdentityOfFamilyMembersSamplesForSeq")}</td>

				</tr>

				<tr class="prop">
					<td valign="top" class="name">Approved Program</td>

					<td valign="top" class="value">${fieldValue(bean: referralRecordInstance, field: "approvedProgram")}</td>

				</tr>

				<g:if test="${referralRecordInstance?.program?.name != 'Other' && referralRecordInstance?.program?.name != 'HICF2 Whole Genome Sequencing Programme'}">
					<tr class="prop">
						<td valign="top" class="name">Approved 100,000 Genomes Project Rare Disease category</td>

						<td valign="top" class="value">${fieldValue(bean: referralRecordInstance, field: "approvedTargetCategory")}</td>

					</tr>
				</g:if>

				<tr class="prop">
					<td valign="top" class="name"><g:message code="referralRecord.approvalDetails.label" default="Further details" /></td>

					<td valign="top" class="value">${fieldValue(bean: referralRecordInstance, field: "approvalDetails")}</td>

				</tr>
			</g:if>
			<g:elseif test="${referralRecordInstance.referralStatus == ReferralStatus.findByReferralStatusName('Not Approved')}">
				<tr class="prop">
					<td valign="top" class="name"><g:message code="referralRecord.notApprovedDetails.label" default="Please provide details" /></td>

					<td valign="top" class="value">${fieldValue(bean: referralRecordInstance, field: "notApprovedDetails")}</td>

				</tr>
			</g:elseif>
		
			<tr class="prop">
				<td valign="top" class="name"><g:message code="referralRecord.clinician.label" default="Applicant" /></td>
				
				<td valign="top" class="value">${fieldValue(bean: referralRecordInstance?.clinician, field: "forename")} ${fieldValue(bean: referralRecordInstance?.clinician, field: "surname")}</td>

			</tr>

			<tr class="prop">
				<td valign="top" class="name">Responsible Consultant Name</td>

				<td valign="top" class="value">${fieldValue(bean: referralRecordInstance?.correspondingClinician, field: "forename")} ${fieldValue(bean: referralRecordInstance?.correspondingClinician, field: "surname")}</td>

			</tr>

			<g:if test="${referralRecordInstance.coApplicants}">
				<tr class="prop">
					<td valign="top" class="name">Co-applicant Details</td>

					<td valign="top" style="text-align: left;" class="value">
						<g:each in="${referralRecordInstance.coApplicants}" var="c">
							<p>${c?.coApplicant?.forename} ${c?.coApplicant?.surname}</P>
						</g:each>
					</td>

				</tr>
			</g:if>

			<tr class="prop">
				<td valign="top" class="name"><g:message code="referralRecord.yourRef.label" default="Unique Ref" /></td>
				
				<td valign="top" class="value">${fieldValue(bean: referralRecordInstance, field: "uniqueRef")}</td>
				
			</tr>

			<tr class="prop">
				<td valign="top" class="name">Proband's forename</td>

				<td valign="top" class="value">${Patient.findByReferralRecordAndIsProband(referralRecordInstance, true)?.givenName}</td>

			</tr>

			<tr class="prop">
				<td valign="top" class="name">Proband's surname</td>

				<td valign="top" class="value">${Patient.findByReferralRecordAndIsProband(referralRecordInstance, true)?.familyName}</td>

			</tr>

			<tr class="prop">
				<td valign="top" class="name">NHS number of the proband</td>

				<td valign="top" class="value">${Patient.findByReferralRecordAndIsProband(referralRecordInstance, true)?.nhsNumber}</td>

			</tr>

			<tr class="prop">
				<td valign="top" class="name">Gender of the proband</td>

				<td valign="top" class="value">${Patient.findByReferralRecordAndIsProband(referralRecordInstance, true)?.gender}</td>

			</tr>

			<tr class="prop">
				<td valign="top" class="name">Ethnicity of the proband</td>

				<td valign="top" class="value">${Patient.findByReferralRecordAndIsProband(referralRecordInstance, true)?.ethnicity}</td>

			</tr>

			<g:if test="${Patient.findByReferralRecordAndIsProband(referralRecordInstance, true)?.ethnicity?.ethnicityName == 'Other'}">
				<tr class="prop">
					<td valign="top" class="name">Other ethnicity of the proband</td>

					<td valign="top" class="value">${Patient.findByReferralRecordAndIsProband(referralRecordInstance, true)?.otherEthnicity}</td>

				</tr>
			</g:if>

			<tr class="prop">
				<td valign="top" class="name">Age of the proband</td>

				<td valign="top" class="value">${Patient.findByReferralRecordAndIsProband(referralRecordInstance, true)?.age}</td>

			</tr>

			<tr class="prop">
				<td valign="top" class="name">Age unit</td>

				<td valign="top" class="value">${Patient.findByReferralRecordAndIsProband(referralRecordInstance, true)?.ageUnit}</td>

			</tr>

			<tr class="prop">
				<td valign="top" class="name"><g:message code="referralRecord.disorderName.label" default="Name or brief description of disorder"/></td>

				<td valign="top" class="value">${fieldValue(bean: referralRecordInstance, field: "disorderName")}</td>

			</tr>
		%{----}%
			%{--<tr class="prop">--}%
				%{--<td valign="top" class="name"><g:message code="referralRecord.causativeVariantAffect.label" default="Causative variant affect" /></td>--}%
				%{----}%
				%{--<td valign="top" class="value">${fieldValue(bean: referralRecordInstance, field: "causativeVariantAffect")}</td>--}%
				%{----}%
			%{--</tr>--}%
		%{----}%
			%{--<tr class="prop">--}%
				%{--<td valign="top" class="name"><g:message code="referralRecord.knownGeneVariant.label" default="Known gene variant" /></td>--}%
				%{----}%
				%{--<td valign="top" class="value">${fieldValue(bean: referralRecordInstance, field: "knownGeneVariant")}</td>--}%
				%{----}%
			%{--</tr>--}%
		%{----}%
			<tr class="prop">
				<td valign="top" class="name"><g:message code="referralRecord.ageOfSymptoms.label" default="Age of onset of main symptoms" /></td>

				<td valign="top" class="value">${fieldValue(bean: referralRecordInstance, field: "ageOfSymptoms")}</td>

			</tr>

			<tr class="prop">
				<td valign="top" class="name"><g:message code="referralRecord.symptomEgeUnit.label" default="Unit" /></td>

				<td valign="top" class="value">${fieldValue(bean: referralRecordInstance, field: "symptomEgeUnit")}</td>

			</tr>

			<tr class="prop">
				<td valign="top" class="name"><g:message code="referralRecord.clinicalDetails.label" default="Clinical Details" /></td>

				<td valign="top" style="text-align: left;" class="value">
					<g:each in="${referralRecordInstance.clinicalDetails?.sort{it?.id}}" var="c">
						<p>${c}</P>
					</g:each>
				</td>

			</tr>

			<tr class="prop">
				<td valign="top" class="name"><g:message code="referralRecord.geneticTestingOnProband.label" default="Genetic Testing On Proband" /></td>
				
				<td valign="top" class="value">${fieldValue(bean: referralRecordInstance, field: "geneticTestingOnProband")}</td>
				
			</tr>
		
			<tr class="prop">
				<td valign="top" class="name"><g:message code="referralRecord.otherTestingOnProband.label" default="Other Testing On Proband (metabolic, nerve conduction tests, muscle/skin biopsy, etc.)" /></td>
				
				<td valign="top" class="value">${fieldValue(bean: referralRecordInstance, field: "otherTestingOnProband")}</td>
				
			</tr>

			<tr class="prop">
				<td valign="top" class="name"><g:message code="referralRecord.arrayCGH.label" default="Has arrayCGH been performed?" /></td>

				<td valign="top" class="value"><g:formatBoolean boolean="${referralRecordInstance?.arrayCGH}" false="No" true="Yes" /></td>

			</tr>

			<g:if test="${referralRecordInstance.arrayCGHDetails}">
				<tr class="prop">
					<td valign="top" class="name"><g:message code="referralRecord.arrayCGHDetails.label" default="Please provide details" /></td>

					<td valign="top" class="value">${fieldValue(bean: referralRecordInstance, field: "arrayCGHDetails")}</td>

				</tr>
			</g:if>

			<tr class="prop">
				<td valign="top" class="name"><g:message code="referralRecord.otherFamilyMembersAffected.label" default="Are any other family members affected with the same or a related condition?" /></td>

				<td valign="top" class="value">${fieldValue(bean: referralRecordInstance, field: "otherFamilyMembersAffected")}</td>

			</tr>

			<g:if test="${referralRecordInstance.otherFamilyMembersAffectedDetails}">
				<tr class="prop">
					<td valign="top" class="name"><g:message code="referralRecord.otherFamilyMembersAffectedDetails.label" default="Please provide details" /></td>

					<td valign="top" class="value">${fieldValue(bean: referralRecordInstance, field: "otherFamilyMembersAffectedDetails")}</td>

				</tr>
			</g:if>

			<tr class="prop">
				<td valign="top" class="name"><g:message code="referralRecord.pedigree.label" default="3-generation pedigree diagram" /></td>
				
				<td valign="top" class="value"><g:link action="download" id="${referralRecordInstance.id}">${referralRecordInstance.pedigree}</g:link></td>
				
			</tr>

			<tr class="prop">
				<td valign="top" class="name"><g:message code="referralRecord.consanguinityEvidence.label" default="Evidence of consanguinity" /></td>

				<td valign="top" class="value">${fieldValue(bean: referralRecordInstance, field: "consanguinityEvidence")}</td>

			</tr>

			<g:if test="${referralRecordInstance.consanguinityEvidenceDetails}">
				<tr class="prop">
					<td valign="top" class="name"><g:message code="referralRecord.consanguinityEvidenceDetails.label" default="Please provide details" /></td>

					<td valign="top" class="value">${fieldValue(bean: referralRecordInstance, field: "consanguinityEvidenceDetails")}</td>

				</tr>
			</g:if>

			<tr class="prop">
				<td valign="top" class="name"><g:message code="referralRecord.penetrance.label" default=" Is there evidence of incomplete penetrance in this condition?" /></td>

				<td valign="top" class="value">${fieldValue(bean: referralRecordInstance, field: "penetrance")}</td>

			</tr>

			<g:if test="${referralRecordInstance.penetranceDetails}">
				<tr class="prop">
					<td valign="top" class="name"><g:message code="referralRecord.penetranceDetails.label" default="Please provide details" /></td>

					<td valign="top" class="value">${fieldValue(bean: referralRecordInstance, field: "penetranceDetails")}</td>

				</tr>
			</g:if>

			<tr class="prop">
				<td valign="top" class="name">Ethnicity of immediate family</td>

				<td valign="top" style="text-align: left;" class="value">
					<g:each in="${referralRecordInstance.patients}" var="p">
						<g:if test="${!p.isProband}">
							<g:if test="${p?.ethnicity?.ethnicityName == 'Other'}">
								<p>${p.relatedFrom.relationshipType}: ${p.ethnicity}, ${p.otherEthnicity}</p>
							</g:if>
							<g:else>
								<p>${p.relatedFrom.relationshipType}: ${p.ethnicity}</p>
							</g:else>
						</g:if>
					</g:each>
				</td>

			</tr>

			<tr class="prop">
				<td valign="top" class="name">Paternal Breast And Or Ovarian Cancer</td>

				<td valign="top" class="value">${fieldValue(bean: referralRecordInstance?.paternal?.first(), field: "breastAndOrOvarianCancer")}</td>

			</tr>

			<tr class="prop">
				<td valign="top" class="name">Paternal Colorectal Cancer</td>

				<td valign="top" class="value">${fieldValue(bean: referralRecordInstance?.paternal?.first(), field: "colorectalCancer")}</td>

			</tr>

			<tr class="prop">
				<td valign="top" class="name">Paternal Ischaemic Heart Disease Or Stroke</td>

				<td valign="top" class="value">${fieldValue(bean: referralRecordInstance?.paternal?.first(), field: "ischaemicHeartDiseaseOrStroke")}</td>

			</tr>

			<tr class="prop">
				<td valign="top" class="name">Paternal Endocrine Tumours</td>

				<td valign="top" class="value">${fieldValue(bean: referralRecordInstance?.paternal?.first(), field: "endocrineTumours")}</td>

			</tr>

			<tr class="prop">
				<td valign="top" class="name">Maternal Breast And Or Ovarian Cancer</td>

				<td valign="top" class="value">${fieldValue(bean: referralRecordInstance?.maternal?.first(), field: "breastAndOrOvarianCancer")}</td>

			</tr>

			<tr class="prop">
				<td valign="top" class="name">Maternal Colorectal Cancer</td>

				<td valign="top" class="value">${fieldValue(bean: referralRecordInstance?.maternal?.first(), field: "colorectalCancer")}</td>

			</tr>

			<tr class="prop">
				<td valign="top" class="name">Maternal Ischaemic Heart Disease Or Stroke</td>

				<td valign="top" class="value">${fieldValue(bean: referralRecordInstance?.maternal?.first(), field: "ischaemicHeartDiseaseOrStroke")}</td>

			</tr>

			<tr class="prop">
				<td valign="top" class="name">Maternal Endocrine Tumours</td>

				<td valign="top" class="value">${fieldValue(bean: referralRecordInstance?.maternal?.first(), field: "endocrineTumours")}</td>

			</tr>
		
			<tr class="prop">
				<td valign="top" class="name"><g:message code="referralRecord.furtherDetailsOfHistory.label" default="Details and/or note any other significant family history" /></td>
				
				<td valign="top" class="value">${fieldValue(bean: referralRecordInstance, field: "furtherDetailsOfHistory")}</td>
				
			</tr>

			<tr class="prop">
				<td valign="top" class="name"><g:message code="referralRecord.numberOfSamplesForSeq.label" default="Number of samples" /></td>

				<td valign="top" class="value">${fieldValue(bean: referralRecordInstance, field: "numberOfSamplesForSeq")}</td>

			</tr>

			<tr class="prop">
				<td valign="top" class="name"><g:message code="referralRecord.identityOfFamilyMembersSamplesForSeq.label" default="Identity of family members" /></td>

				<td valign="top" class="value">${fieldValue(bean: referralRecordInstance, field: "identityOfFamilyMembersSamplesForSeq")}</td>

			</tr>

			<tr class="prop">
				<td valign="top" class="name">Are any of the samples taken from deceased individuals?</td>

				<td valign="top" class="value"><g:formatBoolean false="No" true="Yes" boolean="${referralRecordInstance?.isAnySampleFromDeceasedIndividuals}" /></td>

			</tr>

			<g:if test="${referralRecordInstance.isAnySampleFromDeceasedIndividualsDetails}">
				<tr class="prop">
					<td valign="top" class="name"><g:message code="referralRecord.isAnySampleFromDeceasedIndividualsDetails.label" default="Please provide details" /></td>

					<td valign="top" class="value">${fieldValue(bean: referralRecordInstance, field: "isAnySampleFromDeceasedIndividualsDetails")}</td>

				</tr>
			</g:if>

			<tr class="prop">
				<td valign="top" class="name">Do any of the individuals proposed for sequencing live outside the catchment area of the Oxford Genomic Medicine Centre?</td>

				<td valign="top" class="value"><g:formatBoolean false="No" true="Yes" boolean="${referralRecordInstance?.anyIndividualsForSeqOutOfArea}" /></td>

			</tr>

			<g:if test="${referralRecordInstance.anyIndividualsForSeqOutOfAreaDetails}">
				<tr class="prop">
					<td valign="top" class="name"><g:message code="referralRecord.anyIndividualsForSeqOutOfAreaDetails.label" default="Please provide details" /></td>

					<td valign="top" class="value">${fieldValue(bean: referralRecordInstance, field: "anyIndividualsForSeqOutOfAreaDetails")}</td>

				</tr>
			</g:if>

			<tr class="prop">
				<td valign="top" class="name">Further information about sample availability</td>

				<td valign="top" class="value">${fieldValue(bean: referralRecordInstance, field: "samplesForSeqDetails")}</td>

			</tr>

			<tr class="prop">
				<td valign="top" class="name"><g:message code="referralRecord.program.label" default="Program" /></td>

				<td valign="top" class="value">${fieldValue(bean: referralRecordInstance, field: "program")}</td>

			</tr>

			<tr class="prop">
				<td valign="top" class="name"><g:message code="referralRecord.note.label" default="Supporting information" /></td>

				<td valign="top" class="value">${fieldValue(bean: referralRecordInstance, field: "note")}</td>

			</tr>

			<g:if test="${referralRecordInstance?.program?.name != 'Other' && referralRecordInstance?.program?.name != 'HICF2 Whole Genome Sequencing Programme'}">

				<tr class="prop">
					<td valign="top" class="name"><g:message code="referralRecord.targetCategory.label" default="100,000 Genomes Project Rare Disease category" /></td>

					<td valign="top" class="value">${fieldValue(bean: referralRecordInstance, field: "targetCategory")}</td>

				</tr>

				<tr class="prop">
					<td valign="top" class="name"><g:message code="referralRecord.eligibility.label" default="Is this patient/family eligible?" /></td>

					<td valign="top" class="value">${fieldValue(bean: referralRecordInstance, field: "eligibility")}</td>

				</tr>

				<g:if test="${referralRecordInstance.eligibilityDetails}">
					<tr class="prop">
						<td valign="top" class="name"><g:message code="referralRecord.eligibilityDetails.label" default="Please provide details" /></td>

						<td valign="top" class="value">${fieldValue(bean: referralRecordInstance, field: "eligibilityDetails")}</td>

					</tr>
				</g:if>

				<tr class="prop">
					<td valign="top" class="name"><g:message code="referralRecord.consentPatientOrFamily.label" default="Consent patient or family" /></td>

					<td valign="top" class="value">${fieldValue(bean: referralRecordInstance, field: "consentPatientOrFamily")}</td>

				</tr>
			</g:if>

			<tr class="prop">
				<td valign="top" class="name"><g:message code="referralRecord.assignedTo.label" default="Assigned To" /></td>

				<td valign="top" class="value">${fieldValue(bean: referralRecordInstance, field: "assignedTo")}</td>

			</tr>

			<g:if test="${currentClinician != referralRecordInstance.assignedTo}">
				<tr class="prop">
					<td valign="top" class="name"><g:message code="referralRecord.reviewDetails.label" default="Review Details" /></td>

					<td valign="top" class="value">${fieldValue(bean: referralRecordInstance, field: "reviewDetails")}</td>

				</tr>
			</g:if>
		
			<tr class="prop">
				<td valign="top" class="name"><g:message code="referralRecord.meetingDate.label" default="Meeting Date" /></td>
				
				<td valign="top" class="value"><g:formatDate format="yyyy-MM-dd" date="${referralRecordInstance?.meetingDate}" /></td>
				
			</tr>

			<tr class="prop">
				<td valign="top" class="name"><g:message code="referralRecord.attachedEvidence.label" default="Attached Evidence" /></td>
				
				<td valign="top" style="text-align: left;" class="value">
					<ul>
					<g:each in="${referralRecordInstance.attachedEvidence}" var="a">
						<li><g:link controller="attachedEvidence" action="show" id="${a.id}" target="_blank">${a.type}: ${a.toString().subSequence(a.toString().lastIndexOf('/')+3, a.toString().length())}</g:link></li>
					</g:each>
					</ul>
				</td>
				
			</tr>

			%{--<tr class="prop">--}%
				%{--<td valign="top" class="name">Approved Program</td>--}%

				%{--<td valign="top" class="value">${fieldValue(bean: referralRecordInstance, field: "approvedTargetCategory")}</td>--}%

			%{--</tr>--}%

			%{--<g:if test="${referralRecordInstance?.program?.name != 'Other' && referralRecordInstance?.program?.name != 'HICF2 Whole Genome Sequencing Programme'}">--}%
				%{--<tr class="prop">--}%
					%{--<td valign="top" class="name">Approved Target 100,000 Genomes Project Rare Disease category</td>--}%

					%{--<td valign="top" class="value">${fieldValue(bean: referralRecordInstance, field: "approvedProgram")}</td>--}%

				%{--</tr>--}%
			%{--</g:if>--}%

			<tr class="prop">
				<td valign="top" class="name">Note</td>

				<td valign="top" class="value">${fieldValue(bean: referralRecordInstance, field: "adminNote")}</td>

			</tr>

			<g:if test="${referralRecordInstance.rareDiseasesPhenotypeReports}">
				<tr class="prop">
					<td valign="top" class="name">Rare Diseases Phenotype Report</td>

					<td valign="top" style="text-align: left;" class="value">
						<g:link controller="rareDiseasesPhenotypeReport" action="show" id="${referralRecordInstance?.rareDiseasesPhenotypeReports?.first()?.id}">Phenotype Report</g:link>
					</td>

				</tr>
			</g:if>
		
		</tbody>
	</table>
</section>

%{--<div class="modal fade" id="requestReviewDial">--}%
	%{--<div class="modal-dialog" style="position: absolute; left: 0%;">--}%
		%{--<div class="modal-content">--}%
			%{--<div class="modal-header">--}%
				%{--<button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>--}%
				%{--<h4 class="modal-title">Request Review</h4>--}%
			%{--</div>--}%
			%{--<div class="modal-body">--}%

				%{--<g:form action="updateStatus" class="form-horizontal" role="form" >--}%
					%{--<div >--}%
						%{--<label class="control-label">Assigned To</label>--}%
						%{--<div>--}%
							%{--<g:select class="form-control" id="assignedTo" name="assignedTo.id" from="${rdmdt.Clinician.list()}" optionKey="id" noSelection="['':'- Choose -']"/>--}%
						%{--</div>--}%
						%{--<g:hiddenField name="assignedTo" value="myValue" />--}%
					%{--</div>--}%
				%{--</g:form>--}%
			%{--</div>--}%
			%{--<div class="modal-footer">--}%
				%{--<a class='btn btn-primary btn-small' <g:link controller="referralRecord" action="updateStatus" params="['referralRecord': referralRecordInstance?.id, referralStatus: ReferralStatus.findByReferralStatusName('Review Requested')?.id, assignedTo:assignedTo]" ><i class="glyphicon glyphicon-plus"></i> ${message(code: 'default.add.label', args: [message(code: 'participant.label', default: 'Participant')])}</g:link>--}%
				%{--<button type="button" class="btn" data-dismiss="modal">Close</button>--}%
			%{--</div>--}%
		%{--</div>--}%
	%{--</div>--}%
%{--</div>--}%

<p class="text-primary">Available Actions</p>

<g:if test="${referralRecordInstance.referralStatus == ReferralStatus.findByReferralStatusName('Submitted')}">
	<a class='btn btn-success btn-xs' <g:link action="updateStatus" params="['referralRecord': referralRecordInstance?.id, referralStatus: ReferralStatus.findByReferralStatusName('Submitted')?.id]"><i class="glyphicon glyphicon-ok"></i> Re-submit Application</g:link>
</g:if>
<g:else>
	<a class='btn btn-success btn-xs' <g:link action="updateStatus" params="['referralRecord': referralRecordInstance?.id, referralStatus: ReferralStatus.findByReferralStatusName('Submitted')?.id]"><i class="glyphicon glyphicon-ok"></i> Submit Application</g:link>
</g:else>
<g:if test="${referralRecordInstance.referralStatus != ReferralStatus.findByReferralStatusName('Withdrawn')}">
	<a class='btn btn-warning btn-xs' <g:link action="updateStatus" params="['referralRecord': referralRecordInstance?.id, referralStatus: ReferralStatus.findByReferralStatusName('Withdrawn')?.id]"><i class="glyphicon glyphicon-flag"></i> Withdraw Application</g:link>
</g:if>
<g:if test="${referralRecordInstance.referralStatus != ReferralStatus.findByReferralStatusName('Suspended')}">
	<a class='btn btn-warning btn-xs' <g:link action="updateStatus" params="['referralRecord': referralRecordInstance?.id, referralStatus: ReferralStatus.findByReferralStatusName('Suspended')?.id]"><i class="glyphicon glyphicon-flag"></i> Suspend Application</g:link>
</g:if>
<g:if test="${referralRecordInstance.referralStatus == ReferralStatus.findByReferralStatusName('Approved') && !referralRecordInstance.rareDiseasesPhenotypeReports}">
	<a class='btn btn-success btn-xs' <g:link controller="rareDiseasesPhenotypeReport" action="create" params="['referralRecord': referralRecordInstance?.id, 'specificDisorderId':referralRecordInstance?.approvedTargetCategory?.originalId]"><i class="glyphicon glyphicon-plus"></i> Add Phenotypes</g:link>
</g:if>

<a class='btn btn-primary btn-xs' <g:link action="exportWord" params="['referralRecordId': referralRecordInstance?.id]"><i class="glyphicon glyphicon-download-alt"></i> Download Application in Word format</g:link>

<hr/>

<p class="text-primary">Admin only actions</p>

<sec:ifAnyGranted roles="ROLE_ADMIN">
	<g:if test="${referralRecordInstance.referralStatus == ReferralStatus.findByReferralStatusName('Submitted') && referralRecordInstance.referralStatus != ReferralStatus.findByReferralStatusName('Review Requested')}">
		<a class='btn btn-primary btn-xs' <g:link action="updateStatus" params="['referralRecord': referralRecordInstance?.id, referralStatus: ReferralStatus.findByReferralStatusName('Review Requested')?.id]"><i class="glyphicon glyphicon-eye-open"></i> Request Review </g:link>
		%{--<a class='btn btn-primary btn-xs' onclick="showDialog()"><i class="glyphicon glyphicon-eye-open"></i> Request Review </a>--}%

	</g:if>
	<g:if test="${referralRecordInstance.referralStatus == ReferralStatus.findByReferralStatusName('Review Submitted')}">
		<a class='btn btn-success btn-xs' <g:link action="updateStatus" params="['referralRecord': referralRecordInstance?.id, referralStatus: ReferralStatus.findByReferralStatusName('Approved')?.id]"><i class="glyphicon glyphicon-ok-sign"></i> Approve Application</g:link>
		<a class='btn btn-danger btn-xs' <g:link action="updateStatus" params="['referralRecord': referralRecordInstance?.id, referralStatus: ReferralStatus.findByReferralStatusName('Not Approved')?.id]"><i class="glyphicon glyphicon-remove-circle"></i> Reject Application</g:link>
	</g:if>
	<hr/>
</sec:ifAnyGranted>

<sec:ifAnyGranted roles="ROLE_ADMIN">

	<p class="text-primary">Letters</p>

	<g:if test="${referralRecordInstance.referralStatus == ReferralStatus.findByReferralStatusNameOrReferralStatusName('Review Submitted', 'Review Requested')}">
		<a class='btn btn-default btn-xs' <g:link  action="renderWednesdayMeetingReviewLetter" id="${referralRecordInstance?.id}" target="_blank"><i class="glyphicon glyphicon-print"></i> Print Wednesday Meeting Review Letter</g:link>
	</g:if>

	<g:if test="${(referralRecordInstance.program && referralRecordInstance.program == Program.findByName('HICF2 Whole Genome Sequencing Programme')) || (referralRecordInstance.approvedProgram && referralRecordInstance.approvedProgram == Program.findByName('HICF2 Whole Genome Sequencing Programme'))}">
		<a class='btn btn-default btn-xs' <g:link  action="renderHICFLetter" id="${referralRecordInstance?.id}" target="_blank"><i class="glyphicon glyphicon-print"></i> Print HICF Letter</g:link>
	</g:if>

	<g:if test="${referralRecordInstance.referralStatus == ReferralStatus.findByReferralStatusName('Not Approved')}">
		<a class='btn btn-default btn-xs' <g:link  action="renderNotApprovedLetter" id="${referralRecordInstance?.id}" target="_blank"><i class="glyphicon glyphicon-print"></i> Print Not Approved Letter</g:link>
	</g:if>

</sec:ifAnyGranted>

<sec:ifAnyGranted roles="ROLE_ADMIN, ROLE_USER">

	<g:if test="${referralRecordInstance.referralStatus == ReferralStatus.findByReferralStatusName('Approved')}">
		<a class='btn btn-default btn-xs' <g:link  action="renderStandardApprovalLetter" id="${referralRecordInstance?.id}" target="_blank"><i class="glyphicon glyphicon-print"></i> Print Standard Approval Letter</g:link>
	</g:if>

	<hr/>

</sec:ifAnyGranted>

<content tag="javascript">
	<script>
		showEditButton();
		function showEditButton(){
			if ($("#reviewDetails").val() == ""){
				$("#saveButton").show();
				$("#updateButton").hide();
				$("#editButton").hide();
			} else{
				$("#reviewDetails").prop('readonly', true);
				$("#saveButton").hide();
				$("#updateButton").hide();
				$("#editButton").show()
			}
		}

		function hideEditButton(){
			$("#reviewDetails").prop('readonly', false);
			$("#updateButton").show();
			$("#editButton").hide();
		}

		function showDialog(){
			$('#requestReviewDial').modal()
		}

	</script>
</content>

</body>

</html>
