/**
 * 
 */
package file.service.web.handler;

/**
 * @Title: HandlerProxy
 * @Description: 文件处理分发代理类
 * @author wen578351314@gmail.com
 * @date 2015年12月9日
 * @version 0.0.1-SNAPSHOT
 */
public class HandlerDispatcherFactory {

	/**
	 * 文件处理转发工厂实例
	 */
	private static HandlerDispatcherFactory factory;

	private HandlerDispatcherFactory() {

	}

	/**
	 * 获取工厂实例
	 * @return 处理者分发工厂实例
     */
	public synchronized static HandlerDispatcherFactory getInstance() {
		if (factory == null) {
			factory = new HandlerDispatcherFactory();
		}
		return factory;
	}

	public FileHandler createHandler() {
		return new HandlerDispatcher();
	}
}
