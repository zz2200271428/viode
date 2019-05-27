package file.service.web.handler;

import file.service.web.exception.FileServiceException;
import file.service.web.handler.impl.FileUploadHandler;
import file.service.web.handler.impl.ImageRatioZoomHnadler;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @author chenzhwmf
 * @version V1.0
 * @Project goodpersongroup
 * @Package com.life.file.service.web.handle
 * @Date 2017-4-1
 */
public class HandlerDispatcher implements FileHandler {

    /**
     * 文件处理转发工厂实例
     */
    private Log logger = LogFactory.getLog(HandlerDispatcher.class);

    /**
     * 文件处理者和命令对应的关系
     */
    private Map<String, FileHandler> handlerMap = null;

    public HandlerDispatcher() {
        handlerMap = new HashMap<>();
        register();
    }

    /**
     * 注册文件处理器
     */
    public void register() {
        handlerMap.put(CMD_SAVE, new FileUploadHandler());
        handlerMap.put(CMD_RATIO_ZOOM, new ImageRatioZoomHnadler());
    }

    @Override
    public void handle(HandlerParameter parameter) throws FileServiceException {
        HttpServletRequest request = parameter.getRequest();
        HttpServletResponse response = parameter.getResponse();
        ServletContext servletContext = parameter.getServletContext();

        String cmd = request.getParameter("cmd");
        logger.info("action cmd[" + cmd + "]");

        if (StringUtils.isBlank(cmd) || (!handlerMap.containsKey(cmd))) {
            logger.error("commend can not fund or can not assign handle. ");
            throw new FileServiceException("commend can not fund or can not assign handle. ");
        }
        FileHandler fileHandler = handlerMap.get(cmd);
        fileHandler.handle(new HandlerParameter(request, response, servletContext));
    }
}
