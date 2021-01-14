package board.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.model.service.BoardService;
import board.model.vo.Board;

/**
 * Servlet implementation class BoardReplyServlet
 */
@WebServlet("/breply")
public class BoardReplyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BoardReplyServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 게시글 댓글달기 처리용 컨트롤러
		request.setCharacterEncoding("utf-8");

		int currentPage = Integer.parseInt(request.getParameter("page"));
		int boardNum = Integer.parseInt(request.getParameter("bnum"));

		// 전송받은 bnum 에 대한 정보를 조회함 : 원글인지 댓글인지 확인하기 위함
		BoardService bservice = new BoardService();
		
		Board originBoard = bservice.selectBoard(boardNum);

		// 댓글 객체 생성과 값 기록 저장
		Board reply = new Board();
		reply.setBoardTitle(request.getParameter("btitle"));
		reply.setBoardContent(request.getParameter("bcontent"));
		reply.setBoardWriter(request.getParameter("bwriter"));
		reply.setBoardCategory(originBoard.getBoardCategory());
		//원글의 댓글인지, 댓글의 댓글인지에 따라 값 기록 처리함
		reply.setBoardReplyLev(originBoard.getBoardReplyLev() + 1);
		//참조하는 원글 번호 기록
		reply.setBoardRef(originBoard.getBoardRef());
		//참조하는 댓글 번호 기록
		if(reply.getBoardReplyLev() == 2)  //원글의 댓글이면 일단 원글번호를 기록
			reply.setBoardReplyRef(originBoard.getBoardRef());
		if(reply.getBoardReplyLev() == 3) //댓글의 댓글이면 전송온 글번호를 기록
			reply.setBoardReplyRef(boardNum);
		reply.setBoardReplySeq(1);  //최근 댓글|대댓글이 항상 1이 되게 함
		
		//이전에 등록된 댓글|대댓글의 순번(board_reply_seq)을 1증가 처리함
		bservice.updateReplySeq(reply);
		
		//댓글 등록
		int result = bservice.insertReply(reply);
		
		if(result > 0) {
			response.sendRedirect("/nongbu/blist?page=" + currentPage);
		}else {
			RequestDispatcher view = request.getRequestDispatcher("views/common/error.jsp");
			request.setAttribute("message", boardNum + "번 글에 대한 댓글 달기 실패.");
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
