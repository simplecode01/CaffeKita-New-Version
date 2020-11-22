package ei.eseptiyadi.caffeqita.model;

import com.google.gson.annotations.SerializedName;

public class InitDatatransaksi{

	@SerializedName("totaldata_transaksisaatini")
	private String totaldataTransaksisaatini;

	@SerializedName("kode_unique")
	private String kodeUnique;

	@SerializedName("getdate_time")
	private String getdateTime;

	public String getTotaldataTransaksisaatini(){
		return totaldataTransaksisaatini;
	}

	public String getKodeUnique(){
		return kodeUnique;
	}

	public String getGetdateTime(){
		return getdateTime;
	}
}