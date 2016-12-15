package rdmdt

import grails.plugin.springsecurity.annotation.Secured

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

/**
 * FamilyHistoryTypeController
 * A controller class handles incoming web requests and performs actions such as redirects, rendering views and so on.
 */
@Secured(['ROLE_ADMIN'])
@Transactional(readOnly = true)
class FamilyHistoryTypeController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [familyHistoryTypeInstanceList: FamilyHistoryType.list(params), familyHistoryTypeInstanceCount: FamilyHistoryType.count()]
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [familyHistoryTypeInstanceList: FamilyHistoryType.list(params), familyHistoryTypeInstanceCount: FamilyHistoryType.count()]
    }

    def show(FamilyHistoryType familyHistoryTypeInstance) {
        respond familyHistoryTypeInstance: familyHistoryTypeInstance
    }

    def create() {
        respond new FamilyHistoryType(params)
    }

    @Transactional
    def save(FamilyHistoryType familyHistoryTypeInstance) {
        if (familyHistoryTypeInstance == null) {
            notFound()
            return
        }

        if (familyHistoryTypeInstance.hasErrors()) {
            respond familyHistoryTypeInstance.errors, view: 'create'
            return
        }

        familyHistoryTypeInstance.save flush: true

        request.withFormat {
            form {
                flash.message = message(code: 'default.created.message', args: [message(code: 'familyHistoryTypeInstance.label', default: 'FamilyHistoryType'), familyHistoryTypeInstance.id])
                redirect familyHistoryTypeInstance
            }
            '*' { respond familyHistoryTypeInstance, [status: CREATED] }
        }
    }

    def edit(FamilyHistoryType familyHistoryTypeInstance) {
        respond familyHistoryTypeInstance: familyHistoryTypeInstance
    }

    @Transactional
    def update(FamilyHistoryType familyHistoryTypeInstance) {
        if (familyHistoryTypeInstance == null) {
            notFound()
            return
        }

        if (familyHistoryTypeInstance.hasErrors()) {
            respond familyHistoryTypeInstance.errors, view: 'edit'
            return
        }

        familyHistoryTypeInstance.save flush: true

        request.withFormat {
            form {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'FamilyHistoryType.label', default: 'FamilyHistoryType'), familyHistoryTypeInstance.id])
                redirect familyHistoryTypeInstance
            }
            '*' { respond familyHistoryTypeInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(FamilyHistoryType familyHistoryTypeInstance) {

        if (familyHistoryTypeInstance == null) {
            notFound()
            return
        }

        familyHistoryTypeInstance.delete flush: true

        request.withFormat {
            form {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'FamilyHistoryType.label', default: 'FamilyHistoryType'), familyHistoryTypeInstance.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'familyHistoryTypeInstance.label', default: 'FamilyHistoryType'), params.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NOT_FOUND }
        }
    }
}
