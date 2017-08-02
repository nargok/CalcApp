package jp.techacademy.ryoichi.gokan.calcapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    EditText eEditText1;
    EditText eEditText2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        eEditText1 = (EditText) findViewById(R.id.editText1);
        eEditText2 = (EditText) findViewById(R.id.editText2);

        Button button1 = (Button) findViewById(R.id.button1);
        Button button2 = (Button) findViewById(R.id.button2);
        Button button3 = (Button) findViewById(R.id.button3);
        Button button4 = (Button) findViewById(R.id.button4);
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);

    }

    public void onClick(View v) {
        Log.d("CalcApp", "ボタンをタップしました");
        Double result = 0.0;

        // EditTextに入力したテキストを数値に変換
        // Issue: 未入力で遷移しようとすると落ちる
        // ＞解決：必須入力にする
        // どうもこれだとだめっぽい
        // 違うListenerが必要なのでしょうか？
        // エラー判定機能はそのままにして、次に進む　gihyoのサンプルが役に立ちそう
        if ( eEditText1.toString().isEmpty()) {
            // エラーを出力する
            eEditText1.setError("値を入力してください");
            Log.d("CalcApp", "あああああ");

        } else if ( eEditText2.toString().isEmpty()){
            eEditText2.setError("値を入力してください");
        } else {
            eEditText1.setError(null);
            eEditText2.setError(null);

            Double number1 = Double.parseDouble(eEditText1.getText().toString());
            Double number2 = Double.parseDouble(eEditText2.getText().toString());

            if (v.getId() == R.id.button1) {
                result = number1 + number2;
                Log.d("CalcApp", "Result is" + String.valueOf(result));
            } else if (v.getId() == R.id.button2) {
                result = number1 - number2;
                Log.d("CalcApp", "Result is" + String.valueOf(result));
            } else if (v.getId() == R.id.button3) {
                result = number1 * number2;
                Log.d("CalcApp", "Result is" + String.valueOf(result));
            } else if (v.getId() == R.id.button4) {
                result = number1 / number2;
                Log.d("CalcApp", "Result is" + String.valueOf(result));
            }

            // 画面遷移
            Log.d("CalcApp", "ここで画面遷移");
            Intent intent = new Intent(this, SecondActivity.class);
            intent.putExtra("VALUE", result);
            startActivity(intent);
        }
    }
}
