<%@ page import="rdmdt.Patient; rdmdt.ReferralRecord" %>
<!DOCTYPE html>
<html>

<head>
    <meta name="layout" content="main" />
    <title>Sample Tracking</title>
</head>

<body>

<a class='btn btn-primary btn-sm' <g:link  action="show" id="${referralRecordInstance?.id}"><i class="glyphicon glyphicon-menu-left"></i> Back to Application</g:link>


<h3>${referralRecordInstance}</h3>

<section id="index-referralRecord" class="first">

    <table class="table table-bordered margin-top-medium">
        <thead>
        <tr>

            <th>External Id</th>

            <th>Container Id</th>

            <th>Log Date</th>

            <th>Family Number</th>

            <th>Relationship</th>

            <th>Stored Sample</th>

            <th>Consent</th>

            <th>Glomax</th>

            <th>MW</th>

            <th>Shipment</th>

            <th>Shipment Date</th>

            <th>Patient Name</th>

        </tr>
        </thead>
        <tbody>
        <g:each in="${sampleTrackingList}" status="i" var="sampleTrackingInstance">
            <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">

                <td>${sampleTrackingInstance[0]}</td>

                <td>${sampleTrackingInstance[1]}</td>

                <td>${sampleTrackingInstance[2]}</td>

                <td>${sampleTrackingInstance[3]}</td>

                <td>${sampleTrackingInstance[4]}</td>

                <td>${sampleTrackingInstance[5]}</td>

                <td>${sampleTrackingInstance[6]}</td>

                <td>${sampleTrackingInstance[7]}</td>

                <td>${sampleTrackingInstance[8]}</td>

                <td>${sampleTrackingInstance[9]}</td>

                <td>${sampleTrackingInstance[10]}</td>

                <td>${sampleTrackingInstance[12]} ${sampleTrackingInstance[11]}</td>

            </tr>
        </g:each>
        </tbody>
    </table>

</section>

</body>

</html>