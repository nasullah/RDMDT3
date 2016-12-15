package rdmdt

import grails.plugin.springsecurity.annotation.Secured

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

/**
 * FamilyMembersAffectedTypeController
 * A controller class handles incoming web requests and performs actions such as redirects, rendering views and so on.
 */
@Secured(['ROLE_ADMIN'])
@Transactional(readOnly = true)
class FamilyMembersAffectedTypeController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [familyMembersAffectedTypeInstanceList: FamilyMembersAffectedType.list(params), familyMembersAffectedTypeInstanceCount: FamilyMembersAffectedType.count()]
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [familyMembersAffectedTypeInstanceList: FamilyMembersAffectedType.list(params), familyMembersAffectedTypeInstanceCount: FamilyMembersAffectedType.count()]
    }

    def show(FamilyMembersAffectedType familyMembersAffectedTypeInstance) {
        respond familyMembersAffectedTypeInstance: familyMembersAffectedTypeInstance
    }

    def create() {
        respond new FamilyMembersAffectedType(params)
    }

    @Transactional
    def save(FamilyMembersAffectedType familyMembersAffectedTypeInstance) {
        if (familyMembersAffectedTypeInstance == null) {
            notFound()
            return
        }

        if (familyMembersAffectedTypeInstance.hasErrors()) {
            respond familyMembersAffectedTypeInstance.errors, view: 'create'
            return
        }

        familyMembersAffectedTypeInstance.save flush: true

        request.withFormat {
            form {
                flash.message = message(code: 'default.created.message', args: [message(code: 'familyMembersAffectedTypeInstance.label', default: 'FamilyMembersAffectedType'), familyMembersAffectedTypeInstance.id])
                redirect familyMembersAffectedTypeInstance
            }
            '*' { respond familyMembersAffectedTypeInstance, [status: CREATED] }
        }
    }

    def edit(FamilyMembersAffectedType familyMembersAffectedTypeInstance) {
        respond familyMembersAffectedTypeInstance: familyMembersAffectedTypeInstance
    }

    @Transactional
    def update(FamilyMembersAffectedType familyMembersAffectedTypeInstance) {
        if (familyMembersAffectedTypeInstance == null) {
            notFound()
            return
        }

        if (familyMembersAffectedTypeInstance.hasErrors()) {
            respond familyMembersAffectedTypeInstance.errors, view: 'edit'
            return
        }

        familyMembersAffectedTypeInstance.save flush: true

        request.withFormat {
            form {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'FamilyMembersAffectedType.label', default: 'FamilyMembersAffectedType'), familyMembersAffectedTypeInstance.id])
                redirect familyMembersAffectedTypeInstance
            }
            '*' { respond familyMembersAffectedTypeInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(FamilyMembersAffectedType familyMembersAffectedTypeInstance) {

        if (familyMembersAffectedTypeInstance == null) {
            notFound()
            return
        }

        familyMembersAffectedTypeInstance.delete flush: true

        request.withFormat {
            form {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'FamilyMembersAffectedType.label', default: 'FamilyMembersAffectedType'), familyMembersAffectedTypeInstance.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'familyMembersAffectedTypeInstance.label', default: 'FamilyMembersAffectedType'), params.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NOT_FOUND }
        }
    }
}
