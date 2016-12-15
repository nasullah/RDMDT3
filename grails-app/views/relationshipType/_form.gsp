<%@ page import="rdmdt.RelationshipType" %>


		<div class="row">
			<div class="col-lg-6">
				<div class="${hasErrors(bean: relationshipTypeInstance, field: 'relationshipTypeName', 'error')} ">
					<label for="relationshipTypeName" class="control-label"><g:message code="relationshipType.relationshipTypeName.label" default="Relationship Type Name" /></label>
					<div>
						<g:textField class="form-control" name="relationshipTypeName" value="${relationshipTypeInstance?.relationshipTypeName}"/>
						<span class="help-inline">${hasErrors(bean: relationshipTypeInstance, field: 'relationshipTypeName', 'error')}</span>
					</div>
				</div>
			</div>

			<div class="col-lg-6">
				<div class="${hasErrors(bean: relationshipTypeInstance, field: 'relationshipTypeInverse', 'error')} ">
					<label for="relationshipTypeInverse" class="control-label"><g:message code="relationshipType.relationshipTypeInverse.label" default="Relationship Type Inverse" /></label>
					<div>
						<g:textField class="form-control" name="relationshipTypeInverse" value="${relationshipTypeInstance?.relationshipTypeInverse}"/>
						<span class="help-inline">${hasErrors(bean: relationshipTypeInstance, field: 'relationshipTypeInverse', 'error')}</span>
					</div>
				</div>
			</div>
		</div>

