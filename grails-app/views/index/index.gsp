<%@ page import="rdmdt.ReferralStatus; rdmdt.Clinician" %>


<html>

<head>
	<meta name="layout" content="main" />
	<style>
	#rcorner1 {
		border-radius: 25px;
		background: rgba(255, 240, 99, 0.09);
		border: 2px solid #8AC007;
		padding: 20px;
		width: 285px;
		height: 200px;
	}
	#rcorner2 {
		border-radius: 25px;
		background: rgba(255, 240, 99, 0.09);
		border: 2px solid #8AC007;
		padding: 20px;
		width: 285px;
		height: 200px;
	}
	#rcorner3 {
		border-radius: 25px;
		background: rgba(255, 240, 99, 0.09);
		border: 2px solid #8AC007;
		padding: 20px;
		width: 285px;
		height: 200px;
	}
	#rcorner4 {
		border-radius: 25px;
		background: rgba(255, 240, 99, 0.09);
		border: 2px solid #8AC007;
		padding: 20px;
		width: 285px;
		height: 200px;
	}
	#rcorner5 {
		border-radius: 25px;
		background: rgba(255, 240, 99, 0.09);
		border: 2px solid #8AC007;
		padding: 20px;
		width: 285px;
		height: 210px;
	}
	#rcorner6 {
		border-radius: 25px;
		background: rgba(255, 240, 99, 0.09);
		border: 2px solid #8AC007;
		padding: 20px;
		width: 285px;
		height: 210px;
	}
	#rcorner7 {
		border-radius: 25px;
		background: rgba(255, 240, 99, 0.09);
		border: 2px solid #8AC007;
		padding: 20px;
		width: 285px;
		height: 210px;
	}
	#rcorner8 {
		border-radius: 25px;
		background: rgba(255, 240, 99, 0.09);
		border: 2px solid #8AC007;
		padding: 20px;
		width: 285px;
		height: 210px;
	}
	</style>
</head>

<body>

<sec:ifAnyGranted roles="ROLE_USER">
	<div class="container-fluid">
		<section id="info">
			<div class="equal">
				<div class="col-md-3">
					<div class="panel panel-default">
						<div class="panel-heading">
							<i class="glyphicon glyphicon-user"></i> Your Profile
						</div>
						<div class="panel-body">
							<g:if test="${clinician}">
								<ul>
									<li><g:link controller="clinician" action="show" params="[id:clinician?.id]"> View your profile</g:link></li>
								</ul>

								<ul>
									<li><g:link controller="clinician" action="edit" params="[id:clinician?.id]"> Edit your profile</g:link></li>
								</ul>
							</g:if>
							<g:else>
								<ul>
									<li><g:link controller="clinician" action="create"> Add your profile</g:link></li>
								</ul>
							</g:else>
							<ul>
								<li><g:link controller="clinician" action="resetPassword"> Reset your password</g:link></li>
							</ul>
						</div>
					</div>
				</div>
				<div class="col-md-3">
					<div class="panel panel-default">
						<div class="panel-heading">
							<i class="glyphicon glyphicon-list"></i> View Applications
						</div>
						<div class="panel-body">
							<ul>
								<li><a href ="${createLink(uri: '/ReferralRecord/filteredReferralList')}"> Your Applications List</a></li>
							</ul>
						</div>
					</div>
				</div>
				<div class="col-md-3">
					<div class="panel panel-default">
						<div class="panel-heading">
							<i class="glyphicon glyphicon-plus" ></i> New Application
						</div>
						<div class="panel-body">
							<ul>
								<li><g:link controller="referralRecord" action="create" params="['clinician.id': clinician?.id]"> Create New Application</g:link></li>
							</ul>
						</div>
					</div>
				</div>
				<div class="col-md-3">
					<div class="panel panel-default">
						<div class="panel-heading">
							<i class="glyphicon glyphicon-list" ></i> Review Requests
						</div>
						<div class="panel-body">
							<ul>
								<li><g:link controller="referralRecord" action="reviewRequestedReferralList"> View Applications</g:link></li>
							</ul>
						</div>
					</div>
				</div>
			</div>
		</section>
	</div>
</sec:ifAnyGranted>

