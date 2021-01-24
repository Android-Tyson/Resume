package com.historymakers.resume.ui.workhistory

import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.historymakers.resume.R
import com.historymakers.resume.model.WorkHistory
import org.w3c.dom.Text

class WorkHistoryListAdapter(
    private val list: List<WorkHistory>,
    private val glide: RequestManager
) : RecyclerView.Adapter<WorkHistoryListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_list_work_history, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]
        holder.companyName.text = item.company_name
        holder.duration.text = item.duration
        holder.role.text = item.role
        holder.jobDescription.text = Html.fromHtml(item.job_description)
        glide.load(item.logo).into(holder.companyLogo)

    }

    override fun getItemCount(): Int = list.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val companyName: TextView = view.findViewById(R.id.textView_company_name)
        val duration: TextView = view.findViewById(R.id.textView_duration)
        val companyLogo: ImageView = view.findViewById(R.id.imageView_company)
        val role: TextView = view.findViewById(R.id.textView_role)
        val jobDescription: TextView = view.findViewById(R.id.textView_job_description)

    }
}