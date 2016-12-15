
<%@ page import="rdmdt.Paternal" %>
<!DOCTYPE html>
<html>

<head>
	<meta name="layout" content="main" />
	<g:set var="entityName" value="${message(code: 'paternal.label', default: 'Paternal')}" />
	<title><g:message code="default.list.label" args="[entityName]" /></title>
</head>

<body>

<section id="list-paternal" class="first">

	<table class="table table-bordered margin-top-medium">
		<thead>
			<tr>
			
				<g:sortableColumn property="breastAndOrOvarianCancer" title="${message(code: 'paternal.breastAndOrOvarianCancer.label', default: 'Breast And Or Ovarian Cancer')}" />
			
				<g:sortableColumn property="colorectalCancer" title="${message(code: 'paternal.colorectalCancer.label', default: 'Colorectal Cancer')}" />
			
				<g:sortableColumn property="ischaemicHeartDiseaseOrStroke" title="${message(code: 'paternal.ischaemicHeartDiseaseOrStroke.label', default: 'Ischaemic Heart Disease Or Stroke')}" />
			
				<g:sortableColumn property="endocrineTumours" title="${message(code: 'paternal.endocrineTumours.label', default: 'Endocrine Tumours')}" />
			
				<th><g:message code="paternal.referralRecord.label" default="Referral Record" /></th>
			
			</tr>
		</thead>
		<tbody>
		<g:each in="${paternalInstanceList}" status="i" var="paternalInstance">
			<tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
			
				<td><g:link action="show" id="${paternalInstance.id}">${fieldValue(bean: paternalInstance, field: "breastAndOrOvarianCancer")}</g:link></td>
			
				<td><g:formatBoolean boolean="${paternalInstance.colorectalCancer}" /></td>
			
				<td><g:formatBoolean boolean="${paternalInstance.ischaemicHeartDiseaseOrStroke}" /></td>
			
				<td><g:formatBoolean boolean="${paternalInstance.endocrineTumours}" /></td>
			
				<td>${fieldValue(bean: paternalInstance, field: "referralRecord")}</td>
			
			</tr>
		</g:each>
		</tbody>
	</table>
	<div class="pagination">
		<g:paginate total="${paternalInstanceCount}" />
	</div>
</section>

</body>

</html>
