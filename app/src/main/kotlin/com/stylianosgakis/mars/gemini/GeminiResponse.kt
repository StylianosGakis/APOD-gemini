package com.stylianosgakis.mars.gemini

import kotlinx.serialization.Serializable

/**
 * Input JSON:
 * ```
 * {
 *   "contents": [
 *     {
 *       "parts": [
 *         {
 *           "text": "Explain how AI works"
 *         }
 *       ]
 *     }
 *   ]
 * }
 * ```
 */
@Serializable
data class GeminiInput(val contents: List<Content>) {
    @Serializable
    data class Content(val parts: List<Part>) {
        @Serializable
        data class Part(val text: String)
    }
}

/**
 * Response JSON:
 * ```
 * {
 *   "candidates": [
 *     {
 *       "content": {
 *         "parts": [
 *           {
 *             "text": "## How AI Works: A Simple Explanation\n\nAI, or Artificial Intelligence, is essentially the ability of a computer to perform tasks that typically require human intelligence. It's not one single thing, but a broad field encompassing various techniques and approaches. Here's a simplified breakdown:\n\n**1. Data is the Fuel:** AI systems are built on data. The more data they have, the better they can learn and perform. This data can be anything from images and text to sensor readings and financial records.\n\n**2. Algorithms are the Engine:** Algorithms are sets of instructions that tell the AI system how to process the data. There are many different types of algorithms, each suited for specific tasks, such as:\n\n* **Machine Learning (ML):** This type of algorithm allows the system to learn from data without being explicitly programmed. It identifies patterns and relationships in the data, making predictions or decisions based on them. Examples include:\n    * **Supervised Learning:** The AI is trained on labeled data (e.g., images with labels like \"dog\" or \"cat\") to learn how to classify new data.\n    * **Unsupervised Learning:** The AI learns patterns and structures from unlabeled data (e.g., grouping similar customers based on their purchasing history).\n    * **Reinforcement Learning:** The AI learns through trial and error, receiving rewards for desirable actions and penalties for undesirable ones.\n\n* **Deep Learning (DL):** A powerful subset of ML that uses artificial neural networks inspired by the human brain. These networks can process large amounts of complex data, making them ideal for tasks like image recognition, natural language processing, and speech recognition.\n\n**3. Training is the Process:** AI systems are trained using vast amounts of data. During training, the system adjusts its parameters based on the feedback it receives, improving its performance over time.\n\n**4. Applications are the Output:** Once trained, AI systems can be used for various applications:\n\n* **Image recognition:** Classifying objects in images, identifying faces, detecting anomalies.\n* **Natural language processing:** Understanding and generating human language, like chatbots and machine translation.\n* **Recommendation systems:** Suggesting products, movies, or music based on user preferences.\n* **Self-driving cars:** Navigating roads, making decisions, and reacting to traffic.\n* **Medical diagnosis:** Analyzing medical images and patient data to diagnose diseases.\n\n**In essence, AI works by mimicking human intelligence through complex algorithms that learn from data and apply their knowledge to solve problems and perform tasks.**\n\n**It's important to remember that AI is a constantly evolving field.** New techniques and applications are being developed all the time, making it an exciting and dynamic area of research.\n"
 *           }
 *         ],
 *         "role": "model"
 *       },
 *       "finishReason": "STOP",
 *       "index": 0,
 *       "safetyRatings": [
 *         {
 *           "category": "HARM_CATEGORY_SEXUALLY_EXPLICIT",
 *           "probability": "NEGLIGIBLE"
 *         },
 *         {
 *           "category": "HARM_CATEGORY_HATE_SPEECH",
 *           "probability": "NEGLIGIBLE"
 *         },
 *         {
 *           "category": "HARM_CATEGORY_HARASSMENT",
 *           "probability": "NEGLIGIBLE"
 *         },
 *         {
 *           "category": "HARM_CATEGORY_DANGEROUS_CONTENT",
 *           "probability": "NEGLIGIBLE"
 *         }
 *       ]
 *     }
 *   ],
 *   "usageMetadata": {
 *     "promptTokenCount": 4,
 *     "candidatesTokenCount": 560,
 *     "totalTokenCount": 564
 *   }
 * }
 * ```
 */
@Serializable
data class GeminiResponse(val candidates: List<Candidate>) {
    @Serializable
    data class Candidate(val content: Content) {
        @Serializable
        data class Content(val parts: List<Part>) {
            @Serializable
            data class Part(val text: String)
        }
    }
}