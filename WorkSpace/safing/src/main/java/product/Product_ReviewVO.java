package product;

public class Product_ReviewVO {
	private int		review_num       ;
	private int		review_image_num ;
	private int		product_num      ;
	private String	member_id        ;
	private int		package_num      ;
	private	String	review_content   ;
	private String	review_date      ;
	
	public int getReview_num() {
		return review_num;
	}
	public void setReview_num(int review_num) {
		this.review_num = review_num;
	}
	public int getReview_image_num() {
		return review_image_num;
	}
	public void setReview_image_num(int review_image_num) {
		this.review_image_num = review_image_num;
	}
	public int getProduct_num() {
		return product_num;
	}
	public void setProduct_num(int product_num) {
		this.product_num = product_num;
	}
	public String getMember_id() {
		return member_id;
	}
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	public int getPackage_num() {
		return package_num;
	}
	public void setPackage_num(int package_num) {
		this.package_num = package_num;
	}
	public String getReview_content() {
		return review_content;
	}
	public void setReview_content(String review_content) {
		this.review_content = review_content;
	}
	public String getReview_date() {
		return review_date;
	}
	public void setReview_date(String review_date) {
		this.review_date = review_date;
	}
	
	
	
}
