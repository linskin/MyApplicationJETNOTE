//package com.example.myapplicationjetnote

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import kotlin.math.roundToInt

data class Note(val content: String, val timestamp: String)

@Composable
fun NoteListScreen() {
    val textState = rememberSaveable { mutableStateOf("") }
    var notes by remember { mutableStateOf(listOf<Note>()) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        TextFieldWithHintAndSelection(textState)
        Spacer(modifier = Modifier.height(16.dp))
        NoteList(notes = notes)
        Spacer(modifier = Modifier.height(16.dp))
        SaveButton (onSave = {
            val currentTime = LocalDateTime.now()
            val formattedTime = currentTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
            val newNote = Note(content = textState.value, timestamp = formattedTime)
            notes = notes + newNote
        },textState = textState)
    }
}

@Composable
fun NoteList(notes: List<Note>) {
    Column {
        Text("Note List:")
        notes.forEach { note ->
            Text("${note.timestamp}: ${note.content}")
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SaveButton(onSave: (String) -> Unit,textState: MutableState<String>) {
    val keyboardController = LocalSoftwareKeyboardController.current
    var text by remember { mutableStateOf("") }

    Button(
        onClick = {
            onSave(text)
            text = ""
            keyboardController?.hide()
        },
        modifier = Modifier.fillMaxWidth()
    ) {
        Text("Save Note")
    }
}

@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterial3Api::class)
@Composable
fun TextFieldWithHintAndSelection(textState: MutableState<String>) {

    val keyboardController = LocalSoftwareKeyboardController.current
    LocalDensity.current.density

    TextField(
        value = textState.value,
        onValueChange = {
            textState.value = it
        },
        placeholder = {
                Text("Enter your text")
        },
        singleLine = true,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Done
        ),
        keyboardActions = KeyboardActions(
            onDone = {
                keyboardController?.hide()
            }
        ),
        modifier = Modifier
            .fillMaxWidth(),
        colors = TextFieldDefaults.textFieldColors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
        ),
        textStyle = TextStyle.Default.copy(color = LocalContentColor.current)
    )
}

@Preview(showBackground = true)
@Composable
fun NoteListScreenPreview() {
    NoteListScreen()
}
