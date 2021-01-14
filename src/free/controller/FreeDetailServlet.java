package free.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import free.model.service.FreeService;
import free.model.vo.Free;

/**
 * Servlet implementation class FreeDetailServlet
 */
@WebServlet("/fdetail")
public class FreeDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FreeDetailServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 무료나눔 글 상세보기 처리용 컨트롤러
				int freeNum = Integer.parseInt(request.getParameter("bnum"));
				int currentPage = 1;
				if(request.getParameter("page") != null)
					currentPage = Integer.parseInt(request.getParameter("page"));
				
				
				FreeService fservice = new FreeService();
					
				//해당 게시글 조회수 1 증가 처리함
				fservice.addReadCount(freeNum);
				
				//해당 게시글 조회해 옴
				Free free = fservice.selectFree(freeNum);
				
				RequestDispatcher view = null;
				if(free != null) {
					view = request.getRequestDispatcher("views/free/freeDetailView.jsp");
					request.setAttribute("free", free);
					request.setAttribute("currentPage", currentPage);
					view.forward(request, response);
				}else {
					view = request.getRequestDispatcher("views/common/error.jsp");
					request.setAttribute("message", freeNum + "번 게시글 상세조회 실패.");
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
