
// Added by the Audit-Logging plugin:
grails.plugin.auditLog.auditDomainClassName = 'rdmdt.AuditLogEvent'

// Added by the Spring Security Core plugin:
grails.plugin.springsecurity.userLookup.userDomainClassName = 'rdmdt.User'
grails.plugin.springsecurity.userLookup.authorityJoinClassName = 'rdmdt.UserRole'
grails.plugin.springsecurity.authority.className = 'rdmdt.Role'
grails.plugin.springsecurity.logout.postOnly = false
grails.plugin.springsecurity.controllerAnnotations.staticRules = [
	[pattern: '/',               	access: ['permitAll']],
	[pattern: '/error',          	access: ['permitAll']],
	[pattern: '/index',          	access: ['permitAll']],
	[pattern: '/home/index',        access: ['permitAll']],
	[pattern: '/index.gsp',      	access: ['permitAll']],
	[pattern: '/shutdown',       	access: ['permitAll']],
	[pattern: '/assets/**',      	access: ['permitAll']],
	[pattern: '/**/js/**',       	access: ['permitAll']],
	[pattern: '/**/javascripts/**', access: ['permitAll']],
	[pattern: '/**/css/**',      	access: ['permitAll']],
	[pattern: '/**/stylesheets/**', access: ['permitAll']],
	[pattern: '/**/images/**',   	access: ['permitAll']],
	[pattern: '/**/favicon.ico', 	access: ['permitAll']],
	[pattern: '/**/scripts/**', 	access: ['permitAll']],
	[pattern: '/user/**',        access: 'ROLE_ADMIN'],
	[pattern: '/role/**',        access: 'ROLE_ADMIN']
]

grails.plugin.springsecurity.filterChain.chainMap = [
	[pattern: '/assets/**',      	filters: 'none'],
	[pattern: '/**/js/**',       	filters: 'none'],
	[pattern: '/**/javascripts/**', filters: 'none'],
	[pattern: '/**/css/**',      	filters: 'none'],
	[pattern: '/**/stylesheets/**', filters: 'none'],
	[pattern: '/**/images/**',   	filters: 'none'],
	[pattern: '/**/favicon.ico', 	filters: 'none'],
	[pattern: '/**/scripts/**', 	filters: 'none'],
	[pattern: '/**',             	filters: 'JOINED_FILTERS']
]