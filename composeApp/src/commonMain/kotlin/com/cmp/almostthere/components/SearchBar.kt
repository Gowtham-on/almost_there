package com.cmp.almostthere.components

import almostthere.composeapp.generated.resources.Res
import almostthere.composeapp.generated.resources.magnifier_glass
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.painterResource

@Composable
fun SearchBar(
    text: String,
    placeholder: String,
    contentDesc: String,
    showLeadingIcon: Boolean,
    modifier: Modifier = Modifier,
    keyBoardType: KeyboardType = KeyboardType.Text,
    onTextChange: (String) -> Unit = {}
) {

    TextField(
        value = text,
        onValueChange = onTextChange,
        placeholder = {
            Text(
                placeholder,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.tertiary,
            )
        },
        keyboardOptions = KeyboardOptions(keyboardType = keyBoardType),
        leadingIcon = if (showLeadingIcon) {
            {
                Image(
                    painter = painterResource(Res.drawable.magnifier_glass),
                    colorFilter = ColorFilter.tint(color = MaterialTheme.colorScheme.tertiary),
                    contentDescription = contentDesc
                )
            }
        } else null,
        modifier = modifier.fillMaxWidth().clip(shape = RoundedCornerShape(16.dp)),
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = MaterialTheme.colorScheme.secondary,
            focusedContainerColor = MaterialTheme.colorScheme.secondary,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
            disabledTextColor = Color.Transparent,
            cursorColor = MaterialTheme.colorScheme.tertiary,
            focusedTextColor = MaterialTheme.colorScheme.surface,
            unfocusedTextColor = MaterialTheme.colorScheme.surface
        ),
    )
}