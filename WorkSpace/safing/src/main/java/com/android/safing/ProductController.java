package com.android.safing;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.tagext.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import com.google.gson.Gson;

import board.BoardDAO;
import common.CommonService;
import common.OutPrintln;
import member.MemberDAO;
import member.MemberVO;
import product.ImginsertVO;
import product.ProductDAO;
import product.ProductVO;
import product.Product_PackageVO;
import product.ProductinVO;
import product.TagVO;

@Controller
public class ProductController {
	Gson gson = new Gson();
	
	@Autowired private CommonService service;
	@Autowired private OutPrintln outprintln;
	@Autowired private ProductDAO dao;

	
	//상품 등록
	@ResponseBody
	@RequestMapping("/insert.pro")
	public void join(HttpServletRequest req, HttpServletResponse res, HttpSession session) throws Exception {
		PrintWriter writer = outprintln.outprintln(req, res);

	}

	//상품정보 목록
	@ResponseBody
	@RequestMapping("/list.pro")
	public void  list(HttpServletRequest req, HttpServletResponse res) throws Exception{
		PrintWriter writer = outprintln.outprintln(req, res);
	}
	
	//상품 상세정보
	@ResponseBody
	@RequestMapping("/detail.pro")
	public void  detail(HttpServletRequest req, HttpServletResponse res) throws Exception{
		PrintWriter writer = outprintln.outprintln(req, res);
	}
	
	//상품 정보수정
	@ResponseBody
	@RequestMapping("/update.pro")
	public void  update(HttpServletRequest req, HttpServletResponse res) throws Exception{
		PrintWriter writer = outprintln.outprintln(req, res);
	}
	
	//상품 삭제
	@ResponseBody
	@RequestMapping("/delete.pro")
	public void  delete(HttpServletRequest req, HttpServletResponse res) throws Exception{
		PrintWriter writer = outprintln.outprintln(req, res);
	}
	
	//패키지 리스트
	@ResponseBody
	@RequestMapping("/package_rec.sh")
	public void  package_list(HttpServletRequest req, HttpServletResponse res) throws Exception{
		List<Product_PackageVO> list = dao.package_list();
		req.setCharacterEncoding("UTF-8");
		res.setCharacterEncoding("UTF-8");
		res.setContentType("text/html");
		PrintWriter writer = res.getWriter();
		writer.println( gson.toJson(list));
	}
	
	//상품 리스트
	@ResponseBody
	@RequestMapping("/product_rec.sh")
	public void  product_list(HttpServletRequest req, HttpServletResponse res) throws Exception{
		String search = req.getParameter("search");
		List<ProductVO> list = dao.product_list(search);
		req.setCharacterEncoding("UTF-8");
		res.setCharacterEncoding("UTF-8");
		res.setContentType("text/html");
		PrintWriter writer = res.getWriter();
		writer.println( gson.toJson(list));
	}
	

	//이미지 넣기
	@ResponseBody
	@RequestMapping ("/insert_img.bo")
	public String insert(ImginsertVO vo, MultipartFile file, HttpSession session) {
		
		// 파일 정보가 있다면
		if ( ! file.isEmpty() ) {
			vo.setPackage_name("겨울");
			vo.setFile_name( file.getOriginalFilename() );
			vo.setFile_path(service.fileupload("product_packge", file, session) );
		}
		
		boolean result = dao.img_insert(vo);
		return "redirect:/" + result;
	}
	
	//이미지 넣기
	@ResponseBody
	@RequestMapping ("/insert_tag.bo")
	public String insert_tag(TagVO vo, MultipartFile file, HttpSession session) {
		vo.setPackage_num(8);
		vo.setTag_key("#루프탑#차박#차박텐트");
		boolean result = dao.tag_insert(vo);
		return "redirect:/" + result;
	}
	
	//상품 넣기
	@ResponseBody
	@RequestMapping ("/insert_pro.bo")
	public String insert_pro(ProductinVO vo, MultipartFile file, HttpSession session) {
		vo.setProduct_name("GLAMCAVE 바이오 에탄올난로 실내불멍 화로 가정용불멍");
		vo.setProduct_price(28000);
		vo.setProduct_stock(10);
		vo.setProduct_kind("감성용품");
		
		
		boolean result = dao.pro_insert(vo);
		return "redirect:/" + result;
	}
	
}
