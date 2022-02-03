package product;

import java.text.DecimalFormat;

public class ProductVO {
	private int		product_num 	;
	private String	product_name	;
	private int		product_price	;
	private int		product_stock	;
	private double	rating			;
	private int		re_count		;
	private String	tag_key			;
	private String  file_path		;
	
	public int getProduct_num() {
		return product_num;
	}
	public void setProduct_num(int product_num) {
		this.product_num = product_num;
	}
	public String getProduct_name() {
		return product_name;
	}
	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}
	public int getProduct_price() {
		return product_price;
	}
	public void setProduct_price(int product_price) {
		this.product_price = product_price;
	}
	public int getProduct_stock() {
		return product_stock;
	}
	public void setProduct_stock(int product_stock) {
		this.product_stock = product_stock;
	}
	
	public double getRating() {
		return rating;
	}
	public void setRating(double rating) {
		this.rating = Math.round(rating * 100) / 100.0;
	}
	public int getRe_count() {
		return re_count;
	}
	public void setRe_count(int re_count) {
		this.re_count = re_count;
	}
	public String getTag_key() {
		return tag_key;
	}
	public void setTag_key(String tag_key) {
		this.tag_key = tag_key;
	}
	
	public String getFile_path() {
        return file_path;
    }

    public void setFile_path(String file_path) {
        this.file_path = file_path;
    }
	
}
