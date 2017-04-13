package rdmdt

import grails.plugin.springsecurity.annotation.Secured

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

/**
 * RareDiseaseConditionsController
 * A controller class handles incoming web requests and performs actions such as redirects, rendering views and so on.
 */
@Secured(['ROLE_ADMIN'])
@Transactional(readOnly = true)
class RareDiseaseConditionsController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [rareDiseaseConditionsInstanceList: RareDiseaseConditions.list(params), rareDiseaseConditionsInstanceCount: RareDiseaseConditions.count()]
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [rareDiseaseConditionsInstanceList: RareDiseaseConditions.list(params), rareDiseaseConditionsInstanceCount: RareDiseaseConditions.count()]
    }

    def show(RareDiseaseConditions rareDiseaseConditionsInstance) {
        [rareDiseaseConditionsInstance:rareDiseaseConditionsInstance]
    }

    def create() {
        [rareDiseaseConditionsInstance:new RareDiseaseConditions(params)]
    }

    @Transactional
    def uploadFile () {
        if (!request.getFile('file').originalFilename) {
            flash.message = "Please choose a file"
            redirect(controller:'rareDiseaseConditions',action: 'index')
        } else{
            request.getFile('file').inputStream.splitEachLine(',')
                    { fields ->
                        new RareDiseaseConditions(diseaseGroup: fields[2].trim(), diseaseName:fields[3].trim(), diseaseSubgroup: fields[4].trim(),originalId: fields[5].trim()).save flush: true
                   }
            redirect(controller:'rareDiseaseConditions',action: 'index')
        }
    }

    @Transactional
    def save(RareDiseaseConditions rareDiseaseConditionsInstance) {
        if (rareDiseaseConditionsInstance == null) {
            notFound()
            return
        }

        if (rareDiseaseConditionsInstance.hasErrors()) {
            render view: 'create', model: [rareDiseaseConditionsInstance: rareDiseaseConditionsInstance]
            return
        }

        rareDiseaseConditionsInstance.save flush: true

        request.withFormat {
            form {
                flash.message = message(code: 'default.created.message', args: [message(code: 'rareDiseaseConditionsInstance.label', default: 'RareDiseaseConditions'), rareDiseaseConditionsInstance.id])
                redirect rareDiseaseConditionsInstance
            }
            '*' { respond rareDiseaseConditionsInstance, [status: CREATED] }
        }
    }

    def edit(RareDiseaseConditions rareDiseaseConditionsInstance) {
        [rareDiseaseConditionsInstance:rareDiseaseConditionsInstance]
    }

    @Transactional
    def update(RareDiseaseConditions rareDiseaseConditionsInstance) {
        if (rareDiseaseConditionsInstance == null) {
            notFound()
            return
        }

        if (rareDiseaseConditionsInstance.hasErrors()) {
            render view: 'edit', model: [rareDiseaseConditionsInstance: rareDiseaseConditionsInstance]
            return
        }

        rareDiseaseConditionsInstance.save flush: true

        request.withFormat {
            form {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'RareDiseaseConditions.label', default: 'RareDiseaseConditions'), rareDiseaseConditionsInstance.id])
                redirect rareDiseaseConditionsInstance
            }
            '*' { respond rareDiseaseConditionsInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(RareDiseaseConditions rareDiseaseConditionsInstance) {

        if (rareDiseaseConditionsInstance == null) {
            notFound()
            return
        }

        rareDiseaseConditionsInstance.delete flush: true

        request.withFormat {
            form {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'RareDiseaseConditions.label', default: 'RareDiseaseConditions'), rareDiseaseConditionsInstance.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'rareDiseaseConditionsInstance.label', default: 'RareDiseaseConditions'), params.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NOT_FOUND }
        }
    }
}
