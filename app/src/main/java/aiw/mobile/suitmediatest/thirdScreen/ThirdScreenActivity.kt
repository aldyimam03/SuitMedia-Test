package aiw.mobile.suitmediatest.thirdScreen

import aiw.mobile.submissionawalulang.adapter.UsersAdapter
import aiw.mobile.suitmediatest.R
import aiw.mobile.suitmediatest.databinding.ActivityThirdScreenBinding
import aiw.mobile.suitmediatest.secondScreen.SecondScreenActivity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ThirdScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivityThirdScreenBinding
    private lateinit var viewModel: ThirdScreenViewModel
    private lateinit var usersAdapter: UsersAdapter
    private var currentPage = 1
    private var isLoading = false
    private var pageSize = 10

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityThirdScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.ivBackThirdScreen.setOnClickListener {
            startActivity(Intent(this, SecondScreenActivity::class.java))
        }

        setupRecyclerView()
        setupSwipeRefresh()

        viewModel = ViewModelProvider(this)[ThirdScreenViewModel::class.java]
        observeViewModel()

        fetchUsers()
    }

    private fun setupRecyclerView() {
        usersAdapter = UsersAdapter { user ->
            val intent = Intent(this, SecondScreenActivity::class.java)
            intent.putExtra("USERNAME", "${user.firstName} ${user.lastName}")
            startActivity(intent)
        }

        binding.rvThirdScreen.layoutManager = LinearLayoutManager(this)
        binding.rvThirdScreen.adapter = usersAdapter

        binding.rvThirdScreen.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                val visibleItemCount = layoutManager.childCount
                val totalItemCount = layoutManager.itemCount
                val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()

                if (!isLoading && visibleItemCount + firstVisibleItemPosition >= totalItemCount
                    && firstVisibleItemPosition >= 0
                ) {
                    currentPage++
                    fetchUsers()
                }
            }
        })
    }

    private fun setupSwipeRefresh() {
        binding.swipeRefresh.setOnRefreshListener {
            currentPage = 1
            usersAdapter.submitList(emptyList())
            fetchUsers()
        }
    }

    private fun fetchUsers() {
        isLoading = true
        viewModel.getUsers(currentPage, pageSize)
    }

    private fun observeViewModel() {
        viewModel.users.observe(this) { users ->
            binding.swipeRefresh.isRefreshing = false
            isLoading = false
            if (users != null) {
                Log.d("ThirdScreenActivity", "Jumlah pengguna: ${users.size}")
                if (currentPage == 1) {
                    usersAdapter.submitList(users)
                } else {
                    val currentList = usersAdapter.currentList.toMutableList()
                    currentList.addAll(users)
                    usersAdapter.submitList(currentList)
                }
            } else {
                Log.e("ThirdScreenActivity", "Daftar pengguna kosong")
            }
        }
    }
}
