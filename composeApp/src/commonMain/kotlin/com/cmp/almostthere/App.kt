package com.cmp.almostthere

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.cmp.almostthere.navigation.NavGraph
import com.cmp.almostthere.theme.AlmostThereTheme
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    AlmostThereTheme {
        Scaffold {
            Surface(
                color = MaterialTheme.colorScheme.background,
                modifier = Modifier.padding(
                    top = it.calculateTopPadding(),
                )
            ) {
                val navController = rememberNavController()
                NavGraph(navController)
            }
        }
    }
}

