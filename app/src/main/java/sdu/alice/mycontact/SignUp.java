package sdu.alice.mycontact;

import android.content.Intent;
import android.net.Uri;
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

    private String nameString, userString, passString;  // ประกาศตัวแปรเพิ่มสำหรับเช็คการรับค่า onClick ขั้น 9

    private Uri uri;


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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {

            //if True Success choose image
            Log.d("18FebV1", "Result OK");

            //Choose image Show แสดงภาพที่เลือก
            uri = data.getData();   //เอาข้อมูลที่เลือกมาแยกออก เอาเฉพาะรูปภาพ
            try {
                

            } catch (Exception e) {
                e.printStackTrace();    //เมือมี error ให้แสดงข้อความเตือน
            }

        }   //if

    }   //onActivityResult

    private void imageController() {    //สร้างการเลือกรูปภาพจาก gallery

        imageView.setOnClickListener(new View.OnClickListener() {   //พิมพ์ new แล้วกด Ctrl+Space เลือก OnClickListener
            @Override
            public void onClick(View view) {

                //Move to Choose Image
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);   //ย้ายตัวเองไปเปิดรูปภาพ
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
