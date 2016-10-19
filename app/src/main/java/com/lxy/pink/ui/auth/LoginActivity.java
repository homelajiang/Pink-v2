package com.lxy.pink.ui.auth;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.lxy.pink.R;
import com.lxy.pink.RxBus;
import com.lxy.pink.data.model.auth.Auth;
import com.lxy.pink.event.AuthCreateEvent;
import com.lxy.pink.ui.base.BaseActivity;
import com.lxy.pink.utils.Config;
import com.lxy.pink.utils.TT;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by homelajiang on 2016/10/19 0019.
 */

public class LoginActivity extends BaseActivity implements SignContract.View {
    @BindView(R.id.password)
    EditText mPassword;
    @BindView(R.id.username)
    EditText mUsername;
    @BindView(R.id.signIn)
    Button mSignIn;
    @BindView(R.id.signUp)
    Button mSignUp;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    private Unbinder unbinder;

    static {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }

    private SignContract.Presenter presenter;
    private String tempName;
    private String tempPass;
    private ProgressDialog pd;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        unbinder = ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        setSupportActionBar(mToolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(R.string.pink_login_welcome);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        pd = new ProgressDialog(this);
        new SignPresenter(this).subscribe();
    }


    @OnClick(R.id.signIn)
    public void signIn() {
        if (!checkInput())
            return;
        pd.setMessage(getString(R.string.pink_sign_in_ing));
        presenter.signIn(tempName, tempPass);
    }

    @OnClick(R.id.signUp)
    public void signUp() {
        if (!checkInput())
            return;
        pd.setMessage(getString(R.string.pink_sign_up_ing));
        presenter.signUp(tempName, tempPass);
    }

    @Override
    public void showLoading() {
        pd.show();
    }

    @Override
    public void hideLoading() {
        pd.hide();
    }


    @Override
    public void onSignUp(Auth auth) {
        if (auth.getCode() != Config.HOST_SUCCESS_CODE) {
            TT.s(getContext(), TextUtils.isEmpty(auth.getMsg())
                    ? getString(R.string.pink_error_indescribable) : auth.getMsg());
            return;
        }
        RxBus.getInstance().post(new AuthCreateEvent(auth));
    }


    @Override
    public void onSignIn(Auth auth) {
        if (auth.getCode() != Config.HOST_SUCCESS_CODE) {
            TT.s(getContext(), TextUtils.isEmpty(auth.getMsg())
                    ? getString(R.string.pink_error_indescribable) : auth.getMsg());
            return;
        }
        RxBus.getInstance().post(new AuthCreateEvent(auth));
    }



    @Override
    public void handleError(Throwable error) {

    }

    @Override
    public void setPresenter(SignContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private boolean checkInput() {
        tempName = mUsername.getText().toString().trim();
        tempPass = mPassword.getText().toString().trim();

        if (TextUtils.isEmpty(tempName)) {
            Toast.makeText(getContext(), R.string.pink_sign_username_null, Toast.LENGTH_SHORT).show();
            return false;
        }
        if (TextUtils.isEmpty(tempPass)) {

            Toast.makeText(getContext(), R.string.pink_sign_password_null, Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        pd.dismiss();
        unbinder.unbind();
        presenter.unSubscribe();
    }
}
