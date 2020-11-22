package ei.eseptiyadi.caffeqita.model;

import com.google.gson.annotations.SerializedName;

public class ListmenuItem{

	@SerializedName("kode_menupesanan")
	private String kodeMenupesanan;

	@SerializedName("nama_menu")
	private String namaMenu;

	@SerializedName("harga_menu")
	private String hargaMenu;

	@SerializedName("img_menu")
	private String imgMenu;

	@SerializedName("kategori")
	private String kategori;

	public void setKodeMenupesanan(String kodeMenupesanan){
		this.kodeMenupesanan = kodeMenupesanan;
	}

	public String getKodeMenupesanan(){
		return kodeMenupesanan;
	}

	public void setNamaMenu(String namaMenu){
		this.namaMenu = namaMenu;
	}

	public String getNamaMenu(){
		return namaMenu;
	}

	public void setHargaMenu(String hargaMenu){
		this.hargaMenu = hargaMenu;
	}

	public String getHargaMenu(){
		return hargaMenu;
	}

	public void setImgMenu(String imgMenu){
		this.imgMenu = imgMenu;
	}

	public String getImgMenu(){
		return imgMenu;
	}

	public void setKategori(String kategori){
		this.kategori = kategori;
	}

	public String getKategori(){
		return kategori;
	}

	@Override
 	public String toString(){
		return 
			"ListmenuItem{" + 
			"kode_menupesanan = '" + kodeMenupesanan + '\'' + 
			",nama_menu = '" + namaMenu + '\'' + 
			",harga_menu = '" + hargaMenu + '\'' + 
			",img_menu = '" + imgMenu + '\'' + 
			",kategori = '" + kategori + '\'' + 
			"}";
		}
}