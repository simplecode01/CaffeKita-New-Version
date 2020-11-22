package ei.eseptiyadi.caffeqita.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ResponseCreateTransaksi{

	@SerializedName("code")
	private int code;

	@SerializedName("added_transaksi")
	private List<AddedTransaksiItem> addedTransaksi;

	@SerializedName("message")
	private String message;

	@SerializedName("status")
	private boolean status;

	public void setCode(int code){
		this.code = code;
	}

	public int getCode(){
		return code;
	}

	public void setAddedTransaksi(List<AddedTransaksiItem> addedTransaksi){
		this.addedTransaksi = addedTransaksi;
	}

	public List<AddedTransaksiItem> getAddedTransaksi(){
		return addedTransaksi;
	}

	public void setMessage(String message){
		this.message = message;
	}

	public String getMessage(){
		return message;
	}

	public void setStatus(boolean status){
		this.status = status;
	}

	public boolean isStatus(){
		return status;
	}

	@Override
 	public String toString(){
		return 
			"ResponseCreateTransaksi{" + 
			"code = '" + code + '\'' + 
			",added_transaksi = '" + addedTransaksi + '\'' + 
			",message = '" + message + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}