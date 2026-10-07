package pe.edu.cibertec.appgrupo1_t2

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.google.android.material.navigation.NavigationBarView
import pe.edu.cibertec.appgrupo1_t2.databinding.ActivityPregunta2Binding

class Pregunta2Activity : AppCompatActivity(), View.OnClickListener, NavigationBarView.OnItemSelectedListener {

    private lateinit var binding: ActivityPregunta2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityPregunta2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.btnLogout.setOnClickListener(this)
        binding.bottomNav.setOnItemSelectedListener(this)

        if (savedInstanceState == null) {
            binding.bottomNav.selectedItemId = R.id.nav_p1
        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btnLogout -> {
                Toast.makeText(this, "Cerrando sesión...", Toast.LENGTH_SHORT).show()
                finish()
            }
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        val fragment: Fragment = when (item.itemId) {
            R.id.nav_p1 -> FragmentPregunta1()
            R.id.nav_p2 -> FragmentPregunta2()
            R.id.nav_p3 -> FragmentPregunta3()
            R.id.nav_p4 -> FragmentPregunta4()
            else -> return false
        }

        binding.tvTitle.text = item.title

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, fragment)
            .commit()

        return true
    }
}