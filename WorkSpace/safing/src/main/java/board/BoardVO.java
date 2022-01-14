package board;

public class BoardVO {
	private int	   board_id	        ;	
	private String member_id	    ;   
	private String board_title	    ;   
	private String board_content	;   
	private String board_writedate	;   
	private String board_updatedate	;	
	private int	   board_read_cnt	;   
	private int    board_like_cnt	;   
	private String board_kinds   	;
	
	public int getBoard_id() {
		return board_id;
	}
	public void setBoard_id(int board_id) {
		this.board_id = board_id;
	}
	public String getMember_id() {
		return member_id;
	}
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	public String getBoard_title() {
		return board_title;
	}
	public void setBoard_title(String board_title) {
		this.board_title = board_title;
	}
	public String getBoard_content() {
		return board_content;
	}
	public void setBoard_content(String board_content) {
		this.board_content = board_content;
	}
	public String getBoard_writedate() {
		return board_writedate;
	}
	public void setBoard_writedate(String board_writedate) {
		this.board_writedate = board_writedate;
	}
	public String getBoard_updatedate() {
		return board_updatedate;
	}
	public void setBoard_updatedate(String board_updatedate) {
		this.board_updatedate = board_updatedate;
	}
	public int getBoard_read_cnt() {
		return board_read_cnt;
	}
	public void setBoard_read_cnt(int board_read_cnt) {
		this.board_read_cnt = board_read_cnt;
	}
	public int getBoard_like_cnt() {
		return board_like_cnt;
	}
	public void setBoard_like_cnt(int board_like_cnt) {
		this.board_like_cnt = board_like_cnt;
	}
	public String getBoard_kinds() {
		return board_kinds;
	}
	public void setBoard_kinds(String board_kinds) {
		this.board_kinds = board_kinds;
	} 
	
}
