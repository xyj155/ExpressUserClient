package com.example.administrator.expressuserclient.view.fragment;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.administrator.expressuserclient.R;
import com.example.administrator.expressuserclient.base.BaseFragment;
import com.example.administrator.expressuserclient.contract.UserFragmentContract;
import com.example.administrator.expressuserclient.presenter.UserFragmentPresenter;
import com.example.administrator.expressuserclient.view.activity.SettingActivity;
import com.example.administrator.expressuserclient.weight.CircleImageView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * Created by Administrator on 2018/7/29.
 */

public class UserFragment extends BaseFragment implements UserFragmentContract.View {
    private static final int IMAGE = 1;
    @InjectView(R.id.toolbar)
    Toolbar toolbar;
    @InjectView(R.id.img_user_head)
    CircleImageView imgUserHead;
    @InjectView(R.id.tv_problem_order)
    TextView tvProblemOrder;
    @InjectView(R.id.tv_address_list)
    TextView tvAddressList;
    @InjectView(R.id.tv_my_collection)
    TextView tvMyCollection;
    @InjectView(R.id.tv_service)
    TextView tvService;
    @InjectView(R.id.tv_settting)
    TextView tvSettting;
    private RequestQueue queen;
    private UserFragmentPresenter presenter = new UserFragmentPresenter(this, getActivity());

    @Override
    protected int setLayoutResourceID() {
        return R.layout.fragment_user;
    }

    @Override
    protected void setUpView(View view, Bundle bundle) {

    }

    @Override
    protected void setUpData() {
        toolbar.setSubtitle("用户");
        toolbar.setSubtitleTextColor(0xff000000);
        queen = Volley.newRequestQueue(getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.inject(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }


    public List<MultipartBody.Part> c(List<File> files) {
        List<MultipartBody.Part> parts = new ArrayList<>(files.size());
        for (File file : files) {
            // TODO: 16-4-2  这里为了简单起见，没有判断file的类型
            RequestBody requestBody = RequestBody.create(MediaType.parse("image/png"), file);
            MultipartBody.Part part = MultipartBody.Part.createFormData("file", file.getName(), requestBody);
            parts.add(part);
        }
        return parts;
    }

    //用户选择一张图片，onActivityResult()方法将会被调用，
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //获取图片路径
        if (requestCode == IMAGE && resultCode == Activity.RESULT_OK && data != null) {
            Uri selectedImage = data.getData();
            String[] filePathColumns = {MediaStore.Images.Media.DATA};
            Cursor c = getActivity().getContentResolver().query(selectedImage, filePathColumns, null, null, null);
            assert c != null;
            c.moveToFirst();
            int columnIndex = c.getColumnIndex(filePathColumns[0]);
            String imagePath = c.getString(columnIndex);
            System.out.println(imagePath);
            presenter.addData("0", imagePath);
            Glide.with(getActivity()).load(imagePath).asBitmap().into(imgUserHead);
            c.close();
        }


    }

    private static final String TAG = "UserFragment";

    @OnClick({R.id.img_user_head, R.id.tv_problem_order, R.id.tv_address_list, R.id.tv_my_collection, R.id.tv_service, R.id.tv_settting})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_user_head:
                Intent intent = new Intent(Intent.ACTION_PICK,
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, IMAGE);
                break;
            case R.id.tv_problem_order:
                break;
            case R.id.tv_address_list:
                break;
            case R.id.tv_my_collection:
                break;
            case R.id.tv_service:
                break;
            case R.id.tv_settting:
                startActivity(new Intent(getContext(), SettingActivity.class));
                break;
        }
    }
}
