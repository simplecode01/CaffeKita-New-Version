package ei.eseptiyadi.caffeqita.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import ei.eseptiyadi.caffeqita.R;
import ei.eseptiyadi.caffeqita.adapter.ListMenuInChart;
import ei.eseptiyadi.caffeqita.model.ListmenuInchartItem;
import ei.eseptiyadi.caffeqita.model.ResponseCancelOrder;
import ei.eseptiyadi.caffeqita.model.ResponseDetailTransaksi;
import ei.eseptiyadi.caffeqita.model.ResponseListMenuInChart;
import ei.eseptiyadi.caffeqita.network.ApiServices;
import ei.eseptiyadi.caffeqita.network.RetrofitClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailTransaksiActivity extends AppCompatActivity {

    TextView txKodeTransaksi, txNamaPelanggan, txTotalHarusDibayar,txMetodeBayar, txUangCashMember, txUangKembalian;
    TextView txInformasiMenuinChartnotFound;
    Button btnPlus, btnMin;

    String getKodeTransaksi;

    RecyclerView rvListmenuInChart;

    Button btnHapusTransaksi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_transaksi);

        txKodeTransaksi = (TextView)findViewById(R.id.txtKodeTransaksi);
        txNamaPelanggan = (TextView)findViewById(R.id.txtNamaPelanggan);
        txTotalHarusDibayar = (TextView)findViewById(R.id.txtTotalBayar);
        txMetodeBayar = (TextView)findViewById(R.id.txtMetodeBayar);
        txUangCashMember = (TextView)findViewById(R.id.txtUangCash);
        txUangKembalian = (TextView)findViewById(R.id.txtUangKembali);
        rvListmenuInChart = (RecyclerView)findViewById(R.id.rvListMenuinChart);
        btnHapusTransaksi = (Button)findViewById(R.id.btnHapusTransaksi);
        txInformasiMenuinChartnotFound = (TextView)findViewById(R.id.txtInfoTidakTersedia);



        rvListmenuInChart.setHasFixedSize(true);
        LinearLayoutManager horizontalList = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        rvListmenuInChart.setLayoutManager(horizontalList);
        rvListmenuInChart.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0 && btnHapusTransaksi.isShown()) {
                    btnHapusTransaksi.setVisibility(View.GONE);
                } else if (dy < 0 && !btnHapusTransaksi.isShown()) {
                    btnHapusTransaksi.setVisibility(View.VISIBLE);
                }
            }
        });

        Intent getIntent = getIntent();
        getKodeTransaksi = getIntent.getStringExtra("KODE_TRANSAKSI");
        txKodeTransaksi.setText(getKodeTransaksi);

        showDetailTransaksi(getKodeTransaksi);
        showMenuinChart(getKodeTransaksi);

    }

    public void showMenuinChart(String getKodeTransaksi) {
        ApiServices apiServices = RetrofitClient.getInstance();
        Call<ResponseListMenuInChart> listmenuInChart = apiServices.listmenuinOrder(getKodeTransaksi);

        listmenuInChart.enqueue(new Callback<ResponseListMenuInChart>() {
            @Override
            public void onResponse(Call<ResponseListMenuInChart> call, Response<ResponseListMenuInChart> response) {
                if (response.isSuccessful()) {
                    boolean status = response.body().isStatus();
                    if (status == true) {
                        Toast.makeText(DetailTransaksiActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        txTotalHarusDibayar.setText("Total Bayar (Rp.) : " + response.body().getTotalBayar());

                        List<ListmenuInchartItem> listmenuInChart = response.body().getListmenuInchart();
                        ListMenuInChart adapaterListMenuinChart = new ListMenuInChart(DetailTransaksiActivity.this, listmenuInChart, getKodeTransaksi);
                        rvListmenuInChart.setAdapter(adapaterListMenuinChart);


                    } else {
                        txInformasiMenuinChartnotFound.setVisibility(View.VISIBLE);
                        Toast.makeText(DetailTransaksiActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(DetailTransaksiActivity.this, "Check your connection internet", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseListMenuInChart> call, Throwable t) {
                Toast.makeText(DetailTransaksiActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void showDetailTransaksi(String getKodeTransaksi) {
        ApiServices apiServices = RetrofitClient.getInstance();
        Call<ResponseDetailTransaksi> responseDetailTransaksiCall = apiServices.detailtransaksi(getKodeTransaksi);

        responseDetailTransaksiCall.enqueue(new Callback<ResponseDetailTransaksi>() {
            @Override
            public void onResponse(Call<ResponseDetailTransaksi> call, Response<ResponseDetailTransaksi> response) {
                if (response.isSuccessful()) {
                    boolean status = response.body().isStatus();
                    if (status == true) {

                    } else {

                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseDetailTransaksi> call, Throwable t) {
                Toast.makeText(DetailTransaksiActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void hapusTransaksi(View view) {
        AlertDialog.Builder alertCancelOrder = new AlertDialog.Builder(this);
        alertCancelOrder
                .setTitle("Notice!")
                .setMessage("Apakah anda ingin membatalkan menghapus transaksi? menghapus transaksi akan menghilangkan data transaksi ini.")
                .setPositiveButton("Hapus Transaksi", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        ApiServices apiServices = RetrofitClient.getInstance();
                        Call<ResponseCancelOrder> cancelOrder = apiServices.cancelOrder(getKodeTransaksi);

                        cancelOrder.enqueue(new Callback<ResponseCancelOrder>() {
                            @Override
                            public void onResponse(Call<ResponseCancelOrder> call, Response<ResponseCancelOrder> response) {
                                if (response.isSuccessful()) {
                                    Toast.makeText(DetailTransaksiActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(DetailTransaksiActivity.this, ListTransaksiActivity.class));
                                    finish();
                                } else {
                                    Toast.makeText(DetailTransaksiActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<ResponseCancelOrder> call, Throwable t) {

                            }
                        });
                    }
                })
                .setNegativeButton("Batalkan", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
        AlertDialog showDialog = alertCancelOrder.create();
        showDialog.show();
    }
}