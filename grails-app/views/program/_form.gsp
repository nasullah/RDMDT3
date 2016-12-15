<%@ page import="rdmdt.Program" %>



			<div class="row">
				<div class="col-lg-6">
					<div class="${hasErrors(bean: programInstance, field: 'name', 'error')} ">
						<label for="name" class="control-label"><g:message code="program.name.label" default="Name" /><span class="required-indicator">*</span></label>
						<div>
							<g:textField class="form-control" name="name" value="${programInstance?.name}" required=""/>
							<span class="help-inline">${hasErrors(bean: programInstance, field: 'name', 'error')}</span>
						</div>
					</div>
				</div>

				<div class="col-lg-6">
					<div class="${hasErrors(bean: programInstance, field: 'description', 'error')} ">
						<label for="description" class="control-label"><g:message code="program.description.label" default="Description" /><span class="required-indicator">*</span></label>
						<div>
							<g:textField class="form-control" name="description" value="${programInstance?.description}" required=""/>
							<span class="help-inline">${hasErrors(bean: programInstance, field: 'description', 'error')}</span>
						</div>
					</div>
				</div>
			</div>

