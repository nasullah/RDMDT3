
<%@ page import="rdmdt.RareDiseaseConditions" %>
<!DOCTYPE html>
<html>

<head>
	<meta name="layout" content="main" />
	<g:set var="entityName" value="${message(code: 'rareDiseaseConditions.label', default: 'RareDiseaseConditions')}" />
	<title><g:message code="default.index.label" args="[entityName]" /></title>
</head>

<body>

<section id="index-rareDiseaseConditions" class="first">

	<table class="table table-bordered margin-top-medium">
		<thead>
			<tr>
			
				<g:sortableColumn property="originalId" title="${message(code: 'rareDiseaseConditions.originalId.label', default: 'Original Id')}" />
			
				<g:sortableColumn property="diseaseGroup" title="${message(code: 'rareDiseaseConditions.diseaseGroup.label', default: 'Disease Group')}" />
			
				<g:sortableColumn property="diseaseSubgroup" title="${message(code: 'rareDiseaseConditions.diseaseSubgroup.label', default: 'Disease Subgroup')}" />
			
				<g:sortableColumn property="diseaseName" title="${message(code: 'rareDiseaseConditions.diseaseName.label', default: 'Disease Name')}" />
			
			</tr>
		</thead>
		<tbody>
		<g:each in="${rareDiseaseConditionsInstanceList}" status="i" var="rareDiseaseConditionsInstance">
			<tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
			
				<td><g:link action="show" id="${rareDiseaseConditionsInstance.id}">${fieldValue(bean: rareDiseaseConditionsInstance, field: "originalId")}</g:link></td>
			
				<td>${fieldValue(bean: rareDiseaseConditionsInstance, field: "diseaseGroup")}</td>
			
				<td>${fieldValue(bean: rareDiseaseConditionsInstance, field: "diseaseSubgroup")}</td>
			
				<td>${fieldValue(bean: rareDiseaseConditionsInstance, field: "diseaseName")}</td>
			
			</tr>
		</g:each>
		</tbody>
	</table>
	<div class="pagination">
		<g:paginate total="${rareDiseaseConditionsInstanceCount}" />
	</div>
</section>

<g:form action="uploadFile" method="post" enctype="multipart/form-data">
	<p class="text-success">Upload file</p>
	<input type="file" name="file" id="file" />
	<button type="submit" class="btn btn-success btn-xs" value="Upload"><span class="glyphicon glyphicon-upload"></span> Upload</button>
</g:form>

</body>

</html>
