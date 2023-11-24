package com.example.myapplicationjetnote

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.ContentAlpha
import com.example.myapplicationjetnote.ui.theme.MyApplicationJETNOTETheme
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

private  var useremail = ""
//private var password : String = "123"
//private var isPasswordVisible : Boolean = false
data class Note(val content: String, val timestamp: String)
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationJETNOTETheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    MyApp()
                }
            }
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(onContinueClicked: () -> Unit) {
    var email by remember { mutableStateOf("linskin@foxmail.com") }
    var password by remember { mutableStateOf("123456") }
    var isPasswordVisible by remember { mutableStateOf(false) }

//    val density = LocalDensity.current.density
    val keyboardController = LocalSoftwareKeyboardController.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // 设置公司 logo 图片
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_foreground),
            contentDescription = "App Logo",
            modifier = Modifier
                .size(100.dp)
                .clipToBounds()
                .clip(RectangleShape)
                .background(MaterialTheme.colorScheme.primary)
                .clickable {
                    // 处理 logo 点击事件
                }
                .padding(16.dp)
        )

        // 登录邮箱输入框
        OutlinedTextField(
            value = email,
            singleLine = true,
            onValueChange = { email = it },
            label = { Text("邮箱") },
            leadingIcon = { Icon(imageVector = Icons.Default.Email, contentDescription = "邮箱") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        )

        // 登录密码输入框
        OutlinedTextField(
            singleLine = true,
            value = password,                               // 文本框显示的初始值
            onValueChange = { password = it },               // 当文本框的值改变时调用的回调函数
            label = { Text("密码") },                        // 文本框的标签
            leadingIcon = {
                IconButton(
                    onClick = { isPasswordVisible = !isPasswordVisible },
                    modifier = Modifier.alpha(ContentAlpha.medium)
                ) {
                    Icon(
                        imageVector = if (isPasswordVisible) Icons.Default.Close else Icons.Default.Check,
                        contentDescription = if (isPasswordVisible) "隐藏密码" else "显示密码"
                    )
                }
            },                                       // 引用一个带有点击事件的图标按钮
            visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),   // 视觉转换，如果 `isPasswordVisible` 为 `true` 则使用默认视觉转换，否则使用密码视觉转换
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),   // 调出键盘时的选项，键盘类型为密码类型
            modifier = Modifier
                .fillMaxWidth()                           // 填充最大宽度
                .padding(vertical = 8.dp)                  // 内边距
        )


        // 登录按钮
        Button(
            onClick = {
                // 处理登录按钮点击事件
                if (email.isEmpty() || password.isEmpty()) {
                    // 如果邮箱或密码为空，则显示错误提示
                    keyboardController?.show()
                } else {
                    // 否则，显示欢迎信息
//                    Toast.makeText(currentCompositionLocalContext, "欢迎 $email", Toast.LENGTH_SHORT).show()
                    useremail = email
                    onContinueClicked()
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        ) {
            Text("登录")
        }

        // 忘记密码按钮
        TextButton(
            onClick = {
                // 处理忘记密码点击事件
            },
            modifier = Modifier
                .align(Alignment.End)
        ) {
            Text("忘记密码?")
        }
    }
}

//@Preview
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
//fun ScaffoldDemo() {
//    Scaffold(
//        modifier = Modifier.fillMaxSize(),
//        topBar = { myTopBar()},
//        floatingActionButtonPosition = FabPosition.Center,
//        floatingActionButton = { myFloatingActionButton() },
//        containerColor = Color(0xC4E9DCFE),
//        content = { myContent()},
//    )
//}
fun MainScreen(onContinueClicked: () -> Unit, notes: MutableState<List<Note>>) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { myTopBar()},
        floatingActionButtonPosition = FabPosition.Center,
        floatingActionButton = { myFloatingActionButton(onContinueClicked) },
        containerColor = Color(0xC4E9DCFE),
        content = { myContent(notes)},
    )
}

@Composable
fun myContent(notes: MutableState<List<Note>>) {
    Greetings(notes)
}

@Composable
fun CardDemo(item: Note) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp, 3.dp)
            .clickable { },
    ) {
        Column(
            modifier = Modifier.padding(15.dp)
        ) {
            Text(
                buildAnnotatedString {
                    withStyle(style = SpanStyle(fontWeight = FontWeight.W900, color = Color(0xFF4552B8))
                    ) {
                        append(item.timestamp)
                    }
                }
            )
            Text(
                buildAnnotatedString {
                    withStyle(style = SpanStyle(fontWeight = FontWeight.W900)) {
                        append(item.content)
                    }
                }
            )
        }
    }
}

