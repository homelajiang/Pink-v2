package com.lxy.pink.ui.video;


import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.lxy.pink.R;
import com.lxy.pink.ui.base.BaseDialogFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by homelajiang on 2017/2/17 0017.
 */

public class ACLoginDialogFragment extends BaseDialogFragment {
    @BindView(R.id.et_username)
    EditText mEtUsername;
    @BindView(R.id.et_password)
    EditText mEtPassword;
    @BindView(R.id.bt_go)
    Button mBtGo;
    private AcLoginListener listener;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View view = View.inflate(getContext(), R.layout.ac_login_item, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity())
                .setView(view);
        ButterKnife.bind(this, view);
        return builder.create();
    }

    @OnClick(R.id.bt_go)
    public void login() {
        String username = mEtUsername.getText().toString().trim();
        String password = mEtPassword.getText().toString().trim();

        if(TextUtils.isEmpty(username)){
            Toast.makeText(getContext(),getString(R.string.ac_username_not_null),Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(password)){
            Toast.makeText(getContext(),getString(R.string.ac_password_not_null),Toast.LENGTH_SHORT).show();
            return;
        }

        if (listener != null)
            listener.login(username, password);
    }

    public ACLoginDialogFragment setLoginListener(AcLoginListener listener) {
        this.listener = listener;
        return this;
    }

    public interface AcLoginListener {
        void login(String username, String password);
    }
}
