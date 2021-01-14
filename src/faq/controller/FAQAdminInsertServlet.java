package faq.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import faq.model.service.FAQService;
import faq.model.vo.FAQ;

/**
 * Servlet implementation class NoticeAdminInsertServlet
 */
@WebServlet("/fainsert.ad")
public class FAQAdminInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FAQAdminInsertServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 관리자용 공지사항 등록 처리용 컨트롤러
		//multipart 방식으로 인코딩되어 전송온 폼 데이터를 처리하는 클래스가 
		//JAVA EE 에서 제공되지 않음 => 외부 라이브러리를 가져다 써야 함
		//즉, HTTP 프로토콜로 파일을 서버로 업로드하는 기능을 가진 클래스를 말함
		//예 : cos.jar, commons_fileUpload.jar 등
		//WEB-INF/lib 폴더에 복사해 넣음
		
		//1. multipart 방식으로 인코딩되어서 전송되었는지 확인함
		RequestDispatcher view = null;
		if(!ServletFileUpload.isMultipartContent(request)) {
			//multipart 로 파일업로드 처리가 안 되었다면
			view = request.getRequestDispatcher("views/common/error.jsp");
			request.setAttribute("message", 
					"form의 enctype='multipart/form-data' 속성 누락되었음. 확인하세요.");
			view.forward(request, response);
		}
		
		//2. 업로드할 파일의 용량 제한 설정 : 10Mbyte 로 제한한다면
		int maxSize = 1024 * 1024 * 10;
		
		//3. 업로드되는 파일의 저장 폴더 지정하기
		String savePath = request.getSession().getServletContext()
				.getRealPath("/resources/notice_files");
		
		//4. request를 MultipartRequest 로 변환해야 함
		//cos.jar 가 제공하는 클래스를 사용함
		//자동으로 지정한 폴더에 파일이 저장됨(업로드 완료됨)
		MultipartRequest mrequest = new MultipartRequest(
				request, savePath, maxSize, "UTF-8", 
				new DefaultFileRenamePolicy());
		
		//5. 전송 온 값 꺼내서 변수 또는 VO 객체에 기록 저장
		//주의 : mrequest 로 추출해야 함 (request 로는 값 추출 안 됨, null 임)
		FAQ faq = new FAQ();
		faq.setFaqTitle(mrequest.getParameter("fatitle"));
		faq.setFaqCategory(mrequest.getParameter("facategory"));
		faq.setFaqContent(mrequest.getParameter("facontent"));
		
		//6. 모델의 서비스 객체 생성하고, 메소드 실행하고 결과받기
		int result = new FAQService().insertFaq(faq);
		
		//7. 받은 결과를 가지고 성공/실패에 대한 뷰를 선택해서 내보냄
		if(result > 0) {  //공지글 등록이 성공했다면
			response.sendRedirect("/nongbu/falist");
		}else {
			view = request.getRequestDispatcher("views/common/error.jsp");
			request.setAttribute("message", "새 공지사항 등록 실패!");
			view.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
