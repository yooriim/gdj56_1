package com.web.common;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class PasswordEncodingWrapper 
			extends HttpServletRequestWrapper {
	public PasswordEncodingWrapper(HttpServletRequest request) {
		super(request);
	}
	
	@Override
	public String getParameter(String name) {
		if(name.contains("password")) {
			//param값이 password인건만 암호화해서 비번 변경하면 암호화가 안됨!
			//새로 바꾼 비번까지 걸리게 해주면 됨 
			
			//데이터를 단방향 암호화해서 반환하기
//			String ori=super.getParameter(name);
//			System.out.println("암호화 전 : "+ori);
//			String encrpt=getSHA512(super.getParameter(name));
//			System.out.println("암호화 후 : "+encrpt);
			return getSHA512(super.getParameter(name));
						
		}
		return super.getParameter(name);//원본값
	}
	
	private String getSHA512(String oriVal) {
		//단방향암호화하기
		//java에서 암호화처리 클래스를 제공해줌. -> MessageDigest클래스
		MessageDigest md=null;
		try {
			//적용할 암호화알고리즘을 선택해서 클래스 생성
			md=MessageDigest.getInstance("SHA-512");
			
			//getInstance(String algorithm) : 
			//입력한 해시 알고리즘을 수행하는 MessageDigest 객체를 생성.
			//파라미터로 받는 알고리즘은 NoSuchAlgorithmException 때문에 try / catch로 감싸줘야 한다.
		}catch(NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		//md이용해서 암호화처리를 진행 -> byte[]로 변경해서 암호화를 처리
		byte[] oriDataByte=oriVal.getBytes();
		md.update(oriDataByte);//SHA-512방식으로 암호화처리   //update(byte[] input) : 객체 내에 저장된 digest 값을 갱신시킨다.
		byte[] encryptData=md.digest();//암호화된값을 byte배열로 가져옴
		
		//digest() : update()를 실행, 해시 계산 완료 후 해시화된 값을 반환한다.
		
		//Base64인코더를 이용해서 byte[]로 출력된 내용을 String로 변환
		return Base64.getEncoder().encodeToString(encryptData);
		
		
		
		
	}
	
}