<sec:ifAnyGranted roles="ROLE_ADMIN">
	<div class="container-fluid">
		<section id="info1">
			<div class="equal">
				<div class="col-md-3">
					<div class="panel panel-default">
						<div class="panel-heading">
							<i class="glyphicon glyphicon-list"></i> View Clinicians
						</div>
						<div class="panel-body">
							<ul>
								<li class=""><a href="${createLink(uri: '/clinician/list')}"> All Clinicians</a></li>
							</ul>
						</div>
					</div>
				</div>
				<div class="col-md-3">
					<div class="panel panel-default">
						<div class="panel-heading">
							<i class="glyphicon glyphicon-list"></i> View Applications
						</div>
						<div class="panel-body">
							<%  Date now = new Date()
							Integer currentYear = now.year + 1900
							Integer currentMonth = now.month + 1
							%>
							<g:form controller="referralRecord" action="filteredReferralListByStatus" id="form" name="form">
								<div class="form-inline">
									<label>Filter by month</label>
									<div class="form-group">
										<g:select id="month" name="month" from="${[1,2,3,4,5,6,7,8,9,10,11,12]}" value="${currentMonth}" noSelection="['':'']" onchange="updateAllApplicationsLink()"/>
									</div>
									<div class="form-group">
										<g:select id="year" name="year" from="${[2016, 2017, 2018, 2019, 2020]}" value="${currentYear}"  noSelection="['':'']" onchange="updateAllApplicationsLink()"/>
									</div>
								</div>
								<g:hiddenField name="status" value="" />
							</g:form>
							<hr/>
							<ul>
								<li class=""><a id="link" href="${createLink(uri: '/referralRecord/list')}"> All Applications</a></li>
								<li class=""><g:link controller="referralRecord" action="filteredReferralListByStatus" params="['status': 'In progress']"> In Progress </g:link></li>
								<li class=""><a href="#" onclick="submitted()"> Submitted </a></li>
								<li class=""><a href="#" onclick="reviewRequested()"> Review Requested </a></li>
								<li class=""><a href="#" onclick="reviewSubmitted()"> Review Submitted </a></li>
								<li class=""><a href="#" onclick="approved()"> Approved </a></li>
								<li class=""><a href="#" onclick="notApproved()"> Not Approved </a></li>
								<li class=""><a href="#" onclick="withdrawn()"> Withdrawn </a></li>
								<li class=""><a href="#" onclick="suspended()"> Suspended </a></li>
							</ul>
						</div>
					</div>
				</div>
				<div class="col-md-3">
					<div class="panel panel-default">
						<div class="panel-heading">
							<i class="glyphicon glyphicon-plus" ></i> New Application
						</div>
						<div class="panel-body">
							<ul>
								<li><g:link controller="referralRecord" action="create"> Create New Application</g:link></li>
							</ul>
						</div>
					</div>
				</div>

				<div class="col-md-3">
					<div class="panel panel-default">
						<div class="panel-heading">
							<i class="glyphicon glyphicon-user"></i> User Activity
						</div>
						<div class="panel-body">
							<ul>
								<li><a href ="${createLink(uri: '/index/listAuditLogData')}">  View User Activity Log</a></li>
							</ul>
						</div>
					</div>
				</div>
			</div>
		</section>
	</div>
</sec:ifAnyGranted>

<content tag="javascript">
	<script>
		updateAllApplicationsLink();
		function updateAllApplicationsLink(){
			var selectedMonth = $("#month").val();
			var selectedYear = $("#year").val();
			$("#link").attr('href', '/RDMDT/referralRecord/list?month=' + selectedMonth + '&year=' + selectedYear)
		}
		function submitted(){
			$("#status").val('Submitted');
			document.getElementById('form').submit();
		}
		function reviewRequested(){
			$("#status").val('Review Requested');
			document.getElementById('form').submit();
		}
		function reviewSubmitted(){
			$("#status").val('Review Submitted');
			document.getElementById('form').submit();
		}
		function approved(){
			$("#status").val('Approved');
			document.getElementById('form').submit();
		}
		function notApproved(){
			$("#status").val('Not Approved');
			document.getElementById('form').submit();
		}
		function withdrawn(){
			$("#status").val('Withdrawn');
			document.getElementById('form').submit();
		}
		function suspended(){
			$("#status").val('Suspended');
			document.getElementById('form').submit();
		}

	</script>
</content>

</body>

</html>