/**
 * 
 */
package file.service.web.exception;

/**
 * @author sean.he
 * 
 */
public class FileServiceException extends RuntimeException {

	private static final long serialVersionUID = 1793760050084714190L;

	public FileServiceException() {
		super();
	}

	public FileServiceException(String msg) {
		super(msg);
	}

	public FileServiceException(String msg, Throwable t) {
		super(msg, t);
	}

	public FileServiceException(Throwable t) {
		super(t);
	}
}
