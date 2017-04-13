
<%@ page import="rdmdt.YesNoUnknown; rdmdt.RareDiseasesPhenotypeReport" %>
<!DOCTYPE html>
<html>

<head>
	<meta name="layout" content="main" />
	<h2><center>Create Phenotype Report</center></h2>
</head>

<body>

<h4>Application unique ref: ${rareDiseasesPhenotypeReportInstance?.referralRecord?.uniqueRef}</h4>
<br/>

<form>
	<div class="form-group row">
		<label class="col-sm-2 col-form-label">Disease Group:</label>
		<div class="col-sm-6">
			<input type="text" class="form-control" value="${diseaseGroup?.name}" disabled="">
		</div>
	</div>
</form>
<form>
	<div class="form-group row">
		<label class="col-sm-2 col-form-label">Disease Sub Group:</label>
		<div class="col-sm-6">
			<input type="text" class="form-control" value="${diseaseSubGroup?.name}" disabled="">
		</div>
	</div>
</form>
<form>
	<div class="form-group row">
		<label class="col-sm-2 col-form-label">Specific Disease:</label>
		<div class="col-sm-6">
			<input type="text" class="form-control" value="${specificDisease?.name}" disabled="">
		</div>
	</div>
</form>

<section id="index-rareDiseasesPhenotypeReport" class="first">

	<g:form action="save" class="form-horizontal" role="form" >
		<g:hiddenField name="referralRecord" value="${rareDiseasesPhenotypeReportInstance?.referralRecord?.id}" />
		<table class="table table-bordered margin-top-medium">
			<thead>
			<tr>
				<th>Phenotype Description</th>
				<th>Phenotype Identifier</th>
				<th>HPO Build Number</th>
				<th>Phenotype Present</th>
			</tr>
			</thead>

			<tbody>

			<g:each in="${specificDisease?.shallowPhenotypes?.sort{it.originalId}}" status="i" var="shallowPhenotypeInstance">
				<tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
					<td style='vertical-align:middle'>${fieldValue(bean: shallowPhenotypeInstance, field: "name")}</td>
					<td style='vertical-align:middle'>${fieldValue(bean: shallowPhenotypeInstance, field: "originalId")}</td>
					<td style='vertical-align:middle'>OBO-Edit 2.3</td>
					<g:hiddenField name="shallowPhenotypeIdentifier_${i}" value="${shallowPhenotypeInstance?.originalId}" />
					<g:hiddenField name="shallowPhenotypeDescription_${i}" value="${shallowPhenotypeInstance?.name}" />
					<g:hiddenField name="shallowPhenotypeHPOBuildNumber_${i}" value="OBO-Edit 2.3" />
					<td>
						<g:radioGroup name="shallowPhenotypePresent_${i}"
									  labels="['Yes','No','Unknown']"
									  values="[YesNoUnknown.findByYesNoUnknownName('Yes')?.id, YesNoUnknown.findByYesNoUnknownName('No')?.id, YesNoUnknown.findByYesNoUnknownName('Unknown')?.id]"
									  value="${YesNoUnknown.findByYesNoUnknownName('Unknown')?.id}">
							<p> ${it.radio}  &nbsp; ${it.label}</p>
						</g:radioGroup>
					</td>

				</tr>
			</g:each>
			</tbody>
		</table>
		<div class="form-actions margin-top-medium">
			<g:submitButton name="create" class="btn btn-primary" value="Save" />
			<button class="btn" type="reset">Reset</button>
		</div>
	</g:form>

</section>

</body>

</html>
