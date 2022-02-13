package com.diablo73.springApps.springBootNoSQLApplication.drms;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Field;

@Data
@Configuration
public class ReplItDbDrm {

	@Autowired
	private RestTemplate restTemplate;

	private String replDbURL = System.getenv("REPLIT_DB_URL").concat("/");

	public boolean kamonStatusOn = false;



	public boolean getKamonStatusOn() {
		return Boolean.parseBoolean(replDbCall("kamonStatusOn"));
	}

	private String replDbCall(String key) {
		String value = "";
		try {
			HttpEntity<String> httpEntity = new HttpEntity(null);

			ResponseEntity<String> response = restTemplate.exchange(
					replDbURL + key,
					HttpMethod.GET,
					httpEntity,
					String.class
			);

			value = response.getBody();
		} catch (Exception e) {
			Field field = null;
			try {
				field = ReplItDbDrm.class.getDeclaredField(key);
				field.setAccessible(true);
				value = (String) field.get(field);
			} catch (NoSuchFieldException | IllegalAccessException ex) {
				System.out.println(e.getMessage());
			}
		}
		return value;
	}

}
