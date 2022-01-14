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
import camping.CampingDAO;
import common.CommonService;
import common.OutPrintln;
import member.MemberDAO;
import member.MemberVO;
import product.ProductDAO;
import product.ProductVO;

@Controller
public class CampingController {
	Gson gson = new Gson();
	
	@Autowired private CommonService service;
	@Autowired private OutPrintln outprintln;
	@Autowired private CampingDAO dao;

	
	//캠핑 등록
	@ResponseBody
	@RequestMapping("/insert.camp")
	public void join(HttpServletRequest req, HttpServletResponse res, HttpSession session) throws Exception {
		PrintWriter writer = outprintln.outprintln(req, res);

	}

	//캠핑 목록
	@ResponseBody
	@RequestMapping("/list.camp")
	public void  list(HttpServletRequest req, HttpServletResponse res) throws Exception{
		PrintWriter writer = outprintln.outprintln(req, res);
	}
	
	//캠핑 상세정보
	@ResponseBody
	@RequestMapping("/detail.camp")
	public void  detail(HttpServletRequest req, HttpServletResponse res) throws Exception{
		PrintWriter writer = outprintln.outprintln(req, res);
	}
	
	//캠핑 정보수정
	@ResponseBody
	@RequestMapping("/update.camp")
	public void  update(HttpServletRequest req, HttpServletResponse res) throws Exception{
		PrintWriter writer = outprintln.outprintln(req, res);
	}
	
	//캠핑 삭제
	@ResponseBody
	@RequestMapping("/delete.camp")
	public void  delete(HttpServletRequest req, HttpServletResponse res) throws Exception{
		PrintWriter writer = outprintln.outprintln(req, res);
	}
}
