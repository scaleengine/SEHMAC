<?php

function sehmac_sign($secret, $window = 21600, $ip_address = NULL, $start_time = NULL, $exp_time = NULL, $acl = "/*", $payload = NULL, $salt = NULL) {
	$string = "";
	if ($ip_address) {
		$string .= "ip={$ip_address}~";
	}

	if ($start_time) {
		$string .= "st={$start_time}~";
	}

	if ($exp_time) {
		$string .= "exp={$exp_time}~";
	} elseif ($window) {
		$end_time = strtotime("+{$window} seconds");
		$string .= "exp={$end_time}~";
	}

	if ($acl) {
		$string .= "acl={$acl}~";
	}

	if ($payload) {
		$string .= "data={$payload}~";
	}

	$string = rtrim($string, '~');

	$salt = '';
	if ($salt)
	{
		$salt = "~salt={$salt}";
	}

	$hash = "hdnea={$string}~hmac=" . hash_hmac("SHA256", $string . $salt, hex2bin($secret));

	return $hash;
}
