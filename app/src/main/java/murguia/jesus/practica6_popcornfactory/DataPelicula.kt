package murguia.jesus.practica6_popcornfactory

import android.os.Parcel
import android.os.Parcelable

class DataPelicula : Parcelable{

    var titulo: String?=""
    var image: Int=-1
    var header: Int=-1
    var sinopsis: String?=""
    var seats:ArrayList<Cliente> = ArrayList()


    constructor(){

    }



    constructor(source:Parcel):this() {
        titulo = source.readString()
        image = source.readInt()
        header = source.readInt()
        sinopsis = source.readString()
        seats=source.createTypedArrayList(Cliente.CREATOR)!!
    }

    constructor(title: String?, image: Int, header: Int, synopsis: String?, seats: ArrayList<Cliente>) {
        this.titulo = title
        this.image = image
        this.header = header
        this.sinopsis = synopsis
        this.seats = seats
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(p0: Parcel?, p1: Int) {
        p0?.writeString(titulo)
        p0?.writeInt(image)
        p0?.writeInt(header)
        p0?.writeString(sinopsis)
        p0?.writeTypedList(seats)
    }

    companion object CREATOR : Parcelable.Creator<DataPelicula> {
        override fun createFromParcel(parcel: Parcel): DataPelicula {
            return DataPelicula(parcel)
        }

        override fun newArray(size: Int): Array<DataPelicula?> {
            return arrayOfNulls(size)
        }
    }
}