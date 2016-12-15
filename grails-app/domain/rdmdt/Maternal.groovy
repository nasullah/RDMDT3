package rdmdt

/**
 * Maternal
 * A domain class describes the data object and it's mapping to the database
 */
class Maternal extends FamilyHistory{

    static auditable = true
    static belongsTo = [referralRecord:ReferralRecord]
    static constraints = {
        breastAndOrOvarianCancer()
        colorectalCancer()
        ischaemicHeartDiseaseOrStroke()
        endocrineTumours()
    }

    /*
     * Methods of the Domain Class
     */
//	@Override	// Override toString for a nicer / more descriptive UI 
//	public String toString() {
//		return "${name}";
//	}
}
