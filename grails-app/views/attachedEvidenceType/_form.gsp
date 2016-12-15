<%@ page import="rdmdt.AttachedEvidenceType" %>


		<div class="row">
			<div class="col-lg-6">
				<div class="${hasErrors(bean: attachedEvidenceTypeInstance, field: 'attachedEvidenceTypeName', 'error')} ">
					<label for="attachedEvidenceTypeName" class="control-label"><g:message code="attachedEvidenceType.attachedEvidenceTypeName.label" default="Attached Evidence Type Name" /></label>
					<div>
						<g:textField class="form-control" name="attachedEvidenceTypeName" value="${attachedEvidenceTypeInstance?.attachedEvidenceTypeName}" required=""/>
						<span class="help-inline">${hasErrors(bean: attachedEvidenceTypeInstance, field: 'attachedEvidenceTypeName', 'error')}</span>
					</div>
				</div>
			</div>
		</div>


