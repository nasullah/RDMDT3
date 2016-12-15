<%@ page import="rdmdt.FamilyHistoryType" %>




            <div class="row">
                <div class="col-lg-6">
                    <label class="control-label">Breast And Or Ovarian Cancer</label>
                    <div>
                        <g:if test="${referralRecordInstance?.paternal?.first()?.breastAndOrOvarianCancer?.id}">
                            <g:set var="breastAndOrOvarianCancerPaternal" value="${referralRecordInstance?.paternal?.first()?.breastAndOrOvarianCancer?.id}" />
                        </g:if>
                        <g:else>
                            <g:set var="breastAndOrOvarianCancerPaternal" value="${FamilyHistoryType.findByFamilyHistoryTypeName('Unknown')?.id}" />
                        </g:else>
                        <g:radioGroup name="breastAndOrOvarianCancerPaternal"
                                      values="[FamilyHistoryType.findByFamilyHistoryTypeName('Yes')?.id, FamilyHistoryType.findByFamilyHistoryTypeName('No')?.id, FamilyHistoryType.findByFamilyHistoryTypeName('Unknown')?.id]"
                                      labels="['Yes', 'No', 'Unknown']"
                                      value="${breastAndOrOvarianCancerPaternal}">
                            ${it.radio}  ${it.label} &nbsp;
                        </g:radioGroup>
                    </div>
                </div>

                <div class="col-lg-6">
                    <label class="control-label">Colorectal Cancer</label>
                    <div>
                        <g:if test="${referralRecordInstance?.paternal?.first()?.colorectalCancer?.id}">
                            <g:set var="colorectalCancerPaternal" value="${referralRecordInstance?.paternal?.first()?.colorectalCancer?.id}" />
                        </g:if>
                        <g:else>
                            <g:set var="colorectalCancerPaternal" value="${FamilyHistoryType.findByFamilyHistoryTypeName('Unknown')?.id}" />
                        </g:else>
                        <g:radioGroup name="colorectalCancerPaternal"
                                      values="[FamilyHistoryType.findByFamilyHistoryTypeName('Yes')?.id, FamilyHistoryType.findByFamilyHistoryTypeName('No')?.id, FamilyHistoryType.findByFamilyHistoryTypeName('Unknown')?.id]"
                                      labels="['Yes', 'No', 'Unknown']"
                                      value="${colorectalCancerPaternal}">
                            ${it.radio}  ${it.label} &nbsp;
                        </g:radioGroup>
                    </div>
                </div>

                <div class="col-lg-6">
                    <label class="control-label">Ischaemic Heart Disease Or Stroke</label>
                    <div>
                        <g:if test="${referralRecordInstance?.paternal?.first()?.ischaemicHeartDiseaseOrStroke?.id}">
                            <g:set var="ischaemicHeartDiseaseOrStrokePaternal" value="${referralRecordInstance?.paternal?.first()?.ischaemicHeartDiseaseOrStroke?.id}" />
                        </g:if>
                        <g:else>
                            <g:set var="ischaemicHeartDiseaseOrStrokePaternal" value="${FamilyHistoryType.findByFamilyHistoryTypeName('Unknown')?.id}" />
                        </g:else>
                        <g:radioGroup name="ischaemicHeartDiseaseOrStrokePaternal"
                                      values="[FamilyHistoryType.findByFamilyHistoryTypeName('Yes')?.id, FamilyHistoryType.findByFamilyHistoryTypeName('No')?.id, FamilyHistoryType.findByFamilyHistoryTypeName('Unknown')?.id]"
                                      labels="['Yes', 'No', 'Unknown']"
                                      value="${ischaemicHeartDiseaseOrStrokePaternal}">
                            ${it.radio}  ${it.label} &nbsp;
                        </g:radioGroup>
                    </div>
                </div>

                <div class="col-lg-6">
                    <label class="control-label">Endocrine Tumours</label>
                    <div>
                        <g:if test="${referralRecordInstance?.paternal?.first()?.endocrineTumours?.id}">
                            <g:set var="endocrineTumoursPaternal" value="${referralRecordInstance?.paternal?.first()?.endocrineTumours?.id}" />
                        </g:if>
                        <g:else>
                            <g:set var="endocrineTumoursPaternal" value="${FamilyHistoryType.findByFamilyHistoryTypeName('Unknown')?.id}" />
                        </g:else>
                        <g:radioGroup name="endocrineTumoursPaternal"
                                      values="[FamilyHistoryType.findByFamilyHistoryTypeName('Yes')?.id, FamilyHistoryType.findByFamilyHistoryTypeName('No')?.id, FamilyHistoryType.findByFamilyHistoryTypeName('Unknown')?.id]"
                                      labels="['Yes', 'No', 'Unknown']"
                                      value="${endocrineTumoursPaternal}">
                            ${it.radio}  ${it.label} &nbsp;
                        </g:radioGroup>
                    </div>
                </div>
            </div>