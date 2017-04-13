package rdmdt

class SubGroups {

    static auditable = true
    static hasMany = [specificDisorders:SpecificDisorders]
    static belongsTo = [diseaseGroups:DiseaseGroups]
    static constraints = {
    }
    String name
    String originalId
}
