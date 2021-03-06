package murguia.jesus.practica6_popcornfactory

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_detalle_pelicula.*

class detalle_pelicula : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalle_pelicula)



        var bundle=intent.extras
        var peliculas= ArrayList<DataPelicula>()
        var ns=0;
        var id=-1;
        var title="";
        if(bundle!=null){
            iv_pelicula_img.setImageResource(bundle.getInt("header"))
            title=bundle.getString("titulo")!!
            tv_nombre_pelicula.setText(title)
            tv_description.setText(bundle.getString("sinopsis"))
            seatLeft.setText(bundle.getString("seats")+ " seats available")
            id=bundle.getInt("pos")
            peliculas=bundle.getParcelableArrayList("peliculas")!!

            //Toast.makeText(this, peliculas[id].titulo, Toast.LENGTH_LONG).show()
        }

        buyTickets.setOnClickListener{
            val intent = Intent(this,SeatSelection::class.java)
            intent.putExtra("id", id)
            intent.putExtra("name", title)
            intent.putParcelableArrayListExtra("peliculas",peliculas)
            this!!.startActivity(intent)
        }
    }
}