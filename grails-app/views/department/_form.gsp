<%@ page import="rdmdt.Department" %>



		<div class="row">
			<div class="col-lg-6">
				<div class="${hasErrors(bean: departmentInstance, field: 'departmentName', 'error')} ">
					<label for="departmentName" class="control-label"><g:message code="department.departmentName.label" default="Department Name" /></label>
					<div>
						<g:textField class="form-control" name="departmentName" value="${departmentInstance?.departmentName}"/>
						<span class="help-inline">${hasErrors(bean: departmentInstance, field: 'departmentName', 'error')}</span>
					</div>
				</div>
			</div>
		</div>

