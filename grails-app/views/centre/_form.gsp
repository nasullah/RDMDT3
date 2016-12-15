<%@ page import="rdmdt.Centre" %>



			<div class="row">
				<div class="col-lg-6">
					<div class="${hasErrors(bean: centreInstance, field: 'centreName', 'error')} ">
						<label for="centreName" class="control-label"><g:message code="centre.centreName.label" default="Centre Name" /></label>
						<div>
							<g:textField class="form-control" name="centreName" value="${centreInstance?.centreName}"/>
							<span class="help-inline">${hasErrors(bean: centreInstance, field: 'centreName', 'error')}</span>
						</div>
					</div>
				</div>
			</div>

