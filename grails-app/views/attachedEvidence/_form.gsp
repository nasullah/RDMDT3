<%@ page import="rdmdt.AttachedEvidence" %>



			<div class="row">
				<div class="col-lg-6">
					<div class="${hasErrors(bean: attachedEvidenceInstance, field: 'referralRecord', 'error')} required">
						<label for="referralRecord" class="control-label"><g:message code="attachedEvidence.referralRecord.label" default="Application" /><span class="required-indicator">*</span></label>
						<div>
							<g:select class="form-control" id="referralRecord" name="referralRecord.id" from="${rdmdt.ReferralRecord.list()}" optionKey="id" required="" value="${attachedEvidenceInstance?.referralRecord?.id}"/>
							<span class="help-inline">${hasErrors(bean: attachedEvidenceInstance, field: 'referralRecord', 'error')}</span>
						</div>
					</div>
				</div>

				<div class="col-lg-6">
					<div class="${hasErrors(bean: attachedEvidenceInstance, field: 'type', 'error')} required">
						<label for="type" class="control-label"><g:message code="attachedEvidence.type.label" default="Type" /><span class="required-indicator">*</span></label>
						<div>
							<g:select class="form-control" id="type" name="type.id" from="${rdmdt.AttachedEvidenceType.list()}" optionKey="id" required="" value="${attachedEvidenceInstance?.type?.id}" noSelection="['':'- Choose -']"/>
							<span class="help-inline">${hasErrors(bean: attachedEvidenceInstance, field: 'type', 'error')}</span>
						</div>
					</div>
				</div>

				%{--<div class="col-lg-6">--}%
					%{--<div class="${hasErrors(bean: attachedEvidenceInstance, field: 'addedOn', 'error')} ">--}%
						%{--<label for="addedOn" class="control-label"><g:message code="attachedEvidence.addedOn.label" default="Added On" /></label>--}%
						%{--<div>--}%
							%{--<bs:datePicker name="addedOn" precision="day"  value="${attachedEvidenceInstance?.addedOn}" default="none" noSelection="['': '']" />--}%
							%{--<span class="help-inline">${hasErrors(bean: attachedEvidenceInstance, field: 'addedOn', 'error')}</span>--}%
						%{--</div>--}%
					%{--</div>--}%
				%{--</div>--}%

				<g:if test="${attachedEvidenceInstance?.content == null}">
					<div class="col-lg-6">
						<div class="">
							<label  class="control-label">File</label>
							<div>
								<input type="file" id="attachedEvidenceFile" name="attachedEvidenceFile" />
							</div>
						</div>
					</div>
				</g:if>
			</div>

