package rdmdt

class DiseaseGroups {

    static auditable = true
    static hasMany = [subGroups:SubGroups]
    static constraints = {
    }
    String name
    String originalId
}
