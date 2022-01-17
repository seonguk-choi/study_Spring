package common;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class CommonService {
	
	// 파일 업로드 처리
	public String fileupload(String category, MultipartFile file, HttpSession session) {
		String resources = session.getServletContext().getRealPath("resources");
		//D:\study_Spring\WorkSpace\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\iot\resources		
		
		String folder = resources + "/upload/" + category + "/"
				+ new SimpleDateFormat("yyyy/MM/dd").format(new Date());
		
		// D:\study_Spring\WorkSpace\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\iot\resources
		// /upload/notice/2022/01/07/00000000ssasdfa_123.png
		
		String uuid = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();		
			
		File dir = new File(folder);
		if(!dir.exists()) dir.mkdirs();
			try {
				file.transferTo(new File(folder, uuid));
			} catch (Exception e) {
				e.printStackTrace();
			}
				
		return folder.substring(resources.length() + 1) + "/" + uuid;
	}
	
	// API를 요청하는 메소드
	//	try {
	//	      URL url = new URL(apiURL);
	//	      HttpURLConnection con = (HttpURLConnection)url.openConnection();
	//	      con.setRequestMethod("GET");
	//	      int responseCode = con.getResponseCode();
	//	      BufferedReader br;
	//	      System.out.print("responseCode="+responseCode);
	//	      if(responseCode==200) { // 정상 호출
	//	        br = new BufferedReader(new InputStreamReader(con.getInputStream()));
	//	      } else {  // 에러 발생
	//	        br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
	//	      }
	//	      String inputLine;
	//	      StringBuffer res = new StringBuffer();
	//	      while ((inputLine = br.readLine()) != null) {
	//	        res.append(inputLine);
	//	      }
	//	      br.close();
	//	      if(responseCode==200) {
	//	        out.println(res.toString());
	//	      }
	//	    } catch (Exception e) {
	//	      System.out.println(e);
	//	    }
	
	public String requestAPI(StringBuffer url) {
		String result = null;
		try {
			//전달받은 url 지정
			//Http 연결
			HttpURLConnection con = (HttpURLConnection) new URL(url.toString()).openConnection();
			con.setRequestMethod("GET");
			int responseCode = con.getResponseCode();
			
			//여러가지 정보를 읽어 들이기 위해 BufferedReader 사용
			BufferedReader br;
			System.out.println("responseCode=" + responseCode);
			
			if(responseCode == 200) {
				//정상호출
				br = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"));
			} else {
				//에러 발생
				br = new BufferedReader(new InputStreamReader(con.getErrorStream(), "utf-8"));
			}
			
			String inputLine;
			
			//실제 값이 담겨진 변수 res 값은 리턴
			StringBuffer res = new StringBuffer();
			while((inputLine = br.readLine()) != null) {
				res.append(inputLine);
			}
			br.close();				//BufferReader 닫기
			con.disconnect();		//HTTP 통신 연결 종료
			result = res.toString();//요청한 결과(res)를 result에 담아 리턴
			
		} catch (Exception e) {
			System.out.println(e);
		}
		return result;
	}
	
	//접근 토큰을 이용하여 프로필 API 호출하기 위해 access_token, token_type 값을 파라미터로 전달
	public String requestAPI(StringBuffer url, String property) {
		String result = null;
		try {
			//전달받은 url 지정
			//Http 연결
			HttpURLConnection con = (HttpURLConnection) new URL(url.toString()).openConnection();
			con.setRequestMethod("GET");
			
			//요청 헤더명이 Authorization 으로 property를 보냄
			con.setRequestProperty("Authorization", property);
			int responseCode = con.getResponseCode();
			
			//여러가지 정보를 읽어 들이기 위해 BufferedReader 사용
			BufferedReader br;
			System.out.println("responseCode=" + responseCode);
			
			if(responseCode == 200) {
				//정상호출
				br = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"));
			} else {
				//에러 발생
				br = new BufferedReader(new InputStreamReader(con.getErrorStream(), "utf-8"));
			}
			
			String inputLine;
			
			//실제 값이 담겨진 변수 res 값은 리턴
			StringBuffer res = new StringBuffer();
			while((inputLine = br.readLine()) != null) {
				res.append(inputLine);
			}
			br.close();				//BufferReader 닫기
			con.disconnect();		//HTTP 통신 연결 종료
			result = res.toString();//요청한 결과(res)를 result에 담아 리턴
			
		} catch (Exception e) {
			System.out.println(e);
		}
		return result;
	}
	
	//파일 다운로드
	public void fileDonwload(String filename, String filepath, HttpSession session, HttpServletResponse response) {
		// 실제 파일 위치와 파일을 찾아 file이란 이름으로 관리
		File file = new File(session.getServletContext().getRealPath("resources")
				+ "/" + filepath);
		
		//파일의 형태 확인 (확장자를 통해 확인)
		String mime = session.getServletContext().getMimeType(filename);
		
		//응답처리 setContentType()을 이용해
		//클라이언트에 전송할 데이터 종류(Mime-Type) 지정
		response.setContentType(mime);
		
		// 클라이언트에 파일을 첨부하여 쓰기 작업을 하는데 파일을 첨부하는 건
		// header에 첨부파일 정보를 넘겨줘야 함.
		// content-disposition : 응답 본문을 브라우저에 어떻게 표힐 할지 알려주는 헤더 
		
		try {
			filename = URLEncoder.encode(filename, "utf-8").replaceAll("\\+", "%20");
			
			// '+' 문자를 공백(%20)으로 바꿈 : URL escape code != 아스키코드
			response.setHeader("content-disposition", "attacthment; filename=" + filename);
			
			ServletOutputStream out = response.getOutputStream();
			
			//파일 쓰기위해 파일을 복하여 붙이는능
			//FileCopyUtils를 사용하여 copy 메소드를 통해 파일 정보를 읽어들이기위해
			//FileInputStream을 하여 file 을 읽고 쓰기 작업
			//output out 처리
			FileCopyUtils.copy(new FileInputStream(file), out);
			
			out.flush();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	
		
		
		
	}
	
}
