package rdmdt

import grails.plugin.springsecurity.annotation.Secured

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

/**
 * ConsanguinityController
 * A controller class handles incoming web requests and performs actions such as redirects, rendering views and so on.
 */
@Secured(['ROLE_ADMIN'])
@Transactional(readOnly = true)
class ConsanguinityController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [consanguinityInstanceList: Consanguinity.list(params), consanguinityInstanceCount: Consanguinity.count()]
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [consanguinityInstanceList: Consanguinity.list(params), consanguinityInstanceCount: Consanguinity.count()]
    }

    def show(Consanguinity consanguinityInstance) {
        respond consanguinityInstance: consanguinityInstance
    }

    def create() {
        respond new Consanguinity(params)
    }

    @Transactional
    def save(Consanguinity consanguinityInstance) {
        if (consanguinityInstance == null) {
            notFound()
            return
        }

        if (consanguinityInstance.hasErrors()) {
            respond consanguinityInstance.errors, view: 'create'
            return
        }

        consanguinityInstance.save flush: true

        request.withFormat {
            form {
                flash.message = message(code: 'default.created.message', args: [message(code: 'consanguinityInstance.label', default: 'Consanguinity'), consanguinityInstance.id])
                redirect consanguinityInstance
            }
            '*' { respond consanguinityInstance, [status: CREATED] }
        }
    }

    def edit(Consanguinity consanguinityInstance) {
        respond consanguinityInstance: consanguinityInstance
    }

    @Transactional
    def update(Consanguinity consanguinityInstance) {
        if (consanguinityInstance == null) {
            notFound()
            return
        }

        if (consanguinityInstance.hasErrors()) {
            respond consanguinityInstance.errors, view: 'edit'
            return
        }

        consanguinityInstance.save flush: true

        request.withFormat {
            form {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Consanguinity.label', default: 'Consanguinity'), consanguinityInstance.id])
                redirect consanguinityInstance
            }
            '*' { respond consanguinityInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(Consanguinity consanguinityInstance) {

        if (consanguinityInstance == null) {
            notFound()
            return
        }

        consanguinityInstance.delete flush: true

        request.withFormat {
            form {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'Consanguinity.label', default: 'Consanguinity'), consanguinityInstance.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'consanguinityInstance.label', default: 'Consanguinity'), params.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NOT_FOUND }
        }
    }
}
