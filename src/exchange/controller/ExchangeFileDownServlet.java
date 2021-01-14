package exchange.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ExchangeFileDownServlet
 */
@WebServlet("/efdown")
public class ExchangeFileDownServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExchangeFileDownServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 게시글 상세보기의 파일다운로드 처리용 컨트롤러
		// 지정된 폴더에서 파일을 읽어서(파일입력) 네트워크 출력스트림으로 데이터를 전송함(출력)

		// WAS(Web Application Server : 웹 애플리케이션을 구동하는 서버 엔진) 에서
		// 구동중인 프로젝트 내에 있는 폴더의 경로 정보를 찾아오기 위해서는
		String savePath = request.getSession().getServletContext().getRealPath("/resources/exchange_files");

		// 1. 전송온 파일명에 한글이 있다면 인코딩 처리함
		request.setCharacterEncoding("utf-8");

		// 2. 전송온 파일명 꺼내서 변수에 기록 저장
		String originalFileName = request.getParameter("ofile");
		String renameFileName = request.getParameter("rfile");

		// 저장 폴더에 있는 파일 읽기용 스트림 생성
		File readFile = new File(savePath + "/" + renameFileName);
		BufferedInputStream bin = new BufferedInputStream(
				new FileInputStream(readFile));
		// 클라이언트에게로 내보낼 파일 출력스트림 객체 생성
		ServletOutputStream downOut = response.getOutputStream();

		// 파일 다운을 위한 response 설정 처리
		response.setContentType("text/plain; charset=utf-8");
		// 한글 파일명을 다운되는 컴퓨터의 os 문자셋에 맞춤
		response.addHeader("Content-Disposition",
				"attachment; filename=\"" + new String(originalFileName.getBytes("utf-8"), "ISO-8859-1") + "\"");
		response.setContentLength((int) readFile.length());

		// 파일의 데이터 읽어서, 클라이언트로 내보내기
		int data = -1;
		while ((data = bin.read()) != -1) {
			downOut.write(data); // 읽은 데이터 클라이언트로 보냄
			downOut.flush();
		}

		downOut.close();
		bin.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
