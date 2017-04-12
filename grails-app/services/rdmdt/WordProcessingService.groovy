package rdmdt

import grails.transaction.Transactional
import org.docx4j.openpackaging.packages.WordprocessingMLPackage
import org.grails.web.util.WebUtils

/**
 * WordProcessingService
 * A service class encapsulates the core business logic of a Grails application
 */
@Transactional
class WordProcessingService {

    def processingWordDocument(ReferralRecord referralRecordInstance) {
        WordprocessingMLPackage wordMLPackage = WordprocessingMLPackage.createPackage()
        def mainPart = wordMLPackage.getMainDocumentPart()
        def title = "<w:p xmlns:w=\"http://schemas.openxmlformats.org/wordprocessingml/2006/main\" >\n" +
                "                    <w:pPr>\n" +
                "                        <w:jc w:val=\"center\"/>\n" +
                "                        <w:rPr>\n" +
                "                            <w:rFonts w:ascii=\"Calibri\" w:hAnsi=\"Calibri\" w:cs=\"Calibri\"/>\n" +
                "                            <w:b/>\n" +
                "                            <w:sz w:val=\"22\"/>\n" +
                "                            <w:szCs w:val=\"22\"/>\n" +
                "                            <w:u w:val=\"single\"/>\n" +
                "                        </w:rPr>\n" +
                "                    </w:pPr>\n" +
                "                    <w:r>\n" +
                "                        <w:rPr>\n" +
                "                        <w:jc w:val=\"center\"/>\n" +
                "                            <w:rFonts w:ascii=\"Calibri\" w:hAnsi=\"Calibri\" w:cs=\"Calibri\"/>\n" +
                "                            <w:b/>\n" +
                "                            <w:sz w:val=\"22\"/>\n" +
                "                            <w:szCs w:val=\"22\"/>\n" +
                "                            <w:u w:val=\"single\"/>\n" +
                "                        </w:rPr>\n" +
                "                        <w:t>CLINICAL GENOMIC SEQUENCING APPLICATION FORM </w:t>\n" +
                "                    </w:r>\n" +
                "                </w:p>";

        def monthYear = "<w:p xmlns:w=\"http://schemas.openxmlformats.org/wordprocessingml/2006/main\" >\n" +
                "                    <w:pPr>\n" +
                "                        <w:jc w:val=\"center\"/>\n" +
                "                        <w:rPr>\n" +
                "                            <w:rFonts w:ascii=\"Calibri\" w:hAnsi=\"Calibri\" w:eastAsia=\"Calibri\" w:cs=\"Calibri\"/>\n" +
                "                            <w:b/>\n" +
                "                            <w:u w:val=\"single\"/>\n" +
                "                        </w:rPr>\n" +
                "                    </w:pPr>\n" +
                "                    <w:r>\n" +
                "                        <w:rPr>\n" +
                "                            <w:rFonts w:ascii=\"Calibri\" w:hAnsi=\"Calibri\" w:eastAsia=\"Calibri\" w:cs=\"Calibri\"/>\n" +
                "                            <w:b/>\n" +
                "                            <w:u w:val=\"single\"/>\n" +
                "                        </w:rPr>\n" +
                "                        <w:lastRenderedPageBreak/>\n" +
                "                        <w:t xml:space=\"preserve\">June 2016</w:t>\n" +
                "                    </w:r>\n" +
                "                </w:p>";

        def applicantInformation = "<w:p xmlns:w=\"http://schemas.openxmlformats.org/wordprocessingml/2006/main\" >\n" +
                "                    <w:pPr>\n" +
                "                        <w:spacing w:after=\"120\"/>\n" +
                "                        <w:rPr>\n" +
                "                            <w:rFonts w:eastAsia=\"Calibri\" w:cs=\"Calibri\" w:asciiTheme=\"minorHAnsi\" w:hAnsiTheme=\"minorHAnsi\"/>\n" +
                "                            <w:b/>\n" +
                "                            <w:sz w:val=\"22\"/>\n" +
                "                            <w:szCs w:val=\"22\"/>\n" +
                "                            <w:u w:val=\"single\"/>\n" +
                "                        </w:rPr>\n" +
                "                    </w:pPr>\n" +
                "                    <w:proofErr w:type=\"gramStart\"/>\n" +
                "                    <w:r>\n" +
                "                        <w:rPr>\n" +
                "                            <w:rFonts w:eastAsia=\"Calibri\" w:cs=\"Calibri\" w:asciiTheme=\"minorHAnsi\" w:hAnsiTheme=\"minorHAnsi\"/>\n" +
                "                            <w:b/>\n" +
                "                            <w:sz w:val=\"22\"/>\n" +
                "                            <w:szCs w:val=\"22\"/>\n" +
                "                            <w:u w:val=\"single\"/>\n" +
                "                        </w:rPr>\n" +
                "                        <w:t>Applicant Information</w:t>\n" +
                "                    </w:r>\n" +
                "                </w:p>";

        def clinician = "<w:p xmlns:w=\"http://schemas.openxmlformats.org/wordprocessingml/2006/main\">\n" +
                "                    <w:pPr>\n" +
                "                        <w:spacing w:after=\"120\"/>\n" +
                "                        <w:rPr>\n" +
                "                            <w:rFonts w:eastAsia=\"Calibri\" w:cs=\"Calibri\" w:asciiTheme=\"minorHAnsi\" w:hAnsiTheme=\"minorHAnsi\"/>\n" +
                "                            <w:sz w:val=\"21\"/>\n" +
                "                            <w:szCs w:val=\"21\"/>\n" +
                "                        </w:rPr>\n" +
                "                    </w:pPr>\n" +
                "                    <w:r>\n" +
                "                        <w:rPr>\n" +
                "                            <w:rFonts w:eastAsia=\"Calibri\" w:cs=\"Calibri\" w:asciiTheme=\"minorHAnsi\" w:hAnsiTheme=\"minorHAnsi\"/>\n" +
                "                            <w:sz w:val=\"21\"/>\n" +
                "                            <w:szCs w:val=\"21\"/>\n" +
                "                        </w:rPr>\n" +
                "                        <w:t xml:space=\"preserve\">Applicant: ${referralRecordInstance.clinician.forename.toString()} ${referralRecordInstance.clinician.surname.toString()}</w:t>\n" +
                "                    </w:r>\n" +
                "                </w:p>"

        def clinicianTelephoneNumber = "<w:p xmlns:w=\"http://schemas.openxmlformats.org/wordprocessingml/2006/main\">\n" +
                "                    <w:pPr>\n" +
                "                        <w:spacing w:after=\"120\"/>\n" +
                "                        <w:rPr>\n" +
                "                            <w:rFonts w:eastAsia=\"Calibri\" w:cs=\"Calibri\" w:asciiTheme=\"minorHAnsi\" w:hAnsiTheme=\"minorHAnsi\"/>\n" +
                "                            <w:sz w:val=\"21\"/>\n" +
                "                            <w:szCs w:val=\"21\"/>\n" +
                "                        </w:rPr>\n" +
                "                    </w:pPr>\n" +
                "                    <w:r>\n" +
                "                        <w:rPr>\n" +
                "                            <w:rFonts w:eastAsia=\"Calibri\" w:cs=\"Calibri\" w:asciiTheme=\"minorHAnsi\" w:hAnsiTheme=\"minorHAnsi\"/>\n" +
                "                            <w:sz w:val=\"21\"/>\n" +
                "                            <w:szCs w:val=\"21\"/>\n" +
                "                        </w:rPr>\n" +
                "                        <w:t xml:space=\"preserve\">Applicant Telephone Number: ${referralRecordInstance.clinician.telephone.toString() ?: ''}</w:t>\n" +
                "                    </w:r>\n" +
                "                </w:p>"

        def responsibleConsultant = "<w:p xmlns:w=\"http://schemas.openxmlformats.org/wordprocessingml/2006/main\">\n" +
                "                    <w:pPr>\n" +
                "                        <w:spacing w:after=\"120\"/>\n" +
                "                        <w:rPr>\n" +
                "                            <w:rFonts w:eastAsia=\"Calibri\" w:cs=\"Calibri\" w:asciiTheme=\"minorHAnsi\" w:hAnsiTheme=\"minorHAnsi\"/>\n" +
                "                            <w:sz w:val=\"21\"/>\n" +
                "                            <w:szCs w:val=\"21\"/>\n" +
                "                        </w:rPr>\n" +
                "                    </w:pPr>\n" +
                "                    <w:r>\n" +
                "                        <w:rPr>\n" +
                "                            <w:rFonts w:eastAsia=\"Calibri\" w:cs=\"Calibri\" w:asciiTheme=\"minorHAnsi\" w:hAnsiTheme=\"minorHAnsi\"/>\n" +
                "                            <w:sz w:val=\"21\"/>\n" +
                "                            <w:szCs w:val=\"21\"/>\n" +
                "                        </w:rPr>\n" +
                "                        <w:t xml:space=\"preserve\">Responsible Consultant: ${referralRecordInstance.correspondingClinician.forename.toString()} ${referralRecordInstance.correspondingClinician.surname.toString()}</w:t>\n" +
                "                    </w:r>\n" +
                "                </w:p>"

        def coApplicants = "<w:p xmlns:w=\"http://schemas.openxmlformats.org/wordprocessingml/2006/main\">\n" +
                "                    <w:pPr>\n" +
                "                        <w:spacing w:after=\"120\"/>\n" +
                "                        <w:rPr>\n" +
                "                            <w:rFonts w:eastAsia=\"Calibri\" w:cs=\"Calibri\" w:asciiTheme=\"minorHAnsi\" w:hAnsiTheme=\"minorHAnsi\"/>\n" +
                "                            <w:sz w:val=\"21\"/>\n" +
                "                            <w:szCs w:val=\"21\"/>\n" +
                "                        </w:rPr>\n" +
                "                    </w:pPr>\n" +
                "                    <w:r>\n" +
                "                        <w:rPr>\n" +
                "                            <w:rFonts w:eastAsia=\"Calibri\" w:cs=\"Calibri\" w:asciiTheme=\"minorHAnsi\" w:hAnsiTheme=\"minorHAnsi\"/>\n" +
                "                            <w:sz w:val=\"21\"/>\n" +
                "                            <w:szCs w:val=\"21\"/>\n" +
                "                        </w:rPr>\n" +
                "                        <w:t xml:space=\"preserve\">Co Applicants: ${referralRecordInstance.coApplicants.each{coApp -> coApp.coApplicant?.forename?.toString() + ' ' + coApp?.coApplicant?.surname?.toString() + ', '}?.toString()?.replace('[','')?.toString()?.replace(']','')}</w:t>\n" +
                "                    </w:r>\n" +
                "                </w:p>"

        def probandInformation = "<w:p xmlns:w=\"http://schemas.openxmlformats.org/wordprocessingml/2006/main\" >\n" +
                "                    <w:pPr>\n" +
                "                        <w:spacing w:after=\"120\"/>\n" +
                "                        <w:rPr>\n" +
                "                            <w:rFonts w:eastAsia=\"Calibri\" w:cs=\"Calibri\" w:asciiTheme=\"minorHAnsi\" w:hAnsiTheme=\"minorHAnsi\"/>\n" +
                "                            <w:b/>\n" +
                "                            <w:sz w:val=\"22\"/>\n" +
                "                            <w:szCs w:val=\"22\"/>\n" +
                "                            <w:u w:val=\"single\"/>\n" +
                "                        </w:rPr>\n" +
                "                    </w:pPr>\n" +
                "                    <w:proofErr w:type=\"gramStart\"/>\n" +
                "                    <w:r>\n" +
                "                        <w:rPr>\n" +
                "                            <w:rFonts w:eastAsia=\"Calibri\" w:cs=\"Calibri\" w:asciiTheme=\"minorHAnsi\" w:hAnsiTheme=\"minorHAnsi\"/>\n" +
                "                            <w:b/>\n" +
                "                            <w:sz w:val=\"22\"/>\n" +
                "                            <w:szCs w:val=\"22\"/>\n" +
                "                            <w:u w:val=\"single\"/>\n" +
                "                        </w:rPr>\n" +
                "                        <w:t>Proband Information</w:t>\n" +
                "                    </w:r>\n" +
                "                </w:p>";
        def probandDetails = "<w:tbl xmlns:w=\"http://schemas.openxmlformats.org/wordprocessingml/2006/main\" >\n" +
                "                    <w:tblPr>\n" +
                "                        <w:tblW w:w=\"0\" w:type=\"auto\"/>\n" +
                "                        <w:tblInd w:w=\"98\" w:type=\"dxa\"/>\n" +
                "                        <w:tblBorders>\n" +
                "                            <w:top w:val=\"single\" w:color=\"auto\" w:sz=\"4\" w:space=\"0\"/>\n" +
                "                            <w:left w:val=\"single\" w:color=\"auto\" w:sz=\"4\" w:space=\"0\"/>\n" +
                "                            <w:bottom w:val=\"single\" w:color=\"auto\" w:sz=\"4\" w:space=\"0\"/>\n" +
                "                            <w:right w:val=\"single\" w:color=\"auto\" w:sz=\"4\" w:space=\"0\"/>\n" +
                "                            <w:insideH w:val=\"single\" w:color=\"auto\" w:sz=\"4\" w:space=\"0\"/>\n" +
                "                            <w:insideV w:val=\"single\" w:color=\"auto\" w:sz=\"4\" w:space=\"0\"/>\n" +
                "                        </w:tblBorders>\n" +
                "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"D9D9D9\" w:themeFill=\"background1\" w:themeFillShade=\"D9\"/>\n" +
                "                        <w:tblCellMar>\n" +
                "                            <w:left w:w=\"10\" w:type=\"dxa\"/>\n" +
                "                            <w:right w:w=\"10\" w:type=\"dxa\"/>\n" +
                "                        </w:tblCellMar>\n" +
                "                        <w:tblLook w:firstRow=\"1\" w:lastRow=\"0\" w:firstColumn=\"1\" w:lastColumn=\"0\" w:noHBand=\"0\" w:noVBand=\"1\" w:val=\"04A0\"/>\n" +
                "                    </w:tblPr>\n" +
                "                    <w:tblGrid>\n" +
                "                        <w:gridCol w:w=\"9144\"/>\n" +
                "                    </w:tblGrid>\n" +
                "                    <w:tr w:rsidTr=\"00C87F4E\">\n" +
                "                        <w:trPr>\n" +
                "                            <w:trHeight w:val=\"1\"/>\n" +
                "                        </w:trPr>\n" +
                "                        <w:tc>\n" +
                "                            <w:tcPr>\n" +
                "                                <w:tcW w:w=\"9144\" w:type=\"dxa\"/>\n" +
                "                                <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"D9D9D9\" w:themeFill=\"background1\" w:themeFillShade=\"D9\"/>\n" +
                "                                <w:tcMar>\n" +
                "                                    <w:left w:w=\"108\" w:type=\"dxa\"/>\n" +
                "                                    <w:right w:w=\"108\" w:type=\"dxa\"/>\n" +
                "                                </w:tcMar>\n" +
                "                            </w:tcPr>\n" +
                "                            <w:p>\n" +
                "                                <w:pPr>\n" +
                "                                    <w:spacing w:after=\"120\"/>\n" +
                "                                    <w:rPr>\n" +
                "                                        <w:rFonts w:eastAsia=\"Calibri\" w:cs=\"Calibri\" w:asciiTheme=\"minorHAnsi\" w:hAnsiTheme=\"minorHAnsi\"/>\n" +
                "                                        <w:sz w:val=\"20\"/>\n" +
                "                                        <w:szCs w:val=\"20\"/>\n" +
                "                                    </w:rPr>\n" +
                "                                </w:pPr>\n" +
                "                                <w:r>\n" +
                "                                    <w:rPr>\n" +
                "                                        <w:rFonts w:eastAsia=\"Calibri\" w:cs=\"Calibri\" w:asciiTheme=\"minorHAnsi\" w:hAnsiTheme=\"minorHAnsi\"/>\n" +
                "                                        <w:sz w:val=\"20\"/>\n" +
                "                                        <w:szCs w:val=\"20\"/>\n" +
                "                                    </w:rPr>\n" +
                "                                    <w:t xml:space=\"preserve\">Unique reference: ${referralRecordInstance.uniqueRef}</w:t>\n" +
                "                                </w:r>\n" +
                "                            </w:p>\n" +
                "                            <w:p>\n" +
                "                                <w:pPr>\n" +
                "                                    <w:spacing w:after=\"120\"/>\n" +
                "                                    <w:rPr>\n" +
                "                                        <w:rFonts w:eastAsia=\"Calibri\" w:cs=\"Calibri\" w:asciiTheme=\"minorHAnsi\" w:hAnsiTheme=\"minorHAnsi\"/>\n" +
                "                                        <w:sz w:val=\"20\"/>\n" +
                "                                        <w:szCs w:val=\"20\"/>\n" +
                "                                    </w:rPr>\n" +
                "                                </w:pPr>\n" +
                "                                <w:r>\n" +
                "                                    <w:rPr>\n" +
                "                                        <w:rFonts w:eastAsia=\"Calibri\" w:cs=\"Calibri\" w:asciiTheme=\"minorHAnsi\" w:hAnsiTheme=\"minorHAnsi\"/>\n" +
                "                                        <w:sz w:val=\"20\"/>\n" +
                "                                        <w:szCs w:val=\"20\"/>\n" +
                "                                    </w:rPr>\n" +
                "                                    <w:t xml:space=\"preserve\">NHS Number: ${referralRecordInstance.patients?.find{p -> p.isProband}?.nhsNumber ?: ''}</w:t>\n" +
                "                                </w:r>\n" +
                "                            </w:p>\n" +
                "                            <w:p>\n" +
                "                                <w:pPr>\n" +
                "                                    <w:spacing w:after=\"120\"/>\n" +
                "                                    <w:rPr>\n" +
                "                                        <w:rFonts w:eastAsia=\"Calibri\" w:cs=\"Calibri\" w:asciiTheme=\"minorHAnsi\" w:hAnsiTheme=\"minorHAnsi\"/>\n" +
                "                                        <w:sz w:val=\"20\"/>\n" +
                "                                        <w:szCs w:val=\"20\"/>\n" +
                "                                    </w:rPr>\n" +
                "                                </w:pPr>\n" +
                "                                <w:r>\n" +
                "                                    <w:rPr>\n" +
                "                                        <w:rFonts w:eastAsia=\"Calibri\" w:cs=\"Calibri\" w:asciiTheme=\"minorHAnsi\" w:hAnsiTheme=\"minorHAnsi\"/>\n" +
                "                                        <w:sz w:val=\"20\"/>\n" +
                "                                        <w:szCs w:val=\"20\"/>\n" +
                "                                    </w:rPr>\n" +
                "                                    <w:t xml:space=\"preserve\">Age: ${referralRecordInstance.patients?.find{p -> p.isProband}?.age ?: ''} ${referralRecordInstance.patients?.find{p -> p.isProband}?.ageUnit ?: ''}</w:t>\n" +
                "                                </w:r>\n" +
                "                            </w:p>\n" +
                "                            <w:p>\n" +
                "                                <w:pPr>\n" +
                "                                    <w:spacing w:after=\"120\"/>\n" +
                "                                    <w:rPr>\n" +
                "                                        <w:rFonts w:eastAsia=\"Calibri\" w:cs=\"Calibri\" w:asciiTheme=\"minorHAnsi\" w:hAnsiTheme=\"minorHAnsi\"/>\n" +
                "                                        <w:sz w:val=\"20\"/>\n" +
                "                                        <w:szCs w:val=\"20\"/>\n" +
                "                                    </w:rPr>\n" +
                "                                </w:pPr>\n" +
                "                                <w:r>\n" +
                "                                    <w:rPr>\n" +
                "                                        <w:rFonts w:eastAsia=\"Calibri\" w:cs=\"Calibri\" w:asciiTheme=\"minorHAnsi\" w:hAnsiTheme=\"minorHAnsi\"/>\n" +
                "                                        <w:sz w:val=\"20\"/>\n" +
                "                                        <w:szCs w:val=\"20\"/>\n" +
                "                                    </w:rPr>\n" +
                "                                    <w:t xml:space=\"preserve\">Gender: ${referralRecordInstance.patients?.find{p -> p.isProband}?.gender ?: ''}</w:t>\n" +
                "                                </w:r>\n" +
                "                            </w:p>\n" +
                "                            <w:p>\n" +
                "                                <w:pPr>\n" +
                "                                    <w:rPr>\n" +
                "                                        <w:rFonts w:eastAsia=\"Calibri\" w:cs=\"Calibri\" w:asciiTheme=\"minorHAnsi\" w:hAnsiTheme=\"minorHAnsi\"/>\n" +
                "                                        <w:sz w:val=\"20\"/>\n" +
                "                                        <w:szCs w:val=\"20\"/>\n" +
                "                                    </w:rPr>\n" +
                "                                </w:pPr>\n" +
                "                                <w:r>\n" +
                "                                    <w:rPr>\n" +
                "                                        <w:rFonts w:eastAsia=\"Calibri\" w:cs=\"Calibri\" w:asciiTheme=\"minorHAnsi\" w:hAnsiTheme=\"minorHAnsi\"/>\n" +
                "                                        <w:sz w:val=\"20\"/>\n" +
                "                                        <w:szCs w:val=\"20\"/>\n" +
                "                                    </w:rPr>\n" +
                "                                    <w:t>Ethnicity: ${referralRecordInstance.patients?.find{p -> p.isProband}?.ethnicity ?: ''}</w:t>\n" +
                "                                </w:r>\n" +
                "                            </w:p>\n" +
                "                        </w:tc>\n" +
                "                    </w:tr>\n" +
                "                </w:tbl>"

        def clinicalInformation = "<w:p xmlns:w=\"http://schemas.openxmlformats.org/wordprocessingml/2006/main\" >\n" +
                "                    <w:pPr>\n" +
                "                        <w:spacing w:after=\"120\"/>\n" +
                "                        <w:rPr>\n" +
                "                            <w:rFonts w:eastAsia=\"Calibri\" w:cs=\"Calibri\" w:asciiTheme=\"minorHAnsi\" w:hAnsiTheme=\"minorHAnsi\"/>\n" +
                "                            <w:b/>\n" +
                "                            <w:sz w:val=\"22\"/>\n" +
                "                            <w:szCs w:val=\"22\"/>\n" +
                "                            <w:u w:val=\"single\"/>\n" +
                "                        </w:rPr>\n" +
                "                    </w:pPr>\n" +
                "                    <w:proofErr w:type=\"gramStart\"/>\n" +
                "                    <w:r>\n" +
                "                        <w:rPr>\n" +
                "                            <w:rFonts w:eastAsia=\"Calibri\" w:cs=\"Calibri\" w:asciiTheme=\"minorHAnsi\" w:hAnsiTheme=\"minorHAnsi\"/>\n" +
                "                            <w:b/>\n" +
                "                            <w:sz w:val=\"22\"/>\n" +
                "                            <w:szCs w:val=\"22\"/>\n" +
                "                            <w:u w:val=\"single\"/>\n" +
                "                        </w:rPr>\n" +
                "                        <w:t>Clinical Information</w:t>\n" +
                "                    </w:r>\n" +
                "                </w:p>";

        def disorderName = "<w:tbl xmlns:w=\"http://schemas.openxmlformats.org/wordprocessingml/2006/main\" >\n" +
                "                    <w:tblPr>\n" +
                "                        <w:tblW w:w=\"0\" w:type=\"auto\"/>\n" +
                "                        <w:tblInd w:w=\"98\" w:type=\"dxa\"/>\n" +
                "                        <w:tblBorders>\n" +
                "                            <w:top w:val=\"single\" w:color=\"auto\" w:sz=\"4\" w:space=\"0\"/>\n" +
                "                            <w:left w:val=\"single\" w:color=\"auto\" w:sz=\"4\" w:space=\"0\"/>\n" +
                "                            <w:bottom w:val=\"single\" w:color=\"auto\" w:sz=\"4\" w:space=\"0\"/>\n" +
                "                            <w:right w:val=\"single\" w:color=\"auto\" w:sz=\"4\" w:space=\"0\"/>\n" +
                "                            <w:insideH w:val=\"single\" w:color=\"auto\" w:sz=\"4\" w:space=\"0\"/>\n" +
                "                            <w:insideV w:val=\"single\" w:color=\"auto\" w:sz=\"4\" w:space=\"0\"/>\n" +
                "                        </w:tblBorders>\n" +
                "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"D9D9D9\" w:themeFill=\"background1\" w:themeFillShade=\"D9\"/>\n" +
                "                        <w:tblCellMar>\n" +
                "                            <w:left w:w=\"10\" w:type=\"dxa\"/>\n" +
                "                            <w:right w:w=\"10\" w:type=\"dxa\"/>\n" +
                "                        </w:tblCellMar>\n" +
                "                        <w:tblLook w:firstRow=\"1\" w:lastRow=\"0\" w:firstColumn=\"1\" w:lastColumn=\"0\" w:noHBand=\"0\" w:noVBand=\"1\" w:val=\"04A0\"/>\n" +
                "                    </w:tblPr>\n" +
                "                    <w:tblGrid>\n" +
                "                        <w:gridCol w:w=\"9144\"/>\n" +
                "                    </w:tblGrid>\n" +
                "                    <w:tr w:rsidTr=\"00C87F4E\">\n" +
                "                        <w:trPr>\n" +
                "                            <w:trHeight w:val=\"1\"/>\n" +
                "                        </w:trPr>\n" +
                "                        <w:tc>\n" +
                "                            <w:tcPr>\n" +
                "                                <w:tcW w:w=\"9144\" w:type=\"dxa\"/>\n" +
                "                                <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"D9D9D9\" w:themeFill=\"background1\" w:themeFillShade=\"D9\"/>\n" +
                "                                <w:tcMar>\n" +
                "                                    <w:left w:w=\"108\" w:type=\"dxa\"/>\n" +
                "                                    <w:right w:w=\"108\" w:type=\"dxa\"/>\n" +
                "                                </w:tcMar>\n" +
                "                            </w:tcPr>\n" +
                "                            <w:p>\n" +
                "                                <w:pPr>\n" +
                "                                    <w:spacing w:after=\"120\"/>\n" +
                "                                    <w:rPr>\n" +
                "                                        <w:rFonts w:eastAsia=\"Calibri\" w:cs=\"Calibri\" w:asciiTheme=\"minorHAnsi\" w:hAnsiTheme=\"minorHAnsi\"/>\n" +
                "                                        <w:sz w:val=\"20\"/>\n" +
                "                                        <w:szCs w:val=\"20\"/>\n" +
                "                                    </w:rPr>\n" +
                "                                </w:pPr>\n" +
                "                                <w:r>\n" +
                "                                    <w:rPr>\n" +
                "                                        <w:rFonts w:eastAsia=\"Calibri\" w:cs=\"Calibri\" w:asciiTheme=\"minorHAnsi\" w:hAnsiTheme=\"minorHAnsi\"/>\n" +
                "                                        <w:sz w:val=\"20\"/>\n" +
                "                                        <w:szCs w:val=\"20\"/>\n" +
                "                                    </w:rPr>\n" +
                "                                    <w:t xml:space=\"preserve\">Name or brief description of disorder: ${referralRecordInstance?.disorderName ?: ''}</w:t>\n" +
                "                                </w:r>\n" +
                "                            </w:p>\n" +
                "                            <w:p>\n" +
                "                                <w:pPr>\n" +
                "                                    <w:spacing w:after=\"120\"/>\n" +
                "                                    <w:rPr>\n" +
                "                                        <w:rFonts w:eastAsia=\"Calibri\" w:cs=\"Calibri\" w:asciiTheme=\"minorHAnsi\" w:hAnsiTheme=\"minorHAnsi\"/>\n" +
                "                                        <w:sz w:val=\"20\"/>\n" +
                "                                        <w:szCs w:val=\"20\"/>\n" +
                "                                    </w:rPr>\n" +
                "                                </w:pPr>\n" +
                "                                <w:r>\n" +
                "                                    <w:rPr>\n" +
                "                                        <w:rFonts w:eastAsia=\"Calibri\" w:cs=\"Calibri\" w:asciiTheme=\"minorHAnsi\" w:hAnsiTheme=\"minorHAnsi\"/>\n" +
                "                                        <w:sz w:val=\"20\"/>\n" +
                "                                        <w:szCs w:val=\"20\"/>\n" +
                "                                    </w:rPr>\n" +
                "                                    <w:t xml:space=\"preserve\">Age of onset: ${referralRecordInstance?.ageOfSymptoms ?: ''} ${referralRecordInstance?.symptomEgeUnit ?: ''}</w:t>\n" +
                "                                </w:r>\n" +
                "                            </w:p>\n" +
                "                        </w:tc>\n" +
                "                    </w:tr>\n" +
                "                </w:tbl>"

        def clinicalDetails = "<w:p xmlns:w=\"http://schemas.openxmlformats.org/wordprocessingml/2006/main\" >\n" +
                "                                <w:pPr>\n" +
                "                                    <w:spacing w:after=\"120\"/>\n" +
                "                                    <w:rPr>\n" +
                "                                        <w:rFonts w:eastAsia=\"Calibri\" w:cs=\"Calibri\" w:asciiTheme=\"minorHAnsi\" w:hAnsiTheme=\"minorHAnsi\"/>\n" +
                "                                        <w:sz w:val=\"20\"/>\n" +
                "                                        <w:szCs w:val=\"20\"/>\n" +
                "                                    </w:rPr>\n" +
                "                                </w:pPr>\n" +
                "                                <w:r>\n" +
                "                                    <w:rPr>\n" +
                "                                        <w:rFonts w:eastAsia=\"Calibri\" w:cs=\"Calibri\" w:asciiTheme=\"minorHAnsi\" w:hAnsiTheme=\"minorHAnsi\"/>\n" +
                "                                        <w:sz w:val=\"20\"/>\n" +
                "                                        <w:szCs w:val=\"20\"/>\n" +
                "                                    </w:rPr>\n" +
                "                                    <w:t xml:space=\"preserve\">Clinical Details:</w:t>\n" +
                "                                </w:r>\n" +
                "                            </w:p>"

        mainPart.addObject(org.docx4j.XmlUtils.unmarshalString(title));
        mainPart.addObject(org.docx4j.XmlUtils.unmarshalString(monthYear));
        mainPart.addObject(org.docx4j.XmlUtils.unmarshalString(applicantInformation));
        mainPart.addObject(org.docx4j.XmlUtils.unmarshalString(clinician));
        mainPart.addObject(org.docx4j.XmlUtils.unmarshalString(clinicianTelephoneNumber));
        mainPart.addObject(org.docx4j.XmlUtils.unmarshalString(responsibleConsultant));
        mainPart.addObject(org.docx4j.XmlUtils.unmarshalString(coApplicants));
        mainPart.addObject(org.docx4j.XmlUtils.unmarshalString(probandInformation));
        mainPart.addObject(org.docx4j.XmlUtils.unmarshalString(probandDetails));
        mainPart.addObject(org.docx4j.XmlUtils.unmarshalString(clinicalInformation));
        mainPart.addObject(org.docx4j.XmlUtils.unmarshalString(disorderName));
        mainPart.addObject(org.docx4j.XmlUtils.unmarshalString(clinicalDetails));

        referralRecordInstance.clinicalDetails.sort {it.id}.each {
            def clinicalDetailsItems = "<w:tbl xmlns:w=\"http://schemas.openxmlformats.org/wordprocessingml/2006/main\" >\n" +
                    "                    <w:tblPr>\n" +
                    "                        <w:tblW w:w=\"0\" w:type=\"auto\"/>\n" +
                    "                        <w:tblInd w:w=\"98\" w:type=\"dxa\"/>\n" +
                    "                        <w:tblBorders>\n" +
                    "                            <w:top w:val=\"single\" w:color=\"auto\" w:sz=\"4\" w:space=\"0\"/>\n" +
                    "                            <w:left w:val=\"single\" w:color=\"auto\" w:sz=\"4\" w:space=\"0\"/>\n" +
                    "                            <w:bottom w:val=\"single\" w:color=\"auto\" w:sz=\"4\" w:space=\"0\"/>\n" +
                    "                            <w:right w:val=\"single\" w:color=\"auto\" w:sz=\"4\" w:space=\"0\"/>\n" +
                    "                            <w:insideH w:val=\"single\" w:color=\"auto\" w:sz=\"4\" w:space=\"0\"/>\n" +
                    "                            <w:insideV w:val=\"single\" w:color=\"auto\" w:sz=\"4\" w:space=\"0\"/>\n" +
                    "                        </w:tblBorders>\n" +
                    "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"D9D9D9\" w:themeFill=\"background1\" w:themeFillShade=\"D9\"/>\n" +
                    "                        <w:tblCellMar>\n" +
                    "                            <w:left w:w=\"10\" w:type=\"dxa\"/>\n" +
                    "                            <w:right w:w=\"10\" w:type=\"dxa\"/>\n" +
                    "                        </w:tblCellMar>\n" +
                    "                        <w:tblLook w:firstRow=\"1\" w:lastRow=\"0\" w:firstColumn=\"1\" w:lastColumn=\"0\" w:noHBand=\"0\" w:noVBand=\"1\" w:val=\"04A0\"/>\n" +
                    "                    </w:tblPr>\n" +
                    "                    <w:tblGrid>\n" +
                    "                        <w:gridCol w:w=\"9144\"/>\n" +
                    "                    </w:tblGrid>\n" +
                    "                    <w:tr w:rsidTr=\"00C87F4E\">\n" +
                    "                        <w:trPr>\n" +
                    "                            <w:trHeight w:val=\"1\"/>\n" +
                    "                        </w:trPr>\n" +
                    "                        <w:tc>\n" +
                    "                            <w:tcPr>\n" +
                    "                                <w:tcW w:w=\"9144\" w:type=\"dxa\"/>\n" +
                    "                                <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"D9D9D9\" w:themeFill=\"background1\" w:themeFillShade=\"D9\"/>\n" +
                    "                                <w:tcMar>\n" +
                    "                                    <w:left w:w=\"108\" w:type=\"dxa\"/>\n" +
                    "                                    <w:right w:w=\"108\" w:type=\"dxa\"/>\n" +
                    "                                </w:tcMar>\n" +
                    "                            </w:tcPr>\n" +
                    "                            <w:p>\n" +
                    "                                <w:pPr>\n" +
                    "                                    <w:spacing w:after=\"120\"/>\n" +
                    "                                    <w:rPr>\n" +
                    "                                        <w:rFonts w:eastAsia=\"Calibri\" w:cs=\"Calibri\" w:asciiTheme=\"minorHAnsi\" w:hAnsiTheme=\"minorHAnsi\"/>\n" +
                    "                                        <w:sz w:val=\"20\"/>\n" +
                    "                                        <w:szCs w:val=\"20\"/>\n" +
                    "                                    </w:rPr>\n" +
                    "                                </w:pPr>\n" +
                    "                                <w:r>\n" +
                    "                                    <w:rPr>\n" +
                    "                                        <w:rFonts w:eastAsia=\"Calibri\" w:cs=\"Calibri\" w:asciiTheme=\"minorHAnsi\" w:hAnsiTheme=\"minorHAnsi\"/>\n" +
                    "                                        <w:sz w:val=\"20\"/>\n" +
                    "                                        <w:szCs w:val=\"20\"/>\n" +
                    "                                    </w:rPr>\n" +
                    "                                    <w:t xml:space=\"preserve\"> ${it}</w:t>\n" +
                    "                                </w:r>\n" +
                    "                            </w:p>\n" +
                    "                        </w:tc>\n" +
                    "                    </w:tr>\n" +
                    "                </w:tbl>"
            mainPart.addObject(org.docx4j.XmlUtils.unmarshalString(clinicalDetailsItems));
        }

        def geneticTesting = "<w:p xmlns:w=\"http://schemas.openxmlformats.org/wordprocessingml/2006/main\" >\n" +
                "                                <w:pPr>\n" +
                "                                    <w:spacing w:after=\"120\"/>\n" +
                "                                    <w:rPr>\n" +
                "                                        <w:rFonts w:eastAsia=\"Calibri\" w:cs=\"Calibri\" w:asciiTheme=\"minorHAnsi\" w:hAnsiTheme=\"minorHAnsi\"/>\n" +
                "                                        <w:sz w:val=\"20\"/>\n" +
                "                                        <w:szCs w:val=\"20\"/>\n" +
                "                                    </w:rPr>\n" +
                "                                </w:pPr>\n" +
                "                                <w:r>\n" +
                "                                    <w:rPr>\n" +
                "                                        <w:rFonts w:eastAsia=\"Calibri\" w:cs=\"Calibri\" w:asciiTheme=\"minorHAnsi\" w:hAnsiTheme=\"minorHAnsi\"/>\n" +
                "                                        <w:sz w:val=\"20\"/>\n" +
                "                                        <w:szCs w:val=\"20\"/>\n" +
                "                                    </w:rPr>\n" +
                "                                    <w:t xml:space=\"preserve\">Genetic Testing (chromosome analysis, single gene, gene panel, etc.)</w:t>\n" +
                "                                </w:r>\n" +
                "                            </w:p>"

        def geneticTestingDetails = "<w:tbl xmlns:w=\"http://schemas.openxmlformats.org/wordprocessingml/2006/main\" >\n" +
                "                    <w:tblPr>\n" +
                "                        <w:tblW w:w=\"0\" w:type=\"auto\"/>\n" +
                "                        <w:tblInd w:w=\"98\" w:type=\"dxa\"/>\n" +
                "                        <w:tblBorders>\n" +
                "                            <w:top w:val=\"single\" w:color=\"auto\" w:sz=\"4\" w:space=\"0\"/>\n" +
                "                            <w:left w:val=\"single\" w:color=\"auto\" w:sz=\"4\" w:space=\"0\"/>\n" +
                "                            <w:bottom w:val=\"single\" w:color=\"auto\" w:sz=\"4\" w:space=\"0\"/>\n" +
                "                            <w:right w:val=\"single\" w:color=\"auto\" w:sz=\"4\" w:space=\"0\"/>\n" +
                "                            <w:insideH w:val=\"single\" w:color=\"auto\" w:sz=\"4\" w:space=\"0\"/>\n" +
                "                            <w:insideV w:val=\"single\" w:color=\"auto\" w:sz=\"4\" w:space=\"0\"/>\n" +
                "                        </w:tblBorders>\n" +
                "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"D9D9D9\" w:themeFill=\"background1\" w:themeFillShade=\"D9\"/>\n" +
                "                        <w:tblCellMar>\n" +
                "                            <w:left w:w=\"10\" w:type=\"dxa\"/>\n" +
                "                            <w:right w:w=\"10\" w:type=\"dxa\"/>\n" +
                "                        </w:tblCellMar>\n" +
                "                        <w:tblLook w:firstRow=\"1\" w:lastRow=\"0\" w:firstColumn=\"1\" w:lastColumn=\"0\" w:noHBand=\"0\" w:noVBand=\"1\" w:val=\"04A0\"/>\n" +
                "                    </w:tblPr>\n" +
                "                    <w:tblGrid>\n" +
                "                        <w:gridCol w:w=\"9144\"/>\n" +
                "                    </w:tblGrid>\n" +
                "                    <w:tr w:rsidTr=\"00C87F4E\">\n" +
                "                        <w:trPr>\n" +
                "                            <w:trHeight w:val=\"1\"/>\n" +
                "                        </w:trPr>\n" +
                "                        <w:tc>\n" +
                "                            <w:tcPr>\n" +
                "                                <w:tcW w:w=\"9144\" w:type=\"dxa\"/>\n" +
                "                                <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"D9D9D9\" w:themeFill=\"background1\" w:themeFillShade=\"D9\"/>\n" +
                "                                <w:tcMar>\n" +
                "                                    <w:left w:w=\"108\" w:type=\"dxa\"/>\n" +
                "                                    <w:right w:w=\"108\" w:type=\"dxa\"/>\n" +
                "                                </w:tcMar>\n" +
                "                            </w:tcPr>\n" +
                "                            <w:p>\n" +
                "                                <w:pPr>\n" +
                "                                    <w:spacing w:after=\"120\"/>\n" +
                "                                    <w:rPr>\n" +
                "                                        <w:rFonts w:eastAsia=\"Calibri\" w:cs=\"Calibri\" w:asciiTheme=\"minorHAnsi\" w:hAnsiTheme=\"minorHAnsi\"/>\n" +
                "                                        <w:sz w:val=\"20\"/>\n" +
                "                                        <w:szCs w:val=\"20\"/>\n" +
                "                                    </w:rPr>\n" +
                "                                </w:pPr>\n" +
                "                                <w:r>\n" +
                "                                    <w:rPr>\n" +
                "                                        <w:rFonts w:eastAsia=\"Calibri\" w:cs=\"Calibri\" w:asciiTheme=\"minorHAnsi\" w:hAnsiTheme=\"minorHAnsi\"/>\n" +
                "                                        <w:sz w:val=\"20\"/>\n" +
                "                                        <w:szCs w:val=\"20\"/>\n" +
                "                                    </w:rPr>\n" +
                "                                    <w:t xml:space=\"preserve\"> ${referralRecordInstance?.geneticTestingOnProband ?: ''}</w:t>\n" +
                "                                </w:r>\n" +
                "                            </w:p>\n" +
                "                        </w:tc>\n" +
                "                    </w:tr>\n" +
                "                </w:tbl>"

        mainPart.addObject(org.docx4j.XmlUtils.unmarshalString(geneticTesting));
        mainPart.addObject(org.docx4j.XmlUtils.unmarshalString(geneticTestingDetails));

        def otherTesting = "<w:p xmlns:w=\"http://schemas.openxmlformats.org/wordprocessingml/2006/main\" >\n" +
                "                                <w:pPr>\n" +
                "                                    <w:spacing w:after=\"120\"/>\n" +
                "                                    <w:rPr>\n" +
                "                                        <w:rFonts w:eastAsia=\"Calibri\" w:cs=\"Calibri\" w:asciiTheme=\"minorHAnsi\" w:hAnsiTheme=\"minorHAnsi\"/>\n" +
                "                                        <w:sz w:val=\"20\"/>\n" +
                "                                        <w:szCs w:val=\"20\"/>\n" +
                "                                    </w:rPr>\n" +
                "                                </w:pPr>\n" +
                "                                <w:r>\n" +
                "                                    <w:rPr>\n" +
                "                                        <w:rFonts w:eastAsia=\"Calibri\" w:cs=\"Calibri\" w:asciiTheme=\"minorHAnsi\" w:hAnsiTheme=\"minorHAnsi\"/>\n" +
                "                                        <w:sz w:val=\"20\"/>\n" +
                "                                        <w:szCs w:val=\"20\"/>\n" +
                "                                    </w:rPr>\n" +
                "                                    <w:t xml:space=\"preserve\">Other testing on proband (metabolic, nerve conduction, muscle/skin biopsy, etc.)</w:t>\n" +
                "                                </w:r>\n" +
                "                            </w:p>"

        def otherTestingDetails = "<w:tbl xmlns:w=\"http://schemas.openxmlformats.org/wordprocessingml/2006/main\" >\n" +
                "                    <w:tblPr>\n" +
                "                        <w:tblW w:w=\"0\" w:type=\"auto\"/>\n" +
                "                        <w:tblInd w:w=\"98\" w:type=\"dxa\"/>\n" +
                "                        <w:tblBorders>\n" +
                "                            <w:top w:val=\"single\" w:color=\"auto\" w:sz=\"4\" w:space=\"0\"/>\n" +
                "                            <w:left w:val=\"single\" w:color=\"auto\" w:sz=\"4\" w:space=\"0\"/>\n" +
                "                            <w:bottom w:val=\"single\" w:color=\"auto\" w:sz=\"4\" w:space=\"0\"/>\n" +
                "                            <w:right w:val=\"single\" w:color=\"auto\" w:sz=\"4\" w:space=\"0\"/>\n" +
                "                            <w:insideH w:val=\"single\" w:color=\"auto\" w:sz=\"4\" w:space=\"0\"/>\n" +
                "                            <w:insideV w:val=\"single\" w:color=\"auto\" w:sz=\"4\" w:space=\"0\"/>\n" +
                "                        </w:tblBorders>\n" +
                "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"D9D9D9\" w:themeFill=\"background1\" w:themeFillShade=\"D9\"/>\n" +
                "                        <w:tblCellMar>\n" +
                "                            <w:left w:w=\"10\" w:type=\"dxa\"/>\n" +
                "                            <w:right w:w=\"10\" w:type=\"dxa\"/>\n" +
                "                        </w:tblCellMar>\n" +
                "                        <w:tblLook w:firstRow=\"1\" w:lastRow=\"0\" w:firstColumn=\"1\" w:lastColumn=\"0\" w:noHBand=\"0\" w:noVBand=\"1\" w:val=\"04A0\"/>\n" +
                "                    </w:tblPr>\n" +
                "                    <w:tblGrid>\n" +
                "                        <w:gridCol w:w=\"9144\"/>\n" +
                "                    </w:tblGrid>\n" +
                "                    <w:tr w:rsidTr=\"00C87F4E\">\n" +
                "                        <w:trPr>\n" +
                "                            <w:trHeight w:val=\"1\"/>\n" +
                "                        </w:trPr>\n" +
                "                        <w:tc>\n" +
                "                            <w:tcPr>\n" +
                "                                <w:tcW w:w=\"9144\" w:type=\"dxa\"/>\n" +
                "                                <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"D9D9D9\" w:themeFill=\"background1\" w:themeFillShade=\"D9\"/>\n" +
                "                                <w:tcMar>\n" +
                "                                    <w:left w:w=\"108\" w:type=\"dxa\"/>\n" +
                "                                    <w:right w:w=\"108\" w:type=\"dxa\"/>\n" +
                "                                </w:tcMar>\n" +
                "                            </w:tcPr>\n" +
                "                            <w:p>\n" +
                "                                <w:pPr>\n" +
                "                                    <w:spacing w:after=\"120\"/>\n" +
                "                                    <w:rPr>\n" +
                "                                        <w:rFonts w:eastAsia=\"Calibri\" w:cs=\"Calibri\" w:asciiTheme=\"minorHAnsi\" w:hAnsiTheme=\"minorHAnsi\"/>\n" +
                "                                        <w:sz w:val=\"20\"/>\n" +
                "                                        <w:szCs w:val=\"20\"/>\n" +
                "                                    </w:rPr>\n" +
                "                                </w:pPr>\n" +
                "                                <w:r>\n" +
                "                                    <w:rPr>\n" +
                "                                        <w:rFonts w:eastAsia=\"Calibri\" w:cs=\"Calibri\" w:asciiTheme=\"minorHAnsi\" w:hAnsiTheme=\"minorHAnsi\"/>\n" +
                "                                        <w:sz w:val=\"20\"/>\n" +
                "                                        <w:szCs w:val=\"20\"/>\n" +
                "                                    </w:rPr>\n" +
                "                                    <w:t xml:space=\"preserve\"> ${referralRecordInstance?.otherTestingOnProband ?: ''}</w:t>\n" +
                "                                </w:r>\n" +
                "                            </w:p>\n" +
                "                        </w:tc>\n" +
                "                    </w:tr>\n" +
                "                </w:tbl>"

        mainPart.addObject(org.docx4j.XmlUtils.unmarshalString(otherTesting));
        mainPart.addObject(org.docx4j.XmlUtils.unmarshalString(otherTestingDetails));

        def arrayCGH = "<w:p xmlns:w=\"http://schemas.openxmlformats.org/wordprocessingml/2006/main\" >\n" +
                "                                <w:pPr>\n" +
                "                                    <w:spacing w:after=\"120\"/>\n" +
                "                                    <w:rPr>\n" +
                "                                        <w:rFonts w:eastAsia=\"Calibri\" w:cs=\"Calibri\" w:asciiTheme=\"minorHAnsi\" w:hAnsiTheme=\"minorHAnsi\"/>\n" +
                "                                        <w:sz w:val=\"20\"/>\n" +
                "                                        <w:szCs w:val=\"20\"/>\n" +
                "                                    </w:rPr>\n" +
                "                                </w:pPr>\n" +
                "                                <w:r>\n" +
                "                                    <w:rPr>\n" +
                "                                        <w:rFonts w:eastAsia=\"Calibri\" w:cs=\"Calibri\" w:asciiTheme=\"minorHAnsi\" w:hAnsiTheme=\"minorHAnsi\"/>\n" +
                "                                        <w:sz w:val=\"20\"/>\n" +
                "                                        <w:szCs w:val=\"20\"/>\n" +
                "                                    </w:rPr>\n" +
                "                                    <w:t xml:space=\"preserve\">Has arrayCGH been performed?</w:t>\n" +
                "                                </w:r>\n" +
                "                            </w:p>"
        mainPart.addObject(org.docx4j.XmlUtils.unmarshalString(arrayCGH));
        if (referralRecordInstance?.arrayCGH){
            def arrayCGHDetails = "<w:tbl xmlns:w=\"http://schemas.openxmlformats.org/wordprocessingml/2006/main\" >\n" +
                    "                    <w:tblPr>\n" +
                    "                        <w:tblW w:w=\"0\" w:type=\"auto\"/>\n" +
                    "                        <w:tblInd w:w=\"98\" w:type=\"dxa\"/>\n" +
                    "                        <w:tblBorders>\n" +
                    "                            <w:top w:val=\"single\" w:color=\"auto\" w:sz=\"4\" w:space=\"0\"/>\n" +
                    "                            <w:left w:val=\"single\" w:color=\"auto\" w:sz=\"4\" w:space=\"0\"/>\n" +
                    "                            <w:bottom w:val=\"single\" w:color=\"auto\" w:sz=\"4\" w:space=\"0\"/>\n" +
                    "                            <w:right w:val=\"single\" w:color=\"auto\" w:sz=\"4\" w:space=\"0\"/>\n" +
                    "                            <w:insideH w:val=\"single\" w:color=\"auto\" w:sz=\"4\" w:space=\"0\"/>\n" +
                    "                            <w:insideV w:val=\"single\" w:color=\"auto\" w:sz=\"4\" w:space=\"0\"/>\n" +
                    "                        </w:tblBorders>\n" +
                    "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"D9D9D9\" w:themeFill=\"background1\" w:themeFillShade=\"D9\"/>\n" +
                    "                        <w:tblCellMar>\n" +
                    "                            <w:left w:w=\"10\" w:type=\"dxa\"/>\n" +
                    "                            <w:right w:w=\"10\" w:type=\"dxa\"/>\n" +
                    "                        </w:tblCellMar>\n" +
                    "                        <w:tblLook w:firstRow=\"1\" w:lastRow=\"0\" w:firstColumn=\"1\" w:lastColumn=\"0\" w:noHBand=\"0\" w:noVBand=\"1\" w:val=\"04A0\"/>\n" +
                    "                    </w:tblPr>\n" +
                    "                    <w:tblGrid>\n" +
                    "                        <w:gridCol w:w=\"9144\"/>\n" +
                    "                    </w:tblGrid>\n" +
                    "                    <w:tr w:rsidTr=\"00C87F4E\">\n" +
                    "                        <w:trPr>\n" +
                    "                            <w:trHeight w:val=\"1\"/>\n" +
                    "                        </w:trPr>\n" +
                    "                        <w:tc>\n" +
                    "                            <w:tcPr>\n" +
                    "                                <w:tcW w:w=\"9144\" w:type=\"dxa\"/>\n" +
                    "                                <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"D9D9D9\" w:themeFill=\"background1\" w:themeFillShade=\"D9\"/>\n" +
                    "                                <w:tcMar>\n" +
                    "                                    <w:left w:w=\"108\" w:type=\"dxa\"/>\n" +
                    "                                    <w:right w:w=\"108\" w:type=\"dxa\"/>\n" +
                    "                                </w:tcMar>\n" +
                    "                            </w:tcPr>\n" +
                    "                            <w:p>\n" +
                    "                                <w:pPr>\n" +
                    "                                    <w:spacing w:after=\"120\"/>\n" +
                    "                                    <w:rPr>\n" +
                    "                                        <w:rFonts w:eastAsia=\"Calibri\" w:cs=\"Calibri\" w:asciiTheme=\"minorHAnsi\" w:hAnsiTheme=\"minorHAnsi\"/>\n" +
                    "                                        <w:sz w:val=\"20\"/>\n" +
                    "                                        <w:szCs w:val=\"20\"/>\n" +
                    "                                    </w:rPr>\n" +
                    "                                </w:pPr>\n" +
                    "                                <w:r>\n" +
                    "                                    <w:rPr>\n" +
                    "                                        <w:rFonts w:eastAsia=\"Calibri\" w:cs=\"Calibri\" w:asciiTheme=\"minorHAnsi\" w:hAnsiTheme=\"minorHAnsi\"/>\n" +
                    "                                        <w:sz w:val=\"20\"/>\n" +
                    "                                        <w:szCs w:val=\"20\"/>\n" +
                    "                                    </w:rPr>\n" +
                    "                                    <w:t xml:space=\"preserve\"> Yes, ${referralRecordInstance?.arrayCGHDetails ?: ''}</w:t>\n" +
                    "                                </w:r>\n" +
                    "                            </w:p>\n" +
                    "                        </w:tc>\n" +
                    "                    </w:tr>\n" +
                    "                </w:tbl>"
            mainPart.addObject(org.docx4j.XmlUtils.unmarshalString(arrayCGHDetails));
        }else {
            def arrayCGHDetails = "<w:tbl xmlns:w=\"http://schemas.openxmlformats.org/wordprocessingml/2006/main\" >\n" +
                    "                    <w:tblPr>\n" +
                    "                        <w:tblW w:w=\"0\" w:type=\"auto\"/>\n" +
                    "                        <w:tblInd w:w=\"98\" w:type=\"dxa\"/>\n" +
                    "                        <w:tblBorders>\n" +
                    "                            <w:top w:val=\"single\" w:color=\"auto\" w:sz=\"4\" w:space=\"0\"/>\n" +
                    "                            <w:left w:val=\"single\" w:color=\"auto\" w:sz=\"4\" w:space=\"0\"/>\n" +
                    "                            <w:bottom w:val=\"single\" w:color=\"auto\" w:sz=\"4\" w:space=\"0\"/>\n" +
                    "                            <w:right w:val=\"single\" w:color=\"auto\" w:sz=\"4\" w:space=\"0\"/>\n" +
                    "                            <w:insideH w:val=\"single\" w:color=\"auto\" w:sz=\"4\" w:space=\"0\"/>\n" +
                    "                            <w:insideV w:val=\"single\" w:color=\"auto\" w:sz=\"4\" w:space=\"0\"/>\n" +
                    "                        </w:tblBorders>\n" +
                    "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"D9D9D9\" w:themeFill=\"background1\" w:themeFillShade=\"D9\"/>\n" +
                    "                        <w:tblCellMar>\n" +
                    "                            <w:left w:w=\"10\" w:type=\"dxa\"/>\n" +
                    "                            <w:right w:w=\"10\" w:type=\"dxa\"/>\n" +
                    "                        </w:tblCellMar>\n" +
                    "                        <w:tblLook w:firstRow=\"1\" w:lastRow=\"0\" w:firstColumn=\"1\" w:lastColumn=\"0\" w:noHBand=\"0\" w:noVBand=\"1\" w:val=\"04A0\"/>\n" +
                    "                    </w:tblPr>\n" +
                    "                    <w:tblGrid>\n" +
                    "                        <w:gridCol w:w=\"9144\"/>\n" +
                    "                    </w:tblGrid>\n" +
                    "                    <w:tr w:rsidTr=\"00C87F4E\">\n" +
                    "                        <w:trPr>\n" +
                    "                            <w:trHeight w:val=\"1\"/>\n" +
                    "                        </w:trPr>\n" +
                    "                        <w:tc>\n" +
                    "                            <w:tcPr>\n" +
                    "                                <w:tcW w:w=\"9144\" w:type=\"dxa\"/>\n" +
                    "                                <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"D9D9D9\" w:themeFill=\"background1\" w:themeFillShade=\"D9\"/>\n" +
                    "                                <w:tcMar>\n" +
                    "                                    <w:left w:w=\"108\" w:type=\"dxa\"/>\n" +
                    "                                    <w:right w:w=\"108\" w:type=\"dxa\"/>\n" +
                    "                                </w:tcMar>\n" +
                    "                            </w:tcPr>\n" +
                    "                            <w:p>\n" +
                    "                                <w:pPr>\n" +
                    "                                    <w:spacing w:after=\"120\"/>\n" +
                    "                                    <w:rPr>\n" +
                    "                                        <w:rFonts w:eastAsia=\"Calibri\" w:cs=\"Calibri\" w:asciiTheme=\"minorHAnsi\" w:hAnsiTheme=\"minorHAnsi\"/>\n" +
                    "                                        <w:sz w:val=\"20\"/>\n" +
                    "                                        <w:szCs w:val=\"20\"/>\n" +
                    "                                    </w:rPr>\n" +
                    "                                </w:pPr>\n" +
                    "                                <w:r>\n" +
                    "                                    <w:rPr>\n" +
                    "                                        <w:rFonts w:eastAsia=\"Calibri\" w:cs=\"Calibri\" w:asciiTheme=\"minorHAnsi\" w:hAnsiTheme=\"minorHAnsi\"/>\n" +
                    "                                        <w:sz w:val=\"20\"/>\n" +
                    "                                        <w:szCs w:val=\"20\"/>\n" +
                    "                                    </w:rPr>\n" +
                    "                                    <w:t xml:space=\"preserve\"> No </w:t>\n" +
                    "                                </w:r>\n" +
                    "                            </w:p>\n" +
                    "                        </w:tc>\n" +
                    "                    </w:tr>\n" +
                    "                </w:tbl>"
            mainPart.addObject(org.docx4j.XmlUtils.unmarshalString(arrayCGHDetails));
        }

        def familyInformation = "<w:p xmlns:w=\"http://schemas.openxmlformats.org/wordprocessingml/2006/main\" >\n" +
                "                    <w:pPr>\n" +
                "                        <w:spacing w:after=\"120\"/>\n" +
                "                        <w:rPr>\n" +
                "                            <w:rFonts w:eastAsia=\"Calibri\" w:cs=\"Calibri\" w:asciiTheme=\"minorHAnsi\" w:hAnsiTheme=\"minorHAnsi\"/>\n" +
                "                            <w:b/>\n" +
                "                            <w:sz w:val=\"22\"/>\n" +
                "                            <w:szCs w:val=\"22\"/>\n" +
                "                            <w:u w:val=\"single\"/>\n" +
                "                        </w:rPr>\n" +
                "                    </w:pPr>\n" +
                "                    <w:proofErr w:type=\"gramStart\"/>\n" +
                "                    <w:r>\n" +
                "                        <w:rPr>\n" +
                "                            <w:rFonts w:eastAsia=\"Calibri\" w:cs=\"Calibri\" w:asciiTheme=\"minorHAnsi\" w:hAnsiTheme=\"minorHAnsi\"/>\n" +
                "                            <w:b/>\n" +
                "                            <w:sz w:val=\"22\"/>\n" +
                "                            <w:szCs w:val=\"22\"/>\n" +
                "                            <w:u w:val=\"single\"/>\n" +
                "                        </w:rPr>\n" +
                "                        <w:t>Family Information</w:t>\n" +
                "                    </w:r>\n" +
                "                </w:p>";
        mainPart.addObject(org.docx4j.XmlUtils.unmarshalString(familyInformation));


        def otherFamilyMembersAffected  = "<w:p xmlns:w=\"http://schemas.openxmlformats.org/wordprocessingml/2006/main\" >\n" +
                "                                <w:pPr>\n" +
                "                                    <w:spacing w:after=\"120\"/>\n" +
                "                                    <w:rPr>\n" +
                "                                        <w:rFonts w:eastAsia=\"Calibri\" w:cs=\"Calibri\" w:asciiTheme=\"minorHAnsi\" w:hAnsiTheme=\"minorHAnsi\"/>\n" +
                "                                        <w:sz w:val=\"20\"/>\n" +
                "                                        <w:szCs w:val=\"20\"/>\n" +
                "                                    </w:rPr>\n" +
                "                                </w:pPr>\n" +
                "                                <w:r>\n" +
                "                                    <w:rPr>\n" +
                "                                        <w:rFonts w:eastAsia=\"Calibri\" w:cs=\"Calibri\" w:asciiTheme=\"minorHAnsi\" w:hAnsiTheme=\"minorHAnsi\"/>\n" +
                "                                        <w:sz w:val=\"20\"/>\n" +
                "                                        <w:szCs w:val=\"20\"/>\n" +
                "                                    </w:rPr>\n" +
                "                                    <w:t xml:space=\"preserve\">Are any other family members affected with the same or related condition?</w:t>\n" +
                "                                </w:r>\n" +
                "                            </w:p>"
        mainPart.addObject(org.docx4j.XmlUtils.unmarshalString(otherFamilyMembersAffected));
        if (referralRecordInstance?.otherFamilyMembersAffected?.familyMembersAffectedTypeName == 'Yes'){
            def otherFamilyMembersAffectedDetails = "<w:tbl xmlns:w=\"http://schemas.openxmlformats.org/wordprocessingml/2006/main\" >\n" +
                    "                    <w:tblPr>\n" +
                    "                        <w:tblW w:w=\"0\" w:type=\"auto\"/>\n" +
                    "                        <w:tblInd w:w=\"98\" w:type=\"dxa\"/>\n" +
                    "                        <w:tblBorders>\n" +
                    "                            <w:top w:val=\"single\" w:color=\"auto\" w:sz=\"4\" w:space=\"0\"/>\n" +
                    "                            <w:left w:val=\"single\" w:color=\"auto\" w:sz=\"4\" w:space=\"0\"/>\n" +
                    "                            <w:bottom w:val=\"single\" w:color=\"auto\" w:sz=\"4\" w:space=\"0\"/>\n" +
                    "                            <w:right w:val=\"single\" w:color=\"auto\" w:sz=\"4\" w:space=\"0\"/>\n" +
                    "                            <w:insideH w:val=\"single\" w:color=\"auto\" w:sz=\"4\" w:space=\"0\"/>\n" +
                    "                            <w:insideV w:val=\"single\" w:color=\"auto\" w:sz=\"4\" w:space=\"0\"/>\n" +
                    "                        </w:tblBorders>\n" +
                    "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"D9D9D9\" w:themeFill=\"background1\" w:themeFillShade=\"D9\"/>\n" +
                    "                        <w:tblCellMar>\n" +
                    "                            <w:left w:w=\"10\" w:type=\"dxa\"/>\n" +
                    "                            <w:right w:w=\"10\" w:type=\"dxa\"/>\n" +
                    "                        </w:tblCellMar>\n" +
                    "                        <w:tblLook w:firstRow=\"1\" w:lastRow=\"0\" w:firstColumn=\"1\" w:lastColumn=\"0\" w:noHBand=\"0\" w:noVBand=\"1\" w:val=\"04A0\"/>\n" +
                    "                    </w:tblPr>\n" +
                    "                    <w:tblGrid>\n" +
                    "                        <w:gridCol w:w=\"9144\"/>\n" +
                    "                    </w:tblGrid>\n" +
                    "                    <w:tr w:rsidTr=\"00C87F4E\">\n" +
                    "                        <w:trPr>\n" +
                    "                            <w:trHeight w:val=\"1\"/>\n" +
                    "                        </w:trPr>\n" +
                    "                        <w:tc>\n" +
                    "                            <w:tcPr>\n" +
                    "                                <w:tcW w:w=\"9144\" w:type=\"dxa\"/>\n" +
                    "                                <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"D9D9D9\" w:themeFill=\"background1\" w:themeFillShade=\"D9\"/>\n" +
                    "                                <w:tcMar>\n" +
                    "                                    <w:left w:w=\"108\" w:type=\"dxa\"/>\n" +
                    "                                    <w:right w:w=\"108\" w:type=\"dxa\"/>\n" +
                    "                                </w:tcMar>\n" +
                    "                            </w:tcPr>\n" +
                    "                            <w:p>\n" +
                    "                                <w:pPr>\n" +
                    "                                    <w:spacing w:after=\"120\"/>\n" +
                    "                                    <w:rPr>\n" +
                    "                                        <w:rFonts w:eastAsia=\"Calibri\" w:cs=\"Calibri\" w:asciiTheme=\"minorHAnsi\" w:hAnsiTheme=\"minorHAnsi\"/>\n" +
                    "                                        <w:sz w:val=\"20\"/>\n" +
                    "                                        <w:szCs w:val=\"20\"/>\n" +
                    "                                    </w:rPr>\n" +
                    "                                </w:pPr>\n" +
                    "                                <w:r>\n" +
                    "                                    <w:rPr>\n" +
                    "                                        <w:rFonts w:eastAsia=\"Calibri\" w:cs=\"Calibri\" w:asciiTheme=\"minorHAnsi\" w:hAnsiTheme=\"minorHAnsi\"/>\n" +
                    "                                        <w:sz w:val=\"20\"/>\n" +
                    "                                        <w:szCs w:val=\"20\"/>\n" +
                    "                                    </w:rPr>\n" +
                    "                                    <w:t xml:space=\"preserve\"> Yes, ${referralRecordInstance?.otherFamilyMembersAffectedDetails ?: ''}</w:t>\n" +
                    "                                </w:r>\n" +
                    "                            </w:p>\n" +
                    "                        </w:tc>\n" +
                    "                    </w:tr>\n" +
                    "                </w:tbl>"
            mainPart.addObject(org.docx4j.XmlUtils.unmarshalString(otherFamilyMembersAffectedDetails));
        }else {
            def otherFamilyMembersAffectedDetails = "<w:tbl xmlns:w=\"http://schemas.openxmlformats.org/wordprocessingml/2006/main\" >\n" +
                    "                    <w:tblPr>\n" +
                    "                        <w:tblW w:w=\"0\" w:type=\"auto\"/>\n" +
                    "                        <w:tblInd w:w=\"98\" w:type=\"dxa\"/>\n" +
                    "                        <w:tblBorders>\n" +
                    "                            <w:top w:val=\"single\" w:color=\"auto\" w:sz=\"4\" w:space=\"0\"/>\n" +
                    "                            <w:left w:val=\"single\" w:color=\"auto\" w:sz=\"4\" w:space=\"0\"/>\n" +
                    "                            <w:bottom w:val=\"single\" w:color=\"auto\" w:sz=\"4\" w:space=\"0\"/>\n" +
                    "                            <w:right w:val=\"single\" w:color=\"auto\" w:sz=\"4\" w:space=\"0\"/>\n" +
                    "                            <w:insideH w:val=\"single\" w:color=\"auto\" w:sz=\"4\" w:space=\"0\"/>\n" +
                    "                            <w:insideV w:val=\"single\" w:color=\"auto\" w:sz=\"4\" w:space=\"0\"/>\n" +
                    "                        </w:tblBorders>\n" +
                    "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"D9D9D9\" w:themeFill=\"background1\" w:themeFillShade=\"D9\"/>\n" +
                    "                        <w:tblCellMar>\n" +
                    "                            <w:left w:w=\"10\" w:type=\"dxa\"/>\n" +
                    "                            <w:right w:w=\"10\" w:type=\"dxa\"/>\n" +
                    "                        </w:tblCellMar>\n" +
                    "                        <w:tblLook w:firstRow=\"1\" w:lastRow=\"0\" w:firstColumn=\"1\" w:lastColumn=\"0\" w:noHBand=\"0\" w:noVBand=\"1\" w:val=\"04A0\"/>\n" +
                    "                    </w:tblPr>\n" +
                    "                    <w:tblGrid>\n" +
                    "                        <w:gridCol w:w=\"9144\"/>\n" +
                    "                    </w:tblGrid>\n" +
                    "                    <w:tr w:rsidTr=\"00C87F4E\">\n" +
                    "                        <w:trPr>\n" +
                    "                            <w:trHeight w:val=\"1\"/>\n" +
                    "                        </w:trPr>\n" +
                    "                        <w:tc>\n" +
                    "                            <w:tcPr>\n" +
                    "                                <w:tcW w:w=\"9144\" w:type=\"dxa\"/>\n" +
                    "                                <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"D9D9D9\" w:themeFill=\"background1\" w:themeFillShade=\"D9\"/>\n" +
                    "                                <w:tcMar>\n" +
                    "                                    <w:left w:w=\"108\" w:type=\"dxa\"/>\n" +
                    "                                    <w:right w:w=\"108\" w:type=\"dxa\"/>\n" +
                    "                                </w:tcMar>\n" +
                    "                            </w:tcPr>\n" +
                    "                            <w:p>\n" +
                    "                                <w:pPr>\n" +
                    "                                    <w:spacing w:after=\"120\"/>\n" +
                    "                                    <w:rPr>\n" +
                    "                                        <w:rFonts w:eastAsia=\"Calibri\" w:cs=\"Calibri\" w:asciiTheme=\"minorHAnsi\" w:hAnsiTheme=\"minorHAnsi\"/>\n" +
                    "                                        <w:sz w:val=\"20\"/>\n" +
                    "                                        <w:szCs w:val=\"20\"/>\n" +
                    "                                    </w:rPr>\n" +
                    "                                </w:pPr>\n" +
                    "                                <w:r>\n" +
                    "                                    <w:rPr>\n" +
                    "                                        <w:rFonts w:eastAsia=\"Calibri\" w:cs=\"Calibri\" w:asciiTheme=\"minorHAnsi\" w:hAnsiTheme=\"minorHAnsi\"/>\n" +
                    "                                        <w:sz w:val=\"20\"/>\n" +
                    "                                        <w:szCs w:val=\"20\"/>\n" +
                    "                                    </w:rPr>\n" +
                    "                                    <w:t xml:space=\"preserve\"> ${referralRecordInstance.otherFamilyMembersAffected ?: ''} </w:t>\n" +
                    "                                </w:r>\n" +
                    "                            </w:p>\n" +
                    "                        </w:tc>\n" +
                    "                    </w:tr>\n" +
                    "                </w:tbl>"
            mainPart.addObject(org.docx4j.XmlUtils.unmarshalString(otherFamilyMembersAffectedDetails));
        }

        def consanguinityEvidence  = "<w:p xmlns:w=\"http://schemas.openxmlformats.org/wordprocessingml/2006/main\" >\n" +
                "                                <w:pPr>\n" +
                "                                    <w:spacing w:after=\"120\"/>\n" +
                "                                    <w:rPr>\n" +
                "                                        <w:rFonts w:eastAsia=\"Calibri\" w:cs=\"Calibri\" w:asciiTheme=\"minorHAnsi\" w:hAnsiTheme=\"minorHAnsi\"/>\n" +
                "                                        <w:sz w:val=\"20\"/>\n" +
                "                                        <w:szCs w:val=\"20\"/>\n" +
                "                                    </w:rPr>\n" +
                "                                </w:pPr>\n" +
                "                                <w:r>\n" +
                "                                    <w:rPr>\n" +
                "                                        <w:rFonts w:eastAsia=\"Calibri\" w:cs=\"Calibri\" w:asciiTheme=\"minorHAnsi\" w:hAnsiTheme=\"minorHAnsi\"/>\n" +
                "                                        <w:sz w:val=\"20\"/>\n" +
                "                                        <w:szCs w:val=\"20\"/>\n" +
                "                                    </w:rPr>\n" +
                "                                    <w:t xml:space=\"preserve\">Is there evidence of consanguinity?</w:t>\n" +
                "                                </w:r>\n" +
                "                            </w:p>"
        mainPart.addObject(org.docx4j.XmlUtils.unmarshalString(consanguinityEvidence));
        if (referralRecordInstance?.consanguinityEvidence?.consanguinityEvidence == 'Yes'){
            def consanguinityEvidenceDetails = "<w:tbl xmlns:w=\"http://schemas.openxmlformats.org/wordprocessingml/2006/main\" >\n" +
                    "                    <w:tblPr>\n" +
                    "                        <w:tblW w:w=\"0\" w:type=\"auto\"/>\n" +
                    "                        <w:tblInd w:w=\"98\" w:type=\"dxa\"/>\n" +
                    "                        <w:tblBorders>\n" +
                    "                            <w:top w:val=\"single\" w:color=\"auto\" w:sz=\"4\" w:space=\"0\"/>\n" +
                    "                            <w:left w:val=\"single\" w:color=\"auto\" w:sz=\"4\" w:space=\"0\"/>\n" +
                    "                            <w:bottom w:val=\"single\" w:color=\"auto\" w:sz=\"4\" w:space=\"0\"/>\n" +
                    "                            <w:right w:val=\"single\" w:color=\"auto\" w:sz=\"4\" w:space=\"0\"/>\n" +
                    "                            <w:insideH w:val=\"single\" w:color=\"auto\" w:sz=\"4\" w:space=\"0\"/>\n" +
                    "                            <w:insideV w:val=\"single\" w:color=\"auto\" w:sz=\"4\" w:space=\"0\"/>\n" +
                    "                        </w:tblBorders>\n" +
                    "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"D9D9D9\" w:themeFill=\"background1\" w:themeFillShade=\"D9\"/>\n" +
                    "                        <w:tblCellMar>\n" +
                    "                            <w:left w:w=\"10\" w:type=\"dxa\"/>\n" +
                    "                            <w:right w:w=\"10\" w:type=\"dxa\"/>\n" +
                    "                        </w:tblCellMar>\n" +
                    "                        <w:tblLook w:firstRow=\"1\" w:lastRow=\"0\" w:firstColumn=\"1\" w:lastColumn=\"0\" w:noHBand=\"0\" w:noVBand=\"1\" w:val=\"04A0\"/>\n" +
                    "                    </w:tblPr>\n" +
                    "                    <w:tblGrid>\n" +
                    "                        <w:gridCol w:w=\"9144\"/>\n" +
                    "                    </w:tblGrid>\n" +
                    "                    <w:tr w:rsidTr=\"00C87F4E\">\n" +
                    "                        <w:trPr>\n" +
                    "                            <w:trHeight w:val=\"1\"/>\n" +
                    "                        </w:trPr>\n" +
                    "                        <w:tc>\n" +
                    "                            <w:tcPr>\n" +
                    "                                <w:tcW w:w=\"9144\" w:type=\"dxa\"/>\n" +
                    "                                <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"D9D9D9\" w:themeFill=\"background1\" w:themeFillShade=\"D9\"/>\n" +
                    "                                <w:tcMar>\n" +
                    "                                    <w:left w:w=\"108\" w:type=\"dxa\"/>\n" +
                    "                                    <w:right w:w=\"108\" w:type=\"dxa\"/>\n" +
                    "                                </w:tcMar>\n" +
                    "                            </w:tcPr>\n" +
                    "                            <w:p>\n" +
                    "                                <w:pPr>\n" +
                    "                                    <w:spacing w:after=\"120\"/>\n" +
                    "                                    <w:rPr>\n" +
                    "                                        <w:rFonts w:eastAsia=\"Calibri\" w:cs=\"Calibri\" w:asciiTheme=\"minorHAnsi\" w:hAnsiTheme=\"minorHAnsi\"/>\n" +
                    "                                        <w:sz w:val=\"20\"/>\n" +
                    "                                        <w:szCs w:val=\"20\"/>\n" +
                    "                                    </w:rPr>\n" +
                    "                                </w:pPr>\n" +
                    "                                <w:r>\n" +
                    "                                    <w:rPr>\n" +
                    "                                        <w:rFonts w:eastAsia=\"Calibri\" w:cs=\"Calibri\" w:asciiTheme=\"minorHAnsi\" w:hAnsiTheme=\"minorHAnsi\"/>\n" +
                    "                                        <w:sz w:val=\"20\"/>\n" +
                    "                                        <w:szCs w:val=\"20\"/>\n" +
                    "                                    </w:rPr>\n" +
                    "                                    <w:t xml:space=\"preserve\"> Yes, ${referralRecordInstance?.consanguinityEvidenceDetails ?: ''}</w:t>\n" +
                    "                                </w:r>\n" +
                    "                            </w:p>\n" +
                    "                        </w:tc>\n" +
                    "                    </w:tr>\n" +
                    "                </w:tbl>"
            mainPart.addObject(org.docx4j.XmlUtils.unmarshalString(consanguinityEvidenceDetails));
        }else {
            def consanguinityEvidenceDetails = "<w:tbl xmlns:w=\"http://schemas.openxmlformats.org/wordprocessingml/2006/main\" >\n" +
                    "                    <w:tblPr>\n" +
                    "                        <w:tblW w:w=\"0\" w:type=\"auto\"/>\n" +
                    "                        <w:tblInd w:w=\"98\" w:type=\"dxa\"/>\n" +
                    "                        <w:tblBorders>\n" +
                    "                            <w:top w:val=\"single\" w:color=\"auto\" w:sz=\"4\" w:space=\"0\"/>\n" +
                    "                            <w:left w:val=\"single\" w:color=\"auto\" w:sz=\"4\" w:space=\"0\"/>\n" +
                    "                            <w:bottom w:val=\"single\" w:color=\"auto\" w:sz=\"4\" w:space=\"0\"/>\n" +
                    "                            <w:right w:val=\"single\" w:color=\"auto\" w:sz=\"4\" w:space=\"0\"/>\n" +
                    "                            <w:insideH w:val=\"single\" w:color=\"auto\" w:sz=\"4\" w:space=\"0\"/>\n" +
                    "                            <w:insideV w:val=\"single\" w:color=\"auto\" w:sz=\"4\" w:space=\"0\"/>\n" +
                    "                        </w:tblBorders>\n" +
                    "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"D9D9D9\" w:themeFill=\"background1\" w:themeFillShade=\"D9\"/>\n" +
                    "                        <w:tblCellMar>\n" +
                    "                            <w:left w:w=\"10\" w:type=\"dxa\"/>\n" +
                    "                            <w:right w:w=\"10\" w:type=\"dxa\"/>\n" +
                    "                        </w:tblCellMar>\n" +
                    "                        <w:tblLook w:firstRow=\"1\" w:lastRow=\"0\" w:firstColumn=\"1\" w:lastColumn=\"0\" w:noHBand=\"0\" w:noVBand=\"1\" w:val=\"04A0\"/>\n" +
                    "                    </w:tblPr>\n" +
                    "                    <w:tblGrid>\n" +
                    "                        <w:gridCol w:w=\"9144\"/>\n" +
                    "                    </w:tblGrid>\n" +
                    "                    <w:tr w:rsidTr=\"00C87F4E\">\n" +
                    "                        <w:trPr>\n" +
                    "                            <w:trHeight w:val=\"1\"/>\n" +
                    "                        </w:trPr>\n" +
                    "                        <w:tc>\n" +
                    "                            <w:tcPr>\n" +
                    "                                <w:tcW w:w=\"9144\" w:type=\"dxa\"/>\n" +
                    "                                <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"D9D9D9\" w:themeFill=\"background1\" w:themeFillShade=\"D9\"/>\n" +
                    "                                <w:tcMar>\n" +
                    "                                    <w:left w:w=\"108\" w:type=\"dxa\"/>\n" +
                    "                                    <w:right w:w=\"108\" w:type=\"dxa\"/>\n" +
                    "                                </w:tcMar>\n" +
                    "                            </w:tcPr>\n" +
                    "                            <w:p>\n" +
                    "                                <w:pPr>\n" +
                    "                                    <w:spacing w:after=\"120\"/>\n" +
                    "                                    <w:rPr>\n" +
                    "                                        <w:rFonts w:eastAsia=\"Calibri\" w:cs=\"Calibri\" w:asciiTheme=\"minorHAnsi\" w:hAnsiTheme=\"minorHAnsi\"/>\n" +
                    "                                        <w:sz w:val=\"20\"/>\n" +
                    "                                        <w:szCs w:val=\"20\"/>\n" +
                    "                                    </w:rPr>\n" +
                    "                                </w:pPr>\n" +
                    "                                <w:r>\n" +
                    "                                    <w:rPr>\n" +
                    "                                        <w:rFonts w:eastAsia=\"Calibri\" w:cs=\"Calibri\" w:asciiTheme=\"minorHAnsi\" w:hAnsiTheme=\"minorHAnsi\"/>\n" +
                    "                                        <w:sz w:val=\"20\"/>\n" +
                    "                                        <w:szCs w:val=\"20\"/>\n" +
                    "                                    </w:rPr>\n" +
                    "                                    <w:t xml:space=\"preserve\"> ${referralRecordInstance.consanguinityEvidence ?: ''} </w:t>\n" +
                    "                                </w:r>\n" +
                    "                            </w:p>\n" +
                    "                        </w:tc>\n" +
                    "                    </w:tr>\n" +
                    "                </w:tbl>"
            mainPart.addObject(org.docx4j.XmlUtils.unmarshalString(consanguinityEvidenceDetails));
        }

        def penetranceEvidence  = "<w:p xmlns:w=\"http://schemas.openxmlformats.org/wordprocessingml/2006/main\" >\n" +
                "                                <w:pPr>\n" +
                "                                    <w:spacing w:after=\"120\"/>\n" +
                "                                    <w:rPr>\n" +
                "                                        <w:rFonts w:eastAsia=\"Calibri\" w:cs=\"Calibri\" w:asciiTheme=\"minorHAnsi\" w:hAnsiTheme=\"minorHAnsi\"/>\n" +
                "                                        <w:sz w:val=\"20\"/>\n" +
                "                                        <w:szCs w:val=\"20\"/>\n" +
                "                                    </w:rPr>\n" +
                "                                </w:pPr>\n" +
                "                                <w:r>\n" +
                "                                    <w:rPr>\n" +
                "                                        <w:rFonts w:eastAsia=\"Calibri\" w:cs=\"Calibri\" w:asciiTheme=\"minorHAnsi\" w:hAnsiTheme=\"minorHAnsi\"/>\n" +
                "                                        <w:sz w:val=\"20\"/>\n" +
                "                                        <w:szCs w:val=\"20\"/>\n" +
                "                                    </w:rPr>\n" +
                "                                    <w:t xml:space=\"preserve\">Is there evidence of incomplete penetrance in this condition?</w:t>\n" +
                "                                </w:r>\n" +
                "                            </w:p>"
        mainPart.addObject(org.docx4j.XmlUtils.unmarshalString(penetranceEvidence));
        if (referralRecordInstance?.consanguinityEvidence?.consanguinityEvidence == 'Yes'){
            def penetranceEvidenceDetails = "<w:tbl xmlns:w=\"http://schemas.openxmlformats.org/wordprocessingml/2006/main\" >\n" +
                    "                    <w:tblPr>\n" +
                    "                        <w:tblW w:w=\"0\" w:type=\"auto\"/>\n" +
                    "                        <w:tblInd w:w=\"98\" w:type=\"dxa\"/>\n" +
                    "                        <w:tblBorders>\n" +
                    "                            <w:top w:val=\"single\" w:color=\"auto\" w:sz=\"4\" w:space=\"0\"/>\n" +
                    "                            <w:left w:val=\"single\" w:color=\"auto\" w:sz=\"4\" w:space=\"0\"/>\n" +
                    "                            <w:bottom w:val=\"single\" w:color=\"auto\" w:sz=\"4\" w:space=\"0\"/>\n" +
                    "                            <w:right w:val=\"single\" w:color=\"auto\" w:sz=\"4\" w:space=\"0\"/>\n" +
                    "                            <w:insideH w:val=\"single\" w:color=\"auto\" w:sz=\"4\" w:space=\"0\"/>\n" +
                    "                            <w:insideV w:val=\"single\" w:color=\"auto\" w:sz=\"4\" w:space=\"0\"/>\n" +
                    "                        </w:tblBorders>\n" +
                    "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"D9D9D9\" w:themeFill=\"background1\" w:themeFillShade=\"D9\"/>\n" +
                    "                        <w:tblCellMar>\n" +
                    "                            <w:left w:w=\"10\" w:type=\"dxa\"/>\n" +
                    "                            <w:right w:w=\"10\" w:type=\"dxa\"/>\n" +
                    "                        </w:tblCellMar>\n" +
                    "                        <w:tblLook w:firstRow=\"1\" w:lastRow=\"0\" w:firstColumn=\"1\" w:lastColumn=\"0\" w:noHBand=\"0\" w:noVBand=\"1\" w:val=\"04A0\"/>\n" +
                    "                    </w:tblPr>\n" +
                    "                    <w:tblGrid>\n" +
                    "                        <w:gridCol w:w=\"9144\"/>\n" +
                    "                    </w:tblGrid>\n" +
                    "                    <w:tr w:rsidTr=\"00C87F4E\">\n" +
                    "                        <w:trPr>\n" +
                    "                            <w:trHeight w:val=\"1\"/>\n" +
                    "                        </w:trPr>\n" +
                    "                        <w:tc>\n" +
                    "                            <w:tcPr>\n" +
                    "                                <w:tcW w:w=\"9144\" w:type=\"dxa\"/>\n" +
                    "                                <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"D9D9D9\" w:themeFill=\"background1\" w:themeFillShade=\"D9\"/>\n" +
                    "                                <w:tcMar>\n" +
                    "                                    <w:left w:w=\"108\" w:type=\"dxa\"/>\n" +
                    "                                    <w:right w:w=\"108\" w:type=\"dxa\"/>\n" +
                    "                                </w:tcMar>\n" +
                    "                            </w:tcPr>\n" +
                    "                            <w:p>\n" +
                    "                                <w:pPr>\n" +
                    "                                    <w:spacing w:after=\"120\"/>\n" +
                    "                                    <w:rPr>\n" +
                    "                                        <w:rFonts w:eastAsia=\"Calibri\" w:cs=\"Calibri\" w:asciiTheme=\"minorHAnsi\" w:hAnsiTheme=\"minorHAnsi\"/>\n" +
                    "                                        <w:sz w:val=\"20\"/>\n" +
                    "                                        <w:szCs w:val=\"20\"/>\n" +
                    "                                    </w:rPr>\n" +
                    "                                </w:pPr>\n" +
                    "                                <w:r>\n" +
                    "                                    <w:rPr>\n" +
                    "                                        <w:rFonts w:eastAsia=\"Calibri\" w:cs=\"Calibri\" w:asciiTheme=\"minorHAnsi\" w:hAnsiTheme=\"minorHAnsi\"/>\n" +
                    "                                        <w:sz w:val=\"20\"/>\n" +
                    "                                        <w:szCs w:val=\"20\"/>\n" +
                    "                                    </w:rPr>\n" +
                    "                                    <w:t xml:space=\"preserve\"> Yes, ${referralRecordInstance?.penetranceDetails ?: ''}</w:t>\n" +
                    "                                </w:r>\n" +
                    "                            </w:p>\n" +
                    "                        </w:tc>\n" +
                    "                    </w:tr>\n" +
                    "                </w:tbl>"
            mainPart.addObject(org.docx4j.XmlUtils.unmarshalString(penetranceEvidenceDetails));
        }else {
            def penetranceEvidenceDetails = "<w:tbl xmlns:w=\"http://schemas.openxmlformats.org/wordprocessingml/2006/main\" >\n" +
                    "                    <w:tblPr>\n" +
                    "                        <w:tblW w:w=\"0\" w:type=\"auto\"/>\n" +
                    "                        <w:tblInd w:w=\"98\" w:type=\"dxa\"/>\n" +
                    "                        <w:tblBorders>\n" +
                    "                            <w:top w:val=\"single\" w:color=\"auto\" w:sz=\"4\" w:space=\"0\"/>\n" +
                    "                            <w:left w:val=\"single\" w:color=\"auto\" w:sz=\"4\" w:space=\"0\"/>\n" +
                    "                            <w:bottom w:val=\"single\" w:color=\"auto\" w:sz=\"4\" w:space=\"0\"/>\n" +
                    "                            <w:right w:val=\"single\" w:color=\"auto\" w:sz=\"4\" w:space=\"0\"/>\n" +
                    "                            <w:insideH w:val=\"single\" w:color=\"auto\" w:sz=\"4\" w:space=\"0\"/>\n" +
                    "                            <w:insideV w:val=\"single\" w:color=\"auto\" w:sz=\"4\" w:space=\"0\"/>\n" +
                    "                        </w:tblBorders>\n" +
                    "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"D9D9D9\" w:themeFill=\"background1\" w:themeFillShade=\"D9\"/>\n" +
                    "                        <w:tblCellMar>\n" +
                    "                            <w:left w:w=\"10\" w:type=\"dxa\"/>\n" +
                    "                            <w:right w:w=\"10\" w:type=\"dxa\"/>\n" +
                    "                        </w:tblCellMar>\n" +
                    "                        <w:tblLook w:firstRow=\"1\" w:lastRow=\"0\" w:firstColumn=\"1\" w:lastColumn=\"0\" w:noHBand=\"0\" w:noVBand=\"1\" w:val=\"04A0\"/>\n" +
                    "                    </w:tblPr>\n" +
                    "                    <w:tblGrid>\n" +
                    "                        <w:gridCol w:w=\"9144\"/>\n" +
                    "                    </w:tblGrid>\n" +
                    "                    <w:tr w:rsidTr=\"00C87F4E\">\n" +
                    "                        <w:trPr>\n" +
                    "                            <w:trHeight w:val=\"1\"/>\n" +
                    "                        </w:trPr>\n" +
                    "                        <w:tc>\n" +
                    "                            <w:tcPr>\n" +
                    "                                <w:tcW w:w=\"9144\" w:type=\"dxa\"/>\n" +
                    "                                <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"D9D9D9\" w:themeFill=\"background1\" w:themeFillShade=\"D9\"/>\n" +
                    "                                <w:tcMar>\n" +
                    "                                    <w:left w:w=\"108\" w:type=\"dxa\"/>\n" +
                    "                                    <w:right w:w=\"108\" w:type=\"dxa\"/>\n" +
                    "                                </w:tcMar>\n" +
                    "                            </w:tcPr>\n" +
                    "                            <w:p>\n" +
                    "                                <w:pPr>\n" +
                    "                                    <w:spacing w:after=\"120\"/>\n" +
                    "                                    <w:rPr>\n" +
                    "                                        <w:rFonts w:eastAsia=\"Calibri\" w:cs=\"Calibri\" w:asciiTheme=\"minorHAnsi\" w:hAnsiTheme=\"minorHAnsi\"/>\n" +
                    "                                        <w:sz w:val=\"20\"/>\n" +
                    "                                        <w:szCs w:val=\"20\"/>\n" +
                    "                                    </w:rPr>\n" +
                    "                                </w:pPr>\n" +
                    "                                <w:r>\n" +
                    "                                    <w:rPr>\n" +
                    "                                        <w:rFonts w:eastAsia=\"Calibri\" w:cs=\"Calibri\" w:asciiTheme=\"minorHAnsi\" w:hAnsiTheme=\"minorHAnsi\"/>\n" +
                    "                                        <w:sz w:val=\"20\"/>\n" +
                    "                                        <w:szCs w:val=\"20\"/>\n" +
                    "                                    </w:rPr>\n" +
                    "                                    <w:t xml:space=\"preserve\"> ${referralRecordInstance.penetrance ?: ''} </w:t>\n" +
                    "                                </w:r>\n" +
                    "                            </w:p>\n" +
                    "                        </w:tc>\n" +
                    "                    </w:tr>\n" +
                    "                </w:tbl>"
            mainPart.addObject(org.docx4j.XmlUtils.unmarshalString(penetranceEvidenceDetails));
        }

        def familyEthnicity = "<w:p xmlns:w=\"http://schemas.openxmlformats.org/wordprocessingml/2006/main\" >\n" +
                "                                <w:pPr>\n" +
                "                                    <w:spacing w:after=\"120\"/>\n" +
                "                                    <w:rPr>\n" +
                "                                        <w:rFonts w:eastAsia=\"Calibri\" w:cs=\"Calibri\" w:asciiTheme=\"minorHAnsi\" w:hAnsiTheme=\"minorHAnsi\"/>\n" +
                "                                        <w:sz w:val=\"20\"/>\n" +
                "                                        <w:szCs w:val=\"20\"/>\n" +
                "                                    </w:rPr>\n" +
                "                                </w:pPr>\n" +
                "                                <w:r>\n" +
                "                                    <w:rPr>\n" +
                "                                        <w:rFonts w:eastAsia=\"Calibri\" w:cs=\"Calibri\" w:asciiTheme=\"minorHAnsi\" w:hAnsiTheme=\"minorHAnsi\"/>\n" +
                "                                        <w:sz w:val=\"20\"/>\n" +
                "                                        <w:szCs w:val=\"20\"/>\n" +
                "                                    </w:rPr>\n" +
                "                                    <w:t xml:space=\"preserve\">Ethnicity of immediate family</w:t>\n" +
                "                                </w:r>\n" +
                "                            </w:p>"

        mainPart.addObject(org.docx4j.XmlUtils.unmarshalString(familyEthnicity));
        def familyEthnicityDetails = "<w:tbl xmlns:w=\"http://schemas.openxmlformats.org/wordprocessingml/2006/main\" >\n" +
                "                    <w:tblPr>\n" +
                "                        <w:tblW w:w=\"0\" w:type=\"auto\"/>\n" +
                "                        <w:tblInd w:w=\"98\" w:type=\"dxa\"/>\n" +
                "                        <w:tblBorders>\n" +
                "                            <w:top w:val=\"single\" w:color=\"auto\" w:sz=\"4\" w:space=\"0\"/>\n" +
                "                            <w:left w:val=\"single\" w:color=\"auto\" w:sz=\"4\" w:space=\"0\"/>\n" +
                "                            <w:bottom w:val=\"single\" w:color=\"auto\" w:sz=\"4\" w:space=\"0\"/>\n" +
                "                            <w:right w:val=\"single\" w:color=\"auto\" w:sz=\"4\" w:space=\"0\"/>\n" +
                "                            <w:insideH w:val=\"single\" w:color=\"auto\" w:sz=\"4\" w:space=\"0\"/>\n" +
                "                            <w:insideV w:val=\"single\" w:color=\"auto\" w:sz=\"4\" w:space=\"0\"/>\n" +
                "                        </w:tblBorders>\n" +
                "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"D9D9D9\" w:themeFill=\"background1\" w:themeFillShade=\"D9\"/>\n" +
                "                        <w:tblCellMar>\n" +
                "                            <w:left w:w=\"10\" w:type=\"dxa\"/>\n" +
                "                            <w:right w:w=\"10\" w:type=\"dxa\"/>\n" +
                "                        </w:tblCellMar>\n" +
                "                        <w:tblLook w:firstRow=\"1\" w:lastRow=\"0\" w:firstColumn=\"1\" w:lastColumn=\"0\" w:noHBand=\"0\" w:noVBand=\"1\" w:val=\"04A0\"/>\n" +
                "                    </w:tblPr>\n" +
                "                    <w:tblGrid>\n" +
                "                        <w:gridCol w:w=\"9144\"/>\n" +
                "                    </w:tblGrid>\n" +
                "                    <w:tr w:rsidTr=\"00C87F4E\">\n" +
                "                        <w:trPr>\n" +
                "                            <w:trHeight w:val=\"1\"/>\n" +
                "                        </w:trPr>\n" +
                "                        <w:tc>\n" +
                "                            <w:tcPr>\n" +
                "                                <w:tcW w:w=\"9144\" w:type=\"dxa\"/>\n" +
                "                                <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"D9D9D9\" w:themeFill=\"background1\" w:themeFillShade=\"D9\"/>\n" +
                "                                <w:tcMar>\n" +
                "                                    <w:left w:w=\"108\" w:type=\"dxa\"/>\n" +
                "                                    <w:right w:w=\"108\" w:type=\"dxa\"/>\n" +
                "                                </w:tcMar>\n" +
                "                            </w:tcPr>\n" +
                "                            <w:p>\n" +
                "                                <w:pPr>\n" +
                "                                    <w:spacing w:after=\"120\"/>\n" +
                "                                    <w:rPr>\n" +
                "                                        <w:rFonts w:eastAsia=\"Calibri\" w:cs=\"Calibri\" w:asciiTheme=\"minorHAnsi\" w:hAnsiTheme=\"minorHAnsi\"/>\n" +
                "                                        <w:sz w:val=\"20\"/>\n" +
                "                                        <w:szCs w:val=\"20\"/>\n" +
                "                                    </w:rPr>\n" +
                "                                </w:pPr>\n" +
                "                                <w:r>\n" +
                "                                    <w:rPr>\n" +
                "                                        <w:rFonts w:eastAsia=\"Calibri\" w:cs=\"Calibri\" w:asciiTheme=\"minorHAnsi\" w:hAnsiTheme=\"minorHAnsi\"/>\n" +
                "                                        <w:sz w:val=\"20\"/>\n" +
                "                                        <w:szCs w:val=\"20\"/>\n" +
                "                                    </w:rPr>\n" +
                "                                    <w:t xml:space=\"preserve\"> Mother: ${referralRecordInstance?.patients?.find{p -> p?.relatedFrom?.relationshipType == RelationshipType.findByRelationshipTypeName('Mother')}?.ethnicity ?: ''}</w:t>\n" +
                "                                </w:r>\n" +
                "                            </w:p>\n" +
                "                        </w:tc>\n" +
                "                        <w:tc>\n" +
                "                            <w:tcPr>\n" +
                "                                <w:tcW w:w=\"9144\" w:type=\"dxa\"/>\n" +
                "                                <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"D9D9D9\" w:themeFill=\"background1\" w:themeFillShade=\"D9\"/>\n" +
                "                                <w:tcMar>\n" +
                "                                    <w:left w:w=\"108\" w:type=\"dxa\"/>\n" +
                "                                    <w:right w:w=\"108\" w:type=\"dxa\"/>\n" +
                "                                </w:tcMar>\n" +
                "                            </w:tcPr>\n" +
                "                            <w:p>\n" +
                "                                <w:pPr>\n" +
                "                                    <w:spacing w:after=\"120\"/>\n" +
                "                                    <w:rPr>\n" +
                "                                        <w:rFonts w:eastAsia=\"Calibri\" w:cs=\"Calibri\" w:asciiTheme=\"minorHAnsi\" w:hAnsiTheme=\"minorHAnsi\"/>\n" +
                "                                        <w:sz w:val=\"20\"/>\n" +
                "                                        <w:szCs w:val=\"20\"/>\n" +
                "                                    </w:rPr>\n" +
                "                                </w:pPr>\n" +
                "                                <w:r>\n" +
                "                                    <w:rPr>\n" +
                "                                        <w:rFonts w:eastAsia=\"Calibri\" w:cs=\"Calibri\" w:asciiTheme=\"minorHAnsi\" w:hAnsiTheme=\"minorHAnsi\"/>\n" +
                "                                        <w:sz w:val=\"20\"/>\n" +
                "                                        <w:szCs w:val=\"20\"/>\n" +
                "                                    </w:rPr>\n" +
                "                                    <w:t xml:space=\"preserve\"> Father: ${referralRecordInstance?.patients?.find{p -> p?.relatedFrom?.relationshipType == RelationshipType.findByRelationshipTypeName('Father')}?.ethnicity ?: ''}</w:t>\n" +
                "                                </w:r>\n" +
                "                            </w:p>\n" +
                "                        </w:tc>\n" +
                "                    </w:tr>\n" +
                "                </w:tbl>"

        mainPart.addObject(org.docx4j.XmlUtils.unmarshalString(familyEthnicityDetails));

        def familyHistory = "<w:p xmlns:w=\"http://schemas.openxmlformats.org/wordprocessingml/2006/main\" >\n" +
                "                                <w:pPr>\n" +
                "                                    <w:spacing w:after=\"120\"/>\n" +
                "                                    <w:rPr>\n" +
                "                                        <w:rFonts w:eastAsia=\"Calibri\" w:cs=\"Calibri\" w:asciiTheme=\"minorHAnsi\" w:hAnsiTheme=\"minorHAnsi\"/>\n" +
                "                                        <w:sz w:val=\"20\"/>\n" +
                "                                        <w:szCs w:val=\"20\"/>\n" +
                "                                    </w:rPr>\n" +
                "                                </w:pPr>\n" +
                "                                <w:r>\n" +
                "                                    <w:rPr>\n" +
                "                                        <w:rFonts w:eastAsia=\"Calibri\" w:cs=\"Calibri\" w:asciiTheme=\"minorHAnsi\" w:hAnsiTheme=\"minorHAnsi\"/>\n" +
                "                                        <w:sz w:val=\"20\"/>\n" +
                "                                        <w:szCs w:val=\"20\"/>\n" +
                "                                    </w:rPr>\n" +
                "                                    <w:t xml:space=\"preserve\">Family History</w:t>\n" +
                "                                </w:r>\n" +
                "                            </w:p>"

        mainPart.addObject(org.docx4j.XmlUtils.unmarshalString(familyHistory));
        def familyHistoryDetails = "<w:tbl xmlns:w=\"http://schemas.openxmlformats.org/wordprocessingml/2006/main\" >\n" +
                "                    <w:tblPr>\n" +
                "                        <w:tblW w:w=\"0\" w:type=\"auto\"/>\n" +
                "                        <w:tblInd w:w=\"98\" w:type=\"dxa\"/>\n" +
                "                        <w:tblBorders>\n" +
                "                            <w:top w:val=\"single\" w:color=\"auto\" w:sz=\"4\" w:space=\"0\"/>\n" +
                "                            <w:left w:val=\"single\" w:color=\"auto\" w:sz=\"4\" w:space=\"0\"/>\n" +
                "                            <w:bottom w:val=\"single\" w:color=\"auto\" w:sz=\"4\" w:space=\"0\"/>\n" +
                "                            <w:right w:val=\"single\" w:color=\"auto\" w:sz=\"4\" w:space=\"0\"/>\n" +
                "                            <w:insideH w:val=\"single\" w:color=\"auto\" w:sz=\"4\" w:space=\"0\"/>\n" +
                "                            <w:insideV w:val=\"single\" w:color=\"auto\" w:sz=\"4\" w:space=\"0\"/>\n" +
                "                        </w:tblBorders>\n" +
                "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"D9D9D9\" w:themeFill=\"background1\" w:themeFillShade=\"D9\"/>\n" +
                "                        <w:tblCellMar>\n" +
                "                            <w:left w:w=\"10\" w:type=\"dxa\"/>\n" +
                "                            <w:right w:w=\"10\" w:type=\"dxa\"/>\n" +
                "                        </w:tblCellMar>\n" +
                "                        <w:tblLook w:firstRow=\"1\" w:lastRow=\"0\" w:firstColumn=\"1\" w:lastColumn=\"0\" w:noHBand=\"0\" w:noVBand=\"1\" w:val=\"04A0\"/>\n" +
                "                    </w:tblPr>\n" +
                "                    <w:tblGrid>\n" +
                "                        <w:gridCol w:w=\"9144\"/>\n" +
                "                    </w:tblGrid>\n" +
                "                    <w:tr w:rsidTr=\"00C87F4E\">\n" +
                "                        <w:trPr>\n" +
                "                            <w:trHeight w:val=\"1\"/>\n" +
                "                        </w:trPr>\n" +
                "                        <w:tc>\n" +
                "                            <w:tcPr>\n" +
                "                                <w:tcW w:w=\"9144\" w:type=\"dxa\"/>\n" +
                "                                <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"D9D9D9\" w:themeFill=\"background1\" w:themeFillShade=\"D9\"/>\n" +
                "                                <w:tcMar>\n" +
                "                                    <w:left w:w=\"108\" w:type=\"dxa\"/>\n" +
                "                                    <w:right w:w=\"108\" w:type=\"dxa\"/>\n" +
                "                                </w:tcMar>\n" +
                "                            </w:tcPr>\n" +
                "                            <w:p>\n" +
                "                                <w:pPr>\n" +
                "                                    <w:spacing w:after=\"120\"/>\n" +
                "                                    <w:rPr>\n" +
                "                                        <w:rFonts w:eastAsia=\"Calibri\" w:cs=\"Calibri\" w:asciiTheme=\"minorHAnsi\" w:hAnsiTheme=\"minorHAnsi\"/>\n" +
                "                                        <w:sz w:val=\"20\"/>\n" +
                "                                        <w:szCs w:val=\"20\"/>\n" +
                "                                    </w:rPr>\n" +
                "                                </w:pPr>\n" +
                "                                <w:r>\n" +
                "                                    <w:rPr>\n" +
                "                                        <w:rFonts w:eastAsia=\"Calibri\" w:cs=\"Calibri\" w:asciiTheme=\"minorHAnsi\" w:hAnsiTheme=\"minorHAnsi\"/>\n" +
                "                                        <w:sz w:val=\"20\"/>\n" +
                "                                        <w:szCs w:val=\"20\"/>\n" +
                "                                        <w:u w:val=\"single\"/>\n" +
                "                                    </w:rPr>\n" +
                "                                    <w:t xml:space=\"preserve\">Paternal</w:t>\n" +
                "                                </w:r>\n" +
                "                            </w:p>\n" +
                "                        </w:tc>\n" +
                "                        <w:tc>\n" +
                "                            <w:tcPr>\n" +
                "                                <w:tcW w:w=\"9144\" w:type=\"dxa\"/>\n" +
                "                                <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"D9D9D9\" w:themeFill=\"background1\" w:themeFillShade=\"D9\"/>\n" +
                "                                <w:tcMar>\n" +
                "                                    <w:left w:w=\"108\" w:type=\"dxa\"/>\n" +
                "                                    <w:right w:w=\"108\" w:type=\"dxa\"/>\n" +
                "                                </w:tcMar>\n" +
                "                            </w:tcPr>\n" +
                "                            <w:p>\n" +
                "                                <w:pPr>\n" +
                "                                    <w:spacing w:after=\"120\"/>\n" +
                "                                    <w:rPr>\n" +
                "                                        <w:rFonts w:eastAsia=\"Calibri\" w:cs=\"Calibri\" w:asciiTheme=\"minorHAnsi\" w:hAnsiTheme=\"minorHAnsi\"/>\n" +
                "                                        <w:sz w:val=\"20\"/>\n" +
                "                                        <w:szCs w:val=\"20\"/>\n" +
                "                                    </w:rPr>\n" +
                "                                </w:pPr>\n" +
                "                                <w:r>\n" +
                "                                    <w:rPr>\n" +
                "                                        <w:rFonts w:eastAsia=\"Calibri\" w:cs=\"Calibri\" w:asciiTheme=\"minorHAnsi\" w:hAnsiTheme=\"minorHAnsi\"/>\n" +
                "                                        <w:sz w:val=\"20\"/>\n" +
                "                                        <w:szCs w:val=\"20\"/>\n" +
                "                                        <w:u w:val=\"single\"/>\n" +
                "                                    </w:rPr>\n" +
                "                                    <w:t xml:space=\"preserve\">Maternal</w:t>\n" +
                "                                </w:r>\n" +
                "                            </w:p>\n" +
                "                        </w:tc>\n" +
                "                    </w:tr>\n" +
                "                </w:tbl>"

        mainPart.addObject(org.docx4j.XmlUtils.unmarshalString(familyHistoryDetails));

        def breastAndOrOvarianCancer = "<w:tbl xmlns:w=\"http://schemas.openxmlformats.org/wordprocessingml/2006/main\" >\n" +
                "                    <w:tblPr>\n" +
                "                        <w:tblW w:w=\"0\" w:type=\"auto\"/>\n" +
                "                        <w:tblInd w:w=\"98\" w:type=\"dxa\"/>\n" +
                "                        <w:tblBorders>\n" +
                "                            <w:top w:val=\"single\" w:color=\"auto\" w:sz=\"4\" w:space=\"0\"/>\n" +
                "                            <w:left w:val=\"single\" w:color=\"auto\" w:sz=\"4\" w:space=\"0\"/>\n" +
                "                            <w:bottom w:val=\"single\" w:color=\"auto\" w:sz=\"4\" w:space=\"0\"/>\n" +
                "                            <w:right w:val=\"single\" w:color=\"auto\" w:sz=\"4\" w:space=\"0\"/>\n" +
                "                            <w:insideH w:val=\"single\" w:color=\"auto\" w:sz=\"4\" w:space=\"0\"/>\n" +
                "                            <w:insideV w:val=\"single\" w:color=\"auto\" w:sz=\"4\" w:space=\"0\"/>\n" +
                "                        </w:tblBorders>\n" +
                "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"D9D9D9\" w:themeFill=\"background1\" w:themeFillShade=\"D9\"/>\n" +
                "                        <w:tblCellMar>\n" +
                "                            <w:left w:w=\"10\" w:type=\"dxa\"/>\n" +
                "                            <w:right w:w=\"10\" w:type=\"dxa\"/>\n" +
                "                        </w:tblCellMar>\n" +
                "                        <w:tblLook w:firstRow=\"1\" w:lastRow=\"0\" w:firstColumn=\"1\" w:lastColumn=\"0\" w:noHBand=\"0\" w:noVBand=\"1\" w:val=\"04A0\"/>\n" +
                "                    </w:tblPr>\n" +
                "                    <w:tblGrid>\n" +
                "                        <w:gridCol w:w=\"9144\"/>\n" +
                "                    </w:tblGrid>\n" +
                "                    <w:tr w:rsidTr=\"00C87F4E\">\n" +
                "                        <w:trPr>\n" +
                "                            <w:trHeight w:val=\"1\"/>\n" +
                "                        </w:trPr>\n" +
                "                        <w:tc>\n" +
                "                            <w:tcPr>\n" +
                "                                <w:tcW w:w=\"9144\" w:type=\"dxa\"/>\n" +
                "                                <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"D9D9D9\" w:themeFill=\"background1\" w:themeFillShade=\"D9\"/>\n" +
                "                                <w:tcMar>\n" +
                "                                    <w:left w:w=\"108\" w:type=\"dxa\"/>\n" +
                "                                    <w:right w:w=\"108\" w:type=\"dxa\"/>\n" +
                "                                </w:tcMar>\n" +
                "                            </w:tcPr>\n" +
                "                            <w:p>\n" +
                "                                <w:pPr>\n" +
                "                                    <w:spacing w:after=\"120\"/>\n" +
                "                                    <w:rPr>\n" +
                "                                        <w:rFonts w:eastAsia=\"Calibri\" w:cs=\"Calibri\" w:asciiTheme=\"minorHAnsi\" w:hAnsiTheme=\"minorHAnsi\"/>\n" +
                "                                        <w:sz w:val=\"20\"/>\n" +
                "                                        <w:szCs w:val=\"20\"/>\n" +
                "                                    </w:rPr>\n" +
                "                                </w:pPr>\n" +
                "                                <w:r>\n" +
                "                                    <w:rPr>\n" +
                "                                        <w:rFonts w:eastAsia=\"Calibri\" w:cs=\"Calibri\" w:asciiTheme=\"minorHAnsi\" w:hAnsiTheme=\"minorHAnsi\"/>\n" +
                "                                        <w:sz w:val=\"20\"/>\n" +
                "                                        <w:szCs w:val=\"20\"/>\n" +
                "                                    </w:rPr>\n" +
                "                                    <w:t xml:space=\"preserve\"> Breast And Or Ovarian Cancer: ${referralRecordInstance?.paternal?.first()?.breastAndOrOvarianCancer}</w:t>\n" +
                "                                </w:r>\n" +
                "                            </w:p>\n" +
                "                        </w:tc>\n" +
                "                        <w:tc>\n" +
                "                            <w:tcPr>\n" +
                "                                <w:tcW w:w=\"9144\" w:type=\"dxa\"/>\n" +
                "                                <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"D9D9D9\" w:themeFill=\"background1\" w:themeFillShade=\"D9\"/>\n" +
                "                                <w:tcMar>\n" +
                "                                    <w:left w:w=\"108\" w:type=\"dxa\"/>\n" +
                "                                    <w:right w:w=\"108\" w:type=\"dxa\"/>\n" +
                "                                </w:tcMar>\n" +
                "                            </w:tcPr>\n" +
                "                            <w:p>\n" +
                "                                <w:pPr>\n" +
                "                                    <w:spacing w:after=\"120\"/>\n" +
                "                                    <w:rPr>\n" +
                "                                        <w:rFonts w:eastAsia=\"Calibri\" w:cs=\"Calibri\" w:asciiTheme=\"minorHAnsi\" w:hAnsiTheme=\"minorHAnsi\"/>\n" +
                "                                        <w:sz w:val=\"20\"/>\n" +
                "                                        <w:szCs w:val=\"20\"/>\n" +
                "                                    </w:rPr>\n" +
                "                                </w:pPr>\n" +
                "                                <w:r>\n" +
                "                                    <w:rPr>\n" +
                "                                        <w:rFonts w:eastAsia=\"Calibri\" w:cs=\"Calibri\" w:asciiTheme=\"minorHAnsi\" w:hAnsiTheme=\"minorHAnsi\"/>\n" +
                "                                        <w:sz w:val=\"20\"/>\n" +
                "                                        <w:szCs w:val=\"20\"/>\n" +
                "                                    </w:rPr>\n" +
                "                                    <w:t xml:space=\"preserve\">Breast And Or Ovarian Cancer: ${referralRecordInstance?.maternal?.first()?.breastAndOrOvarianCancer}</w:t>\n" +
                "                                </w:r>\n" +
                "                            </w:p>\n" +
                "                        </w:tc>\n" +
                "                    </w:tr>\n" +
                "                </w:tbl>"

        mainPart.addObject(org.docx4j.XmlUtils.unmarshalString(breastAndOrOvarianCancer));

        def colorectalCancer = "<w:tbl xmlns:w=\"http://schemas.openxmlformats.org/wordprocessingml/2006/main\" >\n" +
                "                    <w:tblPr>\n" +
                "                        <w:tblW w:w=\"0\" w:type=\"auto\"/>\n" +
                "                        <w:tblInd w:w=\"98\" w:type=\"dxa\"/>\n" +
                "                        <w:tblBorders>\n" +
                "                            <w:top w:val=\"single\" w:color=\"auto\" w:sz=\"4\" w:space=\"0\"/>\n" +
                "                            <w:left w:val=\"single\" w:color=\"auto\" w:sz=\"4\" w:space=\"0\"/>\n" +
                "                            <w:bottom w:val=\"single\" w:color=\"auto\" w:sz=\"4\" w:space=\"0\"/>\n" +
                "                            <w:right w:val=\"single\" w:color=\"auto\" w:sz=\"4\" w:space=\"0\"/>\n" +
                "                            <w:insideH w:val=\"single\" w:color=\"auto\" w:sz=\"4\" w:space=\"0\"/>\n" +
                "                            <w:insideV w:val=\"single\" w:color=\"auto\" w:sz=\"4\" w:space=\"0\"/>\n" +
                "                        </w:tblBorders>\n" +
                "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"D9D9D9\" w:themeFill=\"background1\" w:themeFillShade=\"D9\"/>\n" +
                "                        <w:tblCellMar>\n" +
                "                            <w:left w:w=\"10\" w:type=\"dxa\"/>\n" +
                "                            <w:right w:w=\"10\" w:type=\"dxa\"/>\n" +
                "                        </w:tblCellMar>\n" +
                "                        <w:tblLook w:firstRow=\"1\" w:lastRow=\"0\" w:firstColumn=\"1\" w:lastColumn=\"0\" w:noHBand=\"0\" w:noVBand=\"1\" w:val=\"04A0\"/>\n" +
                "                    </w:tblPr>\n" +
                "                    <w:tblGrid>\n" +
                "                        <w:gridCol w:w=\"9144\"/>\n" +
                "                    </w:tblGrid>\n" +
                "                    <w:tr w:rsidTr=\"00C87F4E\">\n" +
                "                        <w:trPr>\n" +
                "                            <w:trHeight w:val=\"1\"/>\n" +
                "                        </w:trPr>\n" +
                "                        <w:tc>\n" +
                "                            <w:tcPr>\n" +
                "                                <w:tcW w:w=\"9144\" w:type=\"dxa\"/>\n" +
                "                                <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"D9D9D9\" w:themeFill=\"background1\" w:themeFillShade=\"D9\"/>\n" +
                "                                <w:tcMar>\n" +
                "                                    <w:left w:w=\"108\" w:type=\"dxa\"/>\n" +
                "                                    <w:right w:w=\"108\" w:type=\"dxa\"/>\n" +
                "                                </w:tcMar>\n" +
                "                            </w:tcPr>\n" +
                "                            <w:p>\n" +
                "                                <w:pPr>\n" +
                "                                    <w:spacing w:after=\"120\"/>\n" +
                "                                    <w:rPr>\n" +
                "                                        <w:rFonts w:eastAsia=\"Calibri\" w:cs=\"Calibri\" w:asciiTheme=\"minorHAnsi\" w:hAnsiTheme=\"minorHAnsi\"/>\n" +
                "                                        <w:sz w:val=\"20\"/>\n" +
                "                                        <w:szCs w:val=\"20\"/>\n" +
                "                                    </w:rPr>\n" +
                "                                </w:pPr>\n" +
                "                                <w:r>\n" +
                "                                    <w:rPr>\n" +
                "                                        <w:rFonts w:eastAsia=\"Calibri\" w:cs=\"Calibri\" w:asciiTheme=\"minorHAnsi\" w:hAnsiTheme=\"minorHAnsi\"/>\n" +
                "                                        <w:sz w:val=\"20\"/>\n" +
                "                                        <w:szCs w:val=\"20\"/>\n" +
                "                                    </w:rPr>\n" +
                "                                    <w:t xml:space=\"preserve\"> Colorectal Cancer: ${referralRecordInstance?.paternal?.first()?.colorectalCancer}</w:t>\n" +
                "                                </w:r>\n" +
                "                            </w:p>\n" +
                "                        </w:tc>\n" +
                "                        <w:tc>\n" +
                "                            <w:tcPr>\n" +
                "                                <w:tcW w:w=\"9144\" w:type=\"dxa\"/>\n" +
                "                                <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"D9D9D9\" w:themeFill=\"background1\" w:themeFillShade=\"D9\"/>\n" +
                "                                <w:tcMar>\n" +
                "                                    <w:left w:w=\"108\" w:type=\"dxa\"/>\n" +
                "                                    <w:right w:w=\"108\" w:type=\"dxa\"/>\n" +
                "                                </w:tcMar>\n" +
                "                            </w:tcPr>\n" +
                "                            <w:p>\n" +
                "                                <w:pPr>\n" +
                "                                    <w:spacing w:after=\"120\"/>\n" +
                "                                    <w:rPr>\n" +
                "                                        <w:rFonts w:eastAsia=\"Calibri\" w:cs=\"Calibri\" w:asciiTheme=\"minorHAnsi\" w:hAnsiTheme=\"minorHAnsi\"/>\n" +
                "                                        <w:sz w:val=\"20\"/>\n" +
                "                                        <w:szCs w:val=\"20\"/>\n" +
                "                                    </w:rPr>\n" +
                "                                </w:pPr>\n" +
                "                                <w:r>\n" +
                "                                    <w:rPr>\n" +
                "                                        <w:rFonts w:eastAsia=\"Calibri\" w:cs=\"Calibri\" w:asciiTheme=\"minorHAnsi\" w:hAnsiTheme=\"minorHAnsi\"/>\n" +
                "                                        <w:sz w:val=\"20\"/>\n" +
                "                                        <w:szCs w:val=\"20\"/>\n" +
                "                                    </w:rPr>\n" +
                "                                    <w:t xml:space=\"preserve\">Colorectal Cancer: ${referralRecordInstance?.maternal?.first()?.colorectalCancer}</w:t>\n" +
                "                                </w:r>\n" +
                "                            </w:p>\n" +
                "                        </w:tc>\n" +
                "                    </w:tr>\n" +
                "                </w:tbl>"

        mainPart.addObject(org.docx4j.XmlUtils.unmarshalString(colorectalCancer));

        def ischaemicHeartDiseaseOrStroke = "<w:tbl xmlns:w=\"http://schemas.openxmlformats.org/wordprocessingml/2006/main\" >\n" +
                "                    <w:tblPr>\n" +
                "                        <w:tblW w:w=\"0\" w:type=\"auto\"/>\n" +
                "                        <w:tblInd w:w=\"98\" w:type=\"dxa\"/>\n" +
                "                        <w:tblBorders>\n" +
                "                            <w:top w:val=\"single\" w:color=\"auto\" w:sz=\"4\" w:space=\"0\"/>\n" +
                "                            <w:left w:val=\"single\" w:color=\"auto\" w:sz=\"4\" w:space=\"0\"/>\n" +
                "                            <w:bottom w:val=\"single\" w:color=\"auto\" w:sz=\"4\" w:space=\"0\"/>\n" +
                "                            <w:right w:val=\"single\" w:color=\"auto\" w:sz=\"4\" w:space=\"0\"/>\n" +
                "                            <w:insideH w:val=\"single\" w:color=\"auto\" w:sz=\"4\" w:space=\"0\"/>\n" +
                "                            <w:insideV w:val=\"single\" w:color=\"auto\" w:sz=\"4\" w:space=\"0\"/>\n" +
                "                        </w:tblBorders>\n" +
                "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"D9D9D9\" w:themeFill=\"background1\" w:themeFillShade=\"D9\"/>\n" +
                "                        <w:tblCellMar>\n" +
                "                            <w:left w:w=\"10\" w:type=\"dxa\"/>\n" +
                "                            <w:right w:w=\"10\" w:type=\"dxa\"/>\n" +
                "                        </w:tblCellMar>\n" +
                "                        <w:tblLook w:firstRow=\"1\" w:lastRow=\"0\" w:firstColumn=\"1\" w:lastColumn=\"0\" w:noHBand=\"0\" w:noVBand=\"1\" w:val=\"04A0\"/>\n" +
                "                    </w:tblPr>\n" +
                "                    <w:tblGrid>\n" +
                "                        <w:gridCol w:w=\"9144\"/>\n" +
                "                    </w:tblGrid>\n" +
                "                    <w:tr w:rsidTr=\"00C87F4E\">\n" +
                "                        <w:trPr>\n" +
                "                            <w:trHeight w:val=\"1\"/>\n" +
                "                        </w:trPr>\n" +
                "                        <w:tc>\n" +
                "                            <w:tcPr>\n" +
                "                                <w:tcW w:w=\"9144\" w:type=\"dxa\"/>\n" +
                "                                <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"D9D9D9\" w:themeFill=\"background1\" w:themeFillShade=\"D9\"/>\n" +
                "                                <w:tcMar>\n" +
                "                                    <w:left w:w=\"108\" w:type=\"dxa\"/>\n" +
                "                                    <w:right w:w=\"108\" w:type=\"dxa\"/>\n" +
                "                                </w:tcMar>\n" +
                "                            </w:tcPr>\n" +
                "                            <w:p>\n" +
                "                                <w:pPr>\n" +
                "                                    <w:spacing w:after=\"120\"/>\n" +
                "                                    <w:rPr>\n" +
                "                                        <w:rFonts w:eastAsia=\"Calibri\" w:cs=\"Calibri\" w:asciiTheme=\"minorHAnsi\" w:hAnsiTheme=\"minorHAnsi\"/>\n" +
                "                                        <w:sz w:val=\"20\"/>\n" +
                "                                        <w:szCs w:val=\"20\"/>\n" +
                "                                    </w:rPr>\n" +
                "                                </w:pPr>\n" +
                "                                <w:r>\n" +
                "                                    <w:rPr>\n" +
                "                                        <w:rFonts w:eastAsia=\"Calibri\" w:cs=\"Calibri\" w:asciiTheme=\"minorHAnsi\" w:hAnsiTheme=\"minorHAnsi\"/>\n" +
                "                                        <w:sz w:val=\"20\"/>\n" +
                "                                        <w:szCs w:val=\"20\"/>\n" +
                "                                    </w:rPr>\n" +
                "                                    <w:t xml:space=\"preserve\"> Ischaemic Heart Disease Or Stroke: ${referralRecordInstance?.paternal?.first()?.ischaemicHeartDiseaseOrStroke}</w:t>\n" +
                "                                </w:r>\n" +
                "                            </w:p>\n" +
                "                        </w:tc>\n" +
                "                        <w:tc>\n" +
                "                            <w:tcPr>\n" +
                "                                <w:tcW w:w=\"9144\" w:type=\"dxa\"/>\n" +
                "                                <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"D9D9D9\" w:themeFill=\"background1\" w:themeFillShade=\"D9\"/>\n" +
                "                                <w:tcMar>\n" +
                "                                    <w:left w:w=\"108\" w:type=\"dxa\"/>\n" +
                "                                    <w:right w:w=\"108\" w:type=\"dxa\"/>\n" +
                "                                </w:tcMar>\n" +
                "                            </w:tcPr>\n" +
                "                            <w:p>\n" +
                "                                <w:pPr>\n" +
                "                                    <w:spacing w:after=\"120\"/>\n" +
                "                                    <w:rPr>\n" +
                "                                        <w:rFonts w:eastAsia=\"Calibri\" w:cs=\"Calibri\" w:asciiTheme=\"minorHAnsi\" w:hAnsiTheme=\"minorHAnsi\"/>\n" +
                "                                        <w:sz w:val=\"20\"/>\n" +
                "                                        <w:szCs w:val=\"20\"/>\n" +
                "                                    </w:rPr>\n" +
                "                                </w:pPr>\n" +
                "                                <w:r>\n" +
                "                                    <w:rPr>\n" +
                "                                        <w:rFonts w:eastAsia=\"Calibri\" w:cs=\"Calibri\" w:asciiTheme=\"minorHAnsi\" w:hAnsiTheme=\"minorHAnsi\"/>\n" +
                "                                        <w:sz w:val=\"20\"/>\n" +
                "                                        <w:szCs w:val=\"20\"/>\n" +
                "                                    </w:rPr>\n" +
                "                                    <w:t xml:space=\"preserve\">Ischaemic Heart Disease Or Stroke: ${referralRecordInstance?.maternal?.first()?.ischaemicHeartDiseaseOrStroke}</w:t>\n" +
                "                                </w:r>\n" +
                "                            </w:p>\n" +
                "                        </w:tc>\n" +
                "                    </w:tr>\n" +
                "                </w:tbl>"

        mainPart.addObject(org.docx4j.XmlUtils.unmarshalString(ischaemicHeartDiseaseOrStroke));

        def endocrineTumours = "<w:tbl xmlns:w=\"http://schemas.openxmlformats.org/wordprocessingml/2006/main\" >\n" +
                "                    <w:tblPr>\n" +
                "                        <w:tblW w:w=\"0\" w:type=\"auto\"/>\n" +
                "                        <w:tblInd w:w=\"98\" w:type=\"dxa\"/>\n" +
                "                        <w:tblBorders>\n" +
                "                            <w:top w:val=\"single\" w:color=\"auto\" w:sz=\"4\" w:space=\"0\"/>\n" +
                "                            <w:left w:val=\"single\" w:color=\"auto\" w:sz=\"4\" w:space=\"0\"/>\n" +
                "                            <w:bottom w:val=\"single\" w:color=\"auto\" w:sz=\"4\" w:space=\"0\"/>\n" +
                "                            <w:right w:val=\"single\" w:color=\"auto\" w:sz=\"4\" w:space=\"0\"/>\n" +
                "                            <w:insideH w:val=\"single\" w:color=\"auto\" w:sz=\"4\" w:space=\"0\"/>\n" +
                "                            <w:insideV w:val=\"single\" w:color=\"auto\" w:sz=\"4\" w:space=\"0\"/>\n" +
                "                        </w:tblBorders>\n" +
                "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"D9D9D9\" w:themeFill=\"background1\" w:themeFillShade=\"D9\"/>\n" +
                "                        <w:tblCellMar>\n" +
                "                            <w:left w:w=\"10\" w:type=\"dxa\"/>\n" +
                "                            <w:right w:w=\"10\" w:type=\"dxa\"/>\n" +
                "                        </w:tblCellMar>\n" +
                "                        <w:tblLook w:firstRow=\"1\" w:lastRow=\"0\" w:firstColumn=\"1\" w:lastColumn=\"0\" w:noHBand=\"0\" w:noVBand=\"1\" w:val=\"04A0\"/>\n" +
                "                    </w:tblPr>\n" +
                "                    <w:tblGrid>\n" +
                "                        <w:gridCol w:w=\"9144\"/>\n" +
                "                    </w:tblGrid>\n" +
                "                    <w:tr w:rsidTr=\"00C87F4E\">\n" +
                "                        <w:trPr>\n" +
                "                            <w:trHeight w:val=\"1\"/>\n" +
                "                        </w:trPr>\n" +
                "                        <w:tc>\n" +
                "                            <w:tcPr>\n" +
                "                                <w:tcW w:w=\"9144\" w:type=\"dxa\"/>\n" +
                "                                <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"D9D9D9\" w:themeFill=\"background1\" w:themeFillShade=\"D9\"/>\n" +
                "                                <w:tcMar>\n" +
                "                                    <w:left w:w=\"108\" w:type=\"dxa\"/>\n" +
                "                                    <w:right w:w=\"108\" w:type=\"dxa\"/>\n" +
                "                                </w:tcMar>\n" +
                "                            </w:tcPr>\n" +
                "                            <w:p>\n" +
                "                                <w:pPr>\n" +
                "                                    <w:spacing w:after=\"120\"/>\n" +
                "                                    <w:rPr>\n" +
                "                                        <w:rFonts w:eastAsia=\"Calibri\" w:cs=\"Calibri\" w:asciiTheme=\"minorHAnsi\" w:hAnsiTheme=\"minorHAnsi\"/>\n" +
                "                                        <w:sz w:val=\"20\"/>\n" +
                "                                        <w:szCs w:val=\"20\"/>\n" +
                "                                    </w:rPr>\n" +
                "                                </w:pPr>\n" +
                "                                <w:r>\n" +
                "                                    <w:rPr>\n" +
                "                                        <w:rFonts w:eastAsia=\"Calibri\" w:cs=\"Calibri\" w:asciiTheme=\"minorHAnsi\" w:hAnsiTheme=\"minorHAnsi\"/>\n" +
                "                                        <w:sz w:val=\"20\"/>\n" +
                "                                        <w:szCs w:val=\"20\"/>\n" +
                "                                    </w:rPr>\n" +
                "                                    <w:t xml:space=\"preserve\"> Endocrine Tumours: ${referralRecordInstance?.paternal?.first()?.endocrineTumours}</w:t>\n" +
                "                                </w:r>\n" +
                "                            </w:p>\n" +
                "                        </w:tc>\n" +
                "                        <w:tc>\n" +
                "                            <w:tcPr>\n" +
                "                                <w:tcW w:w=\"9144\" w:type=\"dxa\"/>\n" +
                "                                <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"D9D9D9\" w:themeFill=\"background1\" w:themeFillShade=\"D9\"/>\n" +
                "                                <w:tcMar>\n" +
                "                                    <w:left w:w=\"108\" w:type=\"dxa\"/>\n" +
                "                                    <w:right w:w=\"108\" w:type=\"dxa\"/>\n" +
                "                                </w:tcMar>\n" +
                "                            </w:tcPr>\n" +
                "                            <w:p>\n" +
                "                                <w:pPr>\n" +
                "                                    <w:spacing w:after=\"120\"/>\n" +
                "                                    <w:rPr>\n" +
                "                                        <w:rFonts w:eastAsia=\"Calibri\" w:cs=\"Calibri\" w:asciiTheme=\"minorHAnsi\" w:hAnsiTheme=\"minorHAnsi\"/>\n" +
                "                                        <w:sz w:val=\"20\"/>\n" +
                "                                        <w:szCs w:val=\"20\"/>\n" +
                "                                    </w:rPr>\n" +
                "                                </w:pPr>\n" +
                "                                <w:r>\n" +
                "                                    <w:rPr>\n" +
                "                                        <w:rFonts w:eastAsia=\"Calibri\" w:cs=\"Calibri\" w:asciiTheme=\"minorHAnsi\" w:hAnsiTheme=\"minorHAnsi\"/>\n" +
                "                                        <w:sz w:val=\"20\"/>\n" +
                "                                        <w:szCs w:val=\"20\"/>\n" +
                "                                    </w:rPr>\n" +
                "                                    <w:t xml:space=\"preserve\">Endocrine Tumours: ${referralRecordInstance?.maternal?.first()?.endocrineTumours}</w:t>\n" +
                "                                </w:r>\n" +
                "                            </w:p>\n" +
                "                        </w:tc>\n" +
                "                    </w:tr>\n" +
                "                </w:tbl>"

        mainPart.addObject(org.docx4j.XmlUtils.unmarshalString(endocrineTumours));

        def familyHistoryNote = "<w:p xmlns:w=\"http://schemas.openxmlformats.org/wordprocessingml/2006/main\" >\n" +
                "                                <w:pPr>\n" +
                "                                    <w:spacing w:after=\"120\"/>\n" +
                "                                    <w:rPr>\n" +
                "                                        <w:rFonts w:eastAsia=\"Calibri\" w:cs=\"Calibri\" w:asciiTheme=\"minorHAnsi\" w:hAnsiTheme=\"minorHAnsi\"/>\n" +
                "                                        <w:sz w:val=\"20\"/>\n" +
                "                                        <w:szCs w:val=\"20\"/>\n" +
                "                                    </w:rPr>\n" +
                "                                </w:pPr>\n" +
                "                                <w:r>\n" +
                "                                    <w:rPr>\n" +
                "                                        <w:rFonts w:eastAsia=\"Calibri\" w:cs=\"Calibri\" w:asciiTheme=\"minorHAnsi\" w:hAnsiTheme=\"minorHAnsi\"/>\n" +
                "                                        <w:sz w:val=\"20\"/>\n" +
                "                                        <w:szCs w:val=\"20\"/>\n" +
                "                                    </w:rPr>\n" +
                "                                    <w:t xml:space=\"preserve\">Details and/or note any other significant family history</w:t>\n" +
                "                                </w:r>\n" +
                "                            </w:p>"

        def familyHistoryNoteDetails = "<w:tbl xmlns:w=\"http://schemas.openxmlformats.org/wordprocessingml/2006/main\" >\n" +
                "                    <w:tblPr>\n" +
                "                        <w:tblW w:w=\"0\" w:type=\"auto\"/>\n" +
                "                        <w:tblInd w:w=\"98\" w:type=\"dxa\"/>\n" +
                "                        <w:tblBorders>\n" +
                "                            <w:top w:val=\"single\" w:color=\"auto\" w:sz=\"4\" w:space=\"0\"/>\n" +
                "                            <w:left w:val=\"single\" w:color=\"auto\" w:sz=\"4\" w:space=\"0\"/>\n" +
                "                            <w:bottom w:val=\"single\" w:color=\"auto\" w:sz=\"4\" w:space=\"0\"/>\n" +
                "                            <w:right w:val=\"single\" w:color=\"auto\" w:sz=\"4\" w:space=\"0\"/>\n" +
                "                            <w:insideH w:val=\"single\" w:color=\"auto\" w:sz=\"4\" w:space=\"0\"/>\n" +
                "                            <w:insideV w:val=\"single\" w:color=\"auto\" w:sz=\"4\" w:space=\"0\"/>\n" +
                "                        </w:tblBorders>\n" +
                "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"D9D9D9\" w:themeFill=\"background1\" w:themeFillShade=\"D9\"/>\n" +
                "                        <w:tblCellMar>\n" +
                "                            <w:left w:w=\"10\" w:type=\"dxa\"/>\n" +
                "                            <w:right w:w=\"10\" w:type=\"dxa\"/>\n" +
                "                        </w:tblCellMar>\n" +
                "                        <w:tblLook w:firstRow=\"1\" w:lastRow=\"0\" w:firstColumn=\"1\" w:lastColumn=\"0\" w:noHBand=\"0\" w:noVBand=\"1\" w:val=\"04A0\"/>\n" +
                "                    </w:tblPr>\n" +
                "                    <w:tblGrid>\n" +
                "                        <w:gridCol w:w=\"9144\"/>\n" +
                "                    </w:tblGrid>\n" +
                "                    <w:tr w:rsidTr=\"00C87F4E\">\n" +
                "                        <w:trPr>\n" +
                "                            <w:trHeight w:val=\"1\"/>\n" +
                "                        </w:trPr>\n" +
                "                        <w:tc>\n" +
                "                            <w:tcPr>\n" +
                "                                <w:tcW w:w=\"9144\" w:type=\"dxa\"/>\n" +
                "                                <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"D9D9D9\" w:themeFill=\"background1\" w:themeFillShade=\"D9\"/>\n" +
                "                                <w:tcMar>\n" +
                "                                    <w:left w:w=\"108\" w:type=\"dxa\"/>\n" +
                "                                    <w:right w:w=\"108\" w:type=\"dxa\"/>\n" +
                "                                </w:tcMar>\n" +
                "                            </w:tcPr>\n" +
                "                            <w:p>\n" +
                "                                <w:pPr>\n" +
                "                                    <w:spacing w:after=\"120\"/>\n" +
                "                                    <w:rPr>\n" +
                "                                        <w:rFonts w:eastAsia=\"Calibri\" w:cs=\"Calibri\" w:asciiTheme=\"minorHAnsi\" w:hAnsiTheme=\"minorHAnsi\"/>\n" +
                "                                        <w:sz w:val=\"20\"/>\n" +
                "                                        <w:szCs w:val=\"20\"/>\n" +
                "                                    </w:rPr>\n" +
                "                                </w:pPr>\n" +
                "                                <w:r>\n" +
                "                                    <w:rPr>\n" +
                "                                        <w:rFonts w:eastAsia=\"Calibri\" w:cs=\"Calibri\" w:asciiTheme=\"minorHAnsi\" w:hAnsiTheme=\"minorHAnsi\"/>\n" +
                "                                        <w:sz w:val=\"20\"/>\n" +
                "                                        <w:szCs w:val=\"20\"/>\n" +
                "                                    </w:rPr>\n" +
                "                                    <w:t xml:space=\"preserve\"> ${referralRecordInstance?.furtherDetailsOfHistory ?: ''}</w:t>\n" +
                "                                </w:r>\n" +
                "                            </w:p>\n" +
                "                        </w:tc>\n" +
                "                    </w:tr>\n" +
                "                </w:tbl>"

        mainPart.addObject(org.docx4j.XmlUtils.unmarshalString(familyHistoryNote));
        mainPart.addObject(org.docx4j.XmlUtils.unmarshalString(familyHistoryNoteDetails));

        def samplesName = "<w:p xmlns:w=\"http://schemas.openxmlformats.org/wordprocessingml/2006/main\" >\n" +
                "                                <w:pPr>\n" +
                "                                    <w:spacing w:after=\"120\"/>\n" +
                "                                    <w:rPr>\n" +
                "                                        <w:rFonts w:eastAsia=\"Calibri\" w:cs=\"Calibri\" w:asciiTheme=\"minorHAnsi\" w:hAnsiTheme=\"minorHAnsi\"/>\n" +
                "                                        <w:sz w:val=\"20\"/>\n" +
                "                                        <w:szCs w:val=\"20\"/>\n" +
                "                                    </w:rPr>\n" +
                "                                </w:pPr>\n" +
                "                                <w:r>\n" +
                "                                    <w:rPr>\n" +
                "                                        <w:rFonts w:eastAsia=\"Calibri\" w:cs=\"Calibri\" w:asciiTheme=\"minorHAnsi\" w:hAnsiTheme=\"minorHAnsi\"/>\n" +
                "                                        <w:sz w:val=\"20\"/>\n" +
                "                                        <w:szCs w:val=\"20\"/>\n" +
                "                                    </w:rPr>\n" +
                "                                    <w:t xml:space=\"preserve\">Number and identity of family members proposed for sequencing</w:t>\n" +
                "                                </w:r>\n" +
                "                            </w:p>"

        mainPart.addObject(org.docx4j.XmlUtils.unmarshalString(samplesName));
        def samplesNameDetails = "<w:tbl xmlns:w=\"http://schemas.openxmlformats.org/wordprocessingml/2006/main\" >\n" +
                "                    <w:tblPr>\n" +
                "                        <w:tblW w:w=\"0\" w:type=\"auto\"/>\n" +
                "                        <w:tblInd w:w=\"98\" w:type=\"dxa\"/>\n" +
                "                        <w:tblBorders>\n" +
                "                            <w:top w:val=\"single\" w:color=\"auto\" w:sz=\"4\" w:space=\"0\"/>\n" +
                "                            <w:left w:val=\"single\" w:color=\"auto\" w:sz=\"4\" w:space=\"0\"/>\n" +
                "                            <w:bottom w:val=\"single\" w:color=\"auto\" w:sz=\"4\" w:space=\"0\"/>\n" +
                "                            <w:right w:val=\"single\" w:color=\"auto\" w:sz=\"4\" w:space=\"0\"/>\n" +
                "                            <w:insideH w:val=\"single\" w:color=\"auto\" w:sz=\"4\" w:space=\"0\"/>\n" +
                "                            <w:insideV w:val=\"single\" w:color=\"auto\" w:sz=\"4\" w:space=\"0\"/>\n" +
                "                        </w:tblBorders>\n" +
                "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"D9D9D9\" w:themeFill=\"background1\" w:themeFillShade=\"D9\"/>\n" +
                "                        <w:tblCellMar>\n" +
                "                            <w:left w:w=\"10\" w:type=\"dxa\"/>\n" +
                "                            <w:right w:w=\"10\" w:type=\"dxa\"/>\n" +
                "                        </w:tblCellMar>\n" +
                "                        <w:tblLook w:firstRow=\"1\" w:lastRow=\"0\" w:firstColumn=\"1\" w:lastColumn=\"0\" w:noHBand=\"0\" w:noVBand=\"1\" w:val=\"04A0\"/>\n" +
                "                    </w:tblPr>\n" +
                "                    <w:tblGrid>\n" +
                "                        <w:gridCol w:w=\"9144\"/>\n" +
                "                    </w:tblGrid>\n" +
                "                    <w:tr w:rsidTr=\"00C87F4E\">\n" +
                "                        <w:trPr>\n" +
                "                            <w:trHeight w:val=\"1\"/>\n" +
                "                        </w:trPr>\n" +
                "                        <w:tc>\n" +
                "                            <w:tcPr>\n" +
                "                                <w:tcW w:w=\"9144\" w:type=\"dxa\"/>\n" +
                "                                <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"D9D9D9\" w:themeFill=\"background1\" w:themeFillShade=\"D9\"/>\n" +
                "                                <w:tcMar>\n" +
                "                                    <w:left w:w=\"108\" w:type=\"dxa\"/>\n" +
                "                                    <w:right w:w=\"108\" w:type=\"dxa\"/>\n" +
                "                                </w:tcMar>\n" +
                "                            </w:tcPr>\n" +
                "                            <w:p>\n" +
                "                                <w:pPr>\n" +
                "                                    <w:spacing w:after=\"120\"/>\n" +
                "                                    <w:rPr>\n" +
                "                                        <w:rFonts w:eastAsia=\"Calibri\" w:cs=\"Calibri\" w:asciiTheme=\"minorHAnsi\" w:hAnsiTheme=\"minorHAnsi\"/>\n" +
                "                                        <w:sz w:val=\"20\"/>\n" +
                "                                        <w:szCs w:val=\"20\"/>\n" +
                "                                    </w:rPr>\n" +
                "                                </w:pPr>\n" +
                "                                <w:r>\n" +
                "                                    <w:rPr>\n" +
                "                                        <w:rFonts w:eastAsia=\"Calibri\" w:cs=\"Calibri\" w:asciiTheme=\"minorHAnsi\" w:hAnsiTheme=\"minorHAnsi\"/>\n" +
                "                                        <w:sz w:val=\"20\"/>\n" +
                "                                        <w:szCs w:val=\"20\"/>\n" +
                "                                    </w:rPr>\n" +
                "                                    <w:t xml:space=\"preserve\"> Number of samples: ${referralRecordInstance?.numberOfSamplesForSeq ?: ''}</w:t>\n" +
                "                                </w:r>\n" +
                "                            </w:p>\n" +
                "                        </w:tc>\n" +
                "                        <w:tc>\n" +
                "                            <w:tcPr>\n" +
                "                                <w:tcW w:w=\"9144\" w:type=\"dxa\"/>\n" +
                "                                <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"D9D9D9\" w:themeFill=\"background1\" w:themeFillShade=\"D9\"/>\n" +
                "                                <w:tcMar>\n" +
                "                                    <w:left w:w=\"108\" w:type=\"dxa\"/>\n" +
                "                                    <w:right w:w=\"108\" w:type=\"dxa\"/>\n" +
                "                                </w:tcMar>\n" +
                "                            </w:tcPr>\n" +
                "                            <w:p>\n" +
                "                                <w:pPr>\n" +
                "                                    <w:spacing w:after=\"120\"/>\n" +
                "                                    <w:rPr>\n" +
                "                                        <w:rFonts w:eastAsia=\"Calibri\" w:cs=\"Calibri\" w:asciiTheme=\"minorHAnsi\" w:hAnsiTheme=\"minorHAnsi\"/>\n" +
                "                                        <w:sz w:val=\"20\"/>\n" +
                "                                        <w:szCs w:val=\"20\"/>\n" +
                "                                    </w:rPr>\n" +
                "                                </w:pPr>\n" +
                "                                <w:r>\n" +
                "                                    <w:rPr>\n" +
                "                                        <w:rFonts w:eastAsia=\"Calibri\" w:cs=\"Calibri\" w:asciiTheme=\"minorHAnsi\" w:hAnsiTheme=\"minorHAnsi\"/>\n" +
                "                                        <w:sz w:val=\"20\"/>\n" +
                "                                        <w:szCs w:val=\"20\"/>\n" +
                "                                    </w:rPr>\n" +
                "                                    <w:t xml:space=\"preserve\"> Identity of family members: ${referralRecordInstance?.identityOfFamilyMembersSamplesForSeq ?: ''}</w:t>\n" +
                "                                </w:r>\n" +
                "                            </w:p>\n" +
                "                        </w:tc>\n" +
                "                    </w:tr>\n" +
                "                </w:tbl>"

        mainPart.addObject(org.docx4j.XmlUtils.unmarshalString(samplesNameDetails));

        def isAnySampleFromDeceasedIndividuals = "<w:p xmlns:w=\"http://schemas.openxmlformats.org/wordprocessingml/2006/main\" >\n" +
                "                                <w:pPr>\n" +
                "                                    <w:spacing w:after=\"120\"/>\n" +
                "                                    <w:rPr>\n" +
                "                                        <w:rFonts w:eastAsia=\"Calibri\" w:cs=\"Calibri\" w:asciiTheme=\"minorHAnsi\" w:hAnsiTheme=\"minorHAnsi\"/>\n" +
                "                                        <w:sz w:val=\"20\"/>\n" +
                "                                        <w:szCs w:val=\"20\"/>\n" +
                "                                    </w:rPr>\n" +
                "                                </w:pPr>\n" +
                "                                <w:r>\n" +
                "                                    <w:rPr>\n" +
                "                                        <w:rFonts w:eastAsia=\"Calibri\" w:cs=\"Calibri\" w:asciiTheme=\"minorHAnsi\" w:hAnsiTheme=\"minorHAnsi\"/>\n" +
                "                                        <w:sz w:val=\"20\"/>\n" +
                "                                        <w:szCs w:val=\"20\"/>\n" +
                "                                    </w:rPr>\n" +
                "                                    <w:t xml:space=\"preserve\">Are any of the samples are taken from deceased individuals?</w:t>\n" +
                "                                </w:r>\n" +
                "                            </w:p>"
        mainPart.addObject(org.docx4j.XmlUtils.unmarshalString(isAnySampleFromDeceasedIndividuals));
        if (referralRecordInstance?.isAnySampleFromDeceasedIndividuals){
            def isAnySampleFromDeceasedIndividualsDetails = "<w:tbl xmlns:w=\"http://schemas.openxmlformats.org/wordprocessingml/2006/main\" >\n" +
                    "                    <w:tblPr>\n" +
                    "                        <w:tblW w:w=\"0\" w:type=\"auto\"/>\n" +
                    "                        <w:tblInd w:w=\"98\" w:type=\"dxa\"/>\n" +
                    "                        <w:tblBorders>\n" +
                    "                            <w:top w:val=\"single\" w:color=\"auto\" w:sz=\"4\" w:space=\"0\"/>\n" +
                    "                            <w:left w:val=\"single\" w:color=\"auto\" w:sz=\"4\" w:space=\"0\"/>\n" +
                    "                            <w:bottom w:val=\"single\" w:color=\"auto\" w:sz=\"4\" w:space=\"0\"/>\n" +
                    "                            <w:right w:val=\"single\" w:color=\"auto\" w:sz=\"4\" w:space=\"0\"/>\n" +
                    "                            <w:insideH w:val=\"single\" w:color=\"auto\" w:sz=\"4\" w:space=\"0\"/>\n" +
                    "                            <w:insideV w:val=\"single\" w:color=\"auto\" w:sz=\"4\" w:space=\"0\"/>\n" +
                    "                        </w:tblBorders>\n" +
                    "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"D9D9D9\" w:themeFill=\"background1\" w:themeFillShade=\"D9\"/>\n" +
                    "                        <w:tblCellMar>\n" +
                    "                            <w:left w:w=\"10\" w:type=\"dxa\"/>\n" +
                    "                            <w:right w:w=\"10\" w:type=\"dxa\"/>\n" +
                    "                        </w:tblCellMar>\n" +
                    "                        <w:tblLook w:firstRow=\"1\" w:lastRow=\"0\" w:firstColumn=\"1\" w:lastColumn=\"0\" w:noHBand=\"0\" w:noVBand=\"1\" w:val=\"04A0\"/>\n" +
                    "                    </w:tblPr>\n" +
                    "                    <w:tblGrid>\n" +
                    "                        <w:gridCol w:w=\"9144\"/>\n" +
                    "                    </w:tblGrid>\n" +
                    "                    <w:tr w:rsidTr=\"00C87F4E\">\n" +
                    "                        <w:trPr>\n" +
                    "                            <w:trHeight w:val=\"1\"/>\n" +
                    "                        </w:trPr>\n" +
                    "                        <w:tc>\n" +
                    "                            <w:tcPr>\n" +
                    "                                <w:tcW w:w=\"9144\" w:type=\"dxa\"/>\n" +
                    "                                <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"D9D9D9\" w:themeFill=\"background1\" w:themeFillShade=\"D9\"/>\n" +
                    "                                <w:tcMar>\n" +
                    "                                    <w:left w:w=\"108\" w:type=\"dxa\"/>\n" +
                    "                                    <w:right w:w=\"108\" w:type=\"dxa\"/>\n" +
                    "                                </w:tcMar>\n" +
                    "                            </w:tcPr>\n" +
                    "                            <w:p>\n" +
                    "                                <w:pPr>\n" +
                    "                                    <w:spacing w:after=\"120\"/>\n" +
                    "                                    <w:rPr>\n" +
                    "                                        <w:rFonts w:eastAsia=\"Calibri\" w:cs=\"Calibri\" w:asciiTheme=\"minorHAnsi\" w:hAnsiTheme=\"minorHAnsi\"/>\n" +
                    "                                        <w:sz w:val=\"20\"/>\n" +
                    "                                        <w:szCs w:val=\"20\"/>\n" +
                    "                                    </w:rPr>\n" +
                    "                                </w:pPr>\n" +
                    "                                <w:r>\n" +
                    "                                    <w:rPr>\n" +
                    "                                        <w:rFonts w:eastAsia=\"Calibri\" w:cs=\"Calibri\" w:asciiTheme=\"minorHAnsi\" w:hAnsiTheme=\"minorHAnsi\"/>\n" +
                    "                                        <w:sz w:val=\"20\"/>\n" +
                    "                                        <w:szCs w:val=\"20\"/>\n" +
                    "                                    </w:rPr>\n" +
                    "                                    <w:t xml:space=\"preserve\"> Yes, ${referralRecordInstance?.isAnySampleFromDeceasedIndividualsDetails ?: ''}</w:t>\n" +
                    "                                </w:r>\n" +
                    "                            </w:p>\n" +
                    "                        </w:tc>\n" +
                    "                    </w:tr>\n" +
                    "                </w:tbl>"
            mainPart.addObject(org.docx4j.XmlUtils.unmarshalString(isAnySampleFromDeceasedIndividualsDetails));
        }else {
            def isAnySampleFromDeceasedIndividualsDetails = "<w:tbl xmlns:w=\"http://schemas.openxmlformats.org/wordprocessingml/2006/main\" >\n" +
                    "                    <w:tblPr>\n" +
                    "                        <w:tblW w:w=\"0\" w:type=\"auto\"/>\n" +
                    "                        <w:tblInd w:w=\"98\" w:type=\"dxa\"/>\n" +
                    "                        <w:tblBorders>\n" +
                    "                            <w:top w:val=\"single\" w:color=\"auto\" w:sz=\"4\" w:space=\"0\"/>\n" +
                    "                            <w:left w:val=\"single\" w:color=\"auto\" w:sz=\"4\" w:space=\"0\"/>\n" +
                    "                            <w:bottom w:val=\"single\" w:color=\"auto\" w:sz=\"4\" w:space=\"0\"/>\n" +
                    "                            <w:right w:val=\"single\" w:color=\"auto\" w:sz=\"4\" w:space=\"0\"/>\n" +
                    "                            <w:insideH w:val=\"single\" w:color=\"auto\" w:sz=\"4\" w:space=\"0\"/>\n" +
                    "                            <w:insideV w:val=\"single\" w:color=\"auto\" w:sz=\"4\" w:space=\"0\"/>\n" +
                    "                        </w:tblBorders>\n" +
                    "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"D9D9D9\" w:themeFill=\"background1\" w:themeFillShade=\"D9\"/>\n" +
                    "                        <w:tblCellMar>\n" +
                    "                            <w:left w:w=\"10\" w:type=\"dxa\"/>\n" +
                    "                            <w:right w:w=\"10\" w:type=\"dxa\"/>\n" +
                    "                        </w:tblCellMar>\n" +
                    "                        <w:tblLook w:firstRow=\"1\" w:lastRow=\"0\" w:firstColumn=\"1\" w:lastColumn=\"0\" w:noHBand=\"0\" w:noVBand=\"1\" w:val=\"04A0\"/>\n" +
                    "                    </w:tblPr>\n" +
                    "                    <w:tblGrid>\n" +
                    "                        <w:gridCol w:w=\"9144\"/>\n" +
                    "                    </w:tblGrid>\n" +
                    "                    <w:tr w:rsidTr=\"00C87F4E\">\n" +
                    "                        <w:trPr>\n" +
                    "                            <w:trHeight w:val=\"1\"/>\n" +
                    "                        </w:trPr>\n" +
                    "                        <w:tc>\n" +
                    "                            <w:tcPr>\n" +
                    "                                <w:tcW w:w=\"9144\" w:type=\"dxa\"/>\n" +
                    "                                <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"D9D9D9\" w:themeFill=\"background1\" w:themeFillShade=\"D9\"/>\n" +
                    "                                <w:tcMar>\n" +
                    "                                    <w:left w:w=\"108\" w:type=\"dxa\"/>\n" +
                    "                                    <w:right w:w=\"108\" w:type=\"dxa\"/>\n" +
                    "                                </w:tcMar>\n" +
                    "                            </w:tcPr>\n" +
                    "                            <w:p>\n" +
                    "                                <w:pPr>\n" +
                    "                                    <w:spacing w:after=\"120\"/>\n" +
                    "                                    <w:rPr>\n" +
                    "                                        <w:rFonts w:eastAsia=\"Calibri\" w:cs=\"Calibri\" w:asciiTheme=\"minorHAnsi\" w:hAnsiTheme=\"minorHAnsi\"/>\n" +
                    "                                        <w:sz w:val=\"20\"/>\n" +
                    "                                        <w:szCs w:val=\"20\"/>\n" +
                    "                                    </w:rPr>\n" +
                    "                                </w:pPr>\n" +
                    "                                <w:r>\n" +
                    "                                    <w:rPr>\n" +
                    "                                        <w:rFonts w:eastAsia=\"Calibri\" w:cs=\"Calibri\" w:asciiTheme=\"minorHAnsi\" w:hAnsiTheme=\"minorHAnsi\"/>\n" +
                    "                                        <w:sz w:val=\"20\"/>\n" +
                    "                                        <w:szCs w:val=\"20\"/>\n" +
                    "                                    </w:rPr>\n" +
                    "                                    <w:t xml:space=\"preserve\"> No </w:t>\n" +
                    "                                </w:r>\n" +
                    "                            </w:p>\n" +
                    "                        </w:tc>\n" +
                    "                    </w:tr>\n" +
                    "                </w:tbl>"
            mainPart.addObject(org.docx4j.XmlUtils.unmarshalString(isAnySampleFromDeceasedIndividualsDetails));
        }

        def anyIndividualsForSeqOutOfArea = "<w:p xmlns:w=\"http://schemas.openxmlformats.org/wordprocessingml/2006/main\" >\n" +
                "                                <w:pPr>\n" +
                "                                    <w:spacing w:after=\"120\"/>\n" +
                "                                    <w:rPr>\n" +
                "                                        <w:rFonts w:eastAsia=\"Calibri\" w:cs=\"Calibri\" w:asciiTheme=\"minorHAnsi\" w:hAnsiTheme=\"minorHAnsi\"/>\n" +
                "                                        <w:sz w:val=\"20\"/>\n" +
                "                                        <w:szCs w:val=\"20\"/>\n" +
                "                                    </w:rPr>\n" +
                "                                </w:pPr>\n" +
                "                                <w:r>\n" +
                "                                    <w:rPr>\n" +
                "                                        <w:rFonts w:eastAsia=\"Calibri\" w:cs=\"Calibri\" w:asciiTheme=\"minorHAnsi\" w:hAnsiTheme=\"minorHAnsi\"/>\n" +
                "                                        <w:sz w:val=\"20\"/>\n" +
                "                                        <w:szCs w:val=\"20\"/>\n" +
                "                                    </w:rPr>\n" +
                "                                    <w:t xml:space=\"preserve\">Do any of the individuals proposed for sequencing live outside the catchment area of the Oxford Genomic Medicine Centre?</w:t>\n" +
                "                                </w:r>\n" +
                "                            </w:p>"
        mainPart.addObject(org.docx4j.XmlUtils.unmarshalString(anyIndividualsForSeqOutOfArea));
        if (referralRecordInstance?.anyIndividualsForSeqOutOfArea){
            def anyIndividualsForSeqOutOfAreaDetails = "<w:tbl xmlns:w=\"http://schemas.openxmlformats.org/wordprocessingml/2006/main\" >\n" +
                    "                    <w:tblPr>\n" +
                    "                        <w:tblW w:w=\"0\" w:type=\"auto\"/>\n" +
                    "                        <w:tblInd w:w=\"98\" w:type=\"dxa\"/>\n" +
                    "                        <w:tblBorders>\n" +
                    "                            <w:top w:val=\"single\" w:color=\"auto\" w:sz=\"4\" w:space=\"0\"/>\n" +
                    "                            <w:left w:val=\"single\" w:color=\"auto\" w:sz=\"4\" w:space=\"0\"/>\n" +
                    "                            <w:bottom w:val=\"single\" w:color=\"auto\" w:sz=\"4\" w:space=\"0\"/>\n" +
                    "                            <w:right w:val=\"single\" w:color=\"auto\" w:sz=\"4\" w:space=\"0\"/>\n" +
                    "                            <w:insideH w:val=\"single\" w:color=\"auto\" w:sz=\"4\" w:space=\"0\"/>\n" +
                    "                            <w:insideV w:val=\"single\" w:color=\"auto\" w:sz=\"4\" w:space=\"0\"/>\n" +
                    "                        </w:tblBorders>\n" +
                    "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"D9D9D9\" w:themeFill=\"background1\" w:themeFillShade=\"D9\"/>\n" +
                    "                        <w:tblCellMar>\n" +
                    "                            <w:left w:w=\"10\" w:type=\"dxa\"/>\n" +
                    "                            <w:right w:w=\"10\" w:type=\"dxa\"/>\n" +
                    "                        </w:tblCellMar>\n" +
                    "                        <w:tblLook w:firstRow=\"1\" w:lastRow=\"0\" w:firstColumn=\"1\" w:lastColumn=\"0\" w:noHBand=\"0\" w:noVBand=\"1\" w:val=\"04A0\"/>\n" +
                    "                    </w:tblPr>\n" +
                    "                    <w:tblGrid>\n" +
                    "                        <w:gridCol w:w=\"9144\"/>\n" +
                    "                    </w:tblGrid>\n" +
                    "                    <w:tr w:rsidTr=\"00C87F4E\">\n" +
                    "                        <w:trPr>\n" +
                    "                            <w:trHeight w:val=\"1\"/>\n" +
                    "                        </w:trPr>\n" +
                    "                        <w:tc>\n" +
                    "                            <w:tcPr>\n" +
                    "                                <w:tcW w:w=\"9144\" w:type=\"dxa\"/>\n" +
                    "                                <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"D9D9D9\" w:themeFill=\"background1\" w:themeFillShade=\"D9\"/>\n" +
                    "                                <w:tcMar>\n" +
                    "                                    <w:left w:w=\"108\" w:type=\"dxa\"/>\n" +
                    "                                    <w:right w:w=\"108\" w:type=\"dxa\"/>\n" +
                    "                                </w:tcMar>\n" +
                    "                            </w:tcPr>\n" +
                    "                            <w:p>\n" +
                    "                                <w:pPr>\n" +
                    "                                    <w:spacing w:after=\"120\"/>\n" +
                    "                                    <w:rPr>\n" +
                    "                                        <w:rFonts w:eastAsia=\"Calibri\" w:cs=\"Calibri\" w:asciiTheme=\"minorHAnsi\" w:hAnsiTheme=\"minorHAnsi\"/>\n" +
                    "                                        <w:sz w:val=\"20\"/>\n" +
                    "                                        <w:szCs w:val=\"20\"/>\n" +
                    "                                    </w:rPr>\n" +
                    "                                </w:pPr>\n" +
                    "                                <w:r>\n" +
                    "                                    <w:rPr>\n" +
                    "                                        <w:rFonts w:eastAsia=\"Calibri\" w:cs=\"Calibri\" w:asciiTheme=\"minorHAnsi\" w:hAnsiTheme=\"minorHAnsi\"/>\n" +
                    "                                        <w:sz w:val=\"20\"/>\n" +
                    "                                        <w:szCs w:val=\"20\"/>\n" +
                    "                                    </w:rPr>\n" +
                    "                                    <w:t xml:space=\"preserve\"> Yes, ${referralRecordInstance?.anyIndividualsForSeqOutOfAreaDetails ?: ''}</w:t>\n" +
                    "                                </w:r>\n" +
                    "                            </w:p>\n" +
                    "                        </w:tc>\n" +
                    "                    </w:tr>\n" +
                    "                </w:tbl>"
            mainPart.addObject(org.docx4j.XmlUtils.unmarshalString(anyIndividualsForSeqOutOfAreaDetails));
        }else {
            def anyIndividualsForSeqOutOfAreaDetails = "<w:tbl xmlns:w=\"http://schemas.openxmlformats.org/wordprocessingml/2006/main\" >\n" +
                    "                    <w:tblPr>\n" +
                    "                        <w:tblW w:w=\"0\" w:type=\"auto\"/>\n" +
                    "                        <w:tblInd w:w=\"98\" w:type=\"dxa\"/>\n" +
                    "                        <w:tblBorders>\n" +
                    "                            <w:top w:val=\"single\" w:color=\"auto\" w:sz=\"4\" w:space=\"0\"/>\n" +
                    "                            <w:left w:val=\"single\" w:color=\"auto\" w:sz=\"4\" w:space=\"0\"/>\n" +
                    "                            <w:bottom w:val=\"single\" w:color=\"auto\" w:sz=\"4\" w:space=\"0\"/>\n" +
                    "                            <w:right w:val=\"single\" w:color=\"auto\" w:sz=\"4\" w:space=\"0\"/>\n" +
                    "                            <w:insideH w:val=\"single\" w:color=\"auto\" w:sz=\"4\" w:space=\"0\"/>\n" +
                    "                            <w:insideV w:val=\"single\" w:color=\"auto\" w:sz=\"4\" w:space=\"0\"/>\n" +
                    "                        </w:tblBorders>\n" +
                    "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"D9D9D9\" w:themeFill=\"background1\" w:themeFillShade=\"D9\"/>\n" +
                    "                        <w:tblCellMar>\n" +
                    "                            <w:left w:w=\"10\" w:type=\"dxa\"/>\n" +
                    "                            <w:right w:w=\"10\" w:type=\"dxa\"/>\n" +
                    "                        </w:tblCellMar>\n" +
                    "                        <w:tblLook w:firstRow=\"1\" w:lastRow=\"0\" w:firstColumn=\"1\" w:lastColumn=\"0\" w:noHBand=\"0\" w:noVBand=\"1\" w:val=\"04A0\"/>\n" +
                    "                    </w:tblPr>\n" +
                    "                    <w:tblGrid>\n" +
                    "                        <w:gridCol w:w=\"9144\"/>\n" +
                    "                    </w:tblGrid>\n" +
                    "                    <w:tr w:rsidTr=\"00C87F4E\">\n" +
                    "                        <w:trPr>\n" +
                    "                            <w:trHeight w:val=\"1\"/>\n" +
                    "                        </w:trPr>\n" +
                    "                        <w:tc>\n" +
                    "                            <w:tcPr>\n" +
                    "                                <w:tcW w:w=\"9144\" w:type=\"dxa\"/>\n" +
                    "                                <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"D9D9D9\" w:themeFill=\"background1\" w:themeFillShade=\"D9\"/>\n" +
                    "                                <w:tcMar>\n" +
                    "                                    <w:left w:w=\"108\" w:type=\"dxa\"/>\n" +
                    "                                    <w:right w:w=\"108\" w:type=\"dxa\"/>\n" +
                    "                                </w:tcMar>\n" +
                    "                            </w:tcPr>\n" +
                    "                            <w:p>\n" +
                    "                                <w:pPr>\n" +
                    "                                    <w:spacing w:after=\"120\"/>\n" +
                    "                                    <w:rPr>\n" +
                    "                                        <w:rFonts w:eastAsia=\"Calibri\" w:cs=\"Calibri\" w:asciiTheme=\"minorHAnsi\" w:hAnsiTheme=\"minorHAnsi\"/>\n" +
                    "                                        <w:sz w:val=\"20\"/>\n" +
                    "                                        <w:szCs w:val=\"20\"/>\n" +
                    "                                    </w:rPr>\n" +
                    "                                </w:pPr>\n" +
                    "                                <w:r>\n" +
                    "                                    <w:rPr>\n" +
                    "                                        <w:rFonts w:eastAsia=\"Calibri\" w:cs=\"Calibri\" w:asciiTheme=\"minorHAnsi\" w:hAnsiTheme=\"minorHAnsi\"/>\n" +
                    "                                        <w:sz w:val=\"20\"/>\n" +
                    "                                        <w:szCs w:val=\"20\"/>\n" +
                    "                                    </w:rPr>\n" +
                    "                                    <w:t xml:space=\"preserve\"> No </w:t>\n" +
                    "                                </w:r>\n" +
                    "                            </w:p>\n" +
                    "                        </w:tc>\n" +
                    "                    </w:tr>\n" +
                    "                </w:tbl>"
            mainPart.addObject(org.docx4j.XmlUtils.unmarshalString(anyIndividualsForSeqOutOfAreaDetails));
        }

        def furtherInformationAboutSampleAvailability = "<w:p xmlns:w=\"http://schemas.openxmlformats.org/wordprocessingml/2006/main\" >\n" +
                "                                <w:pPr>\n" +
                "                                    <w:spacing w:after=\"120\"/>\n" +
                "                                    <w:rPr>\n" +
                "                                        <w:rFonts w:eastAsia=\"Calibri\" w:cs=\"Calibri\" w:asciiTheme=\"minorHAnsi\" w:hAnsiTheme=\"minorHAnsi\"/>\n" +
                "                                        <w:sz w:val=\"20\"/>\n" +
                "                                        <w:szCs w:val=\"20\"/>\n" +
                "                                    </w:rPr>\n" +
                "                                </w:pPr>\n" +
                "                                <w:r>\n" +
                "                                    <w:rPr>\n" +
                "                                        <w:rFonts w:eastAsia=\"Calibri\" w:cs=\"Calibri\" w:asciiTheme=\"minorHAnsi\" w:hAnsiTheme=\"minorHAnsi\"/>\n" +
                "                                        <w:sz w:val=\"20\"/>\n" +
                "                                        <w:szCs w:val=\"20\"/>\n" +
                "                                    </w:rPr>\n" +
                "                                    <w:t xml:space=\"preserve\">Any further information about sample availability </w:t>\n" +
                "                                </w:r>\n" +
                "                            </w:p>"

        def furtherInformationAboutSampleAvailabilityDetails = "<w:tbl xmlns:w=\"http://schemas.openxmlformats.org/wordprocessingml/2006/main\" >\n" +
                "                    <w:tblPr>\n" +
                "                        <w:tblW w:w=\"0\" w:type=\"auto\"/>\n" +
                "                        <w:tblInd w:w=\"98\" w:type=\"dxa\"/>\n" +
                "                        <w:tblBorders>\n" +
                "                            <w:top w:val=\"single\" w:color=\"auto\" w:sz=\"4\" w:space=\"0\"/>\n" +
                "                            <w:left w:val=\"single\" w:color=\"auto\" w:sz=\"4\" w:space=\"0\"/>\n" +
                "                            <w:bottom w:val=\"single\" w:color=\"auto\" w:sz=\"4\" w:space=\"0\"/>\n" +
                "                            <w:right w:val=\"single\" w:color=\"auto\" w:sz=\"4\" w:space=\"0\"/>\n" +
                "                            <w:insideH w:val=\"single\" w:color=\"auto\" w:sz=\"4\" w:space=\"0\"/>\n" +
                "                            <w:insideV w:val=\"single\" w:color=\"auto\" w:sz=\"4\" w:space=\"0\"/>\n" +
                "                        </w:tblBorders>\n" +
                "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"D9D9D9\" w:themeFill=\"background1\" w:themeFillShade=\"D9\"/>\n" +
                "                        <w:tblCellMar>\n" +
                "                            <w:left w:w=\"10\" w:type=\"dxa\"/>\n" +
                "                            <w:right w:w=\"10\" w:type=\"dxa\"/>\n" +
                "                        </w:tblCellMar>\n" +
                "                        <w:tblLook w:firstRow=\"1\" w:lastRow=\"0\" w:firstColumn=\"1\" w:lastColumn=\"0\" w:noHBand=\"0\" w:noVBand=\"1\" w:val=\"04A0\"/>\n" +
                "                    </w:tblPr>\n" +
                "                    <w:tblGrid>\n" +
                "                        <w:gridCol w:w=\"9144\"/>\n" +
                "                    </w:tblGrid>\n" +
                "                    <w:tr w:rsidTr=\"00C87F4E\">\n" +
                "                        <w:trPr>\n" +
                "                            <w:trHeight w:val=\"1\"/>\n" +
                "                        </w:trPr>\n" +
                "                        <w:tc>\n" +
                "                            <w:tcPr>\n" +
                "                                <w:tcW w:w=\"9144\" w:type=\"dxa\"/>\n" +
                "                                <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"D9D9D9\" w:themeFill=\"background1\" w:themeFillShade=\"D9\"/>\n" +
                "                                <w:tcMar>\n" +
                "                                    <w:left w:w=\"108\" w:type=\"dxa\"/>\n" +
                "                                    <w:right w:w=\"108\" w:type=\"dxa\"/>\n" +
                "                                </w:tcMar>\n" +
                "                            </w:tcPr>\n" +
                "                            <w:p>\n" +
                "                                <w:pPr>\n" +
                "                                    <w:spacing w:after=\"120\"/>\n" +
                "                                    <w:rPr>\n" +
                "                                        <w:rFonts w:eastAsia=\"Calibri\" w:cs=\"Calibri\" w:asciiTheme=\"minorHAnsi\" w:hAnsiTheme=\"minorHAnsi\"/>\n" +
                "                                        <w:sz w:val=\"20\"/>\n" +
                "                                        <w:szCs w:val=\"20\"/>\n" +
                "                                    </w:rPr>\n" +
                "                                </w:pPr>\n" +
                "                                <w:r>\n" +
                "                                    <w:rPr>\n" +
                "                                        <w:rFonts w:eastAsia=\"Calibri\" w:cs=\"Calibri\" w:asciiTheme=\"minorHAnsi\" w:hAnsiTheme=\"minorHAnsi\"/>\n" +
                "                                        <w:sz w:val=\"20\"/>\n" +
                "                                        <w:szCs w:val=\"20\"/>\n" +
                "                                    </w:rPr>\n" +
                "                                    <w:t xml:space=\"preserve\"> ${referralRecordInstance?.samplesForSeqDetails ?: ''}</w:t>\n" +
                "                                </w:r>\n" +
                "                            </w:p>\n" +
                "                        </w:tc>\n" +
                "                    </w:tr>\n" +
                "                </w:tbl>"

        mainPart.addObject(org.docx4j.XmlUtils.unmarshalString(furtherInformationAboutSampleAvailability));
        mainPart.addObject(org.docx4j.XmlUtils.unmarshalString(furtherInformationAboutSampleAvailabilityDetails));

        def program = "<w:p xmlns:w=\"http://schemas.openxmlformats.org/wordprocessingml/2006/main\" >\n" +
                "                                <w:pPr>\n" +
                "                                    <w:spacing w:after=\"120\"/>\n" +
                "                                    <w:rPr>\n" +
                "                                        <w:rFonts w:eastAsia=\"Calibri\" w:cs=\"Calibri\" w:asciiTheme=\"minorHAnsi\" w:hAnsiTheme=\"minorHAnsi\"/>\n" +
                "                                        <w:sz w:val=\"20\"/>\n" +
                "                                        <w:szCs w:val=\"20\"/>\n" +
                "                                    </w:rPr>\n" +
                "                                </w:pPr>\n" +
                "                                <w:r>\n" +
                "                                    <w:rPr>\n" +
                "                                        <w:rFonts w:eastAsia=\"Calibri\" w:cs=\"Calibri\" w:asciiTheme=\"minorHAnsi\" w:hAnsiTheme=\"minorHAnsi\"/>\n" +
                "                                        <w:sz w:val=\"20\"/>\n" +
                "                                        <w:szCs w:val=\"20\"/>\n" +
                "                                    </w:rPr>\n" +
                "                                    <w:t xml:space=\"preserve\">Program</w:t>\n" +
                "                                </w:r>\n" +
                "                            </w:p>"

        def programDetails = "<w:tbl xmlns:w=\"http://schemas.openxmlformats.org/wordprocessingml/2006/main\" >\n" +
                "                    <w:tblPr>\n" +
                "                        <w:tblW w:w=\"0\" w:type=\"auto\"/>\n" +
                "                        <w:tblInd w:w=\"98\" w:type=\"dxa\"/>\n" +
                "                        <w:tblBorders>\n" +
                "                            <w:top w:val=\"single\" w:color=\"auto\" w:sz=\"4\" w:space=\"0\"/>\n" +
                "                            <w:left w:val=\"single\" w:color=\"auto\" w:sz=\"4\" w:space=\"0\"/>\n" +
                "                            <w:bottom w:val=\"single\" w:color=\"auto\" w:sz=\"4\" w:space=\"0\"/>\n" +
                "                            <w:right w:val=\"single\" w:color=\"auto\" w:sz=\"4\" w:space=\"0\"/>\n" +
                "                            <w:insideH w:val=\"single\" w:color=\"auto\" w:sz=\"4\" w:space=\"0\"/>\n" +
                "                            <w:insideV w:val=\"single\" w:color=\"auto\" w:sz=\"4\" w:space=\"0\"/>\n" +
                "                        </w:tblBorders>\n" +
                "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"D9D9D9\" w:themeFill=\"background1\" w:themeFillShade=\"D9\"/>\n" +
                "                        <w:tblCellMar>\n" +
                "                            <w:left w:w=\"10\" w:type=\"dxa\"/>\n" +
                "                            <w:right w:w=\"10\" w:type=\"dxa\"/>\n" +
                "                        </w:tblCellMar>\n" +
                "                        <w:tblLook w:firstRow=\"1\" w:lastRow=\"0\" w:firstColumn=\"1\" w:lastColumn=\"0\" w:noHBand=\"0\" w:noVBand=\"1\" w:val=\"04A0\"/>\n" +
                "                    </w:tblPr>\n" +
                "                    <w:tblGrid>\n" +
                "                        <w:gridCol w:w=\"9144\"/>\n" +
                "                    </w:tblGrid>\n" +
                "                    <w:tr w:rsidTr=\"00C87F4E\">\n" +
                "                        <w:trPr>\n" +
                "                            <w:trHeight w:val=\"1\"/>\n" +
                "                        </w:trPr>\n" +
                "                        <w:tc>\n" +
                "                            <w:tcPr>\n" +
                "                                <w:tcW w:w=\"9144\" w:type=\"dxa\"/>\n" +
                "                                <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"D9D9D9\" w:themeFill=\"background1\" w:themeFillShade=\"D9\"/>\n" +
                "                                <w:tcMar>\n" +
                "                                    <w:left w:w=\"108\" w:type=\"dxa\"/>\n" +
                "                                    <w:right w:w=\"108\" w:type=\"dxa\"/>\n" +
                "                                </w:tcMar>\n" +
                "                            </w:tcPr>\n" +
                "                            <w:p>\n" +
                "                                <w:pPr>\n" +
                "                                    <w:spacing w:after=\"120\"/>\n" +
                "                                    <w:rPr>\n" +
                "                                        <w:rFonts w:eastAsia=\"Calibri\" w:cs=\"Calibri\" w:asciiTheme=\"minorHAnsi\" w:hAnsiTheme=\"minorHAnsi\"/>\n" +
                "                                        <w:sz w:val=\"20\"/>\n" +
                "                                        <w:szCs w:val=\"20\"/>\n" +
                "                                    </w:rPr>\n" +
                "                                </w:pPr>\n" +
                "                                <w:r>\n" +
                "                                    <w:rPr>\n" +
                "                                        <w:rFonts w:eastAsia=\"Calibri\" w:cs=\"Calibri\" w:asciiTheme=\"minorHAnsi\" w:hAnsiTheme=\"minorHAnsi\"/>\n" +
                "                                        <w:sz w:val=\"20\"/>\n" +
                "                                        <w:szCs w:val=\"20\"/>\n" +
                "                                    </w:rPr>\n" +
                "                                    <w:t xml:space=\"preserve\"> ${referralRecordInstance?.program?.name ?: ''}</w:t>\n" +
                "                                </w:r>\n" +
                "                            </w:p>\n" +
                "                        </w:tc>\n" +
                "                    </w:tr>\n" +
                "                </w:tbl>"

        mainPart.addObject(org.docx4j.XmlUtils.unmarshalString(program));
        mainPart.addObject(org.docx4j.XmlUtils.unmarshalString(programDetails));

        def note = "<w:p xmlns:w=\"http://schemas.openxmlformats.org/wordprocessingml/2006/main\" >\n" +
                "                                <w:pPr>\n" +
                "                                    <w:spacing w:after=\"120\"/>\n" +
                "                                    <w:rPr>\n" +
                "                                        <w:rFonts w:eastAsia=\"Calibri\" w:cs=\"Calibri\" w:asciiTheme=\"minorHAnsi\" w:hAnsiTheme=\"minorHAnsi\"/>\n" +
                "                                        <w:sz w:val=\"20\"/>\n" +
                "                                        <w:szCs w:val=\"20\"/>\n" +
                "                                    </w:rPr>\n" +
                "                                </w:pPr>\n" +
                "                                <w:r>\n" +
                "                                    <w:rPr>\n" +
                "                                        <w:rFonts w:eastAsia=\"Calibri\" w:cs=\"Calibri\" w:asciiTheme=\"minorHAnsi\" w:hAnsiTheme=\"minorHAnsi\"/>\n" +
                "                                        <w:sz w:val=\"20\"/>\n" +
                "                                        <w:szCs w:val=\"20\"/>\n" +
                "                                    </w:rPr>\n" +
                "                                    <w:t xml:space=\"preserve\">Supporting information</w:t>\n" +
                "                                </w:r>\n" +
                "                            </w:p>"

        def noteDetails = "<w:tbl xmlns:w=\"http://schemas.openxmlformats.org/wordprocessingml/2006/main\" >\n" +
                "                    <w:tblPr>\n" +
                "                        <w:tblW w:w=\"0\" w:type=\"auto\"/>\n" +
                "                        <w:tblInd w:w=\"98\" w:type=\"dxa\"/>\n" +
                "                        <w:tblBorders>\n" +
                "                            <w:top w:val=\"single\" w:color=\"auto\" w:sz=\"4\" w:space=\"0\"/>\n" +
                "                            <w:left w:val=\"single\" w:color=\"auto\" w:sz=\"4\" w:space=\"0\"/>\n" +
                "                            <w:bottom w:val=\"single\" w:color=\"auto\" w:sz=\"4\" w:space=\"0\"/>\n" +
                "                            <w:right w:val=\"single\" w:color=\"auto\" w:sz=\"4\" w:space=\"0\"/>\n" +
                "                            <w:insideH w:val=\"single\" w:color=\"auto\" w:sz=\"4\" w:space=\"0\"/>\n" +
                "                            <w:insideV w:val=\"single\" w:color=\"auto\" w:sz=\"4\" w:space=\"0\"/>\n" +
                "                        </w:tblBorders>\n" +
                "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"D9D9D9\" w:themeFill=\"background1\" w:themeFillShade=\"D9\"/>\n" +
                "                        <w:tblCellMar>\n" +
                "                            <w:left w:w=\"10\" w:type=\"dxa\"/>\n" +
                "                            <w:right w:w=\"10\" w:type=\"dxa\"/>\n" +
                "                        </w:tblCellMar>\n" +
                "                        <w:tblLook w:firstRow=\"1\" w:lastRow=\"0\" w:firstColumn=\"1\" w:lastColumn=\"0\" w:noHBand=\"0\" w:noVBand=\"1\" w:val=\"04A0\"/>\n" +
                "                    </w:tblPr>\n" +
                "                    <w:tblGrid>\n" +
                "                        <w:gridCol w:w=\"9144\"/>\n" +
                "                    </w:tblGrid>\n" +
                "                    <w:tr w:rsidTr=\"00C87F4E\">\n" +
                "                        <w:trPr>\n" +
                "                            <w:trHeight w:val=\"1\"/>\n" +
                "                        </w:trPr>\n" +
                "                        <w:tc>\n" +
                "                            <w:tcPr>\n" +
                "                                <w:tcW w:w=\"9144\" w:type=\"dxa\"/>\n" +
                "                                <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"D9D9D9\" w:themeFill=\"background1\" w:themeFillShade=\"D9\"/>\n" +
                "                                <w:tcMar>\n" +
                "                                    <w:left w:w=\"108\" w:type=\"dxa\"/>\n" +
                "                                    <w:right w:w=\"108\" w:type=\"dxa\"/>\n" +
                "                                </w:tcMar>\n" +
                "                            </w:tcPr>\n" +
                "                            <w:p>\n" +
                "                                <w:pPr>\n" +
                "                                    <w:spacing w:after=\"120\"/>\n" +
                "                                    <w:rPr>\n" +
                "                                        <w:rFonts w:eastAsia=\"Calibri\" w:cs=\"Calibri\" w:asciiTheme=\"minorHAnsi\" w:hAnsiTheme=\"minorHAnsi\"/>\n" +
                "                                        <w:sz w:val=\"20\"/>\n" +
                "                                        <w:szCs w:val=\"20\"/>\n" +
                "                                    </w:rPr>\n" +
                "                                </w:pPr>\n" +
                "                                <w:r>\n" +
                "                                    <w:rPr>\n" +
                "                                        <w:rFonts w:eastAsia=\"Calibri\" w:cs=\"Calibri\" w:asciiTheme=\"minorHAnsi\" w:hAnsiTheme=\"minorHAnsi\"/>\n" +
                "                                        <w:sz w:val=\"20\"/>\n" +
                "                                        <w:szCs w:val=\"20\"/>\n" +
                "                                    </w:rPr>\n" +
                "                                    <w:t xml:space=\"preserve\"> ${referralRecordInstance?.note ?: ''}</w:t>\n" +
                "                                </w:r>\n" +
                "                            </w:p>\n" +
                "                        </w:tc>\n" +
                "                    </w:tr>\n" +
                "                </w:tbl>"

        mainPart.addObject(org.docx4j.XmlUtils.unmarshalString(note));
        mainPart.addObject(org.docx4j.XmlUtils.unmarshalString(noteDetails));

        def targetCategory = "<w:p xmlns:w=\"http://schemas.openxmlformats.org/wordprocessingml/2006/main\" >\n" +
                "                                <w:pPr>\n" +
                "                                    <w:spacing w:after=\"120\"/>\n" +
                "                                    <w:rPr>\n" +
                "                                        <w:rFonts w:eastAsia=\"Calibri\" w:cs=\"Calibri\" w:asciiTheme=\"minorHAnsi\" w:hAnsiTheme=\"minorHAnsi\"/>\n" +
                "                                        <w:sz w:val=\"20\"/>\n" +
                "                                        <w:szCs w:val=\"20\"/>\n" +
                "                                    </w:rPr>\n" +
                "                                </w:pPr>\n" +
                "                                <w:r>\n" +
                "                                    <w:rPr>\n" +
                "                                        <w:rFonts w:eastAsia=\"Calibri\" w:cs=\"Calibri\" w:asciiTheme=\"minorHAnsi\" w:hAnsiTheme=\"minorHAnsi\"/>\n" +
                "                                        <w:sz w:val=\"20\"/>\n" +
                "                                        <w:szCs w:val=\"20\"/>\n" +
                "                                    </w:rPr>\n" +
                "                                    <w:t xml:space=\"preserve\">100,000 Genomes Project Rare Disease category </w:t>\n" +
                "                                </w:r>\n" +
                "                            </w:p>"

        def targetCategoryDetails = "<w:tbl xmlns:w=\"http://schemas.openxmlformats.org/wordprocessingml/2006/main\" >\n" +
                "                    <w:tblPr>\n" +
                "                        <w:tblW w:w=\"0\" w:type=\"auto\"/>\n" +
                "                        <w:tblInd w:w=\"98\" w:type=\"dxa\"/>\n" +
                "                        <w:tblBorders>\n" +
                "                            <w:top w:val=\"single\" w:color=\"auto\" w:sz=\"4\" w:space=\"0\"/>\n" +
                "                            <w:left w:val=\"single\" w:color=\"auto\" w:sz=\"4\" w:space=\"0\"/>\n" +
                "                            <w:bottom w:val=\"single\" w:color=\"auto\" w:sz=\"4\" w:space=\"0\"/>\n" +
                "                            <w:right w:val=\"single\" w:color=\"auto\" w:sz=\"4\" w:space=\"0\"/>\n" +
                "                            <w:insideH w:val=\"single\" w:color=\"auto\" w:sz=\"4\" w:space=\"0\"/>\n" +
                "                            <w:insideV w:val=\"single\" w:color=\"auto\" w:sz=\"4\" w:space=\"0\"/>\n" +
                "                        </w:tblBorders>\n" +
                "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"D9D9D9\" w:themeFill=\"background1\" w:themeFillShade=\"D9\"/>\n" +
                "                        <w:tblCellMar>\n" +
                "                            <w:left w:w=\"10\" w:type=\"dxa\"/>\n" +
                "                            <w:right w:w=\"10\" w:type=\"dxa\"/>\n" +
                "                        </w:tblCellMar>\n" +
                "                        <w:tblLook w:firstRow=\"1\" w:lastRow=\"0\" w:firstColumn=\"1\" w:lastColumn=\"0\" w:noHBand=\"0\" w:noVBand=\"1\" w:val=\"04A0\"/>\n" +
                "                    </w:tblPr>\n" +
                "                    <w:tblGrid>\n" +
                "                        <w:gridCol w:w=\"9144\"/>\n" +
                "                    </w:tblGrid>\n" +
                "                    <w:tr w:rsidTr=\"00C87F4E\">\n" +
                "                        <w:trPr>\n" +
                "                            <w:trHeight w:val=\"1\"/>\n" +
                "                        </w:trPr>\n" +
                "                        <w:tc>\n" +
                "                            <w:tcPr>\n" +
                "                                <w:tcW w:w=\"9144\" w:type=\"dxa\"/>\n" +
                "                                <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"D9D9D9\" w:themeFill=\"background1\" w:themeFillShade=\"D9\"/>\n" +
                "                                <w:tcMar>\n" +
                "                                    <w:left w:w=\"108\" w:type=\"dxa\"/>\n" +
                "                                    <w:right w:w=\"108\" w:type=\"dxa\"/>\n" +
                "                                </w:tcMar>\n" +
                "                            </w:tcPr>\n" +
                "                            <w:p>\n" +
                "                                <w:pPr>\n" +
                "                                    <w:spacing w:after=\"120\"/>\n" +
                "                                    <w:rPr>\n" +
                "                                        <w:rFonts w:eastAsia=\"Calibri\" w:cs=\"Calibri\" w:asciiTheme=\"minorHAnsi\" w:hAnsiTheme=\"minorHAnsi\"/>\n" +
                "                                        <w:sz w:val=\"20\"/>\n" +
                "                                        <w:szCs w:val=\"20\"/>\n" +
                "                                    </w:rPr>\n" +
                "                                </w:pPr>\n" +
                "                                <w:r>\n" +
                "                                    <w:rPr>\n" +
                "                                        <w:rFonts w:eastAsia=\"Calibri\" w:cs=\"Calibri\" w:asciiTheme=\"minorHAnsi\" w:hAnsiTheme=\"minorHAnsi\"/>\n" +
                "                                        <w:sz w:val=\"20\"/>\n" +
                "                                        <w:szCs w:val=\"20\"/>\n" +
                "                                    </w:rPr>\n" +
                "                                    <w:t xml:space=\"preserve\"> ${referralRecordInstance?.targetCategory?.diseaseGroup ?: ''}, ${referralRecordInstance?.targetCategory?.diseaseName ?: ''}</w:t>\n" +
                "                                </w:r>\n" +
                "                            </w:p>\n" +
                "                        </w:tc>\n" +
                "                    </w:tr>\n" +
                "                </w:tbl>"

        mainPart.addObject(org.docx4j.XmlUtils.unmarshalString(targetCategory));
        mainPart.addObject(org.docx4j.XmlUtils.unmarshalString(targetCategoryDetails));

        def eligibility  = "<w:p xmlns:w=\"http://schemas.openxmlformats.org/wordprocessingml/2006/main\" >\n" +
                "                                <w:pPr>\n" +
                "                                    <w:spacing w:after=\"120\"/>\n" +
                "                                    <w:rPr>\n" +
                "                                        <w:rFonts w:eastAsia=\"Calibri\" w:cs=\"Calibri\" w:asciiTheme=\"minorHAnsi\" w:hAnsiTheme=\"minorHAnsi\"/>\n" +
                "                                        <w:sz w:val=\"20\"/>\n" +
                "                                        <w:szCs w:val=\"20\"/>\n" +
                "                                    </w:rPr>\n" +
                "                                </w:pPr>\n" +
                "                                <w:r>\n" +
                "                                    <w:rPr>\n" +
                "                                        <w:rFonts w:eastAsia=\"Calibri\" w:cs=\"Calibri\" w:asciiTheme=\"minorHAnsi\" w:hAnsiTheme=\"minorHAnsi\"/>\n" +
                "                                        <w:sz w:val=\"20\"/>\n" +
                "                                        <w:szCs w:val=\"20\"/>\n" +
                "                                    </w:rPr>\n" +
                "                                    <w:t xml:space=\"preserve\">Is this patient/family eligible?</w:t>\n" +
                "                                </w:r>\n" +
                "                            </w:p>"
        mainPart.addObject(org.docx4j.XmlUtils.unmarshalString(eligibility));
        if (referralRecordInstance?.eligibility?.eligibilityTypeName == 'Yes' || referralRecordInstance?.eligibility?.eligibilityTypeName == 'Unknown'){
            def eligibilityDetails = "<w:tbl xmlns:w=\"http://schemas.openxmlformats.org/wordprocessingml/2006/main\" >\n" +
                    "                    <w:tblPr>\n" +
                    "                        <w:tblW w:w=\"0\" w:type=\"auto\"/>\n" +
                    "                        <w:tblInd w:w=\"98\" w:type=\"dxa\"/>\n" +
                    "                        <w:tblBorders>\n" +
                    "                            <w:top w:val=\"single\" w:color=\"auto\" w:sz=\"4\" w:space=\"0\"/>\n" +
                    "                            <w:left w:val=\"single\" w:color=\"auto\" w:sz=\"4\" w:space=\"0\"/>\n" +
                    "                            <w:bottom w:val=\"single\" w:color=\"auto\" w:sz=\"4\" w:space=\"0\"/>\n" +
                    "                            <w:right w:val=\"single\" w:color=\"auto\" w:sz=\"4\" w:space=\"0\"/>\n" +
                    "                            <w:insideH w:val=\"single\" w:color=\"auto\" w:sz=\"4\" w:space=\"0\"/>\n" +
                    "                            <w:insideV w:val=\"single\" w:color=\"auto\" w:sz=\"4\" w:space=\"0\"/>\n" +
                    "                        </w:tblBorders>\n" +
                    "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"D9D9D9\" w:themeFill=\"background1\" w:themeFillShade=\"D9\"/>\n" +
                    "                        <w:tblCellMar>\n" +
                    "                            <w:left w:w=\"10\" w:type=\"dxa\"/>\n" +
                    "                            <w:right w:w=\"10\" w:type=\"dxa\"/>\n" +
                    "                        </w:tblCellMar>\n" +
                    "                        <w:tblLook w:firstRow=\"1\" w:lastRow=\"0\" w:firstColumn=\"1\" w:lastColumn=\"0\" w:noHBand=\"0\" w:noVBand=\"1\" w:val=\"04A0\"/>\n" +
                    "                    </w:tblPr>\n" +
                    "                    <w:tblGrid>\n" +
                    "                        <w:gridCol w:w=\"9144\"/>\n" +
                    "                    </w:tblGrid>\n" +
                    "                    <w:tr w:rsidTr=\"00C87F4E\">\n" +
                    "                        <w:trPr>\n" +
                    "                            <w:trHeight w:val=\"1\"/>\n" +
                    "                        </w:trPr>\n" +
                    "                        <w:tc>\n" +
                    "                            <w:tcPr>\n" +
                    "                                <w:tcW w:w=\"9144\" w:type=\"dxa\"/>\n" +
                    "                                <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"D9D9D9\" w:themeFill=\"background1\" w:themeFillShade=\"D9\"/>\n" +
                    "                                <w:tcMar>\n" +
                    "                                    <w:left w:w=\"108\" w:type=\"dxa\"/>\n" +
                    "                                    <w:right w:w=\"108\" w:type=\"dxa\"/>\n" +
                    "                                </w:tcMar>\n" +
                    "                            </w:tcPr>\n" +
                    "                            <w:p>\n" +
                    "                                <w:pPr>\n" +
                    "                                    <w:spacing w:after=\"120\"/>\n" +
                    "                                    <w:rPr>\n" +
                    "                                        <w:rFonts w:eastAsia=\"Calibri\" w:cs=\"Calibri\" w:asciiTheme=\"minorHAnsi\" w:hAnsiTheme=\"minorHAnsi\"/>\n" +
                    "                                        <w:sz w:val=\"20\"/>\n" +
                    "                                        <w:szCs w:val=\"20\"/>\n" +
                    "                                    </w:rPr>\n" +
                    "                                </w:pPr>\n" +
                    "                                <w:r>\n" +
                    "                                    <w:rPr>\n" +
                    "                                        <w:rFonts w:eastAsia=\"Calibri\" w:cs=\"Calibri\" w:asciiTheme=\"minorHAnsi\" w:hAnsiTheme=\"minorHAnsi\"/>\n" +
                    "                                        <w:sz w:val=\"20\"/>\n" +
                    "                                        <w:szCs w:val=\"20\"/>\n" +
                    "                                    </w:rPr>\n" +
                    "                                    <w:t xml:space=\"preserve\"> ${referralRecordInstance?.eligibility ?: ''} ${referralRecordInstance?.eligibilityDetails ?: ''}</w:t>\n" +
                    "                                </w:r>\n" +
                    "                            </w:p>\n" +
                    "                        </w:tc>\n" +
                    "                    </w:tr>\n" +
                    "                </w:tbl>"
            mainPart.addObject(org.docx4j.XmlUtils.unmarshalString(eligibilityDetails));
        }else {
            def eligibilityDetails = "<w:tbl xmlns:w=\"http://schemas.openxmlformats.org/wordprocessingml/2006/main\" >\n" +
                    "                    <w:tblPr>\n" +
                    "                        <w:tblW w:w=\"0\" w:type=\"auto\"/>\n" +
                    "                        <w:tblInd w:w=\"98\" w:type=\"dxa\"/>\n" +
                    "                        <w:tblBorders>\n" +
                    "                            <w:top w:val=\"single\" w:color=\"auto\" w:sz=\"4\" w:space=\"0\"/>\n" +
                    "                            <w:left w:val=\"single\" w:color=\"auto\" w:sz=\"4\" w:space=\"0\"/>\n" +
                    "                            <w:bottom w:val=\"single\" w:color=\"auto\" w:sz=\"4\" w:space=\"0\"/>\n" +
                    "                            <w:right w:val=\"single\" w:color=\"auto\" w:sz=\"4\" w:space=\"0\"/>\n" +
                    "                            <w:insideH w:val=\"single\" w:color=\"auto\" w:sz=\"4\" w:space=\"0\"/>\n" +
                    "                            <w:insideV w:val=\"single\" w:color=\"auto\" w:sz=\"4\" w:space=\"0\"/>\n" +
                    "                        </w:tblBorders>\n" +
                    "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"D9D9D9\" w:themeFill=\"background1\" w:themeFillShade=\"D9\"/>\n" +
                    "                        <w:tblCellMar>\n" +
                    "                            <w:left w:w=\"10\" w:type=\"dxa\"/>\n" +
                    "                            <w:right w:w=\"10\" w:type=\"dxa\"/>\n" +
                    "                        </w:tblCellMar>\n" +
                    "                        <w:tblLook w:firstRow=\"1\" w:lastRow=\"0\" w:firstColumn=\"1\" w:lastColumn=\"0\" w:noHBand=\"0\" w:noVBand=\"1\" w:val=\"04A0\"/>\n" +
                    "                    </w:tblPr>\n" +
                    "                    <w:tblGrid>\n" +
                    "                        <w:gridCol w:w=\"9144\"/>\n" +
                    "                    </w:tblGrid>\n" +
                    "                    <w:tr w:rsidTr=\"00C87F4E\">\n" +
                    "                        <w:trPr>\n" +
                    "                            <w:trHeight w:val=\"1\"/>\n" +
                    "                        </w:trPr>\n" +
                    "                        <w:tc>\n" +
                    "                            <w:tcPr>\n" +
                    "                                <w:tcW w:w=\"9144\" w:type=\"dxa\"/>\n" +
                    "                                <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"D9D9D9\" w:themeFill=\"background1\" w:themeFillShade=\"D9\"/>\n" +
                    "                                <w:tcMar>\n" +
                    "                                    <w:left w:w=\"108\" w:type=\"dxa\"/>\n" +
                    "                                    <w:right w:w=\"108\" w:type=\"dxa\"/>\n" +
                    "                                </w:tcMar>\n" +
                    "                            </w:tcPr>\n" +
                    "                            <w:p>\n" +
                    "                                <w:pPr>\n" +
                    "                                    <w:spacing w:after=\"120\"/>\n" +
                    "                                    <w:rPr>\n" +
                    "                                        <w:rFonts w:eastAsia=\"Calibri\" w:cs=\"Calibri\" w:asciiTheme=\"minorHAnsi\" w:hAnsiTheme=\"minorHAnsi\"/>\n" +
                    "                                        <w:sz w:val=\"20\"/>\n" +
                    "                                        <w:szCs w:val=\"20\"/>\n" +
                    "                                    </w:rPr>\n" +
                    "                                </w:pPr>\n" +
                    "                                <w:r>\n" +
                    "                                    <w:rPr>\n" +
                    "                                        <w:rFonts w:eastAsia=\"Calibri\" w:cs=\"Calibri\" w:asciiTheme=\"minorHAnsi\" w:hAnsiTheme=\"minorHAnsi\"/>\n" +
                    "                                        <w:sz w:val=\"20\"/>\n" +
                    "                                        <w:szCs w:val=\"20\"/>\n" +
                    "                                    </w:rPr>\n" +
                    "                                    <w:t xml:space=\"preserve\"> ${referralRecordInstance.eligibility ?: '' } </w:t>\n" +
                    "                                </w:r>\n" +
                    "                            </w:p>\n" +
                    "                        </w:tc>\n" +
                    "                    </w:tr>\n" +
                    "                </w:tbl>"
            mainPart.addObject(org.docx4j.XmlUtils.unmarshalString(eligibilityDetails));
        }

        def genomesProjectRecruitment = "<w:p xmlns:w=\"http://schemas.openxmlformats.org/wordprocessingml/2006/main\" >\n" +
                "                    <w:pPr>\n" +
                "                        <w:spacing w:after=\"120\"/>\n" +
                "                        <w:rPr>\n" +
                "                            <w:rFonts w:eastAsia=\"Calibri\" w:cs=\"Calibri\" w:asciiTheme=\"minorHAnsi\" w:hAnsiTheme=\"minorHAnsi\"/>\n" +
                "                            <w:b/>\n" +
                "                            <w:sz w:val=\"22\"/>\n" +
                "                            <w:szCs w:val=\"22\"/>\n" +
                "                            <w:u w:val=\"single\"/>\n" +
                "                        </w:rPr>\n" +
                "                    </w:pPr>\n" +
                "                    <w:proofErr w:type=\"gramStart\"/>\n" +
                "                    <w:r>\n" +
                "                        <w:rPr>\n" +
                "                            <w:rFonts w:eastAsia=\"Calibri\" w:cs=\"Calibri\" w:asciiTheme=\"minorHAnsi\" w:hAnsiTheme=\"minorHAnsi\"/>\n" +
                "                            <w:b/>\n" +
                "                            <w:sz w:val=\"22\"/>\n" +
                "                            <w:szCs w:val=\"22\"/>\n" +
                "                            <w:u w:val=\"single\"/>\n" +
                "                        </w:rPr>\n" +
                "                        <w:t>100,000 Genomes Project Recruitment</w:t>\n" +
                "                    </w:r>\n" +
                "                </w:p>";

        def consentQuestion = "<w:p xmlns:w=\"http://schemas.openxmlformats.org/wordprocessingml/2006/main\" >\n" +
                "                                <w:pPr>\n" +
                "                                    <w:spacing w:after=\"120\"/>\n" +
                "                                    <w:rPr>\n" +
                "                                        <w:rFonts w:eastAsia=\"Calibri\" w:cs=\"Calibri\" w:asciiTheme=\"minorHAnsi\" w:hAnsiTheme=\"minorHAnsi\"/>\n" +
                "                                        <w:sz w:val=\"20\"/>\n" +
                "                                        <w:szCs w:val=\"20\"/>\n" +
                "                                    </w:rPr>\n" +
                "                                </w:pPr>\n" +
                "                                <w:r>\n" +
                "                                    <w:rPr>\n" +
                "                                        <w:rFonts w:eastAsia=\"Calibri\" w:cs=\"Calibri\" w:asciiTheme=\"minorHAnsi\" w:hAnsiTheme=\"minorHAnsi\"/>\n" +
                "                                        <w:sz w:val=\"20\"/>\n" +
                "                                        <w:szCs w:val=\"20\"/>\n" +
                "                                    </w:rPr>\n" +
                "                                    <w:t xml:space=\"preserve\">The Clinical Genetics Department runs dedicated recruitment clinics for the 100,000 Genomes Project.  At your request, this application can stand as a referral for a Genetic Counsellor to consent the patient or family and collect samples through one of these clinics</w:t>\n" +
                "                                </w:r>\n" +
                "                            </w:p>"

        def consentPatientOrFamilyDetails = "<w:tbl xmlns:w=\"http://schemas.openxmlformats.org/wordprocessingml/2006/main\" >\n" +
                "                    <w:tblPr>\n" +
                "                        <w:tblW w:w=\"0\" w:type=\"auto\"/>\n" +
                "                        <w:tblInd w:w=\"98\" w:type=\"dxa\"/>\n" +
                "                        <w:tblBorders>\n" +
                "                            <w:top w:val=\"single\" w:color=\"auto\" w:sz=\"4\" w:space=\"0\"/>\n" +
                "                            <w:left w:val=\"single\" w:color=\"auto\" w:sz=\"4\" w:space=\"0\"/>\n" +
                "                            <w:bottom w:val=\"single\" w:color=\"auto\" w:sz=\"4\" w:space=\"0\"/>\n" +
                "                            <w:right w:val=\"single\" w:color=\"auto\" w:sz=\"4\" w:space=\"0\"/>\n" +
                "                            <w:insideH w:val=\"single\" w:color=\"auto\" w:sz=\"4\" w:space=\"0\"/>\n" +
                "                            <w:insideV w:val=\"single\" w:color=\"auto\" w:sz=\"4\" w:space=\"0\"/>\n" +
                "                        </w:tblBorders>\n" +
                "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"D9D9D9\" w:themeFill=\"background1\" w:themeFillShade=\"D9\"/>\n" +
                "                        <w:tblCellMar>\n" +
                "                            <w:left w:w=\"10\" w:type=\"dxa\"/>\n" +
                "                            <w:right w:w=\"10\" w:type=\"dxa\"/>\n" +
                "                        </w:tblCellMar>\n" +
                "                        <w:tblLook w:firstRow=\"1\" w:lastRow=\"0\" w:firstColumn=\"1\" w:lastColumn=\"0\" w:noHBand=\"0\" w:noVBand=\"1\" w:val=\"04A0\"/>\n" +
                "                    </w:tblPr>\n" +
                "                    <w:tblGrid>\n" +
                "                        <w:gridCol w:w=\"9144\"/>\n" +
                "                    </w:tblGrid>\n" +
                "                    <w:tr w:rsidTr=\"00C87F4E\">\n" +
                "                        <w:trPr>\n" +
                "                            <w:trHeight w:val=\"1\"/>\n" +
                "                        </w:trPr>\n" +
                "                        <w:tc>\n" +
                "                            <w:tcPr>\n" +
                "                                <w:tcW w:w=\"9144\" w:type=\"dxa\"/>\n" +
                "                                <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"D9D9D9\" w:themeFill=\"background1\" w:themeFillShade=\"D9\"/>\n" +
                "                                <w:tcMar>\n" +
                "                                    <w:left w:w=\"108\" w:type=\"dxa\"/>\n" +
                "                                    <w:right w:w=\"108\" w:type=\"dxa\"/>\n" +
                "                                </w:tcMar>\n" +
                "                            </w:tcPr>\n" +
                "                            <w:p>\n" +
                "                                <w:pPr>\n" +
                "                                    <w:spacing w:after=\"120\"/>\n" +
                "                                    <w:rPr>\n" +
                "                                        <w:rFonts w:eastAsia=\"Calibri\" w:cs=\"Calibri\" w:asciiTheme=\"minorHAnsi\" w:hAnsiTheme=\"minorHAnsi\"/>\n" +
                "                                        <w:sz w:val=\"20\"/>\n" +
                "                                        <w:szCs w:val=\"20\"/>\n" +
                "                                    </w:rPr>\n" +
                "                                </w:pPr>\n" +
                "                                <w:r>\n" +
                "                                    <w:rPr>\n" +
                "                                        <w:rFonts w:eastAsia=\"Calibri\" w:cs=\"Calibri\" w:asciiTheme=\"minorHAnsi\" w:hAnsiTheme=\"minorHAnsi\"/>\n" +
                "                                        <w:sz w:val=\"20\"/>\n" +
                "                                        <w:szCs w:val=\"20\"/>\n" +
                "                                    </w:rPr>\n" +
                "                                    <w:t xml:space=\"preserve\"> ${referralRecordInstance?.consentPatientOrFamily ?: ''}</w:t>\n" +
                "                                </w:r>\n" +
                "                            </w:p>\n" +
                "                        </w:tc>\n" +
                "                    </w:tr>\n" +
                "                </w:tbl>"

        mainPart.addObject(org.docx4j.XmlUtils.unmarshalString(genomesProjectRecruitment));
        mainPart.addObject(org.docx4j.XmlUtils.unmarshalString(consentQuestion));
        mainPart.addObject(org.docx4j.XmlUtils.unmarshalString(consentPatientOrFamilyDetails));

        // write out our word doc to disk
        File file = File.createTempFile("wordexport-", ".docx")
        wordMLPackage.save file

        // and send it all back to the browser
        def response = WebUtils.retrieveGrailsWebRequest().getCurrentResponse();
        response.setHeader("Content-disposition", "attachment; filename=" + referralRecordInstance.patients?.find{p -> p.isProband}?.familyName + "-" + referralRecordInstance.uniqueRef + ".docx");
        response.setContentType("application/vnd.openxmlformats-officedocument.wordprocessingml.document")
        response.outputStream << file.readBytes()
        file.delete()
    }
}

