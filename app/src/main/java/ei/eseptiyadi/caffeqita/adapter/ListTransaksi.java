package ei.eseptiyadi.caffeqita.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ei.eseptiyadi.caffeqita.R;
import ei.eseptiyadi.caffeqita.model.ListtransaksiItem;
import ei.eseptiyadi.caffeqita.views.DetailTransaksiActivity;

public class ListTransaksi extends RecyclerView.Adapter<ListTransaksi.MyViewHolder> {

    Context context;
    List<ListtransaksiItem> listtransaksiItems;

    public ListTransaksi(Context context, List<ListtransaksiItem> listtransaksiItems) {
        this.context = context;
        this.listtransaksiItems = listtransaksiItems;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_listtransaksi, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.tvKodeTransaksi.setText(listtransaksiItems.get(position).getKodeTransaksi());
        holder.tvKodePembeli.setText(listtransaksiItems.get(position).getKodePelanggan());
        holder.tvTanggalTransaksi.setText(listtransaksiItems.get(position).getTanggalTransaksi());
        holder.tvMetodeBayar.setText(listtransaksiItems.get(position).getMetodePembayaran());
        holder.tvTotalCash.setText(listtransaksiItems.get(position).getTotalCash());
        holder.tvKodeWaitress.setText(listtransaksiItems.get(position).getKodeWaitress());


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "Data yang ada pilih adalah kode transaksi " + listtransaksiItems.get(position).getKodeTransaksi(), Toast.LENGTH_SHORT).show();
                kirimKodeTransaksi2Detail(listtransaksiItems.get(position).getKodeTransaksi());
            }
        });
    }

    @Override
    public int getItemCount() {
        return listtransaksiItems.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tvKodeTransaksi, tvKodePembeli, tvTanggalTransaksi, tvMetodeBayar, tvTotalCash, tvKodeWaitress;

        public MyViewHolder(View itemView) {
            super(itemView);

            tvKodeTransaksi = (TextView)itemView.findViewById(R.id.tvKodeTransaksi);
            tvKodePembeli = (TextView)itemView.findViewById(R.id.tvKodePelanggan);
            tvTanggalTransaksi = (TextView)itemView.findViewById(R.id.tvTanggalTransaksi);
            tvMetodeBayar = (TextView)itemView.findViewById(R.id.tvMetodeBayar);
            tvTotalCash = (TextView)itemView.findViewById(R.id.tvUangCash);
            tvKodeWaitress = (TextView)itemView.findViewById(R.id.tvKodeWaitress);
        }
    }

    public void kirimKodeTransaksi2Detail(String kodetransaksi) {
        Intent kirimKode = new Intent(context, DetailTransaksiActivity.class);
        kirimKode.putExtra("KODE_TRANSAKSI", kodetransaksi);
        context.startActivity(kirimKode);
    }
}
