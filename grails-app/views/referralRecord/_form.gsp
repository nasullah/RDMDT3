<%@ page import="rdmdt.FamilyMembersAffectedType; rdmdt.EligibilityType; rdmdt.Penetrance; rdmdt.Consanguinity; rdmdt.Ethnicity; rdmdt.Gender; rdmdt.AgeUnit; rdmdt.ReferralStatus; rdmdt.Program; rdmdt.Patient; rdmdt.AttachedEvidenceType; rdmdt.RelationshipType; rdmdt.Clinician; rdmdt.ReferralRecord" %>


		<h2>Applicant Information</h2>

		<div class="row">
			<div class="col-lg-6">
				<div class="${hasErrors(bean: referralRecordInstance, field: 'clinician', 'error')} required">
					<g:if test="${referralRecordInstance?.clinician?.id}">
						<label for="clinician" class="control-label"><g:message code="referralRecord.clinician.label" default="Clinician" /><span class="required-indicator">*</span></label>
						<div>
							<g:select class="form-control" id="clinician" name="clinician.id" from="${rdmdt.Clinician.list()}" optionKey="id" required="" value="${referralRecordInstance?.clinician?.id}"/>
							<span class="help-inline">${hasErrors(bean: referralRecordInstance, field: 'clinician', 'error')}</span>
						</div>
					</g:if>
					<g:else>
						<label for="clinician" class="control-label">Clinician (enter name)<span class="required-indicator">*</span></label>
					%{--<richui:autoComplete class="form-control"  name="clinicianName" action="${createLinkTo('dir': 'clinician/findClinician')}" value="${referralRecordInstance?.clinician}" onItemSelect="callClinician(id)" maxResultsDisplayed="20" minQueryLength="2"/>--}%
						<div>
							<auto-complete
								css="form-control"
								name="clinicianName"
								action="${createLink('controller': 'clinician','action': 'findClinician')}?format=json&query=%QUERY%"
								action-token="%QUERY%"
								item-selected.delegate="callClinician($event.detail.value)"
								max-items="20"
								min-chars="2"
								value="${referralRecordInstance?.clinician}">
								<input class="form-control" value="${referralRecordInstance?.clinician}" disabled />
							</auto-complete>
						</div>
						<g:hiddenField id ="clinician" name ="clinician" value="${referralRecordInstance?.clinician?.id}"/>
					</g:else>
				</div>
			</div>

			<div class="col-lg-6">
				<div class="">
					<label class="control-label">Responsible consultant if different (enter name)</label>
					%{--<richui:autoComplete class="form-control"  name="consultantName" action="${createLinkTo('dir': 'clinician/findClinician')}" value="${referralRecordInstance?.correspondingClinician}" onItemSelect="callCorrespondingClinician(id)" maxResultsDisplayed="20" minQueryLength="2" />--}%
					<div>
						<auto-complete
							css="form-control"
							name="consultantName"
							action="${createLink('controller': 'clinician','action': 'findClinician')}?format=json&query=%QUERY%"
							action-token="%QUERY%"
							item-selected.delegate="callCorrespondingClinician($event.detail.value)"
							max-items="20"
							min-chars="2"
							value="${referralRecordInstance?.correspondingClinician}">
							<input class="form-control" value="${referralRecordInstance?.correspondingClinician}" disabled />
						</auto-complete>
					</div>
					<g:hiddenField id ="correspondingClinician" name ="correspondingClinician" value="${referralRecordInstance?.correspondingClinician?.id}"/>
				</div>
			</div>

			<div class="col-lg-6">
				<div id="addCoapplicantDetailsButton">
					<label class="control-label">Add Co-applicant</label>
					<div>
						<button type="button" id="addCoapplicantButton" class="btn btn-primary btn" value="add" onClick= 'addCoApplicants()'><span class="glyphicon glyphicon-plus"></span> Add</button>
					</div>
				</div>
			</div>
		</div>

		<g:render template="coapplicant"/>

		<hr/>

		<h2>About The Proband</h2>

		<div class="row">

			<div class="col-lg-6">
				<div class="${hasErrors(bean: patientInstance, field: 'givenName', 'error')} ">
					<label for="givenName" class="control-label"><g:message code="patient.givenName.label" default="Forename" /></label>
					<g:if test="${referralRecordInstance?.patients?.find{p -> p.isProband}?.givenName}">
						<g:set var="givenName" value="${referralRecordInstance.patients?.find{p -> p.isProband}?.givenName}" />
					</g:if>
					<g:else>
						<g:set var="givenName" value="${params.givenName}" />
					</g:else>
					<div>
						<g:textField class="form-control" name="givenName" value="${givenName}"/>
					</div>
				</div>
			</div>

			<div class="col-lg-6">
				<div class="${hasErrors(bean: patientInstance, field: 'familyName', 'error')} ">
					<label for="familyName" class="control-label"><g:message code="patient.familyName.label" default="Surname" /></label>
					<g:if test="${referralRecordInstance?.patients?.find{p -> p.isProband}?.familyName}">
						<g:set var="familyName" value="${referralRecordInstance.patients?.find{p -> p.isProband}?.familyName}" />
					</g:if>
					<g:else>
						<g:set var="familyName" value="${params.familyName}" />
					</g:else>
					<div>
						<g:textField class="form-control" name="familyName" value="${familyName}"/>
					</div>
				</div>
			</div>

			<div class="col-lg-6">
				<div class="${hasErrors(bean: referralRecordInstance, field: 'uniqueRef', 'error')} ">
					<label for="uniqueRef" class="control-label"><g:message code="referralRecord.uniqueRef.label" default="Unique Ref (case number or other local identifier)" /><span class="required-indicator">*</span></label>
					<div>
						<g:textField class="form-control" name="uniqueRef" required="" value="${referralRecordInstance?.uniqueRef}"/>
						<span class="help-inline">${hasErrors(bean: referralRecordInstance, field: 'uniqueRef', 'error')}</span>
					</div>
				</div>
			</div>

			<div class="col-lg-6">
				<div class="${hasErrors(bean: referralRecordInstance, field: 'nhsNumber', 'error')} ">
					<label for="nhsNumberProband" class="control-label">NHS number of the proband<span class="required-indicator">*</span></label>
					<g:if test="${referralRecordInstance?.patients?.find{p -> p.isProband}?.nhsNumber}">
						<g:set var="nhsNumber" value="${referralRecordInstance?.patients?.find{p -> p.isProband}?.nhsNumber}" />
					</g:if>
					<g:else>
						<g:set var="nhsNumber" value="${params.nhsNumberProband}" />
					</g:else>
					<div>
						<g:textField class="form-control" id="nhsNumberProband" name="nhsNumberProband" value="${nhsNumber}" required=""/>
					</div>
				</div>
			</div>

			<div class="col-lg-6">
				<div class="${hasErrors(bean: patientInstance, field: 'gender', 'error')} ">
					<label for="genderProband" class="control-label"><g:message code="patient.gender.label" default="Gender" /></label>
					<div>
						%{--<g:select class="form-control" id="genderProband" name="genderProband" from="${rdmdt.Gender.list()}" optionKey="id" value="${referralRecordInstance.patients?.find{p -> p.isProband}?.gender?.id}" noSelection="['':'- Choose -']"/>--}%
						<g:if test="${referralRecordInstance?.patients?.find{p -> p.isProband}?.gender?.id}">
							<g:set var="genderProband" value="${referralRecordInstance?.patients?.find{p -> p.isProband}?.gender?.id}" />
						</g:if>
						<g:elseif test="${params.genderProband}">
							<g:set var="genderProband" value="${params.genderProband}" />
						</g:elseif>
						<g:else>
							<g:set var="genderProband" value="${Gender.findByGenderName('Not specified')?.id}" />
						</g:else>
						<g:radioGroup name="genderProband"
									  values="[Gender.findByGenderName('Male')?.id, Gender.findByGenderName('Female')?.id, Gender.findByGenderName('Not known')?.id, Gender.findByGenderName('Not specified')?.id]"
									  labels="['Male', 'Female', 'Not known', 'Not specified']"
									  value="${genderProband}">
							${it.radio}  ${it.label} &nbsp;
						</g:radioGroup>
						<span class="help-inline">${hasErrors(bean: patientInstance, field: 'gender', 'error')}</span>
					</div>
				</div>
			</div>
		</div>

		<div class="row">
			<div class="col-lg-6">
				<div class="${hasErrors(bean: patientInstance, field: 'ethnicity', 'error')} ">
					<label for="ethnicityProband" class="control-label">Ethnicity</label>
					<div>
						<g:if test="${referralRecordInstance?.patients?.find{p -> p.isProband}?.ethnicity?.id}">
							<g:set var="ethnicityProband" value="${referralRecordInstance?.patients?.find{p -> p.isProband}?.ethnicity?.id}" />
						</g:if>
						<g:else>
							<g:set var="ethnicityProband" value="${params.ethnicityProband}" />
						</g:else>
						<g:select class="form-control" id="ethnicityProband" name="ethnicityProband" from="${Ethnicity.list()}" onchange="otherEthnicityProbandOpt()" optionKey="id" value="${ethnicityProband}" noSelection="['':'- Choose -']" />
						<span class="help-inline">${hasErrors(bean: patientInstance, field: 'ethnicity', 'error')}</span>
					</div>
				</div>
			</div>

			<div class="col-lg-6" id="otherEthnicityOption">
				<div class="${hasErrors(bean: patientInstance, field: 'otherEthnicity', 'error')} ">
					<label for="otherEthnicityProband" class="control-label">Please specify</label>
					<div>
						<g:if test="${referralRecordInstance?.patients?.find{p -> p.isProband}?.otherEthnicity}">
							<g:set var="otherEthnicityProband" value="${referralRecordInstance?.patients?.find{p -> p.isProband}?.otherEthnicity}" />
						</g:if>
						<g:else>
							<g:set var="otherEthnicityProband" value="${params.otherEthnicityProband}" />
						</g:else>
						<g:textField class="form-control" id="otherEthnicityProband" name="otherEthnicityProband" value="${otherEthnicityProband}"/>
						<span class="help-inline">${hasErrors(bean: patientInstance, field: 'otherEthnicity', 'error')}</span>
					</div>
				</div>
			</div>
		</div>

		<div class="row">
			<div class="col-lg-6">
				<div>
					<label for="ageProband" class="control-label">Age (if deceased, age at death)</label>
					<g:if test="${referralRecordInstance?.patients?.find{p -> p.isProband}?.age}">
						<g:set var="ageProband" value="${referralRecordInstance?.patients?.find{p -> p.isProband}?.age}" />
					</g:if>
					<g:else>
						<g:set var="ageProband" value="${params.ageProband}" />
					</g:else>
					<div>
						<g:field class="form-control" name="ageProband" type="number" min="1" value="${ageProband}"/>
					</div>
				</div>
			</div>

			<div class="col-lg-6">
				<div>
					<label for="egeUnitProband" class="control-label">Age unit</label>
					<div>
						%{--<g:select class="form-control" id="egeUnitProband" name="egeUnitProband" from="${AgeUnit.findAllByAgeUnitNameNotEqualAndAgeUnitNameNotEqual('Congenital', 'Prenatal')}" optionKey="id"  value="${referralRecordInstance.patients?.find{p -> p.isProband}?.ageUnit?.id}" noSelection="['':'- Choose -']"/>--}%
						<g:if test="${referralRecordInstance?.patients?.find{p -> p.isProband}?.ageUnit?.id}">
							<g:set var="egeUnitProband" value="${referralRecordInstance?.patients?.find{p -> p.isProband}?.ageUnit?.id}" />
						</g:if>
						<g:elseif test="${params.egeUnitProband}">
							<g:set var="egeUnitProband" value="${params.egeUnitProband}" />
						</g:elseif>
						<g:else>
							<g:set var="egeUnitProband" value="${AgeUnit.findByAgeUnitName('Years')?.id}" />
						</g:else>
						<g:radioGroup name="egeUnitProband"
									  values="[AgeUnit.findByAgeUnitName('Days')?.id, AgeUnit.findByAgeUnitName('Weeks')?.id, AgeUnit.findByAgeUnitName('Months')?.id, AgeUnit.findByAgeUnitName('Years')?.id]"
									  labels="['Days', 'Weeks', 'Months', 'Years']"
									  value="${egeUnitProband}">
							${it.radio}  ${it.label} &nbsp;
						</g:radioGroup>
					</div>
				</div>
			</div>
		</div>

		<hr/>

		<h2>Clinical details</h2>

		<div class="row">
			<div class="col-lg-6">
				<div class="${hasErrors(bean: referralRecordInstance, field: 'disorderName', 'error')} ">
					<label for="disorderName" class="control-label"><g:message code="referralRecord.disorderName.label" default="Name or brief description of disorder" /></label>
					<div>
						<g:textField class="form-control" name="disorderName" value="${referralRecordInstance?.disorderName}"/>
						<span class="help-inline">${hasErrors(bean: referralRecordInstance, field: 'disorderName', 'error')}</span>
					</div>
				</div>
			</div>
		</div>

		<div class="row">
			<div class="col-lg-6">
				<div class="${hasErrors(bean: referralRecordInstance, field: 'ageOfSymptoms', 'error')} ">
					<label for="ageOfSymptoms" class="control-label"><g:message code="referralRecord.ageOfSymptoms.label" default="Age of onset of main symptoms (enter 0 for congenital or prenatal)" /></label>
					<div>
						<g:field class="form-control" name="ageOfSymptoms" type="number" min="0" value="${referralRecordInstance?.ageOfSymptoms}"/>
						<span class="help-inline">${hasErrors(bean: referralRecordInstance, field: 'ageOfSymptoms', 'error')}</span>
					</div>
				</div>
			</div>

			<div class="col-lg-6">
				<div>
					<label class="control-label">Unit</label>
					<div>
						%{--<g:select class="form-control" id="symptomEgeUnit" name="symptomEgeUnit.id" from="${rdmdt.AgeUnit.list()}" optionKey="id"  value="" noSelection="['':'- Choose -']"/>--}%
						<g:if test="${referralRecordInstance?.symptomEgeUnit?.id}">
							<g:set var="symptomEgeUnit" value="${referralRecordInstance?.symptomEgeUnit?.id}" />
						</g:if>
						<g:else>
							<g:set var="symptomEgeUnit" value="${AgeUnit.findByAgeUnitName('Congenital')?.id}" />
						</g:else>
						<g:radioGroup name="symptomEgeUnit.id"
									  values="[AgeUnit.findByAgeUnitName('Days')?.id, AgeUnit.findByAgeUnitName('Weeks')?.id, AgeUnit.findByAgeUnitName('Months')?.id, AgeUnit.findByAgeUnitName('Years')?.id, AgeUnit.findByAgeUnitName('Prenatal')?.id, AgeUnit.findByAgeUnitName('Asymptomatic')?.id, AgeUnit.findByAgeUnitName('Congenital')?.id]"
									  labels="['Days', 'Weeks', 'Months', 'Years','Prenatal', 'Asymptomatic', 'Congenital']"
									  value="${symptomEgeUnit}">
							${it.radio}  ${it.label} &nbsp;
						</g:radioGroup>
					</div>
				</div>
			</div>
		</div>

		<g:render template="clinicalDetails"/>

		<div class="row">
			<div class="col-lg-6">
				<div class="${hasErrors(bean: referralRecordInstance, field: 'geneticTestingOnProband', 'error')} ">
					<label for="geneticTestingOnProband" class="control-label"><g:message code="referralRecord.geneticTestingOnProband.label" default="Genetic Testing (chromosome analysis, single gene, gene panel, etc.)" /></label>
					<div>
						<g:textArea class="form-control" name="geneticTestingOnProband" value="${referralRecordInstance?.geneticTestingOnProband}" rows="4" cols="40"/>
						<span class="help-inline">${hasErrors(bean: referralRecordInstance, field: 'geneticTestingOnProband', 'error')}</span>
					</div>
				</div>
			</div>

			<div class="col-lg-6">
				<div class="${hasErrors(bean: referralRecordInstance, field: 'otherTestingOnProband', 'error')} ">
					<label for="otherTestingOnProband" class="control-label">Other testing on proband (metabolic, nerve conduction, muscle/skin biopsy, etc.)</label>
					<div>
						<g:textArea class="form-control" name="otherTestingOnProband" value="${referralRecordInstance?.otherTestingOnProband}" rows="4" cols="40"/>
						<span class="help-inline">${hasErrors(bean: referralRecordInstance, field: 'otherTestingOnProband', 'error')}</span>
					</div>
				</div>
			</div>

			<div class="col-lg-6">
				<div class="${hasErrors(bean: personInstance, field: 'arrayCGH', 'error')} ">
					<label class="control-label">Has arrayCGH been performed?</label>
					<div>
						<label class="radio-inline"><input type="radio" name="arrayCGH" id="arrayCGHYes" value="true" ${referralRecordInstance.arrayCGH == true ? 'checked="checked"' : ''} onclick="showArrayCGHDetails()">Yes</label>
						<label class="radio-inline"><input type="radio" name="arrayCGH" id="arrayCGHNo" value="false" ${referralRecordInstance.arrayCGH == false ? 'checked="checked"' : ''} onclick="hideArrayCGHDetails()">No</label>
						<span class="help-inline">${hasErrors(bean: referralRecordInstance, field: 'arrayCGH', 'error')}</span>
					</div>
				</div>
			</div>

			<div class="col-lg-6" id="arrayCGHDetailsOption">
				<div class="${hasErrors(bean: referralRecordInstance, field: 'arrayCGHDetails', 'error')} ">
					<label class="control-label">Please provide results or upload report below</label>
					<div>
						<g:textField class="form-control" id="arrayCGHDetails" name="arrayCGHDetails" value="${referralRecordInstance?.arrayCGHDetails}"/>
						<span class="help-inline">${hasErrors(bean: referralRecordInstance, field: 'arrayCGHDetails', 'error')}</span>
					</div>
				</div>
			</div>
		</div>

		<h3>Attach evidence</h3>

		<p>Use this feature to add test reports, images, or other supporting evidence.  This information is retained within the NHS firewall</p>

		<br/>

		<g:if test="${referralRecordInstance?.attachedEvidence}">
			<div class="row">
				<div class="col-lg-12">
					<div>
						<label class="control-label">Uploaded Evidence</label>
						<div>
							<g:each in="${referralRecordInstance.attachedEvidence}" var="a">
								<g:link controller="attachedEvidence" action="show" id="${a.id}">${a.type}: ${a.toString().subSequence(a.toString().lastIndexOf('/')+3, a.toString().length())}</g:link>
							</g:each>
						</div>
					</div>
				</div>
			</div>
		</g:if>

		<div class="row">
			<div class="col-lg-3">
				<div>
					<label class="control-label">Type</label>
					<div>
						<g:select class="form-control" id="attachedEvidenceType1" name="attachedEvidenceType1" from="${AttachedEvidenceType.list()}" optionKey="id"  noSelection="['':'- Choose -']"/>
					</div>
				</div>
			</div>

			<div class="col-lg-3">
				<div class="">
					<label  class="control-label">File</label>
					<div>
						<input type="file" id="attachedEvidenceFile1" name="attachedEvidenceFile1" onmousedown="attachedDocument1()"/>
					</div>
				</div>
			</div>

			<div class="col-lg-3">
				<div id="addAttachedEvidenceButtonDetails">
					<label class="control-label">Attach more evidence</label>
					<div>
						<button type="button" id="addAttachedEvidenceButton" class="btn btn-primary btn" value="add" onClick= 'addAttachedEvidence()'><span class="glyphicon glyphicon-plus"></span> Add</button>
					</div>
				</div>
			</div>
		</div>

		<g:render template="attachedEvidence"/>

		<hr/>

		<h2>About The Family</h2>

		<div class="row">
			<div class="col-lg-6">
				<div class="${hasErrors(bean: personInstance, field: 'otherFamilyMembersAffected', 'error')} ">
					<label class="control-label">Are any other family members affected with the same or related condition?</label>
					<div>
						<g:if test="${referralRecordInstance?.otherFamilyMembersAffected?.id}">
							<g:set var="otherFamilyMembersAffected" value="${referralRecordInstance?.otherFamilyMembersAffected?.id}" />
						</g:if>
						<g:else>
							<g:set var="otherFamilyMembersAffected" value="${FamilyMembersAffectedType.findByFamilyMembersAffectedTypeName('Unknown')?.id}" />
						</g:else>
						<g:radioGroup name="otherFamilyMembersAffected.id"
									  values="[FamilyMembersAffectedType.findByFamilyMembersAffectedTypeName('Yes')?.id, FamilyMembersAffectedType.findByFamilyMembersAffectedTypeName('No')?.id, FamilyMembersAffectedType.findByFamilyMembersAffectedTypeName('Unknown')?.id]"
									  labels="['Yes', 'No', 'Unknown']"
									  value="${otherFamilyMembersAffected}"
									  id="otherFamilyMembersAffected"
									  onclick="otherFamilyMembersAffectedDetailsOpt()">
							${it.radio}  ${it.label} &nbsp;
						</g:radioGroup>
						<span class="help-inline">${hasErrors(bean: referralRecordInstance, field: 'otherFamilyMembersAffected', 'error')}</span>
					</div>
				</div>
			</div>

			<div class="col-lg-6" id="otherFamilyMembersAffectedDetailsOption">
				<div class="${hasErrors(bean: referralRecordInstance, field: 'otherFamilyMembersAffectedDetails', 'error')} ">
					<label class="control-label">Please provide details</label>
					<div>
						<g:textField class="form-control" id="otherFamilyMembersAffectedDetails" name="otherFamilyMembersAffectedDetails" value="${referralRecordInstance?.otherFamilyMembersAffectedDetails}"/>
						<span class="help-inline">${hasErrors(bean: referralRecordInstance, field: 'otherFamilyMembersAffectedDetails', 'error')}</span>
					</div>
				</div>
			</div>
		</div>

		<div class="row">
			<g:if test="${referralRecordInstance?.pedigree == null}">
				<div class="col-lg-6">
					<div class="">
						<label  class="control-label">Please upload a 3-generation pedigree diagram if possible</label>
						<div>
							<input type="file" id="pedigreeFile" name="pedigreeFile" />
						</div>
					</div>
				</div>
			</g:if>
		</div>

		<div class="row">
			<div class="col-lg-6">
				<div class="${hasErrors(bean: referralRecordInstance, field: 'consanguinityEvidence', 'error')} required">
					<label class="control-label">Is there evidence of consanguinity?</label>
					<div>
						%{--<g:select class="form-control" id="consanguinityEvidence" name="consanguinityEvidence.id" from="${rdmdt.Consanguinity.list()}" optionKey="id" onchange="consanguinityEvidenceDetailsOpt()" value="${referralRecordInstance?.consanguinityEvidence?.id}" noSelection="['':'- Choose -']"/>--}%
						<g:if test="${referralRecordInstance?.consanguinityEvidence?.id}">
							<g:set var="consanguinityEvidence" value="${referralRecordInstance?.consanguinityEvidence?.id}" />
						</g:if>
						<g:else>
							<g:set var="consanguinityEvidence" value="${Consanguinity.findByConsanguinityEvidence('Unknown')?.id}" />
						</g:else>
						<g:radioGroup name="consanguinityEvidence.id"
									  values="[Consanguinity.findByConsanguinityEvidence('Yes')?.id, Consanguinity.findByConsanguinityEvidence('No')?.id, Consanguinity.findByConsanguinityEvidence('Unknown')?.id]"
									  labels="['Yes', 'No', 'Unknown']"
									  value="${consanguinityEvidence}"
									  id="consanguinityEvidence"
									  onclick="consanguinityEvidenceDetailsOpt()">
							${it.radio}  ${it.label} &nbsp;
						</g:radioGroup>
						<span class="help-inline">${hasErrors(bean: referralRecordInstance, field: 'consanguinityEvidence', 'error')}</span>
					</div>
				</div>
			</div>

			<div class="col-lg-6" id="consanguinityEvidenceDetailsOption">
				<div class="${hasErrors(bean: referralRecordInstance, field: 'consanguinityEvidenceDetails', 'error')} ">
					<label class="control-label">Please provide details</label>
					<div>
						<g:textField class="form-control" id="consanguinityEvidenceDetails" name="consanguinityEvidenceDetails" value="${referralRecordInstance?.consanguinityEvidenceDetails}"/>
						<span class="help-inline">${hasErrors(bean: referralRecordInstance, field: 'consanguinityEvidenceDetails', 'error')}</span>
					</div>
				</div>
			</div>
		</div>

		<div class="row">
			<div class="col-lg-6">
				<div class="${hasErrors(bean: referralRecordInstance, field: 'penetrance', 'error')} required">
					<label class="control-label">Is there evidence of incomplete penetrance in this condition?</label>
					<div>
						%{--<g:select class="form-control" id="penetrance" name="penetrance.id" from="${rdmdt.Penetrance.list()}" optionKey="id" onchange="penetranceDetailsOpt()" value="${referralRecordInstance?.penetrance?.id}" noSelection="['':'- Choose -']"/>--}%
						<g:if test="${referralRecordInstance?.penetrance?.id}">
							<g:set var="penetrance" value="${referralRecordInstance?.penetrance?.id}" />
						</g:if>
						<g:else>
							<g:set var="penetrance" value="${Penetrance.findByPenetranceName('Unknown')?.id}" />
						</g:else>
						<g:radioGroup name="penetrance.id"
									  values="[Penetrance.findByPenetranceName('Yes')?.id, Penetrance.findByPenetranceName('No')?.id, Penetrance.findByPenetranceName('Unknown')?.id]"
									  labels="['Yes', 'No', 'Unknown']"
									  value="${penetrance}"
									  id="penetrance"
									  onclick="penetranceDetailsOpt()">
							${it.radio}  ${it.label} &nbsp;
						</g:radioGroup>
						<span class="help-inline">${hasErrors(bean: referralRecordInstance, field: 'penetrance', 'error')}</span>
					</div>
				</div>
			</div>

			<div class="col-lg-6" id="penetranceDetailsOption">
				<div class="${hasErrors(bean: referralRecordInstance, field: 'penetranceDetails', 'error')} ">
					<label class="control-label">Please provide details</label>
					<div>
						<g:textField class="form-control" id="penetranceDetails" name="penetranceDetails" value="${referralRecordInstance?.penetranceDetails}"/>
						<span class="help-inline">${hasErrors(bean: referralRecordInstance, field: 'penetranceDetails', 'error')}</span>
					</div>
				</div>
			</div>
		</div>

		<hr/>

		<h3>Ethnicity of immediate family</h3>

		<div class="row">
			<div class="col-lg-6">
				<div class="">
					<label class="control-label">Mother</label>
					<g:if test="${referralRecordInstance?.patients?.find{p -> p?.relatedFrom?.relationshipType == RelationshipType.findByRelationshipTypeName('Mother')}?.ethnicity?.id}">
						<g:set var="ethnicityMother" value="${referralRecordInstance?.patients?.find{p -> p?.relatedFrom?.relationshipType == RelationshipType.findByRelationshipTypeName('Mother')}?.ethnicity?.id}" />
					</g:if>
					<g:else>
						<g:set var="ethnicityMother" value="${params.ethnicityMother}" />
					</g:else>
					<div>
						<g:select class="form-control" id="ethnicityMother" name="ethnicityMother" from="${Ethnicity.list()}" optionKey="id" value="${ethnicityMother}" onchange="otherEthnicityMotherOpt()" noSelection="['':'- Choose -']"/>
					</div>
				</div>
			</div>

			<div class="col-lg-6" id="otherEthnicityMotherOption">
				<div>
					<label for="otherEthnicityMother" class="control-label">Please specify</label>
					<g:if test="${referralRecordInstance?.patients?.find{p -> p?.relatedFrom?.relationshipType == RelationshipType.findByRelationshipTypeName('Mother')}?.otherEthnicity}">
						<g:set var="otherEthnicityMother" value="${referralRecordInstance?.patients?.find{p -> p?.relatedFrom?.relationshipType == RelationshipType.findByRelationshipTypeName('Mother')}?.otherEthnicity}" />
					</g:if>
					<g:else>
						<g:set var="otherEthnicityMother" value="${params.otherEthnicityMother}" />
					</g:else>
					<div>
						<g:textField class="form-control" id="otherEthnicityMother" name="otherEthnicityMother" value="${otherEthnicityMother}"/>
					</div>
				</div>
			</div>
		</div>

		<div class="row">
			<div class="col-lg-6">
				<div class="">
					<label class="control-label">Father</label>
					<g:if test="${referralRecordInstance?.patients?.find{p -> p?.relatedFrom?.relationshipType == RelationshipType.findByRelationshipTypeName('Father')}?.ethnicity?.id}">
						<g:set var="ethnicityFather" value="${referralRecordInstance?.patients?.find{p -> p?.relatedFrom?.relationshipType == RelationshipType.findByRelationshipTypeName('Father')}?.ethnicity?.id}" />
					</g:if>
					<g:else>
						<g:set var="ethnicityFather" value="${params.ethnicityFather}" />
					</g:else>
					<div>
						<g:select class="form-control" id="ethnicityFather" name="ethnicityFather" from="${Ethnicity.list()}" optionKey="id" value="${ethnicityFather}" onchange="otherEthnicityFatherOpt()" noSelection="['':'- Choose -']"/>
					</div>
				</div>
			</div>

			<div class="col-lg-6" id="otherEthnicityFatherOption">
				<div>
					<label for="otherEthnicityFather" class="control-label">Please specify</label>
					<g:if test="${referralRecordInstance?.patients?.find{p -> p?.relatedFrom?.relationshipType == RelationshipType.findByRelationshipTypeName('Father')}?.otherEthnicity}">
						<g:set var="otherEthnicityFather" value="${referralRecordInstance?.patients?.find{p -> p?.relatedFrom?.relationshipType == RelationshipType.findByRelationshipTypeName('Father')}?.otherEthnicity}" />
					</g:if>
					<g:else>
						<g:set var="otherEthnicityFather" value="${params.otherEthnicityFather}" />
					</g:else>
					<div>
						<g:textField class="form-control" id="otherEthnicityFather" name="otherEthnicityFather" value="${otherEthnicityFather}"/>
					</div>
				</div>
			</div>
		</div>

		<hr/>

		<h3>Family History</h3>

		<br/>

		<h4>Paternal</h4>

		<g:render template="paternal"/>

		<br/>

		<h4>Maternal</h4>

		<g:render template="maternal"/>

		<br/>

		<div class="row">
			<div class="col-lg-6">
				<div class="${hasErrors(bean: referralRecordInstance, field: 'furtherDetailsOfHistory', 'error')} ">
					<label for="furtherDetailsOfHistory" class="control-label"><g:message code="referralRecord.furtherDetailsOfHistory.label" default="Please add details and/or note any other significant family history" /></label>
					<div>
						<g:textArea class="form-control" name="furtherDetailsOfHistory" value="${referralRecordInstance?.furtherDetailsOfHistory}" rows="4" cols="40"/>
						<span class="help-inline">${hasErrors(bean: referralRecordInstance, field: 'furtherDetailsOfHistory', 'error')}</span>
					</div>
				</div>
			</div>
		</div>

		<hr/>

		<h3>Number and identity of family members proposed for sequencing</h3>

		<div class="row">
			<div class="col-lg-6">
				<div class="${hasErrors(bean: referralRecordInstance, field: 'numberOfSamplesForSeq', 'error')} ">
					<label for="numberOfSamplesForSeq" class="control-label"><g:message code="referralRecord.numberOfSamplesForSeq.label" default="Number of samples" /></label>
					<div>
						<g:field class="form-control" name="numberOfSamplesForSeq" type="number" min="1" value="${referralRecordInstance.numberOfSamplesForSeq}"/>
						<span class="help-inline">${hasErrors(bean: referralRecordInstance, field: 'numberOfSamplesForSeq', 'error')}</span>
					</div>
				</div>
			</div>

			<div class="col-lg-6">
				<div class="${hasErrors(bean: referralRecordInstance, field: 'identityOfFamilyMembersSamplesForSeq', 'error')} ">
					<label for="identityOfFamilyMembersSamplesForSeq" class="control-label"><g:message code="referralRecord.identityOfFamilyMembersSamplesForSeq.label" default="Identity of family members (e.g. proband and both parents)"/></label>
					<div>
						<g:field class="form-control" name="identityOfFamilyMembersSamplesForSeq" type="text" value="${referralRecordInstance.identityOfFamilyMembersSamplesForSeq}"/>
						<span class="help-inline">${hasErrors(bean: referralRecordInstance, field: 'identityOfFamilyMembersSamplesForSeq', 'error')}</span>
					</div>
				</div>
			</div>
		</div>

		<div class="row">
			<div class="col-lg-6">
				<div class="${hasErrors(bean: referralRecordInstance, field: 'isAnySampleFromDeceasedIndividuals', 'error')} ">
					<label class="control-label"><g:message code="referralRecord.isAnySampleFromDeceasedIndividuals.label" default="Are any of the samples taken from deceased individuals?" /></label>
					<div>
						<label class="radio-inline"><input type="radio" name="isAnySampleFromDeceasedIndividuals" id="isAnySampleFromDeceasedIndividualsYes" value="true" ${referralRecordInstance.isAnySampleFromDeceasedIndividuals == true ? 'checked="checked"' : ''} onclick="showIsAnySampleFromDeceasedIndividualsDetailsOpt()">Yes</label>
						<label class="radio-inline"><input type="radio" name="isAnySampleFromDeceasedIndividuals" id="isAnySampleFromDeceasedIndividualsNo" value="false" ${referralRecordInstance.isAnySampleFromDeceasedIndividuals == false ? 'checked="checked"' : ''} onclick="hideIsAnySampleFromDeceasedIndividualsDetailsOpt()">No</label>
						<span class="help-inline">${hasErrors(bean: referralRecordInstance, field: 'isAnySampleFromDeceasedIndividuals', 'error')}</span>
					</div>
				</div>
			</div>

			<div class="col-lg-6" id="isAnySampleFromDeceasedIndividualsDetailsOption">
				<div class="${hasErrors(bean: referralRecordInstance, field: 'isAnySampleFromDeceasedIndividualsDetails', 'error')} ">
					<label class="control-label">Please provide details</label>
					<div>
						<g:textField class="form-control" id="isAnySampleFromDeceasedIndividualsDetails" name="isAnySampleFromDeceasedIndividualsDetails" value="${referralRecordInstance?.isAnySampleFromDeceasedIndividualsDetails}"/>
						<span class="help-inline">${hasErrors(bean: referralRecordInstance, field: 'isAnySampleFromDeceasedIndividualsDetails', 'error')}</span>
					</div>
				</div>
			</div>
		</div>

		<div class="row">
			<div class="col-lg-11">
				<div class="${hasErrors(bean: referralRecordInstance, field: 'anyIndividualsForSeqOutOfArea', 'error')} ">
					<label class="control-label"><g:message code="referralRecord.anyIndividualsForSeqOutOfArea.label" default="Do any of the individuals proposed for sequencing live outside the catchment area of the Oxford Genomic Medicine Centre?" /></label>
					<div>
						<label class="radio-inline"><input type="radio" name="anyIndividualsForSeqOutOfArea" id="anyIndividualsForSeqOutOfAreaYes" value="true" ${referralRecordInstance.anyIndividualsForSeqOutOfArea == true ? 'checked="checked"' : ''} onclick="showAnyIndividualsForSeqOutOfAreaDetailsOpt()">Yes</label>
						<label class="radio-inline"><input type="radio" name="anyIndividualsForSeqOutOfArea" id="anyIndividualsForSeqOutOfAreaNo" value="false" ${referralRecordInstance.anyIndividualsForSeqOutOfArea == false ? 'checked="checked"' : ''} onclick="hideAnyIndividualsForSeqOutOfAreaDetailsOpt()">No</label>
						<span class="help-inline">${hasErrors(bean: referralRecordInstance, field: 'anyIndividualsForSeqOutOfArea', 'error')}</span>
					</div>
				</div>
			</div>
		</div>

		<div class="row">
			<div class="col-lg-6" id="anyIndividualsForSeqOutOfAreaDetailsOption">
				<div class="${hasErrors(bean: referralRecordInstance, field: 'anyIndividualsForSeqOutOfAreaDetails', 'error')} ">
					<label class="control-label">Please provide details</label>
					<div>
						<g:textField class="form-control" id="anyIndividualsForSeqOutOfAreaDetails" name="anyIndividualsForSeqOutOfAreaDetails" value="${referralRecordInstance?.anyIndividualsForSeqOutOfAreaDetails}"/>
						<span class="help-inline">${hasErrors(bean: referralRecordInstance, field: 'anyIndividualsForSeqOutOfAreaDetails', 'error')}</span>
					</div>
				</div>
			</div>
		</div>

		<div class="row">
			<div class="col-lg-6">
				<div class="${hasErrors(bean: referralRecordInstance, field: 'samplesForSeqDetails', 'error')} ">
					<label for="samplesForSeqDetails" class="control-label">Record any further information about sample availability in the box below</label>
					<div>
						<g:textArea class="form-control" name="samplesForSeqDetails" value="${referralRecordInstance?.samplesForSeqDetails}" rows="4" cols="40"/>
						<span class="help-inline">${hasErrors(bean: referralRecordInstance, field: 'samplesForSeqDetails', 'error')}</span>
					</div>
				</div>
			</div>
		</div>

		<hr/>

		<h2>Whole genome sequencing programme</h2>

		<p>The default programme for whole genome sequencing is the national 100,000 Genomes Project, but other local providers may be available. Please add any supporting information or comments regarding this, especially if you have a preference. If this case has been discussed through the Clinical Genetics Consultants Meeting, please also indicate here, including the date of the meeting and the selected recruitment category.</p>

		<div class="row">
			<div class="col-lg-9">
				<div class="${hasErrors(bean: referralRecordInstance, field: 'program', 'error')} ">
					<label class="control-label">Program</label>
					<div>
						%{--<g:select class="form-control" id="program" name="program.id" from="${Program.list()}" optionKey="id" value="${referralRecordInstance?.program?.id}" noSelection="['':'- Choose -']"/>--}%
						<g:if test="${referralRecordInstance.program?.id}">
							<g:set var="program" value="${referralRecordInstance.program?.id}" />
						</g:if>
						<g:else>
							<g:set var="program" value="${Program.findByName('100,000 Genomes Project')?.id}" />
						</g:else>
						<g:radioGroup name="program.id"
									  values="[Program.findByName('100,000 Genomes Project')?.id, Program.findByName('HICF2 Whole Genome Sequencing Programme')?.id, Program.findByName('Other')?.id]"
									  labels="['100,000 Genomes Project', 'HICF2 Whole Genome Sequencing Programme', 'Other']"
									  value="${program}"
									  id="program"
									  onclick="gelProjectOpt()">
							${it.radio}  ${it.label} &nbsp;
						</g:radioGroup>
						<span class="help-inline">${hasErrors(bean: referralRecordInstance, field: 'program', 'error')}</span>
					</div>
				</div>
			</div>
		</div>

		<hr/>

		<div class="row">
			<div class="col-lg-6">
				<div class="${hasErrors(bean: referralRecordInstance, field: 'note', 'error')} ">
					<label for="note" class="control-label"><g:message code="referralRecord.note.label" default="Supporting information" /></label>
					<div>
						<g:textArea class="form-control"  name="note" value="${referralRecordInstance?.note}" rows="4" cols="40"/>
						<span class="help-inline">${hasErrors(bean: referralRecordInstance, field: 'note', 'error')}</span>
					</div>
				</div>
			</div>
		</div>

		<div id="gelOptions">
			<div class="row">
				<div class="col-lg-6">
					<label class="control-label">100,000 Genomes Project Rare Disease category (enter key word)</label>
					%{--<richui:autoComplete class="form-control" name="targetCategoryName" action="${createLinkTo('dir': 'referralRecord/searchRareDiseaseCondition')}" value="${referralRecordInstance?.targetCategory}" onItemSelect="callCategory(id)"  maxResultsDisplayed="20" minQueryLength="2"/>--}%
					<div>
						<auto-complete
							css="form-control"
							name="targetCategoryName"
							action="${createLink('controller': 'referralRecord','action': 'searchRareDiseaseCondition')}?format=json&query=%QUERY%"
							action-token="%QUERY%"
							item-selected.delegate="callCategory($event.detail.value)"
							max-items="20"
							min-chars="2"
							value="${referralRecordInstance?.targetCategory}"></auto-complete>
					</div>
					<g:hiddenField id ="targetCategory" name ="targetCategory" value="${referralRecordInstance?.targetCategory?.id}"/>
				</div>

				<div class="col-lg-3">
					<label class="control-label">Eligibility statements</label>
					<div>
						<a href="http://ouh.oxnet.nhs.uk/MolecularGenetics/Document%20Library/Rare%20Disease%20Conditions%20Eligibility%20Criteria%20v1%205%201_updated%2021-07-2016%20(1).pdf" target="_blank">Click here</a>
					</div>
				</div>

				<div class="col-lg-3">
					<label class="control-label">Disease Specific Eligibility statements</label>
					<div>
						<g:link url="${assetPath(src: '10960.14.html')}" target="_blank">Click here</g:link>
					</div>
				</div>
			</div>

			<div class="row">

			</div>

			<div class="row">
				<div class="col-lg-6">
					<div class="${hasErrors(bean: referralRecordInstance, field: 'eligibility', 'error')} required">
						<label for="eligibility" class="control-label">Is this patient/family eligible?</label>
						<div>
							%{--<g:select class="form-control" id="eligibility" name="eligibility.id" from="${EligibilityType.list()}" optionKey="id" onchange="eligibilityDetailsOpt()" value="${referralRecordInstance?.eligibility?.id}" noSelection="['':'- Choose -']"/>--}%
							<g:if test="${referralRecordInstance?.eligibility?.id}">
								<g:set var="eligibility" value="${referralRecordInstance?.eligibility?.id}" />
							</g:if>
							<g:else>
								<g:set var="eligibility" value="${EligibilityType.findByEligibilityTypeName('Unknown')?.id}" />
							</g:else>
							<g:radioGroup name="eligibility.id"
										  values="[EligibilityType.findByEligibilityTypeName('Yes')?.id, EligibilityType.findByEligibilityTypeName('No')?.id, EligibilityType.findByEligibilityTypeName('Unknown')?.id]"
										  labels="['Yes', 'No', 'Unknown']"
										  value="${eligibility}"
										  id="eligibility"
										  onclick="eligibilityDetailsOpt()">
								${it.radio}  ${it.label} &nbsp;
							</g:radioGroup>
							<span class="help-inline">${hasErrors(bean: referralRecordInstance, field: 'eligibility', 'error')}</span>
						</div>
					</div>
				</div>

				<div class="col-lg-6" id="eligibilityDetailsOption">
					<div class="${hasErrors(bean: referralRecordInstance, field: 'eligibilityDetails', 'error')} ">
						<label class="control-label">Please provide details</label>
						<div>
							<g:textField class="form-control" id="eligibilityDetails" name="eligibilityDetails" value="${referralRecordInstance?.eligibilityDetails}"/>
							<span class="help-inline">${hasErrors(bean: referralRecordInstance, field: 'eligibilityDetails', 'error')}</span>
						</div>
					</div>
				</div>
			</div>

			<br/>

			<p>The 100,000 Genomes Project has an expedited results policy (which you can read <a href="${resource(dir:'docs',file:'Rare disease programme Expedited Results Policy -v 0.1 11 Dec 2015.pdf')}" target="_blank">here</a>), if you would like to pursue this please contact <a href="mailto:ed.blair@ouh.nhs.uk" target="_blank">Dr Edward Blair</a>, Rare Diseases Clinical Lead for discussion. </p>

			<hr/>

			<h2>100,000 Genomes Project Recruitment</h2>

			<p>The Clinical Genetics Department runs dedicated recruitment clinics for the 100,000 Genomes Project.  At your request, this application can stand as a referral for a Genetic Counsellor to consent the patient or family and collect samples through one of these clinics.  Please select from the following options below:</p>

			<br/>

			<div class="row">
				<div class="col-lg-12">
					<div class="${hasErrors(bean: referralRecordInstance, field: 'consentPatientOrFamily', 'error')} ">
						<g:radioGroup name="consentPatientOrFamily" values="['YES, I would like this application to stand as a referral to Clinical Genetics for consenting and am happy for this patient/family to be contacted immediately','YES, I would like this application to stand as a referral to Clinical Genetics for consenting but request that the patient/family are not contacted until confirmation that this can proceed is received','NO, I do not wish to refer this patient/family to Clinical Genetics for consenting']"
									  value="${referralRecordInstance.consentPatientOrFamily}"
									  labels="['YES, I would like this application to stand as a referral to Clinical Genetics for consenting and am happy for this patient/family to be contacted immediately','YES, I would like this application to stand as a referral to Clinical Genetics for consenting but request that the patient/family are not contacted until confirmation that this can proceed is received','NO, I do not wish to refer this patient/family to Clinical Genetics for consenting']">
							<p>${it.radio} &nbsp; ${it.label}</p>
						</g:radioGroup>
					</div>
				</div>
			</div>
		</div>

		<hr/>

		<sec:ifAnyGranted roles="ROLE_ADMIN">

			<h2><mark>Admin Section</mark></h2>

			<div class="row">
				<div class="col-lg-6">
					<div class="${hasErrors(bean: referralRecordInstance, field: 'assignedTo', 'error')} ">
						<label for="assignedTo" class="control-label"><g:message code="referralRecord.assignedTo.label" default="Assigned To" /></label>
						<div>
							<g:select class="form-control" id="assignedTo" name="assignedTo.id" from="${rdmdt.Clinician.list()}" optionKey="id" value="${referralRecordInstance?.assignedTo?.id}"  noSelection="['':'- Choose -']"/>
							<span class="help-inline">${hasErrors(bean: referralRecordInstance, field: 'assignedTo', 'error')}</span>
						</div>
					</div>
				</div>

				<div class="col-lg-6">
					<div class="${hasErrors(bean: referralRecordInstance, field: 'meetingDate', 'error')} ">
						<label for="meetingDate" class="control-label"><g:message code="referralRecord.meetingDate.label" default="Meeting Date" /></label>
						<div>
							%{--<bs:datePicker name="meetingDate" precision="day"  value="${referralRecordInstance?.meetingDate}" default="none" noSelection="['': '']" />--}%
							<bs-datepicker name="meetingDate" css="form-control" value="${referralRecordInstance?.meetingDate}"></bs-datepicker>
							<span class="help-inline">${hasErrors(bean: referralRecordInstance, field: 'meetingDate', 'error')}</span>
						</div>
					</div>
				</div>
			</div>
		</sec:ifAnyGranted>

		<div class="row" id="reviewDetails">
			<div class="col-lg-6">
				<div class="${hasErrors(bean: referralRecordInstance, field: 'reviewDetails', 'error')} ">
					<label for="reviewDetails" class="control-label"><g:message code="referralRecord.reviewDetails.label" default="Add Review" /></label>
					<div>
						<g:textArea class="form-control" name="reviewDetails" value="${referralRecordInstance?.reviewDetails}" rows="4" cols="40"/>
						<span class="help-inline">${hasErrors(bean: referralRecordInstance, field: 'reviewDetails', 'error')}</span>
					</div>
				</div>
			</div>
		</div>

		<sec:ifAnyGranted roles="ROLE_ADMIN">

			<div class="row">
				<div class="col-lg-6">
					<div class="${hasErrors(bean: referralRecordInstance, field: 'referralStatus', 'error')} required">
						<label for="referralStatus" class="control-label"><g:message code="referralRecord.referralStatus.label" default="Application Status" /><span class="required-indicator">*</span></label>
						<div>
							<g:select class="form-control" id="referralStatus" name="referralStatus.id" from="${rdmdt.ReferralStatus.list().sort{it.id}}" optionKey="id" onchange="statusDetailsOpt()" value="${referralRecordInstance?.referralStatus?.id}" noSelection="['':'- Choose -']"/>
							<span class="help-inline">${hasErrors(bean: referralRecordInstance, field: 'referralStatus', 'error')}</span>
						</div>
					</div>
				</div>

				<div class="col-lg-6" id="approvedIdentityOfFamilyMembersSamplesForSeqOption">
					<div class="${hasErrors(bean: referralRecordInstance, field: 'approvedIdentityOfFamilyMembersSamplesForSeq', 'error')} ">
						<label class="control-label">Number and identity of family members for sequencing</label>
						<div>
							<g:textField class="form-control" id="approvedIdentityOfFamilyMembersSamplesForSeq" name="approvedIdentityOfFamilyMembersSamplesForSeq" value="${referralRecordInstance?.approvedIdentityOfFamilyMembersSamplesForSeq}"/>
							<span class="help-inline">${hasErrors(bean: referralRecordInstance, field: 'approvedIdentityOfFamilyMembersSamplesForSeq', 'error')}</span>
						</div>
					</div>
				</div>

				<div class="col-lg-6" id="approvalDetailsOption">
					<div class="${hasErrors(bean: referralRecordInstance, field: 'approvalDetails', 'error')} ">
						<label class="control-label">Further Details</label>
						<div>
							<g:textField class="form-control" id="approvalDetails" name="approvalDetails" value="${referralRecordInstance?.approvalDetails}"/>
							<span class="help-inline">${hasErrors(bean: referralRecordInstance, field: 'approvalDetails', 'error')}</span>
						</div>
					</div>
				</div>

				<div class="col-lg-6" id="notApprovedDetailsOption">
					<div class="${hasErrors(bean: referralRecordInstance, field: 'notApprovedDetails', 'error')} ">
						<label class="control-label">Please provide further details</label>
						<div>
							<g:textField class="form-control" id="notApprovedDetails" name="notApprovedDetails" value="${referralRecordInstance?.notApprovedDetails}"/>
							<span class="help-inline">${hasErrors(bean: referralRecordInstance, field: 'notApprovedDetails', 'error')}</span>
						</div>
					</div>
				</div>
			</div>

			<div class="row" id="approvedProgramDetails">
				<div class="col-lg-6">
					<div class="${hasErrors(bean: referralRecordInstance, field: 'approvedProgram', 'error')} ">
						<label for="approvedProgram" class="control-label">Approved Program</label>
						<div>
							<g:select class="form-control" id="approvedProgram" name="approvedProgram.id" from="${Program.list()}" optionKey="id" value="${referralRecordInstance?.approvedProgram?.id}" noSelection="['':'- Choose -']"/>
							<span class="help-inline">${hasErrors(bean: referralRecordInstance, field: 'approvedProgram', 'error')}</span>
						</div>
					</div>
				</div>

				<div class="col-lg-6">
					<label class="control-label">Approved Target 100,000 Genomes Project Rare Disease category (enter key word)</label>
					%{--<richui:autoComplete class="form-control" name="approvedTargetCategoryName" action="${createLinkTo('dir': 'referralRecord/searchRareDiseaseCondition')}" value="${referralRecordInstance?.approvedTargetCategory}" onItemSelect="callApprovedTargetCategory(id)"  maxResultsDisplayed="20" minQueryLength="2"/>--}%
					<div>
						<auto-complete
							css="form-control"
							name="approvedTargetCategoryName"
							action="${createLink('controller': 'referralRecord','action': 'searchRareDiseaseCondition')}?format=json&query=%QUERY%"
							action-token="%QUERY%"
							item-selected.delegate="callApprovedTargetCategory($event.detail.value)"
							max-items="20"
							min-chars="2"
							value="${referralRecordInstance?.approvedTargetCategory}"></auto-complete>
					</div>
					<g:hiddenField id ="approvedTargetCategory" name ="approvedTargetCategory" value="${referralRecordInstance?.approvedTargetCategory?.id}"/>
				</div>
			</div>

			<div class="row">
				<div class="col-lg-6">
					<div class="${hasErrors(bean: referralRecordInstance, field: 'adminNote', 'error')} ">
						<label for="reviewDetails" class="control-label"><g:message code="referralRecord.adminNote.label" default="Note" /></label>
						<div>
							<g:textArea class="form-control" name="adminNote" value="${referralRecordInstance?.adminNote}" rows="4" cols="40"/>
							<span class="help-inline">${hasErrors(bean: referralRecordInstance, field: 'adminNote', 'error')}</span>
						</div>
					</div>
				</div>
			</div>

			<hr/>

		</sec:ifAnyGranted>

