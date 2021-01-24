package com.historymakers.resume.ui.workhistory

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.google.android.material.snackbar.Snackbar
import com.historymakers.resume.R
import com.historymakers.resume.model.WorkHistory
import com.historymakers.resume.ui.workhistory.viewmodel.WorkHistoryListViewModel
import com.historymakers.resume.ui.workhistory.viewmodel.WorkHistoryListViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlinx.android.synthetic.main.work_history_list_fragment.*
import kotlinx.android.synthetic.main.work_history_list_fragment.progress_bar
import javax.inject.Inject

@AndroidEntryPoint
class WorkHistoryListFragment : Fragment() {

    private lateinit var viewModel: WorkHistoryListViewModel

    @Inject
    lateinit var glide: RequestManager

    @Inject
    lateinit var viewModelFactory: WorkHistoryListViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.work_history_list_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpViewModel()
        observeLoader()
        observerWorkHistoryList()
    }

    private fun observeLoader() {
        viewModel.loader.observe(viewLifecycleOwner) { loading ->
            when (loading) {
                true -> progress_bar.visibility = View.VISIBLE
                else -> {
                    progress_bar.visibility = View.GONE
                }
            }
        }
    }

    private fun observerWorkHistoryList() {
        viewModel.workHistoryListLiveData.observe(viewLifecycleOwner) { list ->
            if (list.getOrNull() != null) {
                setUpList(recyclerView_work_history, list.getOrNull()!!)
            } else {
                Snackbar.make(work_history_root, R.string.generic_error, Snackbar.LENGTH_LONG)
                    .show()
            }
        }
    }

    private fun setUpList(view: RecyclerView, list: List<WorkHistory>) {
        with(view) {
            layoutManager = LinearLayoutManager(context)
            adapter = WorkHistoryListAdapter(list, glide)
        }
    }


    private fun setUpViewModel() {
        viewModel =
            ViewModelProvider(this, viewModelFactory).get(WorkHistoryListViewModel::class.java)

    }

}