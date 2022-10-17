package com.example.onboardingapp.ui.utils.components

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun OutlinedHidableTextField(
    modifier: Modifier = Modifier,
    value: MutableState<String>,
    label: String,
    keyboardOptions: KeyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
) {
    val isTextVisible = rememberSaveable { mutableStateOf(false) }
    OutlinedTextField(
        value = value.value,
        onValueChange = { newValue: String -> value.value = newValue },
        singleLine = true,
        label = { Text(text = label) },
        keyboardOptions = keyboardOptions,
        visualTransformation = if (isTextVisible.value)
            VisualTransformation.None
        else PasswordVisualTransformation(),
        trailingIcon = {
            val image = if (isTextVisible.value) Icons.Filled.Visibility
            else Icons.Filled.VisibilityOff

            IconButton(onClick = { isTextVisible.value = !isTextVisible.value }) {
                Icon(imageVector = image, null)
            }
        },
        modifier = modifier,
    )
}

@Preview(showBackground = true)
@Composable
private fun OutlinedHidableTextFieldPreview() {
    OutlinedHidableTextField(
        value = remember { mutableStateOf("Test text") },
        label = "TestLabel",
    )
}
