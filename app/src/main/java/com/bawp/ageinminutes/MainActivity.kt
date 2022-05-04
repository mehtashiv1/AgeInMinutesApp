package com.bawp.ageinminutes

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    private var tvselectedDate :TextView? =  null
    private var tvInMinutes : TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnDatePicker : Button = findViewById(R.id.button2)
        tvselectedDate = findViewById(R.id.tvselectedDate)
        tvInMinutes = findViewById(R.id.tvInMinutes)
        btnDatePicker.setOnClickListener{
            clickdatepicker()
        }
    }
    private fun clickdatepicker() {
        val myCalender = Calendar.getInstance() //gets calender from java to get all the year,month and dates
        val year = myCalender.get(Calendar.YEAR) //takes years from the date picker
        val month = myCalender.get(Calendar.MONTH) // takes month from date picker
        val day = myCalender.get(Calendar.DAY_OF_MONTH) // takes the day from date picker
        val dpd = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view, selectedYear, selectedMonth, selecteddayOfMonth ->
            val selectedDate = "$selecteddayOfMonth/${selectedMonth + 1}/$selectedYear"
            tvselectedDate?.setText(selectedDate)
            var sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH) // converts the date format to the format dd/MM//yyyy
            val theDate = sdf.parse(selectedDate) // parses the selected date
            val selectedDateInMinutes = theDate.time / 60000 // converts the selected date in minutes
            val currentDate = sdf.parse(sdf.format(System.currentTimeMillis())) //tells how much time passes between current adn selected dAte //
            val currentDateInMinutes = currentDate.time / 60000
            val differenceBetweenDates =  currentDateInMinutes - selectedDateInMinutes
            tvInMinutes?.text = differenceBetweenDates.toString()
        }
        ,
        year , month , day)
        dpd.datePicker.maxDate = System.currentTimeMillis() - 86400000
        dpd.show()

    }
}