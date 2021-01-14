package notice.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import notice.model.service.NoticeService;

/**
 * Servlet implementation class NoticeAdminDeleteServlet
 */
@WebServlet("/ndelete.ad")
public class NoticeAdminDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeAdminDeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 관리자가 요청한 공지글 삭제 처리용 컨트롤러
		int noticeNo = Integer.parseInt(request.getParameter("no"));
		
		int result = new NoticeService().deleteNotice(noticeNo);
		
		if(result > 0) {
			//공지글 삭제 성공시, 첨부파일이 있었다면 파일도 삭제함
			String renameFileName = request.getParameter("rfile");
			if(renameFileName != null) {  //첨부된 파일이 있었다면
				String savePath = request.getSession().getServletContext()
						.getRealPath("/resources/notice_files");
				new File(savePath + "\\" + renameFileName).delete();  //파일 삭제
			}
			response.sendRedirect("/nongbu/nlist");
		}else {
			//공지글 삭제 실패시
			RequestDispatcher view = request.getRequestDispatcher("views/common/error.jsp");
			request.setAttribute("message", noticeNo + "번 공지 삭제 실패!");
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
