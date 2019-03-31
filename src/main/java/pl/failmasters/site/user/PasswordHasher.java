package pl.failmasters.site.user;

import java.nio.charset.StandardCharsets;

import com.google.common.hash.Hashing;

public class PasswordHasher {

	private String password;

	public PasswordHasher(String password) {
		this.password = password;
	}

	public String hash() {

		return Hashing.sha256().hashString(password, StandardCharsets.UTF_8).toString();
	}

}
