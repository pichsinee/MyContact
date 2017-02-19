package sdu.alice.mycontact;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class SignUp extends AppCompatActivity {

    //Explicit ประกาศตัวแปรที่ใช้รองรับอิลิเมนต์บน layout file ของ activity_sign_up.xml ทั้งหมด ขั้น 4
    private EditText nameEditText, userEditText, passEditText;
    private ImageView imageView;
    private Button button;

    private String nameString, userString, passString,   //ขั้น 9 ประกาศตัวแปรเพิ่มสำหรับเช็คการรับค่า onClick
            pathImageString, nameImageString;           //ขั้น 13 ประกาศตัวแปรเพิ่มเพื่อเก็บ path ของรูปและชื่อรูปที่อยู่ในเครื่องมือถือ

    private Uri uri;
    private boolean aBoolean = true;    //ขั้น 15 ประกาศตัวแปรเพ่มสำหรับเช็คว่าเลือกรูปภาพในหน้า SignIn หรือยัง


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        //Bind Widget (Initial View) โดยการสร้าง Method bindWidget
        bindWidget();   //พิมพ์ชื่อ method แล้ว กด Alt+Enter เพื่อสร้าง method bindWidget()  ขั้น 5

        //Button Controller
        buttonController();     // สร้าง Method เพื่อรับค่าจาก edittext      ขั้น 7

        //Image Controller      ขั้น 11
        imageController();

    }   //Main Method

    //กด Alt+Enter เลือก onActivityResult เพื่อ Override Method onActivityResult
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) { //เป็น method ที่ทำงานหลังจาก method อื่นทำงานเสร็จแล้ว
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {

            aBoolean = false;   //ขั้น 17 ถ้าเลือกรูปแล้ว เปลี่ยนค่า aBoolean เป็น false

            //if True Success choose image
            Log.d("18FebV1", "Result OK");

            //Choose image Show แสดงภาพที่เลือก
            uri = data.getData();   //เอาข้อมูลที่เลือกมาแยกออก เอาเฉพาะรูปภาพ
            try {       //ในการเขียนโปรแกรม ถ้าการทำงานใดที่อาจจะ error เกิดขึ้น ให้ใช้คำสั่ง Try {} catch {} โดยเมื่อเกิด error แอปจะไม่ catch เป็น code error แต่จะหยุดการทำงาน

                //ดึง uri จาก Gallery มาแสดงบนหน้าแอป   ขั้น 12
                Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(uri));  //จากข้อมูลที่ได้รับมาจาก method imageController() ซึ่งอยู่ในตัวแปร uri มาทำการกรองเอาเฉพาะรูปภาพ
                imageView.setImageBitmap(bitmap);   //เอารูปภาพที่กรองได้จากบรรทัดบนมาเก็บที่ตัวแปร bitmap

            } catch (Exception e) {
                e.printStackTrace();    //เมือมี error ให้แสดงข้อความเตือน
            }

        }   //if

        //Find Image Path    ขั้น 14 เมื่อได้รูปแภาพมาแล้ว ทำการ get path และชื่อภาพขึ้นมา
        String[] strings = new String[]{MediaStore.Images.Media.DATA};  //Media มี 3 ประเภท Audio, Image, Video ในที่นี้เลือก Image โดยเป็นการกรองข้อมูลเอาเฉพาะรูปภาพ
        Cursor cursor = getContentResolver().query(uri, strings, null, null, null);   //เป็นการจองหน่วยความจำ ชื่อ cursor เพื่อเก็บรูปทั้งหมดบน external storage ที่เปิด โดย query() จะเป็นการกรองรูปภาพที่เราต้องการ
        //query(uri, strings, null, null, null) คือ ข้อมูลทั้งก้อนที่เก็บใน uri เลือกเฉพาะรูปภาพ ที่เก็บในตัวแปร string ส่วน arg. ที่เหลือไม่ใช้ ให้เป็น null

        if (cursor != null) {   //กรณีใน external storage มีมากกว่า 1 รูป

            cursor.moveToFirst();   //เลื่อนตำแหน่งไปที่ตำแหน่งภาพแรกสุด
            int index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA); //เมื่อทำการแตะเลือกรูปภาพ
            pathImageString = cursor.getString(index);  //เก็บค่า path ตรงตำแหน่งรูปที่เลือกลงตัวแปร pathImageString

        } else {    //กรณีใน external storage มีแค่รูปเดียว
            pathImageString = uri.getPath();    //ให้เอา path รูปภาพมาเก็บในตัวแปร pathImageString เลย

        }

        Log.d("19FebV1", "pathImage ==>" + pathImageString);    //กด Alt+6 เพื่อเรียก Logcat เพื่อดูการทำงานของโปรแกรม เรียกดู path ของรูปภาพที่เลือก เช่น pathImage ==>/storage/3734-6331/รูปภาพเกาะรัตนโกสินทร์/IMG_4593.JPG

    }   //onActivityResult


    private void imageController() {    //สร้างการเลือกรูปภาพจาก gallery

        imageView.setOnClickListener(new View.OnClickListener() {   //พิมพ์ new แล้วกด Ctrl+Space เลือก OnClickListener
            @Override
            public void onClick(View view) {

                //Move to Choose Image
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);   //คำสั่ง ACTION_GET_CONTENT เป็นการย้ายตัวเองไปทำงานที่อื่น และรอส่งผลลัพธ์กลับมา ในที่นี้ไปเปิดรูปภาพ แล้วเอารูปภาพมาแสดงบนหน้าจอ
                intent.setType("image/*");  //เปิดรูปภาพทั้งหมดที่มีในเครื่อง
                startActivityForResult(Intent.createChooser(intent, "Select picture"), 1);   //เมื่อทำงานสำเร็จให้ส่งค่ากลับมาบอก ซึ่งจะโยนรูปภาพที่เลือกกลับมา ตรงเลข 1 ใส่เลขอะไรก็ได้ที่เป็นจน.เต็ม


            }   //onClick
        });

    }   //Method imageController

    private void buttonController() {   //เพื่อรับค่าจาก edittext    ขั้น 8

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Get value from Edittext   รับค่าจาก Edittext มาเก็บในตัวแปร
                nameString = nameEditText.getText().toString().trim();  //getText() คือรับค่า toString() คือเท่าความยาวข้อความ trim() คือตัดช่องว่าง
                userString = userEditText.getText().toString().trim();
                passString = passEditText.getText().toString().trim();

                //Check Space ตรวจดูว่ามีค่าว่างไหม
                if (nameString.length() == 0 || userString.length() == 0 || passString.length() == 0) { //Chk ค่าว่าง
                    //ถ้าเป็นจริง คือมี Edittext ช่องใดช่องหนึ่งว่าง
                    Log.d("18FebV1", "Have Space"); //ใช้ติดตามการทำงานของแอป โดยกด Alt+6
                    MyAlert myAlert = new MyAlert(SignUp.this);     //ขั้น 10 ต้องทำขั้น 9 ที่ MyAlert.java
                    myAlert.myDialog("Have space", "Please fill every blank.");  //ข้อความ Title และ Message พิมพ์ภาษาไทยก็ได้

                } else if (aBoolean) {  //ขั้น 15   chk ว่ารูปภาพยังไม่ได้เลือก คือ ตัวแปร aBoolean เป็น true
                    //Non choose Image
                    MyAlert myAlert = new MyAlert(SignUp.this);
                    myAlert.myDialog("ยังไม่ได้เลือกรูป", "กรุณาเลือกรูปก่อน");

                } else {


                }

            }   //onClick
        });

    }   //Method buttonController

    private void bindWidget() {     //ผูกอิลิเมนต์กับตัวแปรยน java  ขั้น 6

        nameEditText = (EditText) findViewById(R.id.editText);
        userEditText = (EditText) findViewById(R.id.editText2);
        passEditText = (EditText) findViewById(R.id.editText3);
        imageView = (ImageView) findViewById(R.id.imageView);
        button = (Button) findViewById(R.id.button4);

    }   //Method bindWidget




}   //Main Class
