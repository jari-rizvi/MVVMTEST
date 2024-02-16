package com.ingenious.betterworld.ui.fragments.HomeFragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ingenious.betterworld.databinding.ItemRegisterContactBinding
import com.squareup.picasso.Picasso
import com.ingenious.betterworld.ui.fragments.HomeFragment.model.RegisteredContact

class RegisterContactAdapter(
    val arrayList: ArrayList<RegisteredContact>
) : RecyclerView.Adapter<RegisterContactAdapter.ItemRegisterContactViewHolder>() {


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ItemRegisterContactViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemRegisterBinding = ItemRegisterContactBinding.inflate(inflater, parent, false)
        return ItemRegisterContactViewHolder(itemRegisterBinding)
    }

    override fun onBindViewHolder(holder: ItemRegisterContactViewHolder, position: Int) {
        val doc: RegisteredContact = arrayList[position]

        if (doc.image.isNotBlank()) {
            Picasso.get().load(doc.image).into(holder.binding.profilePicture)
        }

        holder.binding.name.text = try {
            doc.name
        } catch (e: java.lang.Exception) {
            ""
        }

        if (doc.contactType == "Active Member") {
            holder.binding.active.visibility = View.VISIBLE

        } else if (doc.contactType == "Fitness") {
            holder.binding.notActive.visibility = View.VISIBLE


        }


    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    class ItemRegisterContactViewHolder(itemRegisterContactbinding: ItemRegisterContactBinding) :
        RecyclerView.ViewHolder(itemRegisterContactbinding.root) {
        val binding = itemRegisterContactbinding

    }

}