package com.pooja.devmateai.model

data class ResumeAnalysisResponse(
    val overallScore: Int,
    val strengths: List<String>,
    val improvements: List<String>,
    val atsScore: Int
)