package rdmdt

/**
 * AttachedEvidence
 * A domain class describes the data object and it's mapping to the database
 */
class AttachedEvidence {

    static auditable = true
    static belongsTo = [referralRecord:ReferralRecord]
    static mapping = {
        content type: "text"
    }
    static constraints = {
        referralRecord()
        addedOn(nullable: true)
        type()
        content(nullable: true)
    }

    Date addedOn
    String content
    AttachedEvidenceType type
    /*
     * Methods of the Domain Class
     */
    @Override	// Override toString for a nicer / more descriptive UI
    public String toString() {
        return "Type: ${type}, File: ${content}";
    }
}