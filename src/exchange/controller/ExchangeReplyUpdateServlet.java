package exchange.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import exchange.model.service.ExchangeService;
import exchange.model.vo.Exchange;


/**
 * Servlet implementation class ExchangeReplyUpdateServlet
 */
@WebServlet("/ereplyup")
public class ExchangeReplyUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExchangeReplyUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//댓글과 대댓글 수정 처리용 컨트롤러
		request.setCharacterEncoding("utf-8");
		
		int currentPage = Integer.parseInt(request.getParameter("page"));
		
		Exchange reply = new Exchange();
		reply.setExNo(Integer.parseInt(request.getParameter("bnum")));
		reply.setExTitle(request.getParameter("btitle"));
		reply.setExContent(request.getParameter("bcontent"));
		
		int result = new ExchangeService().updateReply(reply);
		
		if(result > 0) {
			response.sendRedirect("/nongbu/elist?page=" + currentPage);
			
		}else {
			RequestDispatcher view = request.getRequestDispatcher("views/common/error.jsp");
			request.setAttribute("message", reply.getExNo() + "번 댓글 수정 실패!");
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
