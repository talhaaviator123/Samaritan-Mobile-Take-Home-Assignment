package com.samaritan.assignment

import android.app.Application
import com.samaritan.assignment.enum.ColorEnums
import dagger.hilt.android.HiltAndroidApp


@HiltAndroidApp
class MainApplication : Application(){
    val hashMap = HashMap<String,Int>()

    override fun onCreate() {
        super.onCreate()
        setUpColorsHashmap()
    }

    private fun setUpColorsHashmap(){
        hashMap.put(ColorEnums.ROCK.color,getColor(R.color.rock))
        hashMap.put(ColorEnums.GHOST.color,getColor(R.color.ghost))
        hashMap.put(ColorEnums.STEEL.color,getColor(R.color.steel))
        hashMap.put(ColorEnums.WATER.color,getColor(R.color.water))
        hashMap.put(ColorEnums.GRASS.color,getColor(R.color.grass))
        hashMap.put(ColorEnums.PSYCHIC.color,getColor(R.color.psychic))
        hashMap.put(ColorEnums.ICE.color,getColor(R.color.ice))
        hashMap.put(ColorEnums.DARK.color,getColor(R.color.dark))
        hashMap.put(ColorEnums.NORMAL.color,getColor(R.color.normal))
        hashMap.put(ColorEnums.FIGHTING.color,getColor(R.color.fighting))
        hashMap.put(ColorEnums.FLYING.color,getColor(R.color.flying))
        hashMap.put(ColorEnums.POISON.color,getColor(R.color.poison))
        hashMap.put(ColorEnums.GROUND.color,getColor(R.color.ground))
        hashMap.put(ColorEnums.BUG.color,getColor(R.color.bug))
        hashMap.put(ColorEnums.FIRE.color,getColor(R.color.fire))
        hashMap.put(ColorEnums.ELECTRIC.color,getColor(R.color.electric))
        hashMap.put(ColorEnums.DRAGON.color,getColor(R.color.dragon))
    }
}