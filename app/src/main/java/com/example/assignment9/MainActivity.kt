import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.assignment9.Action
import com.example.assignment9.R
import java.util.ArrayList


class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        val dbHelper = DatabaseHelper(this)
        val db = dbHelper.readableDatabase

        val cursor = db.rawQuery(
            "SELECT * FROM ${DatabaseHelper.TABLE_NAME} ORDER BY ${DatabaseHelper.COLUMN_ID} DESC",
            null
        )

        val intentFilter = IntentFilter()

        val actions = ArrayList<Action>()
        if (cursor.moveToFirst()) {
            do {

                val action = intentFilter.addAction(Intent.ACTION_TIME_TICK)
                val state = intentFilter.addAction(Intent.ACTION_TIMEZONE_CHANGED)
                val time = intentFilter.addAction(Intent.ACTION_TIME_CHANGED)
                actions.add(Action(action.toString(), state.toString(), time.toString()))
            } while (cursor.moveToNext())
        }

        val myBroadcastReceiver = ActionReceiver()

        registerReceiver(myBroadcastReceiver, intentFilter)


        cursor.close()
        db.close()

    }
}
