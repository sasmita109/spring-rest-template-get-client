package com.app.client;

import java.lang.reflect.Type;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.app.model.EmployeeModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class EmployeeControllerClient {

	// this client interacts with spring-boot-search-controllers which is
	// running on port 9999
	public static final String HOST = "http://localhost";
	public static final String PORT = "9999";
	public static final String APP_NAME = "spring-boot-search-controllers";

	public static void displayEmployeeById(String empId) {
		EmployeeControllerClient client = new EmployeeControllerClient();
		ResponseEntity<?> response = client.getEmployeeById(empId);
		System.out.println("\n" + response);
		if (response.hasBody() == false) {// without this if statement if we
											// call to convert jsontoobject then
											// we will get null pointer
											// exception.
			System.out.println("No response found");
			return;
		}
		EmployeeModel model = (EmployeeModel) client.jsonToObject(response,
				EmployeeModel.class);
		// i hv not called list here.
		System.out.println(model);

	}

	public static void displayEmployeeList() {
		EmployeeControllerClient client = new EmployeeControllerClient();

		ResponseEntity<?> response = client.getEmployeeList();
		System.out.println("\n" + response);
		if (response.hasBody() == false) {// without this if statement if we
			// call to convert jsontoobject then
			// we will get null pointer
			// exception.
			System.out.println("No response found");
			return;
		}
		List<EmployeeModel> modelList = (List<EmployeeModel>) client
				.jsonToObjectList(response);
		System.out.println(modelList);
	}

	public ResponseEntity<?> getEmployeeById(String empId) {
		// get-employee-{idx}-data
		String serviceLocation = "get-employee-" + empId + "-data";

		String url = HOST + ":" + PORT + "/" + APP_NAME + "/" + serviceLocation;

		ResponseEntity<?> response = new RestTemplate().getForEntity(url,
				String.class);

		return response;
	}

	public ResponseEntity<?> getEmployeeList() {
		String serviceLocation = "employee-list";

		String url = HOST + ":" + PORT + "/" + APP_NAME + "/" + serviceLocation;

		ResponseEntity<?> response = new RestTemplate().getForEntity(url,
				String.class);

		return response;
	}

	private Object jsonToObject(ResponseEntity<?> response, Class clazz) {
		Object obj = null;
		Gson gson = new Gson();
		obj = gson.fromJson(response.getBody().toString(), clazz);
		return obj;
	}

	private Object jsonToObjectList(ResponseEntity<?> response) {
		Type listType = new TypeToken<List<Object>>() {
		}.getType();
		List<Object> list = new Gson().fromJson(response.getBody().toString(),
				listType);

		return list;
	}
}
