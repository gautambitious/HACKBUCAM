package com.gautam.metadoc

data class Patient(
    val type: Boolean = true,
    val medicines: ArrayList<Medicine> = arrayListOf(Medicine())
)

data class Medicine(
    val days: Int = 0,
    val frequency: Int =0,
    val name: String = ""
)
