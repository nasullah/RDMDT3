package rdmdt

/**
 * FamilyHistory
 * A domain class describes the data object and it's mapping to the database
 */
class FamilyHistory {

    static auditable = true
    static constraints = {
        breastAndOrOvarianCancer(nullable: true)
        colorectalCancer(nullable: true)
        ischaemicHeartDiseaseOrStroke(nullable: true)
        endocrineTumours(nullable: true)
    }

    FamilyHistoryType breastAndOrOvarianCancer
    FamilyHistoryType colorectalCancer
    FamilyHistoryType ischaemicHeartDiseaseOrStroke
    FamilyHistoryType endocrineTumours
    /*
     * Methods of the Domain Class
     */
//	@Override	// Override toString for a nicer / more descriptive UI 
//	public String toString() {
//		return "${name}";
//	}
}
