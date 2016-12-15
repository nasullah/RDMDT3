package rdmdt

import grails.converters.JSON
import grails.plugin.springsecurity.annotation.Secured
import grails.plugins.rendering.RenderingService
import org.apache.commons.lang.BooleanUtils
//import org.codehaus.groovy.grails.commons.ApplicationHolder
import grails.util.Holders
import org.grails.core.io.ResourceLocator

//import org.codehaus.groovy.grails.web.context.ServletContextHolder
import org.grails.plugins.filterpane.FilterPaneUtils
import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional
import org.docx4j.openpackaging.packages.WordprocessingMLPackage
/**
 * ReferralRecordController
 * A controller class handles incoming web requests and performs actions such as redirects, rendering views and so on.
 */
@Secured(['ROLE_USER', 'ROLE_ADMIN'])
@Transactional(readOnly = true)
class ReferralRecordController {

    def filterPaneService
    def springSecurityService
    static allowedMethods = [save: "POST", update: ["PUT", "POST"], delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond ReferralRecord.list(params).sort {it?.referralStatus?.referralStatusName}, model: [referralRecordInstanceCount: ReferralRecord.count()]
    }

//    def exportWord = {
//        def referralRecordInstance = ReferralRecord.findById(params.long('referralRecordId'))
//        wordProcessingService.processingWordDocument(referralRecordInstance)
//    }


    def exportWord = {
        def referralRecordInstance = ReferralRecord.findById(params.long('referralRecordId'))
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
        if (referralRecordInstance?.otherFamilyMembersAffected){
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
                    "                                    <w:t xml:space=\"preserve\"> No </w:t>\n" +
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
                "                                    <w:t xml:space=\"preserve\">Is there evidence of reduced penetrance?</w:t>\n" +
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
                "                                    <w:t xml:space=\"preserve\"> Breast And Or Ovarian Cancer: </w:t>\n" +
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
                "                                    <w:t xml:space=\"preserve\">Breast And Or Ovarian Cancer: </w:t>\n" +
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
                "                                    <w:t xml:space=\"preserve\"> Colorectal Cancer: </w:t>\n" +
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
                "                                    <w:t xml:space=\"preserve\">Colorectal Cancer:</w:t>\n" +
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
                "                                    <w:t xml:space=\"preserve\"> Ischaemic Heart Disease Or Stroke: </w:t>\n" +
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
                "                                    <w:t xml:space=\"preserve\">Ischaemic Heart Disease Or Stroke: </w:t>\n" +
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
                "                                    <w:t xml:space=\"preserve\"> Endocrine Tumours: </w:t>\n" +
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
                "                                    <w:t xml:space=\"preserve\">Endocrine Tumours: </w:t>\n" +
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
                "                                    <w:t xml:space=\"preserve\">Are any individuals proposed for sequencing out of area?</w:t>\n" +
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
                "                                    <w:t xml:space=\"preserve\">Target 100,000 Genomes Project Rare Disease category </w:t>\n" +
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
        response.setHeader("Content-disposition", "attachment; filename=" + referralRecordInstance.patients?.find{p -> p.isProband}?.familyName + "-" + referralRecordInstance.uniqueRef + ".docx");
        response.setContentType("application/vnd.openxmlformats-officedocument.wordprocessingml.document")
        response.outputStream << file.readBytes()
        file.delete()
    }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [referralRecordInstanceList: ReferralRecord.list(params).sort {it?.referralStatus?.referralStatusName}, referralRecordInstanceTotal: ReferralRecord.count()]
    }

    def filter = {
        if(!params.max) params.max = 10
        render( view:'list', model:[ referralRecordInstanceList: filterPaneService.filter( params, ReferralRecord ),
                                     referralRecordInstanceTotal: filterPaneService.count( params, ReferralRecord ),
                                     filterParams: FilterPaneUtils.extractFilterParams(params), params:params ] )
    }

    def filteredReferralList() {
        def currentUser = springSecurityService.currentUser.username
        def clinician
        if (currentUser?.toString()?.contains('.')) {
            def forename = currentUser?.toString()?.split("\\.")[0]
            def surname = currentUser?.toString()?.split("\\.")[1]
            clinician = Clinician.createCriteria().get {
                and {
                    eq("forename", forename, [ignoreCase: true])
                    eq("surname", surname, [ignoreCase: true])
                }
            }
            def referralRecordInstanceList = ReferralRecord.findAllByClinicianOrCorrespondingClinician(clinician, clinician).sort {it.referralStatus.referralStatusName}
            [referralRecordInstanceList: referralRecordInstanceList]
        }
    }

    def reviewRequestedReferralList() {
        def currentUser = springSecurityService.currentUser.username
        def clinician
        if (currentUser?.toString()?.contains('.')) {
            def forename = currentUser?.toString()?.split("\\.")[0]
            def surname = currentUser?.toString()?.split("\\.")[1]
            clinician = Clinician.createCriteria().get {
                and {
                    eq("forename", forename, [ignoreCase: true])
                    eq("surname", surname, [ignoreCase: true])
                }
            }
            [referralRecordInstanceList: ReferralRecord.findAllByAssignedToAndReferralStatus(clinician, ReferralStatus.findByReferralStatusName('Review Requested'))]
        }
    }

    def filteredReferralListByStatus(){
        def status = ReferralStatus.findById(params.long('status'))
        def referralRecordInstanceList = ReferralRecord.findAllByReferralStatus(status)
        [referralRecordInstanceList:referralRecordInstanceList, status:status?.referralStatusName]
    }

