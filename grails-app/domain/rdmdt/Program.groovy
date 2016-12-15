package rdmdt

/**
 * Program
 * A domain class describes the data object and it's mapping to the database
 */
class Program{

    static auditable = true
    static constraints = {
        name()
        description()
    }

    String name
    String description

    /*
     * Methods of the Domain Class
     */
	@Override	// Override toString for a nicer / more descriptive UI
	public String toString() {
		return "${name}";
	}
}
