package rdmdt

class RareDiseasesPhenotypeReport {

    static auditable = true
    static belongsTo = [referralRecord:ReferralRecord]
    static hasMany = [statements:Statement]
    static constraints = {
    }
}

