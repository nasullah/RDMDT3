package rdmdt

/**
 * Consanguinity
 * A domain class describes the data object and it's mapping to the database
 */
class Consanguinity {

    static auditable = true
    static constraints = {
        consanguinityEvidence()
    }

    String consanguinityEvidence

    /*
     * Methods of the Domain Class
     */
	@Override	// Override toString for a nicer / more descriptive UI
	public String toString() {
		return "${consanguinityEvidence}";
	}
}
