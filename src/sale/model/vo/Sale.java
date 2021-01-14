package sale.model.vo;

import java.sql.Date;

/**
 * Servlet implementation class Sale
 */

public class Sale implements java.io.Serializable { 
	private static final long serialVersionUID = 1L;
    
	private String productNo; //상품번호
	private String sellerId; // 판매자아이디
	private String sellerProduct; // 상품명
	private String userAddress; // 구매자주소
	private int price; // 상품 가격
	private String sellerOriginalFileName; // 원본첨부파일이름
	private String sellerRenameFileName; // 수정첨부파일이름
	
public Sale() {}

public Sale(String productNo, String sellerId, String sellerProduct, String userAddress, int price,
		String sellerOriginalFileName, String sellerRenameFileName) {
	super();
	this.productNo = productNo;
	this.sellerId = sellerId;
	this.sellerProduct = sellerProduct;
	this.userAddress = userAddress;
	this.price = price;
	this.sellerOriginalFileName = sellerOriginalFileName;
	this.sellerRenameFileName = sellerRenameFileName;
}

public String getProductNo() {
	return productNo;
}

public void setProductNo(String productNo) {
	this.productNo = productNo;
}

public String getSellerId() {
	return sellerId;
}

public void setSellerId(String sellerId) {
	this.sellerId = sellerId;
}

public String getSellerProduct() {
	return sellerProduct;
}

public void setSellerProduct(String sellerProduct) {
	this.sellerProduct = sellerProduct;
}

public String getUserAddress() {
	return userAddress;
}

public void setUserAddress(String userAddress) {
	this.userAddress = userAddress;
}

public int getPrice() {
	return price;
}

public void setPrice(int price) {
	this.price = price;
}

public String getSellerOriginalFileName() {
	return sellerOriginalFileName;
}

public void setSellerOriginalFileName(String sellerOriginalFileName) {
	this.sellerOriginalFileName = sellerOriginalFileName;
}

public String getSellerRenameFileName() {
	return sellerRenameFileName;
}

public void setSellerRenameFileName(String sellerRenameFileName) {
	this.sellerRenameFileName = sellerRenameFileName;
}

public static long getSerialversionuid() {
	return serialVersionUID;
}

@Override
public String toString() {
	return "Sale [productNo=" + productNo + ", sellerId=" + sellerId + ", sellerProduct=" + sellerProduct
			+ ", userAddress=" + userAddress + ", price=" + price + ", sellerOriginalFileName=" + sellerOriginalFileName
			+ ", sellerRenameFileName=" + sellerRenameFileName + "]";
}
	
	
	
	
}
