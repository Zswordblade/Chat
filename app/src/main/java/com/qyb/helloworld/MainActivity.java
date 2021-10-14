package com.qyb.helloworld;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private static final String API_URL = "http://121.5.153.108:8888/";
    //private static final String API_URL = "http://192.168.56.1:8888/";
    private static final URI uri =  URI.create("ws://121.5.153.108:8088/wsSERVER");
    //private static final URI uri = URI.create("ws://192.168.56.1:8088/wsSERVER");//netty地址
//    private IWebSocketClient iWebSocketClient = new IWebSocketClient(uri);
    private EditText phoneNumber;
    private EditText username;
    private EditText password;

    private Button login;
    private Button register;
    private TextView textView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        phoneNumber = (EditText) findViewById(R.id.phoneNumber);
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        login = (Button) findViewById(R.id.login);
        register = (Button) findViewById(R.id.register);
        textView2 = (TextView) findViewById(R.id.textView2);

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .retryOnConnectionFailure(false)
                .connectTimeout(100000, TimeUnit.MICROSECONDS)
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
        CallService callService = retrofit.create(CallService.class);
        List<String> permissionList = new ArrayList<String>();
        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED)
            permissionList.add(Manifest.permission.READ_PHONE_STATE);
        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACTIVITY_RECOGNITION) != PackageManager.PERMISSION_GRANTED)
            permissionList.add(Manifest.permission.ACTIVITY_RECOGNITION);
        if (!permissionList.isEmpty()) {
            String[] permisssions = permissionList.toArray(new String[permissionList.size()]);
            ActivityCompat.requestPermissions(MainActivity.this, permisssions, 1);
        }

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Users users =new Users();
                users.setPhonenumber(phoneNumber.getText().toString());
                users.setPassword(password.getText().toString());
                users.setUsername(username.getText().toString());
                //int w =Integer.parseInt(textView.getText().toString());
                callService.Register(users).enqueue(new Callback<UsersResponse>() {
                    @Override
                    public void onResponse(Call<UsersResponse> call, Response<UsersResponse> response) {
                        if(response.code()==200&&response.body().getMsg().equals("OK")) {//注册成功，传回user，跳转activity
                            System.out.println(response.body().getStatus());
                            System.out.println(response.body().getData().getPhonenumber());
                        }
                        else{
                            System.out.println(response.body().getMsg());
                        }
                    }
                    @Override
                    public void onFailure(Call<UsersResponse> call, Throwable t) {
                        t.printStackTrace();
                    }
                });
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginData loginData =new LoginData();
                loginData.setIp("192.168.0.1");
                loginData.setPhonenumber(phoneNumber.getText().toString());
                loginData.setBrand(android.os.Build.BRAND);
                loginData.setDevice(android.os.Build.DEVICE);
                loginData.setManufacturer(android.os.Build.MANUFACTURER);
                loginData.setModel(android.os.Build.MODEL);
                loginData.setProduct(android.os.Build.PRODUCT);
                Log.e("onClick", "onClick: "+android.os.Build.MODEL +"厂商："+android.os.Build.MANUFACTURER+"产品"+android.os.Build.PRODUCT+"品牌："+android.os.Build.BRAND+"设备名："+android.os.Build.DEVICE);
                loginData.setPassword(password.getText().toString());
                callService.Login(loginData).enqueue(new Callback<UsersResponse>() {
                    @Override
                    public void onResponse(Call<UsersResponse> call, Response<UsersResponse> response) {
                        if(response.code()==200&&response.body().getMsg().equals("OK")) {//成功，跳转activity
                            System.out.println(response.body().getData().toString());
                            System.out.println(response.body().getMsg());
                            Intent intent=new Intent(MainActivity.this,chatActivity.class);
                            startActivity(intent);
                        }
                        else {//错误
                            System.out.println(response.body().getMsg());
                        }
                    }

                    @Override
                    public void onFailure(Call<UsersResponse> call, Throwable t) {
                        t.printStackTrace();
                    }
                });
            }
        });

        //用于创建channel，不可省略！！！
        //使用websocket需新建线程
        //step1
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    iWebSocketClient.connectBlocking();//获取连接服务端的channel
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
                }
                /*
                enum参考：
                CONNECT(100, "第一次(或重连)初始化连接"),
                JOIN(101, "加入群组"),
                CHAT(102, "聊天消息"),
                LEAVE(103,"离开群组"),
                KEEPALIVE(104, "客户端保持心跳"),//未完成
                LOCATION(105,"上传位置");

                 */
              /*
              ！！！！！！！！！！！！！！！
                新建ChatMsg对象，放入
                ChatMsg chatMsg = new ChatMsg();
                chatMsg.setMsg("forever");
                chatMsg.setSenderId("123");
                chatMsg.setReceiverId("test");
                chatMsg.setUsername("222");
                DataContent data = new DataContent();
                data.setAction(100);
                data.setChatMsg(chatMsg);
                data.setExtend("1");
                JSONObject jsonObject=(JSONObject) JSONObject.toJSON(data);
                iWebSocketClient.send(jsonObject.toString());


               */
                //创建ChatMsg对象，放入消息内容（setMsg)，放入发送者电话（setSenderId),
                // 放入接收消息的聊天组（setReceiverId），放入发送者用户名（setUsername）
