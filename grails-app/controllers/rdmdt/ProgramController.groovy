package rdmdt

import grails.plugin.springsecurity.annotation.Secured

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

/**
 * ProgramController
 * A controller class handles incoming web requests and performs actions such as redirects, rendering views and so on.
 */
@Secured(['ROLE_ADMIN'])
@Transactional(readOnly = true)
class ProgramController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [programInstanceList: Program.list(params), programInstanceCount: Program.count()]
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [programInstanceList: Program.list(params), programInstanceCount: Program.count()]
    }

    def show(Program programInstance) {
        respond programInstance: programInstance
    }

    def create() {
        respond new Program(params)
    }

    @Transactional
    def save(Program programInstance) {
        if (programInstance == null) {
            notFound()
            return
        }

        if (programInstance.hasErrors()) {
            respond programInstance.errors, view: 'create'
            return
        }

        programInstance.save flush: true

        request.withFormat {
            form {
                flash.message = message(code: 'default.created.message', args: [message(code: 'programInstance.label', default: 'Program'), programInstance.id])
                redirect programInstance
            }
            '*' { respond programInstance, [status: CREATED] }
        }
    }

    def edit(Program programInstance) {
        respond programInstance: programInstance
    }

    @Transactional
    def update(Program programInstance) {
        if (programInstance == null) {
            notFound()
            return
        }

        if (programInstance.hasErrors()) {
            respond programInstance.errors, view: 'edit'
            return
        }

        programInstance.save flush: true

        request.withFormat {
            form {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Program.label', default: 'Program'), programInstance.id])
                redirect programInstance
            }
            '*' { respond programInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(Program programInstance) {

        if (programInstance == null) {
            notFound()
            return
        }

        programInstance.delete flush: true

        request.withFormat {
            form {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'Program.label', default: 'Program'), programInstance.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'programInstance.label', default: 'Program'), params.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NOT_FOUND }
        }
    }
}
