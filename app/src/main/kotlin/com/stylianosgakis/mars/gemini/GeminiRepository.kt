package com.stylianosgakis.mars.gemini

import arrow.core.Either
import arrow.retrofit.adapter.either.networkhandling.CallError
import arrow.retrofit.adapter.either.networkhandling.HttpError

class GeminiRepository(
    private val geminiService: GeminiService,
) {
    suspend fun generateResponse(inputText: String): Either<CallError, String> {
        val finalPrompt = BasePrompt + System.lineSeparator() + inputText
        return geminiService.generateResponse(
            GeminiInput(listOf(GeminiInput.Content(listOf(GeminiInput.Content.Part(finalPrompt)))))
        ).map { response ->
            val responseText =
                response.candidates.firstOrNull()?.content?.parts?.firstOrNull()?.text
            responseText ?: return Either.Left(
                HttpError(1, "Response was not of the right type", response.toString())
            )
        }
    }

    companion object {
        private const val BasePrompt = """
            I will be providing you with some information which comes from Nasa's Astronomy Picture
            of the Day.
            Astronomy Picture of the Day is where each day a different image or photograph of our
            fascinating universe is featured, along with a brief explanation written by a
            professional astronomer.
            I want you to try your best to give me an educational answer about what this is
            related to, why it is important to us, and what we can learn from it. If you know of 
            any historical context like which mission this information is from, which planet we are
            looking at, who are the people involved and so on, try to help me learn more about them.
            The information will come in the form of one title and one explanation about the photo.
            Input:
        """
    }
}
