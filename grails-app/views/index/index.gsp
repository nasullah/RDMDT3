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
							<ul>
								<li class=""><a href="${createLink(uri: '/referralRecord/list')}"> All Applications</a></li>
								<li class=""><g:link controller="referralRecord" action="filteredReferralListByStatus" params="['status': ReferralStatus.findByReferralStatusName('In progress')?.id]"> In Progress </g:link></li>
								<li class=""><g:link controller="referralRecord" action="filteredReferralListByStatus" params="['status': ReferralStatus.findByReferralStatusName('Submitted')?.id]"> Submitted </g:link></li>
								<li class=""><g:link controller="referralRecord" action="filteredReferralListByStatus" params="['status': ReferralStatus.findByReferralStatusName('Review Requested')?.id]"> Review Requested </g:link></li>
								<li class=""><g:link controller="referralRecord" action="filteredReferralListByStatus" params="['status': ReferralStatus.findByReferralStatusName('Review Submitted')?.id]"> Review Submitted </g:link></li>
								<li class=""><g:link controller="referralRecord" action="filteredReferralListByStatus" params="['status': ReferralStatus.findByReferralStatusName('Approved')?.id]"> Approved </g:link></li>
								<li class=""><g:link controller="referralRecord" action="filteredReferralListByStatus" params="['status': ReferralStatus.findByReferralStatusName('Not Approved')?.id]"> Not Approved </g:link></li>
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

</body>

</html>