package board;

public class Board_CommentVO {
	private int		comment_id     		;
	private int		board_id       		;
	private String	member_id       	;
	private	String	comment_content     ;
	private String	comment_regdate 	;
	private int		comment_lev         ;
	private int		comment_seq         ;
	
	public int getComment_id() {
		return comment_id;
	}
	public void setComment_id(int comment_id) {
		this.comment_id = comment_id;
	}
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
	public String getComment_content() {
		return comment_content;
	}
	public void setComment_content(String comment_content) {
		this.comment_content = comment_content;
	}
	public String getComment_regdate() {
		return comment_regdate;
	}
	public void setComment_regdate(String comment_regdate) {
		this.comment_regdate = comment_regdate;
	}
	public int getComment_lev() {
		return comment_lev;
	}
	public void setComment_lev(int comment_lev) {
		this.comment_lev = comment_lev;
	}
	public int getComment_seq() {
		return comment_seq;
	}
	public void setComment_seq(int comment_seq) {
		this.comment_seq = comment_seq;
	}
	
	
	
	
                                
}
