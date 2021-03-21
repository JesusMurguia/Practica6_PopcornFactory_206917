package murguia.jesus.practica6_popcornfactory

import android.content.Context
import android.content.Intent
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_grid.*
import kotlinx.android.synthetic.main.activity_pelicula.view.*

class grid : AppCompatActivity() {

    var adapter: PeliculaAdapter? = null
    var peliculas = ArrayList<DataPelicula>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_grid)

            cargarPeliculas()
        var bundle= intent.extras
        if(bundle!=null){
            val myList = intent.getSerializableExtra("peliculas") as ArrayList<DataPelicula>
            peliculas=myList
        }


        adapter=PeliculaAdapter(this, peliculas)
        gridview.adapter=adapter
    }

    fun cargarPeliculas(){
        peliculas.add(DataPelicula("Dr. House", R.drawable.drhouse, R.drawable.househeader, resources.getString(R.string.synopsis_dr_house), arrayListOf<Cliente>()))
        peliculas.add(DataPelicula("Smallville", R.drawable.smallville, R.drawable.smallvilleheader, getString(R.string.synopsis_smallville), arrayListOf<Cliente>()))
        peliculas.add(DataPelicula("Dr. Who", R.drawable.drwho, R.drawable.drwhoheader, getString(R.string.synopsis_dr_who), arrayListOf<Cliente>()))
        peliculas.add((DataPelicula("Bones", R.drawable.bones, R.drawable.bonesheader, getString(R.string.synopsis_bones), arrayListOf<Cliente>())))
        peliculas.add((DataPelicula("Suits", R.drawable.suits, R.drawable.suitsheader, getString(R.string.synopsis_suits), arrayListOf<Cliente>())))
        peliculas.add(DataPelicula("Friends", R.drawable.friends, R.drawable.friendsheader, resources.getString(R.string.synopsis_friends), arrayListOf<Cliente>()))

        peliculas.add((DataPelicula("Bones", R.drawable.s, R.drawable.sh, getString(R.string.synopsis_p1917), arrayListOf<Cliente>())))
        peliculas.add(DataPelicula("Big Hero 6", R.drawable.bighero6, R.drawable.headerbighero6, resources.getString(R.string.synopsis_big_hero_6), arrayListOf<Cliente>()))
        peliculas.add(DataPelicula("Leap Year", R.drawable.leapyear, R.drawable.leapyearheader, resources.getString(R.string.synopsis_leap_year), arrayListOf<Cliente>()))
        peliculas.add((DataPelicula("Bones", R.drawable.mib, R.drawable.mibheader, getString(R.string.synopsis_men_in_black), arrayListOf<Cliente>())))
        peliculas.add(DataPelicula("Toy Story", R.drawable.toystory, R.drawable.toystoryheader, resources.getString(R.string.synopsis_toy_story), arrayListOf<Cliente>()))
        peliculas.add(DataPelicula("Inception", R.drawable.inception, R.drawable.inceptionheader, resources.getString(R.string.synopsis_inception), arrayListOf<Cliente>()))

    }
    }

class PeliculaAdapter: BaseAdapter{
    var peliculas= ArrayList<DataPelicula>()
    var contexto: Context? =null
    constructor(contexto: Context, peliculas: ArrayList<DataPelicula>){
        this.contexto=contexto
        this.peliculas=peliculas
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        var pelicula=peliculas[p0]
        var inflador=contexto!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        var vista=inflador.inflate(R.layout.activity_pelicula, null)
        vista.iv_pelicula.setImageResource(pelicula.image)
        vista.tv_nombre.setText(pelicula.titulo)


        vista.iv_pelicula.setOnClickListener(){
            var seatsAvailable= 20 - pelicula.seats.size
            var intent= Intent(contexto, detalle_pelicula::class.java)
            intent.putExtra("titulo", pelicula.titulo)
            intent.putExtra("image", pelicula.image)
            intent.putExtra("header", pelicula.header)
            intent.putExtra("sinopsis", pelicula.sinopsis)
            intent.putExtra("seats", seatsAvailable.toString())
            intent.putExtra("pos", p0)
            intent.putParcelableArrayListExtra("peliculas",peliculas)

            contexto!!.startActivity(intent)
        }


        return vista
    }

    override fun getItem(p0: Int): Any {
        return peliculas[p0]
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getCount(): Int {
        return peliculas.size
    }
}