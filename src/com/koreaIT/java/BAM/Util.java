package com.koreaIT.java.BAM;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Util {
	static String getNowDate() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

		LocalDateTime now = LocalDateTime.now();

		String formatedNow = now.format(formatter);

		return formatedNow;
	}
}
