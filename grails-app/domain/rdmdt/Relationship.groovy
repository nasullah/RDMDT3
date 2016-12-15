package rdmdt

/**
 * Relationship
 * A domain class describes the data object and it's mapping to the database
 */
class Relationship {

    static auditable = true
    static belongsTo = [patient:Patient]
    static hasOne = [relatedPatient:Patient]
    static constraints = {
        relationshipType()
        relatedPatient(nullable: true)
    }

    RelationshipType relationshipType

    /*
     * Methods of the Domain Class
     */
	@Override	// Override toString for a nicer / more descriptive UI
	public String toString() {
		return "${relationshipType?.relationshipTypeName}";
	}
}
