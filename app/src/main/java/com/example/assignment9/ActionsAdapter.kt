import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.assignment9.Action
import com.example.assignment9.R

class ActionsAdapter(private val actions: List<Action>) :
    RecyclerView.Adapter<ActionsAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val actionTextView: TextView = view.findViewById(R.id.actionTextView)
        val stateTextView: TextView = view.findViewById(R.id.stateTextView)
        val timeTextView: TextView = view.findViewById(R.id.timeTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_action, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val action = actions[position]
        holder.actionTextView.text = "Action: ${action.action}"
        holder.stateTextView.text = "State: ${action.state}"
        holder.timeTextView.text = "Time: ${action.time}"
    }

    override fun getItemCount(): Int {
        return actions.size
    }
}
