package ei.eseptiyadi.caffeqita.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ResponseListMenu{

	@SerializedName("code")
	private int code;

	@SerializedName("message")
	private String message;

	@SerializedName("listmenu")
	private List<ListmenuItem> listmenu;

	@SerializedName("status")
	private boolean status;

	public void setCode(int code){
		this.code = code;
	}

	public int getCode(){
		return code;
	}

	public void setMessage(String message){
		this.message = message;
	}

	public String getMessage(){
		return message;
	}

	public void setListmenu(List<ListmenuItem> listmenu){
		this.listmenu = listmenu;
	}

	public List<ListmenuItem> getListmenu(){
		return listmenu;
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
			"ResponseListMenu{" + 
			"code = '" + code + '\'' + 
			",message = '" + message + '\'' + 
			",listmenu = '" + listmenu + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}