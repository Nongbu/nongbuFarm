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
 * Servlet implementation class ExchangeReplyServlet
 */
@WebServlet("/ereply")
public class ExchangeReplyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExchangeReplyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 게시글 댓글달기 처리용 컨트롤러
		request.setCharacterEncoding("utf-8");

		int currentPage = Integer.parseInt(request.getParameter("page"));
		int exNo = Integer.parseInt(request.getParameter("bnum"));

		// 전송받은 bnum 에 대한 정보를 조회함 : 원글인지 댓글인지 확인하기 위함
		ExchangeService eservice = new ExchangeService();
		
		Exchange originExchange = eservice.selectExchange(exNo);

		// 댓글 객체 생성과 값 기록 저장
		Exchange reply = new Exchange();
		reply.setExTitle(request.getParameter("btitle"));
		reply.setExContent(request.getParameter("bcontent"));
		reply.setExWriter(request.getParameter("bwriter"));
		//원글의 댓글인지, 댓글의 댓글인지에 따라 값 기록 처리함
		reply.setExReplyLev(originExchange.getExReplyLev() + 1);
		//참조하는 원글 번호 기록
		reply.setExRef(originExchange.getExRef());
		//참조하는 댓글 번호 기록
		if(reply.getExReplyLev() == 2)  //원글의 댓글이면 일단 원글번호를 기록
			reply.setExReplyRef(originExchange.getExRef());
		if(reply.getExReplyLev() == 3) //댓글의 댓글이면 전송온 글번호를 기록
			reply.setExReplyRef(exNo);
		reply.setExReplySeq(1);  //최근 댓글|대댓글이 항상 1이 되게 함
		
		//이전에 등록된 댓글|대댓글의 순번(board_reply_seq)을 1증가 처리함
		eservice.updateReplySeq(reply);
		
		//댓글 등록
		int result = eservice.insertReply(reply);
		
		if(result > 0) {
			response.sendRedirect("/nongbu/elist?page=" + currentPage);
		}else {
			RequestDispatcher view = request.getRequestDispatcher("views/common/error.jsp");
			request.setAttribute("message", exNo + "번 글에 대한 댓글 달기 실패.");
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
