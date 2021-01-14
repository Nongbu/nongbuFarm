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
import free.model.service.FreeService;
import free.model.vo.Free;

/**
 * Servlet implementation class ExchangeDetailServlet
 */
@WebServlet("/edetail")
public class ExchangeDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExchangeDetailServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 교환 글 상세보기 처리용 컨트롤러
		int exNo = Integer.parseInt(request.getParameter("bnum"));
		int currentPage = 1;
		if(request.getParameter("page") != null)
			currentPage = Integer.parseInt(request.getParameter("page"));
		
		
		ExchangeService eservice = new ExchangeService();
			
		//해당 게시글 조회수 1 증가 처리함
		eservice.addReadCount(exNo);
		
		//해당 게시글 조회해 옴
		Exchange exchange = eservice.selectExchange(exNo);
		
		RequestDispatcher view = null;
		if(exchange != null) {
			view = request.getRequestDispatcher("views/exchange/exchangeDetailView.jsp");
			request.setAttribute("exchange", exchange);
			request.setAttribute("currentPage", currentPage);
			view.forward(request, response);
		}else {
			view = request.getRequestDispatcher("views/common/error.jsp");
			request.setAttribute("message", exNo + "번 게시글 상세조회 실패.");
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
