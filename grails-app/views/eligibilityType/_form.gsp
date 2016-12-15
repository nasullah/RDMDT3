<%@ page import="rdmdt.EligibilityType" %>



			<div class="row">
				<div class="col-lg-6">
					<div class="${hasErrors(bean: eligibilityTypeInstance, field: 'eligibilityTypeName', 'error')} ">
						<label for="eligibilityTypeName" class="control-label"><g:message code="eligibilityType.eligibilityTypeName.label" default="Eligibility Type Name" /></label>
						<div>
							<g:textField class="form-control" name="eligibilityTypeName" value="${eligibilityTypeInstance?.eligibilityTypeName}"/>
							<span class="help-inline">${hasErrors(bean: eligibilityTypeInstance, field: 'eligibilityTypeName', 'error')}</span>
						</div>
					</div>
				</div>
			</div>

