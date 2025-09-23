package com.solar.learncompose1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.jin.compose.core.ui.scaffold.RootComposeApp
import com.solar.learncompose1.ui.theme.LearnCompose1Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LearnCompose1Theme {
                RootComposeApp {
                    Greeting(name = "Android")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!", modifier = modifier
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview() {
    LearnCompose1Theme {
        RootComposeApp {
            Greeting(name = "Android")
        }
    }
}