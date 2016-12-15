<%@ page import="rdmdt.DiseaseGroups" %>



<div class="fieldcontain ${hasErrors(bean: diseaseGroupsInstance, field: 'name', 'error')} required">
	<label for="name">
		<g:message code="diseaseGroups.name.label" default="Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="name" required="" value="${diseaseGroupsInstance?.name}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: diseaseGroupsInstance, field: 'originalId', 'error')} required">
	<label for="originalId">
		<g:message code="diseaseGroups.originalId.label" default="Original Id" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="originalId" required="" value="${diseaseGroupsInstance?.originalId}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: diseaseGroupsInstance, field: 'subGroups', 'error')} ">
	<label for="subGroups">
		<g:message code="diseaseGroups.subGroups.label" default="Sub Groups" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${diseaseGroupsInstance?.subGroups?}" var="s">
    <li><g:link controller="subGroups" action="show" id="${s.id}">${s?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="subGroups" action="create" params="['diseaseGroups.id': diseaseGroupsInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'subGroups.label', default: 'SubGroups')])}</g:link>
</li>
</ul>


</div>

