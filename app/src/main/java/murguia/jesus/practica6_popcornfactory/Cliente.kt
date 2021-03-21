package murguia.jesus.practica6_popcornfactory

import android.os.Parcel
import android.os.Parcelable

data class Cliente (var nombre: String,
                    var tipoPago: tipodePago,
                    var asiento: Int,
                    var idasiento:Int) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readString()!!,
            tipodePago.values()[parcel.readInt()],
            parcel.readInt(),
            parcel.readInt()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(nombre)
        parcel.writeInt(tipoPago.ordinal)
        parcel.writeInt(asiento)
        parcel.writeInt(idasiento)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Cliente> {
        override fun createFromParcel(parcel: Parcel): Cliente {
            return Cliente(parcel)
        }

        override fun newArray(size: Int): Array<Cliente?> {
            return arrayOfNulls(size)
        }
    }

}