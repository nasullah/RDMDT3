package rdmdt

import grails.plugin.springsecurity.annotation.Secured
import groovy.json.JsonSlurper
import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Secured(['ROLE_USER', 'ROLE_ADMIN'])
@Transactional(readOnly = true)
class DiseaseGroupsController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond DiseaseGroups.list(params), model: [diseaseGroupsInstanceCount: DiseaseGroups.count()]
    }

    def show(DiseaseGroups diseaseGroupsInstance) {
        respond diseaseGroupsInstance: diseaseGroupsInstance
    }

    def create() {
        respond new DiseaseGroups(params)
    }

    def assetResourceLocator
//    @Transactional
//    def test(){
////        def jsonObject = new JsonSlurper().parseText('{"DiseaseGroups": [ {"id": "10950","name": "Cardiovascular disorders","subGroups": [ {"id": "33332","name": "Arteriopathies", "specificDisorders": [{"id": "33666","name": "Familial Hypercholesterolaemia","eligibilityQuestion": {"date": "2016-06-28","version": "5"},"shallowPhenotypes": [{"name": "Hyperlipidemia","id": "HP:0003077"},{"name": "Hypercholesterolemia","id": "HP:0003124"},{"name": "Hyperbetalipoproteinemia","id": "HP:0003141"},{"name": "Hyperlipoproteinemia","id": "HP:0010980"},{"name": "Myocardial infarction","id": "HP:0001658"},{"name": "Hypertriglyceridemia","id": "HP:0002155"},{"name": "Tendon xanthomatosis","id": "HP:0010874"},{"name": "Corneal arcus","id": "HP:0001084"}],"tests": [{"name": "Lipids","id": "33158.1"}]}] }] }] }')
//        def path = grailsApplication.config.uploadFolder + "DiseaseOntology-v1.6.0.json"
//        def inputFile = new File(path)
//        def jsonObject = new JsonSlurper().parseText(inputFile.text)
//
//        jsonObject.DiseaseGroups.each{dg ->
//            def diseaseGroupsInstance = new DiseaseGroups(originalId: dg.id, name: dg.name)
//            dg.subGroups.each{sg ->
//                def subGroupsInstance = new SubGroups(originalId: sg.id, name: sg.name)
//                diseaseGroupsInstance.addToSubGroups(subGroupsInstance).save()
//                sg.specificDisorders.each{sd ->
//                    def specificDisordersInstance = new SpecificDisorders(originalId: sd.id, name: sd.name)
//                    subGroupsInstance.addToSpecificDisorders(specificDisordersInstance).save()
//                    print(sd.eligibilityQuestion.version)
//                    print(sd.eligibilityQuestion.date)
//                    specificDisordersInstance.eligibilityQuestion = new EligibilityQuestion(date: new Date().parse("yyyy-MM-dd", sd.eligibilityQuestion.date), versionNumber: sd.eligibilityQuestion.version)
//                    specificDisordersInstance.save()
//                    sd.shallowPhenotypes.each{sp ->
//                        def shallowPhenotypesInstance = new ShallowPhenotypes(originalId: sp.id, name: sp.name)
//                        specificDisordersInstance.addToShallowPhenotypes(shallowPhenotypesInstance).save()
//                    }
//                    sd.tests.each{test ->
//                        def testsInstance = new Tests(originalId: test.id, name: test.name)
//                        specificDisordersInstance.addToTests(testsInstance).save()
//                    }
//                }
//            }
//        }
//
//        redirect action: "index", method: "GET"
//
//    }

    @Transactional
    def save(DiseaseGroups diseaseGroupsInstance) {
        if (diseaseGroupsInstance == null) {
            notFound()
            return
        }

        if (diseaseGroupsInstance.hasErrors()) {
            respond diseaseGroupsInstance.errors, view: 'create'
            return
        }

        diseaseGroupsInstance.save flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'diseaseGroups.label', default: 'DiseaseGroups'), diseaseGroupsInstance.id])
                redirect diseaseGroupsInstance
            }
            '*' { respond diseaseGroupsInstance, [status: CREATED] }
        }
    }

    def edit(DiseaseGroups diseaseGroupsInstance) {
        respond diseaseGroupsInstance: diseaseGroupsInstance
    }

    @Transactional
    def update(DiseaseGroups diseaseGroupsInstance) {
        if (diseaseGroupsInstance == null) {
            notFound()
            return
        }

        if (diseaseGroupsInstance.hasErrors()) {
            respond diseaseGroupsInstance.errors, view: 'edit'
            return
        }

        diseaseGroupsInstance.save flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'DiseaseGroups.label', default: 'DiseaseGroups'), diseaseGroupsInstance.id])
                redirect diseaseGroupsInstance
            }
            '*' { respond diseaseGroupsInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(DiseaseGroups diseaseGroupsInstance) {

        if (diseaseGroupsInstance == null) {
            notFound()
            return
        }

        diseaseGroupsInstance.delete flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'DiseaseGroups.label', default: 'DiseaseGroups'), diseaseGroupsInstance.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'diseaseGroups.label', default: 'DiseaseGroups'), params.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NOT_FOUND }
        }
    }
}
