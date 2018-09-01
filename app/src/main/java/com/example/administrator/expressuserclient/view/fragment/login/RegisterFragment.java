package com.example.administrator.expressuserclient.view.fragment.login;

import android.app.Dialog;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.expressuserclient.R;
import com.example.administrator.expressuserclient.base.BaseGson;
import com.example.administrator.expressuserclient.commonUtil.ToastUtil;
import com.example.administrator.expressuserclient.contract.login.RegisterFragmentContract;
import com.example.administrator.expressuserclient.gson.UserGson;
import com.example.administrator.expressuserclient.presenter.login.RegisterFragmentPresenter;
import com.example.administrator.expressuserclient.weight.AppleDialog;
import com.example.administrator.expressuserclient.weight.CheckCodeCountDown;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;

/**
 * @author Administrator
 * @date 2018/8/28/028
 */

public class RegisterFragment extends Fragment implements RegisterFragmentContract.View {


    @InjectView(R.id.tv_title)
    TextView tvTitle;
    @InjectView(R.id.et_username)
    EditText etUsername;
    @InjectView(R.id.et_password)
    EditText etPassword;
    @InjectView(R.id.et_smscode)
    EditText etSmscode;
    @InjectView(R.id.btn_login)
    Button btnLogin;

    CheckCodeCountDown mCheckCodeCountDown;
    @InjectView(R.id.tv_already)
    TextView tvAlready;
    private RegisterFragmentPresenter presenter = new RegisterFragmentPresenter(this);
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int event = msg.arg1;
            int result = msg.arg2;
            final Object data = msg.obj;
            Log.e("event", "event=" + event);
            if (result == SMSSDK.RESULT_COMPLETE) {
                System.out.println("----" + event);
                if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {
                    if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {//提交验证码成功
                        presenter.register(getActivity(), etUsername.getText().toString(), etPassword.getText().toString(), etUsername.getText().toString());
                    } else if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE) {
                        ToastUtil.showToastInfor("验证码已发送！");
                    } else if (event == SMSSDK.EVENT_GET_SUPPORTED_COUNTRIES) {//返回支持发送验证码的国家列表
                        Toast.makeText(getContext(), "获取国家列表成功", Toast.LENGTH_SHORT).show();
                    } else if (event == SMSSDK.RESULT_ERROR) {
                        ToastUtil.showToastError("验证码发送失败");
                    }
                }
            }

        }
    };


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = inflater.inflate(R.layout.vp_register_item1, container, false);
        EventHandler eh = new EventHandler() {
            @Override
            public void afterEvent(int event, int result, Object data) {
                Message msg = new Message();
                msg.arg1 = event;
                msg.arg2 = result;
                msg.obj = data;
                handler.sendMessage(msg);
            }
        };

        SMSSDK.registerEventHandler(eh);
        ButterKnife.inject(this, rootView);
        Typeface typeFace = Typeface.createFromAsset(getActivity().getAssets(), "font.ttf");
        tvTitle.setTypeface(typeFace);
        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                presenter.querySameUser(etUsername.getText().toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        };
        etUsername.addTextChangedListener(textWatcher);
        mCheckCodeCountDown = rootView.findViewById(R.id.tv_send);
        mCheckCodeCountDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean phoneNumber = isPhoneNumber(etUsername.getText().toString());
                mCheckCodeCountDown.performOnClick(phoneNumber);
            }
        });
        mCheckCodeCountDown.setOnSendCheckCodeListener(new CheckCodeCountDown.OnSendCheckCodeListener() {
            @Override
            public void sendCheckCode() {
                if (isPhoneNumber(etUsername.getText().toString())) {
                    ToastUtil.showToastInfor("发送验证码...");
                    SMSSDK.getVerificationCode("86", etUsername.getText().toString());
                } else {
                    ToastUtil.showToastWarning("请输入正确手机号码！");
                }
//                TODO 向手机发送验证码的逻辑
            }
        });
        mCheckCodeCountDown.setOnFinishListener(new CheckCodeCountDown.OnFinishListener() {
            @Override
            public void OnFinish() {
                mCheckCodeCountDown.setText("重新发送");
            }
        });
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        SMSSDK.unregisterAllEventHandler();
        ButterKnife.reset(this);
    }

    @OnClick({R.id.btn_login, R.id.tv_send})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                if (!etSmscode.getText().toString().isEmpty()) {
                    SMSSDK.submitVerificationCode("86", etUsername.getText().toString(), etSmscode.getText().toString());
                } else {
                    ToastUtil.showToastInfor("请输入验证码");
                }
                break;
        }
    }

    /**
     * 验证手机号码
     *
     * @param mobiles
     * @return
     */
    private boolean isPhoneNumber(String mobiles) {
        String telRegex = "[1][3578]\\d{9}";
        if (TextUtils.isEmpty(mobiles)) return false;
        else return mobiles.matches(telRegex);
    }

    @Override
    public void register(BaseGson<UserGson> baseGson) {

    }

    @Override
    public void querySameUser(BaseGson<UserGson> baseGson) {
        System.out.println(baseGson.getData() + "data");
        if (!baseGson.isSuccess()) {
            tvAlready.setText("可注册");
            tvAlready.setTextColor(getResources().getColor(R.color.green));
        } else {
            tvAlready.setText("已被注册");
            tvAlready.setTextColor(getResources().getColor(R.color.player_red));
        }
    }

    private Dialog dialog;

    @Override
    public void showDialog(String msg) {
        dialog = AppleDialog.createLoadingDialog(getActivity(), "注册中...");
        dialog.show();
    }

    @Override
    public void hideDialog() {
        dialog.dismiss();
    }

}
