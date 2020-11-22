package ei.eseptiyadi.caffeqita.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ResponseListTransaksi{

	@SerializedName("code")
	private int code;

	@SerializedName("listtransaksi")
	private List<ListtransaksiItem> listtransaksi;

	@SerializedName("message")
	private String message;

	@SerializedName("status")
	private boolean status;

	public int getCode(){
		return code;
	}

	public List<ListtransaksiItem> getListtransaksi(){
		return listtransaksi;
	}

	public String getMessage(){
		return message;
	}

	public boolean isStatus(){
		return status;
	}

	@Override
 	public String toString(){
		return 
			"ResponseListTransaksi{" + 
			"code = '" + code + '\'' + 
			",listtransaksi = '" + listtransaksi + '\'' + 
			",message = '" + message + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}