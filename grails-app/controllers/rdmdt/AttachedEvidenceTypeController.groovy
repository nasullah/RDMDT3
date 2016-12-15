package rdmdt

import grails.plugin.springsecurity.annotation.Secured

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

/**
 * AttachedEvidenceTypeController
 * A controller class handles incoming web requests and performs actions such as redirects, rendering views and so on.
 */
@Secured(['ROLE_ADMIN'])
@Transactional(readOnly = true)
class AttachedEvidenceTypeController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [attachedEvidenceTypeInstanceList: AttachedEvidenceType.list(params), attachedEvidenceTypeInstanceCount: AttachedEvidenceType.count()]
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [attachedEvidenceTypeInstanceList: AttachedEvidenceType.list(params), attachedEvidenceTypeInstanceCount: AttachedEvidenceType.count()]
    }

    def show(AttachedEvidenceType attachedEvidenceTypeInstance) {
        respond attachedEvidenceTypeInstance: attachedEvidenceTypeInstance
    }

    def create() {
        respond new AttachedEvidenceType(params)
    }

    @Transactional
    def save(AttachedEvidenceType attachedEvidenceTypeInstance) {
        if (attachedEvidenceTypeInstance == null) {
            notFound()
            return
        }

        if (attachedEvidenceTypeInstance.hasErrors()) {
            respond attachedEvidenceTypeInstance.errors, view: 'create'
            return
        }

        attachedEvidenceTypeInstance.save flush: true

        request.withFormat {
            form {
                flash.message = message(code: 'default.created.message', args: [message(code: 'attachedEvidenceTypeInstance.label', default: 'AttachedEvidenceType'), attachedEvidenceTypeInstance.id])
                redirect attachedEvidenceTypeInstance
            }
            '*' { respond attachedEvidenceTypeInstance, [status: CREATED] }
        }
    }

    def edit(AttachedEvidenceType attachedEvidenceTypeInstance) {
        respond attachedEvidenceTypeInstance: attachedEvidenceTypeInstance
    }

    @Transactional
    def update(AttachedEvidenceType attachedEvidenceTypeInstance) {
        if (attachedEvidenceTypeInstance == null) {
            notFound()
            return
        }

        if (attachedEvidenceTypeInstance.hasErrors()) {
            respond attachedEvidenceTypeInstance.errors, view: 'edit'
            return
        }

        attachedEvidenceTypeInstance.save flush: true

        request.withFormat {
            form {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'AttachedEvidenceType.label', default: 'AttachedEvidenceType'), attachedEvidenceTypeInstance.id])
                redirect attachedEvidenceTypeInstance
            }
            '*' { respond attachedEvidenceTypeInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(AttachedEvidenceType attachedEvidenceTypeInstance) {

        if (attachedEvidenceTypeInstance == null) {
            notFound()
            return
        }

        attachedEvidenceTypeInstance.delete flush: true

        request.withFormat {
            form {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'AttachedEvidenceType.label', default: 'AttachedEvidenceType'), attachedEvidenceTypeInstance.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'attachedEvidenceTypeInstance.label', default: 'AttachedEvidenceType'), params.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NOT_FOUND }
        }
    }
}
