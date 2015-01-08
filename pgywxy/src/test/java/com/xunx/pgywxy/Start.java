package com.xunx.pgywxy;

import org.mortbay.jetty.Server;

import com.xunx.core.test.utils.JettyUtils;

/**
 * 使用Jetty运行调试Web应用, 在Console输入回车停止服务.
 * 
 * @author calvin
 */
public class Start {

	public static final int PORT = 8088;
	public static final String CONTEXT = "/product";
	public static final String BASE_URL = "http://localhost:8088/product";

	public static void main(String[] args) throws Exception {
		Server server = JettyUtils.buildNormalServer(PORT, CONTEXT);
		server.start();
		/*Could not load org/mybatis/spring/support/SqlSessionDaoSupport.class - [unknown location]
		*/

		System.out.println("Hit Enter in console to stop server");
		if (System.in.read() != 0) {
			server.stop();
			System.out.println("Server stopped");
			System.exit(0);
		}
	}
}
