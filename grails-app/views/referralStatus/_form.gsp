<%@ page import="rdmdt.ReferralStatus" %>



			<div class="row">
				<div class="col-lg-6">
					<div class="${hasErrors(bean: referralStatusInstance, field: 'referralStatusName', 'error')} ">
						<label for="referralStatusName" class="control-label"><g:message code="referralStatus.referralStatusName.label" default="Referral Status Name" /></label>
						<div>
							<g:textField class="form-control" name="referralStatusName" value="${referralStatusInstance?.referralStatusName}"/>
							<span class="help-inline">${hasErrors(bean: referralStatusInstance, field: 'referralStatusName', 'error')}</span>
						</div>
					</div>
				</div>
			</div>

