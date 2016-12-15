package rdmdt

class EligibilityQuestion {

    static belongsTo = [specificDisorders:SpecificDisorders]
    static constraints = {
    }
    Date date
    String versionNumber
}