<%@ page import="rdmdt.Ethnicity" %>


		<div class="row">
			<div class="col-lg-6">
				<div class="${hasErrors(bean: ethnicityInstance, field: 'ethnicityName', 'error')} ">
					<label for="ethnicityName" class="control-label"><g:message code="ethnicity.ethnicityName.label" default="Ethnicity Name" /></label>
					<div>
						<g:textField class="form-control" name="ethnicityName" value="${ethnicityInstance?.ethnicityName}" required=""/>
						<span class="help-inline">${hasErrors(bean: ethnicityInstance, field: 'ethnicityName', 'error')}</span>
					</div>
				</div>
			</div>
		</div>



