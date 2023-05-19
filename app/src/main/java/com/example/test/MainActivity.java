package com.example.test;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    // 선언 부분
    private DatabaseReference mDatabaseRef;  // firebase DB 객체 선언
    private EditText EditText;
    private TextView TextView;
    private Button Button;
    private ListView Listview;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDatabaseRef = FirebaseDatabase.getInstance().getReference();  // firebase 객체 생성
        EditText = findViewById(R.id.edit_text);
        TextView = findViewById(R.id.text_view);
        Button = findViewById(R.id.button);
        Listview = findViewById(R.id.list_view);  // 리스트 뷰로 데이터를 긁어 와야 함. ( 예정 )




        Button.setOnClickListener(new View.OnClickListener() {  // Button 클릭 시 실행문
            @Override
            public void onClick(View v) {
                String value = EditText.getText().toString();
                DatabaseReference newDataRef = mDatabaseRef.child("newData"); //  child(db에 저장되는 key값)
                newDataRef.setValue(value); // value 값

            }
        });

        mDatabaseRef.addValueEventListener(new ValueEventListener() { // 데이터 값 관련 메소드
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) { // 데이터가 변할 때마다 실행 됨

                if (dataSnapshot.hasChild("newData")) { // db에 'newData란 key가 있다면.
                    String value = dataSnapshot.child("newData").getValue(String.class);
                    TextView.setText(value);

                }
            };

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // 에러 처리를 수행하는 코드 ( 미정 )
            }
        });



    }
}
