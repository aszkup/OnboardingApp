package com.example.onboardingapp.ui.utils.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun OutlinedValidationTextField(
    modifier: Modifier = Modifier,
    value: MutableState<String>,
    label: String,
    isError: Boolean = false,
    errorText: String = "",
    keyboardOptions: KeyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
) {

    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        OutlinedTextField(
            value = value.value,
            onValueChange = { newValue: String -> value.value = newValue },
            singleLine = true,
            label = { Text(text = label) },
            keyboardOptions = keyboardOptions,
            isError = isError,
            modifier = modifier.fillMaxWidth(),
        )
        if (isError && errorText.isNotEmpty()) {
            Text(
                text = errorText,
                color = MaterialTheme.colors.error,
                style = MaterialTheme.typography.caption,
                modifier = Modifier.padding(start = 16.dp, end = 16.dp)
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun OutlinedTextFieldVisibleWithErrorPreview() {
    OutlinedValidationTextField(
        value = remember { mutableStateOf("Test text") },
        label = "TestLabel",
        isError = true,
        errorText = "Error on field",
    )
}
