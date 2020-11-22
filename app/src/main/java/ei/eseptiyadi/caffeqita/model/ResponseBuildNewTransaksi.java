package ei.eseptiyadi.caffeqita.model;

import com.google.gson.annotations.SerializedName;

public class ResponseBuildNewTransaksi{

	@SerializedName("code")
	private int code;

	@SerializedName("message")
	private String message;

	@SerializedName("status")
	private boolean status;

	@SerializedName("init_datatransaksi")
	private InitDatatransaksi initDatatransaksi;

	public int getCode(){
		return code;
	}

	public String getMessage(){
		return message;
	}

	public boolean isStatus(){
		return status;
	}

	public InitDatatransaksi getInitDatatransaksi(){
		return initDatatransaksi;
	}
}