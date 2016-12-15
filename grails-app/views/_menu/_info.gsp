<sec:ifLoggedIn>
	<g:form controller="login" class="navbar-form navbar-left" >
		<ul class="nav">
			<li class="active">
				<g:if test="${sec?.username()?.toString()?.contains('.')}">
					<g:link controller="login" action="auth"><span class="glyphicon glyphicon-user"></span> ${sec?.username()?.toString()?.substring(0, sec?.username()?.toString()?.lastIndexOf('.'))?.capitalize()}</g:link>
				</g:if>
				<g:else>
					<g:link controller="login" action="auth"><span class="glyphicon glyphicon-user"></span> ${sec?.username()?.capitalize()}</g:link>
				</g:else>
			</li>
		</ul>
	</g:form>
</sec:ifLoggedIn>