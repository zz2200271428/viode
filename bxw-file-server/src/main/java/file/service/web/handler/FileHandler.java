/**
 *
 */
package file.service.web.handler;

import file.service.web.exception.FileServiceException;

/**
 * 文件服务处理接口
 *
 * @author wen578351314@gmail.com
 * @date 2015年12月8日
 * @since 1.0.0-SNAPSHOT
 */
public interface FileHandler {

    /**
     * 文件保存
     */
    String CMD_SAVE = "save";

    /**
     * 图片截取
     */
    String CMD_RATIO_ZOOM = "ratioZoom";

    /**
     * 文件服务处理器
     *
     * @param handlerParameter
     * @throws FileServiceException TODO
     */
    void handle(HandlerParameter handlerParameter) throws FileServiceException;
}
