package tw.edu.pu.csim.tcyang.countdown

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    lateinit var mainHandler: Handler
    var secondsLeft:Int = 100  //倒數100秒

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainHandler = Handler(Looper.getMainLooper())
        mainHandler.post(updateTextTask)
    }

    private val updateTextTask = object : Runnable {
        override fun run() {
            if (secondsLeft > 0) {
                secondsLeft--
                var txv: TextView = findViewById(R.id.txv)
                txv.text = secondsLeft.toString()
            }
            mainHandler.postDelayed(this, 1000) //間隔1秒
        }
    }

    override fun onPause() {
        super.onPause()
        mainHandler.removeCallbacks(updateTextTask)
    }

    override fun onResume() {
        super.onResume()
        mainHandler.post(updateTextTask)
    }
}