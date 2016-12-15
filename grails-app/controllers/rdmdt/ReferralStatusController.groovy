package rdmdt

import grails.plugin.springsecurity.annotation.Secured

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

/**
 * ReferralStatusController
 * A controller class handles incoming web requests and performs actions such as redirects, rendering views and so on.
 */
@Secured(['ROLE_ADMIN'])
@Transactional(readOnly = true)
class ReferralStatusController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [referralStatusInstanceList: ReferralStatus.list(params), referralStatusInstanceCount: ReferralStatus.count()]
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [referralStatusInstanceList: ReferralStatus.list(params), referralStatusInstanceCount: ReferralStatus.count()]
    }

    def show(ReferralStatus referralStatusInstance) {
        respond referralStatusInstance:referralStatusInstance
    }

    def create() {
        respond new ReferralStatus(params)
    }

    @Transactional
    def save(ReferralStatus referralStatusInstance) {

        if (referralStatusInstance == null) {
            notFound()
            return
        }

        if (referralStatusInstance.hasErrors()) {
            respond referralStatusInstance.errors, view: 'create'
            return
        }

        referralStatusInstance.save flush: true

        request.withFormat {
            form {
                flash.message = message(code: 'default.created.message', args: [message(code: 'referralStatusInstance.label', default: 'ReferralStatus'), referralStatusInstance.id])
                redirect referralStatusInstance
            }
            '*' { respond referralStatusInstance, [status: CREATED] }
        }
    }

    def edit(ReferralStatus referralStatusInstance) {
        respond referralStatusInstance: referralStatusInstance
    }

    @Transactional
    def update(ReferralStatus referralStatusInstance) {
        if (referralStatusInstance == null) {
            notFound()
            return
        }

        if (referralStatusInstance.hasErrors()) {
            respond referralStatusInstance.errors, view: 'edit'
            return
        }

        referralStatusInstance.save flush: true

        request.withFormat {
            form {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'ReferralStatus.label', default: 'ReferralStatus'), referralStatusInstance.id])
                redirect referralStatusInstance
            }
            '*' { respond referralStatusInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(ReferralStatus referralStatusInstance) {

        if (referralStatusInstance == null) {
            notFound()
            return
        }

        referralStatusInstance.delete flush: true

        request.withFormat {
            form {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'ReferralStatus.label', default: 'ReferralStatus'), referralStatusInstance.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'referralStatusInstance.label', default: 'ReferralStatus'), params.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NOT_FOUND }
        }
    }
}
