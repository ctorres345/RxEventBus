package com.example.cesartorres.testmvpbus.presentation.mvp.utils;

import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;

/**
 * Created by cesar.torres on 3/14/2018.
 */

public class ServerConnection {
    private WebSocket webSocket;
    private OkHttpClient okHttpClient;
    private String serverUrl;
    private Handler messageHandler;
    private Handler statusHandler;
    private ServerListener serverListener;
    private static ServerConnection instance;

    public static ServerConnection getInstance(){
        if(instance == null){
            instance = new ServerConnection();
        }
        return instance;
    }

    public class SocketListener extends WebSocketListener{
        @Override
        public void onOpen(WebSocket webSocket, Response response) {
            Message message = statusHandler.obtainMessage(0,ConnectionStatus.CONNECTED);
            statusHandler.sendMessage(message);
        }

        @Override
        public void onMessage(WebSocket webSocket, String text) {
            Message message = messageHandler.obtainMessage(0,text);
            messageHandler.sendMessage(message);
        }

        @Override
        public void onClosed(WebSocket webSocket, int code, String reason) {
            Message message = statusHandler.obtainMessage(0,ConnectionStatus.DISCONNECTED);
            statusHandler.sendMessage(message);
        }

        @Override
        public void onFailure(WebSocket webSocket, Throwable t, @Nullable Response response) {
            Log.v("SERVER SOCKET","failure in connection",t);
            disconnect();
        }
    }

    public void initializeConnection(String url, final ServerListener serverListener){
        this.serverUrl = url;
        okHttpClient = new OkHttpClient.Builder()
                .readTimeout(3, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .build();
        Request request = new Request.Builder()
                .url(this.serverUrl)
                .build();
        webSocket = okHttpClient.newWebSocket(request,new SocketListener());
        this.serverListener = serverListener;
        messageHandler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                serverListener.onNewMessage((String) msg.obj);
                return true;
            }
        });
        statusHandler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                serverListener.onConnectionStatusChange((ConnectionStatus) msg.obj);
                return true;
            }
        });
    }

    public void sendMessage(String message){
        webSocket.send(message);
    }

    public void disconnect() {
        webSocket.cancel();
        serverListener = null;
        messageHandler.removeCallbacksAndMessages(null);
        statusHandler.removeCallbacksAndMessages(null);
    }

    public boolean isConnected(){
        if(messageHandler != null && messageHandler.obtainMessage() != null){
            if(messageHandler.obtainMessage().obj instanceof ConnectionStatus){
                return messageHandler.obtainMessage().obj == ConnectionStatus.CONNECTED;
            }
        }
        return false;
    }

    public enum ConnectionStatus{
        DISCONNECTED,
        CONNECTED
    }

    public interface ServerListener{
        void onNewMessage(String message);
        void onConnectionStatusChange(ConnectionStatus status);
    }
}
