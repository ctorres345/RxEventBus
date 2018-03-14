package com.example.cesartorres.testmvpbus.presentation.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cesartorres.testmvpbus.R;
import com.example.cesartorres.testmvpbus.presentation.mvp.utils.ServerConnection;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by cesar.torres on 3/13/2018.
 */

public class TCPClientActivity extends AppCompatActivity implements ServerConnection.ServerListener {
    @BindView(R.id.tvLog)protected TextView tvLog;
    private String SERVER_URL = "http://192.168.7.40:10001";
    private ServerConnection serverConnection;

    public static Intent newIntent(Context context){
        Intent intent = new Intent(context,TCPClientActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tcpclient);
        ButterKnife.bind(this);
        serverConnection = ServerConnection.getInstance();
        serverConnection.initializeConnection(SERVER_URL,this);
    }

    @OnClick(R.id.btnTest)
    public void onTestClick(){
        if(serverConnection != null){
            serverConnection.sendMessage("Hola Mundo");
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(serverConnection != null && serverConnection.isConnected()){
            serverConnection.disconnect();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(serverConnection != null && serverConnection.isConnected()){
            serverConnection.disconnect();
        }
    }

    @Override
    public void onNewMessage(String message) {
        if(message != null){
            tvLog.setText(message);
        }
    }

    @Override
    public void onConnectionStatusChange(ServerConnection.ConnectionStatus status) {
        String statusMessage = status == ServerConnection.ConnectionStatus.CONNECTED ? "Conectado"
                : status == ServerConnection.ConnectionStatus.DISCONNECTED ? "Desconectado"
                : "Desconocido";
        Toast.makeText(this,statusMessage,Toast.LENGTH_SHORT);
    }

}
