package dgtic.unam.sonido

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import dgtic.unam.modulosiete.R

class RecipeAdapterAudio(context: Context, private val list: ArrayList<ModeloAudio>) :
    BaseAdapter() {
    private var inflater: LayoutInflater

    init {
        inflater = LayoutInflater.from(context)
    }

    override fun getCount(): Int {
        return list.size
    }

    override fun getItem(p0: Int): Any? {
        return null
    }

    override fun getItemId(p0: Int): Long {
        return 1
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        val view = inflater.inflate(R.layout.list_item_audio, null)
        val file: TextView = view.findViewById(R.id.title)
        val image: ImageView = view.findViewById(R.id.image_sound)
        file.text = list[p0].namefile
        image.setImageResource(list[p0].nameImage)
        return view
    }
}