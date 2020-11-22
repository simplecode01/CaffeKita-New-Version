package ei.eseptiyadi.caffeqita.model.update;

import com.google.gson.annotations.SerializedName;

public class DataUpdatedItem{

    @SerializedName("kode_transaksi")
    private String kodeTransaksi;

    @SerializedName("kode_relasi")
    private String kodeRelasi;

    @SerializedName("kode_menupesanan")
    private String kodeMenupesanan;

    public String getKodeTransaksi(){
        return kodeTransaksi;
    }

    public String getKodeRelasi(){
        return kodeRelasi;
    }

    public String getKodeMenupesanan(){
        return kodeMenupesanan;
    }
}