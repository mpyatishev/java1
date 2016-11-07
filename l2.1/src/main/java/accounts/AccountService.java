package accounts;

import java.util.HashMap;
import java.util.Map;

public class AccountService {
	private final Map<String, UserProfile> loginToProfile;
	private final Map<String, UserProfile> sessionIdToProfile;

	public AccountService() {
		loginToProfile = new HashMap<>();
		sessionIdToProfile = new HashMap<>();
	}

	public void addNewUser(UserProfile userProfie) {
		loginToProfile.put(userProfie.getLogin(), userProfie);
	}

	public UserProfile getUserByLogin(String login) {
		return loginToProfile.get(login);
	}

	public UserProfile getUserBySession(String sessionId) {
		return sessionIdToProfile.get(sessionId);
	}

	public void addSession(String sessionId, UserProfile userProfie) {
		sessionIdToProfile.put(sessionId, userProfie);
	}

	public void deleteSession(String sessionId) {
		sessionIdToProfile.remove(sessionId);
	}
}
