package com.example.datastoredemo.ui.widgets.activityMain

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LifecycleCoroutineScope
import com.example.datastoredemo.data.local.PreferenceDataStoreConstants.AGE_KEY
import com.example.datastoredemo.data.local.PreferenceDataStoreConstants.MOBILE_NUMBER
import com.example.datastoredemo.data.local.PreferenceDataStoreConstants.NAME_KEY
import com.example.datastoredemo.di.DIComponent
import kotlinx.coroutines.launch

@Composable
fun TextFields(diComponent: DIComponent, lifecycleScope: LifecycleCoroutineScope) {

    var name by remember { mutableStateOf("") }
    var age by remember { mutableStateOf("") }
    var phoneNumber by remember { mutableStateOf("") }


    var savedName by remember {
        mutableStateOf("")
    }
    var savedAge by remember {
        mutableIntStateOf(0)
    }
    var saveNumber by remember {
        mutableStateOf("")
    }

    LaunchedEffect(Unit){
        lifecycleScope.launch {
            diComponent.dataStore.getFlowPreference(NAME_KEY, "Default Name").collect {
                savedName = it
            }.toString()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 20.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text(text = "NAME", style = TextStyle(color = Color.DarkGray)) },
            textStyle = TextStyle(color = Color.Black)
        )
        OutlinedTextField(
            value = age,

            onValueChange = { age = it },
            label = { Text(text = "AGE", style = TextStyle(color = Color.DarkGray)) },
            textStyle = TextStyle(color = Color.Black),
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
        )
        OutlinedTextField(
            value = phoneNumber,
            onValueChange = { phoneNumber = it },
            label = { Text(text = "MOBILE NUMBER", style = TextStyle(color = Color.DarkGray)) },
            textStyle = TextStyle(color = Color.Black),
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Phone)
        )
        Button(onClick = {
            lifecycleScope.launch {
                diComponent.dataStore.putPreference(NAME_KEY, name)
                val tempAge = age.replace(",","")
                if (tempAge.isNotEmpty()) {
                    diComponent.dataStore.putPreference(AGE_KEY, tempAge.toInt())
                }
                if (phoneNumber.isNotEmpty()) {
                    diComponent.dataStore.putPreference(MOBILE_NUMBER, phoneNumber)
                }
            }
        }) {
            Text(text = "Save In DataStore")
        }


        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Name: ",
                style = TextStyle(
                    color = Color.Blue,
                    fontSize = 16.sp,
                    background = Color.White,
                    fontWeight = FontWeight.Bold
                ),
                maxLines = 1,
                modifier = Modifier.weight(1f)
            )
            Text(
                text = savedName,
                style = TextStyle(
                    color = Color.Black,
                    fontSize = 16.sp,
                    background = Color.White
                ),
                maxLines = 1,
                modifier = Modifier.weight(1f)
            )
        }

        Text(text = "Name is attached using Flow. So, each time the value changed, the observer will trigger and automatically changed the value here. also it will get the value at start of the app. " +
                "For the rest, you have to manually press the button and get the values",
            modifier = Modifier.padding(horizontal = 20.dp),
            color = Color.Gray,
            fontSize = 12.sp
        )

        Button(onClick = {
            lifecycleScope.launch {
                savedAge = diComponent.dataStore.getPreference(AGE_KEY, 0)
                saveNumber = diComponent.dataStore.getPreference(MOBILE_NUMBER, "0")
            }
        }) {
            Text(text = "Get Values")
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Age: ",
                style = TextStyle(
                    color = Color.Blue,
                    fontSize = 16.sp,
                    background = Color.White,
                    fontWeight = FontWeight.Bold
                ),
                maxLines = 1,
                modifier = Modifier.weight(1f)
            )
            Text(
                text = savedAge.toString(),
                style = TextStyle(
                    color = Color.Black,
                    fontSize = 16.sp,
                    background = Color.White
                ),
                maxLines = 1,
                modifier = Modifier.weight(1f)
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Phone Number: ",
                style = TextStyle(
                    color = Color.Blue,
                    fontSize = 16.sp,
                    background = Color.White,
                    fontWeight = FontWeight.Bold
                ),
                maxLines = 1,
                modifier = Modifier.weight(1f)
            )
            Text(
                text = saveNumber.toString(),
                style = TextStyle(
                    color = Color.Black,
                    fontSize = 16.sp,
                    background = Color.White
                ),
                maxLines = 1,
                modifier = Modifier.weight(1f)
            )
        }

    }
}

/*
@Composable
@Preview
fun TextFieldPreview() {
    TextFields(DIComponent())
}*/
