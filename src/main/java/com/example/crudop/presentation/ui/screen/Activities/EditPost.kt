package com.example.crudop.presentation.ui.screen.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import com.example.crudop.MainActivity
import com.example.crudop.R
import com.example.crudop.data.model.PostModel
import com.example.crudop.databinding.ActivityEditPostBinding
import com.example.crudop.presentation.viewmodel.PostViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EditPost : AppCompatActivity() {
    companion object {
        const val EXTRA_POST_ID = "extra_post_id"
    }
    private  val viewModel: PostViewModel by viewModels()
    private lateinit var binding: ActivityEditPostBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditPostBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.homeIcon.setOnClickListener{
            navigateToMainActivity()
        }
        val postId = intent.getLongExtra(EXTRA_POST_ID, 0L)
        Log.i("POSTIDINEDIT",postId.toString())
       var likes:Int=0
        var dislike:Int=0
        viewModel.getOneFromDb(postId).observe(this) { post ->
            updateUI(post)
            likes=post.likes
            dislike=post.dislike
        }
        binding.buttonSubmit.setOnClickListener {
           val newpost= PostModel(
                postid = postId, postTitle = binding.editTextPostTitle.text.toString(),
                posterDes = binding.editTextPostDescription.text.toString(),
                author = binding.editTextPostAuthor.text.toString(),
                likes= likes,
                dislike = dislike

            )
            viewModel.updatePost(newpost)
            navigateToMainActivity()
        }

    }
    fun navigateToMainActivity() {
        val intent = Intent(this@EditPost, MainActivity::class.java)
        startActivity(intent)

    }
    private fun updateUI(post: PostModel) {
        binding.editTextPostTitle.setText(post.postTitle)

        binding.editTextPostDescription.setText(post.posterDes)
        binding.editTextPostAuthor.setText(post.author)




    }
}