package com.example.crudop.presentation.ui.screen.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.viewModels
import com.example.crudop.MainActivity
import com.example.crudop.databinding.ActivityCreatePostBinding
import com.example.crudop.domain.util.createPost
import com.example.crudop.presentation.viewmodel.CreatePostViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

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
            fun isValidInput(title: String, description: String, author: String): Boolean {
                return when {
                    title.isEmpty() -> {
                        titleEditText.error = "Title cannot be empty"
                        false
                    }
                    description.isEmpty() -> {
                        descriptionEditText.error = "Description cannot be empty"
                        false
                    }
                    author.isEmpty() -> {
                        authorEditText.error = "Author cannot be empty"
                        false
                    }
                    else -> true
                }
            }
            if (isValidInput(title, description, author)) {
                val post = createPost(title, description, author)

                CoroutineScope(Dispatchers.Default).launch {
                    viewModel.savePost(post)
                    navigateToMainActivity()
                }
            } else {
                var errorMessage = "Please fix the following issues:\n"

                if (title.isEmpty()) {
                    errorMessage += "- Title cannot be empty\n"
                }

                if (description.isEmpty()) {
                    errorMessage += "- Description cannot be empty\n"
                }

                if (author.isEmpty()) {
                    errorMessage += "- Author cannot be empty\n"
                }

                // Display a Toast with the error message
                Toast.makeText(this@CreatePost, errorMessage, Toast.LENGTH_LONG).show()
            }
        }

    }
    fun navigateToMainActivity() {
        val intent = Intent(this@CreatePost, MainActivity::class.java)
        startActivity(intent)

    }


}


