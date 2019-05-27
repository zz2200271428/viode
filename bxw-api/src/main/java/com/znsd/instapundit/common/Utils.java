package com.znsd.instapundit.common;


import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;
public class Utils {
	public static String getIpAddr(HttpServletRequest request) {
		String client_ip = request.getHeader("x-forwarded-for");
		if (client_ip == null || client_ip.length() == 0 || "unknown".equalsIgnoreCase(client_ip)) {
			client_ip = request.getHeader("Proxy-Client-IP");
		}
		if (client_ip == null || client_ip.length() == 0 || "unknown".equalsIgnoreCase(client_ip)) {
			client_ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (client_ip == null || client_ip.length() == 0 || "unknown".equalsIgnoreCase(client_ip)) {
			client_ip = request.getRemoteAddr();
			if (client_ip.equals("127.0.0.1") || client_ip.equals("0:0:0:0:0:0:0:1")) {
				// 根据网卡取本机配置的IP
				InetAddress inet = null;
				try {
					inet = InetAddress.getLocalHost();
				} catch (UnknownHostException e) {
					e.printStackTrace();
				}
				client_ip = inet.getHostAddress();
			}
		}
		// 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
		if (client_ip != null && client_ip.length() > 15) { // "***.***.***.***".length() = 15
			if (client_ip.indexOf(",") > 0) {
				client_ip = client_ip.substring(0, client_ip.indexOf(","));
			}
		}

		String server_ip = null;
		try {
			server_ip = InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		return server_ip;
	}
}
