package com.example.dropdown.items

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


enum class FontOption(val displayName: String, val fontFamily: FontFamily) {
    Default("Predeterminado", FontFamily.Default),
    Serif("Serif", FontFamily.Serif),
    SansSerif("Sans Serif", FontFamily.SansSerif),
    Monospace("Monoespaciado", FontFamily.Monospace);
}

enum class ColorOption(val displayName: String, val color: Color) {
    Black("Negro", Color.Black),
    Red("Rojo", Color.Red),
    Blue("Azul", Color.Blue),
    Green("Verde", Color.Green);
}

@Composable
fun CustomTextEditor() {
    var text by remember { mutableStateOf("") }
    var selectedFontTemp by remember { mutableStateOf(FontOption.Default) }
    var selectedColorTemp by remember { mutableStateOf(ColorOption.Black) }
    var selectedFont by remember { mutableStateOf(FontOption.Default) }
    var selectedColor by remember { mutableStateOf(ColorOption.Black) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        TextField(
            value = text,
            onValueChange = { text = it },
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
                .border(
                    width = 1.dp,
                    color = Color.LightGray,
                    shape = RoundedCornerShape(4.dp)
                ),
            textStyle = TextStyle(
                color = selectedColor.color,
                fontFamily = selectedFont.fontFamily,
                fontSize = 16.sp
            ),
            placeholder = { Text("Escribe tu texto aquí") }
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(vertical = 4.dp)
        ) {
            Text(
                text = "Fuente:",
                modifier = Modifier.width(80.dp)
            )

            FontDropdown(
                selectedFont = selectedFontTemp,
                onFontSelected = { selectedFontTemp = it }
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(vertical = 4.dp)
        ) {
            Text(
                text = "Color:",
                modifier = Modifier.width(80.dp)
            )

            ColorDropdown(
                selectedColor = selectedColorTemp,
                onColorSelected = { selectedColorTemp = it }
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                selectedFont = selectedFontTemp
                selectedColor = selectedColorTemp
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Aplicar cambios")
        }
    }
}

@Composable
fun FontDropdown(
    selectedFont: FontOption,
    onFontSelected: (FontOption) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .border(1.dp, Color.LightGray, RoundedCornerShape(4.dp))
                .clickable { expanded = true }
                .padding(16.dp)
        ) {
            Text(
                text = selectedFont.displayName,
                modifier = Modifier.weight(1f)
            )
            Icon(
                imageVector = Icons.Default.ArrowDropDown,
                contentDescription = "Seleccionar fuente"
            )
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.fillMaxWidth()
        ) {
            FontOption.values().forEach { fontOption ->
                DropdownMenuItem(
                    text = {
                        Text(
                            text = fontOption.displayName,
                            fontFamily = fontOption.fontFamily
                        )
                    },
                    onClick = {
                        onFontSelected(fontOption)
                        expanded = false
                    }
                )
            }
        }
    }
}

@Composable
fun ColorDropdown(
    selectedColor: ColorOption,
    onColorSelected: (ColorOption) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .border(1.dp, Color.LightGray, RoundedCornerShape(4.dp))
                .clickable { expanded = true }
                .padding(16.dp)
        ) {
            Box(
                modifier = Modifier
                    .width(20.dp)
                    .height(20.dp)
                    .background(selectedColor.color)
                    .border(1.dp, Color.LightGray)
            )

            Spacer(modifier = Modifier.width(8.dp))

            Text(
                text = selectedColor.displayName,
                modifier = Modifier.weight(1f)
            )

            Icon(
                imageVector = Icons.Default.ArrowDropDown,
                contentDescription = "Seleccionar color"
            )
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.fillMaxWidth()
        ) {
            ColorOption.values().forEach { colorOption ->
                DropdownMenuItem(
                    text = {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Box(
                                modifier = Modifier
                                    .width(20.dp)
                                    .height(20.dp)
                                    .background(colorOption.color)
                                    .border(1.dp, Color.LightGray)
                            )

                            Spacer(modifier = Modifier.width(8.dp))

                            Text(text = colorOption.displayName)
                        }
                    },
                    onClick = {
                        onColorSelected(colorOption)
                        expanded = false
                    }
                )
            }
        }
    }
}