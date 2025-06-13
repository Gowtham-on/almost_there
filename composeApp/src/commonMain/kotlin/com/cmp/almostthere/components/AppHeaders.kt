package com.cmp.almostthere.components

import almostthere.composeapp.generated.resources.Res
import almostthere.composeapp.generated.resources.add_icon
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

@Composable
fun AppHeader(
    title: String,
    showLeftIcon: Boolean = false,
    showRightIcon: Boolean = false,
    onLeftIconClick: () -> Unit = {},
    onRightIconClick: () -> Unit = {},
    modifier: Modifier = Modifier,
    leftIcon: DrawableResource? = null,
    rightIcon: DrawableResource? = null,
) {
    Box(modifier = modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = MaterialTheme.colorScheme.background)
                .padding(top = 5.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            if (showLeftIcon)
                Image(
                    alignment = Alignment.CenterStart,
                    painter = painterResource(leftIcon ?: Res.drawable.add_icon),
                    contentDescription = "add_icon",
                    modifier = Modifier
                        .clickable(
                            onClick = onLeftIconClick
                        )
                        .padding(vertical = 10.dp)
                        .padding(end = 10.dp),
                    colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.surface)
                )
            else {
                Spacer(modifier = Modifier.width(48.dp))
            }
            if (showRightIcon)
                Image(
                    alignment = Alignment.CenterEnd,
                    painter = painterResource(rightIcon ?: Res.drawable.add_icon),
                    contentDescription = "add_icon",
                    modifier = Modifier
                        .clickable(
                            onClick = onRightIconClick,
                        )
                        .padding(10.dp),
                    colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.surface)
                )
            else {
                Spacer(modifier = Modifier.width(48.dp))
            }
        }
        Text(
            title,
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.surface,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth().padding(top = 5.dp)
        )
    }

}