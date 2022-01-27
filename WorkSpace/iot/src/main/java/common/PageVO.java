package common;

public class PageVO {

	int	totalList;			// 총 글의 건수 (DB에서 조회해 온 전체 글수)
	int totalPage;			// 총 페이지 수
	int totalBlock;			// 총 블럭 수
	int pageList = 10;		// 페이지당 보여질 목록의 수
	int blockPage = 10;		// 블럭당 보여질 페이지 수
	int curPage;			// 현재 페이지
	int beginList, endList;	// 각 페이지에 보여질 시작 목록번호, 끝 목록번호
	int curBlock;			// 현재 블럭
	int beginPage, endPage;	// 각 블럭에 보여질 시작, 끝 페이지 번호
	String search, keyword;	// 검색 조건, 검색어
	private String viewType ="list";	// 게시판 형태 (기본 : 목록형태)
	
	public String getViewType() {
		return viewType;
	}
	public void setViewType(String viewType) {
		this.viewType = viewType;
	}
	public String getSearch() {
		return search;
	}
	public void setSearch(String search) {
		this.search = search;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public int getTotalList() {
		return totalList;
	}
	public void setTotalList(int totalList) {
		this.totalList = totalList;	// DB에서 받아온 총 글의 건수
		totalPage = totalList / pageList; 
		// 총 1024건의 글을 페이지당 10건씩 나타낸다면 1024/10 : 102.4 총 페이지는 102 + 1 
		if ( totalList % pageList > 0 ) ++totalPage;
		
		totalBlock = totalPage / blockPage;
		// 총 페이지수 103 / 10 : 총 블럭수는 10 + 1
		if ( totalPage % blockPage > 0 ) ++totalBlock;
		
		endList = totalList - (curPage-1) * pageList;		
		// 총 목록수 - (페이지번호-1) * 페이지당 보여질 목록수
		beginList = endList - (pageList -1); 
		// 끝 목록번호 - (페이지당 보여질 목록수 - 1)

		curBlock = curPage / blockPage;
		// 블럭번호(curBlock) : 페이지번호(curPage) / 블럭당 보여질 페이지수(blockPage)
		if ( curPage % blockPage > 0 ) ++curBlock;
		
		endPage = curBlock * blockPage;
		// 각 블럭의 끝 페이지번호 : 블럭번호 * 블럭당 보여질 페이지수
		beginPage = endPage - (blockPage - 1);
		// 각 블럭의 시작 페이지번호 : 끝 페이지번호 - (블럭당 보여질 페이지수 - 1)
		
		// 마지막 블럭에서 끝 페이지번호가 총 페이지수보다 클 수 없으므로
		// 총 페이지수를 끝 페이지번호로 한다
		if (endPage > totalPage ) endPage = totalPage;
		
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public int getTotalBlock() {
		return totalBlock;
	}
	public void setTotalBlock(int totalBlock) {
		this.totalBlock = totalBlock;
	}
	public int getPageList() {
		return pageList;
	}
	public void setPageList(int pageList) {
		this.pageList = pageList;
	}
	public int getBlockPage() {
		return blockPage;
	}
	public void setBlockPage(int blockPage) {
		this.blockPage = blockPage;
	}
	public int getCurPage() {
		return curPage;
	}
	public void setCurPage(int curPage) {
		this.curPage = curPage;
	}
	public int getBeginList() {
		return beginList;
	}
	public void setBeginList(int beginList) {
		this.beginList = beginList;
	}
	public int getEndList() {
		return endList;
	}
	public void setEndList(int endList) {
		this.endList = endList;
	}
	public int getCurBlock() {
		return curBlock;
	}
	public void setCurBlock(int curBlock) {
		this.curBlock = curBlock;
	}
	public int getBeginPage() {
		return beginPage;
	}
	public void setBeginPage(int beginPage) {
		this.beginPage = beginPage;
	}
	public int getEndPage() {
		return endPage;
	}
	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}
	
	
}