%{--<g:javascript plugin="jquery" library="jquery" />--}%
<content tag="javascript">
	<script>
		//Make required functions available within Aurelia execution context
		var aureliaViewModel = {
			callClinician: callClinician,
			callCorrespondingClinician: callCorrespondingClinician,
			callCategory: callCategory,
			callApprovedTargetCategory: callApprovedTargetCategory
		}

		function callClinician(clinician){
			document.getElementById('clinician').value = clinician;
		}

		function callCorrespondingClinician(correspondingClinician){
			document.getElementById('correspondingClinician').value = correspondingClinician;
		}

		function callCoApplicant1(coapplican){
			document.getElementById('coapplicant1').value = coapplican;
		}

		function callCoApplicant2(coapplican){
			document.getElementById('coapplicant2').value = coapplican;
		}

		function callCoApplicant3(coapplican){
			document.getElementById('coapplicant3').value = coapplican;
		}

		function callCoApplicant4(coapplican){
			document.getElementById('coapplicant4').value = coapplican;
		}

		function callCoApplicant5(coapplican){
			document.getElementById('coapplicant5').value = coapplican;
		}

		hideClinicalDetails();
		var count = 1;
		function hideClinicalDetails(){
			if ($("#cDetails1").val() == ""){
				$("#clinicalDetails1").hide();
			}
			if ($("#cDetails2").val() == ""){
				$("#clinicalDetails2").hide();
			}
			if ($("#cDetails3").val() == ""){
				$("#clinicalDetails3").hide();
			}
			if ($("#cDetails4").val() == ""){
				$("#clinicalDetails4").hide();
			}
			if ($("#cDetails5").val() == ""){
				$("#clinicalDetails5").hide();
			}
			if ($("#cDetails6").val() == ""){
				$("#clinicalDetails6").hide();
			}
		}

		function addClinicalDetails(){
			$("#clinicalDetails"+count).show();
			count++;
			if (count > 6){
				$("#addClinicalDetailsButton").hide();
			}
		}

		hideCoApplicants();
		var countCoApplicants = 1;
		function hideCoApplicants(){
			if ($("#coapplicant1").val() == ""){
				$("#coapplicantDetails1").hide();
			}
			if ($("#coapplicant2").val() == ""){
				$("#coapplicantDetails2").hide();
			}
			if ($("#coapplicant3").val() == ""){
				$("#coapplicantDetails3").hide();
			}
			if ($("#coapplicant4").val() == ""){
				$("#coapplicantDetails4").hide();
			}
			if ($("#coapplicant5").val() == ""){
				$("#coapplicantDetails5").hide();
			}
		}

		function addCoApplicants(){
			$("#coapplicantDetails"+countCoApplicants).show();
			countCoApplicants++;
			if (countCoApplicants > 5){
				$("#addCoapplicantDetailsButton").hide();
			}
		}

		hideAttachedEvidence();
		var countAttachedEvidence = 2;
		function hideAttachedEvidence(){
			if ($("#attachedEvidenceType2").val() == ""){
				$("#attachedEvidenceTypeDetails2").hide();
			}
			if ($("#attachedEvidenceType3").val() == ""){
				$("#attachedEvidenceTypeDetails3").hide();
			}
			if ($("#attachedEvidenceType4").val() == ""){
				$("#attachedEvidenceTypeDetails4").hide();
			}
			if ($("#attachedEvidenceType5").val() == ""){
				$("#attachedEvidenceTypeDetails5").hide();
			}
		}

		function addAttachedEvidence(){
			$("#attachedEvidenceTypeDetails"+countAttachedEvidence).show();
			countAttachedEvidence++;
			if (countAttachedEvidence > 5){
				$("#addAttachedEvidenceButtonDetails").hide();
			}
		}

		function callCategory(targetCategory){
			document.getElementById('targetCategory').value = targetCategory;
		}

		function callApprovedTargetCategory(approvedTargetCategory){
			document.getElementById('approvedTargetCategory').value = approvedTargetCategory;
		}

		otherEthnicityProbandOpt();
		function otherEthnicityProbandOpt(){
			var ethnicityProband = $("#ethnicityProband").val();
			if (ethnicityProband && ethnicityProband.length > 0 && ethnicityProband == '${Ethnicity.findByEthnicityName('Other')?.id}'){
				$("#otherEthnicityOption").show();
			}else{
				$("#otherEthnicityOption").hide();
				$("#otherEthnicityProband").val("");
			}
		}

		otherEthnicityMotherOpt();
		function otherEthnicityMotherOpt(){
			var ethnicityMother = $("#ethnicityMother").val();
			if (ethnicityMother && ethnicityMother.length > 0 && ethnicityMother == '${Ethnicity.findByEthnicityName('Other')?.id}'){
				$("#otherEthnicityMotherOption").show();
			}else{
				$("#otherEthnicityMotherOption").hide();
				$("#otherEthnicityMother").val("");
			}
		}

		otherEthnicityFatherOpt();
		function otherEthnicityFatherOpt(){
			var ethnicityFather = $("#ethnicityFather").val();
			if (ethnicityFather && ethnicityFather.length > 0 && ethnicityFather == '${Ethnicity.findByEthnicityName('Other')?.id}'){
				$("#otherEthnicityFatherOption").show();
			}else{
				$("#otherEthnicityFatherOption").hide();
				$("#otherEthnicityFather").val("");
			}
		}

		function showArrayCGHDetails(){
			$("#arrayCGHDetailsOption").show();
		}

		hideArrayCGHDetails();
		function hideArrayCGHDetails(){
			if ($('input:radio[name=arrayCGH]:checked').val() != 'true'){
				$("#arrayCGHDetailsOption").hide();
				$("#arrayCGHDetails").val("");
			}
		}

		otherFamilyMembersAffectedDetailsOpt();
		function otherFamilyMembersAffectedDetailsOpt(){
			var otherFamilyMembersAffected = $('input:radio[id=otherFamilyMembersAffected]:checked').val();
			if (otherFamilyMembersAffected && otherFamilyMembersAffected.length > 0 && otherFamilyMembersAffected == '${FamilyMembersAffectedType.findByFamilyMembersAffectedTypeName('Yes')?.id}'){
				$("#otherFamilyMembersAffectedDetailsOption").show();
			}else{
				$("#otherFamilyMembersAffectedDetailsOption").hide();
				$("#otherFamilyMembersAffectedDetails").val("");
			}
		}

		gelProjectOpt();
		function gelProjectOpt(){
			var program = $('input:radio[id=program]:checked').val();
			if (program && program.length > 0 && (program == '${Program.findByName('HICF2 Whole Genome Sequencing Programme')?.id}' || program == '${Program.findByName('Other')?.id}')){
				$("#gelOptions").hide();
			}else{
				$("#gelOptions").show();
			}
		}

		consanguinityEvidenceDetailsOpt();
		function consanguinityEvidenceDetailsOpt(){
			var consanguinityEvidence = $('input:radio[id=consanguinityEvidence]:checked').val();
			if (consanguinityEvidence && consanguinityEvidence.length > 0 && consanguinityEvidence == '${Consanguinity.findByConsanguinityEvidence('Yes')?.id}'){
				$("#consanguinityEvidenceDetailsOption").show();
			}else{
				$("#consanguinityEvidenceDetailsOption").hide();
				$("#consanguinityEvidenceDetails").val("");
			}
		}

		penetranceDetailsOpt();
		function penetranceDetailsOpt(){
			var penetrance = $('input:radio[id=penetrance]:checked').val();
			if (penetrance && penetrance.length > 0 && penetrance == '${Penetrance.findByPenetranceName('Yes')?.id}'){
				$("#penetranceDetailsOption").show();
			}else{
				$("#penetranceDetailsOption").hide();
				$("#penetranceDetails").val("");
			}
		}

		function showIsAnySampleFromDeceasedIndividualsDetailsOpt(){
			$("#isAnySampleFromDeceasedIndividualsDetailsOption").show();
		}

		hideIsAnySampleFromDeceasedIndividualsDetailsOpt();
		function hideIsAnySampleFromDeceasedIndividualsDetailsOpt(){
			if ($('input:radio[name=isAnySampleFromDeceasedIndividuals]:checked').val() != 'true'){
				$("#isAnySampleFromDeceasedIndividualsDetailsOption").hide();
				$("#isAnySampleFromDeceasedIndividualsDetails").val("");
			}
		}

		function showAnyIndividualsForSeqOutOfAreaDetailsOpt(){
			$("#anyIndividualsForSeqOutOfAreaDetailsOption").show();
		}

		hideAnyIndividualsForSeqOutOfAreaDetailsOpt();
		function hideAnyIndividualsForSeqOutOfAreaDetailsOpt(){
			if ($('input:radio[name=anyIndividualsForSeqOutOfArea]:checked').val() != 'true'){
				$("#anyIndividualsForSeqOutOfAreaDetailsOption").hide();
				$("#anyIndividualsForSeqOutOfAreaDetails").val("");
			}
		}

		eligibilityDetailsOpt();
		function eligibilityDetailsOpt(){
			var eligibility = $('input:radio[id=eligibility]:checked').val();
			if (eligibility && eligibility.length > 0 && (eligibility == '${EligibilityType.findByEligibilityTypeName('No')?.id}' || eligibility == '${EligibilityType.findByEligibilityTypeName('Unknown')?.id}')){
				$("#eligibilityDetailsOption").show();
			}else{
				$("#eligibilityDetailsOption").hide();
				$("#eligibilityDetails").val("");
			}
		}

		statusDetailsOpt();
		function statusDetailsOpt(){
			var referralStatus = $("#referralStatus").val();
			if  (referralStatus && referralStatus.length > 0 && referralStatus == '${ReferralStatus.findByReferralStatusName('Approved')?.id}'){
				$("#approvalDetailsOption").show();
				$("#approvedProgramDetails").show();
				$("#approvedIdentityOfFamilyMembersSamplesForSeqOption").show()
				$("#notApprovedDetailsOption").hide();
				$("#reviewDetails").hide();
				$("#notApprovedDetails").val("");
			}else if (referralStatus && referralStatus.length > 0 && referralStatus == '${ReferralStatus.findByReferralStatusName('Not Approved')?.id}'){
				$("#approvalDetailsOption").hide();
				$("#approvalDetails").val("");
				$("#approvedProgramDetails").hide();
				$("#approvedProgram").val("");
				$("#approvedTargetCategory").val("");
				$("#approvedTargetCategoryName").val("");
				$("#reviewDetails").hide();
				$("#notApprovedDetailsOption").show();
				$("#approvedIdentityOfFamilyMembersSamplesForSeqOption").hide()
			} else if (referralStatus && referralStatus.length > 0 && (referralStatus == '${ReferralStatus.findByReferralStatusName('Review Submitted')?.id}' || referralStatus == '${ReferralStatus.findByReferralStatusName('Review Requested')?.id}')){
				$("#conditionalApprovalDetailsOption").hide();
				$("#conditionalApprovalDetails").val("");
				$("#approvalDetailsOption").hide();
				$("#approvalDetails").val("");
				$("#approvedProgramDetails").hide();
				$("#approvedProgram").val("");
				$("#approvedTargetCategory").val("");
				$("#approvedTargetCategoryName").val("");
				$("#notApprovedDetailsOption").hide();
				$("#reviewDetails").show();
				$("#approvedIdentityOfFamilyMembersSamplesForSeqOption").hide()
			}else {
				$("#approvalDetailsOption").hide();
				$("#approvalDetails").val("");
				$("#notApprovedDetailsOption").hide();
				$("#notApprovedDetails").val("");
				$("#approvedProgram").val("");
				$("#approvedTargetCategory").val("");
				$("#approvedTargetCategoryName").val("");
				$("#approvedProgramDetails").hide();
				$("#reviewDetails").hide();
				$("#approvedIdentityOfFamilyMembersSamplesForSeqOption").hide()
			}
		}

		$(document).on('keypress', ':input:not(textarea):not([type=submit])', function (e) {
			if (e.which == 13) e.preventDefault();
		});

		window.onbeforeunload = function() {
			return "";
		};

		function attachedDocument1(){
			var attachedEvidenceType1 = $("#attachedEvidenceType1").val();
			if (attachedEvidenceType1 == ""){
				alert('Please select file type')
			}
		}

		function attachedDocument2(){
			var attachedEvidenceType2 = $("#attachedEvidenceType2").val();
			if (attachedEvidenceType2 == ""){
				alert('Please select file type')
			}
		}

		function attachedDocument3(){
			var attachedEvidenceType3 = $("#attachedEvidenceType3").val();
			if (attachedEvidenceType3 == ""){
				alert('Please select file type')
			}
		}

		function attachedDocument4(){
			var attachedEvidenceType4 = $("#attachedEvidenceType4").val();
			if (attachedEvidenceType4 == ""){
				alert('Please select file type')
			}
		}

		function attachedDocument5(){
			var attachedEvidenceType5 = $("#attachedEvidenceType5").val();
			if (attachedEvidenceType5 == ""){
				alert('Please select file type')
			}
		}

	</script>
</content>
