
<%@ page import="rdmdt.Clinician; rdmdt.Department; rdmdt.Patient; rdmdt.ReferralRecord" %>
<!DOCTYPE html>
<html>

<head>
    <meta name="layout" content="main" />
    <title>Export Options</title>
</head>

<body>

<div>
    <div class="container">
        <p>
        <h5 class="text-center">Export Options</h5>
        <p>


        <div class="row">
            <div class="col-md-12">
                <g:form controller="clinician" action="exportOptions" params="['format': 'excel', 'extension': 'xls']">
                    <label class="control-label"><small>Export Applications By Meeting Date (To export all applications, deselect month and year.)</small></label>
                    <div class="form-group">
                        <%  Date now = new Date()
                        Integer currentYear = now.year + 1900
                        Integer currentMonth = now.month + 1
                        %>
                        <div class="col-md-3">
                            <g:select class="form-control" id="month" name="month" from="${[1,2,3,4,5,6,7,8,9,10,11,12]}" value="${currentMonth}"  noSelection="['':'- Choose Month -']"/>
                        </div>
                        <div class="col-md-3">
                            <g:select class="form-control" id="year" name="year" from="${[2016,2017,2018,2019,2020]}" value="${currentYear}"  noSelection="['':'- Choose Year -']"/>
                        </div>
                            <button type="submit"  class="btn btn-success btn-sm" ><span class="glyphicon glyphicon-export"></span> Excel Format</button>
                    </div>
                </g:form>
            </div>
        </div>

        <div class="row">
            <div class="col-md-11">
                <hr>
            </div>
        </div>

        <div class="row">
            <div class="col-md-12">
                <g:form controller="clinician" action="exportOptions" params="['format': 'excel', 'extension': 'xls']">
                    <label class="control-label"><small>Export Applications By Referring Clinician</small></label>
                    <div class="form-group">
                        <div class="col-md-6">
                            <g:select class="form-control" id="clinician" name="clinician" from="${Clinician.list()}" required="" value="${params.clinician}" optionKey="id" noSelection="['':'- Choose Clinician -']"/>
                        </div>
                        <button type="submit"  class="btn btn-success btn-sm" ><span class="glyphicon glyphicon-export"></span> Excel Format</button>
                    </div>
                </g:form>
            </div>
        </div>

        <div class="row">
            <div class="col-md-11">
                <hr>
            </div>
        </div>

        <div class="row">
            <div class="col-md-12">
                <g:form controller="clinician" action="exportOptions" params="['format': 'excel', 'extension': 'xls']">
                    <label class="control-label"><small>Export Applications By Referral Department</small></label>
                    <div class="form-group">
                        <div class="col-md-6">
                            <g:select class="form-control" id="department" name="department" from="${Department.list()}" required="" value="${params.department}" optionKey="id" noSelection="['':'- Choose Department -']"/>
                        </div>
                        <button type="submit"  class="btn btn-success btn-sm" ><span class="glyphicon glyphicon-export"></span> Excel Format</button>
                    </div>
                </g:form>
            </div>
        </div>
    </div>
</div>

<hr/>

</body>

</html>