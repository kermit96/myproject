package iedu.dao;

import java.util.Date;

public class BoardReply {
/*
 * 
 *   `rr_No` bigint(20) NOT NULL AUTO_INCREMENT,
  `rb_No` int(11) NOT NULL,
  `rr_Writer` nvarchar(20) DEFAULT NULL,
  `rr_Content` longtext,
  `rr_isShow` char(1) DEFAULT 'Y',
  `rr_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
 * 
 */
	public int rr_No;
	public int rb_No;
	public String rr_Writer;
	public String rr_Content;
	public Date rr_date;
}
