package veggie.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import veggie.model.service.ProductService;
import veggie.model.vo.Product;

/**
 * Servlet implementation class veggieUpdateViewServlet
 */
@WebServlet("/proUpdateView")
public class veggieUpdateViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public veggieUpdateViewServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int proNum = Integer.parseInt(request.getParameter("no"));
		Product pro = new ProductService().selectPro(proNum);

		RequestDispatcher view = null;
		if (pro != null) {
			view = request.getRequestDispatcher("views/veggie/veggieUpdate.jsp");
			request.setAttribute("pro", pro);
			view.forward(request, response);
		} else {
			view = request.getRequestDispatcher("views/common/error.jsp");
			request.setAttribute("message", proNum + "번 글 상세조회 실패.");
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
