<%@ page import="rdmdt.RareDiseasesPhenotypeReport" %>



<div class="fieldcontain ${hasErrors(bean: rareDiseasesPhenotypeReportInstance, field: 'statements', 'error')} ">
	<label for="statements">
		<g:message code="rareDiseasesPhenotypeReport.statements.label" default="Statements" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${rareDiseasesPhenotypeReportInstance?.statements?}" var="s">
    <li><g:link controller="statement" action="show" id="${s.id}">${s?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="statement" action="create" params="['rareDiseasesPhenotypeReport.id': rareDiseasesPhenotypeReportInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'statement.label', default: 'Statement')])}</g:link>
</li>
</ul>


</div>

