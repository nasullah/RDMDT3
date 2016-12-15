<%@ page import="rdmdt.AgeUnit" %>



				<div class="row">
					<div class="col-lg-6">
						<div class="${hasErrors(bean: ageUnitInstance, field: 'ageUnitName', 'error')} ">
							<label for="ageUnitName" class="control-label"><g:message code="ageUnit.ageUnitName.label" default="Age Unit Name" /></label>
							<div>
								<g:textField class="form-control" name="ageUnitName" value="${ageUnitInstance?.ageUnitName}"/>
								<span class="help-inline">${hasErrors(bean: ageUnitInstance, field: 'ageUnitName', 'error')}</span>
							</div>
						</div>
					</div>
				</div>