    //def pdfRenderingService
    def renderWednesdayMeetingReviewLetter(){
        def ouh_nhs = new File(ApplicationHolder.application.parentContext.servletContext.getRealPath("/images/ouh_nhs_logo.jpg"))
        def ox_brc_logos = new File(ApplicationHolder.application.parentContext.servletContext.getRealPath("/images/ox_brc_logos.jpg"))
        def oxford_uni = new File(ApplicationHolder.application.parentContext.servletContext.getRealPath("/images/oxford_uni.gif"))
        def julian_knight_signature = new File(ApplicationHolder.application.parentContext.servletContext.getRealPath("/images/julian_knight_signature.jpg"))
        def formInstance = ReferralRecord.get(params.long('id'))
        def args = [template:"renderWednesdayMeetingReviewLetter", model:[form:formInstance, ouh_nhs:ouh_nhs.bytes, ox_brc_logos:ox_brc_logos.bytes, oxford_uni:oxford_uni.bytes, julian_knight_signature:julian_knight_signature.bytes]]
        RenderingService.renderPdf(args+[controller:this])
    }

    def renderHICFLetter(){
        def ouh_nhs = new File(ApplicationHolder.application.parentContext.servletContext.getRealPath("/images/ouh_nhs_logo.jpg"))
        def ox_brc_logos = new File(ApplicationHolder.application.parentContext.servletContext.getRealPath("/images/ox_brc_logos.jpg"))
        def oxford_uni = new File(ApplicationHolder.application.parentContext.servletContext.getRealPath("/images/oxford_uni.gif"))
        def julian_knight_signature = new File(ApplicationHolder.application.parentContext.servletContext.getRealPath("/images/julian_knight_signature.jpg"))
        def formInstance = ReferralRecord.get(params.long('id'))
        def args = [template:"renderHICFLetter", model:[form:formInstance, ouh_nhs:ouh_nhs.bytes, ox_brc_logos:ox_brc_logos.bytes, oxford_uni:oxford_uni.bytes, julian_knight_signature:julian_knight_signature.bytes]]
        RenderingService.renderPdf(args+[controller:this])
    }

    def renderOtherTestingConditionalLetter(){
        def ouh_nhs = new File(ApplicationHolder.application.parentContext.servletContext.getRealPath("/images/ouh_nhs_logo.jpg"))
        def ox_brc_logos = new File(ApplicationHolder.application.parentContext.servletContext.getRealPath("/images/ox_brc_logos.jpg"))
        def oxford_uni = new File(ApplicationHolder.application.parentContext.servletContext.getRealPath("/images/oxford_uni.gif"))
        def julian_knight_signature = new File(ApplicationHolder.application.parentContext.servletContext.getRealPath("/images/julian_knight_signature.jpg"))
        def formInstance = ReferralRecord.get(params.long('id'))
        def args = [template:"renderOtherTestingConditionalLetter", model:[form:formInstance, ouh_nhs:ouh_nhs.bytes, ox_brc_logos:ox_brc_logos.bytes, oxford_uni:oxford_uni.bytes, julian_knight_signature:julian_knight_signature.bytes]]
        RenderingService.renderPdf(args+[controller:this])
    }

    def renderNotApprovedLetter(){
        def ouh_nhs = new File(ApplicationHolder.application.parentContext.servletContext.getRealPath("/images/ouh_nhs_logo.jpg"))
        def ox_brc_logos = new File(ApplicationHolder.application.parentContext.servletContext.getRealPath("/images/ox_brc_logos.jpg"))
        def oxford_uni = new File(ApplicationHolder.application.parentContext.servletContext.getRealPath("/images/oxford_uni.gif"))
        def julian_knight_signature = new File(ApplicationHolder.application.parentContext.servletContext.getRealPath("/images/julian_knight_signature.jpg"))
        def formInstance = ReferralRecord.get(params.long('id'))
        def args = [template:"renderNotApprovedLetter", model:[form:formInstance, ouh_nhs:ouh_nhs.bytes, ox_brc_logos:ox_brc_logos.bytes, oxford_uni:oxford_uni.bytes, julian_knight_signature:julian_knight_signature.bytes]]
        RenderingService.renderPdf(args+[controller:this])
    }

    def renderStandardApprovalLetter(){
        def ouh_nhs = new File(Holders.grailsApplication.mainContext.servletContext.getRealPath("/images/ouh_nhs_logo.jpg"))
        def ox_brc_logos = new File(Holders.grailsApplication.mainContext.servletContext.getRealPath("/images/ox_brc_logos.jpg"))
        def oxford_uni = new File(Holders.grailsApplication.mainContext.servletContext.getRealPath("/images/oxford_uni.gif"))
        def julian_knight_signature = new File(Holders.grailsApplication.mainContext.servletContext.getRealPath("/images/julian_knight_signature.jpg"))
       def formInstance = ReferralRecord.get(params.long('id'))
        def args = [template:"/referralRecord/renderStandardApprovalLetter", model:[form:formInstance, ouh_nhs:ouh_nhs.bytes, ox_brc_logos:ox_brc_logos.bytes, oxford_uni:oxford_uni.bytes, julian_knight_signature:julian_knight_signature.bytes]]
        pdfRenderingService.render(args+[controller:this],response)
//        pdfRenderingService(template: "renderStandardApprovalLetter", model:[form:formInstance])
    }


