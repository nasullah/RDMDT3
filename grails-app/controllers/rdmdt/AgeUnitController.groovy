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
class AgeUnitController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [ageUnitInstanceList: AgeUnit.list(params), ageUnitInstanceCount: AgeUnit.count()]
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [ageUnitInstanceList: AgeUnit.list(params), ageUnitInstanceCount: AgeUnit.count()]
    }

    def show(AgeUnit ageUnitInstance) {
        respond ageUnitInstance: ageUnitInstance
    }

    def create() {
        respond new AgeUnit(params)
    }

    @Transactional
    def save(AgeUnit ageUnitInstance) {
        if (ageUnitInstance == null) {
            notFound()
            return
        }

        if (ageUnitInstance.hasErrors()) {
            respond ageUnitInstance.errors, view: 'create'
            return
        }

        ageUnitInstance.save flush: true

        request.withFormat {
            form {
                flash.message = message(code: 'default.created.message', args: [message(code: 'ageUnitInstance.label', default: 'AgeUnit'), ageUnitInstance.id])
                redirect ageUnitInstance
            }
            '*' { respond ageUnitInstance, [status: CREATED] }
        }
    }

    def edit(AgeUnit ageUnitInstance) {
        respond ageUnitInstance: ageUnitInstance
    }

    @Transactional
    def update(AgeUnit ageUnitInstance) {
        if (ageUnitInstance == null) {
            notFound()
            return
        }

        if (ageUnitInstance.hasErrors()) {
            respond ageUnitInstance.errors, view: 'edit'
            return
        }

        ageUnitInstance.save flush: true

        request.withFormat {
            form {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'AgeUnit.label', default: 'AgeUnit'), ageUnitInstance.id])
                redirect ageUnitInstance
            }
            '*' { respond ageUnitInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(AgeUnit ageUnitInstance) {

        if (ageUnitInstance == null) {
            notFound()
            return
        }

        ageUnitInstance.delete flush: true

        request.withFormat {
            form {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'AgeUnit.label', default: 'AgeUnit'), ageUnitInstance.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'ageUnitInstance.label', default: 'AgeUnit'), params.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NOT_FOUND }
        }
    }
}
