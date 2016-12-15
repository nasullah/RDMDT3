package rdmdt

import grails.plugin.springsecurity.annotation.Secured

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

/**
 * CentreController
 * A controller class handles incoming web requests and performs actions such as redirects, rendering views and so on.
 */
@Secured(['ROLE_ADMIN'])
@Transactional(readOnly = true)
class CentreController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [centreInstanceList: Centre.list(params), centreInstanceCount: Centre.count()]
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [centreInstanceList: Centre.list(params), centreInstanceCount: Centre.count()]
    }

    def show(Centre centreInstance) {
        respond centreInstance: centreInstance
    }

    def create() {
        respond new Centre(params)
    }

    @Transactional
    def save(Centre centreInstance) {
        if (centreInstance == null) {
            notFound()
            return
        }

        if (centreInstance.hasErrors()) {
            respond centreInstance.errors, view: 'create'
            return
        }

        centreInstance.save flush: true

        request.withFormat {
            form {
                flash.message = message(code: 'default.created.message', args: [message(code: 'centreInstance.label', default: 'Centre'), centreInstance.id])
                redirect centreInstance
            }
            '*' { respond centreInstance, [status: CREATED] }
        }
    }

    def edit(Centre centreInstance) {
        respond centreInstance: centreInstance
    }

    @Transactional
    def update(Centre centreInstance) {
        if (centreInstance == null) {
            notFound()
            return
        }

        if (centreInstance.hasErrors()) {
            respond centreInstance.errors, view: 'edit'
            return
        }

        centreInstance.save flush: true

        request.withFormat {
            form {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Centre.label', default: 'Centre'), centreInstance.id])
                redirect centreInstance
            }
            '*' { respond centreInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(Centre centreInstance) {

        if (centreInstance == null) {
            notFound()
            return
        }

        centreInstance.delete flush: true

        request.withFormat {
            form {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'Centre.label', default: 'Centre'), centreInstance.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'centreInstance.label', default: 'Centre'), params.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NOT_FOUND }
        }
    }
}
