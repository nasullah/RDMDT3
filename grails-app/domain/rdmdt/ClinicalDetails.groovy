package rdmdt

/**
 * ClinicalDetails
 * A domain class describes the data object and it's mapping to the database
 */
class ClinicalDetails {

    static auditable = true
    static belongsTo = [referralRecord:ReferralRecord]
    static constraints = {
        referralRecord()
        clinicalDetailsName()
    }

    String clinicalDetailsName
    /*
     * Methods of the Domain Class
     */
	@Override	// Override toString for a nicer / more descriptive UI
	public String toString() {
		return "${clinicalDetailsName}";
	}
}
