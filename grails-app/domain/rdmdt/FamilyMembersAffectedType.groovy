package rdmdt

/**
 * FamilyMembersAffectedType
 * A domain class describes the data object and it's mapping to the database
 */
class FamilyMembersAffectedType {

    static auditable = true
    static constraints = {
        familyMembersAffectedTypeName()
    }

    String familyMembersAffectedTypeName
    /*
     * Methods of the Domain Class
     */
    @Override	// Override toString for a nicer / more descriptive UI
    public String toString() {
        return "${familyMembersAffectedTypeName}";
    }
}
