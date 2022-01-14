package com.android.safing;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
import product.ProductDAO;
import product.ProductVO;

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
}
