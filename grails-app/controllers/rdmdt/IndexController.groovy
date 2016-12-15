package rdmdt

import org.springframework.security.access.annotation.Secured
/**
 * IndexController
 * A controller class handles incoming web requests and performs actions such as redirects, rendering views and so on.
 */
class IndexController {
    def springSecurityService

    @Secured(['IS_AUTHENTICATED_REMEMBERED'])
    def index() {
        def currentUser = springSecurityService?.currentUser?.username
        if (currentUser?.toString()?.contains('.')){
            def forename = currentUser?.toString()?.split("\\.")[0]
            def surname = currentUser?.toString()?.split("\\.")[1]
            def clinician = Clinician.createCriteria().get {
                and{
                    eq("forename", forename, [ignoreCase: true])
                    eq("surname", surname, [ignoreCase: true])
                }
            }
            [clinician:clinician]
        }
    }

    @Secured(['ROLE_ADMIN'])
    def listAuditLogData(){
        def user = User?.get(params.long('user.id'))
        def listAuditLogData = AuditLogEvent?.findAllByActor(user?.username)
        listAuditLogData = listAuditLogData.sort {it.dateCreated}
        listAuditLogData = listAuditLogData.reverse()
        [listAuditLogData: listAuditLogData]
    }
}
