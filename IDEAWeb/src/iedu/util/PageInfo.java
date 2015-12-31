package iedu.util;
/*
 * 	이 클래스는 페이지 나눔 기능이 필요한 곳에 페이지 나눔 기능에 필요한 각종 데이터를 처리해 줄 클래스
 */
public class PageInfo {
	public		int		nowPage;			//	현재 페이지
	public		int		totalCount;		//	총 데이터 개수
	public		int		pageList;			//	한 페이지에 보여줄 게시물의 갯수
	public		int		pageGroup;		//	한 화면에서 선택할 수 있는 페이지 수
	public		int		startPage;		//	현재 화면에서시작할 페이지 수
	public		int		endPage;			//	현재 화면에서 종료될 페이지 수
	public		int		totalPage;		//	총 페이지수
	
	//	생성자 함수를 이용해서 필수적인 내용을 입력 받도록 한다.
	public PageInfo(int nowPage, int totalCount) {
		this(nowPage, totalCount, 10, 5);
	}
	//	게시판 종류마다 한 화면에 보여줄 개시물의 개수가 달라질 수도 있다.
	//	이것역시 생성자 함수를 이용해서 사용자가 입력하도록 하자.
	public PageInfo(int nowPage, int totalCount, int pageList, int pageGroup) {
		this.nowPage = nowPage;
		this.totalCount = totalCount;
		this.pageList = pageList;
		this.pageGroup = pageGroup;
	}
	//	이제 필요한 나머지 정보를 계산하자.
	public void calcInfo() {
		//	총 페이지 수
		totalPage = ((totalCount % pageList) == 0) ? totalCount / pageList : (totalCount / pageList) + 1;
		//	시작 페이지 구하기
		//		현재 페이지가 무엇인가에 따라 시작 페이지가 달라진다.
		//		예>
		//			현재 페이지가 2, 3페이지		
		//			[1][2][3][4][5]						1그룹
		//			현재 페이지가 7페이지
		//			[6][7][8][9][10]					2그룹
		//	즉	1~5페이지까지는 같은 그룹이 나와야 하고	6~10페이지까지도 같은 그룹이 나와야 한다.
		//		이것을 처리하기 위해서는 현재 페이지가 어떤 그룹에 속하는지를 알아야겠다.
		int	tempGroup = ((nowPage % pageGroup) == 0) ? nowPage / pageGroup - 1: (nowPage / pageGroup);
		//		예>		1			0	
		//					2			0
		//					3			0
		//					4			0
		//					5			0
		//					6			1
		//		시작페이지		group		0				1
		//										1				6
		//										2				11
		startPage = tempGroup * pageGroup + 1;
		endPage = startPage + pageGroup - 1;			//	계산
		//	마지막 페이지가 총페이지수 보다 작으면 마지막페이지를 사용할 수 없다.
		if(endPage > totalPage) {
			endPage = totalPage;
		}
	}
	public void calcInfo2() {
		//	총 페이지 수
		totalPage = ((totalCount % pageList) == 0) ? totalCount / pageList : (totalCount / pageList) + 1;
		//	가운데로 현재 페이지가 가기 위해서 가운데 부분을 구한다.
		int		gab = pageGroup / 2;
		startPage = nowPage - gab;
		//	시작 페이지가 1페이지보다 작으면 강제로 1페이지로 한다.
		if(startPage < 1) {
			startPage = 1;
		}
		//	마지막 페이지는 시작 페이지에서 5만큼 떨어진다.
		endPage = startPage + pageGroup - 1;
		//	계산된 마지막 페이지가 총 페이지보다 크면 강제로 마지막을 총 페이지로 한다.
		if(endPage > totalPage) {
			endPage = totalPage;
			//	마지막 페이지가 다시 계산되었으므로 시작 페이지를 다시 계산한다.
			startPage = endPage - pageGroup + 1;
			//	데이터가 몇개 없어서 5개를 다 출력하지 못하면(즉, 시작 페이지가 음수가 되면....)
			//	어쩔수 없이 1페이지부터 보이자.
			if(startPage < 1) {
				startPage = 1;
			}
		}
	}
	//		숙제
	//				현재 페이지보다 2만큼 적고	현재 페이지보다 2만큼 크게 나오도록 하자.
	//		예>		현재페이지			7
	//											[5][6][7][8][9]
	//					현재페이지가 		1, 2페이지인 경우는 앞쪽에 2만큼 없다.
	//					현재페이지가			마지막 페이지인 경우는 뒤쪽에 2만큼 없다.
	public int getNowPage() {
		return nowPage;
	}
	public void setNowPage(int nowPage) {
		this.nowPage = nowPage;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public int getPageList() {
		return pageList;
	}
	public void setPageList(int pageList) {
		this.pageList = pageList;
	}
	public int getPageGroup() {
		return pageGroup;
	}
	public void setPageGroup(int pageGroup) {
		this.pageGroup = pageGroup;
	}
	public int getStartPage() {
		return startPage;
	}
	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}
	public int getEndPage() {
		return endPage;
	}
	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	
}
