package aiw.mobile.suitmediatest.secondScreen

import aiw.mobile.suitmediatest.R
import aiw.mobile.suitmediatest.databinding.ActivitySecondScreenBinding
import aiw.mobile.suitmediatest.firstScreen.FirstScreenActivity
import aiw.mobile.suitmediatest.thirdScreen.ThirdScreenActivity
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class SecondScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySecondScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivitySecondScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val name = intent.getStringExtra("NAME")
        binding.tvJohnDoe.text = name

        val selectedUserName = intent.getStringExtra("USERNAME")
        if (selectedUserName != null) {
            binding.tvSelectedUser.text = selectedUserName
        }

        binding.btChooseUser.setOnClickListener {
            val intent = Intent(this, ThirdScreenActivity::class.java)
            startActivity(intent)
        }

        binding.ivBackSecondScreen.setOnClickListener {
            val intent = Intent(this, FirstScreenActivity::class.java)
            startActivity(intent)
        }
    }
}