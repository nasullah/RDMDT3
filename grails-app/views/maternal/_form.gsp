<%@ page import="rdmdt.Maternal" %>



			<div class="${hasErrors(bean: maternalInstance, field: 'breastAndOrOvarianCancer', 'error')} ">
				<label for="breastAndOrOvarianCancer" class="control-label"><g:message code="maternal.breastAndOrOvarianCancer.label" default="Breast And Or Ovarian Cancer" /></label>
				<div>
					%{--<bs:checkBox name="breastAndOrOvarianCancer" value="${maternalInstance?.breastAndOrOvarianCancer}" />--}%
					<bs-checkbox name="breastAndOrOvarianCancer" checked="${maternalInstance?.breastAndOrOvarianCancer}"></bs-checkbox>
					<span class="help-inline">${hasErrors(bean: maternalInstance, field: 'breastAndOrOvarianCancer', 'error')}</span>
				</div>
			</div>

			<div class="${hasErrors(bean: maternalInstance, field: 'colorectalCancer', 'error')} ">
				<label for="colorectalCancer" class="control-label"><g:message code="maternal.colorectalCancer.label" default="Colorectal Cancer" /></label>
				<div>
					%{--<bs:checkBox name="colorectalCancer" value="${maternalInstance?.colorectalCancer}" />--}%
					<bs-checkbox name="colorectalCancer" checked="${maternalInstance?.colorectalCancer}"></bs-checkbox>
					<span class="help-inline">${hasErrors(bean: maternalInstance, field: 'colorectalCancer', 'error')}</span>
				</div>
			</div>

			<div class="${hasErrors(bean: maternalInstance, field: 'ischaemicHeartDiseaseOrStroke', 'error')} ">
				<label for="ischaemicHeartDiseaseOrStroke" class="control-label"><g:message code="maternal.ischaemicHeartDiseaseOrStroke.label" default="Ischaemic Heart Disease Or Stroke" /></label>
				<div>
					%{--<bs:checkBox name="ischaemicHeartDiseaseOrStroke" value="${maternalInstance?.ischaemicHeartDiseaseOrStroke}" />--}%
					<bs-checkbox name="ischaemicHeartDiseaseOrStroke" checked="${maternalInstance?.ischaemicHeartDiseaseOrStroke}"></bs-checkbox>
					<span class="help-inline">${hasErrors(bean: maternalInstance, field: 'ischaemicHeartDiseaseOrStroke', 'error')}</span>
				</div>
			</div>

			<div class="${hasErrors(bean: maternalInstance, field: 'endocrineTumours', 'error')} ">
				<label for="endocrineTumours" class="control-label"><g:message code="maternal.endocrineTumours.label" default="Endocrine Tumours" /></label>
				<div>
					%{--<bs:checkBox name="endocrineTumours" value="${maternalInstance?.endocrineTumours}" />--}%
					<bs-checkbox name="endocrineTumours" checked="${maternalInstance?.endocrineTumours}"></bs-checkbox>
					<span class="help-inline">${hasErrors(bean: maternalInstance, field: 'endocrineTumours', 'error')}</span>
				</div>
			</div>

			<div class="${hasErrors(bean: maternalInstance, field: 'referralRecord', 'error')} required">
				<label for="referralRecord" class="control-label"><g:message code="maternal.referralRecord.label" default="Referral Record" /><span class="required-indicator">*</span></label>
				<div>
					<g:select class="form-control" id="referralRecord" name="referralRecord.id" from="${rdmdt.ReferralRecord.list()}" optionKey="id" required="" value="${maternalInstance?.referralRecord?.id}" class="many-to-one"/>
					<span class="help-inline">${hasErrors(bean: maternalInstance, field: 'referralRecord', 'error')}</span>
				</div>
			</div>

