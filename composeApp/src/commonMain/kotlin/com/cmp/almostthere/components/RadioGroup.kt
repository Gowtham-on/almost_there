package com.cmp.almostthere.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun RadioGroup(
    modifier: Modifier = Modifier,
    radioItems: List<RadioItem> = listOf<RadioItem>()
) {

    LazyColumn(modifier = modifier) {
        items(radioItems.size) {
            GetRadioItems(radioItems[it])
        }
    }

}

@Composable
fun GetRadioItems(radioItem: RadioItem) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxWidth().clickable(
            onClick = radioItem.onClick
        ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            radioItem.name,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.surface,
        )
        RadioButton(
            selected = radioItem.isSelected,
            onClick = radioItem.onClick,
            colors = RadioButtonDefaults.colors(
                selectedColor = MaterialTheme.colorScheme.surface,
                unselectedColor = MaterialTheme.colorScheme.surface,
            )
        )
    }
}

data class RadioItem(
    val name: String,
    var isSelected: Boolean,
    var onClick: () -> Unit
)

