package product;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class ProductDAO {
	
	@Autowired @Qualifier("bteam") private SqlSession sql;
	
	//상품 등록 
	public boolean insert(ProductVO vo) {
		return sql.insert("product.mapper.insert", vo) == 1 ? true : false;
	}
	
	//상품정보 목록
	public List<ProductVO> list(ProductVO vo) {
		return sql.selectList("product.mapper.list", vo);
	}
	
	//상품 상세정보
	public ProductVO detail(ProductVO vo) {
		return sql.selectOne("product.mapper.detail", vo);
	}
	
	//상품 정보수정
	public boolean update(ProductVO vo) {
		return sql.update("product.mapper.update", vo) == 1 ? true : false;
	}
	
	//상품 삭제
	public boolean delete(ProductVO vo) {
		return sql.delete("product.mapper.delete", vo) == 1 ? true : false;
	}
	
	//패키지 리스트
	public List<Product_PackageVO> package_list(){
		return sql.selectList("product.mapper.package_list");
		
	}
	
	//상품 리스트
	public List<ProductVO> product_list(String search){
		return sql.selectList("product.mapper.product_list", search);
		
	}
	
	//이미지등록
	public boolean img_insert(ImginsertVO vo){
		return sql.update("product.mapper.insert_img", vo)== 1 ? true : false;
		
	}
	
	//태그등록
	public boolean tag_insert(TagVO vo){
		return sql.update("product.mapper.insert_tag", vo)== 1 ? true : false;
	}
		
	//상품등록
	public boolean pro_insert(ProductinVO vo){
		return sql.update("product.mapper.insert_pro", vo)== 1 ? true : false;
		
	}
}