    def submittedApplication(){
        def referralRecordInstanceList = ReferralRecord.findAllByReferralStatus(ReferralStatus.findByReferralStatusName('Submitted'))?.sort {it?.submittedDate}
        [referralRecordInstanceList:referralRecordInstanceList]
    }

    def searchRareDiseaseCondition = {
        def listRareDiseaseCondition = RareDiseaseConditions.createCriteria().listDistinct {
            ilike("diseaseName", "%${params.query}%")
        }

        withFormat {
            xml {
                //Create XML response
                render(contentType: "text/xml") {
                    results() {
                        listRareDiseaseCondition.each { rareDiseaseCondition ->
                            result() {
                                name(rareDiseaseCondition)
                                //Optional id which will be available in onItemSelect
                                id(rareDiseaseCondition.id)
                            }
                        }
                    }
                }
            }
            json {
                def results = listRareDiseaseCondition.collect {
                    ['label':it.toString(), 'value':it.id]
                }
                render results as JSON
            }
        }
    }

    def show(ReferralRecord referralRecordInstance) {
        def currentUser = springSecurityService?.currentUser?.username
        def currentClinician = null
        if (currentUser?.toString()?.contains('.')){
            def forename = currentUser?.toString()?.split("\\.")[0]
            def surname = currentUser?.toString()?.split("\\.")[1]
            currentClinician = Clinician.createCriteria().get {
                and{
                    eq("forename", forename, [ignoreCase: true])
                    eq("surname", surname, [ignoreCase: true])
                }
            }
        }
        [referralRecordInstance: referralRecordInstance, currentClinician:currentClinician]
    }

    def create() {
        respond referralRecordInstance: new ReferralRecord(params)
    }

    def download(long id) {
        ReferralRecord referralRecordInstance = ReferralRecord.get(id)
        if ( referralRecordInstance == null) {
            flash.message = "Pedigree file not found"
            redirect (action:'index')
        } else {
            response.setContentType("APPLICATION/OCTET-STREAM")
            response.setHeader("Content-Disposition", "Attachment;Filename=\"${referralRecordInstance.pedigree}\"")

            def file = new File(referralRecordInstance.pedigree)
            def fileInputStream = new FileInputStream(file)
            def outputStream = response.getOutputStream()

            byte[] buffer = new byte[4096];
            int len;
            while ((len = fileInputStream.read(buffer)) > 0) {
                outputStream.write(buffer, 0, len);
            }

            outputStream.flush()
            outputStream.close()
            fileInputStream.close()
        }
    }

