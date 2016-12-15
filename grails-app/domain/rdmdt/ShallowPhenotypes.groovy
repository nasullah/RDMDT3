package rdmdt

class ShallowPhenotypes {

    static belongsTo = [specificDisorders:SpecificDisorders]
    static constraints = {
    }
    String name
    String originalId
}