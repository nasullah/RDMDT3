package rdmdt

import grails.plugin.springsecurity.annotation.Secured

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

/**
 * AttachedEvidenceController
 * A controller class handles incoming web requests and performs actions such as redirects, rendering views and so on.
 */
@Secured(['ROLE_USER', 'ROLE_ADMIN'])
@Transactional(readOnly = true)
class AttachedEvidenceController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond AttachedEvidence.list(params), model: [attachedEvidenceInstanceCount: AttachedEvidence.count()]
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond AttachedEvidence.list(params), model: [attachedEvidenceInstanceCount: AttachedEvidence.count()]
    }

    def show(AttachedEvidence attachedEvidenceInstance) {
        respond attachedEvidenceInstance: attachedEvidenceInstance
    }

    def create() {
        respond new AttachedEvidence(params)
    }

    def download(long id) {
        AttachedEvidence attachedEvidenceInstance = AttachedEvidence.get(id)
        if ( attachedEvidenceInstance == null) {
            flash.message = "Attached evidence file not found"
            redirect (action:'index')
        } else {
            response.setContentType("APPLICATION/OCTET-STREAM")
            response.setHeader("Content-Disposition", "Attachment;Filename=\"${attachedEvidenceInstance.content}\"")

            def file = new File(attachedEvidenceInstance.content)
            def fileInputStream = new FileInputStream(file)
            def outputStream = response.getOutputStream()

            byte[] buffer = new byte[4096];
            int len;
            while ((len = fileInputStream.read(buffer)) > 0) {
                outputStream.write(buffer, 0, len);
            }

            outputStream.flush()
            outputStream.close()
            fileInputStream.close()
        }
    }

    @Transactional
    def save(AttachedEvidence attachedEvidenceInstance) {
        if (attachedEvidenceInstance == null) {
            notFound()
            return
        }

        if (attachedEvidenceInstance.hasErrors()) {
            respond attachedEvidenceInstance.errors, view: 'create'
            return
        }

        def file = request.getFile('attachedEvidenceFile')
        if (!file.originalFilename) {
            flash.message = "Please choose a file"
            respond attachedEvidenceInstance, view: 'create'
        }else{
            attachedEvidenceInstance.addedOn = new Date()
            attachedEvidenceInstance.save flush: true
            attachedEvidenceInstance.content = grailsApplication.config.uploadFolder +'Attached_Evidence/'+ attachedEvidenceInstance.id.toString() + '.' + file.originalFilename
            def destinationFile = new File(attachedEvidenceInstance.content)

            try {
                file.transferTo(destinationFile)
                attachedEvidenceInstance.save flush: true
                flash.message = "Attached evidence ${attachedEvidenceInstance.id} is created"
                redirect(controller:'referralRecord',action: 'show', params: [id: attachedEvidenceInstance.referralRecord.id])
            }
            catch (Exception ex) {
                log.error(ex)
                flash.message = "Attached evidence ${referralRecordInstance.id} is not created."
                respond attachedEvidenceInstance, view: 'create'
            }
        }
    }

    def edit(AttachedEvidence attachedEvidenceInstance) {
        respond attachedEvidenceInstance:attachedEvidenceInstance
    }

    @Transactional
    def update(AttachedEvidence attachedEvidenceInstance) {
        if (attachedEvidenceInstance == null) {
            notFound()
            return
        }

        if (attachedEvidenceInstance.hasErrors()) {
            respond attachedEvidenceInstance.errors, view: 'edit'
            return
        }
        attachedEvidenceInstance.addedOn = new Date()
        attachedEvidenceInstance.save flush: true

        request.withFormat {
            form {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'AttachedEvidence.label', default: 'AttachedEvidence'), attachedEvidenceInstance.id])
                redirect attachedEvidenceInstance
            }
            '*' { respond attachedEvidenceInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(AttachedEvidence attachedEvidenceInstance) {

        if (attachedEvidenceInstance == null) {
            notFound()
            return
        }

        if (attachedEvidenceInstance.content){
            def attachedEvidenceFile = new File(attachedEvidenceInstance.content)
            if (attachedEvidenceFile.exists()){
                boolean fileDeletedSuccessfully = new File(attachedEvidenceInstance.content).delete()
                if (fileDeletedSuccessfully){
                    attachedEvidenceInstance.delete flush: true
                    flash.message = "Attached evidence instance has been deleted"
                    redirect(controller:'referralRecord',action: 'show', params: [id: attachedEvidenceInstance.referralRecord.id])
                } else{
                    flash.message = "Could not delete the file"
                    redirect(controller:'referralRecord',action: 'show', params: [id: attachedEvidenceInstance.referralRecord.id])
                }
            }
        }else {
            attachedEvidenceInstance.delete flush: true
            flash.message = "Attached evidence instance has been deleted"
            redirect(controller:'referralRecord',action: 'show', params: [id: attachedEvidenceInstance.referralRecord.id])

        }
    }

    protected void notFound() {
        request.withFormat {
            form {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'attachedEvidenceInstance.label', default: 'AttachedEvidence'), params.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NOT_FOUND }
        }
    }
}
