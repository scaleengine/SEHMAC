String sehmac_sign(String key, String token_name, String ipAddress, String window, String acl, String payload, String salt) {
	Dictionary token_config = new Hashtable();
	String hmac_token = "";

	if(token_name == null || token_name.isEmpty()) {
		token_name = "hdnea";
	}

	token_config.put("token_name", token_name);
	token_config.put("key", key);
	token_config.put("algo", new String("sha256"));
	token_config.put("salt", salt);
	token_config.put("field_delimiter", new String("~"));
	token_config.put("acl_delimiter", new String("!"));
	token_config.put("escape_early", new String("false"));
	token_config.put("escape_early_upper", new String("false"));

	if (ipAddress != null && !ipAddress.isEmpty()) {
		token_config.put("ip_address", ipAddress);
	}
	if (window != null && !window.isEmpty()) {
		token_config.put("window_seconds", window);
	}
	if (acl != null && !acl.isEmpty()) {
		token_config.put("acl", acl);
	}
	if (payload != null && !payload.isEmpty()) {
		token_config.put("payload", payload);
	}
		
	hmac_token = AkamaiToken.generateToken(token_config);

	return (hmac_token);
}
