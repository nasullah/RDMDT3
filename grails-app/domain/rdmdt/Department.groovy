package rdmdt

/**
 * Department
 * A domain class describes the data object and it's mapping to the database
 */
class Department {

    static auditable = true
    static constraints = {
        departmentName()
    }

    String departmentName

    /*
     * Methods of the Domain Class
     */
	@Override	// Override toString for a nicer / more descriptive UI
	public String toString() {
		return "${departmentName}";
	}
}
