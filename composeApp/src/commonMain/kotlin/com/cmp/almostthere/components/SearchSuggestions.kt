package com.cmp.almostthere.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.cmp.almostthere.model.MapDetails
import com.cmp.almostthere.network.searchPlaces
import com.cmp.almostthere.utils.Debouncer
import com.cmp.almostthere.viewmodel.TriggerViewmodel
import kotlinx.coroutines.launch

val API_KEY = "AIzaSyDY72UzWRsAJy_JG00KI58eJZxck06uM1k"

@Composable
fun SearchWithSuggestions(
    placeholder: String,
    contentDesc: String,
    showLeadingIcon: Boolean,
    viewmodel: TriggerViewmodel
) {
    var searchText by remember { mutableStateOf(TextFieldValue("")) }
    var showSuggestions by remember { mutableStateOf(false) }
    val debouncer = remember { Debouncer(debounceTimeMs = 500) }
    val scope = rememberCoroutineScope()
    var places by remember { mutableStateOf<List<MapDetails>>(emptyList()) }
    val focusRequester = remember { FocusRequester() }



    Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
        SearchBar(
            text = searchText.text,
            onTextChange = {
                searchText = TextFieldValue(it, TextRange(it.length))
                debouncer.submit(scope) {
                    scope.launch {
                        places = searchPlaces(it, API_KEY)
                        showSuggestions = places.isNotEmpty()
                    }
                }
            },
            placeholder = placeholder,
            contentDesc = contentDesc,
            showLeadingIcon = showLeadingIcon,
            modifier = Modifier.focusRequester(focusRequester)
        )

        // Suggestions dropdown
        Box(
            modifier = Modifier.padding(horizontal = 14.dp)
        ) {
            DropdownMenu(
                expanded = showSuggestions && places.isNotEmpty(),
                onDismissRequest = { showSuggestions = false },
                modifier = Modifier.height(300.dp).fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                containerColor = MaterialTheme.colorScheme.secondary,
            ) {
                places.forEachIndexed { index, place ->
                    Column {
                        DropdownMenuItem(
                            text = {
                                Column(
                                    verticalArrangement = Arrangement.spacedBy(10.dp)
                                ) {
                                    Text(
                                        place.name,
                                        style = MaterialTheme.typography.bodyMedium,
                                        color = MaterialTheme.colorScheme.surface,
                                        maxLines = 1,
                                        overflow = TextOverflow.Ellipsis
                                    )
                                    Text(
                                        place.address,
                                        style = MaterialTheme.typography.labelSmall,
                                        color = MaterialTheme.colorScheme.surface,
                                    )

                                }
                            },
                            onClick = {
                                searchText =
                                    TextFieldValue(place.name, TextRange(place.name.length))
                                showSuggestions = false
                                focusRequester.requestFocus()
                                viewmodel.setUserDestination(places[index])
                            }
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(1.dp)
                                .background(color = MaterialTheme.colorScheme.tertiary.copy(alpha = 0.3f))
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                    }
                }
            }
        }
    }
}