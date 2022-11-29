package dgtic.unam.modulosiete

import android.media.MediaPlayer
import android.os.Bundle
import android.os.Environment
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import dgtic.unam.modulosiete.databinding.ActivitySoundBinding
import dgtic.unam.sonido.ModeloAudio
import dgtic.unam.sonido.RecipeAdapterAudio
import java.io.File

class SoundActivity : AppCompatActivity() {
    private lateinit var archivos: ArrayList<ModeloAudio>
    private lateinit var binding: ActivitySoundBinding
    private lateinit var adap: RecipeAdapterAudio
    private var mediaPlayer: MediaPlayer? = null
    private var indice: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySoundBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)
        val stadoSD: String = Environment.getExternalStorageState()
        if (stadoSD == Environment.MEDIA_MOUNTED) {
            val ff =
                File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC).path)
            archivos = ArrayList()
            ff.listFiles()!!.forEach {
                if (it.isFile) {
                    println(it)
                    archivos.add(ModeloAudio(it.name, R.drawable.musica_img, it.path))
                }
            }
            archivos.add(
                ModeloAudio(
                    "Piano Sonata No. 32 in C minor, Op. 111 (1st movement).ogg",
                    R.drawable.musica_img,
                    "https://upload.wikimedia.org/wikipedia/commons/9/95/Beethoven_-_Piano_sonata_in_C_minor_%28opus_111%29%2C_movement_1.ogg"
                )
            )
        }
        adap = RecipeAdapterAudio(this, archivos)
        binding.list.adapter = adap
        binding.play.setOnClickListener {
            if (mediaPlayer == null) {
                mediaPlayer = MediaPlayer().apply {
                    setDataSource(archivos.get(indice).path)
                    prepare()
                    start()
                }
            } else {
                mediaPlayer!!.start()
            }
        }
        binding.stop.setOnClickListener {
            if (mediaPlayer != null) {
                mediaPlayer!!.release()
                mediaPlayer = null
            }
        }
        binding.pause.setOnClickListener {
            if (mediaPlayer != null) {
                mediaPlayer!!.pause()
            }
        }
        binding.path.setOnClickListener {
            Toast.makeText(this, archivos.get(indice).path, Toast.LENGTH_SHORT).show()
        }
        binding.list.setOnItemClickListener { parent, view, position, id ->
            val data: ModeloAudio = archivos.get(position)
            indice = position
            if (mediaPlayer == null) {
                mediaPlayer = MediaPlayer().apply {
                    setDataSource(data.path)
                    prepare()
                    start()
                }
            } else {
                mediaPlayer!!.release()
                mediaPlayer = MediaPlayer().apply {
                    setDataSource(data.path)
                    prepare()
                    start()
                }
            }
            Toast.makeText(this, data.namefile, Toast.LENGTH_SHORT).show()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}