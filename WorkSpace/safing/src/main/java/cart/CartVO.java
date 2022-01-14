package cart;

public class CartVO {
	private int		cart_num	  ; 
	private String	member_id	  ; 
	private int		product_num   ;	
	private int		package_num   ;	
	private int		product_price ;	
	private int		product_cnt   ;
	
	public int getCart_num() {
		return cart_num;
	}
	public void setCart_num(int cart_num) {
		this.cart_num = cart_num;
	}
	public String getMember_id() {
		return member_id;
	}
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	public int getProduct_num() {
		return product_num;
	}
	public void setProduct_num(int product_num) {
		this.product_num = product_num;
	}
	public int getPackage_num() {
		return package_num;
	}
	public void setPackage_num(int package_num) {
		this.package_num = package_num;
	}
	public int getProduct_price() {
		return product_price;
	}
	public void setProduct_price(int product_price) {
		this.product_price = product_price;
	}
	public int getProduct_cnt() {
		return product_cnt;
	}
	public void setProduct_cnt(int product_cnt) {
		this.product_cnt = product_cnt;
	}	
	
	
		                          
}
