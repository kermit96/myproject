package MyMaven.MyMaven;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Vector;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.annotate.JsonView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


class Views {

	public static class NameOnly{};
	public static class ValueAndName extends NameOnly{};

}


class aa2 {
	@JsonView(Views.NameOnly.class)
	int a;
	@JsonView(Views.NameOnly.class)
	int b;
	@JsonView(Views.NameOnly.class)
	String  c;
	@JsonView(Views.NameOnly.class)
	String[] aa ;
}
public class App 
{
	public static void main( String[] args )
	{

		aa2 bb = new aa2();
		bb.a = 10;
		bb.b =20;
		bb.c="20";
	/*	
		bb.aa= new String[10];
		
		bb.aa[0]= "11212";
		bb.aa[1]= "11212";
		bb.aa[2]= "11212";
		bb.aa[3]= "11212";
		bb.aa[4]= "11212";
		bb.aa[5]= "11212";
		bb.aa[6]= "11212";
		bb.aa[7]= "11212";
		bb.aa[8]= "11212";
		bb.aa[9]= "11212-20-30";
		*/
		
		
//       bb.aa = new Vector();
//       bb.aa.add("aaa");
 //      bb.aa.add("aaa1");
  //     bb.aa.add("aaa2");
		ObjectMapper mapper = new ObjectMapper();

		try {
			String jsonInString = mapper.writeValueAsString(bb);
			System.out.println(jsonInString);
		} catch (Exception ex) {
          ex.printStackTrace();
		}



       Gson gson = new GsonBuilder().serializeNulls().create();
//	     Gson gson = new Gson();

       String jsonstr = gson.toJson(bb);
       
       
    	System.out.println(jsonstr);

	//	System.out.println( "Hello World!" );
	}
}
