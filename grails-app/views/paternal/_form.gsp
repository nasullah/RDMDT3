<%@ page import="rdmdt.Paternal" %>



			<div class="${hasErrors(bean: paternalInstance, field: 'breastAndOrOvarianCancer', 'error')} ">
				<label for="breastAndOrOvarianCancer" class="control-label"><g:message code="paternal.breastAndOrOvarianCancer.label" default="Breast And Or Ovarian Cancer" /></label>
				<div>
					%{--<bs:checkBox name="breastAndOrOvarianCancer" />--}%
					<bs-checkbox name="breastAndOrOvarianCancer"></bs-checkbox>
					<span class="help-inline">${hasErrors(bean: paternalInstance, field: 'breastAndOrOvarianCancer', 'error')}</span>
				</div>
			</div>

			<div class="${hasErrors(bean: paternalInstance, field: 'colorectalCancer', 'error')} ">
				<label for="colorectalCancer" class="control-label"><g:message code="paternal.colorectalCancer.label" default="Colorectal Cancer" /></label>
				<div>
					%{--<bs:checkBox name="colorectalCancer" />--}%
					<bs-checkbox name="colorectalCancer"></bs-checkbox>
					<span class="help-inline">${hasErrors(bean: paternalInstance, field: 'colorectalCancer', 'error')}</span>
				</div>
			</div>

			<div class="${hasErrors(bean: paternalInstance, field: 'ischaemicHeartDiseaseOrStroke', 'error')} ">
				<label for="ischaemicHeartDiseaseOrStroke" class="control-label"><g:message code="paternal.ischaemicHeartDiseaseOrStroke.label" default="Ischaemic Heart Disease Or Stroke" /></label>
				<div>
					%{--<bs:checkBox name="ischaemicHeartDiseaseOrStroke"  />--}%
					<bs-checkbox name="ischaemicHeartDiseaseOrStroke"></bs-checkbox>
					<span class="help-inline">${hasErrors(bean: paternalInstance, field: 'ischaemicHeartDiseaseOrStroke', 'error')}</span>
				</div>
			</div>

			<div class="${hasErrors(bean: paternalInstance, field: 'endocrineTumours', 'error')} ">
				<label for="endocrineTumours" class="control-label"><g:message code="paternal.endocrineTumours.label" default="Endocrine Tumours" /></label>
				<div>
					%{--<bs:checkBox name="endocrineTumours"  />--}%
					<bs-checkbox name="endocrineTumours"></bs-checkbox>
					<span class="help-inline">${hasErrors(bean: paternalInstance, field: 'endocrineTumours', 'error')}</span>
				</div>
			</div>

			<div class="${hasErrors(bean: paternalInstance, field: 'referralRecord', 'error')} required">
				<label for="referralRecord" class="control-label"><g:message code="paternal.referralRecord.label" default="Referral Record" /><span class="required-indicator">*</span></label>
				<div>
					<g:select class="form-control" id="referralRecord" name="referralRecord.id" from="${rdmdt.ReferralRecord.list()}" optionKey="id" required="" value="${paternalInstance?.referralRecord?.id}" class="many-to-one"/>
					<span class="help-inline">${hasErrors(bean: paternalInstance, field: 'referralRecord', 'error')}</span>
				</div>
			</div>

