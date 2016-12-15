<%@ page import="rdmdt.FamilyMembersAffectedType" %>



			<div class="row">
				<div class="col-lg-6">
					<div class="${hasErrors(bean: familyMembersAffectedTypeInstance, field: 'familyMembersAffectedTypeName', 'error')} ">
						<label for="familyMembersAffectedTypeName" class="control-label"><g:message code="familyMembersAffectedType.familyMembersAffectedTypeName.label" default="Family Members Affected Type Name" /></label>
						<div>
							<g:textField class="form-control" name="familyMembersAffectedTypeName" value="${familyMembersAffectedTypeInstance?.familyMembersAffectedTypeName}"/>
							<span class="help-inline">${hasErrors(bean: familyMembersAffectedTypeInstance, field: 'familyMembersAffectedTypeName', 'error')}</span>
						</div>
					</div>
				</div>
			</div>

