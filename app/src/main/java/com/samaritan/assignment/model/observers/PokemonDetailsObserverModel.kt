package com.samaritan.assignment.model.observers

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.samaritan.assignment.BR

class PokemonDetailsObserverModel : BaseObservable() {

    var name: String = ""
        @Bindable get
        set(value) {
            field = value
            notifyPropertyChanged(BR.name)
        }
    var types: String = ""
        @Bindable get
        set(value) {
            field = value
            notifyPropertyChanged(BR.types)
        }
    var weight: String ?= null
        @Bindable get
        set(value) {
            field = value
            notifyPropertyChanged(BR.weight)
        }

    var height: String ?= null
        @Bindable get
        set(value) {
            field = value
            notifyPropertyChanged(BR.height)
        }
    var hp: String = ""
        @Bindable get
        set(value) {
            field = value
            notifyPropertyChanged(BR.hp)
        }
    var attack: String ?= null
        @Bindable get
        set(value) {
            field = value
            notifyPropertyChanged(BR.attack)
        }
    var deffence: String = ""
        @Bindable get
        set(value) {
            field = value
            notifyPropertyChanged(BR.deffence)
        }
    var specialAttak: String = ""
        @Bindable get
        set(value) {
            field = value
            notifyPropertyChanged(BR.specialAttak)
        }
    var specialDeffence: String = ""
        @Bindable get
        set(value) {
            field = value
            notifyPropertyChanged(BR.specialDeffence)
        }
    var speed: String = ""
        @Bindable get
        set(value) {
            field = value
            notifyPropertyChanged(BR.speed)
        }
    var nickname: String = ""
        @Bindable get
        set(value) {
            field = value
            notifyPropertyChanged(BR.nickname)
        }
    var capturedDate: String = ""
        @Bindable get
        set(value) {
            field = value
            notifyPropertyChanged(BR.capturedDate)
        }
    var capturedLevel: String = ""
        @Bindable get
        set(value) {
            field = value
            notifyPropertyChanged(BR.capturedLevel)
        }



}