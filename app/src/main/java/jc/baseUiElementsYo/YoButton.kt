package jc.baseUiElementsYo

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ButtonElevation
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

/**
 * YoButton
 *
 *  params :
 *      1. onClick : лямбда выполняемая при нажатии
 *      2. modifier : модифаер)
 *      3. enabled: в случае если кнопка выключена будут применены цвета указанные в ButtonColors disabled и onClick не будет выполняться
 *      если включена все как обычно цвета дефолтные и лямбда выполняется
 *      4. colors: принимает обьект ButtonColors который принимает в себя 4 параметра
 *          contentColor, containerColor, disabledContentColor, disabledContainerColor
 *      5. interactionSource: прикольный обработчик нажатия на клавишу можно увеличивать кнопку в размерах это прикольно )
 *      6. shape: параметр принимающий
 *      7. contentPadding принимает в себя класс PaddingValues у которого есть три конструктора принимающие
 *      следущие значения:
 *          1. all падинг будет применен по всем направлениям
 *          2. horizontal, vertical падинг будет применен вертикально или горизонтально
 *          3. start, end, top, bottom падинг будет применен по конкретному направлению
 *      8. elevation: да типо размер тени нажатой и не нажатой клавиши принимает
 *      ButtonDefault.buttonElevation(
 *          defaultElevation = dp,
 *          pressedElevation = dp,
 *          hoveredElevation = dp, при наведении хз) с телека штоли=)
 *      )
 *      9. border принимает класс BorderStroke который принимает ширину бордера и цвет
 *      10. content ну и так понятно это лямбда container того что будет внутри кнопки
 *
 *
 */

@Composable
@Preview(showBackground = true)
fun YoButtonPreview() {
    YoButton()
}

@Composable
fun YoButton() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val enabled = remember { mutableStateOf(true) }
        val enabled1 = remember { mutableStateOf(true) }
        val interactionSource = remember { MutableInteractionSource() }
        val isPressed by interactionSource.collectIsPressedAsState()
        val size = if(isPressed) 110.dp else 100.dp
        val buttonColor by animateColorAsState(if (isPressed) Color.Green else Color.Blue, label = "")

        Button(
            onClick = {
                enabled1.value = !enabled1.value
            },
            contentPadding = PaddingValues(
                all = 20.dp
            ),
            shape = CircleShape,
            border = BorderStroke(width = 2.dp, color = Color.Red),
            elevation = ButtonDefaults.buttonElevation(
            ),
            enabled = enabled.value,
            colors = ButtonColors(
                contentColor = Color.Red,
                containerColor = Color.White,
                disabledContentColor = Color.White,
                disabledContainerColor = Color.Red
            )
        ) {
            Text("main")
        }

        Button(
            modifier = Modifier.size(size),
            onClick = {
                enabled.value = !enabled.value
            },
            shape = RoundedCornerShape(20.dp),
            enabled = enabled1.value,
            interactionSource = interactionSource,
            colors = ButtonColors(
                contentColor = Color.White,
                containerColor = buttonColor,
                disabledContentColor = Color.White,
                disabledContainerColor = buttonColor
            )
        ) {
            Text("enable is ${enabled1.value}")
        }
    }
}