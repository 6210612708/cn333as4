package com.example.unitconverter.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.unitconverter.R
import java.lang.NumberFormatException

class PressureViewModel: ViewModel() {
    private val _point: MutableLiveData<Int> = MutableLiveData(R.string.psi)

    val point: LiveData<Int>
        get() = _point

    fun setPoint(value: Int) {
        _point.value = value
    }

    private val _pressure: MutableLiveData<String> = MutableLiveData("")

    val pressure: LiveData<String>
        get() = _pressure

    fun getPressureAsFloat(): Float = (_pressure.value ?: "").let {
        return try {
            it.toFloat()
        } catch (e: NumberFormatException) {
            Float.NaN
        }
    }
    fun setPressure(value: String) {
        _pressure.value = value
    }
    fun convert() = getPressureAsFloat().let {
        if (!it.isNaN())
            if (_point.value == R.string.psi)
                it * 0.068948F
            else
                it / 0.068948F
        else
            Float.NaN
    }
}