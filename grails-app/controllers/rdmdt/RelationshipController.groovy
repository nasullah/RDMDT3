package rdmdt

import grails.plugin.springsecurity.annotation.Secured

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

/**
 * RelationshipController
 * A controller class handles incoming web requests and performs actions such as redirects, rendering views and so on.
 */
@Secured(['ROLE_USER', 'ROLE_ADMIN'])
@Transactional(readOnly = true)
class RelationshipController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Relationship.list(params), model: [relationshipInstanceCount: Relationship.count()]
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Relationship.list(params), model: [relationshipInstanceCount: Relationship.count()]
    }

    def show(Relationship relationshipInstance) {
        respond relationshipInstance
    }

    def create() {
        respond new Relationship(params)
    }

    @Transactional
    def save(Relationship relationshipInstance) {
        if (relationshipInstance == null) {
            notFound()
            return
        }

        if (relationshipInstance.hasErrors()) {
            respond relationshipInstance.errors, view: 'create'
            return
        }

        relationshipInstance.save flush: true

        request.withFormat {
            form {
                flash.message = message(code: 'default.created.message', args: [message(code: 'relationshipInstance.label', default: 'Relationship'), relationshipInstance.id])
                redirect relationshipInstance
            }
            '*' { respond relationshipInstance, [status: CREATED] }
        }
    }

    def edit(Relationship relationshipInstance) {
        respond relationshipInstance
    }

    @Transactional
    def update(Relationship relationshipInstance) {
        if (relationshipInstance == null) {
            notFound()
            return
        }

        if (relationshipInstance.hasErrors()) {
            respond relationshipInstance.errors, view: 'edit'
            return
        }

        relationshipInstance.save flush: true

        request.withFormat {
            form {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Relationship.label', default: 'Relationship'), relationshipInstance.id])
                redirect relationshipInstance
            }
            '*' { respond relationshipInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(Relationship relationshipInstance) {

        if (relationshipInstance == null) {
            notFound()
            return
        }

        relationshipInstance.delete flush: true

        request.withFormat {
            form {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'Relationship.label', default: 'Relationship'), relationshipInstance.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'relationshipInstance.label', default: 'Relationship'), params.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NOT_FOUND }
        }
    }
}
