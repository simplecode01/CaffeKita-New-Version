package ei.eseptiyadi.caffeqita.views;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.List;

import ei.eseptiyadi.caffeqita.R;
import ei.eseptiyadi.caffeqita.adapter.ListMenu;
import ei.eseptiyadi.caffeqita.model.ListmenuItem;
import ei.eseptiyadi.caffeqita.model.ResponseCancelOrder;
import ei.eseptiyadi.caffeqita.model.ResponseListMenu;
import ei.eseptiyadi.caffeqita.network.ApiServices;
import ei.eseptiyadi.caffeqita.network.RetrofitClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListMenuActivity extends AppCompatActivity {

    RecyclerView rvListMenu;
    SwipeRefreshLayout refreshMenu;

    LinearLayout layoutButton;
    String getNewCodeTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_menu);

        layoutButton = (LinearLayout)findViewById(R.id.itemButton);

        Intent getNewTransaction = getIntent();
        getNewCodeTransaction = getNewTransaction.getStringExtra("NEW_TRANSAKSI");

        getSupportActionBar().setTitle("New Transaction");
        getSupportActionBar().setSubtitle(Html.fromHtml("<small>" + getNewCodeTransaction + "</small>"));

        rvListMenu = (RecyclerView)findViewById(R.id.rvListMenu);
        refreshMenu = (SwipeRefreshLayout)findViewById(R.id.srlListMenuRefresh);
        rvListMenu.setHasFixedSize(true);
        LinearLayoutManager horizontalList = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        rvListMenu.setLayoutManager(horizontalList);

        listingDataMenu();

        rvListMenu.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                if (dy > 0 && layoutButton.isShown()) {
                    layoutButton.setVisibility(View.GONE);
                } else if (dy < 0 && !layoutButton.isShown()) {
                    layoutButton.setVisibility(View.VISIBLE);
                }
            }
        });

        refreshMenu.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshMenu();
            }
        });
    }

    private void refreshMenu() {

        ApiServices apiServices = RetrofitClient.getInstance();
        Call<ResponseListMenu> listMenu = apiServices.listmenu();

        listMenu.enqueue(new Callback<ResponseListMenu>() {
            @Override
            public void onResponse(Call<ResponseListMenu> call, Response<ResponseListMenu> response) {
                if (response.isSuccessful()) {
                    List<ListmenuItem> listmenuItems = response.body().getListmenu();
                    boolean status = response.body().isStatus();

                    if (status == true) {
                        ListMenu adapterListMenu = new ListMenu(ListMenuActivity.this, listmenuItems, getNewCodeTransaction);
                        rvListMenu.setAdapter(adapterListMenu);
                        refreshMenu.setRefreshing(false);
                    } else {
                        Toast.makeText(ListMenuActivity.this, "Your connection is failed!", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseListMenu> call, Throwable t) {

            }
        });

    }

    private void listingDataMenu() {
        ApiServices apiServices = RetrofitClient.getInstance();
        Call<ResponseListMenu> listMenu = apiServices.listmenu();

        listMenu.enqueue(new Callback<ResponseListMenu>() {
            @Override
            public void onResponse(Call<ResponseListMenu> call, Response<ResponseListMenu> response) {
                if (response.isSuccessful()) {
                    List<ListmenuItem> listmenuItems = response.body().getListmenu();
                    boolean status = response.body().isStatus();

                    if (status == true) {
                        ListMenu adapterListMenu = new ListMenu(ListMenuActivity.this, listmenuItems, getNewCodeTransaction);
                        rvListMenu.setAdapter(adapterListMenu);
                    } else {
                        Toast.makeText(ListMenuActivity.this, "Your connection is failed!", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseListMenu> call, Throwable t) {

            }
        });
    }

    public void cancelOrderAct(View view) {

        AlertDialog.Builder alertCancelOrder = new AlertDialog.Builder(this);
        alertCancelOrder
                .setTitle("Notice!")
                .setMessage("Apakah anda ingin membatalkan pesanan sekarang? membatalkan pesanan merupakan membatalkan transaksi.")
                .setPositiveButton("Batalkan Pesanan", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        ApiServices apiServices = RetrofitClient.getInstance();
                        Call<ResponseCancelOrder> cancelOrder = apiServices.cancelOrder(getNewCodeTransaction);

                        cancelOrder.enqueue(new Callback<ResponseCancelOrder>() {
                            @Override
                            public void onResponse(Call<ResponseCancelOrder> call, Response<ResponseCancelOrder> response) {
                                if (response.isSuccessful()) {
                                    Toast.makeText(ListMenuActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(ListMenuActivity.this, ListTransaksiActivity.class));
                                    finish();
                                } else {
                                    Toast.makeText(ListMenuActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<ResponseCancelOrder> call, Throwable t) {

                            }
                        });
                    }
                })
                .setNegativeButton("Lanjutkan", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
        AlertDialog showDialog = alertCancelOrder.create();
        showDialog.show();


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Toast.makeText(this, "Anda kembali ke list transaksi.", Toast.LENGTH_SHORT).show();
    }

    public void toCheckout(View view) {
        AlertDialog.Builder alertCheckout = new AlertDialog.Builder(this);
        alertCheckout
                .setTitle("Finish")
                .setMessage("Apakah Anda ingin checkout dari pembelian ?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
        AlertDialog showDialog = alertCheckout.create();
        showDialog.show();

    }
}