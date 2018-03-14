package com.example.cesartorres.testmvpbus.presentation.mvp.utils;

import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by cesar.torres on 3/13/2018.
 */

public class TCPClient {
    public static final String TAG = "TCPClient";
    public static final String SERVER_IP = "192.168.7.40";
    public static final int SERVER_PORT = 10001;

    private StringBuilder serverMessage;
    private OnMessageReceived onMessageReceived;
    private boolean isRunning = false;
    private PrintWriter printWriter;
    private BufferedReader bufferedReader;
    private Runnable messageRunnable;
    private Thread messageThread;


    public TCPClient(OnMessageReceived onMessageReceived) {
        this.onMessageReceived = onMessageReceived;
    }

    public interface OnMessageReceived{
        void messageReceived(String message);
    }

    public void sendMessage(final String message) {
        messageRunnable = new Runnable() {
            @Override
            public void run() {
                if (printWriter != null) {
                    Log.d(TAG, "Sending: " + message);
                    printWriter.println(message + "\r\n");
                    printWriter.flush();
                }
            }
        };
        Observable.just(messageRunnable)
                .observeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Runnable>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Runnable runnable) {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
        messageThread = new Thread(messageRunnable);
        messageThread.start();
    }

    public void stopClient(){
        isRunning = false;

        if(printWriter != null){
            printWriter.flush();
            printWriter.close();
        }

        onMessageReceived = null;
        printWriter = null;
        bufferedReader = null;
        serverMessage = null;
    }

    public void run(){
        isRunning = true;

        try{
            InetAddress serverAddress = InetAddress.getByName(SERVER_IP);
            Log.e("TCP Client","C: Connecting...");
            Socket socket = new Socket(serverAddress,SERVER_PORT);
            serverMessage = new StringBuilder();
            try{
                printWriter = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())));
                bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                while (isRunning){
                    serverMessage.append(bufferedReader.readLine());
                    if(serverMessage != null && onMessageReceived != null){
                        onMessageReceived.messageReceived(serverMessage.toString());
                        messageThread.join();
                    }
                    Log.v(TAG,"Response from Server S: Received Message - " + serverMessage);
                }
            }catch (Exception e){
                Log.e(TAG,"TCP S: Error" + e);
            }finally {
                socket.close();
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
