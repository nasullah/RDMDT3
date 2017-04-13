<%@ page import="rdmdt.YesNoUnknown; rdmdt.RareDiseasesPhenotypeReport" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main" />
		<h2><center>Edit Phenotype Report</center></h2>
	</head>
	<body>

	<h4>Application unique ref: ${rareDiseasesPhenotypeReportInstance?.referralRecord?.uniqueRef}</h4>
	<br/>

	<form>
		<div class="form-group row">
			<label class="col-sm-2 col-form-label">Disease Group:</label>
			<div class="col-sm-6">
				<input type="text" class="form-control" value="${rareDiseasesPhenotypeReportInstance?.referralRecord?.approvedTargetCategory?.diseaseGroup}" disabled="">
			</div>
		</div>
	</form>
	<form>
		<div class="form-group row">
			<label class="col-sm-2 col-form-label">Disease Sub Group:</label>
			<div class="col-sm-6">
				<input type="text" class="form-control" value="${rareDiseasesPhenotypeReportInstance?.referralRecord?.approvedTargetCategory?.diseaseSubgroup}" disabled="">
			</div>
		</div>
	</form>
	<form>
		<div class="form-group row">
			<label class="col-sm-2 col-form-label">Specific Disease:</label>
			<div class="col-sm-6">
				<input type="text" class="form-control" value="${rareDiseasesPhenotypeReportInstance?.referralRecord?.approvedTargetCategory?.diseaseName}" disabled="">
			</div>
		</div>
	</form>

	<section id="index-rareDiseasesPhenotypeReport" class="first">

		<g:form action="updateRecord" class="form-horizontal" role="form" >
			<g:hiddenField name="referralRecord" value="${rareDiseasesPhenotypeReportInstance?.referralRecord?.id}" />
			<g:hiddenField name="rareDiseasesPhenotypeReportInstance" value="${rareDiseasesPhenotypeReportInstance?.id}" />
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

				<g:each in="${rareDiseasesPhenotypeReportInstance?.statements?.sort{it.identifier}}" status="i" var="statementInstance">
					<tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
						<td style='vertical-align:middle'>${fieldValue(bean: statementInstance, field: "description")}</td>
						<td style='vertical-align:middle'>${fieldValue(bean: statementInstance, field: "identifier")}</td>
						<td style='vertical-align:middle'>${fieldValue(bean: statementInstance, field: "hpoBuildNumber")}</td>
						<g:hiddenField name="shallowPhenotypeIdentifier_${i}" value="${statementInstance?.identifier}" />
						<g:hiddenField name="shallowPhenotypeDescription_${i}" value="${statementInstance?.description}" />
						<g:hiddenField name="shallowPhenotypeHPOBuildNumber_${i}" value="${statementInstance?.hpoBuildNumber}" />
						<td>
							<g:radioGroup name="shallowPhenotypePresent_${i}"
										  labels="['Yes','No','Unknown']"
										  values="[YesNoUnknown.findByYesNoUnknownName('Yes')?.id, YesNoUnknown.findByYesNoUnknownName('No')?.id, YesNoUnknown.findByYesNoUnknownName('Unknown')?.id]"
										  value="${statementInstance?.present?.id}">
								<p> ${it.radio}  &nbsp; ${it.label}</p>
							</g:radioGroup>
						</td>

					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="form-actions margin-top-medium">
				<g:submitButton name="create" class="btn btn-primary" value="Update" />
				<button class="btn" type="reset">Reset</button>
			</div>
		</g:form>

	</section>
	</body>
</html>
