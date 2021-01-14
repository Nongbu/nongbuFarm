package sale.controller;

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

import sale.model.service.SaleService;
import sale.model.vo.Sale;

/**
 * Servlet implementation class SaleInsert
 */
@WebServlet("/sinsert")
public class SaleInsert extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SaleInsert() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8"); //인코딩 처리		
		RequestDispatcher view = null;
		if(!ServletFileUpload.isMultipartContent(request)) {
			//multipart로 파일업로드 처리가 안되었다면
			view = request.getRequestDispatcher("views/common/error.jsp");
			request.setAttribute("message",
					"form의 enctype='multipart/form-data' 속성 누락되어있음");
			view.forward(request,response);
		}
		// 업로드할 파일의 용랑 제한 설정
		int maxSize = 1024 *1024 * 10;
		
		// 업로드 되는 파일의 저장 폴더 지정하기
		String savePath = request.getSession().getServletContext()
				.getRealPath("/resources/sale_files");
		//request -> MultipartRequest로 변환
		//자동으로 지정한 폴더에 파일이 저장 (업로드 완료)
		MultipartRequest mrequest = new MultipartRequest(
				request, savePath, maxSize, "UTF-8",
				new DefaultFileRenamePolicy());
		
		//전송 온 값 변수/vo 객체에 기록 저장
		//mrequest 로 추출해야 함(request로는 값이 null임)
		Sale sale = new Sale();
		
		sale.setProductNo(mrequest.getParameter("productno"));
		sale.setSellerId(mrequest.getParameter("sellerid"));
		sale.setSellerProduct(mrequest.getParameter("product"));
		sale.setUserAddress(mrequest.getParameter("address"));
		sale.setPrice(Integer.parseInt(mrequest.getParameter("price")));
		
		//서버에 업로드된 파일명 추출하기
		String originalFileName = mrequest.getFilesystemName("upfile");
		sale.setSellerOriginalFileName(originalFileName);
		
		//첨부파일이 있을때만 파일 이름 바꾸기
		//원본파일을 년월일시분초.확장자 형식으로 변경함
		if(originalFileName != null) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMDDHHmmss");
			
			//바꿀 파일명 만들기
			String renameFileName = sdf.format(new java.sql.Date(System.currentTimeMillis()));
			//업로드된 파일 확장자 추출해서, 새 파일명에 붙임
			renameFileName += "." + originalFileName.substring(originalFileName.lastIndexOf(".") + 1);
			//저장된 원본파일명을 rename 하기 위해 File 객체 만듦
			File originFile = new File(savePath + "\\" + originalFileName);
			File renameFile = new File(savePath + "\\" + renameFileName);
		
		//이름 바꾸기 실행
			if(!originFile.renameTo(renameFile)) {//파일 이름 바꾸기 실패하면
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
			}
			sale.setSellerRenameFileName(renameFileName);
		}		
		
		  // 모델 서비스 객체 생성, 메소드 실행 결과 받기 
		  int result = new SaleService().insertSale(sale);
		  if(result >0) {
			  response.sendRedirect("/nongbu/slist.do");
		}else {
			view = request.getRequestDispatcher("views/common/error.jsp");
			request.setAttribute("message","새 등록 실패");
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
