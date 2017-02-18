package sdu.alice.mycontact;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

/**
 * Created by aom on 18/2/2560.
 */

public class MyAlert {  //ขั้น 9

    //Explicit
    private Context context;    //Context คือการส่งข้อมูลไม่ว่าจะเป็น ข้อความ ภาพ ตัวเลข โดยการต่อท่อก่อน ซึ่งคือ context จากนั้นโยนข้อมูลไปทั้งก้อน

    //กด Alt+Insert เลือก Constructor
    public MyAlert(Context context) {
        this.context = context;
    }

    public void myDialog(String strTitle, String strMessage) {  //แสดงข้อความเตือนในกรณีต่างๆ
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setCancelable(false);   //การแสดงข้อความเตือน Popup ต้องกด OK ข้อความ PopUp ถึงจะหายไป
        builder.setIcon(R.drawable.doremon48);  //กำหนด icon ที่แสดงบน Popup โดยขนาด icon มักเท่ากับ 48*48
        builder.setTitle(strTitle); //แสดงข้อความ Title
        builder.setMessage(strMessage); //แสดงข้อความเตือน
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() { //กำหนดให้แสดงปุ่ม OK (ปุ่ม PositiveButton) ถ้าต้องการปุ่ม Cancle เลือก NegativeButton
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();  //เมื่อกด OK ปุ่ม Popup จึงหายไป
            }
        });
        builder.show();

    }
}   //Main Class