    @Transactional
    def save(ReferralRecord referralRecordInstance) {
        if (referralRecordInstance == null) {
            notFound()
            return
        }

        if (referralRecordInstance.hasErrors()) {
            respond referralRecordInstance.errors, view: 'create'
            return
        }

        if (params.nhsNumberProband && Patient.findByNhsNumber(params.nhsNumberProband)){
            flash.message = "A patient with NHS number ${params.nhsNumberProband} already exists"
            respond referralRecordInstance, view: 'create'
            return
        }

        def proband = new Patient(isProband: true, nhsNumber: params.nhsNumberProband, gender: params.genderProband, ethnicity: params.ethnicityProband,
                                  otherEthnicity: params.otherEthnicityProband, age: params.ageProband, ageUnit: params.egeUnitProband, givenName: params.givenName, familyName: params.familyName)
        if (proband){
            referralRecordInstance.addToPatients(proband)
            if (ReferralStatus.findById(params.long('referralStatus'))){
                referralRecordInstance.referralStatus = ReferralStatus.findById(params.long('referralStatus'))
            }else {
                referralRecordInstance.referralStatus = ReferralStatus.findByReferralStatusName('In progress')
            }
            referralRecordInstance.save flush: true

            List<ClinicalDetails> clinicalDetailsList = new ArrayList<ClinicalDetails>();
            if (params.cDetails0){
                clinicalDetailsList.add(new ClinicalDetails(clinicalDetailsName: params.cDetails0))
            }
            if (params.cDetails1){
                clinicalDetailsList.add(new ClinicalDetails(clinicalDetailsName:params.cDetails1))
            }
            if (params.cDetails2){
                clinicalDetailsList.add(new ClinicalDetails(clinicalDetailsName:params.cDetails2))
            }
            if (params.cDetails3){
                clinicalDetailsList.add(new ClinicalDetails(clinicalDetailsName:params.cDetails3))
            }
            if (params.cDetails4){
                clinicalDetailsList.add(new ClinicalDetails(clinicalDetailsName:params.cDetails4))
            }
            if (params.cDetails5){
                clinicalDetailsList.add(new ClinicalDetails(clinicalDetailsName:params.cDetails5))
            }
            if (params.cDetails6){
                clinicalDetailsList.add(new ClinicalDetails(clinicalDetailsName:params.cDetails6))
            }
            if (!clinicalDetailsList.empty){
                for (int i = 0; i <clinicalDetailsList.size(); i++ ){
                    referralRecordInstance.addToClinicalDetails(clinicalDetailsList.get(i)).save flush: true
                }
            }

            List<CoApplicant> coApplicantList = new ArrayList<CoApplicant>();
            if (Clinician.findById(params.long('coapplicant1'))){
                def clinician = Clinician.findById(params.long('coapplicant1'))
                coApplicantList.add(new CoApplicant(coApplicant: clinician))
            }

            if (Clinician.findById(params.long('coapplicant2'))){
                def clinician = Clinician.findById(params.long('coapplicant2'))
                coApplicantList.add(new CoApplicant(coApplicant: clinician))
            }

            if (Clinician.findById(params.long('coapplicant3'))){
                def clinician = Clinician.findById(params.long('coapplicant3'))
                coApplicantList.add(new CoApplicant(coApplicant: clinician))
            }

            if (Clinician.findById(params.long('coapplicant4'))){
                def clinician = Clinician.findById(params.long('coapplicant4'))
                coApplicantList.add(new CoApplicant(coApplicant: clinician))
            }

            if (Clinician.findById(params.long('coapplicant5'))){
                def clinician = Clinician.findById(params.long('coapplicant5'))
                coApplicantList.add(new CoApplicant(coApplicant: clinician))
            }

            if (!coApplicantList.empty){
                for (int i = 0; i <coApplicantList.size(); i++ ){
                    referralRecordInstance.addToCoApplicants(coApplicantList.get(i)).save flush: true
                }
            }

            new Paternal(breastAndOrOvarianCancer: params.breastAndOrOvarianCancerPaternal, colorectalCancer: params.colorectalCancerPaternal,
                    ischaemicHeartDiseaseOrStroke: params.ischaemicHeartDiseaseOrStrokePaternal, endocrineTumours: params.endocrineTumoursPaternal, referralRecord: referralRecordInstance).save flush: true

            new Maternal(breastAndOrOvarianCancer: params.breastAndOrOvarianCancerMaternal, colorectalCancer: params.colorectalCancerMaternal,
                    ischaemicHeartDiseaseOrStroke: params.ischaemicHeartDiseaseOrStrokeMaternal, endocrineTumours: params.endocrineTumoursMaternal, referralRecord: referralRecordInstance).save flush: true

            if (params.ethnicityFather){
                def ethnicity = params.ethnicityFather
                def patient = new Patient(isProband: false, referralRecord: referralRecordInstance, ethnicity: ethnicity, otherEthnicity: params.otherEthnicityFather).save flush: true
                new Relationship(relationshipType: RelationshipType.findByRelationshipTypeName('Father'), patient: proband, relatedPatient: patient).save flush: true
            }

            if (params.ethnicityMother){
                def ethnicity = params.ethnicityMother
                def patient = new Patient(isProband: false, referralRecord: referralRecordInstance, ethnicity: ethnicity, otherEthnicity: params.otherEthnicityMother).save flush: true
                new Relationship(relationshipType: RelationshipType.findByRelationshipTypeName('Mother'), patient: proband, relatedPatient: patient).save flush: true
            }

            if (Clinician.findById(params.long('correspondingClinician'))){
                def correspondingClinician = Clinician.findById(params.long('correspondingClinician'))
                referralRecordInstance.correspondingClinician = correspondingClinician
                referralRecordInstance.save flush: true
            } else {
                referralRecordInstance.correspondingClinician = referralRecordInstance.clinician
                referralRecordInstance.save flush: true
            }

            def pedigreeFile = request.getFile('pedigreeFile')
            if (pedigreeFile?.originalFilename){
                if (pedigreeFile?.empty) {
                    flash.message = "File cannot be empty"
                    respond referralRecordInstance.errors, view: 'create'
                    return
                }
                referralRecordInstance.pedigree = grailsApplication.config.uploadFolder +'Pedigree/'+
                        referralRecordInstance.id.toString() + '.' + pedigreeFile.originalFilename
                def destinationFile = new File(referralRecordInstance.pedigree)

                try {
                    pedigreeFile.transferTo(destinationFile)
                    referralRecordInstance.save flush: true
                }
                catch (Exception ex) {
                    log.error(ex)
                    referralRecordInstance.pedigree = null
                    referralRecordInstance.save flush: true
                }
            }

            if (params.attachedEvidenceFile && params.attachedEvidenceType){
                def attachedEvidenceFile = request.getFile('attachedEvidenceFile')
                if (!attachedEvidenceFile.originalFilename) {
                    flash.message = "Please choose a file"
                    respond referralRecordInstance, view: 'create'
                }else{
                    def attachedEvidenceInstance = new AttachedEvidence(type: params.attachedEvidenceType, addedOn: new Date(), referralRecord: referralRecordInstance)
                    attachedEvidenceInstance.save flush: true
                    attachedEvidenceInstance.content = grailsApplication.config.uploadFolder +'Attached_Evidence/'+ attachedEvidenceInstance.id.toString() + '.' + attachedEvidenceFile.originalFilename
                    def destinationFile = new File(attachedEvidenceInstance.content)

                    try {
                        attachedEvidenceFile.transferTo(destinationFile)
                        attachedEvidenceInstance.save flush: true
                    }
                    catch (Exception ex) {
                        log.error(ex)
                    }
                }
            }

            if (params.attachedEvidenceFile1 && params.attachedEvidenceType1){
                def attachedEvidenceFile = request.getFile('attachedEvidenceFile1')
                if (!attachedEvidenceFile.originalFilename) {
                    flash.message = "Please choose a file"
                    respond referralRecordInstance, view: 'create'
                }else{
                    def attachedEvidenceInstance = new AttachedEvidence(type: params.attachedEvidenceType1, addedOn: new Date(), referralRecord: referralRecordInstance)
                    attachedEvidenceInstance.save flush: true
                    attachedEvidenceInstance.content = grailsApplication.config.uploadFolder +'Attached_Evidence/'+ attachedEvidenceInstance.id.toString() + '.' + attachedEvidenceFile.originalFilename
                    def destinationFile = new File(attachedEvidenceInstance.content)

                    try {
                        attachedEvidenceFile.transferTo(destinationFile)
                        attachedEvidenceInstance.save flush: true
                    }
                    catch (Exception ex) {
                        log.error(ex)
                    }
                }
            }

            if (params.attachedEvidenceFile2 && params.attachedEvidenceType2){
                def attachedEvidenceFile = request.getFile('attachedEvidenceFile2')
                if (!attachedEvidenceFile.originalFilename) {
                    flash.message = "Please choose a file"
                    respond referralRecordInstance, view: 'create'
                }else{
                    def attachedEvidenceInstance = new AttachedEvidence(type: params.attachedEvidenceType2, addedOn: new Date(), referralRecord: referralRecordInstance)
                    attachedEvidenceInstance.save flush: true
                    attachedEvidenceInstance.content = grailsApplication.config.uploadFolder +'Attached_Evidence/'+ attachedEvidenceInstance.id.toString() + '.' + attachedEvidenceFile.originalFilename
                    def destinationFile = new File(attachedEvidenceInstance.content)

                    try {
                        attachedEvidenceFile.transferTo(destinationFile)
                        attachedEvidenceInstance.save flush: true
                    }
                    catch (Exception ex) {
                        log.error(ex)
                    }
                }
            }

            if (params.attachedEvidenceFile3 && params.attachedEvidenceType3){
                def attachedEvidenceFile = request.getFile('attachedEvidenceFile3')
                if (!attachedEvidenceFile.originalFilename) {
                    flash.message = "Please choose a file"
                    respond referralRecordInstance, view: 'create'
                }else{
                    def attachedEvidenceInstance = new AttachedEvidence(type: params.attachedEvidenceType3, addedOn: new Date(), referralRecord: referralRecordInstance)
                    attachedEvidenceInstance.save flush: true
                    attachedEvidenceInstance.content = grailsApplication.config.uploadFolder +'Attached_Evidence/'+ attachedEvidenceInstance.id.toString() + '.' + attachedEvidenceFile.originalFilename
                    def destinationFile = new File(attachedEvidenceInstance.content)

                    try {
                        attachedEvidenceFile.transferTo(destinationFile)
                        attachedEvidenceInstance.save flush: true
                    }
                    catch (Exception ex) {
                        log.error(ex)
                    }
                }
            }

            if (params.attachedEvidenceFile4 && params.attachedEvidenceType4){
                def attachedEvidenceFile = request.getFile('attachedEvidenceFile4')
                if (!attachedEvidenceFile.originalFilename) {
                    flash.message = "Please choose a file"
                    respond referralRecordInstance, view: 'create'
                }else{
                    def attachedEvidenceInstance = new AttachedEvidence(type: params.attachedEvidenceType4, addedOn: new Date(), referralRecord: referralRecordInstance)
                    attachedEvidenceInstance.save flush: true
                    attachedEvidenceInstance.content = grailsApplication.config.uploadFolder +'Attached_Evidence/'+ attachedEvidenceInstance.id.toString() + '.' + attachedEvidenceFile.originalFilename
                    def destinationFile = new File(attachedEvidenceInstance.content)

                    try {
                        attachedEvidenceFile.transferTo(destinationFile)
                        attachedEvidenceInstance.save flush: true
                    }
                    catch (Exception ex) {
                        log.error(ex)
                    }
                }
            }

            if (params.attachedEvidenceFile5 && params.attachedEvidenceType5){
                def attachedEvidenceFile = request.getFile('attachedEvidenceFile5')
                if (!attachedEvidenceFile.originalFilename) {
                    flash.message = "Please choose a file"
                    respond referralRecordInstance, view: 'create'
                }else{
                    def attachedEvidenceInstance = new AttachedEvidence(type: params.attachedEvidenceType5, addedOn: new Date(), referralRecord: referralRecordInstance)
                    attachedEvidenceInstance.save flush: true
                    attachedEvidenceInstance.content = grailsApplication.config.uploadFolder +'Attached_Evidence/'+ attachedEvidenceInstance.id.toString() + '.' + attachedEvidenceFile.originalFilename
                    def destinationFile = new File(attachedEvidenceInstance.content)

                    try {
                        attachedEvidenceFile.transferTo(destinationFile)
                        attachedEvidenceInstance.save flush: true
                    }
                    catch (Exception ex) {
                        log.error(ex)
                    }
                }
            }

            flash.message = "Application ${referralRecordInstance.uniqueRef} is created"
            redirect referralRecordInstance
        }
    }

