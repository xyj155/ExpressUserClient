package com.example.administrator.expressuserclient.view.fragment;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.administrator.expressuserclient.R;
import com.example.administrator.expressuserclient.base.BaseFragment;
import com.example.administrator.expressuserclient.contract.user.UserFragmentContract;
import com.example.administrator.expressuserclient.presenter.user.UserFragmentPresenter;
import com.example.administrator.expressuserclient.view.activity.ServiceActivity;
import com.example.administrator.expressuserclient.view.activity.SettingActivity;
import com.example.administrator.expressuserclient.view.activity.UserDetailActivity;
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

import static android.content.Context.MODE_PRIVATE;

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
    @InjectView(R.id.tv_username)
    TextView tvUsername;
    @InjectView(R.id.tv_tel)
    TextView tvTel;
    @InjectView(R.id.fl_user_detail)
    FrameLayout flUserDetail;
    private UserFragmentPresenter presenter = new UserFragmentPresenter(this, getActivity());

    @Override
    protected int setLayoutResourceID() {
        return R.layout.fragment_user;
    }

    @Override
    protected void setUpView(View view, Bundle bundle) {
        SharedPreferences sp = getActivity().getSharedPreferences("user", MODE_PRIVATE);
        String userhead = sp.getString("userhead", "");
        Glide.with(getActivity()).load(userhead).asBitmap().error(R.mipmap.w3).into(imgUserHead);
    }

    @Override
    protected void setUpData() {
        toolbar.setSubtitle("用户");
        toolbar.setSubtitleTextColor(0xff000000);
        SharedPreferences sp = getActivity().getSharedPreferences("user", MODE_PRIVATE);
        String username = sp.getString("username", "");
        String tel = sp.getString("tel", "");
        Log.i(TAG, "setUpData: " + tel);
        String headurl = sp.getString("userhead", "").isEmpty() ? "" : sp.getString("userhead", "");
        Glide.with(getActivity()).load(headurl).asBitmap().error(R.mipmap.w3).into(imgUserHead);
        if (tel.equals("")) {
            tvTel.setText("你还没有绑定手机号码哦！");
        } else {
            tvTel.setText("手机：" + tel);
        }
        tvUsername.setText("用户：" + username);
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
            SharedPreferences sp = getActivity().getSharedPreferences("user", MODE_PRIVATE);
            presenter.addUserAvar(String.valueOf(sp.getInt("id", 20)), imagePath);
            Glide.with(getActivity()).load(imagePath).asBitmap().error(R.mipmap.w3).into(imgUserHead);
            c.close();
        }


    }

    private static final String TAG = "UserFragment";

    @OnClick({R.id.fl_user_detail, R.id.img_user_head, R.id.tv_problem_order, R.id.tv_address_list, R.id.tv_my_collection, R.id.tv_service, R.id.tv_settting})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fl_user_detail:
                startActivity(new Intent(getContext(), UserDetailActivity.class));
                break;
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
                startActivity(new Intent(getContext(), ServiceActivity.class));
                break;
            case R.id.tv_settting:
                startActivity(new Intent(getContext(), SettingActivity.class));
                break;
        }
    }
}
