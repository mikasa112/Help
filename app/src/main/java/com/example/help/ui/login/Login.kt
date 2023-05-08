package com.example.help.ui.login

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.OutlinedTextField
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.example.help.R
import com.example.help.ui.lottie.LottieLoginView
import com.example.help.ui.lottie.LottieWorkingLoadingView
import com.example.help.ui.theme.components.HorizontalDottedProgressBar
import com.example.help.ui.theme.helpers.TextFieldDefaultsMaterial
import com.guru.fontawesomecomposelib.FaIcon
import com.guru.fontawesomecomposelib.FaIcons


/**
 * @author yuanan
 * @date 2023/5/7
 * @desc 登录组件
 */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginView(modifier: Modifier = Modifier) {
    Scaffold { paddingValues ->
        var account by remember {
            mutableStateOf(TextFieldValue(""))
        }
        var password by remember {
            mutableStateOf(TextFieldValue(""))
        }
        var passwordVisualTransformation by remember {
            mutableStateOf<VisualTransformation>(
                PasswordVisualTransformation()
            )
        }
        var hasError by remember {
            mutableStateOf(false)
        }
        val accountInteractionState = remember {
            MutableInteractionSource()
        }
        val passwordInteractionState = remember {
            MutableInteractionSource()
        }
        LazyColumn(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            item { LottieWorkingLoadingView(context = LocalContext.current) }
            item {
                Text(
                    text = stringResource(id = R.string.login_welcome),
                    style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.ExtraBold),
                    modifier = Modifier.padding(top = 8.dp)
                )
            }
            item {
                Text(
                    text = stringResource(id = R.string.login_label),
                    style = MaterialTheme.typography.labelMedium,
                    modifier = Modifier.padding(bottom = 12.dp)
                )
            }
            item {
                OutlinedTextField(
                    value = account,
                    leadingIcon = {
                        FaIcon(
                            faIcon = FaIcons.User,
                            tint = LocalContentColor.current.copy(alpha = LocalContentAlpha.current)
                        )
                    },
                    maxLines = 1,
                    isError = hasError,
                    colors = TextFieldDefaultsMaterial.outlinedTextFieldColors(),
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Next
                    ),
                    label = { Text(text = "用户名") },
                    onValueChange = { account = it },
//                    placeholder = { Text(text = "用户名") },
                    interactionSource = accountInteractionState,
                )
            }
            item {
                OutlinedTextField(
                    value = password,
                    leadingIcon = {
                        FaIcon(
                            faIcon = FaIcons.Key,
                            tint = LocalContentColor.current.copy(alpha = LocalContentAlpha.current)
                        )
                    },
                    trailingIcon = {
                        FaIcon(
                            faIcon = FaIcons.EyeSlash,
                            tint = LocalContentColor.current.copy(alpha = LocalContentAlpha.current),
                            modifier = Modifier.clickable(onClick = {
                                passwordVisualTransformation =
                                    if (passwordVisualTransformation != VisualTransformation.None) {
                                        VisualTransformation.None
                                    } else {
                                        PasswordVisualTransformation()
                                    }
                            })
                        )
                    },
                    colors = TextFieldDefaultsMaterial.outlinedTextFieldColors(),
                    maxLines = 1,
                    isError = hasError,
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Password,
                        imeAction = ImeAction.Done
                    ),
                    label = { Text(text = "密码") },
//                    placeholder = { Text(text = "12334444") },
                    onValueChange = {
                        password = it
                    },
                    interactionSource = passwordInteractionState,
                    visualTransformation = passwordVisualTransformation,
                )
            }
            item {
                var loading by remember {
                    mutableStateOf(false)
                }
                Button(
                    onClick = {
                        if (invalidInput(account.text, password.text)) {
                            hasError = true
                            loading = false
                        } else {
                            loading = true
                            hasError = false
                        }
                    }, modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp)
                        .height(50.dp)
                        .clip(CircleShape)
                ) {
                    if (loading) {
                        HorizontalDottedProgressBar()
                    } else {
                        Text(text = "登录", color = Color.White)
                    }
                }
            }
        }
    }
}

fun invalidInput(account: String, password: String): Boolean {
    return account.isBlank() || password.isBlank()
}