import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.firebaseproject.R
import com.example.firebaseproject.Squad

class CustomAdapter(private val dataSet: MutableList <Squad> = mutableListOf()) :
    RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val squad: TextView
        val lider: TextView
        val funcao: TextView

        init {
            // Define click listener for the ViewHolder's View.
            squad = view.findViewById(R.id.lblSquad)
            lider = view.findViewById(R.id.lblLider)
            funcao = view.findViewById(R.id.lblFuncao)
        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.card_view_design, viewGroup, false)

        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.squad.text = "Squad: "+dataSet[position].nome
        viewHolder.lider.text = "Lider: "+dataSet[position].lider
        viewHolder.funcao.text ="Função: "+dataSet[position].funcao
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size

}