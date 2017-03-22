package com.app.client;

import java.lang.reflect.Type;
import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.app.model.DepartmentModel;
import com.app.model.EmployeeModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class DepartmentControllerClient {

	public static final String HOST = "http://localhost";
	public static final String PORT = "9999";
	public static final String APP_NAME = "spring-boot-search-controllers";

	public static void displayDepartmentById(String deptId) {
		DepartmentControllerClient client = new DepartmentControllerClient();

		ResponseEntity<?> response = client.getDepartmentById(deptId);
		System.out.println("\n" + response);
		if (response.hasBody() == false) {
			System.out.println("No response found");
			return;
		}
		
		DepartmentModel model = (DepartmentModel) client.jsonToObject(response, DepartmentModel.class);
		// i hv not called list here.
		System.out.println(model);

	}

	private ResponseEntity<?> getDepartmentById(String deptId) {
		String serviceLocation = "get-department-" + deptId + "-data";

		String url = HOST + ":" + PORT + "/" + APP_NAME + "/" + serviceLocation;

		ResponseEntity<?> response = new RestTemplate().getForEntity(url,
				String.class);

		return response;
	}

	private ResponseEntity<?> getDepartmentById() {
		String serviceLocation = "department-list";

		String url = HOST + ":" + PORT + "/" + APP_NAME + "/" + serviceLocation;

		ResponseEntity<?> response = new RestTemplate().getForEntity(url,
				String.class);

		return response;
	}

	private Object jsonToObject(ResponseEntity<?> response, Class c) {
		Object obj = null;
		Gson gson = new Gson();
		obj = gson.fromJson(response.getBody().toString(), c);
		return obj;
	}

	private Object jsonToObjectList(ResponseEntity<?> response) {
		Type listType = new TypeToken<List<Object>>() {
		}.getType();
		List<Object> list = new Gson().fromJson(response.getBody().toString(),
				listType);

		return list;
	}
	public static void displayDepartmentList() {
		DepartmentControllerClient client = new DepartmentControllerClient();
		ResponseEntity<?> respons = client.getDepartmentList();
		System.out.println("\n" + respons);
		if (respons.hasBody() == false) {

			System.out.println("No response found");
			return;
		}

	}

	public ResponseEntity<?> getDepartmentList() {

		String serviceLocation = "department-list";

		String url = HOST + ":" + PORT + "/" + APP_NAME + "/" + serviceLocation;

		ResponseEntity<?> response = new RestTemplate().getForEntity(url,
				String.class);
		return response;
	
	
	}
}
