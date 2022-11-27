@file:OptIn(ExperimentalFoundationApi::class)

package com.example.lab3.ui.compose
import android.content.Context
import android.util.DisplayMetrics
import android.widget.Toast
import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.lab3.R
import com.example.lab3.model.Todo
import com.example.lab3.model.TodoViewModel
import com.example.lab3.ui.theme.lab3SecondaryDarkColor
import com.example.lab3.ui.theme.lab3TertiaryColor
import java.util.*


@Composable
fun TodoScreen()
{
    val viewModel: TodoViewModel = viewModel()
    val context = LocalContext.current
    var showDialog by remember { mutableStateOf(false) }

    Scaffold(
        backgroundColor = MaterialTheme.colors.surface,
        floatingActionButton = {
            FloatingActionButton(onClick = {showDialog = true})
            {
                Icon(Icons.Filled.Add, contentDescription = "",tint = Color.White,)
            }
        }
    ) {
        if (showDialog)
        {
            addTodoDialog(context, dismissDialog = { showDialog = false }, { viewModel.add(it) })
        }
        Column {
            Text(text = stringResource(id = R.string.description), modifier = Modifier.padding(20.dp))
            LazyColumn(
                contentPadding = PaddingValues(
                    vertical = 8.dp,
                    horizontal = 8.dp
                )
            )
            {
                items(viewModel.todoList, key = { todo -> todo.todoId }) { todo ->
                    TodoItem(item = todo, context) { viewModel.deleteTodo(it) }
                }
            }
        }

    }
}

@Composable
fun addTodoDialog(context: Context, dismissDialog:() -> Unit, addTodo: (Todo) -> Unit)
{
    var titleTextField by remember { mutableStateOf("") }
    var descriptionTextField by remember { mutableStateOf("") }
    var completed by remember { mutableStateOf(false) }

    AlertDialog(
        onDismissRequest = { dismissDialog},
        title={Text(text = stringResource(id =R.string.addTodo ), style = MaterialTheme.typography.h6)},

        text = {
            Column(modifier = Modifier.padding(top=20.dp)) {
                OutlinedTextField(label = {Text(text=stringResource(id = R.string.todoTitle))}, value = titleTextField, onValueChange = {titleTextField=it})
                Spacer(modifier = Modifier.height(10.dp))
                OutlinedTextField(label = {Text(text=stringResource(id = R.string.todoDesc))},value = descriptionTextField, onValueChange = {descriptionTextField=it})
            }
        },
        confirmButton = {
            Button(onClick = {
                if(titleTextField.isNotEmpty()) {
                    val newID = UUID.randomUUID().toString()
                    addTodo(Todo(newID, titleTextField, descriptionTextField,completed))
                    Toast.makeText(
                        context,
                        context.resources.getString(R.string.todoAdded),
                        Toast.LENGTH_SHORT
                    ).show()
                }
                dismissDialog()
            })
            {
                Text(text = stringResource(id = R.string.add))
            }
        },dismissButton = {
            Button(onClick = {
                dismissDialog()
            }) {
                Text(text = stringResource(id = R.string.cancel))
            }
        }
    )
}

@Composable
fun deleteTodoDialog(context: Context, dismissDialog:() -> Unit, item: Todo, deleteBook: (Todo) -> Unit)
{
    AlertDialog(
        onDismissRequest = { dismissDialog},
        title={Text(text = stringResource(id = R.string.delete), style = MaterialTheme.typography.h6)},
        confirmButton = {
            Button(onClick = {
                deleteBook(item)
                Toast.makeText(
                    context,
                    context.resources.getString(R.string.deleteTodo),
                    Toast.LENGTH_SHORT
                ).show()
                dismissDialog()
            })
            {
                Text(text = stringResource(id = R.string.yes))
            }
        },dismissButton = {
            Button(onClick = {
                dismissDialog()
            }) {
                Text(text = stringResource(id = R.string.no))
            }
        }
    )
}

@Composable
fun SimpleCheckboxComponent()
{
    val checkedState = remember { mutableStateOf(false) }
    val context = LocalContext.current
    val displayMetrics: DisplayMetrics = context.resources.displayMetrics
    val dpWidth = (displayMetrics.widthPixels / displayMetrics.density)*0.2

    Row {
        Checkbox(
            checked = checkedState.value,
            modifier = Modifier
                .padding(16.dp)
                .width(dpWidth.dp),
            onCheckedChange = { checkedState.value = it },
        )
    }
}

@Composable
fun TodoItem(item: Todo, context: Context, deleteTodo: (Todo) -> Unit)
{
    var showDeleteDialog by remember { mutableStateOf(false) }

    Card(
        elevation = 5.dp,
        shape = RoundedCornerShape(10.dp),
        backgroundColor = lab3TertiaryColor,
        contentColor = MaterialTheme.colors.onPrimary,
        border = BorderStroke(2.dp, color = MaterialTheme.colors.primaryVariant),
        modifier = Modifier
            .padding(5.dp)
            .fillMaxWidth()
            .pointerInput(Unit) {
                detectTapGestures(onLongPress = { showDeleteDialog = true })
            }
    ) {
        Row{
            val context = LocalContext.current
            val displayMetrics: DisplayMetrics = context.resources.displayMetrics
            val dpWidth = (displayMetrics.widthPixels / displayMetrics.density)*0.7
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .width(dpWidth.dp)
            ) {
                Text(text = item.title, style = MaterialTheme.typography.body1, modifier = Modifier.padding(bottom = 20.dp))
                Text(text = item.description, style = MaterialTheme.typography.h6)
            }
            SimpleCheckboxComponent()
        }
    }
    if (showDeleteDialog){
        deleteTodoDialog(context, dismissDialog = {showDeleteDialog = false}, item, deleteTodo)
    }
}
