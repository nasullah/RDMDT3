<!DOCTYPE html>
<html lang="en" class="no-js">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
	<meta http-equiv="X-UA-Compatible" content="IE=edge"/>
	<title><g:layoutTitle default="RDMDT"/></title>
	<meta name="viewport" content="width=device-width, initial-scale=1"/>

	<asset:stylesheet src="application.css"/>

	<g:layoutHead/>
</head>
<body aurelia-app="main">
<g:render template="/_menu/navbar"/>

<g:render template="/layouts/content"/>

<footer class="footer">
	<div class="container" style="margin-bottom: 10px;">
		<div class="row text-center">
			&#169; University of Oxford 2017
		</div>
	</div>
</footer>

<div id="spinner" class="spinner" style="display:none;">
	<g:message code="spinner.alt" default="Loading&hellip;"/>
</div>

<asset:javascript src="application.js"/>
<g:pageProperty name="page.javascript"/>

</body>
</html>

