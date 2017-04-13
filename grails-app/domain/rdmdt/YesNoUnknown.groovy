package rdmdt

class YesNoUnknown {

    static auditable = true
    static constraints = {
        yesNoUnknownName()
    }
    String yesNoUnknownName
}
