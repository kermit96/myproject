/**
 * 
 */
package iedu.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author 태섭
 *
 */
public interface BoarderMain {
     public String action(HttpServletRequest req,HttpServletResponse resp);
}
