package rdmdt

class Statement {
    static belongsTo = [phenotyping:RareDiseasesPhenotypeReport]
    static constraints = {
        identifier blank: false, matches: /HP:[0-9]{7}/
        description nullable: true
        hpoBuildNumber nullable: true, validator: {val, obj ->
            if (obj.phenotyping.referralRecord) {
                return val ? true : ['default.null.message']
            }
            true
        }
        present()
    }
    String identifier
    YesNoUnknown present
    String hpoBuildNumber
    String description
}
