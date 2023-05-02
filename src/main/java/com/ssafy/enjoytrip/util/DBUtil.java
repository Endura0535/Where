package com.ssafy.enjoytrip.util;

import org.springframework.stereotype.Component;

@Component
public class DBUtil {
	public void close(AutoCloseable... acs) {
		for(AutoCloseable c: acs) {
			if (c != null) {
				try {
					c.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
}

