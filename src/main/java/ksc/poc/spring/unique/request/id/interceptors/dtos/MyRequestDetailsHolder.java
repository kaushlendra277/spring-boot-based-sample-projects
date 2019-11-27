package ksc.poc.spring.unique.request.id.interceptors.dtos;

public class MyRequestDetailsHolder {

	private static final ThreadLocal<MyRequestDetail> REQUESTHOLDER = new ThreadLocal<MyRequestDetail>();

	public static synchronized void setRequestDetails(MyRequestDetail requestDetails) {
		REQUESTHOLDER.set(requestDetails);
	}

	public static MyRequestDetail getRequestDetails() {
		MyRequestDetail requestDetail = REQUESTHOLDER.get();
		if (requestDetail == null) {
			requestDetail = new MyRequestDetail();
		}
		return requestDetail;
	}

	public static void clearRequestInfo() {
		REQUESTHOLDER.remove();
	}

	public static void setCorelationId(String corelationId) {
		MyRequestDetail requestDetails = MyRequestDetailsHolder.getRequestDetails();
		requestDetails.setCorelationId(corelationId);
		MyRequestDetailsHolder.setRequestDetails(requestDetails);
	}
	
	public static String getCorelationId() {
		MyRequestDetail requestDetails = MyRequestDetailsHolder.getRequestDetails();
		return requestDetails.getCorelationId();
	}
}
