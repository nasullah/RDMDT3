package rdmdt

class Tests {

    static auditable = true
    static belongsTo = [specificDisorders:SpecificDisorders]
    static constraints = {
    }
    String name
    String originalId
}