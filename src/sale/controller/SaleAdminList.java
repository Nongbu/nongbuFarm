package sale.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.model.vo.Member;
import sale.model.service.SaleService;
import sale.model.vo.Sale;

/**
 * Servlet implementation class SaleAdminInsert
 */
@WebServlet("/slist.ad")
public class SaleAdminList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SaleAdminList() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Sale 목록보기 처리용 컨트롤러
	      ArrayList<Sale> list = new SaleService().selectList();

	      RequestDispatcher view = null;
	      if (list.size() > 0) { // 목록 조회 성공
	         HttpSession session = request.getSession(false);
	         Member loginMember = (Member)session.getAttribute("loginMember");
	         if (session != null && loginMember != null) {
	            if (loginMember.getUserLev().charAt(0) == 'D' || loginMember.getUserLev().charAt(0) == 'B') {
	               // 관리자로 로그인된 상태라면
	               view = request.getRequestDispatcher("views/sale/saleAdminView.jsp");
	         } else {
	            view = request.getRequestDispatcher("views/sale/sale.jsp");
	         }
	         request.setAttribute("list", list);
	         view.forward(request, response);
	      } else {
	         view = request.getRequestDispatcher("views/common/error.jsp");
	         request.setAttribute("message", "sale 전체 조회 실패");
	         view.forward(request, response);
	      }
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
