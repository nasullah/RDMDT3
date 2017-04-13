package rdmdt

import grails.plugin.springsecurity.annotation.Secured

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Secured(['ROLE_USER', 'ROLE_ADMIN'])
@Transactional(readOnly = true)
class RareDiseasesPhenotypeReportController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond RareDiseasesPhenotypeReport.list(params), model: [rareDiseasesPhenotypeReportInstanceCount: RareDiseasesPhenotypeReport.count()]
    }

    def show(RareDiseasesPhenotypeReport rareDiseasesPhenotypeReportInstance) {
        respond rareDiseasesPhenotypeReportInstance: rareDiseasesPhenotypeReportInstance
    }

    def create() {
        def rareDiseasesPhenotypeReportInstance = new RareDiseasesPhenotypeReport(params)
        def specificDisorderId = params.specificDisorderId
        def diseaseGroup = SpecificDisorders.findByOriginalId(specificDisorderId)?.subGroups?.diseaseGroups
        def diseaseSubGroup = SpecificDisorders.findByOriginalId(specificDisorderId)?.subGroups
        def specificDisease = SpecificDisorders.findByOriginalId(specificDisorderId)
        [rareDiseasesPhenotypeReportInstance: rareDiseasesPhenotypeReportInstance, diseaseGroup: diseaseGroup, diseaseSubGroup:diseaseSubGroup, specificDisease:specificDisease]
    }

    def test(){
        def rareDiseasesPhenotypeReportInstanceList = ShallowPhenotypes.findAllBySpecificDisorders(SpecificDisorders.get(1))
        [rareDiseasesPhenotypeReportInstanceList:rareDiseasesPhenotypeReportInstanceList]
    }

    @Transactional
    def save(){
        def referralRecord = ReferralRecord.findById(params.long('referralRecord'))
        def rareDiseasesPhenotypeReportInstance = new RareDiseasesPhenotypeReport(referralRecord: referralRecord).save flush: true
        def statementNumber = SpecificDisorders.findByOriginalId(referralRecord?.approvedTargetCategory?.originalId?.toString())?.shallowPhenotypes?.size()
        for(int i = 0; i < statementNumber; i++){
            def shallowPhenotypeIdentifier = params.find{it.key.equals('shallowPhenotypeIdentifier_'+ i)}?.value
            def shallowPhenotypeHPOBuildNumber = params.find{it.key.equals('shallowPhenotypeHPOBuildNumber_'+ i)}?.value
            def shallowPhenotypePresent = YesNoUnknown.findById(params.find{it.key.equals('shallowPhenotypePresent_'+ i)}?.value?.toString()?.toLong())
            def shallowPhenotypeDescription = params.find{it.key.equals('shallowPhenotypeDescription_'+ i)}?.value
            new Statement(identifier:shallowPhenotypeIdentifier, hpoBuildNumber:shallowPhenotypeHPOBuildNumber,
                    present:shallowPhenotypePresent, description:shallowPhenotypeDescription, phenotyping:rareDiseasesPhenotypeReportInstance).save flush: true
        }
        flash.message = "Phenotype report has been created for application with Unique Ref ${referralRecord.uniqueRef}"
        redirect(controller:'referralRecord',action: 'show', params: [id: rareDiseasesPhenotypeReportInstance.referralRecord.id])
    }

    @Transactional
    def updateRecord(){
        def rareDiseasesPhenotypeReportInstance = RareDiseasesPhenotypeReport.findById(params.long('rareDiseasesPhenotypeReportInstance'))
        def statementNumber = SpecificDisorders.findByOriginalId(rareDiseasesPhenotypeReportInstance.referralRecord?.approvedTargetCategory?.originalId?.toString())?.shallowPhenotypes?.size()
        for(int i = 0; i < statementNumber; i++){
            def shallowPhenotypeIdentifier = params.find{it.key.equals('shallowPhenotypeIdentifier_'+ i)}?.value
            def shallowPhenotypeHPOBuildNumber = params.find{it.key.equals('shallowPhenotypeHPOBuildNumber_'+ i)}?.value
            def shallowPhenotypePresent = YesNoUnknown.findById(params.find{it.key.equals('shallowPhenotypePresent_'+ i)}?.value?.toString()?.toLong())
            def shallowPhenotypeDescription = params.find{it.key.equals('shallowPhenotypeDescription_'+ i)}?.value
            def statementInstance = Statement.findByIdentifier(shallowPhenotypeIdentifier?.toString())
            if (statementInstance){
                statementInstance.identifier = shallowPhenotypeIdentifier
                statementInstance.hpoBuildNumber = shallowPhenotypeHPOBuildNumber
                statementInstance.present = shallowPhenotypePresent
                statementInstance.description = shallowPhenotypeDescription
                statementInstance.save flush:true
            }
        }
        flash.message = "Phenotype report has been update for application with Unique Ref ${rareDiseasesPhenotypeReportInstance.referralRecord.uniqueRef}"
        redirect(controller:'referralRecord',action: 'show', params: [id: rareDiseasesPhenotypeReportInstance.referralRecord.id])
    }

    def edit(RareDiseasesPhenotypeReport rareDiseasesPhenotypeReportInstance) {
        respond rareDiseasesPhenotypeReportInstance: rareDiseasesPhenotypeReportInstance
    }

    @Transactional
    def delete(RareDiseasesPhenotypeReport rareDiseasesPhenotypeReportInstance) {

        if (rareDiseasesPhenotypeReportInstance == null) {
            notFound()
            return
        }

        rareDiseasesPhenotypeReportInstance.delete flush: true

        request.withFormat {
            form multipartForm {
                flash.message = "Phenotype report has been deleted for application with Unique Ref ${rareDiseasesPhenotypeReportInstance.referralRecord.uniqueRef}"
                redirect(controller:'referralRecord',action: 'show', params: [id: rareDiseasesPhenotypeReportInstance.referralRecord.id])
            }
            '*' { render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'rareDiseasesPhenotypeReport.label', default: 'RareDiseasesPhenotypeReport'), params.id])
                redirect(controller:'referralRecord',action: 'show', params: [id: rareDiseasesPhenotypeReportInstance.referralRecord.id])
            }
            '*' { render status: NOT_FOUND }
        }
    }
}