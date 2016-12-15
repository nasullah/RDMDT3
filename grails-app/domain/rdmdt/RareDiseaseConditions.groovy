package rdmdt

/**
 * RareDiseaseConditions
 * A domain class describes the data object and it's mapping to the database
 */
class RareDiseaseConditions {

    static auditable = true
    static constraints = {
        originalId()
        diseaseGroup()
        diseaseSubgroup()
        diseaseName()
    }

    Integer originalId
    String diseaseGroup
    String diseaseSubgroup
    String diseaseName
    /*
     * Methods of the Domain Class
     */
	@Override	// Override toString for a nicer / more descriptive UI
	public String toString() {
		return "${diseaseName}";
	}
}
