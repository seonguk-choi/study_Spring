package common;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class CommonService {
	
	// 파일 업로드 처리
	public String fileupload(String category, MultipartFile file, HttpSession session) {
		String resources = session.getServletContext().getRealPath("resources");
		//D:\study_Spring\WorkSpace\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\iot\resources		
		
		String folder = resources + "/upload" + "/" + category + "/"
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
	
}
