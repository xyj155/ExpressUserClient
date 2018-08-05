package com.example.administrator.expressuserclient.contract.user;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;

/**
 * Created by Administrator on 2018/7/31.
 */

public interface UserFragmentContract {
    interface Model {
        Call<ResponseBody> uploadAvatar(RequestBody id, MultipartBody.Part file);
//        @Override
//        public Observable<BaseData<EmptyData>> addData(RequestBody content, RequestBody contact, List<MultipartBody.Part> files) {
//            return RetrofitUtils
//                    .getInstance()
//                    .getServerices()
//                    .addFeedback(content,contact,files);

    }

    interface View {
    }

    interface Presenter {
        void addData(String id, String files);
    }
}
