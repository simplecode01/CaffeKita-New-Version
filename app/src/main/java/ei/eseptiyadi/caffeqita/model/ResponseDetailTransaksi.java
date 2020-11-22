package ei.eseptiyadi.caffeqita.model;

import com.google.gson.annotations.SerializedName;

public class ResponseDetailTransaksi{

	@SerializedName("code")
	private int code;

	@SerializedName("message")
	private String message;

	@SerializedName("status")
	private boolean status;

	@SerializedName("detail_transaksi")
	private DetailTransaksi detailTransaksi;

	public int getCode(){
		return code;
	}

	public String getMessage(){
		return message;
	}

	public boolean isStatus(){
		return status;
	}

	public DetailTransaksi getDetailTransaksi(){
		return detailTransaksi;
	}
}