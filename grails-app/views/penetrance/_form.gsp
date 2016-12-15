<%@ page import="rdmdt.Penetrance" %>



		<div class="row">
			<div class="col-lg-6">
				<div class="${hasErrors(bean: penetranceInstance, field: 'penetranceName', 'error')} ">
					<label for="penetranceName" class="control-label"><g:message code="penetrance.penetranceName.label" default="Penetrance Name" /></label>
					<div>
						<g:textField class="form-control" name="penetranceName" value="${penetranceInstance?.penetranceName}"/>
						<span class="help-inline">${hasErrors(bean: penetranceInstance, field: 'penetranceName', 'error')}</span>
					</div>
				</div>
			</div>
		</div>

