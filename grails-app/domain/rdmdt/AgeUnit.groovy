package rdmdt

/**
 * AgeUnit
 * A domain class describes the data object and it's mapping to the database
 */
class AgeUnit {

    static auditable = true
    static constraints = {
        ageUnitName()
    }

    String ageUnitName

    /*
     * Methods of the Domain Class
     */
    @Override	// Override toString for a nicer / more descriptive UI
    public String toString() {
        return "${ageUnitName}";
    }
}
