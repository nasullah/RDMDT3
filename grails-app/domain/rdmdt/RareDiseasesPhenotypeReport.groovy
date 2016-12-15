package rdmdt

class RareDiseasesPhenotypeReport {

    static belongsTo = [referralRecord:ReferralRecord]
    static hasMany = [statements:Statement]
    static constraints = {
    }
}

