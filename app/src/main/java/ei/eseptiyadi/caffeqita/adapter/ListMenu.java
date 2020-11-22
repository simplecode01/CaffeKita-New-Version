package ei.eseptiyadi.caffeqita.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import ei.eseptiyadi.caffeqita.R;
import ei.eseptiyadi.caffeqita.model.ListmenuItem;
import ei.eseptiyadi.caffeqita.model.order.ResponseAddMenuToCart;
import ei.eseptiyadi.caffeqita.network.ApiServices;
import ei.eseptiyadi.caffeqita.network.RetrofitClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListMenu extends RecyclerView.Adapter<ListMenu.MyViewHolder> {

    Context context;
    List<ListmenuItem> listmenuItems;
    LayoutInflater inflater;
    private String kode_transaksi;

    public ListMenu(Context context, List<ListmenuItem> listmenuItems, String kode_transaksi) {
        this.context = context;
        this.listmenuItems = listmenuItems;
        this.kode_transaksi = kode_transaksi;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_listmenu, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.tvNamaMenu.setText(listmenuItems.get(position).getNamaMenu());
        holder.tvHargaMenu.setText(listmenuItems.get(position).getHargaMenu());
        holder.tvKategoriMenu.setText(listmenuItems.get(position).getKategori());

        String name_of_image = listmenuItems.get(position).getImgMenu();

        String link_image = "https://caffesmk11.000webhostapp.com/caffe/pictures/" + name_of_image;

        Glide.with(context).load(link_image).into(holder.imgMenu);

        String kode_menu = listmenuItems.get(position).getKodeMenupesanan();

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alertAddMenu = new AlertDialog.Builder(context);
                inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View dialogView = inflater.inflate(R.layout.choosingmenu, null);

                    TextView namaMenu, hargaMenu, kategoriMenu;
                    ImageView imgMenu;
                    EditText setJumlahPesanan;

                    imgMenu = (ImageView)dialogView.findViewById(R.id.ImgGetMenu);
                    namaMenu = (TextView)dialogView.findViewById(R.id.TvGetNamaMenu);
                    hargaMenu = (TextView)dialogView.findViewById(R.id.TvGetHargaMenu);
                    kategoriMenu = (TextView)dialogView.findViewById(R.id.TvGetKategoriMenu);
                    setJumlahPesanan = (EditText)dialogView.findViewById(R.id.setJumlahPesanan);

                    namaMenu.setText(listmenuItems.get(position).getNamaMenu());
                    hargaMenu.setText(listmenuItems.get(position).getHargaMenu());
                    kategoriMenu.setText(listmenuItems.get(position).getKategori());
                    Glide.with(context).load(link_image).into(imgMenu);


                alertAddMenu
                        .setView(dialogView)
                        .setCancelable(true)
                        .setPositiveButton("Add to Chart", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                String jumlah = String.valueOf(setJumlahPesanan.getText());
                                int setjumlah = Integer.parseInt(jumlah);
                                Log.d("LOG", "MESSAGE " + setjumlah);

                                addMenutoCart(kode_transaksi, kode_menu, setjumlah);
                            }
                        })
                        .setNeutralButton("Batalkan", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        });

                AlertDialog showDialog = alertAddMenu.create();
                showDialog.show();
            }
        });


    }

    private void addMenutoCart(String kode_transaksi, String kode_menu, int jumlahPesanan) {
        ApiServices apiServices = RetrofitClient.getInstance();
        Call<ResponseAddMenuToCart> addMenutoCart = apiServices.addMenuinOrder(kode_transaksi, kode_menu, jumlahPesanan);

        addMenutoCart.enqueue(new Callback<ResponseAddMenuToCart>() {
            @Override
            public void onResponse(Call<ResponseAddMenuToCart> call, Response<ResponseAddMenuToCart> response) {
                if (response.isSuccessful()) {
                    Boolean status = response.body().isStatus();
                    if (status == true) {
                        Toast.makeText(context, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(context, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseAddMenuToCart> call, Throwable t) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return listmenuItems.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView imgMenu;
        TextView tvNamaMenu, tvHargaMenu, tvKategoriMenu;

        public MyViewHolder(View itemView) {
            super(itemView);
            imgMenu = (ImageView)itemView.findViewById(R.id.IvMenu);
            tvNamaMenu = (TextView)itemView.findViewById(R.id.TvNamaMenu);
            tvHargaMenu = (TextView)itemView.findViewById(R.id.TvHargaMenu);
            tvKategoriMenu = (TextView)itemView.findViewById(R.id.TvKategoriMenu);
        }
    }


}
