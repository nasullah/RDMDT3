<sec:ifAnyGranted roles="ROLE_ADMIN">

	<li class="dropdown" style="padding-top: 3px;">
		<a class="dropdown-toggle" data-toggle="dropdown" href="#">
			<i class="glyphicon glyphicon-cog white"></i>
			Administration <b class="caret"></b>
		</a>

		<ul class="dropdown-menu" style="height: auto; max-height: 350px; width: 270px; overflow-x: hidden;">
			<li class="">
				<a tabindex="-1" href="#">
					<b>User Access Management</b></a>
			</li>

			<p>

			<li class="">
				<a href="${createLink(uri: '/role/create')}">
					<i class="glyphicon glyphicon-plus-sign"></i>
					Add Role
				</a>
			</li>

			<li class="">
				<a href="${createLink(uri: '/role')}">
					<i class="glyphicon glyphicon-search"></i>
					Search Role
				</a>
			</li>

			<li class="">
				<a href="${createLink(uri: '/user/create')}">
					<i class="glyphicon glyphicon-plus-sign"></i>
					Add User
				</a>
			</li>

			<li class="">
				<a href="${createLink(uri: '/user')}">
					<i class="glyphicon glyphicon-search"></i>
					Search User
				</a>
			</li>

			<hr/>

			<li class="">
				<a tabindex="-1" href="#">
					<b>Dropdown Lists Management</b></a>
			</li>

			<p>

			<li class="">
				<a href="${createLink(uri: '/ageUnit')}">
					<i class="glyphicon glyphicon-plus-sign"></i>
					Age Unit
				</a>
			</li>

			<li class="">
				<a href="${createLink(uri: '/referralStatus')}">
					<i class="glyphicon glyphicon-plus-sign"></i>
					Application Status Type
				</a>
			</li>

			<li class="">
				<a href="${createLink(uri: '/attachedEvidenceType')}">
					<i class="glyphicon glyphicon-plus-sign"></i>
					Attached Evidence Type
				</a>
			</li>

			<li class="">
				<a href="${createLink(uri: '/centre')}">
					<i class="glyphicon glyphicon-plus-sign"></i>
					Centre
				</a>
			</li>

			<li class="">
				<a href="${createLink(uri: '/consanguinity')}">
					<i class="glyphicon glyphicon-plus-sign"></i>
					Consanguinity
				</a>
			</li>

			<li class="">
				<a href="${createLink(uri: '/department')}">
					<i class="glyphicon glyphicon-plus-sign"></i>
					Department
				</a>
			</li>

			<li class="">
				<a href="${createLink(uri: '/eligibilityType')}">
					<i class="glyphicon glyphicon-plus-sign"></i>
					Eligibility Type
				</a>
			</li>

			<li class="">
				<a href="${createLink(uri: '/ethnicity')}">
					<i class="glyphicon glyphicon-plus-sign"></i>
					Ethnicity
				</a>
			</li>

			<li class="">
				<a href="${createLink(uri: '/familyHistoryType')}">
					<i class="glyphicon glyphicon-plus-sign"></i>
					Family History Type
				</a>
			</li>

			<li class="">
				<a href="${createLink(uri: '/familyMembersAffectedType')}">
					<i class="glyphicon glyphicon-plus-sign"></i>
					Family Members Affected Type
				</a>
			</li>

			<li class="">
				<a href="${createLink(uri: '/gender')}">
					<i class="glyphicon glyphicon-plus-sign"></i>
					Gender
				</a>
			</li>

			<li class="">
				<a href="${createLink(uri: '/penetrance')}">
					<i class="glyphicon glyphicon-plus-sign"></i>
					Penetrance
				</a>
			</li>

			<li class="">
				<a href="${createLink(uri: '/program')}">
					<i class="glyphicon glyphicon-plus-sign"></i>
					Program
				</a>
			</li>

			<li class="">
				<a href="${createLink(uri: '/relationshipType')}">
					<i class="glyphicon glyphicon-plus-sign"></i>
					Relationship Type
				</a>
			</li>

			<li class="">
				<a href="${createLink(uri: '/roleType')}">
					<i class="glyphicon glyphicon-plus-sign"></i>
					Role Type
				</a>
			</li>

			<li class="">
				<a href="${createLink(uri: '/yesNoUnknown')}">
					<i class="glyphicon glyphicon-plus-sign"></i>
					Yes No Unknown
				</a>
			</li>

		</ul>

	</li>

</sec:ifAnyGranted>