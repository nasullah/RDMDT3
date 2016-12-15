<%@ page import="rdmdt.Consanguinity" %>



			<div class="row">
				<div class="col-lg-6">
					<div class="${hasErrors(bean: consanguinityInstance, field: 'consanguinityEvidence', 'error')} ">
						<label for="consanguinityEvidence" class="control-label"><g:message code="consanguinity.consanguinityEvidence.label" default="Consanguinity Evidence" /></label>
						<div>
							<g:textField class="form-control" name="consanguinityEvidence" value="${consanguinityInstance?.consanguinityEvidence}"/>
							<span class="help-inline">${hasErrors(bean: consanguinityInstance, field: 'consanguinityEvidence', 'error')}</span>
						</div>
					</div>
				</div>
			</div>

