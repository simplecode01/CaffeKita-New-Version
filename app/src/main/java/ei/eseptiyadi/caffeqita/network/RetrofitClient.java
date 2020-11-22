package ei.eseptiyadi.caffeqita.network;

import ei.eseptiyadi.caffeqita.constraint.Baselink;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    public static Retrofit setInit(){
        return new Retrofit.Builder()
                .baseUrl(Baselink.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static ApiServices getInstance(){
        return setInit().create(ApiServices.class);
    }
}
