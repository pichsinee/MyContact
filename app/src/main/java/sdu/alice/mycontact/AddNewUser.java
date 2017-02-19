package sdu.alice.mycontact;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

/**
 * Created by aom on 19/2/2560.
 */

// ขั้น 20 สร้าง class ในการ insert ข้อมูลลง MySQL
public class AddNewUser extends AsyncTask<Void, Void, String> {   //เพิ่ม extends AsyncTask<...> จากนั้น กด Alt+Enter เลือก Implement Method เลือก doInBackground

    private Context context;    //สร้างท่อ คือตัวแปร context เพื่อเชื่อมต่อ DB
    private static final String urlPHP = "http://swiftcodingthai.com/19feb/addUserAom.php"; //ประกาศตัวแปรค่าคงที่ แสดงค่า url ที่เก็บไฟล์ PHP ในการ Insert ข้อมูลลง table
    private String nameString, userString, passwordString, imageString;

    //กด Alt+Enter เลือก Contructor เลือก field ทั้งหมด กด OK
    public AddNewUser(Context context, String nameString, String userString, String passwordString, String imageString) {
        this.context = context;
        this.nameString = nameString;
        this.userString = userString;
        this.passwordString = passwordString;
        this.imageString = imageString;
    }

    @Override
    protected String doInBackground(Void... voids) {

        try {

        //ทำการ Import Library ของ Google เลือก Library okhttp ก่อน จึงจะเห็น class OkHttpClient(com.squreup)
            OkHttpClient okHttpClient = new OkHttpClient();
            RequestBody requestBody = new FormEncodingBuilder() //รวมข้อมูลที่จะโยนขึ้น DB เป็นก้อนก่อน เก็บในตัวแปร requestBody โดยพิมพ์ถึง Fo แล้วกด Ctrl+Space
                    .add("isAdd", "true")   //สัมพันธ์กับ Line 7 ใน addUserAom.php คือ เมื่อกดปุ่ม Add ให้ set ค่า isAdd เป็น true แล้วเรียกคำสั่ง insert จากไฟล์ addUserAom.php
                    .add("name", nameString)
                    .add("user", userString)
                    .add("password", passwordString)
                    .add("image", imageString)
                    .build();
            Request.Builder builder = new Request.Builder();    //สร้าง Request เพื่อขอการ insert
            Request request = builder.url(urlPHP).post(requestBody).build();    //ส่ง request โดยการ post
            Response response = okHttpClient.newCall(request).execute();
            return response.body().string();


        } catch (Exception e) {
            Log.d("19febV1", "e doin ==>" + e.toString());  //chk erroe การทำงาน โดยแสดงบน Logcat กดดู Alt+6
            return null;
        }

    }
}   //Main Class
