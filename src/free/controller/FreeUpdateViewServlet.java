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
 * Servlet implementation class FreeUpdateViewServlet
 */
@WebServlet("/fupview")
public class FreeUpdateViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FreeUpdateViewServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 게시글 수정페이지 출력 처리용 컨트롤러
		
				int freeNo = Integer.parseInt(request.getParameter("bnum"));
				int currentPage = Integer.parseInt(request.getParameter("page"));
				
				Free free = new FreeService().selectFree(freeNo);
				
				response.setContentType("text/html; charset=utf-8");
				RequestDispatcher view = null;
				if(free != null) {
					view = request.getRequestDispatcher("views/free/freeUpdateView.jsp");
					request.setAttribute("free", free);
					request.setAttribute("page", currentPage);
					view.forward(request, response);
				}else {
					view = request.getRequestDispatcher("views/common/error.jsp");
					request.setAttribute("message", freeNo + "번 글 수정페이지로 이동 실패.");
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
