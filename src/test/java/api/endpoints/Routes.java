package api.endpoints;

/*
 Swagger URI (Base URL)--> https://petstore.swagger.io/v2 
 Create user(POST) --> https://petstore.swagger.io/v2/user
 Get user(GET) --> https://petstore.swagger.io/v2/user/{username}
 Update user(PUT) --> https://petstore.swagger.io/v2/user/{username}
 Delete user --> https://petstore.swagger.io/v2/user/{username}
 */

public class Routes {

	// Base URL
	public static String base_url = "https://petstore.swagger.io/v2";

	// User module
	public static String post_url = base_url + "/user";
	public static String get_url = base_url + "/user/{username}";
	public static String update_url = base_url + "/user/{username}";
	public static String delete_url = base_url + "/user/{username}";

	// Store module URLs

	// Pet module URLs

}