//                ChatMsg chatMsg = new ChatMsg();
//                chatMsg.setMsg("forever");//消息，第一次连接时随便写
//                chatMsg.setSenderId("1234");//用户电话号码
//                chatMsg.setReceiverId("test");//聊天组的Id
//                chatMsg.setUsername("222");//用户id,暂时不重要，随便写
//                //创建DataContent对象，是发送给服务端必须的参数
//                DataContent data = new DataContent();//data是要上传的对象，
//                data.setAction(100);//action:100指第一次连接，上方注释的enum有介绍
//                data.setChatMsg(chatMsg);//将chatMsg放入。。。
//                data.setExtend("1");//忘记以前怎么写了，别改这句
//                JSONObject jsonObject = (JSONObject) JSONObject.toJSON(data);//data转JSON
//                iWebSocketClient.send(jsonObject.toString());//发送json，接收的消息见IWebSocketClient类（在最下面）
//                //Message message = new Message();
//                //message.obj=iWebSocketClient;
//                //handler.sendMessage(message);
//            }
//        }).start();

        //step2
        //加入某个讨论组，这里的action为101，即加入用户所选择的聊天组（无聊天组则自动创建），服务端会负责传回消息。
//        login.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                System.out.println("ddd----------------");
//                new Thread(new Runnable() {
//                    @Override
//                    public void run() {
//                        //创建ChatMsg对象，放入消息内容（setMsg)，放入发送者电话（setSenderId),
//                        // 放入接收消息的聊天组（setReceiverId），放入发送者用户名（setUsername）
//                        ChatMsg chatMsg = new ChatMsg();
//                        chatMsg.setMsg("forever");//第一步是加入组，消息不重要，随便输
//                        chatMsg.setSenderId("1234");//用户电话号码
//                        chatMsg.setReceiverId("1111");//要加入的聊天组Id
//                        chatMsg.setUsername("222");//用户名，随便输
//                        //创建DataContent对象，是发送给服务端必须的参数
//                        DataContent data = new DataContent();
//                        data.setAction(101);//action：101，特指加入某个讨论组
//                        data.setChatMsg(chatMsg);
//                        data.setExtend("1");//别改!!!!
//                        System.out.println("send---------------");
//                        JSONObject jsonObject = (JSONObject) JSONObject.toJSON(data);//data转JSON
//                        iWebSocketClient.send(jsonObject.toString());//发送
//                    }
//                }).start();
//            }
//        });
//        //step3发送消息（在完成step1，2后，可反复执行）
//        register.setOnClickListener(new View.OnClickListener() {//action：102，发送消息
//            @Override
//            public void onClick(View v) {
//                new Thread(new Runnable() {
//                    @Override
//                    public void run() {
//                        /*
//                        LocMsg locMsg = new LocMsg();
//                        locMsg.setGroupid("1111");
//                        locMsg.setLatitude(12.11);
//                        locMsg.setLongitude(33.111);
//                        locMsg.setUserid("123");
//                        LocContent locContent = new LocContent();
//                        locContent.setAction(105);
//                        locContent.setLocMsg(locMsg);
//                        JSONObject jsonObject = (JSONObject) JSONObject.toJSON(locContent);
//                        iWebSocketClient.send(jsonObject.toString());
//
//                         */
//                        ChatMsg chatMsg = new ChatMsg();
//                        chatMsg.setMsg("foreverand");//要发送的消息
//                        chatMsg.setSenderId("1234");//发送者的电话号码
//                        chatMsg.setReceiverId("1111");//聊天的聊天组Id
//                        chatMsg.setUsername("222");//用户名，随便输
//                        DataContent data = new DataContent();
//                        data.setAction(102);//102，特指发送消息
//                        data.setChatMsg(chatMsg);
//                        data.setExtend("1");//别改
//                        System.out.println("send---------------");
//                        JSONObject jsonObject=(JSONObject) JSONObject.toJSON(data);
//                        iWebSocketClient.send(jsonObject.toString());
//                    }
//                }).start();
//            }
//        });
//
//
//    }

    public static class IWebSocketClient extends WebSocketClient {//继承WebSocketClient,
        public IWebSocketClient(URI serverUri) {
            super(serverUri);
        }

        @Override
        public void onOpen(ServerHandshake handshakedata) {
            Log.e("IWebSocketClient", "onOpen()");
        }
/*
下面的onMessage接收消息
 */
        @Override
        public void onMessage(String message) {//
            Log.e("IWebSocketClient", "onMessage()" + message);//收到的消息出现在这里！！！！！！！
        }

        @Override
        public void onClose(int code, String reason, boolean remote) {
            Log.e("IWebSocketClient", "onClose()");
        }

        @Override
        public void onError(Exception ex) {
            Log.e("IWebSocketClient", "onError()" + ex);
        }
    }
}
