package net.planeteronde.bernard.backend.data;

public class Role {
	public static final String RESP_PROJET = "responsable de projet";
	public static final String RESP_PORTFOLIO = "responsable du portfolio";
	public static final String RESP_SYSTEME = "responsable du système";

	private Role() {
		// Static methods and fields only
	}

	public static String[] getAllRoles() {
		return new String[] { RESP_PROJET, RESP_PORTFOLIO, RESP_SYSTEME };
	}

}
