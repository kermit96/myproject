package iedu.dao;

import java.util.Date;
import java.util.Vector;

public class BoardDao {
   public String title;
   public String write;
   public Date date;
   public String content;
   public int hit;   
   public int no;   
   public Vector<BoardReply> replys = new Vector<BoardReply>();   
   
}
