package file.service.web.handler.impl;

import file.service.web.exception.FileServiceException;
import file.service.web.handler.FileHandler;
import file.service.web.handler.HandlerParameter;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.ServletException;

/**
 * 抽象的文件处理器,加入了处理之间和处理之后的业务逻辑方法
 * Created by wen578351314@gmail.com
 * Date 15/12/11
 */
public abstract class AbstractFileHandler implements FileHandler {

    private Log logger = LogFactory.getLog(getClass());

    /**
     * 处理之前的业务逻辑
     *
     * @param handlerParameter TODO
     */
    protected void before(HandlerParameter handlerParameter) {
        if (logger.isDebugEnabled()) {
            logger.debug("can not implements before method.");
        }
    }

    /**
     * 处理之后的业务所及
     *
     * @param handlerParameter TODO
     */
    protected void after(HandlerParameter handlerParameter) {
        if (logger.isDebugEnabled()) {
            logger.debug("can not implements after method.");
        }
    }

    @Override
    public void handle(HandlerParameter handlerParameter) throws FileServiceException {
        this.before(handlerParameter);
        try {
            this.doHandler(handlerParameter);
        } catch (FileServiceException e) {
            throw new FileServiceException(e);
        } finally {
            this.after(handlerParameter);
        }
    }

    /**
     * @param handlerParameter TODO
     * @throws FileServiceException TODO
     * @throws ServletException
     */
    protected abstract void doHandler(HandlerParameter handlerParameter) throws FileServiceException;
}
