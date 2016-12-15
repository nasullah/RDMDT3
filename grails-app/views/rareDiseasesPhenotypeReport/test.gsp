
<%@ page import="rdmdt.RareDiseasesPhenotypeReport" %>
<!DOCTYPE html>
<html>

<head>
    <meta name="layout" content="main" />
    <h2><center>Rare Diseases Phenotypes</center></h2>
</head>

<body>


<form>
    <div class="form-group row">
        <label class="col-sm-2 col-form-label">Disease Group:</label>
        <div class="col-sm-6">
            <input type="text" class="form-control" value="${rdmdt.DiseaseGroups.get(1).name}" disabled="">
        </div>
    </div>
</form>
<form>
    <div class="form-group row">
        <label class="col-sm-2 col-form-label">Disease Sub Group:</label>
        <div class="col-sm-6">
            <input type="text" class="form-control" value="${rdmdt.SubGroups.get(1).name}" disabled="">
        </div>
    </div>
</form>
<form>
    <div class="form-group row">
        <label class="col-sm-2 col-form-label">Specific Disease:</label>
        <div class="col-sm-6">
            <input type="text" class="form-control" value="${rdmdt.SpecificDisorders.get(1).name}" disabled="">
        </div>
    </div>
</form>

<section id="index-rareDiseasesPhenotypeReport" class="first">

    <g:form url="[resource:rareDiseasesPhenotypeReportInstance, action:'savetest']" >
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

            <g:each in="${rareDiseasesPhenotypeReportInstanceList}" status="i" var="rareDiseasesPhenotypeReportInstance">
                <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                    <td style='vertical-align:middle'>${fieldValue(bean: rareDiseasesPhenotypeReportInstance, field: "name")}</td>
                    <td style='vertical-align:middle'>${fieldValue(bean: rareDiseasesPhenotypeReportInstance, field: "originalId")}</td>
                    <td style='vertical-align:middle'>OBO-Edit 2.3</td>
                    <td>
                        <g:radioGroup name="${rareDiseasesPhenotypeReportInstance.id}"
                                      labels="['Yes','No','Unknown']"
                                      values="[1,2,3]"
                                      value="3">
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
