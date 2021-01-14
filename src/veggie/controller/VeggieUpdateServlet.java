package veggie.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import faq.model.service.FAQService;
import faq.model.vo.FAQ;
import member.model.vo.Member;
import veggie.model.service.ProductService;
import veggie.model.vo.Product;

/**
 * Servlet implementation class VeggieUpdateServlet
 */
@WebServlet("/proUpdate")
public class VeggieUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public VeggieUpdateServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 작성자용 상품 수정 처리용 컨트롤러

		// 1. multipart 방식으로 인코딩되어서 전송되었는지 확인함
		RequestDispatcher view = null;
		if (!ServletFileUpload.isMultipartContent(request)) {
			// multipart 로 파일업로드 처리가 안 되었다면
			view = request.getRequestDispatcher("views/common/error.jsp");
			request.setAttribute("message", "form의 enctype='multipart/form-data' 속성 누락되었음. 확인하세요.");
			view.forward(request, response);
		}

		// 2. 업로드할 파일의 용량 제한 설정 : 10Mbyte 로 제한한다면
		int maxSize = 1024 * 1024 * 10;

		// 3. 업로드되는 파일의 저장 폴더 지정하기
		String savePath = request.getSession().getServletContext().getRealPath("/resources/notice_files");

		// 4. request를 MultipartRequest 로 변환해야 함
		// cos.jar 가 제공하는 클래스를 사용함
		// 자동으로 지정한 폴더에 파일이 저장됨(업로드 완료됨)
		MultipartRequest mrequest = new MultipartRequest(request, savePath, maxSize, "UTF-8",
				new DefaultFileRenamePolicy());

		// 5. 전송 온 값 꺼내서 변수 또는 VO 객체에 기록 저장
		// 주의 : mrequest 로 추출해야 함 (request 로는 값 추출 안 됨, null 임)
		Product pro = new Product();
		pro.setProID(mrequest.getParameter("proid"));
		pro.setProNum(Integer.parseInt(mrequest.getParameter("pronum")));
		pro.setProName(mrequest.getParameter("proname"));
		pro.setProCtgr(Integer.parseInt(mrequest.getParameter("pacategory")));
		pro.setProPrice(Integer.parseInt(mrequest.getParameter("proprice")));
		pro.setProOrgin(mrequest.getParameter("prorigin"));
		
		HttpSession session = request.getSession(false);
		
		// 6. 모델의 서비스 객체 생성하고, 메소드 실행하고 결과받기
		int result = new ProductService().updatePro(pro);

		// 7. 받은 결과를 가지고 성공/실패에 대한 뷰를 선택해서 내보냄
		if (result > 0) { // 공지글 등록이 성공했다면
			response.sendRedirect("/nongbu/veggieList");
		} else {
			view = request.getRequestDispatcher("views/common/error.jsp");
			request.setAttribute("message", "상품 수정 실패!");
			view.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