    def edit(ReferralRecord referralRecordInstance) {
        def currentUser = springSecurityService?.currentUser?.username
        if (springSecurityService.authentication.authorities.find {it.authority == 'ROLE_ADMIN'}){
            respond referralRecordInstance: referralRecordInstance
        }else {
            def clinician
            if (currentUser?.toString()?.contains('.')){
                def forename = currentUser?.toString()?.split("\\.")[0]
                def surname = currentUser?.toString()?.split("\\.")[1]
                clinician = Clinician.createCriteria().get {
                    and{
                        eq("forename", forename, [ignoreCase: true])
                        eq("surname", surname, [ignoreCase: true])
                    }
                }
            }
            if (clinician){
                if (referralRecordInstance.clinician == clinician || referralRecordInstance.correspondingClinician == clinician){
                    respond referralRecordInstance: referralRecordInstance
                }else {
                    flash.message = "You cannot edit this application"
                    redirect(action: 'show', params: [id: referralRecordInstance.id])
                }
            }
        }
    }

    @Transactional
    def updateStatus(){
            def referralRecordInstance = ReferralRecord.findById(params.long('referralRecord'))
            referralRecordInstance.referralStatus = ReferralStatus.findById(params.long('referralStatus'))
            if (referralRecordInstance.referralStatus.referralStatusName == 'Submitted'){
                    referralRecordInstance.submittedDate = new Date()
            }
            if (referralRecordInstance.referralStatus.referralStatusName == 'Approved'){
                    referralRecordInstance.approvedDate = new Date()
            }
            referralRecordInstance.save flush: true
            flash.message = "Application status updated on ${referralRecordInstance?.submittedDate}"
            redirect referralRecordInstance
    }

