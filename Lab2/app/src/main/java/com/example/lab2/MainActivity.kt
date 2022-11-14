package com.example.lab2

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lab2.ui.theme.Lab2Theme
import com.example.lab2.ui.theme.lab2PrimaryColor
import com.example.lab2.ui.theme.lab2SecondaryColor
import com.example.lab2.ui.theme.lab2TertiaryColor
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment
import androidx.compose.foundation.layout.padding as padding

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Lab2Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Greeting()
                }
            }
        }
    }
}

@Composable
fun displayText(sampleText:String)
{
    if (sampleText.isNotEmpty())
    {
        Text(
            text = "$sampleText",
            color = Color.White,
            fontSize = 24.sp,
            textAlign = TextAlign.Center
        )
    }

}

@Composable
fun userInput(inputText:String, changed:(String)->Unit)
{
    TextField(
        value = inputText, 
        label={ Text(stringResource(id = R.string.input_text))},
        onValueChange =changed,

        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp, bottom = 10.dp)
    )
}

@Composable
fun textHandlerBtn(anyText:String,clicked:()-> Unit)
{
    Button(onClick = clicked)
    {
        Text(text = stringResource(id =R.string.text_btn))
    }
}

@Composable
fun MyImage(image: Int)
{
    Image(
        painter = painterResource(image),
        contentDescription = stringResource(id = R.string.my_name),
        modifier = Modifier
            .padding(top = 40.dp, bottom = 40.dp)
            .size(190.dp)
            .clip(CircleShape),
    )
}

@Composable
fun Greeting()
{
    val context = LocalContext.current
    val userInput = remember { mutableStateOf("") }
    val text= remember { mutableStateOf("") }
    val myImage= remember { mutableStateOf(R.drawable.me) }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        userInput(inputText = userInput.value, changed = { userInput.value = it })
        MyImage(image = myImage.value)
        textHandlerBtn(userInput.value)
        {
            text.value = userInput.value

            if (userInput.value.isNotEmpty()) {
                myImage.value = R.drawable.me_1
            }
            else{
                Toast.makeText(context, "Insert Bio first!.", Toast.LENGTH_LONG).show()
            }
        }
        Box(
            contentAlignment= Alignment.Center,
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
                .background(lab2TertiaryColor)
                .padding(all = 10.dp)
        )
        {
            displayText(sampleText = text.value)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Lab2Theme {
        Greeting()
    }
}