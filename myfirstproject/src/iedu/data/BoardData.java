package iedu.data;

import java.util.Date;
import java.util.Vector;

public class BoardData {
   public String title;
   public String writename;
   public int writerseq;   
   public Date createdate;
   public Date updatedate;
   public String content;
   public int hit;   
   public int seq;   
   
   public int ref_seq;
   public int step;
   public int order;
   
   public Vector<BoardComment> replys = new Vector<BoardComment>();   
   
}
