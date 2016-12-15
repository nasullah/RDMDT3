<sec:ifAnyGranted roles="ROLE_ADMIN, ROLE_USER">
	<g:if test="${params.controller != 'home'}">
		<div class="btn-group" style="margin-top: 10px">
			<g:if test="${params.controller == 'index'}">
				<g:link controller="index" action="index" class="btn btn-primary"><i class="glyphicon glyphicon-th-large"></i> Main Menu</g:link>
			</g:if>
			<g:else>
				<g:link controller="index" action="index" class="btn btn-primary"><i class="glyphicon glyphicon-menu-left"></i> Back To Main Menu</g:link>
			</g:else>
		</div>
		<hr />

	%{--<ul class="nav nav-tabs" style="margin-top: 10px;">--}%

	%{--<g:if test="${params.controller == 'index'}">--}%
	%{--<li>--}%
	%{--<g:link controller="index" action="index" class="">Main Menu</g:link>--}%
	%{--</li>--}%
	%{--</g:if>--}%
	%{--<g:else>--}%
	%{--<li>--}%
	%{--<g:link controller="index" action="index">Back To Main Menu</g:link>--}%
	%{--</li>--}%
	%{--</g:else>--}%

	%{--</ul>--}%
	</g:if>
</sec:ifAnyGranted>