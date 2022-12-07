package pracServlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.tribes.util.Arrays;

/**
 * Servlet implementation class PracServlet
 */
@WebServlet("/enrolldata.do")
public class PracServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PracServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		
		String id=request.getParameter("userId");
		String pw=request.getParameter("userPw");
		String name=request.getParameter("name");
		String nname=request.getParameter("nickname");		
		String[] langs=request.getParameterValues("lang");
		String gen=request.getParameter("gender");
		String intro=request.getParameter("introduce");

//		System.out.println(id);
		
		response.setContentType("text/html;charset=utf-8");
		
		PrintWriter out=response.getWriter();
		String html="<html>";
		html+="<head>";
		html+="<title>회원가입</title>";
		html+="</head>";
		html+="<body>";
		html+="<fieldset>";
		html+="<legend>나의 정보</legend>";
		html+="<h3>안녕하세요? "+name+"님! </h3>";
		html+="<hr>";
		html+="<h5> 아이디 : "+id+"</h5>";
		html+="<h5> 비밀번호 : "+pw+"</h5>";
		html+="<h5> 이름 : "+name+"</h5>";
		html+="<h5> 닉네임 : "+nname+"</h5>";
		html+="<h5> 언어 : "+Arrays.toString(langs)+"</h5>";
		html+="<h5> 성별 : "+gen+"</h5>";
		html+="<h5> 자기소개 : "+intro+"</h5>";
		html+="</fieldset>";
		html+="</body>";
		html+="</html>";
		
		out.write(html);
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
