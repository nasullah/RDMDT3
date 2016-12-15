package rdmdt

/**
 * ReferralStatus
 * A domain class describes the data object and it's mapping to the database
 */
class ReferralStatus {

    static auditable = true
    static constraints = {
        referralStatusName()
    }

    String referralStatusName

    /*
     * Methods of the Domain Class
     */
	@Override	// Override toString for a nicer / more descriptive UI
	public String toString() {
		return "${referralStatusName}";
	}
}
