package faq.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import faq.model.service.FAQService;
import faq.model.vo.FAQ;

/**
 * Servlet implementation class NoticeAdminDetailServlet
 */
@WebServlet("/fadetail.ad")
public class FAQAdminDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FAQAdminDetailServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 관리자가 요청하는 공지글 상세보기 처리용 컨트롤러
		int faqNum = Integer.parseInt(request.getParameter("faqnum"));

		FAQ faq = new FAQService().selectFaq(faqNum);

		RequestDispatcher view = null;
		if (faq != null) {
			view = request.getRequestDispatcher("views/faq/faqAdminDetailView.jsp");
			request.setAttribute("faq", faq);
			view.forward(request, response);
		} else {
			view = request.getRequestDispatcher("views/common/error.jsp");
			request.setAttribute("message", faqNum + "번 FAQ 상세조회 실패.");
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
