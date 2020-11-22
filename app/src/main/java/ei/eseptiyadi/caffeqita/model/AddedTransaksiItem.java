package ei.eseptiyadi.caffeqita.model;

import com.google.gson.annotations.SerializedName;

public class AddedTransaksiItem{

	@SerializedName("kode_transaksi")
	private String kodeTransaksi;

	@SerializedName("metode_pembayaran")
	private Object metodePembayaran;

	@SerializedName("total_cash")
	private Object totalCash;

	@SerializedName("tanggal_transaksi")
	private String tanggalTransaksi;

	@SerializedName("kode_pelanggan")
	private Object kodePelanggan;

	@SerializedName("kode_waitress")
	private String kodeWaitress;

	public void setKodeTransaksi(String kodeTransaksi){
		this.kodeTransaksi = kodeTransaksi;
	}

	public String getKodeTransaksi(){
		return kodeTransaksi;
	}

	public void setMetodePembayaran(Object metodePembayaran){
		this.metodePembayaran = metodePembayaran;
	}

	public Object getMetodePembayaran(){
		return metodePembayaran;
	}

	public void setTotalCash(Object totalCash){
		this.totalCash = totalCash;
	}

	public Object getTotalCash(){
		return totalCash;
	}

	public void setTanggalTransaksi(String tanggalTransaksi){
		this.tanggalTransaksi = tanggalTransaksi;
	}

	public String getTanggalTransaksi(){
		return tanggalTransaksi;
	}

	public void setKodePelanggan(Object kodePelanggan){
		this.kodePelanggan = kodePelanggan;
	}

	public Object getKodePelanggan(){
		return kodePelanggan;
	}

	public void setKodeWaitress(String kodeWaitress){
		this.kodeWaitress = kodeWaitress;
	}

	public String getKodeWaitress(){
		return kodeWaitress;
	}

	@Override
 	public String toString(){
		return 
			"AddedTransaksiItem{" + 
			"kode_transaksi = '" + kodeTransaksi + '\'' + 
			",metode_pembayaran = '" + metodePembayaran + '\'' + 
			",total_cash = '" + totalCash + '\'' + 
			",tanggal_transaksi = '" + tanggalTransaksi + '\'' + 
			",kode_pelanggan = '" + kodePelanggan + '\'' + 
			",kode_waitress = '" + kodeWaitress + '\'' + 
			"}";
		}
}