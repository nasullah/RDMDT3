<%@ page import="rdmdt.Relationship" %>



			<div class="${hasErrors(bean: relationshipInstance, field: 'relationshipType', 'error')} required">
				<label for="relationshipType" class="control-label"><g:message code="relationship.relationshipType.label" default="Relationship Type" /><span class="required-indicator">*</span></label>
				<div>
					<g:select class="form-control" id="relationshipType" name="relationshipType.id" from="${rdmdt.RelationshipType.list()}" optionKey="id" required="" value="${relationshipInstance?.relationshipType?.id}" class="many-to-one"/>
					<span class="help-inline">${hasErrors(bean: relationshipInstance, field: 'relationshipType', 'error')}</span>
				</div>
			</div>

			<div class="${hasErrors(bean: relationshipInstance, field: 'relatedFrom', 'error')} ">
				<label for="relatedFrom" class="control-label"><g:message code="relationship.relatedFrom.label" default="Related From" /></label>
				<div>
					<g:select class="form-control" name="relatedFrom" from="${rdmdt.Patient.list()}" multiple="multiple" optionKey="id" size="5" value="${relationshipInstance?.relatedFrom*.id}" class="many-to-many"/>
					<span class="help-inline">${hasErrors(bean: relationshipInstance, field: 'relatedFrom', 'error')}</span>
				</div>
			</div>

