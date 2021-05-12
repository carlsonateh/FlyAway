package constant;
public class Queries {

	public static final String GET_MASTER_LIST_SOURCE_AND_DESTINATIONS = "select id,name from City";

	public static final String GET_MASTER_LIST_AIRLINES = "select id,name from Airline";

	public static final String GET_MASTER_LIST_FLIGHTS = "select id,name,price,sourceId,destinationId,airlineId,travelDate from Flight";

	public static final String UPDATE_ADMIN_PASSWORD = "update Admin set password=:newPassword";

	public static final String VERIFY_LOGIN = "select id from Admin where username=:username and password=:password";
}
