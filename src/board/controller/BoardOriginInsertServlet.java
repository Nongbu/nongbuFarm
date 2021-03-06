package board.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import board.model.service.BoardService;
import board.model.vo.Board;

/**
 * Servlet implementation class BoardOriginInsertServlet
 */
@WebServlet("/binsert")
public class BoardOriginInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BoardOriginInsertServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 게시글 원글 등록 처리용 컨트롤러
		// 첨부파일 업로드 기능이 있음. (multipart 방식으로 인코딩되어 전송왔음)
		
		// 1. multipart 방식으로 인코딩되어서 전송되었는지 확인함
		RequestDispatcher view = null;
		if (!ServletFileUpload.isMultipartContent(request)) {
			// multipart 로 파일업로드 처리가 안 되었다면
			view = request.getRequestDispatcher("views/common/error.jsp");
			request.setAttribute("message", 
					"form의 enctype='multipart/form-data' 속성 누락되었음. 확인하세요.");
			view.forward(request, response);
		}

		// 2. 업로드할 파일의 용량 제한 설정 : 10Mbyte 로 제한한다면
		int maxSize = 1024 * 1024 * 10;

		// 3. 업로드되는 파일의 저장 폴더 지정하기
		String savePath = request.getSession().getServletContext()
				.getRealPath("/resources/board_files");

		// 4. request를 MultipartRequest 로 변환해야 함
		// cos.jar 가 제공하는 클래스를 사용함
		// 자동으로 지정한 폴더에 파일이 저장됨(업로드 완료됨)
		MultipartRequest mrequest = new MultipartRequest(
				request, savePath, maxSize, "UTF-8",
				new DefaultFileRenamePolicy());

		// 5. 전송 온 값 꺼내서 변수 또는 VO 객체에 기록 저장
		// 주의 : mrequest 로 추출해야 함 (request 로는 값 추출 안 됨, null 임)
		Board board = new Board();
		
		board.setBoardCategory(mrequest.getParameter("bcategory"));
		board.setBoardTitle(mrequest.getParameter("btitle"));
		board.setBoardWriter(mrequest.getParameter("bwriter"));
		board.setBoardContent(mrequest.getParameter("bcontent"));

		// 서버에 업로드된 파일명 추출하기
		String originalFileName = mrequest.getFilesystemName("upfile");
		// System.out.println("ofile : " + originalFileName);
		board.setBoardOriginalFileName(originalFileName);

		// 첨부된 파일의 파일명 바꾸기 (선택사항임)
		// 저장 폴더에 같은 이름의 파일이 있을 경우를 대비하기 위함
		// 저장된 원본 파일명을 "년월일시분초.확장자" 형식으로 변경함
		// 첨부파일이 있을 때만 파일 이름바꾸기를 실행함
		if (originalFileName != null) {
			// 바꿀 파일명에 대한 포맷문자열 만들기 : 년월일시분초 형식으로
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");

			// 바꿀 파일명 만들기
			String renameFileName = sdf.format(new java.sql.Date(System.currentTimeMillis()));

			// 업로드된 파일의 확장자를 추출해서, 새 파일명에 붙임
			renameFileName += "." + originalFileName.substring(originalFileName.lastIndexOf(".") + 1);

			// 저장된 원본파일명을 rename 하기 위해 File 객체 만듦
			File originFile = new File(savePath + "\\" + originalFileName);
			File renameFile = new File(savePath + "\\" + renameFileName);

			// 이름 바꾸기 실행함
			if (!originFile.renameTo(renameFile)) { // 파일 이름바꾸기 실패했다면
				// 직접 바꾸는 코드 작성함
				// 업로드된 원본 파일(originFile)의 내용을 읽어서(read),
				// renameFile 에 기록함(write)
				// originFile 삭제함
				FileInputStream fin = new FileInputStream(originFile);
				FileOutputStream fout = new FileOutputStream(renameFile);
				int data = -1;
				byte[] buffer = new byte[1024];

				while ((data = fin.read(buffer, 0, buffer.length)) != -1) {
					fout.write(buffer, 0, buffer.length);
				}

				fin.close();
				fout.close();
				originFile.delete(); // 원본 파일 삭제함
			} // file rename failed...

			board.setBoardRenameFileName(renameFileName);
		} // upload file rename...

		// 6. 모델의 서비스 객체 생성하고, 메소드 실행하고 결과받기
		int result = new BoardService().insertBoard(board);

		// 7. 받은 결과를 가지고 성공/실패에 대한 뷰를 선택해서 내보냄
		if (result > 0) { // 게시글 원글 등록이 성공했다면
			response.sendRedirect("/nongbu/blist?page=1");
		} else {
			view = request.getRequestDispatcher("views/common/error.jsp");
			request.setAttribute("message", "게시글 원글 등록 실패!");
			System.out.println(board.getBoardCategory());
			System.out.println(board.getBoardContent());
			System.out.println(board.getBoardWriter());
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
