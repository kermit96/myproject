package iedu.data;

import java.util.Date;

public class BoardComment {
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
	public int seq;
	public int boardseq;
	public String writername;
	public String Content;
	public Date Createdate;
	public String isDelete;
}
