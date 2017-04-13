package rdmdt

class ShallowPhenotypes {

    static auditable = true
    static belongsTo = [specificDisorders:SpecificDisorders]
    static constraints = {
    }
    String name
    String originalId
}