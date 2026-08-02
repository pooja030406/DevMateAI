package com.pooja.devmateai.model

object ATSAnalyzer {

    fun calculateATSScore(resumeText: String): Int {

        var score = 0

        val text = resumeText.lowercase()

        // Contact
        if ("@" in text) score += 10

        // Skills
        val skills = listOf(
            "java",
            "kotlin",
            "python",
            "c++",
            "android",
            "firebase",
            "sql",
            "git",
            "api"
        )

        score += skills.count {
            text.contains(it)
        } * 3

        // Projects
        if (text.contains("project")) score += 15

        // Experience
        if (text.contains("intern")) score += 10

        // Education
        if (text.contains("b.tech")) score += 10

        // Achievements
        if (text.contains("hackathon")) score += 10

        // Limit to 100
        if (score > 100) score = 100

        return score
    }

}