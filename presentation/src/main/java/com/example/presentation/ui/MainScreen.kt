package com.example.presentation.ui

import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.presentation.MainContract
import com.example.presentation.MainViewModel
import com.example.presentation.SubFirstActivity
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(viewModel: MainViewModel) {
    val state = viewModel.collectAsState()  // 상태 구독
    val context = LocalContext.current

    viewModel.collectSideEffect { effect ->
        when (effect) {
            is MainContract.Effect.ShowToast -> {
                Toast.makeText(context, effect.message, Toast.LENGTH_SHORT).show()
            }

            is MainContract.Effect.StartResultActivity -> {
                context.startActivity(Intent(context, SubFirstActivity::class.java))
            }
        }
    }

    SearchBar(viewModel, state)

}

@Composable
fun SearchBar(viewModel: MainViewModel, state: State<MainContract.State>) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        contentAlignment = Alignment.Center,
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = "DNF MVI Practice",
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onBackground
            )
            Text(
                text = "검색할 캐릭터의 서버와 캐릭터명을 입력해주세요.",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onBackground
            )

            Spacer(modifier = Modifier.height(16.dp))

            Row(verticalAlignment = Alignment.CenterVertically) {
                val id = remember { mutableStateOf(state.value.id) }
                val isDropDownExpanded = remember { mutableStateOf(false) }

                fun onClickDropDownMenuItem(server: String) {
                    viewModel.updateServer(server)
                    isDropDownExpanded.value = false
                }

                Box() {
                    TextField(
                        modifier = Modifier
                            .width(130.dp)
                            .clickable { isDropDownExpanded.value = true },
                        value = state.value.server,
                        enabled = false,
                        onValueChange = { },
                        trailingIcon = {
                            Icon(
                                imageVector = Icons.Default.ArrowDropDown,
                                tint = Color.Gray,
                                contentDescription = "dropdown"
                            )
                        }
                    )
                }

                DropdownMenu(
                    expanded = isDropDownExpanded.value, onDismissRequest = {
                        /* TODO */
                    }) {
                    DropdownMenuItem(
                        text = { "카인" },
                        onClick = { onClickDropDownMenuItem("카인") })
                    DropdownMenuItem(
                        text = { "프레이" },
                        onClick = { onClickDropDownMenuItem("프레이") })
                }

                Spacer(modifier = Modifier.width(8.dp))

                TextField(
                    modifier = Modifier.width(200.dp),
                    value = id.value,
                    onValueChange = { newValue ->
                        id.value = newValue
                        viewModel.updateId(newValue)
                    }
                )

                Spacer(modifier = Modifier.width(8.dp))

                Button(onClick = {
                    viewModel.onEvent(MainContract.Event.Search(state.value.server, state.value.id))
                }) {
                    Text(text = "submit")
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

