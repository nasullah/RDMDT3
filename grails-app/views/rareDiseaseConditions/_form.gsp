<%@ page import="rdmdt.RareDiseaseConditions" %>



			<div class="${hasErrors(bean: rareDiseaseConditionsInstance, field: 'originalId', 'error')} required">
				<label for="originalId" class="control-label"><g:message code="rareDiseaseConditions.originalId.label" default="Original Id" /><span class="required-indicator">*</span></label>
				<div>
					<g:field class="form-control" name="originalId" type="number" value="${rareDiseaseConditionsInstance?.originalId}" required=""/>
					<span class="help-inline">${hasErrors(bean: rareDiseaseConditionsInstance, field: 'originalId', 'error')}</span>
				</div>
			</div>

			<div class="${hasErrors(bean: rareDiseaseConditionsInstance, field: 'diseaseGroup', 'error')} ">
				<label for="diseaseGroup" class="control-label"><g:message code="rareDiseaseConditions.diseaseGroup.label" default="Disease Group" /></label>
				<div>
					<g:textField class="form-control" name="diseaseGroup" value="${rareDiseaseConditionsInstance?.diseaseGroup}"/>
					<span class="help-inline">${hasErrors(bean: rareDiseaseConditionsInstance, field: 'diseaseGroup', 'error')}</span>
				</div>
			</div>

			<div class="${hasErrors(bean: rareDiseaseConditionsInstance, field: 'diseaseSubgroup', 'error')} ">
				<label for="diseaseSubgroup" class="control-label"><g:message code="rareDiseaseConditions.diseaseSubgroup.label" default="Disease Subgroup" /></label>
				<div>
					<g:textField class="form-control" name="diseaseSubgroup" value="${rareDiseaseConditionsInstance?.diseaseSubgroup}"/>
					<span class="help-inline">${hasErrors(bean: rareDiseaseConditionsInstance, field: 'diseaseSubgroup', 'error')}</span>
				</div>
			</div>

			<div class="${hasErrors(bean: rareDiseaseConditionsInstance, field: 'diseaseName', 'error')} ">
				<label for="diseaseName" class="control-label"><g:message code="rareDiseaseConditions.diseaseName.label" default="Disease Name" /></label>
				<div>
					<g:textField class="form-control" name="diseaseName" value="${rareDiseaseConditionsInstance?.diseaseName}"/>
					<span class="help-inline">${hasErrors(bean: rareDiseaseConditionsInstance, field: 'diseaseName', 'error')}</span>
				</div>
			</div>

