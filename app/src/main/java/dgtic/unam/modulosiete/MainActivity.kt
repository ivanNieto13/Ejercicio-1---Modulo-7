package dgtic.unam.modulosiete

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import dgtic.unam.modulosiete.databinding.ActivityMainBinding

enum class TipoProveedor {
    CORREO,
    GOOGLE,
    FACEBOOK
}

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private lateinit var drawer: DrawerLayout
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        loginData()
        inicioToolsBar()
    }

    private fun loginData() {
        var bundle: Bundle? = intent.extras
        var email: String? = bundle?.getString("email")
        var proveedor: String? = bundle?.getString("proveedor")

        val preferencias =
            getSharedPreferences(getString(R.string.file_preferencia), Context.MODE_PRIVATE).edit()
        preferencias.putString("email", email)
        preferencias.putString("proveedor", proveedor)
        preferencias.apply()
        preferencias.apply()
    }

    private fun inicioToolsBar() {
        val toolbar: Toolbar = findViewById(R.id.toolbar_main)
        setSupportActionBar(toolbar)
        drawer = findViewById(R.id.drawer_layout)
        val toggle = ActionBarDrawerToggle(this, drawer, toolbar, R.string.abrir, R.string.cerrar)
        drawer.addDrawerListener(toggle)
        toggle.syncState()
        toolbar.setNavigationIcon(R.drawable.unam_32)
        iniciarNavegacionView()
        binding.btnLogout.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            onBackPressed()
        }
    }

    private fun iniciarNavegacionView() {
        val navegacionView: NavigationView = findViewById(R.id.nav_view)
        navegacionView.setNavigationItemSelectedListener(this)
        val headerView: View = LayoutInflater.from(this).inflate(
            R.layout.header_main,
            navegacionView, false
        )
        navegacionView.addHeaderView(headerView)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.contraint_layout -> {
                startActivity(Intent(this, ConstraintActivity::class.java))
            }
            R.id.nestedscrollview -> {
                startActivity(Intent(this, NestedScrollViewActivity::class.java))
            }
            R.id.collapsing -> {
                startActivity(Intent(this, CollapsingToolbarLayout::class.java))
            }
            R.id.recyclerview -> {
                startActivity(Intent(this, RecyclerViewActivity::class.java))
            }
            R.id.music -> {
                startActivity(Intent(this, SoundActivity::class.java))
            }
            R.id.video -> {
                startActivity(Intent(this, VideoActivity::class.java))
            }
        }
        drawer.closeDrawer(GravityCompat.START)
        return true
    }
}