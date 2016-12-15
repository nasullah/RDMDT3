package rdmdt

class DiseaseGroups {

    static hasMany = [subGroups:SubGroups]
    static constraints = {
    }
    String name
    String originalId
}
