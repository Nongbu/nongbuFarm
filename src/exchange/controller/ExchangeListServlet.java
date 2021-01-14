package exchange.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import exchange.model.service.ExchangeService;
import exchange.model.vo.Exchange;
import free.model.service.FreeService;
import free.model.vo.Free;

/**
 * Servlet implementation class ExchangeListServlet
 */
@WebServlet("/elist")
public class ExchangeListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExchangeListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 페이지 별로 출력되는 무료나눔 전체 조회 처리용 컨트롤러
		
		//페이지의 기본값 지정해 둠
		int currentPage = 1; //페이지는 1페이지부터!
		
		//전송 온 페이지값 추출함
		if(request.getParameter("page") !=null) { //페이지라는 이름으로 전송된 값이 있으면 
			currentPage = Integer.parseInt(request.getParameter("page"));
		}
		//한 페이지당 출력할 목록 갯수 지정함
		int limit = 10;
		
		ExchangeService eservice = new ExchangeService();
		
		//전체 목록 갯수 조회함 : 총 페이지 수 계산을 위함 (정수형으로 받아)
		int listCount = eservice.getListCount();
		System.out.println("총 갯수 : " + listCount);
		
		//요청한 페이지에 출력할 게시글 조회하고 결과 받음
		ArrayList<Exchange> list = eservice.selectList(currentPage, limit);
	
		//뷰에서 사용할 페이지 출력과 관련된 값 만들기
		int maxPage = (int)((double)listCount / limit + 0.9); //12.3 page는 13page가 되게 함 
		//현재 페이지가 속한 페이지그룹의 시작 페이지 값 지정
		//예 : currentPage가 35page이면, 10개씩 페이지를 표시할 경우 시작페이지는 31이 됨
		int startPage = ((int)((double)currentPage/10)) * 10 + 1 ;
		int endPage = startPage + 9;
		
		if(maxPage < endPage) { //maxpage는 마지막 페이지다.
			endPage = maxPage;
		}
		
		//뷰 지정해서 내보내기
		RequestDispatcher view = null;
		if(list.size() > 0 ) {
			view= request.getRequestDispatcher("views/exchange/exchangeListView.jsp"); //getrequestdispatcher는 상대경로만 쓴다.
			request.setAttribute("list", list); // request에 닶 담아줘
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("maxPage", maxPage);
			request.setAttribute("startPage", startPage);
			request.setAttribute("endPage", endPage); // (원래 객체 써야 하는데)integer객체로 자동 박싱 되서 자동으로 객체 상태가 된다.
			request.setAttribute("listCount", listCount);
			
			view.forward(request, response);
		}else {
			view = request.getRequestDispatcher("views/common/error.jsp");
			request.setAttribute("message", currentPage + "페이지에 대한 목록 조회 실패.");
			view.forward(request, response); //setarrtibute로 msg에 담았으면, forward로 view를 보여줘라!
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
