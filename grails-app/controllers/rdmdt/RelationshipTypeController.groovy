package rdmdt

import grails.plugin.springsecurity.annotation.Secured

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

/**
 * RelationshipTypeController
 * A controller class handles incoming web requests and performs actions such as redirects, rendering views and so on.
 */
@Secured(['ROLE_ADMIN'])
@Transactional(readOnly = true)
class RelationshipTypeController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [relationshipTypeInstanceList:RelationshipType.list(params), relationshipTypeInstanceCount: RelationshipType.count()]
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [relationshipTypeInstanceList:RelationshipType.list(params), relationshipTypeInstanceCount: RelationshipType.count()]
    }

    def show(RelationshipType relationshipTypeInstance) {
        respond relationshipTypeInstance: relationshipTypeInstance
    }

    def create() {
        respond new RelationshipType(params)
    }

    @Transactional
    def save(RelationshipType relationshipTypeInstance) {
        if (relationshipTypeInstance == null) {
            notFound()
            return
        }

        if (relationshipTypeInstance.hasErrors()) {
            respond relationshipTypeInstance.errors, view: 'create'
            return
        }

        relationshipTypeInstance.save flush: true

        request.withFormat {
            form {
                flash.message = message(code: 'default.created.message', args: [message(code: 'relationshipTypeInstance.label', default: 'RelationshipType'), relationshipTypeInstance.id])
                redirect relationshipTypeInstance
            }
            '*' { respond relationshipTypeInstance, [status: CREATED] }
        }
    }

    def edit(RelationshipType relationshipTypeInstance) {
        respond relationshipTypeInstance: relationshipTypeInstance
    }

    @Transactional
    def update(RelationshipType relationshipTypeInstance) {
        if (relationshipTypeInstance == null) {
            notFound()
            return
        }

        if (relationshipTypeInstance.hasErrors()) {
            respond relationshipTypeInstance.errors, view: 'edit'
            return
        }

        relationshipTypeInstance.save flush: true

        request.withFormat {
            form {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'RelationshipType.label', default: 'RelationshipType'), relationshipTypeInstance.id])
                redirect relationshipTypeInstance
            }
            '*' { respond relationshipTypeInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(RelationshipType relationshipTypeInstance) {

        if (relationshipTypeInstance == null) {
            notFound()
            return
        }

        relationshipTypeInstance.delete flush: true

        request.withFormat {
            form {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'RelationshipType.label', default: 'RelationshipType'), relationshipTypeInstance.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'relationshipTypeInstance.label', default: 'RelationshipType'), params.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NOT_FOUND }
        }
    }
}