@Composable
private fun Greetings(items: MutableState<List<Note>>) {
    LazyColumn(
        modifier = Modifier.padding(top = 70.dp)
    ) {
        items(items = items.value) { item ->
            CardDemo(item = item)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun myTopBar(){
    val email= useremail
    TopAppBar(title = {Text("$email")},
        actions = {
            IconButton(onClick = { actionIconOnClick() }) {
                Icon(Icons.Default.Search, contentDescription = "搜索")
            }
        },
        modifier = Modifier.fillMaxWidth(),
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            titleContentColor = Color.Black,
            containerColor = Color(0xFFE9DCFE),
        ),
    )
}

fun backIconOnClick() {

}

private fun actionIconOnClick() {
    TODO("Not yet implemented")
}

@Composable
private fun myFloatingActionButton(onContinueClicked: () -> Unit){
    FloatingActionButton(onClick = {onContinueClicked()}){
        Icon(Icons.Default.Add, contentDescription = "添加")
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun NotebookScreen(onContinueClicked: () -> Unit, notes: MutableState<List<Note>>) {
    val noteTextState = rememberSaveable { mutableStateOf("") }
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { notBookTopBar(onContinueClicked,noteTextState)},
//        floatingActionButtonPosition = FabPosition.Center,
        floatingActionButton = { noteBookFloatingActionButton(onContinueClicked,noteTextState,notes) },
        containerColor = Color(0xC4E9DCFE),
        content = { noteBookContent(noteTextState)},
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun noteBookContent(textState: MutableState<String>) {
    TextField(
        value = textState.value,
        onValueChange = { textState.value = it},
        label = { Text("note") },
        placeholder = { Text("请输入笔记内容") },
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(horizontal = 8.dp)
            .padding(top = 73.dp)
            .padding(bottom = 8.dp)
    )
}
@Composable
fun noteBookFloatingActionButton(
    onContinueClicked: () -> Unit,
    textState: MutableState<String>,
    notes: MutableState<List<Note>>
) {
    FloatingActionButton(onClick = {onContinueClicked();saveButtonOnClick(textState, notes)}){
        Icon(Icons.Default.Done, contentDescription = "保存")
    }
}



fun saveButtonOnClick(textState: MutableState<String>, notes: MutableState<List<Note>>) {
    //在此处执行将信息保存至数据库的操作
    //将数据加入数据库
    //数量类型：日期（直接调用），文本内容
    val currentTime = LocalDateTime.now()
    val formattedTime = currentTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
    val newNote = Note(content = textState.value, timestamp = formattedTime)
    notes.value += newNote
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun notBookTopBar(onContinueClicked: () -> Unit,textState: MutableState<String>) {
    TopAppBar(
        title = { Text(if (false) "修改笔记" else "新建笔记") },
        navigationIcon = {
            IconButton(onClick = { onContinueClicked() }) {
                Icon(Icons.Default.ArrowBack, contentDescription = "返回")
            }
        },
        actions = {
            IconButton(onClick = { clearButtonOnClick(textState) }) {
                Icon(Icons.Default.Delete, contentDescription = "清空")
            }
        },
        modifier = Modifier.fillMaxWidth(),
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            titleContentColor = Color.Black,
            containerColor = Color(0xFFE9DCFE),
        ),
    )
}

fun clearButtonOnClick(textState: MutableState<String>) {
    textState.value = ""
}
@Preview
@Composable
private fun MyApp(modifier: Modifier = Modifier) {
    // 创建一个名为shouldShowOnboarding的可变状态变量，使用rememberSaveable进行保存
//    var shouldShowOnboarding by rememberSaveable { mutableStateOf(true) }
//    var shouldShowNotebookScreen by rememberSaveable { mutableStateOf(true) }
    val notes = rememberSaveable { mutableStateOf(listOf<Note>()) }
    var shouldShowOnboarding by rememberSaveable { mutableStateOf(true) }
    var shouldShowNotebookScreen by rememberSaveable { mutableStateOf(true) }
    // 创建一个Surface组件，并使用modifier作为修饰符
    Surface(modifier) {
        // 如果shouldShowOnboarding为true，则显示OnboardingScreen组件，并传入一个lambda函数作为点击事件，点击事件会将shouldShowOnboarding的值设为false
        if (shouldShowNotebookScreen){
            if (shouldShowOnboarding) {
                LoginScreen(onContinueClicked = { shouldShowOnboarding = false })
            } else {
                // 如果shouldShowOnboarding为false，则显示Greetings组件
//            Greetings()
                MainScreen(onContinueClicked = { shouldShowNotebookScreen = !shouldShowNotebookScreen },notes = notes)
            }
        }else{
            NotebookScreen(onContinueClicked =  { shouldShowNotebookScreen = !shouldShowNotebookScreen},notes = notes)
        }
        }

    }




//深色预览
//@Preview(
//    showBackground = true,
//    widthDp = 320,
//    uiMode = UI_MODE_NIGHT_YES,
//    name = "Dark"
//)
//@Preview(showBackground = true, name = "MyAPP")
@Composable
fun MyAppPreview() {
    MyApplicationJETNOTETheme {
        MyApp(Modifier.fillMaxSize())
//        OnboardingScreen()
    }
}