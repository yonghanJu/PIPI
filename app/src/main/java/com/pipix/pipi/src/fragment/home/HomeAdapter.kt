package com.pipix.pipi.src.fragment.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.Navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.pipix.pipi.R
import com.pipix.pipi.data.Old
import com.pipix.pipi.src.fragment.search.SearchAdapter
import com.pipix.pipi.src.main.MainActivity

class ViewPagerAdapter (private val oldList: MutableList<Old>)  :  RecyclerView.Adapter<ViewPagerAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        var name : TextView? = null
        var image: ImageView? = null
        var address: TextView? = null
        var schedule: TextView? = null
        lateinit var old: Old

        init {
            // Define click listener for the ViewHolder's View.
            name = view.findViewById(R.id.home_card_name)
            address = view.findViewById(R.id.home_card_address)
            image = view.findViewById(R.id.home_card_image)
            schedule = view.findViewById(R.id.home_card_schedule)

            // 객체 넘겨주기
            view.setOnClickListener {
                MainActivity.viewModel.currentOldID = old.oldID
                findNavController(view).navigate(R.id.action_homeFragment_to_profileFragment)
            }
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.home_card_layout, viewGroup, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var currentItem = oldList[position]

        holder.name!!.text = currentItem.oldName

        if (currentItem.oldImage == null){
            Glide.with(holder.itemView.getContext())
                .load(R.drawable.ic_basic_profile).centerCrop()
                .into(holder.image!!)}
        else{
            Glide.with(holder.itemView.getContext())
                .load(currentItem.oldImage).centerCrop()
                .into(holder.image!!)
        }
        holder.address!!.text = currentItem.oldAddress
        holder.schedule!!.text = SearchAdapter.getSchedule(currentItem)
        holder.old = oldList[position]
    }

    override fun getItemCount(): Int {
        return oldList.size
    }
}