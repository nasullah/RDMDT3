
<%@ page import="rdmdt.Maternal" %>
<!DOCTYPE html>
<html>

<head>
	<meta name="layout" content="main" />
	<g:set var="entityName" value="${message(code: 'maternal.label', default: 'Maternal')}" />
	<title><g:message code="default.list.label" args="[entityName]" /></title>
</head>

<body>

<section id="list-maternal" class="first">

	<table class="table table-bordered margin-top-medium">
		<thead>
			<tr>
			
				<g:sortableColumn property="breastAndOrOvarianCancer" title="${message(code: 'maternal.breastAndOrOvarianCancer.label', default: 'Breast And Or Ovarian Cancer')}" />
			
				<g:sortableColumn property="colorectalCancer" title="${message(code: 'maternal.colorectalCancer.label', default: 'Colorectal Cancer')}" />
			
				<g:sortableColumn property="ischaemicHeartDiseaseOrStroke" title="${message(code: 'maternal.ischaemicHeartDiseaseOrStroke.label', default: 'Ischaemic Heart Disease Or Stroke')}" />
			
				<g:sortableColumn property="endocrineTumours" title="${message(code: 'maternal.endocrineTumours.label', default: 'Endocrine Tumours')}" />
			
				<th><g:message code="maternal.referralRecord.label" default="Referral Record" /></th>
			
			</tr>
		</thead>
		<tbody>
		<g:each in="${maternalInstanceList}" status="i" var="maternalInstance">
			<tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
			
				<td><g:link action="show" id="${maternalInstance.id}">${fieldValue(bean: maternalInstance, field: "breastAndOrOvarianCancer")}</g:link></td>
			
				<td><g:formatBoolean boolean="${maternalInstance.colorectalCancer}" /></td>
			
				<td><g:formatBoolean boolean="${maternalInstance.ischaemicHeartDiseaseOrStroke}" /></td>
			
				<td><g:formatBoolean boolean="${maternalInstance.endocrineTumours}" /></td>
			
				<td>${fieldValue(bean: maternalInstance, field: "referralRecord")}</td>
			
			</tr>
		</g:each>
		</tbody>
	</table>
	<div class="pagination">
		<g:paginate total="${maternalInstanceCount}" />
	</div>
</section>

</body>

</html>
