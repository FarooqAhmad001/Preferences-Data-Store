package com.example.datastoredemo.ui.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import com.example.datastoredemo.di.DIComponent
import com.example.datastoredemo.ui.theme.DataStoreDemoTheme
import com.example.datastoredemo.ui.widgets.activityMain.TextFields

class MainActivity : ComponentActivity() {

    private val diComponent by lazy { DIComponent() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DataStoreDemoTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = Color.White /*MaterialTheme.colorScheme.background*/) {
                    TextFields(diComponent, lifecycleScope)
                }
            }
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun Preview() {
        DataStoreDemoTheme {
            TextFields(diComponent, lifecycleScope)
        }
    }

}