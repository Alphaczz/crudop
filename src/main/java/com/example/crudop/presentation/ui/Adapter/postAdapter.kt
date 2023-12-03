package com.example.crudop.presentation.ui.Adapter
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.crudop.data.model.PostModel
import com.example.crudop.databinding.CustomCardLayoutBinding
class PostAdapter(private val clickListener: (PostModel) -> Unit, private val likeListener: (PostModel) -> Unit, private val dislikeListener: (PostModel) -> Unit) :
    ListAdapter<PostModel, PostAdapter.PostViewHolder>(PostDiffCallback) {

    class PostViewHolder(private val binding: CustomCardLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(post: PostModel, clickListener: (PostModel) -> Unit, likeListener: (PostModel) -> Unit, dislikeListener: (PostModel) -> Unit) {
            binding.titleTextView.text = post.postTitle
            binding.description.text = post.posterDes
            binding.likeCountTextView.text=post.likes.toString()
            binding.dislikeCountTextView.text=post.dislike.toString()

            binding.readMoreButton.setOnClickListener {
                clickListener(post)
            }
            binding.likeButton.setOnClickListener {
                likeListener(post)
            }

            binding.dislikeButton.setOnClickListener {
                dislikeListener(post)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = CustomCardLayoutBinding.inflate(inflater, parent, false)
        return PostViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val post = getItem(position)
        holder.bind(post, clickListener, likeListener, dislikeListener)
    }
}

object PostDiffCallback : DiffUtil.ItemCallback<PostModel>() {
    override fun areItemsTheSame(oldItem: PostModel, newItem: PostModel): Boolean {
        return oldItem.postid == newItem.postid
    }

    override fun areContentsTheSame(oldItem: PostModel, newItem: PostModel): Boolean {
        return oldItem == newItem
    }
}
