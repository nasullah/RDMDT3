<%@ page import="rdmdt.Patient" %>



			<div class="${hasErrors(bean: patientInstance, field: 'givenName', 'error')} ">
				<label for="givenName" class="control-label"><g:message code="patient.givenName.label" default="Proband's given name" /></label>
				<div>
					<g:textField class="form-control" name="givenName" value="${patientInstance?.givenName}"/>
					<span class="help-inline">${hasErrors(bean: patientInstance, field: 'givenName', 'error')}</span>
				</div>
			</div>

			<div class="${hasErrors(bean: patientInstance, field: 'familyName', 'error')} ">
				<label for="familyName" class="control-label"><g:message code="patient.familyName.label" default="Proband's family name" /></label>
				<div>
					<g:textField class="form-control" name="familyName" value="${patientInstance?.familyName}"/>
					<span class="help-inline">${hasErrors(bean: patientInstance, field: 'familyName', 'error')}</span>
				</div>
			</div>

			<div class="${hasErrors(bean: patientInstance, field: 'nhsNumber', 'error')} ">
				<label for="nhsNumber" class="control-label"><g:message code="patient.nhsNumber.label" default="Nhs Number" /></label>
				<div>
					<g:textField class="form-control" name="nhsNumber" value="${patientInstance?.nhsNumber}"/>
					<span class="help-inline">${hasErrors(bean: patientInstance, field: 'nhsNumber', 'error')}</span>
				</div>
			</div>

			<div class="${hasErrors(bean: patientInstance, field: 'mrn', 'error')} ">
				<label for="mrn" class="control-label"><g:message code="patient.mrn.label" default="Mrn" /></label>
				<div>
					<g:textField class="form-control" name="mrn" value="${patientInstance?.mrn}"/>
					<span class="help-inline">${hasErrors(bean: patientInstance, field: 'mrn', 'error')}</span>
				</div>
			</div>

			<div class="${hasErrors(bean: patientInstance, field: 'dateOfBirth', 'error')} ">
				<label for="dateOfBirth" class="control-label"><g:message code="patient.dateOfBirth.label" default="Date Of Birth" /></label>
				<div>
					%{--<bs:datePicker name="dateOfBirth" precision="day"  value="${patientInstance?.dateOfBirth}" default="none" noSelection="['': '']" />--}%
					<bs-datepicker name="dateOfBirth" css="form-control" value="${referralRecordInstance?.dateOfBirth}"></bs-datepicker>
					<span class="help-inline">${hasErrors(bean: patientInstance, field: 'dateOfBirth', 'error')}</span>
				</div>
			</div>

			<div class="${hasErrors(bean: patientInstance, field: 'gender', 'error')} ">
				<label for="gender" class="control-label"><g:message code="patient.gender.label" default="Gender" /></label>
				<div>
					<g:select class="form-control" id="gender" name="gender.id" from="${rdmdt.Gender.list()}" optionKey="id" value="${patientInstance?.gender?.id}" class="many-to-one" noSelection="['null': '']"/>
					<span class="help-inline">${hasErrors(bean: patientInstance, field: 'gender', 'error')}</span>
				</div>
			</div>

			<div class="${hasErrors(bean: patientInstance, field: 'ethnicity', 'error')} ">
				<label for="ethnicity" class="control-label"><g:message code="patient.ethnicity.label" default="Ethnicity" /></label>
				<div>
					<g:select class="form-control" id="ethnicity" name="ethnicity.id" from="${rdmdt.Ethnicity.list()}" optionKey="id" value="${patientInstance?.ethnicity?.id}" class="many-to-one" noSelection="['null': '']"/>
					<span class="help-inline">${hasErrors(bean: patientInstance, field: 'ethnicity', 'error')}</span>
				</div>
			</div>

			<div class="${hasErrors(bean: patientInstance, field: 'otherEthnicity', 'error')} ">
				<label for="otherEthnicity" class="control-label"><g:message code="patient.otherEthnicity.label" default="Other Ethnicity" /></label>
				<div>
					<g:textField class="form-control" name="otherEthnicity" value="${patientInstance?.otherEthnicity}"/>
					<span class="help-inline">${hasErrors(bean: patientInstance, field: 'otherEthnicity', 'error')}</span>
				</div>
			</div>

			<div class="${hasErrors(bean: patientInstance, field: 'availableForOAR', 'error')} ">
				<label for="availableForOAR" class="control-label"><g:message code="patient.availableForOAR.label" default="Available For OAR" /></label>
				<div>
					%{--<bs:checkBox name="availableForOAR" value="${patientInstance?.availableForOAR}" />--}%
					<bs-checkbox name="availableForOAR" checked="${patientInstance?.availableForOAR}"></bs-checkbox>
					<span class="help-inline">${hasErrors(bean: patientInstance, field: 'availableForOAR', 'error')}</span>
				</div>
			</div>

			<div class="${hasErrors(bean: patientInstance, field: 'isProband', 'error')} ">
				<label for="isProband" class="control-label"><g:message code="patient.isProband.label" default="Is Proband" /></label>
				<div>
					%{--<bs:checkBox name="isProband" value="${patientInstance?.isProband}" />--}%
					<bs-checkbox name="isProband" checked="${patientInstance?.isProband}"></bs-checkbox>
					<span class="help-inline">${hasErrors(bean: patientInstance, field: 'isProband', 'error')}</span>
				</div>
			</div>

			<div class="${hasErrors(bean: patientInstance, field: 'age', 'error')} ">
				<label for="age" class="control-label"><g:message code="patient.ege.label" default="Ege" /></label>
				<div>
					<g:field class="form-control" name="age" type="number" value="${patientInstance.age}"/>
					<span class="help-inline">${hasErrors(bean: patientInstance, field: 'age', 'error')}</span>
				</div>
			</div>

			<div class="${hasErrors(bean: patientInstance, field: 'referralRecord', 'error')} required">
				<label for="referralRecord" class="control-label"><g:message code="patient.referralRecord.label" default="Referral Record" /><span class="required-indicator">*</span></label>
				<div>
					<g:select class="form-control" id="referralRecord" name="referralRecord.id" from="${rdmdt.ReferralRecord.list()}" optionKey="id"  value="${patientInstance?.referralRecord?.id}" class="many-to-one"/>
					<span class="help-inline">${hasErrors(bean: patientInstance, field: 'referralRecord', 'error')}</span>
				</div>
			</div>

			<div class="${hasErrors(bean: patientInstance, field: 'relationshipTo', 'error')} ">
				<label for="relationshipTo" class="control-label"><g:message code="patient.relationshipTo.label" default="Relationship To" /></label>
				<div>
					
					<span class="help-inline">${hasErrors(bean: patientInstance, field: 'relationshipTo', 'error')}</span>
				</div>
			</div>

