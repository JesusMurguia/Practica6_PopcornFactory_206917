package murguia.jesus.practica6_popcornfactory

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.RadioButton
import android.widget.Toast
import androidx.annotation.IntegerRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.get
import kotlinx.android.synthetic.main.activity_seat_selection.*
import java.lang.Exception


class SeatSelection : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_seat_selection)
        var posMovie=-1
        var posSeat=-1
        var peliculas= ArrayList<DataPelicula>()
        var bundle= intent.extras
        var idseat=-1
        if(bundle!=null){
            titleSeats.setText(bundle.getString("name"))
            posMovie=bundle.getInt("id")
            peliculas=bundle.getParcelableArrayList("peliculas")!!
            //Toast.makeText(this, peliculas[posMovie].titulo, Toast.LENGTH_LONG).show()
            cargarAsientos(peliculas[posMovie])
        }


        Confirm.setOnClickListener(){
            if(posSeat>-1){
            Toast.makeText(this, "Reservaste el asiento No.$posSeat", Toast.LENGTH_LONG).show()
            var intent= Intent(this, grid::class.java)
            peliculas[posMovie].seats.add((Cliente("cliente1", tipodePago.PAYPAL, posSeat,idseat)))
            cargarAsientos(peliculas[posMovie])
            intent.putParcelableArrayListExtra("peliculas", peliculas)
            this!!.startActivity(intent)
            }else{
                Toast.makeText(this, "Selecciona un asiento disponible", Toast.LENGTH_LONG).show()
            }

        }
         row1.setOnCheckedChangeListener{ group, chekedId->
             if(chekedId>-1){
                 row2.clearCheck()
                 row3.clearCheck()
                 row4.clearCheck()
                 row1.check(chekedId)
                 idseat=chekedId
                 var radio = findViewById<View>(chekedId) as RadioButton
                 posSeat=Integer.parseInt(radio.text as String)
                 }
         }
        row2.setOnCheckedChangeListener{ group, chekedId->
            if(chekedId>-1){
                row1.clearCheck()
                row3.clearCheck()
                row4.clearCheck()
                row2.check(chekedId)
                var radio = findViewById<View>(chekedId) as RadioButton
                idseat=chekedId
                posSeat=Integer.parseInt(radio.text as String)
            }
        }
        row3.setOnCheckedChangeListener{ group, chekedId->
            if(chekedId>-1){
                row2.clearCheck()
                row1.clearCheck()
                row4.clearCheck()
                idseat=chekedId
                row3.check(chekedId)
                var radio = findViewById<View>(chekedId) as RadioButton
                posSeat=Integer.parseInt(radio.text as String)

            }
        }
        row4.setOnCheckedChangeListener{ group, chekedId->
            if(chekedId>-1){
                row2.clearCheck()
                row3.clearCheck()
                row1.clearCheck()
                row4.check(chekedId)
                idseat=chekedId
                var radio = findViewById<View>(chekedId) as RadioButton

                posSeat=Integer.parseInt(radio.text as String)
            }
        }
    }

    fun cargarAsientos(p: DataPelicula){
        try {
            for (s in p.seats){
                //Toast.makeText(this,s.idasiento.toString(), Toast.LENGTH_LONG).show()
                var radio = findViewById<View>(Integer.parseInt(s.idasiento.toString())) as RadioButton
                radio.isEnabled=false
                radio.setBackgroundResource(R.drawable.radio_disabled)
            }
        }catch (e: Exception){
            Toast.makeText(this,e.message, Toast.LENGTH_LONG).show()
        }


    }

}