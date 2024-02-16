package com.ingenious.betterworld.ui.fragments.HomeFragment

import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.ingenious.betterworld.MainApplication.Companion.context
import com.ingenious.betterworld.databinding.ItemResultBinding
import com.ingenious.betterworld.ui.fragments.HomeFragment.model.RegisteredContact
import com.ingenious.betterworld.ui.fragments.HomeFragment.model.Result
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class ResultAdapter(
    val arrayList: ArrayList<Result>
) : RecyclerView.Adapter<ResultAdapter.ItemMainViewHolder>() {

    lateinit var registerContactAdapter: RegisterContactAdapter

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemMainViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemResultBinding = ItemResultBinding.inflate(inflater, parent, false)
        return ItemMainViewHolder(itemResultBinding)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: ItemMainViewHolder, position: Int) {

        val result: Result = arrayList[position]

        holder.binding.textView42.text = try {
            result.classRosterName
        } catch (e: java.lang.Exception) {
            ""
        }

        val dateTimeString =  result.classDateTime
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")
        val dateTime = LocalDateTime.parse(dateTimeString, formatter)
        val date = dateTime.toLocalDate()
        holder.binding.date.text = try {
            date.toString()
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
            ""
        }

        holder.binding.recMain

        result.registeredContacts

        val resulttArrayList: ArrayList<RegisteredContact> = ArrayList()
        result.registeredContacts.forEach {
            resulttArrayList.add(it)
        }

//        val linearLayoutManager = GridLayoutManager(context, 2)

        var spanCountInt = 0
        spanCountInt = if (resulttArrayList.size > 4){
            2
        }else{
            1
        }

        val staggeredGridLayoutManager = StaggeredGridLayoutManager(spanCountInt, StaggeredGridLayoutManager.HORIZONTAL)
        holder.binding.recMain.layoutManager = staggeredGridLayoutManager

        registerContactAdapter = RegisterContactAdapter(resulttArrayList)
        holder.binding.recMain.adapter = registerContactAdapter

    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    class ItemMainViewHolder(itemResultbinding: ItemResultBinding) :
        RecyclerView.ViewHolder(itemResultbinding.root) {
        val binding = itemResultbinding

    }


}