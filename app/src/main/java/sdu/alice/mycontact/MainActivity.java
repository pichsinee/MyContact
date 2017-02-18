package sdu.alice.mycontact;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    //Explicit  ประกาศตัวแปร    ขั้น 1
    public Button singInButton, singUpButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initial View ผูก button บน layout file กับ ตัวแปร ใน java    ขั้น 2
        singInButton = (Button) findViewById(R.id.button);
        singUpButton = (Button) findViewById(R.id.button2);

        //Button Controller ทำให้ปุ่มคลิกได้ (Get evevt จากการคลิก)   ขั้น 3
        singUpButton.setOnClickListener(new View.OnClickListener() {    //ในวงเล็บ พิมพ์ new on เลือก OnClickListener โปรแกรมจะ Generate method ให้
            @Override
            public void onClick(View view) {
                //Intent to SignUp ส่งข้อมูลไปหน้า SignUp.java
                startActivity(new Intent(MainActivity.this, SignUp.class)); //ต้องสร้างหน้า SignUp.java ก่อน
            }
        });

    }   //Main Method



}   //Main Class
