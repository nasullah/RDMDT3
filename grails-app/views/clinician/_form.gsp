<%@ page import="rdmdt.RoleType; rdmdt.Department; rdmdt.Centre; rdmdt.Clinician" %>



			<div class="row">
				<div class="col-lg-2">
					<div class="${hasErrors(bean: clinicianInstance, field: 'professionalTitle', 'error')} ">
						<label for="professionalTitle" class="control-label"><g:message code="clinician.professionalTitle.label" default="Title" /></label>
						<div>
							<div>
								<g:select class="form-control" name="professionalTitle" from="${["Dr", "Professor", "Mr", "Ms", "Miss", "Mrs"]}" value="${clinicianInstance?.professionalTitle}" valueMessagePrefix="clinicianInstance.clinicianInstance" noSelection="['':'- Choose -']"/>
							</div>
							<span class="help-inline">${hasErrors(bean: clinicianInstance, field: 'professionalTitle', 'error')}</span>
						</div>
					</div>
				</div>

				<div class="col-lg-4">
					<div class="${hasErrors(bean: clinicianInstance, field: 'forename', 'error')} ">
						<label for="forename" class="control-label"><g:message code="clinician.forename.label" default="Forename" /><span class="required-indicator">*</span></label>
						<div>
							<g:textField class="form-control" name="forename" value="${clinicianInstance?.forename}" required=""/>
							<span class="help-inline">${hasErrors(bean: clinicianInstance, field: 'forename', 'error')}</span>
						</div>
					</div>
				</div>

				<div class="col-lg-6">
					<div class="${hasErrors(bean: clinicianInstance, field: 'surname', 'error')} ">
						<label for="surname" class="control-label"><g:message code="clinician.surname.label" default="Surname" /><span class="required-indicator">*</span></label>
						<div>
							<g:textField class="form-control" name="surname" value="${clinicianInstance?.surname}" required=""/>
							<span class="help-inline">${hasErrors(bean: clinicianInstance, field: 'surname', 'error')}</span>
						</div>
					</div>
				</div>

				<div class="col-lg-6">
					<div class="${hasErrors(bean: clinicianInstance, field: 'email', 'error')} ">
						<label for="email" class="control-label"><g:message code="clinician.email.label" default="Email" /></label>
						<div>
							<g:textField class="form-control" name="email" value="${clinicianInstance?.email}"/>
							<span class="help-inline">${hasErrors(bean: clinicianInstance, field: 'email', 'error')}</span>
						</div>
					</div>
				</div>

				<div class="col-lg-6">
					<div class="${hasErrors(bean: clinicianInstance, field: 'telephone', 'error')} ">
						<label for="telephone" class="control-label"><g:message code="clinician.telephone.label" default="Telephone" /></label>
						<div>
							<g:textField class="form-control" name="telephone" value="${clinicianInstance?.telephone}"/>
							<span class="help-inline">${hasErrors(bean: clinicianInstance, field: 'telephone', 'error')}</span>
						</div>
					</div>
				</div>

				<div class="col-lg-6">
					<div class="${hasErrors(bean: clinicianInstance, field: 'centreName', 'error')} required">
						<label for="centreName" class="control-label"><g:message code="clinician.centreName.label" default="Main Centre" /><span class="required-indicator">*</span></label>
						<div>
							<g:select class="form-control" id="centreName" name="centreName.id" from="${Centre.list()?.sort{it?.centreName}}" optionKey="id" required="" value="${clinicianInstance?.centreName?.id}" noSelection="['':'- Choose -']"/>
							<span class="help-inline">${hasErrors(bean: clinicianInstance, field: 'centreName', 'error')}</span>
						</div>
					</div>
				</div>
			</div>

			<div class="row">
				<div class="col-lg-6">
					<div class="${hasErrors(bean: clinicianInstance, field: 'departmentName', 'error')} ">
						<label for="departmentName" class="control-label"><g:message code="clinician.departmentName.label" default="Department Name" /><span class="required-indicator">*</span></label>
						<div>
							<g:select class="form-control" id="departmentName" name="departmentName.id" from="${Department.list()}" optionKey="id" required="" onchange="departmentOtherOpt()" value="${clinicianInstance?.departmentName?.id}" noSelection="['':'- Choose -']"/>
							<span class="help-inline">${hasErrors(bean: clinicianInstance, field: 'departmentName', 'error')}</span>
						</div>
					</div>
				</div>

				<div class="col-lg-6" id="departmentOtherOption">
					<div class="${hasErrors(bean: clinicianInstance, field: 'departmentOther', 'error')} ">
						<label for="departmentOther" class="control-label"><g:message code="clinician.departmentOther.label" default="Specify Department" /></label>
						<div>
							<g:textField class="form-control" id="departmentOther" name="departmentOther" value="${clinicianInstance?.departmentOther}"/>
							<span class="help-inline">${hasErrors(bean: clinicianInstance, field: 'departmentOther', 'error')}</span>
						</div>
					</div>
				</div>
			</div>

			<div class="row">
				<div class="col-lg-6">
					<div class="${hasErrors(bean: clinicianInstance, field: 'roleType', 'error')} ">
						<label for="roleType" class="control-label"><g:message code="clinician.roleType.label" default="Role" /><span class="required-indicator">*</span></label>
						<div>
							<g:select class="form-control" id="roleType" name="roleType.id" from="${rdmdt.RoleType.list()}" optionKey="id" required="" onchange="roleTypeOtherOpt()" value="${clinicianInstance?.roleType?.id}" noSelection="['':'- Choose -']"/>
							<span class="help-inline">${hasErrors(bean: clinicianInstance, field: 'roleType', 'error')}</span>
						</div>
					</div>
				</div>

				<div class="col-lg-6" id="roleTypeOtherOption">
					<div class="${hasErrors(bean: clinicianInstance, field: 'roleTypeOther', 'error')} ">
						<label for="roleTypeOther" class="control-label"><g:message code="clinician.roleTypeOther.label" default="Specify Role" /></label>
						<div>
							<g:textField class="form-control" id="roleTypeOther" name="roleTypeOther" value="${clinicianInstance?.roleTypeOther}"/>
							<span class="help-inline">${hasErrors(bean: clinicianInstance, field: 'roleTypeOther', 'error')}</span>
						</div>
					</div>
				</div>
			</div>

			<div class="row">
				<div class="col-lg-6">
					<div class="${hasErrors(bean: clinicianInstance, field: 'address', 'error')} ">
						<label for="address" class="control-label"><g:message code="clinician.address.label" default="Correspondence address" /></label>
						<div>
							<g:textArea class="form-control" name="address" value="${clinicianInstance?.address}" rows="4" cols="40"/>
							<span class="help-inline">${hasErrors(bean: clinicianInstance, field: 'address', 'error')}</span>
						</div>
					</div>
				</div>

				<div class="col-lg-6">
					<div class="${hasErrors(bean: clinicianInstance, field: 'postcode', 'error')} ">
						<label for="postcode" class="control-label"><g:message code="clinician.postcode.label" default="Postcode" /></label>
						<div>
							<g:textField class="form-control" name="postcode" value="${clinicianInstance?.postcode}"/>
							<span class="help-inline">${hasErrors(bean: clinicianInstance, field: 'postcode', 'error')}</span>
						</div>
					</div>
				</div>
			</div>



<g:javascript plugin="jquery" library="jquery" />
<script>

	departmentOtherOpt();
	function departmentOtherOpt(){
		var departmentName = $("#departmentName").val();
		var departmentOther = $("#departmentOther").val();

		if (departmentName == ${rdmdt.Department.findByDepartmentName('Other')?.id}){
			$("#departmentOtherOption").show();
		}else{
			$("#departmentOtherOption").hide();
			$("#departmentOther").val("");
		}
	}

	roleTypeOtherOpt();
	function roleTypeOtherOpt(){
		var roleType = $("#roleType").val();
		var roleTypeOther = $("#roleTypeOther").val();
		if (roleType == ${rdmdt.RoleType.findByRoleTypeName('Other')?.id}){
			$("#roleTypeOtherOption").show();
		}else{
			$("#roleTypeOtherOption").hide();
			$("#roleTypeOther").val("");
		}
	}

</script>

