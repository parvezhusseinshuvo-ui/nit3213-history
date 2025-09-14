package au.vu.nit3213.history.ui.dashboard

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import au.vu.nit3213.history.data.remote.HistoryEntity
import au.vu.nit3213.history.databinding.RowHistoryBinding

class HistoryAdapter(
    private val items: MutableList<HistoryEntity>,
    private val onClick: (HistoryEntity) -> Unit
) : RecyclerView.Adapter<HistoryAdapter.VH>() {

    inner class VH(val b: RowHistoryBinding) : RecyclerView.ViewHolder(b.root) {
        init { b.root.setOnClickListener { onClick(items[bindingAdapterPosition]) } }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val b = RowHistoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return VH(b)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: VH, position: Int) {
        val e = items[position]
        holder.b.tvTitle.text = e.event
        holder.b.tvSub.text   = "${e.startYear}–${e.endYear} • ${e.location} • Key figure: ${e.keyFigure}"
    }

    fun submit(data: List<HistoryEntity>) {
        items.clear(); items.addAll(data); notifyDataSetChanged()
    }
}
