package rdmdt

import grails.plugin.springsecurity.annotation.Secured

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

/**
 * AgeUnitController
 * A controller class handles incoming web requests and performs actions such as redirects, rendering views and so on.
 */
@Secured(['ROLE_ADMIN'])
@Transactional(readOnly = true)

class YesNoUnknownController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [yesNoUnknownInstanceList: YesNoUnknown.list(params), yesNoUnknownInstanceCount: YesNoUnknown.count()]
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [yesNoUnknownInstanceList: YesNoUnknown.list(params), yesNoUnknownInstanceCount: YesNoUnknown.count()]
    }

    def show(YesNoUnknown yesNoUnknownInstance) {
        respond yesNoUnknownInstance: yesNoUnknownInstance
    }

    def create() {
        respond new YesNoUnknown(params)
    }

    @Transactional
    def save(YesNoUnknown yesNoUnknownInstance) {
        if (yesNoUnknownInstance == null) {
            notFound()
            return
        }

        if (yesNoUnknownInstance.hasErrors()) {
            respond yesNoUnknownInstance.errors, view: 'create'
            return
        }

        yesNoUnknownInstance.save flush: true

        request.withFormat {
            form {
                flash.message = message(code: 'default.created.message', args: [message(code: 'YesNoUnknown.label', default: 'YesNoUnknown'), yesNoUnknownInstance.id])
                redirect yesNoUnknownInstance
            }
            '*' { respond yesNoUnknownInstance, [status: CREATED] }
        }
    }

    def edit(YesNoUnknown yesNoUnknownInstance) {
        respond yesNoUnknownInstance: yesNoUnknownInstance
    }

    @Transactional
    def update(YesNoUnknown yesNoUnknownInstance) {
        if (yesNoUnknownInstance == null) {
            notFound()
            return
        }

        if (yesNoUnknownInstance.hasErrors()) {
            respond yesNoUnknownInstance.errors, view: 'edit'
            return
        }

        yesNoUnknownInstance.save flush: true

        request.withFormat {
            form {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'YesNoUnknown.label', default: 'YesNoUnknown'), yesNoUnknownInstance.id])
                redirect yesNoUnknownInstance
            }
            '*' { respond yesNoUnknownInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(YesNoUnknown yesNoUnknownInstance) {

        if (yesNoUnknownInstance == null) {
            notFound()
            return
        }

        yesNoUnknownInstance.delete flush: true

        request.withFormat {
            form {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'YesNoUnknown.label', default: 'YesNoUnknown'), yesNoUnknownInstance.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'yesNoUnknownInstance.label', default: 'YesNoUnknown'), params.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NOT_FOUND }
        }
    }
}
