package com.android.middle;


import java.util.HashMap;
import java.util.UUID;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import com.google.gson.Gson;

import common.CommonService;
import member.MemberDAO;
import member.MemberVO;

@Controller
public class MemberController {
	Gson gson = new Gson();
	
	@Autowired private CommonService service;
	@Autowired private MemberDAO dao;
	
	@ResponseBody
	@RequestMapping("/join")
	public String text(HttpServletRequest req, HttpServletResponse res, HttpSession session) {
		
		
		String tempVo = req.getParameter("vo");
		MemberVO vo = gson.fromJson(tempVo, MemberVO.class);
		
		MultipartRequest mulReq = (MultipartRequest) req;
		MultipartFile file = mulReq.getFile("file");
		
		if(file != null) {
			System.out.println("Null 아님 파일 들어옴");
			String path = service.fileupload("and", file, session);
			String server_path = "http://" + req.getLocalAddr()
			+ ":" + req.getLocalPort() + req.getContextPath() + "/resources/";
			System.out.println(server_path + path);
			
			//Gson gson = new Gson();
			//vo = gson.fromJson("VO", MemberVO.class);
		
			vo.setFilepath(server_path + path);
			dao.member_insert(vo);
				
		} else {
			System.out.println("Null 파일이 안들어 옴");
		}
		return null;
	}
	
	@ResponseBody
	@RequestMapping("/login")
	public String  login(HttpServletRequest req, HttpServletResponse res){
		MemberVO vo = new MemberVO();
		
		vo.setId("aaa");
		vo.setPw("aaa");
		vo = dao.member_list(vo);
		System.out.println(vo.getId());
		
		return null;
	}
	
}
