<%@ page import="rdmdt.FamilyHistoryType" %>



			<div class="row">
				<div class="col-lg-6">
					<div class="${hasErrors(bean: familyHistoryTypeInstance, field: 'familyHistoryTypeName', 'error')} ">
						<label for="familyHistoryTypeName" class="control-label"><g:message code="familyHistoryType.familyHistoryTypeName.label" default="Family History Type Name" /></label>
						<div>
							<g:textField class="form-control" name="familyHistoryTypeName" value="${familyHistoryTypeInstance?.familyHistoryTypeName}"/>
							<span class="help-inline">${hasErrors(bean: familyHistoryTypeInstance, field: 'familyHistoryTypeName', 'error')}</span>
						</div>
					</div>
				</div>
			</div>

