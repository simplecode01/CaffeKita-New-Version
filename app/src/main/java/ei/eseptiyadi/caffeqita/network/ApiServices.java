package ei.eseptiyadi.caffeqita.network;

import ei.eseptiyadi.caffeqita.model.ResponseBuildNewTransaksi;
import ei.eseptiyadi.caffeqita.model.ResponseCancelOrder;
import ei.eseptiyadi.caffeqita.model.ResponseCreateTransaksi;
import ei.eseptiyadi.caffeqita.model.ResponseDetailTransaksi;
import ei.eseptiyadi.caffeqita.model.ResponseListMenu;
import ei.eseptiyadi.caffeqita.model.ResponseListMenuInChart;
import ei.eseptiyadi.caffeqita.model.ResponseListTransaksi;
import ei.eseptiyadi.caffeqita.model.order.ResponseAddMenuToCart;
import ei.eseptiyadi.caffeqita.model.update.RequestUpdateQtyMenuinCart;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiServices {

    @GET("listtransaksi.php")
    Call<ResponseListTransaksi> listtransaksi();

    @FormUrlEncoded
    @POST("aksestransaksi.php")
    Call<ResponseDetailTransaksi> detailtransaksi(
            @Field("kode_transaksi") String kodetransaksi
    );

    @FormUrlEncoded
    @POST("addtransaksi.php")
    Call<ResponseCreateTransaksi> createNewTransaction(
            @Field("kode_transaksi") String kodeTransaksi,
            @Field("kode_waitress") String kodeWaitress
    );

    @FormUrlEncoded
    @POST("cancelorder.php")
    Call<ResponseCancelOrder> cancelOrder (
            @Field("kode_transaksi") String kodeTransaksi
    );

    @FormUrlEncoded
    @POST("transaksi/listmenuinchart.php")
    Call<ResponseListMenuInChart> listmenuinOrder (
            @Field("kode_transaksi") String kodeTransaksi
    );

    @FormUrlEncoded
    @POST("transaksi/addmenu_inchart.php")
    Call<ResponseAddMenuToCart> addMenuinOrder (
            @Field("kode_transaksi") String kodeTransaksi,
            @Field("kode_menupesanan") String kode_menupesanan,
            @Field("jumlah_pesanan") int jumlah_pesanan
    );

    @FormUrlEncoded
    @POST("transaksi/updateqtymenu_inchart.php")
    Call<RequestUpdateQtyMenuinCart> updateMenuinCart (
            @Field("kode_transaksi") String kodeTransaksi,
            @Field("kode_menupesanan") String kode_menupesanan,
            @Field("jumlah_pesanan") int jumlah_pesanan,
            @Field("kode_relasi") String kode_relasi
    );

    @GET("initialdatatransaksi.php")
    Call<ResponseBuildNewTransaksi> buildNewTransaksi();

    @GET("listmenu.php")
    Call<ResponseListMenu> listmenu();
}