package dgtic.unam.modulosiete

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import dgtic.unam.modulosiete.databinding.ActivityRecyclerViewBinding
import dgtic.unam.recyclerview.AdapterViewHolder
import dgtic.unam.recyclerview.Source

class RecyclerViewActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRecyclerViewBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecyclerViewBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)
        initItem()
    }

    private fun initItem() {
        val recyclerView = binding.rvRecyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = AdapterViewHolder(this, Source.dataList)
        recyclerView.adapter = adapter
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}