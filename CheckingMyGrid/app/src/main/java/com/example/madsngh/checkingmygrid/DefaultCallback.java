package com.example.madsngh.checkingmygrid;

import android.app.ProgressDialog;
import android.content.Context;
import android.widget.Toast;

import com.backendless.async.callback.BackendlessCallback;
import com.backendless.exceptions.BackendlessFault;

/**
 * Created by madsngh on 28-02-2016.
 */

public class DefaultCallback<T> extends BackendlessCallback<T> {
    Context context;
    ProgressDialog progressDialog;

    public DefaultCallback(Context context) {
        this.context = context;
        progressDialog = ProgressDialog.show(context, "", "Loading", true);
    }

    @Override
    public void handleResponse(T response) {
        progressDialog.cancel();
    }

    //This override is optional
    @Override
    public void handleFault(BackendlessFault fault) {
        progressDialog.cancel();
        Toast.makeText(context, fault.getMessage(), Toast.LENGTH_SHORT).show();
    }
    public void showLoading()
    {
        progressDialog.show();
    }
    public void hideLoading()
    {
        progressDialog.dismiss();
    }
}