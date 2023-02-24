package com.draft.userreg

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatSpinner
import androidx.appcompat.widget.SwitchCompat
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.HashMap


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val userMap = HashMap<Int,UserMain>()

        val name: EditText = findViewById(R.id.name_edit)
        val dataEdit: EditText = findViewById(R.id.data_enter)
        val birthDayScroll: DatePicker = findViewById(R.id.date_pick)
        val isMarriedSwitch: SwitchCompat = findViewById(R.id.married_switch)
        val dataShow:TextView = findViewById(R.id.data_show)

        val submitBtn: Button = findViewById(R.id.btn_submit)
        val resetBtn: Button = findViewById(R.id.btn_reset)
        val showDataBtn: Button = findViewById(R.id.btn_data)
        val radioGroup: RadioGroup = findViewById(R.id.radio_btns)
        val ageSpinner: Spinner = findViewById(R.id.spinner)

        var ageInt: Int = 25;
        val stringArr = resources.getStringArray(R.array.age_array)
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, stringArr)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        ageSpinner.adapter = adapter

        ageSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View?,
                position: Int,
                id: Long
            ) {
                ageInt = Integer.parseInt(stringArr[position])
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }

        val vegCheckBox:CheckBox = findViewById(R.id.food_check_box1)
        val nonVegCheckBox:CheckBox = findViewById(R.id.food_check_box2)
        val veganCheckBox:CheckBox = findViewById(R.id.food_check_box3)

        val day = birthDayScroll.dayOfMonth
        val month = birthDayScroll.month
        val year = birthDayScroll.year

        val calendar = Calendar.getInstance()
        calendar.set(year, month, day)

        val dob = calendar.time

        val dateFormat = SimpleDateFormat("MM/dd/yyyy", Locale.US)
        val formattedDate = dateFormat.format(dob)
        birthDayScroll.init(
            birthDayScroll.year,
            birthDayScroll.month,
            birthDayScroll.dayOfMonth
        )
        { view, year, month, day ->
            val calendar = Calendar.getInstance()
            calendar.set(year, month, day)
        }

        // Submit Button
        submitBtn.setOnClickListener {
            val selectedGender = when (radioGroup.checkedRadioButtonId) {
                R.id.male_radio -> Gender.MALE
                R.id.female_radio -> Gender.FEMALE
                else -> null
            }
            var foodChoice:FoodChoice = FoodChoice.VEGETARIAN;
            if(vegCheckBox.isSelected)
                foodChoice = FoodChoice.VEGETARIAN
            else if(nonVegCheckBox.isSelected)
                foodChoice = FoodChoice.NON_VEG
            else if(veganCheckBox.isSelected)
                foodChoice = FoodChoice.VEGAN

            val user = UserMain(name.text.toString(),ageInt,
                selectedGender ?: Gender.MALE,foodChoice ,formattedDate, isMarriedSwitch.isChecked)

            val age:Int = 33
            ageSpinner.setSelection(stringArr.indexOf(age.toString()))

            userMap.put(ageInt as Int, user)

            val loginIntent = Intent(this, LoginActivity::class.java)
            startActivity(/* intent = */ loginIntent)

        }

        // Show Data
        // Show Data
        // Show Data Button
        showDataBtn.setOnClickListener {
            // Get the age from the input field
            val age = dataEdit.text.toString().toIntOrNull() ?: return@setOnClickListener

            // Get the user data from the HashMap
            val user = userMap[age] ?: return@setOnClickListener

            // Format the user data as a string
            val userData = "Name: ${user.name}\n" +
                    "Age: ${user.age}\n" +
                    "Gender: ${user.gender}\n" +
                    "Food Choice: ${user.foodChoice}\n" +
                    "Date of Birth: ${user.dateOfBirth}\n" +
                    "Married: ${user.isMarried}"

            // Display the user data in the data_output EditText
            dataShow.setText(userData)
        }

    }
    }
