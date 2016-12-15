package rdmdt

class SpecificDisorders {

    static hasMany = [shallowPhenotypes:ShallowPhenotypes, tests:Tests]
    static hasOne = [eligibilityQuestion:EligibilityQuestion]
    static belongsTo = [subGroups:SubGroups]
    static constraints = {
        eligibilityQuestion(nullable: true)
    }
    String name
    String originalId
}
