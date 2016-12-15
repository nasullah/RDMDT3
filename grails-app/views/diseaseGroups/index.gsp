
<%@ page import="rdmdt.DiseaseGroups" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'diseaseGroups.label', default: 'DiseaseGroups')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-diseaseGroups" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-diseaseGroups" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
			<thead>
					<tr>
					
						<g:sortableColumn property="name" title="${message(code: 'diseaseGroups.name.label', default: 'Name')}" />
					
						<g:sortableColumn property="originalId" title="${message(code: 'diseaseGroups.originalId.label', default: 'Original Id')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${diseaseGroupsInstanceList}" status="i" var="diseaseGroupsInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${diseaseGroupsInstance.id}">${fieldValue(bean: diseaseGroupsInstance, field: "name")}</g:link></td>
					
						<td>${fieldValue(bean: diseaseGroupsInstance, field: "originalId")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${diseaseGroupsInstanceCount ?: 0}" />
			</div>
		</div>

	<a  id="quicktabs-tab-galeri-1"
	   href="/test/public_html/10960.4.html">NEWS</a>

	<button>Change href Value</button>
	<g:link uri ="/public_html/10960.4.html" id="w3s">Book Home</g:link>
	<g:javascript plugin="jquery" library="jquery" />
	<script>
		$(document).ready(function(){
			$("button").click(function(){
				var ff = "/test/public_html/10964.14.html"
				$("#quicktabs-tab-galeri-1").attr("href", ff);
			});
		});
	</script>

	</body>
</html>