    @Transactional
    def updateReviewDetails(){
        def referralRecordInstance = ReferralRecord.findById(params.long('referralRecord'))
        referralRecordInstance.reviewDetails = params.reviewDetails
        referralRecordInstance.referralStatus = ReferralStatus.findByReferralStatusName('Review Submitted')
        referralRecordInstance.save flush: true
        flash.message = "Thank you for reviewing the application"
        redirect(action: 'show', params: [id: referralRecordInstance.id])
    }

    @Transactional
    def update(ReferralRecord referralRecordInstance) {

        if (referralRecordInstance == null) {
            notFound()
            return
        }

        if (referralRecordInstance.hasErrors()) {
            respond referralRecordInstance.errors, view: 'edit'
            return
        }

        referralRecordInstance.save flush: true

        def proband = Patient.findByReferralRecordAndIsProband(referralRecordInstance, true)

        proband.nhsNumber = params.nhsNumberProband
        proband.gender = Gender.findById(params.long('genderProband'))
        proband.ethnicity = Ethnicity.findById(params.long('ethnicityProband'))
        proband.otherEthnicity = params.otherEthnicityProband
        proband.age = params.int('ageProband')
        proband.ageUnit = AgeUnit.findById(params.long('egeUnitProband'))
        proband.givenName = params.givenName
        proband.familyName = params.familyName
        proband.save flush: true

        def oldClinicalDetails = ClinicalDetails.findAllByReferralRecord(referralRecordInstance)
        if (!oldClinicalDetails.empty){
            oldClinicalDetails.each {it.delete flush: true}
        }

        List<ClinicalDetails> clinicalDetailsList = new ArrayList<ClinicalDetails>();
        if (params.cDetails0) {
            clinicalDetailsList.add(new ClinicalDetails(clinicalDetailsName: params.cDetails0))
        }
        if (params.cDetails1) {
            clinicalDetailsList.add(new ClinicalDetails(clinicalDetailsName: params.cDetails1))
        }
        if (params.cDetails2) {
            clinicalDetailsList.add(new ClinicalDetails(clinicalDetailsName: params.cDetails2))
        }
        if (params.cDetails3) {
            clinicalDetailsList.add(new ClinicalDetails(clinicalDetailsName: params.cDetails3))
        }
        if (params.cDetails4) {
            clinicalDetailsList.add(new ClinicalDetails(clinicalDetailsName: params.cDetails4))
        }
        if (params.cDetails5) {
            clinicalDetailsList.add(new ClinicalDetails(clinicalDetailsName: params.cDetails5))
        }
        if (params.cDetails6) {
            clinicalDetailsList.add(new ClinicalDetails(clinicalDetailsName: params.cDetails6))
        }
        if (!clinicalDetailsList.empty) {
            for (int i = 0; i < clinicalDetailsList.size(); i++) {
                referralRecordInstance.addToClinicalDetails(clinicalDetailsList.get(i)).save flush: true
            }
            referralRecordInstance.save flush: true
        }

        def oldCoApplicants = CoApplicant.findAllByReferralRecord(referralRecordInstance)
        if (!oldCoApplicants.empty){
            oldCoApplicants.each {it.delete flush: true}
        }
        List<CoApplicant> coApplicantList = new ArrayList<CoApplicant>();
        if (Clinician.findById(params.long('coapplicant1'))) {
            def clinician = Clinician.findById(params.long('coapplicant1'))
            coApplicantList.add(new CoApplicant(coApplicant: clinician))
        }

        if (Clinician.findById(params.long('coapplicant2'))) {
            def clinician = Clinician.findById(params.long('coapplicant2'))
            coApplicantList.add(new CoApplicant(coApplicant: clinician))
        }

        if (Clinician.findById(params.long('coapplicant3'))) {
            def clinician = Clinician.findById(params.long('coapplicant3'))
            coApplicantList.add(new CoApplicant(coApplicant: clinician))
        }

        if (Clinician.findById(params.long('coapplicant4'))) {
            def clinician = Clinician.findById(params.long('coapplicant4'))
            coApplicantList.add(new CoApplicant(coApplicant: clinician))
        }

        if (Clinician.findById(params.long('coapplicant5'))) {
            def clinician = Clinician.findById(params.long('coapplicant5'))
            coApplicantList.add(new CoApplicant(coApplicant: clinician))
        }

        if (!coApplicantList.empty) {
            for (int i = 0; i < coApplicantList.size(); i++) {
                referralRecordInstance.addToCoApplicants(coApplicantList.get(i)).save flush: true
            }
        }

        def paternal = Paternal.findByReferralRecord(referralRecordInstance)
        paternal.breastAndOrOvarianCancer = FamilyHistoryType.findById(params.long('breastAndOrOvarianCancerPaternal'))
        paternal.colorectalCancer = FamilyHistoryType.findById(params.long('colorectalCancerPaternal'))
        paternal.ischaemicHeartDiseaseOrStroke = FamilyHistoryType.findById(params.long('ischaemicHeartDiseaseOrStrokePaternal'))
        paternal.endocrineTumours = FamilyHistoryType.findById(params.long('endocrineTumoursPaternal'))
        paternal.save flush: true

        def maternal = Maternal.findByReferralRecord(referralRecordInstance)
        maternal.breastAndOrOvarianCancer = FamilyHistoryType.findById(params.long('breastAndOrOvarianCancerMaternal'))
        maternal.colorectalCancer = FamilyHistoryType.findById(params.long('colorectalCancerMaternal'))
        maternal.ischaemicHeartDiseaseOrStroke = FamilyHistoryType.findById(params.long('ischaemicHeartDiseaseOrStrokeMaternal'))
        maternal.endocrineTumours = FamilyHistoryType.findById(params.long('endocrineTumoursMaternal'))
        maternal.save flush: true

        if (params.ethnicityFather) {
            def father = referralRecordInstance?.patients?.find{p -> p?.relatedFrom?.relationshipType == RelationshipType.findByRelationshipTypeName('Father')}
            if (father){
                father.ethnicity = Ethnicity.findById(params.long('ethnicityFather'))
                father.save flush: true
            }else {
                def patient = new Patient(isProband: false, referralRecord: referralRecordInstance, ethnicity: params.ethnicityFather).save flush: true
                new Relationship(relationshipType: RelationshipType.findByRelationshipTypeName('Father'), patient: proband, relatedPatient: patient).save flush: true
            }
        }

        if (params.ethnicityMother) {
            def mother = referralRecordInstance?.patients?.find{p -> p?.relatedFrom?.relationshipType == RelationshipType.findByRelationshipTypeName('Mother')}
            if (mother){
                mother.ethnicity = Ethnicity.findById(params.long('ethnicityMother'))
                mother.save flush: true
            }else {
                def patient = new Patient(isProband: false, referralRecord: referralRecordInstance, ethnicity: params.ethnicityFather).save flush: true
                new Relationship(relationshipType: RelationshipType.findByRelationshipTypeName('Mother'), patient: proband, relatedPatient: patient).save flush: true
            }
        }

        if (Clinician.findById(params.long('correspondingClinician'))) {
            def correspondingClinician = Clinician.findById(params.long('correspondingClinician'))
            referralRecordInstance.correspondingClinician = correspondingClinician
            referralRecordInstance.save flush: true
        } else {
            referralRecordInstance.correspondingClinician = referralRecordInstance.clinician
            referralRecordInstance.save flush: true
        }

        def pedigreeFile = request?.getFile('pedigreeFile')
        if (pedigreeFile?.originalFilename) {
            if (pedigreeFile?.empty) {
                flash.message = "File cannot be empty"
                respond referralRecordInstance.errors, view: 'create'
                return
            }
            referralRecordInstance.pedigree = grailsApplication.config.uploadFolder + 'Pedigree/' +
                    referralRecordInstance.id.toString() + '.' + pedigreeFile.originalFilename
            def destinationFile = new File(referralRecordInstance.pedigree)

            try {
                pedigreeFile.transferTo(destinationFile)
                referralRecordInstance.save flush: true
            }
            catch (Exception ex) {
                log.error(ex)
                referralRecordInstance.pedigree = null
                referralRecordInstance.save flush: true
            }
        }

        if (params.attachedEvidenceFile1 && params.attachedEvidenceType1){
            def attachedEvidenceFile = request.getFile('attachedEvidenceFile1')
            if (!attachedEvidenceFile.originalFilename) {
                flash.message = "Please choose a file"
                respond referralRecordInstance, view: 'create'
            }else{
                def attachedEvidenceInstance = new AttachedEvidence(type: params.attachedEvidenceType1, addedOn: new Date(), referralRecord: referralRecordInstance)
                attachedEvidenceInstance.save flush: true
                attachedEvidenceInstance.content = grailsApplication.config.uploadFolder +'Attached_Evidence/'+ attachedEvidenceInstance.id.toString() + '.' + attachedEvidenceFile.originalFilename
                def destinationFile = new File(attachedEvidenceInstance.content)

                try {
                    attachedEvidenceFile.transferTo(destinationFile)
                    attachedEvidenceInstance.save flush: true
                }
                catch (Exception ex) {
                    log.error(ex)
                }
            }
        }

        if (params.attachedEvidenceFile2 && params.attachedEvidenceType2){
            def attachedEvidenceFile = request.getFile('attachedEvidenceFile2')
            if (!attachedEvidenceFile.originalFilename) {
                flash.message = "Please choose a file"
                respond referralRecordInstance, view: 'create'
            }else{
                def attachedEvidenceInstance = new AttachedEvidence(type: params.attachedEvidenceType2, addedOn: new Date(), referralRecord: referralRecordInstance)
                attachedEvidenceInstance.save flush: true
                attachedEvidenceInstance.content = grailsApplication.config.uploadFolder +'Attached_Evidence/'+ attachedEvidenceInstance.id.toString() + '.' + attachedEvidenceFile.originalFilename
                def destinationFile = new File(attachedEvidenceInstance.content)

                try {
                    attachedEvidenceFile.transferTo(destinationFile)
                    attachedEvidenceInstance.save flush: true
                }
                catch (Exception ex) {
                    log.error(ex)
                }
            }
        }

        if (params.attachedEvidenceFile3 && params.attachedEvidenceType3){
            def attachedEvidenceFile = request.getFile('attachedEvidenceFile3')
            if (!attachedEvidenceFile.originalFilename) {
                flash.message = "Please choose a file"
                respond referralRecordInstance, view: 'create'
            }else{
                def attachedEvidenceInstance = new AttachedEvidence(type: params.attachedEvidenceType3, addedOn: new Date(), referralRecord: referralRecordInstance)
                attachedEvidenceInstance.save flush: true
                attachedEvidenceInstance.content = grailsApplication.config.uploadFolder +'Attached_Evidence/'+ attachedEvidenceInstance.id.toString() + '.' + attachedEvidenceFile.originalFilename
                def destinationFile = new File(attachedEvidenceInstance.content)

                try {
                    attachedEvidenceFile.transferTo(destinationFile)
                    attachedEvidenceInstance.save flush: true
                }
                catch (Exception ex) {
                    log.error(ex)
                }
            }
        }

        if (params.attachedEvidenceFile4 && params.attachedEvidenceType4){
            def attachedEvidenceFile = request.getFile('attachedEvidenceFile4')
            if (!attachedEvidenceFile.originalFilename) {
                flash.message = "Please choose a file"
                respond referralRecordInstance, view: 'create'
            }else{
                def attachedEvidenceInstance = new AttachedEvidence(type: params.attachedEvidenceType4, addedOn: new Date(), referralRecord: referralRecordInstance)
                attachedEvidenceInstance.save flush: true
                attachedEvidenceInstance.content = grailsApplication.config.uploadFolder +'Attached_Evidence/'+ attachedEvidenceInstance.id.toString() + '.' + attachedEvidenceFile.originalFilename
                def destinationFile = new File(attachedEvidenceInstance.content)

                try {
                    attachedEvidenceFile.transferTo(destinationFile)
                    attachedEvidenceInstance.save flush: true
                }
                catch (Exception ex) {
                    log.error(ex)
                }
            }
        }

        if (params.attachedEvidenceFile5 && params.attachedEvidenceType5){
            def attachedEvidenceFile = request.getFile('attachedEvidenceFile5')
            if (!attachedEvidenceFile.originalFilename) {
                flash.message = "Please choose a file"
                respond referralRecordInstance, view: 'create'
            }else{
                def attachedEvidenceInstance = new AttachedEvidence(type: params.attachedEvidenceType5, addedOn: new Date(), referralRecord: referralRecordInstance)
                attachedEvidenceInstance.save flush: true
                attachedEvidenceInstance.content = grailsApplication.config.uploadFolder +'Attached_Evidence/'+ attachedEvidenceInstance.id.toString() + '.' + attachedEvidenceFile.originalFilename
                def destinationFile = new File(attachedEvidenceInstance.content)

                try {
                    attachedEvidenceFile.transferTo(destinationFile)
                    attachedEvidenceInstance.save flush: true
                }
                catch (Exception ex) {
                    log.error(ex)
                }
            }
        }

        flash.message = "Application updated"
        redirect referralRecordInstance
    }

