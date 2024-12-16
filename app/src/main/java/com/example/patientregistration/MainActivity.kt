package com.example.patientregistration

import android.app.Activity
import android.app.DatePickerDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class MainActivity : AppCompatActivity() {

    private val REQUEST_CODE_CAPTURE_PHOTO = 1001
    private val photoUris = mutableMapOf<String, Uri?>()

    private lateinit var firstName: TextInputEditText
    private lateinit var lastName: TextInputEditText
    private lateinit var dateOfBirth: TextView
    private lateinit var weight: TextInputEditText
    private lateinit var shoeSize: TextInputEditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        firstName = findViewById(R.id.firstName)
        lastName = findViewById(R.id.lastName)
        dateOfBirth = findViewById(R.id.dateOfBirth)
        weight = findViewById(R.id.weight)
        shoeSize = findViewById(R.id.shoeSize)

        findViewById<Button>(R.id.captureTopLeftButton).setOnClickListener {
            capturePhoto("TopLeft")
        }
        findViewById<Button>(R.id.captureBottomLeftButton).setOnClickListener {
            capturePhoto("BottomLeft")
        }
        findViewById<Button>(R.id.captureTopRightButton).setOnClickListener {
            capturePhoto("TopRight")
        }
        findViewById<Button>(R.id.captureBottomRightButton).setOnClickListener {
            capturePhoto("BottomRight")
        }

        findViewById<Button>(R.id.submitButton).setOnClickListener {
            if (validateForm()) {
                submitData()
            }
        }

        dateOfBirth.setOnClickListener { showDatePicker() }
    }

    private fun capturePhoto(tag: String) {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        intent.putExtra("tag", tag)
        startActivityForResult(intent, REQUEST_CODE_CAPTURE_PHOTO)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_CODE_CAPTURE_PHOTO && resultCode == Activity.RESULT_OK) {
            val tag = data?.extras?.getString("tag") ?: ""
            val photoUri = data?.data

            if (photoUri != null && tag.isNotEmpty()) {
                photoUris[tag] = photoUri
                Toast.makeText(this, "$tag photo captured successfully!", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Failed to capture $tag photo.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun validateForm(): Boolean {
        if (firstName.text.isNullOrEmpty()) return showError("First Name is required")
        if (lastName.text.isNullOrEmpty()) return showError("Last Name is required")
        if (dateOfBirth.text.isNullOrEmpty() || dateOfBirth.text == "Select Date of Birth") return showError("Date of Birth is required")
        if (weight.text.isNullOrEmpty()) return showError("Weight is required")
        if (shoeSize.text.isNullOrEmpty()) return showError("Shoe Size is required")
        if (photoUris.size < 4) return showError("Please capture all 4 photos")
        return true
    }

    private fun showError(message: String): Boolean {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        return false
    }

    private fun submitData() {
        val photosMap = photoUris.mapValues { it.value.toString() }
        val patientData = PatientData(
            firstName.text.toString(),
            lastName.text.toString(),
            dateOfBirth.text.toString(),
            weight.text.toString(),
            shoeSize.text.toString(),
            photosMap
        )

        RetrofitClient.apiService.submitPatientData(patientData)
            .enqueue(object : Callback<ResponseBody> {
                override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                    if (response.isSuccessful) {
                        Toast.makeText(this@MainActivity, "Data submitted successfully!", Toast.LENGTH_LONG).show()
                    } else {
                        Toast.makeText(this@MainActivity, "Submission failed: ${response.code()}", Toast.LENGTH_LONG).show()
                    }
                }

                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    Toast.makeText(this@MainActivity, "Submission failed: ${t.message}", Toast.LENGTH_LONG).show()
                }
            })
    }

    private fun showDatePicker() {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(
            this,
            { _, selectedYear, selectedMonth, selectedDay ->
                dateOfBirth.text = "$selectedDay/${selectedMonth + 1}/$selectedYear"
            },
            year, month, day
        )
        datePickerDialog.show()
    }
}
