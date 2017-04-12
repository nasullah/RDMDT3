package rdmdt

import grails.converters.JSON
import grails.plugin.springsecurity.annotation.Secured

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional
import org.grails.plugins.filterpane.FilterPaneUtils

/**
 * ClinicianController
 * A controller class handles incoming web requests and performs actions such as redirects, rendering views and so on.
 */
@Secured(['ROLE_USER', 'ROLE_ADMIN'])
@Transactional(readOnly = true)
class ClinicianController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def filterPaneService
    def exportService

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [clinicianInstanceList: Clinician.list(params), clinicianInstanceCount: Clinician.count()]
    }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [clinicianInstanceList: Clinician.list(params), clinicianInstanceTotal: Clinician.count()]
    }

    def filter = {
        if(!params.max) params.max = 10
        render( view:'list', model:[ clinicianInstanceList: filterPaneService.filter( params, Clinician ),
                                     clinicianInstanceTotal: filterPaneService.count( params, Clinician ),
                                     filterParams: FilterPaneUtils.extractFilterParams(params), params:params ] )
    }

    @Secured(['ROLE_ADMIN'])
    def exportOptions(){
        if(params?.format && params.format != "html"){
            response.contentType = grailsApplication.config.grails.mime.types[params.format]
            response.setHeader("Content-disposition", "attachment; filename=Applications.${params.extension}")
            def month= params.month
            def year= params.year
            def referralRecordList

            if (month && year){
                if (month.toString().size() == 1){
                    month = '0' + month.toString()
                }
                def startDate = new Date()
                def paramsStartDate = year.toString() + '-' + month.toString() + '-' +'01'
                startDate = startDate.parse("yyyy-MM-dd", paramsStartDate)
                def endDate =new Date()
                def paramsEndDate = year.toString() + '-' + month.toString() + '-' +'31'
                endDate = endDate.parse("yyyy-MM-dd", paramsEndDate)
                referralRecordList = ReferralRecord.createCriteria().list {
                    and {
                        le("meetingDate", endDate)
                        ge("meetingDate", startDate)
                    }
                }?.sort{it?.meetingDate}
            }else if (params.clinician){
                def clinician = Clinician.findById(params.long("clinician"))
                if (clinician){
                    referralRecordList = ReferralRecord.findAllByClinician(clinician).sort{it?.clinician?.surname}
                }
            }else if(params.department){
                def department = Department.findById(params.long("department"))
                def clinicianList = Clinician.findAllByDepartmentName(department)
                if (!clinicianList.empty){
                    referralRecordList = ReferralRecord.findAllByClinicianInList(clinicianList).sort{it?.clinician?.departmentName?.departmentName}
                }
            }else {
                referralRecordList = ReferralRecord.list().sort{it?.id}
            }

            List fields = ["clinician", "clinician.departmentName", "meetingDate", "uniqueRef", "Proband First Name", "Proband Last Name", "referralStatus"]
            Map labels = ["clinician":"Clinician", "uniqueRef":"Unique Ref", "clinician.departmentName":"Referral Department", "meetingDate":"Meeting Date",
                          "referralStatus":"Application Status"]

            def probandFirstName = { domain, value ->
                def givenName = Patient.findByReferralRecordAndIsProband(domain, true)?.givenName
                return givenName
            }

            def probandLastName = { domain, value ->
                def familyName = Patient.findByReferralRecordAndIsProband(domain, true)?.familyName
                return familyName
            }

            def meetingDate= { domain, value ->
                if (value){
                    value = value.toString().substring(0,10)
                    return value
                }
            }

            Map formatters = ["Proband First Name": probandFirstName, "Proband Last Name":probandLastName, "meetingDate":meetingDate]
            Map parameters = [title: "Application data", "column.widths": [0.25, 0.2, 0.15, 0.2, 0.2, 0.2, 0.2]]
            exportService.export(params.format, response.outputStream, referralRecordList, fields, labels, formatters, parameters)
        }
    }

    def show(Clinician clinicianInstance) {
        respond clinicianInstance: clinicianInstance
    }

    def create() {
        respond new Clinician(params)
    }

    def findClinician(){
        def listClinician = Clinician.createCriteria().listDistinct{
            or {
                ilike("forename", "%${params.query}%")
                ilike("surname", "%${params.query}%")
            }
        }

        withFormat {
            xml {
                render(contentType: "text/xml") {
                    results() {
                        listClinician.each { clinician ->
                            result(){
                                name(clinician)
                                //Optional id which will be available in onItemSelect
                                id(clinician.id)
                            }
                        }
                    }
                }
            }
            json {
                def results = listClinician.collect {
                    ['label':it.toString(), 'value':it.id]
                }
                render results as JSON
            }
        }

    }

    @Transactional
    def save(Clinician clinicianInstance) {
        if (clinicianInstance == null) {
            notFound()
            return
        }

        if (clinicianInstance.hasErrors()) {
            respond clinicianInstance.errors, view: 'create'
            return
        }

        clinicianInstance.save flush: true

        request.withFormat {
            form {
                flash.message = message(code: 'default.created.message', args: [message(code: 'clinicianInstance.label', default: 'Clinician'), clinicianInstance.id])
                redirect clinicianInstance
            }
            '*' { respond clinicianInstance, [status: CREATED] }
        }
    }

    def edit(Clinician clinicianInstance) {
        respond clinicianInstance: clinicianInstance
    }

    @Transactional
    def update(Clinician clinicianInstance) {
        if (clinicianInstance == null) {
            notFound()
            return
        }

        if (clinicianInstance.hasErrors()) {
            respond clinicianInstance.errors, view: 'edit'
            return
        }

        clinicianInstance.save flush: true
        flash.message = "Your profile has been updated"
        redirect clinicianInstance
    }

    @Transactional
    def delete(Clinician clinicianInstance) {

        if (clinicianInstance == null) {
            notFound()
            return
        }

        clinicianInstance.delete flush: true

        request.withFormat {
            form {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'Clinician.label', default: 'Clinician'), clinicianInstance.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'clinicianInstance.label', default: 'Clinician'), params.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NOT_FOUND }
        }
    }
}
