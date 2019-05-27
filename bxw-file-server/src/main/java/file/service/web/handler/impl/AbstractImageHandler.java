/**
 *
 */
package file.service.web.handler.impl;

import file.service.web.handler.HandlerParameter;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

/**
 * @author wen578351314@gmail.com
 * @date 2016年8月24日
 * @since 0.0.1-SNAPSHOT
 */
public abstract class AbstractImageHandler extends AbstractFileHandler {

    private Log logger = LogFactory.getLog(getClass());

    /**
     * 图片保存路径
     */
    protected String fileSavePath;

    /**
     * @param fileSavePath
     *            the fileSavePath to set
     */
    protected void setFileSavePath(String fileSavePath) {
        this.fileSavePath = fileSavePath;
    }

    /**
     * @param servletContext
     *            the servletContext to set
     */
    protected void setFileSavePath(ServletContext servletContext) {
        if (this.fileSavePath == null || StringUtils.isEmpty(this.fileSavePath)) {
            this.setFileSavePath(servletContext.getRealPath("/"));
            logger.info("setFileSavePath|fileSavePath=" + fileSavePath);
        }
    }

    @Override
    protected void before(HandlerParameter handlerParameter) {
        // 设置文件保存位置
        HttpServletRequest request = handlerParameter.getRequest();
        String rootPath = request.getParameter("rootPath");
        if (StringUtils.isNotEmpty(rootPath)) {
            this.setFileSavePath(rootPath);
        } else {
            setFileSavePath(handlerParameter.getServletContext());
        }
    }

    /* (non-Javadoc)
     * @see com.life.file.service.web.handle.AbstractFileHandler#doHandler(com.life.file.service.web.handle.HandlerParameter)
     */
    @Override
    protected abstract void doHandler(HandlerParameter handlerParameter);
}