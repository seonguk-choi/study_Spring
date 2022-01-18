package common;

public class PageVO {
	int totalList; 			//총 목록수
	int totalPage;			//총 페이지수
	int totalBlock; 		//총 블럭수
	int pageList = 10; 		// 페이지당 보여질 목록의 수
	int blockPage = 10; 	// 블록다 보여질 페이지 수
	int curPage; 			//현재 페이지 값
	int beginList, endList; //각 페이지 시작, 끝 목록번호
	int curBlock;			//현재 블럭
	int beginPage, endPage; //각 블럭에 보여질 시작, 끝 페이지 번호
	
	public int getTotalList() {
		return totalList;
	}
	public void setTotalList(int totalList) {
		//DB에서 받아온 총 글의 건수
		this.totalList = totalList;
		
		//총 페이지수
		totalPage = totalList/pageList;
		if(totalList%pageList > 0) ++totalPage;
		
		totalBlock = totalPage / blockPage;
		if(totalPage % blockPage > 0) ++totalBlock;
		
		endList = totalList - (curPage - 1) * pageList;
		beginList = endList - (pageList - 1);
		
		curBlock = curPage / blockPage;
		if(curPage % blockPage > 0) ++curBlock;
		
		endPage = curBlock * blockPage;
		beginPage = endPage - (blockPage -1);
		
		//마지막 블럭에서 끝 페이지번호가 총페이지수 보다 크지 않도록 한다
		if(endPage > totalPage) endPage = totalPage;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public int getTotalBlokc() {
		return totalBlock;
	}
	public void setTotalBlokc(int totalBlokc) {
		this.totalBlock = totalBlokc;
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
