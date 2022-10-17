package com.example.onboardingapp.ui.utils.components

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp

@Composable
fun PageTitleText(
    modifier: Modifier = Modifier,
    text: String,
) {
    Text(
        fontSize = 32.sp,
        textAlign = TextAlign.Center,
        text = text,
        modifier = modifier,
        color = MaterialTheme.colors.primaryVariant
    )
}

@Preview(showBackground = true)
@Composable
private fun CommonButtonPreview() {
    PageTitleText(
        text = "Page title",
    )
}
