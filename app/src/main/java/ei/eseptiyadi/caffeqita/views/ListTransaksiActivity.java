package ei.eseptiyadi.caffeqita.views;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import ei.eseptiyadi.caffeqita.R;
import ei.eseptiyadi.caffeqita.adapter.ListTransaksi;
import ei.eseptiyadi.caffeqita.model.ListtransaksiItem;
import ei.eseptiyadi.caffeqita.model.ResponseBuildNewTransaksi;
import ei.eseptiyadi.caffeqita.model.ResponseCreateTransaksi;
import ei.eseptiyadi.caffeqita.model.ResponseListTransaksi;
import ei.eseptiyadi.caffeqita.network.ApiServices;
import ei.eseptiyadi.caffeqita.network.RetrofitClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListTransaksiActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    SwipeRefreshLayout swfLayoutRefreshTransaksi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listtransaksi);

        recyclerView = (RecyclerView)findViewById(R.id.rvListTransaksi);
        swfLayoutRefreshTransaksi = (SwipeRefreshLayout)findViewById(R.id.srlListTransaksiRefresh);
        swfLayoutRefreshTransaksi.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // Do Action
                getUpdateListTransaksi();
            }
        });

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        getListTransaksi();
    }

    private void getListTransaksi() {
        ApiServices apiServices = RetrofitClient.getInstance();
        Call<ResponseListTransaksi> listTransaksiCall = apiServices.listtransaksi();

        listTransaksiCall.enqueue(new Callback<ResponseListTransaksi>() {
            @Override
            public void onResponse(Call<ResponseListTransaksi> call, Response<ResponseListTransaksi> response) {
                if (response.isSuccessful()) {
                    List<ListtransaksiItem> listtransaksiItems = response.body().getListtransaksi();
                    boolean status = response.body().isStatus();
                    if (status == true) {
                        ListTransaksi adapterListtransaksi = new ListTransaksi(ListTransaksiActivity.this, listtransaksiItems);
                        recyclerView.setAdapter(adapterListtransaksi);
                        // Log.d("LOG", "Data : " + response.body().getListtransaksi() + "\n");
                    } else {
                        Toast.makeText(ListTransaksiActivity.this, "Masalah otentikasi sistem", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseListTransaksi> call, Throwable t) {
                Toast.makeText(ListTransaksiActivity.this, "ERROR " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getUpdateListTransaksi() {
        ApiServices apiServices = RetrofitClient.getInstance();
        Call<ResponseListTransaksi> listTransaksiCall = apiServices.listtransaksi();

        listTransaksiCall.enqueue(new Callback<ResponseListTransaksi>() {
            @Override
            public void onResponse(Call<ResponseListTransaksi> call, Response<ResponseListTransaksi> response) {
                if (response.isSuccessful()) {
                    List<ListtransaksiItem> listtransaksiItems = response.body().getListtransaksi();
                    boolean status = response.body().isStatus();
                    if (status == true) {
                        ListTransaksi adapterListtransaksi = new ListTransaksi(ListTransaksiActivity.this, listtransaksiItems);
                        recyclerView.setAdapter(adapterListtransaksi);
                        Toast.makeText(ListTransaksiActivity.this, "Data list transaksi berhasil diperbaharui", Toast.LENGTH_SHORT).show();
                        swfLayoutRefreshTransaksi.setRefreshing(false);
                    } else {
                        Toast.makeText(ListTransaksiActivity.this, "Galat!, Masalah otentikasi sistem", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseListTransaksi> call, Throwable t) {
                Toast.makeText(ListTransaksiActivity.this, "ERROR " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void FABaddTransaksi(View view) {
        Toast.makeText(this, "add transaction", Toast.LENGTH_SHORT).show();

        ApiServices apiServices = RetrofitClient.getInstance();
        Call<ResponseBuildNewTransaksi> buildNewTranscation = apiServices.buildNewTransaksi();

        buildNewTranscation.enqueue(new Callback<ResponseBuildNewTransaksi>() {
            @Override
            public void onResponse(Call<ResponseBuildNewTransaksi> call, Response<ResponseBuildNewTransaksi> response) {
                AlertDialog.Builder builderDialog = new AlertDialog.Builder(ListTransaksiActivity.this);

                if (response.isSuccessful()) {
                    String totaldata_transaksi = response.body().getInitDatatransaksi().getTotaldataTransaksisaatini();
                    String kode_unique = response.body().getInitDatatransaksi().getKodeUnique();
                    String tanggal_tambah = response.body().getInitDatatransaksi().getGetdateTime();

                    // TODO 1: CREATE NEW UNIQUE NUMBER OF TRANSCATION
                    int newkodetransaksi = Integer.valueOf(totaldata_transaksi) + 1;

                    builderDialog
                            .setTitle("Transaksi Baru")
                            .setMessage("Kode transaksi kita yang baru adalah " + kode_unique + "-" + tanggal_tambah + "-" + newkodetransaksi)
                            .setPositiveButton("Test Simpan Transaksi", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    String kodeTransaksiBaru = kode_unique + "-" + tanggal_tambah + "-" + newkodetransaksi;

                                    Log.d("LOG", "KODE: " + kodeTransaksiBaru);

                                    String kodeWaitress = "WAIT-056";
                                    String kodeTransaksi = kodeTransaksiBaru;

                                    ApiServices apiServices = RetrofitClient.getInstance();
                                    Call<ResponseCreateTransaksi> createTransaksiCall = apiServices.createNewTransaction(kodeTransaksi, kodeWaitress);

                                    createTransaksiCall.enqueue(new Callback<ResponseCreateTransaksi>() {
                                        @Override
                                        public void onResponse(Call<ResponseCreateTransaksi> call, Response<ResponseCreateTransaksi> response) {
                                            if (response.isSuccessful()) {
                                                //Log.d("LOG", "NEW :" + response.body().getAddedTransaksi().toString());
                                                Boolean status = response.body().isStatus();

                                                if (status == true) {

                                                    Toast.makeText(ListTransaksiActivity.this, response.body().getMessage().toString(), Toast.LENGTH_SHORT).show();

                                                    Intent kirimKodeTransaksiBaru = new Intent(getApplicationContext(), ListMenuActivity.class);
                                                    kirimKodeTransaksiBaru.putExtra("NEW_TRANSAKSI", kodeTransaksiBaru);
                                                    startActivity(kirimKodeTransaksiBaru);
                                                } else {
                                                    Toast.makeText(ListTransaksiActivity.this, response.body().getMessage().toString(), Toast.LENGTH_SHORT).show();
                                                }

                                            }
                                        }

                                        @Override
                                        public void onFailure(Call<ResponseCreateTransaksi> call, Throwable t) {

                                        }
                                    });
                                }
                            })
                            .setNegativeButton("Batalkan", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {

                                }
                            });
                    AlertDialog alertDialog = builderDialog.create();
                    alertDialog.show();
                } else {
                    Toast.makeText(ListTransaksiActivity.this, "Check your internet connection, or api services.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBuildNewTransaksi> call, Throwable t) {
                Toast.makeText(ListTransaksiActivity.this, "Message Error " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        ApiServices apiServices = RetrofitClient.getInstance();
        Call<ResponseListTransaksi> listTransaksiCall = apiServices.listtransaksi();

        listTransaksiCall.enqueue(new Callback<ResponseListTransaksi>() {
            @Override
            public void onResponse(Call<ResponseListTransaksi> call, Response<ResponseListTransaksi> response) {
                if (response.isSuccessful()) {
                    List<ListtransaksiItem> listtransaksiItems = response.body().getListtransaksi();
                    boolean status = response.body().isStatus();
                    if (status == true) {
                        ListTransaksi adapterListtransaksi = new ListTransaksi(ListTransaksiActivity.this, listtransaksiItems);
                        recyclerView.setAdapter(adapterListtransaksi);
                    } else {
                        Toast.makeText(ListTransaksiActivity.this, "Data gagal di refresh", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseListTransaksi> call, Throwable t) {
                Toast.makeText(ListTransaksiActivity.this, "ERROR " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void AddMenu(View view) {
    }

}