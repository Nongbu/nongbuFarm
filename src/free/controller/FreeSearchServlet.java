package free.controller;

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

import free.model.service.FreeService;
import free.model.vo.Free;
import member.model.vo.Member;

/**
 * Servlet implementation class FreeSearchServlet
 */
@WebServlet("/fsearch")
public class FreeSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public FreeSearchServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//무료나눔글 검색용 컨트롤러
		
		request.setCharacterEncoding("utf-8");
		
		String search = request.getParameter("search");
		
		ArrayList<Free> list = null; 
		FreeService fservice = new FreeService();
		
		switch(search) {
		case "title": String freeTitle = request.getParameter("keyword");
					list = fservice.selectSearchTitle(freeTitle);
					break;
		case "writer": String freeWriter = request.getParameter("keyword");
					list = fservice.selectSearchWriter(freeWriter);
					break;
		case "date": String beginDate = request.getParameter("from");
				String endDate = request.getParameter("to");
				list = fservice.selectSearchDate(Date.valueOf(beginDate),
												Date.valueOf(endDate));
				break;
		}
		
		RequestDispatcher view = null;
		if(list.size() > 0) {
			HttpSession session = request.getSession(false);
			Member loginMember = (Member)session.getAttribute("loginMember");
			if(session != null && loginMember != null) {
				if(loginMember.getUserLev().charAt(0) > 'C') { //관리자로 로그인된 상태라면
					view = request.getRequestDispatcher("views/free/freeListView.jsp");
				}
			}else {
				view = request.getRequestDispatcher("views/free/freeListView.jsp");
			}
			request.setAttribute("list", list);
			view.forward(null, response);
		}else {
			view = request.getRequestDispatcher("views/common/error.jsp");
			request.setAttribute("message", search + " 항목의 검색 조회 실패했습니다.");
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
