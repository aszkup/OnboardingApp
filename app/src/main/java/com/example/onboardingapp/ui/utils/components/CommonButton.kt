package com.example.onboardingapp.ui.utils.components

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.onboardingapp.R
import com.example.onboardingapp.ui.utils.theme.Dimens

@Composable
fun CommonButton(
    modifier: Modifier = Modifier,
    isEnabled: Boolean = true,
    text: String,
    onClick: () -> Unit,
) {
    Button(
        modifier = modifier.height(48.dp),
        enabled = isEnabled,
        onClick = onClick,
        shape = RoundedCornerShape(Dimens.cornerRadius),
    ) {
        Text(text = text)
    }
}

@Preview
@Composable
private fun CommonButtonPreview() {
    CommonButton(
        text = stringResource(id = R.string.common_next),
        onClick = {},
    )
}

@Preview
@Composable
private fun CommonButtonDisabledPreview() {
    CommonButton(
        isEnabled = false,
        text = stringResource(id = R.string.common_next),
        onClick = {},
    )
}
