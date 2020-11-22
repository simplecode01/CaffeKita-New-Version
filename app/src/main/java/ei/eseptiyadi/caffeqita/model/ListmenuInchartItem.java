package ei.eseptiyadi.caffeqita.model;

import com.google.gson.annotations.SerializedName;

public class ListmenuInchartItem{

	@SerializedName("jumlah_harga")
	private String jumlahHarga;

	@SerializedName("kode_transaksi")
	private String kodeTransaksi;

	@SerializedName("kode_relasi")
	private String kodeRelasi;

	@SerializedName("kode_menupesanan")
	private String kodeMenupesanan;

	@SerializedName("nama_menu")
	private String namaMenu;

	@SerializedName("harga_menu")
	private String hargaMenu;

	@SerializedName("jumlah_pesanan")
	private String jumlahPesanan;

	@SerializedName("kategori")
	private String kategori;

	public String getJumlahHarga(){
		return jumlahHarga;
	}

	public String getKodeTransaksi(){
		return kodeTransaksi;
	}

	public String getKodeRelasi(){
		return kodeRelasi;
	}

	public String getKodeMenupesanan(){
		return kodeMenupesanan;
	}

	public String getNamaMenu(){
		return namaMenu;
	}

	public String getHargaMenu(){
		return hargaMenu;
	}

	public String getJumlahPesanan(){
		return jumlahPesanan;
	}

	public String getKategori(){
		return kategori;
	}
}
