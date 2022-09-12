package com.example.restapi.activities

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.restapi.R
import com.example.restapi.databinding.ActivityUserBinding
import com.example.restapi.models.user.UsersItem

class UserActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUserBinding
    private lateinit var user: UsersItem

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_user)

        user = intent.extras?.get("user") as UsersItem
        binding.tvUserNameAlpha.text = intent?.extras?.getString("alphabet")
        binding.tvUserName.text = user.name

        init()

    }

    private fun init(){
        binding.clLoadMorePost.setOnClickListener{
            val intent = Intent(this@UserActivity, PostActivity::class.java).apply {
                putExtra("userId", user.id.toString())
            }
            startActivity(intent)
        }

        binding.clLoadMorePhotos.setOnClickListener {
            Toast.makeText(this@UserActivity, "Coming soon", Toast.LENGTH_SHORT).show()
        }

        binding.clLoadMoreTodos.setOnClickListener {
            Toast.makeText(this@UserActivity, "Coming soon", Toast.LENGTH_SHORT).show()
        }

        binding.ivPhone.setOnClickListener {
            try {
                val callIntent = Intent(Intent.ACTION_DIAL)
                callIntent.data = Uri.parse("tel:${user.phone}")
                startActivity(callIntent)
            }catch (e: Exception){
                Toast.makeText(this@UserActivity,"App Not Found!",Toast.LENGTH_SHORT).show()
            }
        }

        binding.ivEmail.setOnClickListener {
            try {
                val mailIntent = Intent(Intent.ACTION_VIEW).apply {
                    putExtra(Intent.EXTRA_EMAIL, arrayOf(user.email))
                    putExtra(Intent.EXTRA_SUBJECT, "Hello ${user.name}")
                    putExtra(Intent.EXTRA_TEXT, "Send this mail on behalf of ${user.name}")
                }
                startActivity(Intent.createChooser(mailIntent,null))
            }catch (e: Exception){
                Toast.makeText(this@UserActivity,"App Not Found!",Toast.LENGTH_SHORT).show()
            }
        }

        binding.ivMap.setOnClickListener {
            try{
                val strUri =
                    "http://maps.google.com/maps?q=loc:" + user.address.geo.lat.toString() + "," + user.address.geo.lng.toString() + " (" + "Label which you want" + ")"
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(strUri))

                intent.setClassName(
                    "com.google.android.apps.maps",
                    "com.google.android.maps.MapsActivity"
                )

                startActivity(intent)
            }catch (e: Exception){
                Toast.makeText(this@UserActivity,"App Not Found!",Toast.LENGTH_SHORT).show()
            }

        }

        binding.tvCompanyName.text = user.company.name
        binding.tvWebsite.text = user.website
        binding.tvAddress.text = "${user.address.suite}, ${user.address.street}, ${user.address.city}"
    }
}