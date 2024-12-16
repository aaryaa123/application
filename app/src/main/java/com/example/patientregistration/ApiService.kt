package com.example.patientregistration

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("patients")
    fun submitPatientData(@Body patientData: PatientData): Call<ResponseBody>
}
