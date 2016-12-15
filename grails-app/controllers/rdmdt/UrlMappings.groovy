package rdmdt

class UrlMappings {

    static mappings = {

        "/"(view:"/home/index")
        "500"(view:'/error')
        "404"(view:'/notFound')

        "/$controller/$action?/$id?"{
            constraints {
                controller(matches:/^((?!(api|mobile|web)).*)$/)
            }
        }
    }
}
