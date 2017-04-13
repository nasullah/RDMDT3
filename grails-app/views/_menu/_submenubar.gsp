<!-- 
This menu is used to show function that can be triggered on the content (an object or list of objects).
-->

<%-- Only show the "Pills" navigation menu if a controller exists (but not for home) --%>
<g:if test="${	params.controller != null
			&&	params.controller != ''
			&&	params.controller != 'home'
			&&	params.controller != 'index'
}">
	<ul id="Menu" class="nav nav-pills margin-top-small">

		<g:set var="entityName" value="${message(code: params.controller+'.label', default: params.controller.substring(0,1).toUpperCase() + params.controller.substring(1).toLowerCase())}" />

		%{--<g:if test="${ params.controller == 'ReferralRecord' && params.action == "filteredReferralList"}">--}%
			%{--<li class="${ params.action == "filteredReferralList" ? 'active' : '' }">--}%
				%{--<g:link action="filteredReferralList"><i class="glyphicon glyphicon-th-list"></i> Your Application List</g:link>--}%
			%{--</li>--}%
		%{--</g:if>--}%

		<g:if test="${ params.controller == 'rareDiseasesPhenotypeReport' }">
			<li>
				<g:link controller="referralRecord" action="show" params="['id': rareDiseasesPhenotypeReportInstance?.referralRecord?.id]"><i class="glyphicon glyphicon-menu-left"></i> Back To Application</g:link>
			</li>
		</g:if>

		<g:if test="${( params.controller != 'referralRecord' && params.action != "filteredReferralList") && (params.controller != "rareDiseasesPhenotypeReport" && params.action != "create")}">
			<g:if test="${ params.controller != 'clinician'}">
				<li class="${ params.action == "list" ? 'active' : '' }">
					<g:link action="list"><i class="glyphicon glyphicon-th-list"></i> <g:message code="default.list.label" args="[entityName]"/></g:link>
				</li>
			</g:if>
		</g:if>

		%{--<g:if test="${ params.controller == 'referralRecord' && params.action == "create"}">--}%
			%{--<li class="${ params.action == "create" ? 'active' : '' }">--}%
				%{--<g:link action="create"><i class="glyphicon glyphicon-plus"></i> Create Application</g:link>--}%
			%{--</li>--}%
		%{--</g:if>--}%

		<g:if test="${ (params.controller != 'referralRecord' && params.action != "filteredReferralList" && params.action != "exportOptions") && (params.controller != "rareDiseasesPhenotypeReport" && params.action != "create")}">
			<g:if test="${ params.controller != 'clinician'}">
				<li class="${ params.action == "create" ? 'active' : '' }">
					<g:link action="create"><i class="glyphicon glyphicon-plus"></i> <g:message code="default.new.label"  args="[entityName]"/></g:link>
				</li>
			</g:if>
			<g:else>
				<sec:ifAnyGranted roles="ROLE_ADMIN">
					<li class="${ params.action == "create" ? 'active' : '' }">
						<g:link action="create"><i class="glyphicon glyphicon-plus"></i> <g:message code="default.new.label"  args="[entityName]"/></g:link>
					</li>
				</sec:ifAnyGranted>
			</g:else>
		</g:if>

		<g:if test="${ params.action == 'show' || params.action == 'edit' }">
			<!-- the item is an object (not a list) -->
			<li class="${ params.action == "edit" ? 'active' : '' }">
				<g:if test="${ params.controller == 'referralRecord'}">
					<g:link action="edit" id="${params.id}"><i class="glyphicon glyphicon-pencil"></i> Edit Application</g:link>
				</g:if>
				<g:elseif test="${ params.controller == 'clinician'}">
					<g:link action="edit" id="${params.id}"><i class="glyphicon glyphicon-pencil"></i> Edit Profile</g:link>
				</g:elseif>
				<g:elseif test="${ params.controller == 'rareDiseasesPhenotypeReport'}">
					<g:link action="edit" id="${params.id}"><i class="glyphicon glyphicon-pencil"></i> Edit Phenotype Report</g:link>
				</g:elseif>
				<g:else>
					<g:link action="edit" id="${params.id}"><i class="glyphicon glyphicon-pencil"></i> <g:message code="default.edit.label"  args="[entityName]"/></g:link>
				</g:else>
			</li>
			<g:if test="${ params.controller == 'referralRecord'}">
				<li class="">
					<g:link action="sampleTracking" id="${params.id}"><i class="glyphicon glyphicon-eye-open"></i> Sample Tracking</g:link>
				</li>
			</g:if>
			<li class="">
				<g:render template="/_common/modals/deleteTextLink"/>
			</li>
		</g:if>
		
	</ul>
</g:if>
