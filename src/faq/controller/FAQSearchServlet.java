package faq.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import faq.model.service.FAQService;
import faq.model.vo.FAQ;
import member.model.vo.Member;
import notice.model.service.NoticeService;
import notice.model.vo.Notice;

/**
 * Servlet implementation class FAQSerachServlet
 */
@WebServlet("/fasearch")
public class FAQSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FAQSearchServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 공지사항 검색 처리용 컨트롤러

		request.setCharacterEncoding("utf-8");

		String search = request.getParameter("search");

		ArrayList<FAQ> list = null;
		FAQService fservice = new FAQService();

		String faqTitle = request.getParameter("keyword");
		list = fservice.selectSearchTitle(faqTitle);

		RequestDispatcher view = null;
		if (list.size() > 0) {
			HttpSession session = request.getSession(false);
			Member loginMember = (Member) session.getAttribute("loginMember");
			if (session != null && loginMember != null) {
				if (loginMember.getUserLev().charAt(0) == 'D') {
					// 관리자로 로그인된 상태라면
					view = request.getRequestDispatcher("views/faq/faqAdminListView.jsp");
				} else {
					view = request.getRequestDispatcher("views/faq/faqListView.jsp");
				}
				request.setAttribute("list", list);
				view.forward(request, response);
			} else {
				view = request.getRequestDispatcher("views/common/error.jsp");
				request.setAttribute("message", search + " 항목의 검색 조회 실패.");
				view.forward(request, response);
			}
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
