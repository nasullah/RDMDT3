package rdmdt

import grails.converters.JSON
import grails.plugin.springsecurity.annotation.Secured

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

/**
 * ClinicianController
 * A controller class handles incoming web requests and performs actions such as redirects, rendering views and so on.
 */
@Secured(['ROLE_USER', 'ROLE_ADMIN'])
@Transactional(readOnly = true)
class ClinicianController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [clinicianInstanceList: Clinician.list(params), clinicianInstanceCount: Clinician.count()]
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [clinicianInstanceList: Clinician.list(params), clinicianInstanceCount: Clinician.count()]
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
