package rdmdt

class EligibilityQuestion {

    static auditable = true
    static belongsTo = [specificDisorders:SpecificDisorders]
    static constraints = {
    }
    Date date
    String versionNumber
}