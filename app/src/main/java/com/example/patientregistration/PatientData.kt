package com.example.patientregistration

data class PatientData(
    val firstName: String,
    val lastName: String,
    val dateOfBirth: String,
    val weight: String,
    val shoeSize: String,
    val photos: Map<String, String>
)
