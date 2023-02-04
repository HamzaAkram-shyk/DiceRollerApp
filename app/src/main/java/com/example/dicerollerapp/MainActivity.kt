package com.example.dicerollerapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dicerollerapp.ui.theme.DiceRollerAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DiceRollerAppTheme {
                var isWin = remember {
                    mutableStateOf(false)
                }
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Green),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    DiceRollerApp(
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight(0.8f)
                            .background(if (isWin.value) Color.Green else Color.Black),
                        isWin.value
                    ) {
                        isWin.value = it == 1
                    }

                    if (isWin.value)
                        Text(
                            text = "Congrats You Win the Game",
                            fontSize = 30.sp,
                            color = Color.White,
                            modifier = Modifier.padding(horizontal = 20.dp, vertical = 4.dp)
                        )

                }


            }
        }
    }
}


@Composable
fun DiceRollerApp(modifier: Modifier = Modifier, isWin: Boolean, callBack: (Int) -> Unit) {
    var counter by remember {
        mutableStateOf(1)
    }
    val diceImage = when (counter) {
        1 -> R.drawable.dice_1
        2 -> R.drawable.dice_2
        3 -> R.drawable.dice_3
        4 -> R.drawable.dice_4
        5 -> R.drawable.dice_5
        else -> R.drawable.dice_6
    }
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Image(painter = painterResource(diceImage), contentDescription = "$diceImage")
        Spacer(modifier = Modifier.heightIn(20.dp))
        if (!isWin)
            Button(onClick = {
                counter = (1..6).random()
                callBack(counter)
            }) {
                Text(text = "Roll Dice")
            }

    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    DiceRollerAppTheme {
        var isWin = remember {
            mutableStateOf(false)
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Green),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            DiceRollerApp(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.8f)
                    .background(if (isWin.value) Color.Green else Color.Black),
                isWin.value
            ) {
                isWin.value = it == 1
            }

            if (isWin.value)
                Text(
                    text = "Congrats You Win the Game",
                    fontSize = 30.sp,
                    color = Color.White,
                    modifier = Modifier.padding(horizontal = 20.dp, vertical = 4.dp)
                )

        }


    }
}