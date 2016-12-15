<%@ page import="rdmdt.EligibilityType; rdmdt.Penetrance; rdmdt.Consanguinity; rdmdt.Ethnicity; rdmdt.Gender; rdmdt.AgeUnit; rdmdt.ReferralStatus; rdmdt.Program; rdmdt.Patient; rdmdt.AttachedEvidenceType; rdmdt.RelationshipType; rdmdt.Clinician; rdmdt.ReferralRecord" %>


<div class="row" id="coapplicantDetails1" style="display: none;">
                <div class="col-lg-6">
                    <div class="">
                        <label class="control-label">Co-applicant (enter name)</label>
                        <g:if test="${referralRecordInstance?.coApplicants?.getAt(0)}">
                            <g:set var="coapplicant1Value" value="${referralRecordInstance?.coApplicants?.getAt(0)}" />
                            <g:set var="coapplicant1Id" value="${referralRecordInstance?.coApplicants?.getAt(0)?.id}" />
                        </g:if>
                        <g:else>
                            <g:set var="coapplicant1Value" value="${Clinician.findById(params.coapplicant1)}" />
                            <g:set var="coapplicant1Id" value="${params.coapplicant1}" />
                        </g:else>
                        %{--<richui:autoComplete class="form-control"  name="coapplicantName1" action="${createLinkTo('dir': 'clinician/findClinician')}" value="${coapplicant1Value}" onItemSelect="callCoApplicant1(id)" maxResultsDisplayed="20" minQueryLength="2"/>--}%
                        <div>
                            <auto-complete
                                css="form-control"
                                name="coapplicantName1"
                                action="${createLink('controller': 'clinician','action': 'findClinician')}?format=json&query=%QUERY%"
                                action-token="%QUERY%"
                                item-selected.delegate="callCoApplicant1($event.detail.value)"
                                max-items="20"
                                min-chars="2"
                                value="${coapplicant1Value}"></auto-complete>
                        </div>
                        <g:hiddenField id ="coapplicant1" name ="coapplicant1" value="${coapplicant1Id}"/>
                    </div>
                </div>
            </div>

            <div class="row" id="coapplicantDetails2" style="display: none;">
                <div class="col-lg-6">
                    <div class="">
                        <label class="control-label">Co-applicant (enter name)</label>
                        <g:if test="${referralRecordInstance?.coApplicants?.getAt(1)}">
                            <g:set var="coapplicant2Value" value="${referralRecordInstance?.coApplicants?.getAt(1)}" />
                            <g:set var="coapplicant2Id" value="${referralRecordInstance?.coApplicants?.getAt(1)?.id}" />
                        </g:if>
                        <g:else>
                            <g:set var="coapplicant2Value" value="${Clinician.findById(params.coapplicant2)}" />
                            <g:set var="coapplicant2Id" value="${params.coapplicant2}" />
                        </g:else>
                        %{--<richui:autoComplete class="form-control"  name="coapplicantName2" action="${createLinkTo('dir': 'clinician/findClinician')}" value="${coapplicant2Value}" onItemSelect="callCoApplicant2(id)" maxResultsDisplayed="20" minQueryLength="2" />--}%
                        <div>
                            <auto-complete
                                css="form-control"
                                name="coapplicantName2"
                                action="${createLink('controller': 'clinician','action': 'findClinician')}?format=json&query=%QUERY%"
                                action-token="%QUERY%"
                                item-selected.delegate="callCoApplicant2($event.detail.value)"
                                max-items="20"
                                min-chars="2"
                                value="${coapplicant2Value}"></auto-complete>
                        </div>
                        <g:hiddenField id ="coapplicant2" name ="coapplicant2" value="${coapplicant2Id}"/>
                    </div>
                </div>
            </div>

            <div class="row" id="coapplicantDetails3" style="display: none;">
                <div class="col-lg-6">
                    <div class="">
                        <label class="control-label">Co-applicant (enter name)</label>
                        <g:if test="${referralRecordInstance?.coApplicants?.getAt(2)}">
                            <g:set var="coapplicant3Value" value="${referralRecordInstance?.coApplicants?.getAt(2)}" />
                            <g:set var="coapplicant3Id" value="${referralRecordInstance?.coApplicants?.getAt(2)?.id}" />
                        </g:if>
                        <g:else>
                            <g:set var="coapplicant3Value" value="${Clinician.findById(params.coapplicant3)}" />
                            <g:set var="coapplicant3Id" value="${params.coapplicant3}" />
                        </g:else>
                        %{--<richui:autoComplete class="form-control"  name="coapplicantName3" action="${createLinkTo('dir': 'clinician/findClinician')}" value="${coapplicant3Value}" onItemSelect="callCoApplicant3(id)" maxResultsDisplayed="20" minQueryLength="2" />--}%
                        <div>
                            <auto-complete
                                css="form-control"
                                name="coapplicantName3"
                                action="${createLink('controller': 'clinician','action': 'findClinician')}?format=json&query=%QUERY%"
                                action-token="%QUERY%"
                                item-selected.delegate="callCoApplicant3($event.detail.value)"
                                max-items="20"
                                min-chars="2"
                                value="${coapplicant3Value}"></auto-complete>
                        </div>
                        <g:hiddenField id ="coapplicant3" name ="coapplicant3" value="${coapplicant3Id}"/>
                    </div>
                </div>
            </div>

            <div class="row" id="coapplicantDetails4" style="display: none;">
                <div class="col-lg-6">
                    <div class="">
                        <label class="control-label">Co-applicant (enter name)</label>
                        <g:if test="${referralRecordInstance?.coApplicants?.getAt(3)}">
                            <g:set var="coapplicant4Value" value="${referralRecordInstance?.coApplicants?.getAt(3)}" />
                            <g:set var="coapplicant4Id" value="${referralRecordInstance?.coApplicants?.getAt(3)?.id}" />
                        </g:if>
                        <g:else>
                            <g:set var="coapplicant4Value" value="${Clinician.findById(params.coapplicant4)}" />
                            <g:set var="coapplicant4Id" value="${params.coapplicant4}" />
                        </g:else>
                        %{--<richui:autoComplete class="form-control"  name="coapplicantName4" action="${createLinkTo('dir': 'clinician/findClinician')}" value="${coapplicant4Value}" onItemSelect="callCoApplicant4(id)" maxResultsDisplayed="20" minQueryLength="2" />--}%
                        <div>
                            <auto-complete
                                css="form-control"
                                name="coapplicantName4"
                                action="${createLink('controller': 'clinician','action': 'findClinician')}?format=json&query=%QUERY%"
                                action-token="%QUERY%"
                                item-selected.delegate="callCoApplicant4($event.detail.value)"
                                max-items="20"
                                min-chars="2"
                                value="${coapplicant4Value}"></auto-complete>
                        </div>
                        <g:hiddenField id ="coapplicant4" name ="coapplicant4" value="${coapplicant4Id}"/>
                    </div>
                </div>
            </div>

            <div class="row" id="coapplicantDetails5" style="display: none;">
                <div class="col-lg-6">
                    <div class="">
                        <label class="control-label">Co-applicant (enter name)</label>
                        <g:if test="${referralRecordInstance?.coApplicants?.getAt(4)}">
                            <g:set var="coapplicant5Value" value="${referralRecordInstance?.coApplicants?.getAt(4)}" />
                            <g:set var="coapplicant5Id" value="${referralRecordInstance?.coApplicants?.getAt(4)?.id}" />
                        </g:if>
                        <g:else>
                            <g:set var="coapplicant5Value" value="${Clinician.findById(params.coapplicant5)}" />
                            <g:set var="coapplicant5Id" value="${params.coapplicant5}" />
                        </g:else>
                        %{--<richui:autoComplete class="form-control"  name="coapplicantName4" action="${createLinkTo('dir': 'clinician/findClinician')}" value="${coapplicant5Value}" onItemSelect="callCoApplicant5(id)" maxResultsDisplayed="20" minQueryLength="2" />--}%
                        <div>
                            <auto-complete
                                css="form-control"
                                name="coapplicantName5"
                                action="${createLink('controller': 'clinician','action': 'findClinician')}?format=json&query=%QUERY%"
                                action-token="%QUERY%"
                                item-selected.delegate="callCoApplicant5($event.detail.value)"
                                max-items="20"
                                min-chars="2"
                                value="${coapplicant5Value}"></auto-complete>
                        </div>
                        <g:hiddenField id ="coapplicant5" name ="coapplicant5" value="${coapplicant5Id}"/>
                    </div>
                </div>
            </div>