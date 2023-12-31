package com.example.fitnesshub

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView

class fitness(private val context: Context, private var fitnessDataList: List<FitnessData>) :
    RecyclerView.Adapter<fitness.FitnessViewHolder>() {

    private lateinit var listener: fitness.OnItemClickListener

    inner class FitnessViewHolder(itemView:View) : RecyclerView.ViewHolder(itemView){

        val logo:ImageView = itemView.findViewById(R.id.logoUthm)
        val title: TextView = itemView.findViewById(R.id.titleUthm)

    }

    fun setFilteredList(mlist: List<FitnessData>){
        this.fitnessDataList=mlist
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FitnessViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.each_item, parent, false)
        val holder = FitnessViewHolder(view)

        // قم بتوصيل مستمع النقرات باستخدام السياق الصحيح
        holder.itemView.setOnClickListener {
            onItemClick(holder)
        }

        return holder
    }

    fun onItemClick(holder: FitnessViewHolder) {
       // startActivity(Intent(context, Aevent::class.java))
       val nextActivity = Aevent::class.java
        val intent = Intent(context, nextActivity)
        ContextCompat.startActivity(context, intent, null)
    // استخدم السياق الصحيح
    }

    override fun getItemCount(): Int {
       return fitnessDataList.size
    }

    override fun onBindViewHolder(holder: FitnessViewHolder, position: Int) {
       holder.logo.setImageResource(fitnessDataList[position].logo)
        holder.title.text = fitnessDataList[position].title
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }

    interface OnItemClickListener {
        fun onItemClick(fitnessData: FitnessData)
    }

    fun onItemClick(holder: FitnessViewHolder, position: Int) {
        val fitnessData = fitnessDataList[position]

        // اتصل بالمستمع
        listener?.onItemClick(fitnessData)

    }




}