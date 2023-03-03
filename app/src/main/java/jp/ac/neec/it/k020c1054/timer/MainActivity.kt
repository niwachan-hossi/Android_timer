package jp.ac.neec.it.k020c1054.timer

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //初期値10秒
        var startTime: Long = 10 * 1000

        val countTextView = findViewById<TextView>(R.id.countTextView)

        val button_start = findViewById<Button>(R.id.button_start)
        val button_reset = findViewById<Button>(R.id.button_reset)
        val bt_up_second = findViewById<Button>(R.id.bt_up_second)
        val bt_down_second = findViewById<Button>(R.id.bt_down_second)
        val bt_up_minutes = findViewById<Button>(R.id.bt_up_minutes)
        val bt_down_minutes = findViewById<Button>(R.id.bt_down_minutes)

        lateinit var mp: MediaPlayer
        mp = MediaPlayer.create(this, R.raw.ring)


        countTextView.text = second_minutes_converter(startTime)

        //スタートボタン
        button_start.setOnClickListener {
            val timer = object : CountDownTimer(startTime, 100) {

                //残り時間
                override fun onTick(p0: Long) {
                    countTextView.text = second_minutes_converter(p0)
                }

                //終了
                override fun onFinish() {
                    countTextView.text = "終了"

                    button_start.isEnabled = true;

                    bt_up_second.isEnabled = true;
                    bt_down_second.isEnabled = true;
                    bt_up_minutes.isEnabled = true;
                    bt_down_minutes.isEnabled = true;
                    mp.start()

                }
            }

            button_start.isEnabled = false;

            bt_up_second.isEnabled = false;
            bt_down_second.isEnabled = false;
            bt_up_minutes.isEnabled = false;
            bt_down_minutes.isEnabled = false;

            timer.start()


            //リセットボタン
            button_reset.setOnClickListener {
                timer.cancel()
                countTextView.text = second_minutes_converter(startTime)
                button_start.isEnabled = true;

                bt_up_second.isEnabled = true;
                bt_down_second.isEnabled = true;
                bt_up_minutes.isEnabled = true;
                bt_down_minutes.isEnabled = true;
            }
        }

        //+10秒ボタン
        bt_up_second.setOnClickListener {
            startTime += (10 * 1000)
            countTextView.text = second_minutes_converter(startTime)
        }

        //-10秒ボタン
        bt_down_second.setOnClickListener {
            startTime -= (10 * 1000)
            if (startTime < (10 * 1000)) {
                startTime = (10 * 1000)
            }
            countTextView.text = second_minutes_converter(startTime)
        }

        //+1分ボタン
        bt_up_minutes.setOnClickListener {
            startTime += (60 * 1000)
            countTextView.text = second_minutes_converter(startTime)
        }

        //-1分ボタン
        bt_down_minutes.setOnClickListener {
            startTime -= (60 * 1000)
            if (startTime < (10 * 1000)) {
                startTime = (10 * 1000)
            }
            countTextView.text = second_minutes_converter(startTime)
        }
    }

    //    秒数変換
    fun second_minutes_converter(seconds: Long): String {
        val minutes = (seconds / 1000) / 60
        val second = (seconds / 1000) % 60

        return if ((seconds / 1000) >= 60) {
            "${minutes}${getString(R.string.text_lv_minutes)}${second}${getString(R.string.text_lv_second)}"
        } else {
            "${second}${getString(R.string.text_lv_second)}"
        }
    }
}