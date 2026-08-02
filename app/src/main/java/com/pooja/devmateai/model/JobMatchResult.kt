package com.pooja.devmateai.model

data class JobMatchResult(
    val matchScore: Int,
    val missingSkills: List<String>,
    val suggestions: List<String>
)