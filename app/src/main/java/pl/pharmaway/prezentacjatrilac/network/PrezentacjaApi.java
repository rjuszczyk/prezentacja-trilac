package pl.pharmaway.prezentacjatrilac.network;

import pl.pharmaway.prezentacjatrilac.Constants;
import retrofit.Call;
import retrofit.http.GET;

public interface PrezentacjaApi {
    @GET("getData.php?lekarzType="+ Constants.LEKARZ_TYPE)
    Call<GetDataResponse> getData();

    @GET("version.php")
    Call<DataVersion> getDataVersion();
}
