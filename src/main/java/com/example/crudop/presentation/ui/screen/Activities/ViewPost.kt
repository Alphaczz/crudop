package com.example.crudop.presentation.ui.screen.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import com.example.crudop.MainActivity
import com.example.crudop.data.model.PostModel
import com.example.crudop.databinding.ActivityViewPostBinding
import com.example.crudop.presentation.viewmodel.PostViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ViewPost : AppCompatActivity() {
    companion object {
        const val EXTRA_POST_ID = "extra_post_id"
    }
    private  val viewModel: PostViewModel by viewModels()
    private lateinit var binding: ActivityViewPostBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityViewPostBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val postId = intent.getLongExtra(EXTRA_POST_ID, 0L)
        Log.i("POSTID",postId.toString())
        binding.homeIcon.setOnClickListener{
            navigateToMainActivity()
        }
        binding.EditBtn.setOnClickListener {
            val intent = Intent(this@ViewPost, EditPost::class.java)
            intent.putExtra(ViewPost.EXTRA_POST_ID, postId)
            startActivity(intent)
        }

        viewModel.getOneFromDb(postId).observe(this) { post ->
            updateUI(post)

        }





        fun deleteItem(postid:Long) {
            viewModel.deletePost(postid)
            navigateToMainActivity()
        }
        fun showConfirmationDialog(postid: Long) {
            val alertDialogBuilder = AlertDialog.Builder(this)
            alertDialogBuilder.setTitle("Are you sure?")
            alertDialogBuilder.setMessage("Are you sure you want to delete?")

            alertDialogBuilder.setPositiveButton("Yes") { dialog, _ ->
                deleteItem(postid)
                dialog.dismiss()
            }


            alertDialogBuilder.setNegativeButton("No") { dialog, _ ->

                dialog.dismiss()
            }

            val alertDialog: AlertDialog = alertDialogBuilder.create()
            alertDialog.show()
        }

        binding.DelBtn.setOnClickListener {
            showConfirmationDialog(postId)
        }

    }
    private fun updateUI(post: PostModel) {
        binding.textViewTitleContent.text = post.postTitle
        binding.textViewDescContent.text = post.posterDes
        binding.textViewAuthorContent.text = post.author

        binding.likeCountTextView.text = post.likes.toString()
        binding.dislikeCountTextView.text = post.dislike.toString()
    }

    fun navigateToMainActivity() {
        val intent = Intent(this@ViewPost, MainActivity::class.java)
        startActivity(intent)

    }

}
