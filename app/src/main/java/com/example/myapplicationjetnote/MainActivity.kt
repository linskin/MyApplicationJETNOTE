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
import androidx.compose.foundation.layout.height
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
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
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

private var email : String = "linskin"
private var password : String = "123"
private var isPasswordVisible : Boolean = false
private var text: String = ""

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
private fun LoginScreen(onContinueClicked: () -> Unit) {
//    var email by remember { mutableStateOf("") }
//    var password by remember { mutableStateOf("") }
//    var isPasswordVisible by remember { mutableStateOf(false) }

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
            onValueChange = { email = it },
            label = { Text("邮箱") },
            leadingIcon = { Icon(imageVector = Icons.Default.Email, contentDescription = "邮箱") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        )

        // 登录密码输入框
        OutlinedTextField(
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
fun MainScreen(onContinueClicked: () -> Unit) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { myTopBar()},
        floatingActionButtonPosition = FabPosition.Center,
        floatingActionButton = { myFloatingActionButton(onContinueClicked) },
        containerColor = Color(0xC4E9DCFE),
        content = { myContent()},
    )
}

@Composable
fun myContent() {
    Greetings()
}

@Composable
fun CardDemo(item: String) {
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
                    append("welcome!")
                    withStyle(style = SpanStyle(fontWeight = FontWeight.W900, color = Color(0xFF4552B8))
                    ) {
                        append("$item")
                    }
                }
            )
            Text(
                buildAnnotatedString {
                    append("Now you are in the ")
                    withStyle(style = SpanStyle(fontWeight = FontWeight.W900)) {
                        append("Card")
                    }
                    append(" section")
                }
            )
        }
    }
}

@Composable
private fun Greetings(items: List<String> = listOf("World", "Jetpack Compose", "Kotlin")) {
    LazyColumn(
        modifier = Modifier.padding(top = 70.dp)
    ) {
        items(items = items) { item ->
            CardDemo(item = item)

        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun myTopBar(){
    TopAppBar(title = {Text("$email")},
        navigationIcon = {
            IconButton(onClick = { backIconOnClick() }) {
                Icon(Icons.Default.ArrowBack, contentDescription = "返回")
            }
        },
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
    TODO("Not yet implemented")
}

private fun navigationIconOnClick() {
    TODO("Not yet implemented")
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
private fun floatingActionButtonOnClick(onContinueClicked : () -> Unit) {
    TODO("Not yet implemented")
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
//@Preview
@Composable
private fun NotebookScreen() {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { notBookTopBar()},
//        floatingActionButtonPosition = FabPosition.Center,
        floatingActionButton = { noteBookFloatingActionButton() },
        containerColor = Color(0xC4E9DCFE),
        content = { noteBookContent()},
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun noteBookContent() {
    TextField(
        value = text,
        onValueChange = { text = it },
        label = { Text("笔记标题") },
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(horizontal = 8.dp)
            .padding(top = 73.dp)
            .padding(bottom = 8.dp)
    )
}
@Composable
fun noteBookFloatingActionButton() {
    FloatingActionButton(onClick = {saveButtonOnClick()}){
        Icon(Icons.Default.Done, contentDescription = "保存")
    }
//    FloatingActionButton(onClick = {clearButtonOnClick()}){
//        Icon(Icons.Default.Delete, contentDescription = "清空")
//    }
}

fun clearButtonOnClick() {
    TODO("Not yet implemented")
}

fun saveButtonOnClick() {
    TODO("Not yet implemented")
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun notBookTopBar() {
    TopAppBar(
        title = { Text(if (false) "修改笔记" else "新建笔记") },
        navigationIcon = {
            IconButton(onClick = { backIconOnClick() }) {
                Icon(Icons.Default.ArrowBack, contentDescription = "返回")
            }
        },
        actions = {
            IconButton(onClick = { deleteIconOnClick() }) {
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

fun deleteIconOnClick() {
    TODO("Not yet implemented")
}
@Preview
@Composable
private fun MyApp(modifier: Modifier = Modifier) {
    // 创建一个名为shouldShowOnboarding的可变状态变量，使用rememberSaveable进行保存
    var shouldShowOnboarding by rememberSaveable { mutableStateOf(true) }
    var shouldShowNotebookScreen by rememberSaveable { mutableStateOf(false) }
    // 创建一个Surface组件，并使用modifier作为修饰符
    Surface(modifier) {
        // 如果shouldShowOnboarding为true，则显示OnboardingScreen组件，并传入一个lambda函数作为点击事件，点击事件会将shouldShowOnboarding的值设为false
        if (shouldShowNotebookScreen){
            if (shouldShowOnboarding) {
                LoginScreen(onContinueClicked = { shouldShowOnboarding = false })
            } else {
                // 如果shouldShowOnboarding为false，则显示Greetings组件
//            Greetings()
                MainScreen(onContinueClicked = { shouldShowNotebookScreen = true })
            }
        }else{
            NotebookScreen()
        }
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