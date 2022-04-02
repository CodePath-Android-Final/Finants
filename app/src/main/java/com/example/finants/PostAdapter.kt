package com.example.finants

import android.content.Context
import android.graphics.Color
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class PostAdapter(val context: Context, val posts: List<Post>)
    : RecyclerView.Adapter<PostAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostAdapter.ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_post,parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: PostAdapter.ViewHolder, position: Int) {
        val post = posts.get(position)
        holder.bind(post)
    }

    override fun getItemCount(): Int {
        return posts.size
    }
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val ivProfilePicture : ImageView
        val tvDescription : TextView

        init{
            ivProfilePicture = itemView.findViewById(R.id.ivProfilePicture)
            tvDescription = itemView.findViewById(R.id.tvDescription)
        }
        fun bind(post: Post){
            val builder: SpannableStringBuilder  = SpannableStringBuilder()
            val str1: SpannableString = SpannableString(post.getUser()?.username)
            str1.setSpan(ForegroundColorSpan(Color.BLUE), 0, str1.length, 0)
            builder.append(str1)
            builder.append(" wants to save up $")
            val str2: SpannableString = SpannableString(post.getAmount().toString())
            str2.setSpan(ForegroundColorSpan(Color.GREEN),0,str2.length,0)
            builder.append(str2)
            builder.append(" for ")
            val str3: SpannableString = SpannableString(post.getGoal())
            str3.setSpan(ForegroundColorSpan(Color.RED),0,str3.length,0)
            builder.append(str3)
            tvDescription.setText(builder, TextView.BufferType.SPANNABLE)
            Glide.with(itemView.context).load(post.getUser()?.getParseFile("profile_pic")?.url).into(ivProfilePicture)
        }
    }
}