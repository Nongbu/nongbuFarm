package board.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.model.service.BoardService;
import board.model.vo.Board;

/**
 * Servlet implementation class BoardListServlet
 */
@WebServlet("/blist")
public class BoardListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 페이지별로 출력되는 게시글 전체 조회 처리용 컨트롤러
		
		//페이지의 기본값 지정해 둠
		int currentPage = 1;
		
		//전송온 페이지값 추출함
		if(request.getParameter("page") != null) {
			currentPage = Integer.parseInt(request.getParameter("page"));
		}
		
		//한 페이지당 출력할 목록 갯수 지정함
		int limit = 10;
		
		BoardService bservice = new BoardService();
		
		//전체 목록 갯수 조회함 : 총페이지수 계산을 위함
		int listCount = bservice.getListCount();
		//System.out.println("총갯수 : " + listCount);
		
		//요청한 페이지에 출력할 게시글 조회하고 결과 받음
		ArrayList<Board> list = bservice.selectList(currentPage, limit);
		
		//뷰에서 사용할 페이지 출력과 관련된 값 만들기
		int maxPage = (int)((double)listCount / limit + 0.9);  //12.1 page 는 13 page 가 되게 함
		//현재 페이지가 속한 페이지그룹의 시작 페이지 값 지정
		//예 : currentPage 가 35page 이면, 10개씩 페이지를 표시할 경우 시작페이지는 31이 됨
		int startPage = ((int)((double)currentPage / 10)) * 10 + 1;
		int endPage = startPage + 9;
		
		if(maxPage < endPage) {
			endPage = maxPage;
		}
		
		//뷰 지정해서 내보내기
		RequestDispatcher view = null;
		if(list.size() > 0) {
			view = request.getRequestDispatcher("views/board/boardListView.jsp");
			
			request.setAttribute("list", list);
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("maxPage", maxPage);
			request.setAttribute("startPage", startPage);
			request.setAttribute("endPage", endPage);
			request.setAttribute("listCount", listCount);
			
			view.forward(request, response);
		}else {
			view = request.getRequestDispatcher("views/common/error.jsp");
			request.setAttribute("message", 
					currentPage + "페이지에 대한 목록 조회 실패.");
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
