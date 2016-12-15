package rdmdt

/**
 * EligibilityType
 * A domain class describes the data object and it's mapping to the database
 */
class EligibilityType {

    static auditable = true
    static constraints = {
        eligibilityTypeName()
    }

    String eligibilityTypeName
    /*
     * Methods of the Domain Class
     */
	@Override	// Override toString for a nicer / more descriptive UI
	public String toString() {
		return "${eligibilityTypeName}";
	}
}