    @Transactional
    def delete(ReferralRecord referralRecordInstance) {

        if (referralRecordInstance == null) {
            notFound()
            return
        }

        def currentUser = springSecurityService?.currentUser?.username
        if (springSecurityService.authentication.authorities.find {it.authority == 'ROLE_ADMIN'}){
            if (referralRecordInstance.pedigree){
                def pedigreeFile = new File(referralRecordInstance.pedigree)
                if (pedigreeFile.exists()){
                    boolean fileDeletedSuccessfully = new File(referralRecordInstance.pedigree).delete()
                    if (fileDeletedSuccessfully){
                        referralRecordInstance.delete flush: true
                        flash.message = "Application has been deleted"
                        redirect action: "index", method: "GET"
                    } else{
                        flash.message = "Could not delete the pedigree file"
                        redirect action: "index", method: "GET"
                    }
                }

            }else {
                referralRecordInstance.delete flush: true
                flash.message = "Application has been deleted"
                redirect action: "index", method: "GET"
            }
        }else {
            def clinician
            if (currentUser?.toString()?.contains('.')){
                def forename = currentUser?.toString()?.split("\\.")[0]
                def surname = currentUser?.toString()?.split("\\.")[1]
                clinician = Clinician.createCriteria().get {
                    and{
                        eq("forename", forename, [ignoreCase: true])
                        eq("surname", surname, [ignoreCase: true])
                    }
                }
            }
            if (clinician){
                if (referralRecordInstance.clinician == clinician || referralRecordInstance.correspondingClinician == clinician){
                    if (referralRecordInstance.pedigree){
                        def pedigreeFile = new File(referralRecordInstance.pedigree)
                        if (pedigreeFile.exists()){
                            boolean fileDeletedSuccessfully = new File(referralRecordInstance.pedigree).delete()
                            if (fileDeletedSuccessfully){
                                referralRecordInstance.delete flush: true
                                flash.message = "Application has been deleted"
                                redirect controller: "index", action: "index"
                            } else{
                                flash.message = "Could not delete the pedigree file"
                                redirect controller: "index", action: "index"
                            }
                        }

                    }else {
                        referralRecordInstance.delete flush: true
                        flash.message = "Application has been deleted"
                        redirect controller: "index", action: "index"
                    }
                }else {
                    flash.message = "You cannot delete this application"
                    redirect(action: 'show', params: [id: referralRecordInstance.id])
                }
            }
        }
    }

    protected void notFound() {
        request.withFormat {
            form {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'referralRecordInstance.label', default: 'ReferralRecord'), params.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NOT_FOUND }
        }
    }
}
