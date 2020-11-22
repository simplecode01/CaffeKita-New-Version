package ei.eseptiyadi.caffeqita.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ei.eseptiyadi.caffeqita.R;
import ei.eseptiyadi.caffeqita.model.ListmenuInchartItem;
import ei.eseptiyadi.caffeqita.model.update.DataUpdatedItem;
import ei.eseptiyadi.caffeqita.model.update.RequestUpdateQtyMenuinCart;
import ei.eseptiyadi.caffeqita.network.ApiServices;
import ei.eseptiyadi.caffeqita.network.RetrofitClient;
import ei.eseptiyadi.caffeqita.views.DetailTransaksiActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListMenuInChart extends RecyclerView.Adapter<ListMenuInChart.MyViewHolder> {

    Context context;
    List<ListmenuInchartItem> listmenuInchartItems;
    LayoutInflater inflater;
    private String kode_transaksi;

    public ListMenuInChart(Context context, List<ListmenuInchartItem> listmenuInchartItems, String kode_transaksi) {
        this.context = context;
        this.listmenuInchartItems = listmenuInchartItems;
        this.kode_transaksi = kode_transaksi;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.itemmenu_inchart, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.nmMenu.setText(listmenuInchartItems.get(position).getNamaMenu());
        holder.hrgMenu.setText(listmenuInchartItems.get(position).getHargaMenu());
        holder.katMenu.setText(listmenuInchartItems.get(position).getKategori());
        holder.jmlPesanan.setText(listmenuInchartItems.get(position).getJumlahPesanan());
        holder.ttlByr.setText(listmenuInchartItems.get(position).getJumlahHarga());

        holder.btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int qtyAwal = Integer.valueOf(listmenuInchartItems.get(position).getJumlahPesanan());
                int qtyTambah = ++qtyAwal;

                Toast.makeText(context, "Ini Plus  di menu " + listmenuInchartItems.get(position).getKodeMenupesanan() + " menjadi " + qtyTambah, Toast.LENGTH_SHORT).show();

                Log.d("LOG", "DATA " + listmenuInchartItems.get(position).getKodeRelasi() + " " + listmenuInchartItems.get(position).getKodeMenupesanan() + " " + listmenuInchartItems.get(position).getKodeTransaksi() + " ");

                String kode_menu = listmenuInchartItems.get(position).getKodeMenupesanan();
                String kode_transaksi = listmenuInchartItems.get(position).getKodeTransaksi();
                String kode_relasi = listmenuInchartItems.get(position).getKodeRelasi();

                ApiServices apiServices = RetrofitClient.getInstance();
                Call<RequestUpdateQtyMenuinCart> reqUpdateQtyMenuinCart = apiServices.updateMenuinCart(kode_transaksi, kode_menu, qtyTambah, kode_relasi);

                reqUpdateQtyMenuinCart.enqueue(new Callback<RequestUpdateQtyMenuinCart>() {
                    @Override
                    public void onResponse(Call<RequestUpdateQtyMenuinCart> call, Response<RequestUpdateQtyMenuinCart> response) {
                        if (response.isSuccessful()) {

                            boolean status = response.body().isStatus();

                            if (status == true) {
                                Toast.makeText(context, response.body().getQtyMenuTerupdate(), Toast.LENGTH_LONG).show();
                                holder.jmlPesanan.setText(response.body().getQtyMenuTerupdate());
                            } else {
                                Toast.makeText(context, response.body().getMessage(), Toast.LENGTH_LONG).show();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<RequestUpdateQtyMenuinCart> call, Throwable t) {

                    }
                });
            }
        });

        holder.btnMin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int qtyAwal = Integer.valueOf(listmenuInchartItems.get(position).getJumlahPesanan());
                int qtyKurang = qtyAwal - 1;
                Toast.makeText(context, "Ini Min  di menu " + listmenuInchartItems.get(position).getKodeMenupesanan() + " menjadi " + qtyKurang, Toast.LENGTH_SHORT).show();
            }
        });

        String namaMenu = listmenuInchartItems.get(position).getNamaMenu().toString();

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "Nama Menu " + namaMenu, Toast.LENGTH_SHORT).show();

            }
        });
    }

    @Override
    public int getItemCount() {
        return listmenuInchartItems.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView nmMenu, hrgMenu, katMenu, jmlPesanan, ttlByr;
        Button btnMin, btnPlus;

        public MyViewHolder(View itemView) {
            super(itemView);
            nmMenu = (TextView)itemView.findViewById(R.id.txtgetNamaMenu);
            hrgMenu = (TextView)itemView.findViewById(R.id.txtgetHarga);
            katMenu = (TextView)itemView.findViewById(R.id.txtgetKatMenu);
            jmlPesanan = (TextView)itemView.findViewById(R.id.txtgetJumlahPesan);
            ttlByr = (TextView)itemView.findViewById(R.id.txtgetTotalBayar);
            btnMin = (Button)itemView.findViewById(R.id.btMin);
            btnPlus = (Button)itemView.findViewById(R.id.btPlus);


        }
    }


}