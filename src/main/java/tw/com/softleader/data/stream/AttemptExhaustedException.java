package tw.com.softleader.data.stream;

/**
 * 代表嘗試次數到達上限了 (eg. after N attempts)
 *
 * @author Matt Ho
 */
public class AttemptExhaustedException extends IllegalStateException {

  public AttemptExhaustedException(String message) {
    super(message);
  }

  public AttemptExhaustedException(String message, Throwable cause) {
    super(message, cause);
  }
}
