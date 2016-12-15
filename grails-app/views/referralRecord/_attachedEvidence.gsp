<%@ page import="rdmdt.EligibilityType; rdmdt.Penetrance; rdmdt.Consanguinity; rdmdt.Ethnicity; rdmdt.Gender; rdmdt.AgeUnit; rdmdt.ReferralStatus; rdmdt.Program; rdmdt.Patient; rdmdt.AttachedEvidenceType; rdmdt.RelationshipType; rdmdt.Clinician; rdmdt.ReferralRecord" %>


            <div class="row" id="attachedEvidenceTypeDetails2">
                <div class="col-lg-3">
                    <div>
                        <label class="control-label">Type</label>
                        <div>
                            <g:select class="form-control" id="attachedEvidenceType2" name="attachedEvidenceType2" from="${AttachedEvidenceType.list()}" optionKey="id"  noSelection="['':'- Choose -']"/>
                        </div>
                    </div>
                </div>

                <div class="col-lg-3">
                    <div class="">
                        <label  class="control-label">File</label>
                        <div>
                            <input type="file" id="attachedEvidenceFile2" name="attachedEvidenceFile2" onmousedown="attachedDocument2()" />
                        </div>
                    </div>
                </div>
            </div>

            <div class="row" id="attachedEvidenceTypeDetails3">
                <div class="col-lg-3">
                    <div>
                        <label class="control-label">Type</label>
                        <div>
                            <g:select class="form-control" id="attachedEvidenceType3" name="attachedEvidenceType3" from="${AttachedEvidenceType.list()}" optionKey="id"  noSelection="['':'- Choose -']"/>
                        </div>
                    </div>
                </div>

                <div class="col-lg-3">
                    <div class="">
                        <label  class="control-label">File</label>
                        <div>
                            <input type="file" id="attachedEvidenceFile3" name="attachedEvidenceFile3" onmousedown="attachedDocument3()"/>
                        </div>
                    </div>
                </div>
            </div>

            <div class="row" id="attachedEvidenceTypeDetails4">
                <div class="col-lg-3">
                    <div>
                        <label class="control-label">Type</label>
                        <div>
                            <g:select class="form-control" id="attachedEvidenceType4" name="attachedEvidenceType4" from="${AttachedEvidenceType.list()}" optionKey="id"  noSelection="['':'- Choose -']"/>
                        </div>
                    </div>
                </div>

                <div class="col-lg-3">
                    <div class="">
                        <label  class="control-label">File</label>
                        <div>
                            <input type="file" id="attachedEvidenceFile4" name="attachedEvidenceFile4" onmousedown="attachedDocument4()"/>
                        </div>
                    </div>
                </div>
            </div>

            <div class="row" id="attachedEvidenceTypeDetails5">
                <div class="col-lg-3">
                    <div>
                        <label class="control-label">Type</label>
                        <div>
                            <g:select class="form-control" id="attachedEvidenceType5" name="attachedEvidenceType5" from="${AttachedEvidenceType.list()}" optionKey="id"  noSelection="['':'- Choose -']"/>
                        </div>
                    </div>
                </div>

                <div class="col-lg-3">
                    <div class="">
                        <label  class="control-label">File</label>
                        <div>
                            <input type="file" id="attachedEvidenceFile5" name="attachedEvidenceFile5" onmousedown="attachedDocument5()"/>
                        </div>
                    </div>
                </div>
            </div>