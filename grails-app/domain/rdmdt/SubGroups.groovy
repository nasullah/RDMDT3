package rdmdt

class SubGroups {

    static hasMany = [specificDisorders:SpecificDisorders]
    static belongsTo = [diseaseGroups:DiseaseGroups]
    static constraints = {
    }
    String name
    String originalId
}
