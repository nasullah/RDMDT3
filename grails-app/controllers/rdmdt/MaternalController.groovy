package rdmdt

import grails.plugin.springsecurity.annotation.Secured

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

/**
 * MaternalController
 * A controller class handles incoming web requests and performs actions such as redirects, rendering views and so on.
 */
@Secured(['ROLE_USER', 'ROLE_ADMIN'])
@Transactional(readOnly = true)
class MaternalController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Maternal.list(params), model: [maternalInstanceCount: Maternal.count()]
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Maternal.list(params), model: [maternalInstanceCount: Maternal.count()]
    }

    def show(Maternal maternalInstance) {
        respond maternalInstance
    }

    def create() {
        respond new Maternal(params)
    }

    @Transactional
    def save(Maternal maternalInstance) {
        if (maternalInstance == null) {
            notFound()
            return
        }

        if (maternalInstance.hasErrors()) {
            respond maternalInstance.errors, view: 'create'
            return
        }

        maternalInstance.save flush: true

        request.withFormat {
            form {
                flash.message = message(code: 'default.created.message', args: [message(code: 'maternalInstance.label', default: 'Maternal'), maternalInstance.id])
                redirect maternalInstance
            }
            '*' { respond maternalInstance, [status: CREATED] }
        }
    }

    def edit(Maternal maternalInstance) {
        respond maternalInstance
    }

    @Transactional
    def update(Maternal maternalInstance) {
        if (maternalInstance == null) {
            notFound()
            return
        }

        if (maternalInstance.hasErrors()) {
            respond maternalInstance.errors, view: 'edit'
            return
        }

        maternalInstance.save flush: true

        request.withFormat {
            form {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Maternal.label', default: 'Maternal'), maternalInstance.id])
                redirect maternalInstance
            }
            '*' { respond maternalInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(Maternal maternalInstance) {

        if (maternalInstance == null) {
            notFound()
            return
        }

        maternalInstance.delete flush: true

        request.withFormat {
            form {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'Maternal.label', default: 'Maternal'), maternalInstance.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'maternalInstance.label', default: 'Maternal'), params.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NOT_FOUND }
        }
    }
}
