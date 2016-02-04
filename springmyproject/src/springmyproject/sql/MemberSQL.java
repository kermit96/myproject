package springmyproject.sql;

public class MemberSQL {
	
	public static final int	GETSIDO = 1;
	public static final int	GETGUGUN = 2;
	public static final int	GETDONG = 3;
	public static final int	GETROAD = 4;
	public static final int	GETZIPCODE = 5;
	
	
	public static String getSQL(int code) {
		StringBuffer	buff = new StringBuffer();
		switch(code) {
		case 	GETSIDO:		//	시도 검색
			buff.append("SELECT ");
			buff.append("		distinct Sido AS SIDO ");
			buff.append("FROM ");
			buff.append("		ZipCode ");
			buff.append("ORDER BY ");
			buff.append("		Sido ASC ");
			break;
		case	GETGUGUN:		//	구군 검색
			buff.append("SELECT ");
			buff.append("		distinct GunGu AS GUGUN ");
			buff.append("FROM ");
			buff.append("		ZipCode ");
			buff.append("WHERE ");
			buff.append("		sido = ? ");
			buff.append("ORDER BY GunGu ASC ");
			break;
		case	GETDONG:		//	읍면동검색
			buff.append("SELECT ");
			buff.append("		distinct DongName1 AS DONG ");
			buff.append("FROM ");
			buff.append("		ZipCode ");
			buff.append("WHERE ");
			buff.append("				sido = ? ");
			buff.append("		AND 	gungu = ? ");
			buff.append("ORDER BY DongName1 ASC ");
			break;
		case	GETROAD:		//	읍면동검색
			buff.append("SELECT ");
			buff.append("		distinct Road AS Road ");
			buff.append("FROM ");
			buff.append("		ZipCode ");
			buff.append("WHERE ");
			buff.append("				sido = ? ");
			buff.append("		AND 	gungu = ? ");
			buff.append("		AND 	DongName1 = ? ");
			buff.append("ORDER BY Road ASC ");
			break;
		case	GETZIPCODE:
			buff.append("SELECT ");
			buff.append("		ZipCode AS ZIP, ");
			buff.append("		Sido AS SIDO, ");
			buff.append("		GunGu AS GUGUN, ");
			buff.append("		DongName1 AS DONG,  ");
			buff.append("		Road AS ROAD, ");
			buff.append("		nvl(BlgName,'') AS BLG, ");
			buff.append("		Jibun AS JIBUN ");
			buff.append("FROM ");
			buff.append("		ZipCode ");
			buff.append("WHERE ");
			buff.append("		Sido = ? ");
			buff.append("		AND Gungu = ? ");
			buff.append("		AND DongName1 = ? ");
			buff.append("		AND Road = ? ");
			buff.append("ORDER BY ");
			buff.append("		Road ASC ");
			break;
		}
		return buff.toString();
	}
}