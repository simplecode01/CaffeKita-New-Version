package ei.eseptiyadi.caffeqita.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ResponseListMenuInChart{

	@SerializedName("code")
	private int code;

	@SerializedName("listmenu_inchart")
	private List<ListmenuInchartItem> listmenuInchart;

	@SerializedName("message")
	private String message;

	@SerializedName("total_bayar")
	private String totalBayar;

	@SerializedName("status")
	private boolean status;

	public int getCode(){
		return code;
	}

	public List<ListmenuInchartItem> getListmenuInchart(){
		return listmenuInchart;
	}

	public String getMessage(){
		return message;
	}

	public String getTotalBayar(){
		return totalBayar;
	}

	public boolean isStatus(){
		return status;
	}
}