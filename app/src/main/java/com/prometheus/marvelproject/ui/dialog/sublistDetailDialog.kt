package com.prometheus.marvelproject.ui.dialog

import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.prometheus.marvelproject.R
import com.prometheus.marvelproject.ui.Adapter.sublistItemAdapter
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.PublishSubject
import org.json.JSONObject


class sublistDetailDialog : DialogFragment() {

//    lateinit var binding : sublistDetailDialogBinding
   // private lateinit var viewModel: LocatonViewModel
//    private val disposables = CompositeDisposable()
//    val onItemSelected = PublishSubject.create<String>()

    companion object{
        fun newInstance():sublistDetailDialog{
            val args = Bundle()
            val dialogFragment = sublistDetailDialog()
            dialogFragment.arguments = args
            return dialogFragment
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(requireContext())
        val inflater = requireActivity().layoutInflater
        /*
        binding = DataBindingUtil.inflate(inflater, R.layout.sublist_detail_dialog, null, false )
       // userName = gibbonApp.appComponent.simpleCache().getStringValue("UserName")!!
        builder.setView(binding.root)
  //      initViewModel()
        initViews()
        */
        return builder.create()
    }

    /*
    private fun initViewModel() {
        binding.tvTitle.text = getString(R.string.btnAuditLocationField)
        viewModel = ViewModelProviders.of(this).get(LocatonViewModel::class.java)
        viewModel.onLocationListLoaded.observe(this, Observer { itemsFound ->
            val adapter = binding.rvSubitemListDetail.adapter as sublistItemAdapter
            adapter.setItems(itemsFound.locations!!)
        })
    }
*/

    private fun updateAdapter(it: MutableList<String>) {
    //    val adapter = binding.rvLocations.adapter as sublistItemAdapter
    //    adapter.setItems(it)
    }

    private fun initViews() {

    //    binding.progressBarWrapper.root.visibility = View.GONE
    //    binding.rvLocations.layoutManager = LinearLayoutManager(requireContext())
        setupAdapter()

    }

    private fun setupAdapter() {
    //    val adapter = sublistItemAdapter(mutableListOf(), requireContext(), this)
    //    disposables.add(adapter.onItemsSelected.subscribe {
    //        onItemSelected.onNext(it)
    //        dismiss()
     //   })
     //   binding.rvLocations.adapter = adapter
    }

    override fun onDestroyView() {
     //   disposables.clear()
        super.onDestroyView()
    }


}