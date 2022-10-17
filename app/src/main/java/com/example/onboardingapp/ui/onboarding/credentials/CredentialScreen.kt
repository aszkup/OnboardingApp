package com.example.onboardingapp.ui.onboarding.credentials

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.onboardingapp.R
import com.example.onboardingapp.ui.onboarding.OnboardingViewModel
import com.example.onboardingapp.ui.utils.components.CommonButton
import com.example.onboardingapp.ui.utils.components.OutlinedHidableTextField
import com.example.onboardingapp.ui.utils.components.OutlinedValidationTextField
import com.example.onboardingapp.ui.utils.components.PageTitleText
import com.example.onboardingapp.ui.utils.theme.Dimens
import com.example.onboardingapp.ui.utils.theme.OnboardingAppTheme

const val NAV_CREDENTIALS = "nav_credentials"

@Composable
fun CredentialScreen(
    onboardingViewModel: OnboardingViewModel,
    onNextClicked: () -> Unit
) {
    OnboardingAppTheme {
        ConstraintLayout(
            modifier = Modifier.fillMaxSize()
        ) {
            LaunchedEffect(rememberCoroutineScope()) {
                onboardingViewModel.observeEmail()
            }
            val guideStart = createGuidelineFromStart(Dimens.spaceNormal)
            val guideEnd = createGuidelineFromEnd(Dimens.spaceNormal)
            val (
                titleRef,
                emailRef,
                passRef,
                buttonRef,
            ) = createRefs()

            val email = rememberSaveable { onboardingViewModel.email }
            val password = rememberSaveable { onboardingViewModel.password }

            PageTitleText(
                text = stringResource(id = R.string.personal_loginData),
                modifier = Modifier.constrainAs(titleRef) {
                    top.linkTo(parent.top, Dimens.spaceExtraLarge)
                    linkTo(guideStart, guideEnd)
                    width = Dimension.fillToConstraints
                },
            )

            OutlinedValidationTextField(
                value = email,
                label = stringResource(R.string.common_email),
                isError = onboardingViewModel.isEmailError.value,
                errorText = stringResource(onboardingViewModel.emailErrorText.value),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                modifier = Modifier.constrainAs(emailRef) {
                    top.linkTo(titleRef.bottom, Dimens.spaceLarge)
                    linkTo(guideStart, guideEnd)
                    width = Dimension.fillToConstraints
                },
            )

            OutlinedHidableTextField(
                value = password,
                label = stringResource(R.string.common_password),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                modifier = Modifier.constrainAs(passRef) {
                    top.linkTo(emailRef.bottom, Dimens.spaceLarge)
                    linkTo(guideStart, guideEnd)
                    width = Dimension.fillToConstraints
                },
            )

            val isButtonEnabled =
                onboardingViewModel.isEmailValid.value && password.value.isNotEmpty()
            CommonButton(
                isEnabled = isButtonEnabled,
                onClick = onNextClicked,
                text = stringResource(id = R.string.common_next),
                modifier = Modifier.constrainAs(buttonRef) {
                    bottom.linkTo(parent.bottom, Dimens.spaceExtraLarge)
                    linkTo(guideStart, guideEnd)
                    width = Dimension.fillToConstraints
                },
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun DefaultPreview() {
    CredentialScreen(
        onboardingViewModel = hiltViewModel(),
        onNextClicked = {}
    )
}
