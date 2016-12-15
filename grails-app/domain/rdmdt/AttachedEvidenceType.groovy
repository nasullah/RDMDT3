package rdmdt

/**
 * AttachedEvidenceType
 * A domain class describes the data object and it's mapping to the database
 */
class AttachedEvidenceType {

    static auditable = true
    static constraints = {
        attachedEvidenceTypeName()
    }

    String attachedEvidenceTypeName

    /*
     * Methods of the Domain Class
     */
	@Override	// Override toString for a nicer / more descriptive UI
	public String toString() {
		return "${attachedEvidenceTypeName}";
	}
}
