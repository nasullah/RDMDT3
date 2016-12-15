<%@ page import="rdmdt.FamilyHistoryType" %>



            <div class="row">
                <div class="col-lg-6">
                    <label class="control-label">Breast And Or Ovarian Cancer</label>
                    <div>
                        <g:if test="${referralRecordInstance?.maternal?.first()?.breastAndOrOvarianCancer?.id}">
                            <g:set var="breastAndOrOvarianCancerMaternal" value="${referralRecordInstance?.maternal?.first()?.breastAndOrOvarianCancer?.id}" />
                        </g:if>
                        <g:else>
                            <g:set var="breastAndOrOvarianCancerMaternal" value="${FamilyHistoryType.findByFamilyHistoryTypeName('Unknown')?.id}" />
                        </g:else>
                        <g:radioGroup name="breastAndOrOvarianCancerMaternal"
                                      values="[FamilyHistoryType.findByFamilyHistoryTypeName('Yes')?.id, FamilyHistoryType.findByFamilyHistoryTypeName('No')?.id, FamilyHistoryType.findByFamilyHistoryTypeName('Unknown')?.id]"
                                      labels="['Yes', 'No', 'Unknown']"
                                      value="${breastAndOrOvarianCancerMaternal}">
                            ${it.radio}  ${it.label} &nbsp;
                        </g:radioGroup>
                    </div>
                </div>

                <div class="col-lg-6">
                    <label class="control-label">Colorectal Cancer</label>
                    <div>
                        <g:if test="${referralRecordInstance?.maternal?.first()?.colorectalCancer?.id}">
                            <g:set var="colorectalCancerMaternal" value="${referralRecordInstance?.maternal?.first()?.colorectalCancer?.id}" />
                        </g:if>
                        <g:else>
                            <g:set var="colorectalCancerMaternal" value="${FamilyHistoryType.findByFamilyHistoryTypeName('Unknown')?.id}" />
                        </g:else>
                        <g:radioGroup name="colorectalCancerMaternal"
                                      values="[FamilyHistoryType.findByFamilyHistoryTypeName('Yes')?.id, FamilyHistoryType.findByFamilyHistoryTypeName('No')?.id, FamilyHistoryType.findByFamilyHistoryTypeName('Unknown')?.id]"
                                      labels="['Yes', 'No', 'Unknown']"
                                      value="${colorectalCancerMaternal}">
                            ${it.radio}  ${it.label} &nbsp;
                        </g:radioGroup>
                    </div>
                </div>

                <div class="col-lg-6">
                    <label class="control-label">Ischaemic Heart Disease Or Stroke</label>
                    <div>
                        <g:if test="${referralRecordInstance?.maternal?.first()?.ischaemicHeartDiseaseOrStroke?.id}">
                            <g:set var="ischaemicHeartDiseaseOrStrokeMaternal" value="${referralRecordInstance?.maternal?.first()?.ischaemicHeartDiseaseOrStroke?.id}" />
                        </g:if>
                        <g:else>
                            <g:set var="ischaemicHeartDiseaseOrStrokeMaternal" value="${FamilyHistoryType.findByFamilyHistoryTypeName('Unknown')?.id}" />
                        </g:else>
                        <g:radioGroup name="ischaemicHeartDiseaseOrStrokeMaternal"
                                      values="[FamilyHistoryType.findByFamilyHistoryTypeName('Yes')?.id, FamilyHistoryType.findByFamilyHistoryTypeName('No')?.id, FamilyHistoryType.findByFamilyHistoryTypeName('Unknown')?.id]"
                                      labels="['Yes', 'No', 'Unknown']"
                                      value="${ischaemicHeartDiseaseOrStrokeMaternal}">
                            ${it.radio}  ${it.label} &nbsp;
                        </g:radioGroup>
                    </div>
                </div>

                <div class="col-lg-6">
                    <label class="control-label">Endocrine Tumours</label>
                    <div>
                        <g:if test="${referralRecordInstance?.maternal?.first()?.endocrineTumours?.id}">
                            <g:set var="endocrineTumoursMaternal" value="${referralRecordInstance?.maternal?.first()?.endocrineTumours?.id}" />
                        </g:if>
                        <g:else>
                            <g:set var="endocrineTumoursMaternal" value="${FamilyHistoryType.findByFamilyHistoryTypeName('Unknown')?.id}" />
                        </g:else>
                        <g:radioGroup name="endocrineTumoursMaternal"
                                      values="[FamilyHistoryType.findByFamilyHistoryTypeName('Yes')?.id, FamilyHistoryType.findByFamilyHistoryTypeName('No')?.id, FamilyHistoryType.findByFamilyHistoryTypeName('Unknown')?.id]"
                                      labels="['Yes', 'No', 'Unknown']"
                                      value="${endocrineTumoursMaternal}">
                            ${it.radio}  ${it.label} &nbsp;
                        </g:radioGroup>
                    </div>
                </div>
            </div>

