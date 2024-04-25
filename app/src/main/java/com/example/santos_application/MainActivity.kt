package com.example.santos_application

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// Main Act of my Application
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MyApp()
        }
    }
}

// a data class to represent a question, its options, and the correct answer.
data class Question(val question: String, val options: List<String>, val answer: String)

// This is a composable function that represent my entire quiz application.
@Composable
fun MyApp() {
    //  to keep track of the current question index, score, and quiz completion status.
    var currentQuestionIndex by remember { mutableStateOf(0) }
    var score by remember { mutableStateOf(0) }
    var quizFinished by remember { mutableStateOf(false) }

    val questions = listOf(  // List of questions, choices and the correct answers.
        Question(
            "Is Chihiro a Boy or a Girl?",
            listOf("Boy", "Girl"),
            "Girl"
        ),
        Question(
            "Where was Spirited Away made?",
            listOf("Britain", "China", "Japan", "America"),
            "Japan"
        ),
        Question(
            "What animal does Haku turn into?",
            listOf("Cow", "Dragon", "Goat", "Cat"),
            "Dragon"
        ),
        Question(
            "What does the witch take from Chihiro?",
            listOf("Her parents", "Her spirit", "Her purse", "Her name"),
            "Her name"
        ),
        Question(
            "What are Chihiro's parents turned into?",
            listOf("Pigs", "Dogs", "Birds", "Ghosts"),
            "Pigs"
        ),
    )
    // Layout of my Application using box and column function
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFFC0CB)) // my background color <3
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            Text( // Kaartehan lang po
                text = "⋆ ˚｡⋆୨♡୧⋆ ˚｡⋆       ⋆ ˚｡⋆୨♡୧⋆ ˚｡⋆       ⋆ ˚｡⋆୨♡୧⋆ ˚｡⋆       ⋆ ˚｡⋆୨♡୧⋆ ˚｡⋆       ⋆ ˚｡⋆୨♡୧⋆ ˚｡⋆",
                fontSize = 9.5.sp,
                color = Color(0xFFFF69B4),
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
            )
            Text( // My welcome message
                text = "Welcome to my Spirited Away Quiz! ♡ ",
                color = Color.White,
                fontSize = 19.sp,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(vertical = 3.dp)
            )
            Text( // My name !! ˚ʚ♡ɞ˚
                text = "⋆ ˚｡⋆୨♡୧⋆ ˚｡⋆    ⋆ ˚｡⋆୨♡୧⋆ ˚｡⋆  By: Santos, Angelica S.⋆  ˚｡⋆୨♡୧⋆ ˚｡⋆    ⋆ ˚｡⋆୨♡୧⋆ ˚｡⋆ ",
                color = Color(0xFFFF69B4),
                fontSize = 9.5.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(vertical = 0.dp)
                    .fillMaxWidth()
            )
            Image( // Spirited away image
                painter = painterResource(id = R.drawable.pic_1),
                contentDescription = "pic_1",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .padding(vertical = 8.dp)
            )

            if (!quizFinished) {
                Text( // The current question number.
                    text = "Question ${currentQuestionIndex + 1}/${questions.size}",
                    color = Color(0xFFFF69B4),
                    fontSize = 8.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth().padding(vertical = 0.dp)
                )
                Text( // The current question.
                    text = questions[currentQuestionIndex].question,
                    color = Color(0xFFFF69B4),
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)
                )

                // Display options as buttons and handle user selection.
                questions[currentQuestionIndex].options.forEach { option ->
                    OptionButton(
                        option = option,
                        onOptionSelected = { selectedOption ->
                            if (selectedOption == questions[currentQuestionIndex].answer) {
                                score++
                            }
                            if (currentQuestionIndex < questions.size - 1) {
                                currentQuestionIndex++
                            } else {
                                // Set quizFinished to true if all questions are answered.
                                quizFinished = true
                            }
                        }
                    )
                }
                // The current score.
                Text(
                    text = "Score: $score",
                    color = Color(0xFFFF69B4),
                    fontWeight = FontWeight.Bold,
                    fontSize = 12.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            } else {
                // The final score if the quiz is finished
                Text(
                    text = "Quiz finished! Your final score is $score",
                    color = Color(0xFFFF69B4),
                    fontSize = 20.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxSize().padding(16.dp)
                )
            }
        }
    }
}

// To create option buttons for each question.
@Composable
fun OptionButton(option: String, onOptionSelected: (String) -> Unit) {
    Button(
        onClick = { onOptionSelected(option) },
        modifier = Modifier.fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(Color(0xFFFF69B4))
    ) {
        Text(text = option, color = Color.White)
    }
}

@Preview(showBackground = true) // To display the output
@Composable
fun MyAppPreview() {
    MyApp()
}



