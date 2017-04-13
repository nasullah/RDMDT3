<%@ page import="rdmdt.EligibilityType; rdmdt.Penetrance; rdmdt.Consanguinity; rdmdt.Ethnicity; rdmdt.Gender; rdmdt.AgeUnit; rdmdt.ReferralStatus; rdmdt.Program; rdmdt.Patient; rdmdt.AttachedEvidenceType; rdmdt.RelationshipType; rdmdt.Clinician; rdmdt.ReferralRecord" %>



            <div class="row">
                <div class="col-lg-6">
                    <div id="clinicalDetails0">
                        <label class="control-label">Clinical details (please include any features which are notable by their absence)</label>
                        <g:if test="${referralRecordInstance?.clinicalDetails?.getAt(0)?.clinicalDetailsName}">
                            <g:set var="cDetails0" value="${referralRecordInstance?.clinicalDetails?.getAt(0)?.clinicalDetailsName}" />
                        </g:if>
                        <g:else>
                            <g:set var="cDetails0" value="${params.cDetails0}" />
                        </g:else>
                        <div>
                            <g:field class="form-control" name="cDetails0" type="text" value="${cDetails0}"/>
                        </div>
                    </div>
                </div>

                <div class="col-lg-6">
                    <div id="addClinicalDetailsButton">
                        <label class="control-label">Add More Clinical Details </label>
                        <div>
                            <button type="button" id="addClinicalButton" class="btn btn-primary btn" value="add" onClick= 'addClinicalDetails()'><span class="glyphicon glyphicon-plus"></span> Add</button>
                        </div>
                    </div>
                </div>
            </div>

            <div class="row">
                <div class="col-lg-6">
                    <div id="clinicalDetails1">
                        <label class="control-label">Clinical details (please include any features which are notable by their absence)</label>
                        <g:if test="${referralRecordInstance?.clinicalDetails?.getAt(1)?.clinicalDetailsName}">
                            <g:set var="cDetails1" value="${referralRecordInstance?.clinicalDetails?.getAt(1)?.clinicalDetailsName}" />
                        </g:if>
                        <g:else>
                            <g:set var="cDetails1" value="${params.cDetails1}" />
                        </g:else>
                        <div>
                            <g:field class="form-control" name="cDetails1" type="text" value="${cDetails1}"/>
                        </div>
                    </div>
                </div>

                <div class="col-lg-6">
                    <div id="clinicalDetails2">
                        <label class="control-label">Clinical details (please include any features which are notable by their absence)</label>
                        <g:if test="${referralRecordInstance?.clinicalDetails?.getAt(2)?.clinicalDetailsName}">
                            <g:set var="cDetails2" value="${referralRecordInstance?.clinicalDetails?.getAt(2)?.clinicalDetailsName}" />
                        </g:if>
                        <g:else>
                            <g:set var="cDetails2" value="${params.cDetails2}" />
                        </g:else>
                        <div>
                            <g:field class="form-control" name="cDetails2" type="text" value="${cDetails2}"/>
                        </div>
                    </div>
                </div>

                <div class="col-lg-6">
                    <div id="clinicalDetails3">
                        <label class="control-label">Clinical details (please include any features which are notable by their absence)</label>
                        <g:if test="${referralRecordInstance?.clinicalDetails?.getAt(3)?.clinicalDetailsName}">
                            <g:set var="cDetails3" value="${referralRecordInstance?.clinicalDetails?.getAt(3)?.clinicalDetailsName}" />
                        </g:if>
                        <g:else>
                            <g:set var="cDetails3" value="${params.cDetails3}" />
                        </g:else>
                        <div>
                            <g:field class="form-control" name="cDetails3" type="text" value="${cDetails3}"/>
                        </div>
                    </div>
                </div>

                <div class="col-lg-6">
                    <div id="clinicalDetails4">
                        <label class="control-label">Clinical details (please include any features which are notable by their absence)</label>
                        <g:if test="${referralRecordInstance?.clinicalDetails?.getAt(4)?.clinicalDetailsName}">
                            <g:set var="cDetails4" value="${referralRecordInstance?.clinicalDetails?.getAt(4)?.clinicalDetailsName}" />
                        </g:if>
                        <g:else>
                            <g:set var="cDetails4" value="${params.cDetails4}" />
                        </g:else>
                        <div>
                            <g:field class="form-control" name="cDetails4" type="text" value="${cDetails4}"/>
                        </div>
                    </div>
                </div>

                <div class="col-lg-6">
                    <div id="clinicalDetails5">
                        <label class="control-label">Clinical details (please include any features which are notable by their absence)</label>
                        <g:if test="${referralRecordInstance?.clinicalDetails?.getAt(5)?.clinicalDetailsName}">
                            <g:set var="cDetails5" value="${referralRecordInstance?.clinicalDetails?.getAt(5)?.clinicalDetailsName}" />
                        </g:if>
                        <g:else>
                            <g:set var="cDetails5" value="${params.cDetails5}" />
                        </g:else>
                        <div>
                            <g:field class="form-control" name="cDetails5" type="text" value="${cDetails5}"/>
                        </div>
                    </div>
                </div>

                <div class="col-lg-6">
                    <div id="clinicalDetails6">
                        <label class="control-label">Clinical details (please include any features which are notable by their absence)</label>
                        <g:if test="${referralRecordInstance?.clinicalDetails?.getAt(6)?.clinicalDetailsName}">
                            <g:set var="cDetails6" value="${referralRecordInstance?.clinicalDetails?.getAt(6)?.clinicalDetailsName}" />
                        </g:if>
                        <g:else>
                            <g:set var="cDetails6" value="${params.cDetails6}" />
                        </g:else>
                        <div>
                            <g:field class="form-control" name="cDetails6" type="text" value="${cDetails6}"/>
                        </div>
                    </div>
                </div>

                <div class="col-lg-6">
                    <div id="clinicalDetails7">
                        <label class="control-label">Clinical details (please include any features which are notable by their absence)</label>
                        <g:if test="${referralRecordInstance?.clinicalDetails?.getAt(7)?.clinicalDetailsName}">
                            <g:set var="cDetails7" value="${referralRecordInstance?.clinicalDetails?.getAt(7)?.clinicalDetailsName}" />
                        </g:if>
                        <g:else>
                            <g:set var="cDetails7" value="${params.cDetails7}" />
                        </g:else>
                        <div>
                            <g:field class="form-control" name="cDetails7" type="text" value="${cDetails7}"/>
                        </div>
                    </div>
                </div>

                <div class="col-lg-6">
                    <div id="clinicalDetails8">
                        <label class="control-label">Clinical details (please include any features which are notable by their absence)</label>
                        <g:if test="${referralRecordInstance?.clinicalDetails?.getAt(8)?.clinicalDetailsName}">
                            <g:set var="cDetails8" value="${referralRecordInstance?.clinicalDetails?.getAt(8)?.clinicalDetailsName}" />
                        </g:if>
                        <g:else>
                            <g:set var="cDetails8" value="${params.cDetails8}" />
                        </g:else>
                        <div>
                            <g:field class="form-control" name="cDetails8" type="text" value="${cDetails8}"/>
                        </div>
                    </div>
                </div>

                <div class="col-lg-6">
                    <div id="clinicalDetails9">
                        <label class="control-label">Clinical details (please include any features which are notable by their absence)</label>
                        <g:if test="${referralRecordInstance?.clinicalDetails?.getAt(9)?.clinicalDetailsName}">
                            <g:set var="cDetails9" value="${referralRecordInstance?.clinicalDetails?.getAt(9)?.clinicalDetailsName}" />
                        </g:if>
                        <g:else>
                            <g:set var="cDetails9" value="${params.cDetails9}" />
                        </g:else>
                        <div>
                            <g:field class="form-control" name="cDetails9" type="text" value="${cDetails9}"/>
                        </div>
                    </div>
                </div>

                <div class="col-lg-6">
                    <div id="clinicalDetails10">
                        <label class="control-label">Clinical details (please include any features which are notable by their absence)</label>
                        <g:if test="${referralRecordInstance?.clinicalDetails?.getAt(10)?.clinicalDetailsName}">
                            <g:set var="cDetails10" value="${referralRecordInstance?.clinicalDetails?.getAt(10)?.clinicalDetailsName}" />
                        </g:if>
                        <g:else>
                            <g:set var="cDetails10" value="${params.cDetails10}" />
                        </g:else>
                        <div>
                            <g:field class="form-control" name="cDetails10" type="text" value="${cDetails10}"/>
                        </div>
                    </div>
                </div>

                <div class="col-lg-6">
                    <div id="clinicalDetails11">
                        <label class="control-label">Clinical details (please include any features which are notable by their absence)</label>
                        <g:if test="${referralRecordInstance?.clinicalDetails?.getAt(11)?.clinicalDetailsName}">
                            <g:set var="cDetails11" value="${referralRecordInstance?.clinicalDetails?.getAt(11)?.clinicalDetailsName}" />
                        </g:if>
                        <g:else>
                            <g:set var="cDetails11" value="${params.cDetails11}" />
                        </g:else>
                        <div>
                            <g:field class="form-control" name="cDetails11" type="text" value="${cDetails11}"/>
                        </div>
                    </div>
                </div>

                <div class="col-lg-6">
                    <div id="clinicalDetails12">
                        <label class="control-label">Clinical details (please include any features which are notable by their absence)</label>
                        <g:if test="${referralRecordInstance?.clinicalDetails?.getAt(12)?.clinicalDetailsName}">
                            <g:set var="cDetails12" value="${referralRecordInstance?.clinicalDetails?.getAt(12)?.clinicalDetailsName}" />
                        </g:if>
                        <g:else>
                            <g:set var="cDetails12" value="${params.cDetails12}" />
                        </g:else>
                        <div>
                            <g:field class="form-control" name="cDetails12" type="text" value="${cDetails12}"/>
                        </div>
                    </div>
                </div>

                <div class="col-lg-6">
                    <div id="clinicalDetails13">
                        <label class="control-label">Clinical details (please include any features which are notable by their absence)</label>
                        <g:if test="${referralRecordInstance?.clinicalDetails?.getAt(13)?.clinicalDetailsName}">
                            <g:set var="cDetails13" value="${referralRecordInstance?.clinicalDetails?.getAt(13)?.clinicalDetailsName}" />
                        </g:if>
                        <g:else>
                            <g:set var="cDetails13" value="${params.cDetails13}" />
                        </g:else>
                        <div>
                            <g:field class="form-control" name="cDetails13" type="text" value="${cDetails13}"/>
                        </div>
                    </div>
                </div>

                <div class="col-lg-6">
                    <div id="clinicalDetails14">
                        <label class="control-label">Clinical details (please include any features which are notable by their absence)</label>
                        <g:if test="${referralRecordInstance?.clinicalDetails?.getAt(14)?.clinicalDetailsName}">
                            <g:set var="cDetails14" value="${referralRecordInstance?.clinicalDetails?.getAt(14)?.clinicalDetailsName}" />
                        </g:if>
                        <g:else>
                            <g:set var="cDetails14" value="${params.cDetails14}" />
                        </g:else>
                        <div>
                            <g:field class="form-control" name="cDetails14" type="text" value="${cDetails14}"/>
                        </div>
                    </div>
                </div>
            </div>