package rdmdt

import grails.plugin.springsecurity.annotation.Secured

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Secured(['ROLE_USER', 'ROLE_ADMIN'])
@Transactional(readOnly = true)
class RareDiseasesPhenotypeReportController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond RareDiseasesPhenotypeReport.list(params), model: [rareDiseasesPhenotypeReportInstanceCount: RareDiseasesPhenotypeReport.count()]
    }

    def show(RareDiseasesPhenotypeReport rareDiseasesPhenotypeReportInstance) {
        respond rareDiseasesPhenotypeReportInstance
    }

    def create() {
        def rareDiseasesPhenotypeReportInstance = new RareDiseasesPhenotypeReport(params)
        def specificDisorderId = params.specificDisorderId
        def diseaseGroup = SpecificDisorders.findByOriginalId(specificDisorderId)?.subGroups?.diseaseGroups
        def diseaseSubGroup = SpecificDisorders.findByOriginalId(specificDisorderId)?.subGroups
        def specificDisease = SpecificDisorders.findByOriginalId(specificDisorderId)
        [rareDiseasesPhenotypeReportInstance: rareDiseasesPhenotypeReportInstance, diseaseGroup: diseaseGroup, diseaseSubGroup:diseaseSubGroup, specificDisease:specificDisease]
    }

    def test(){
        def rareDiseasesPhenotypeReportInstanceList = ShallowPhenotypes.findAllBySpecificDisorders(SpecificDisorders.get(1))
        [rareDiseasesPhenotypeReportInstanceList:rareDiseasesPhenotypeReportInstanceList]
    }

    def savetest(){
        print(params)
        redirect action: "index", method: "GET"
    }

    @Transactional
    def save(RareDiseasesPhenotypeReport rareDiseasesPhenotypeReportInstance) {
        if (rareDiseasesPhenotypeReportInstance == null) {
            notFound()
            return
        }

        if (rareDiseasesPhenotypeReportInstance.hasErrors()) {
            respond rareDiseasesPhenotypeReportInstance.errors, view: 'create'
            return
        }

        rareDiseasesPhenotypeReportInstance.save flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'rareDiseasesPhenotypeReport.label', default: 'RareDiseasesPhenotypeReport'), rareDiseasesPhenotypeReportInstance.id])
                redirect rareDiseasesPhenotypeReportInstance
            }
            '*' { respond rareDiseasesPhenotypeReportInstance, [status: CREATED] }
        }
    }

    def edit(RareDiseasesPhenotypeReport rareDiseasesPhenotypeReportInstance) {
        respond rareDiseasesPhenotypeReportInstance
    }

    @Transactional
    def update(RareDiseasesPhenotypeReport rareDiseasesPhenotypeReportInstance) {
        if (rareDiseasesPhenotypeReportInstance == null) {
            notFound()
            return
        }

        if (rareDiseasesPhenotypeReportInstance.hasErrors()) {
            respond rareDiseasesPhenotypeReportInstance.errors, view: 'edit'
            return
        }

        rareDiseasesPhenotypeReportInstance.save flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'RareDiseasesPhenotypeReport.label', default: 'RareDiseasesPhenotypeReport'), rareDiseasesPhenotypeReportInstance.id])
                redirect rareDiseasesPhenotypeReportInstance
            }
            '*' { respond rareDiseasesPhenotypeReportInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(RareDiseasesPhenotypeReport rareDiseasesPhenotypeReportInstance) {

        if (rareDiseasesPhenotypeReportInstance == null) {
            notFound()
            return
        }

        rareDiseasesPhenotypeReportInstance.delete flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'RareDiseasesPhenotypeReport.label', default: 'RareDiseasesPhenotypeReport'), rareDiseasesPhenotypeReportInstance.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'rareDiseasesPhenotypeReport.label', default: 'RareDiseasesPhenotypeReport'), params.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NOT_FOUND }
        }
    }
}