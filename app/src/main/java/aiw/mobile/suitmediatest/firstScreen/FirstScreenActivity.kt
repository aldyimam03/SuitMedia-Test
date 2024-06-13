package aiw.mobile.suitmediatest.firstScreen

import aiw.mobile.suitmediatest.R
import aiw.mobile.suitmediatest.databinding.ActivityFirstScreenMainBinding
import aiw.mobile.suitmediatest.secondScreen.SecondScreenActivity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class FirstScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFirstScreenMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityFirstScreenMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.btnCheck.setOnClickListener {
            val text = binding.customViewEditTextPalindrome.text.toString()
            if (isPalindrome(text)) {
                Toast.makeText(this, "isPalindrome", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "not Palindrome", Toast.LENGTH_SHORT).show()
            }
        }

        binding.btnNext.setOnClickListener {
            val name = binding.customViewEditTextName.text.toString()
            val intent = Intent(this, SecondScreenActivity::class.java)
            intent.putExtra("NAME", name)
            startActivity(intent)
        }

    }

    private fun isPalindrome(text: String): Boolean {
        val cleanText = text.replace("\\s+".toRegex(), "").lowercase()
        return cleanText == cleanText.reversed()
    }
}