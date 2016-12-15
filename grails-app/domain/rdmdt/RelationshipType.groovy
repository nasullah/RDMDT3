package rdmdt

/**
 * RelationshipType
 * A domain class describes the data object and it's mapping to the database
 */
class RelationshipType {

	static auditable = true
	static	constraints = {
		relationshipTypeName()
		relationshipTypeInverse()
    }

	String relationshipTypeName
	String relationshipTypeInverse

	/*
	 * Methods of the Domain Class
	 */
	@Override	// Override toString for a nicer / more descriptive UI
	public String toString() {
		return "${relationshipTypeName}";
	}
}
