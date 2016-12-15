package rdmdt

import grails.plugin.springsecurity.annotation.Secured

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

/**
 * EligibilityTypeController
 * A controller class handles incoming web requests and performs actions such as redirects, rendering views and so on.
 */
@Secured(['ROLE_ADMIN'])
@Transactional(readOnly = true)
class EligibilityTypeController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [eligibilityTypeInstanceList: EligibilityType.list(params), eligibilityTypeInstanceCount: EligibilityType.count()]
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [eligibilityTypeInstanceList: EligibilityType.list(params), eligibilityTypeInstanceCount: EligibilityType.count()]
    }

    def show(EligibilityType eligibilityTypeInstance) {
        respond eligibilityTypeInstance: eligibilityTypeInstance
    }

    def create() {
        respond new EligibilityType(params)
    }

    @Transactional
    def save(EligibilityType eligibilityTypeInstance) {
        if (eligibilityTypeInstance == null) {
            notFound()
            return
        }

        if (eligibilityTypeInstance.hasErrors()) {
            respond eligibilityTypeInstance.errors, view: 'create'
            return
        }

        eligibilityTypeInstance.save flush: true

        request.withFormat {
            form {
                flash.message = message(code: 'default.created.message', args: [message(code: 'eligibilityTypeInstance.label', default: 'EligibilityType'), eligibilityTypeInstance.id])
                redirect eligibilityTypeInstance
            }
            '*' { respond eligibilityTypeInstance, [status: CREATED] }
        }
    }

    def edit(EligibilityType eligibilityTypeInstance) {
        respond eligibilityTypeInstance: eligibilityTypeInstance
    }

    @Transactional
    def update(EligibilityType eligibilityTypeInstance) {
        if (eligibilityTypeInstance == null) {
            notFound()
            return
        }

        if (eligibilityTypeInstance.hasErrors()) {
            respond eligibilityTypeInstance.errors, view: 'edit'
            return
        }

        eligibilityTypeInstance.save flush: true

        request.withFormat {
            form {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'EligibilityType.label', default: 'EligibilityType'), eligibilityTypeInstance.id])
                redirect eligibilityTypeInstance
            }
            '*' { respond eligibilityTypeInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(EligibilityType eligibilityTypeInstance) {

        if (eligibilityTypeInstance == null) {
            notFound()
            return
        }

        eligibilityTypeInstance.delete flush: true

        request.withFormat {
            form {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'EligibilityType.label', default: 'EligibilityType'), eligibilityTypeInstance.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'eligibilityTypeInstance.label', default: 'EligibilityType'), params.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NOT_FOUND }
        }
    }
}
