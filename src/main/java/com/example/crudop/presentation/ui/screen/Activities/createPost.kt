package com.example.crudop.presentation.ui.screen.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.crudop.MainActivity
import com.example.crudop.data.model.PostModel
import com.example.crudop.databinding.ActivityCreatePostBinding
import com.example.crudop.presentation.viewmodel.CreatePostViewModel
import com.example.crudop.presentation.viewmodel.PostViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.UUID
@AndroidEntryPoint
class CreatePost : AppCompatActivity() {
    private  val viewModel: CreatePostViewModel by viewModels()
    private lateinit var binding: ActivityCreatePostBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreatePostBinding.inflate(layoutInflater)
        setContentView(binding.root)

            val titleEditText: EditText = binding.editTextPostTitle
            val descriptionEditText: EditText = binding.editTextPostDescription
            val authorEditText: EditText = binding.editTextPostAuthor


            val saveButton: Button = binding.buttonSubmit
binding.homeIcon.setOnClickListener {
    navigateToMainActivity()
}
        saveButton.setOnClickListener {
        val title = titleEditText.text.toString()
        val description = descriptionEditText.text.toString()
        val author = authorEditText.text.toString()

        val post = createPost(title, description, author)

        CoroutineScope(Dispatchers.Default).launch {
            viewModel.savePost(post)
            navigateToMainActivity()
        }




}



    }
    fun navigateToMainActivity() {
        val intent = Intent(this@CreatePost, MainActivity::class.java)
        startActivity(intent)

    }


}
fun createPost( postTitle:String,
                posterDes:String,
                author:String,
): PostModel {
    return PostModel(postid = generateUniqueId() , postTitle=postTitle, posterDes = posterDes, author = author, likes = 0, dislike = 0)
}
fun generateUniqueId(): Long {
    val uuid = UUID.randomUUID()
    return uuid.leastSignificantBits
}
