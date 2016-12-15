package rdmdt

/**
 * Ethnicity
 * A domain class describes the data object and it's mapping to the database
 */
class Ethnicity {

    static auditable = true
    static constraints = {
        ethnicityName()
    }

    String ethnicityName
    /*
     * Methods of the Domain Class
     */
    @Override	// Override toString for a nicer / more descriptive UI
    public String toString() {
        return "${ethnicityName}";
    }
}
