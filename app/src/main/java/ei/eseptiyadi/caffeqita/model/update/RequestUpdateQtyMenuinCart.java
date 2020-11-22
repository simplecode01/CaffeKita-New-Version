package ei.eseptiyadi.caffeqita.model.update;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class RequestUpdateQtyMenuinCart{

    @SerializedName("code")
    private int code;

    @SerializedName("qty_menu_terupdate")
    private String qtyMenuTerupdate;

    @SerializedName("message")
    private String message;

    @SerializedName("data_updated")
    private List<DataUpdatedItem> dataUpdated;

    @SerializedName("status")
    private boolean status;

    public int getCode(){
        return code;
    }

    public String getQtyMenuTerupdate(){
        return qtyMenuTerupdate;
    }

    public String getMessage(){
        return message;
    }

    public List<DataUpdatedItem> getDataUpdated(){
        return dataUpdated;
    }

    public boolean isStatus(){
        return status;
    }
}