package ei.eseptiyadi.caffeqita.model;

import com.google.gson.annotations.SerializedName;

public class DetailTransaksi{

	@SerializedName("kode_transaksi")
	private String kodeTransaksi;

	@SerializedName("metode_pembayaran")
	private String metodePembayaran;

	@SerializedName("nama_pelanggan")
	private String namaPelanggan;

	@SerializedName("uang_kembalian")
	private String uangKembalian;

	@SerializedName("total_yang_harus_dibayar")
	private String totalYangHarusDibayar;

	@SerializedName("uang_cash")
	private String uangCash;

	public String getKodeTransaksi(){
		return kodeTransaksi;
	}

	public String getMetodePembayaran(){
		return metodePembayaran;
	}

	public String getNamaPelanggan(){
		return namaPelanggan;
	}

	public String getUangKembalian(){
		return uangKembalian;
	}

	public String getTotalYangHarusDibayar(){
		return totalYangHarusDibayar;
	}

	public String getUangCash(){
		return uangCash;
	}
}