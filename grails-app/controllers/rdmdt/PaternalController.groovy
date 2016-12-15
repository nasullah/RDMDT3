package rdmdt

import grails.plugin.springsecurity.annotation.Secured

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

/**
 * PaternalController
 * A controller class handles incoming web requests and performs actions such as redirects, rendering views and so on.
 */
@Secured(['ROLE_USER', 'ROLE_ADMIN'])
@Transactional(readOnly = true)
class PaternalController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Paternal.list(params), model: [paternalInstanceCount: Paternal.count()]
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Paternal.list(params), model: [paternalInstanceCount: Paternal.count()]
    }

    def show(Paternal paternalInstance) {
        respond paternalInstance
    }

    def create() {
        respond new Paternal(params)
    }

    @Transactional
    def save(Paternal paternalInstance) {
        print(params)
        if (paternalInstance == null) {
            notFound()
            return
        }

        if (paternalInstance.hasErrors()) {
            respond paternalInstance.errors, view: 'create'
            return
        }

        paternalInstance.save flush: true

        request.withFormat {
            form {
                flash.message = message(code: 'default.created.message', args: [message(code: 'paternalInstance.label', default: 'Paternal'), paternalInstance.id])
                redirect paternalInstance
            }
            '*' { respond paternalInstance, [status: CREATED] }
        }
    }

    def edit(Paternal paternalInstance) {
        respond paternalInstance
    }

    @Transactional
    def update(Paternal paternalInstance) {
        if (paternalInstance == null) {
            notFound()
            return
        }

        if (paternalInstance.hasErrors()) {
            respond paternalInstance.errors, view: 'edit'
            return
        }

        paternalInstance.save flush: true

        request.withFormat {
            form {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Paternal.label', default: 'Paternal'), paternalInstance.id])
                redirect paternalInstance
            }
            '*' { respond paternalInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(Paternal paternalInstance) {

        if (paternalInstance == null) {
            notFound()
            return
        }

        paternalInstance.delete flush: true

        request.withFormat {
            form {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'Paternal.label', default: 'Paternal'), paternalInstance.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'paternalInstance.label', default: 'Paternal'), params.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NOT_FOUND }
        }
    }
}
