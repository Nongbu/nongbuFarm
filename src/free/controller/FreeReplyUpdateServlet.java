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
 * Servlet implementation class FreeReplyUpdateServlet
 */
@WebServlet("/freplyup")
public class FreeReplyUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FreeReplyUpdateServlet() {
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
				
				Free reply = new Free();
				reply.setFreeNo(Integer.parseInt(request.getParameter("bnum")));
				reply.setFreeTitle(request.getParameter("btitle"));
				reply.setFreeContent(request.getParameter("bcontent"));
				
				int result = new FreeService().updateReply(reply);
				
				if(result > 0) {
					response.sendRedirect("/nongbu/flist?page=" + currentPage);
					
				}else {
					RequestDispatcher view = request.getRequestDispatcher("views/common/error.jsp");
					request.setAttribute("message", reply.getFreeNo() + "번 댓글 수정 실패!");
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
