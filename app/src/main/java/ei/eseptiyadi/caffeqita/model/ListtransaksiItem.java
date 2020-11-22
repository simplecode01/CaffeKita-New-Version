package ei.eseptiyadi.caffeqita.model;

import com.google.gson.annotations.SerializedName;

public class ListtransaksiItem{

	@SerializedName("kode_transaksi")
	private String kodeTransaksi;

	@SerializedName("metode_pembayaran")
	private String metodePembayaran;

	@SerializedName("total_cash")
	private String totalCash;

	@SerializedName("tanggal_transaksi")
	private String tanggalTransaksi;

	@SerializedName("kode_pelanggan")
	private String kodePelanggan;

	@SerializedName("kode_waitress")
	private String kodeWaitress;

	public String getKodeTransaksi(){
		return kodeTransaksi;
	}

	public String getMetodePembayaran(){
		return metodePembayaran;
	}

	public String getTotalCash(){
		return totalCash;
	}

	public String getTanggalTransaksi(){
		return tanggalTransaksi;
	}

	public String getKodePelanggan(){
		return kodePelanggan;
	}

	public String getKodeWaitress(){
		return kodeWaitress;
	}

	@Override
 	public String toString(){
		return 
			"ListtransaksiItem{" + 
			"kode_transaksi = '" + kodeTransaksi + '\'' + 
			",metode_pembayaran = '" + metodePembayaran + '\'' + 
			",total_cash = '" + totalCash + '\'' + 
			",tanggal_transaksi = '" + tanggalTransaksi + '\'' + 
			",kode_pelanggan = '" + kodePelanggan + '\'' + 
			",kode_waitress = '" + kodeWaitress + '\'' + 
			"}";
		}
}