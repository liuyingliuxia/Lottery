package edu.fjut.se1603.lwd34.lottery

import org.litepal.crud.LitePalSupport

class InputName : LitePalSupport(){
    var id :Int = 0
    var name :String = ""
    override fun toString(): String {
        return "InputName(id=$id, name='$name')"
    }
}