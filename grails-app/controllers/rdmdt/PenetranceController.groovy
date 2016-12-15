package rdmdt

import grails.plugin.springsecurity.annotation.Secured

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

/**
 * PenetranceController
 * A controller class handles incoming web requests and performs actions such as redirects, rendering views and so on.
 */
@Secured(['ROLE_ADMIN'])
@Transactional(readOnly = true)
class PenetranceController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [penetranceInstanceList: Penetrance.list(params), penetranceInstanceCount: Penetrance.count()]
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [penetranceInstanceList: Penetrance.list(params), penetranceInstanceCount: Penetrance.count()]
    }

    def show(Penetrance penetranceInstance) {
        respond penetranceInstance: penetranceInstance
    }

    def create() {
        respond new Penetrance(params)
    }

    @Transactional
    def save(Penetrance penetranceInstance) {
        if (penetranceInstance == null) {
            notFound()
            return
        }

        if (penetranceInstance.hasErrors()) {
            respond penetranceInstance.errors, view: 'create'
            return
        }

        penetranceInstance.save flush: true

        request.withFormat {
            form {
                flash.message = message(code: 'default.created.message', args: [message(code: 'penetranceInstance.label', default: 'Penetrance'), penetranceInstance.id])
                redirect penetranceInstance
            }
            '*' { respond penetranceInstance, [status: CREATED] }
        }
    }

    def edit(Penetrance penetranceInstance) {
        respond penetranceInstance: penetranceInstance
    }

    @Transactional
    def update(Penetrance penetranceInstance) {
        if (penetranceInstance == null) {
            notFound()
            return
        }

        if (penetranceInstance.hasErrors()) {
            respond penetranceInstance.errors, view: 'edit'
            return
        }

        penetranceInstance.save flush: true

        request.withFormat {
            form {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Penetrance.label', default: 'Penetrance'), penetranceInstance.id])
                redirect penetranceInstance
            }
            '*' { respond penetranceInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(Penetrance penetranceInstance) {

        if (penetranceInstance == null) {
            notFound()
            return
        }

        penetranceInstance.delete flush: true

        request.withFormat {
            form {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'Penetrance.label', default: 'Penetrance'), penetranceInstance.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'penetranceInstance.label', default: 'Penetrance'), params.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NOT_FOUND }
        }
    }
}
