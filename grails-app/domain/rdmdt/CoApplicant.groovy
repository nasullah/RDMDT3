package rdmdt

/**
 * CoApplicant
 * A domain class describes the data object and it's mapping to the database
 */
class CoApplicant {

    static auditable = true
    static belongsTo = [referralRecord:ReferralRecord]
    static constraints = {
        referralRecord()
        coApplicant()
    }

    Clinician coApplicant
    /*
     * Methods of the Domain Class
     */
    @Override	// Override toString for a nicer / more descriptive UI
    public String toString() {
        return "${coApplicant.forename} ${coApplicant.surname}";
    }
}
