package murguia.jesus.practica6_popcornfactory

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_seat_selection.*

class SeatSelection : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_seat_selection)

        var posMovie=-1
        var bundle= intent.extras
        if(bundle!=null){
            titleSeats.setText(bundle.getString("name"))
            posMovie=bundle.getInt("id")
        }

        Confirm.setOnClickListener(){
            Toast.makeText(this,"Enjoy the movie!", Toast.LENGTH_LONG).show()
        }
         row1.setOnCheckedChangeListener{group, chekedId->
             if(chekedId>-1){
                 row2.clearCheck()
                 row3.clearCheck()
                 row4.clearCheck()
                 row1.check(chekedId)
                 }
         }
        row2.setOnCheckedChangeListener{group, chekedId->
            if(chekedId>-1){
                row1.clearCheck()
                row3.clearCheck()
                row4.clearCheck()
                row2.check(chekedId)
            }
        }
        row3.setOnCheckedChangeListener{group, chekedId->
            if(chekedId>-1){
                row2.clearCheck()
                row1.clearCheck()
                row4.clearCheck()
                row3.check(chekedId)
            }
        }
        row4.setOnCheckedChangeListener{group, chekedId->
            if(chekedId>-1){
                row2.clearCheck()
                row3.clearCheck()
                row1.clearCheck()
                row4.check(chekedId)
            }
        }
    }

}