import android.content.BroadcastReceiver
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*

class ActionReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {

        val action = intent?.action
        val state = if (intent?.extras?.getInt("plugged", -1) == 0) "Unplugged" else "Plugged"
        val time = SimpleDateFormat("dd-MM-yyyy HH:mm:ss", Locale.getDefault()).format(Date())


        context?.let {
            val dbHelper = DatabaseHelper(it)
            val db = dbHelper.writableDatabase

            val values = ContentValues().apply {
                put(DatabaseHelper.COLUMN_ACTION, action)
                put(DatabaseHelper.COLUMN_STATE, state)
                put(DatabaseHelper.COLUMN_TIME, time)
            }

            db.insert(DatabaseHelper.TABLE_NAME, null, values)

            Toast.makeText(
                it,
                "Action: $action, State: $state, Time: $time",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}
