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
import cart.CartDAO;
import cart.CartVO;
import common.CommonService;
import common.OutPrintln;
import member.MemberDAO;
import member.MemberVO;
import product.ProductDAO;
import product.ProductVO;

@Controller
public class CartController {
	Gson gson = new Gson();
	
	@Autowired private CommonService service;
	@Autowired private OutPrintln outprintln;
	@Autowired private CartDAO dao;

	
	//장바구니 넣기
	@ResponseBody
	@RequestMapping("/insert.cart")
	public void join(HttpServletRequest req, HttpServletResponse res, HttpSession session) throws Exception {
		PrintWriter writer = outprintln.outprintln(req, res);

	}

	//장바구니 목록
	@ResponseBody
	@RequestMapping("/list.cart")
	public void  list(HttpServletRequest req, HttpServletResponse res) throws Exception{
		PrintWriter writer = outprintln.outprintln(req, res);
	}
	
	//장바구니 상세정보
	@ResponseBody
	@RequestMapping("/detail.cart")
	public void  detail(HttpServletRequest req, HttpServletResponse res) throws Exception{
		PrintWriter writer = outprintln.outprintln(req, res);
	}
	
	//장바구니 정보수정
	@ResponseBody
	@RequestMapping("/update.cart")
	public void  update(HttpServletRequest req, HttpServletResponse res) throws Exception{
		PrintWriter writer = outprintln.outprintln(req, res);
	}
	
	//장바구니 삭제
	@ResponseBody
	@RequestMapping("/delete.cart")
	public void  delete(HttpServletRequest req, HttpServletResponse res) throws Exception{
		PrintWriter writer = outprintln.outprintln(req, res);
	}
}
