package com.example.onboardingapp.ui.onboarding.personal_Info

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
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
import com.example.onboardingapp.ui.utils.components.OutlinedValidationTextField
import com.example.onboardingapp.ui.utils.components.PageTitleText
import com.example.onboardingapp.ui.utils.theme.Dimens
import com.example.onboardingapp.ui.utils.theme.OnboardingAppTheme

const val NAV_PERSONAL_INFO = "nav_personal_info"

@Composable
fun PersonalInfoScreen(
    onboardingViewModel: OnboardingViewModel,
    onNextClicked: () -> Unit
) {
    OnboardingAppTheme {
        ConstraintLayout(
            modifier = Modifier.fillMaxSize()
        ) {
            LaunchedEffect(rememberCoroutineScope()) {
                onboardingViewModel.observePhone()
            }
            val guideStart = createGuidelineFromStart(Dimens.spaceNormal)
            val guideEnd = createGuidelineFromEnd(Dimens.spaceNormal)
            val (
                titleRef,
                firstNameRef,
                lastNameRef,
                phoneRef,
                buttonRef,
            ) = createRefs()

            val firstName = rememberSaveable { onboardingViewModel.firstName }
            val lastName = rememberSaveable { onboardingViewModel.lastName }
            val number = rememberSaveable { onboardingViewModel.phone }

            PageTitleText(
                text = stringResource(id = R.string.personal_info),
                modifier = Modifier.constrainAs(titleRef) {
                    top.linkTo(parent.top, Dimens.spaceExtraLarge)
                    linkTo(guideStart, guideEnd)
                    width = Dimension.fillToConstraints
                },
            )

            OutlinedTextField(
                value = firstName.value,
                onValueChange = { text -> firstName.value = text },
                singleLine = true,
                label = {
                    Text(text = stringResource(R.string.common_firstName))
                },
                modifier = Modifier.constrainAs(firstNameRef) {
                    top.linkTo(titleRef.bottom, Dimens.spaceLarge)
                    linkTo(guideStart, guideEnd)
                    width = Dimension.fillToConstraints
                },
            )

            OutlinedTextField(
                value = lastName.value,
                onValueChange = { text -> lastName.value = text },
                singleLine = true,
                label = {
                    Text(text = stringResource(R.string.common_lastName))
                },
                modifier = Modifier.constrainAs(lastNameRef) {
                    top.linkTo(firstNameRef.bottom, Dimens.spaceLarge)
                    linkTo(guideStart, guideEnd)
                    width = Dimension.fillToConstraints
                },
            )

            OutlinedValidationTextField(
                value = number,
                label = stringResource(R.string.phoneNumber_hint),
                isError = onboardingViewModel.isPhoneError.value,
                errorText = stringResource(onboardingViewModel.phoneErrorText.value),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                modifier = Modifier.constrainAs(phoneRef) {
                    top.linkTo(lastNameRef.bottom, Dimens.spaceLarge)
                    linkTo(guideStart, guideEnd)
                    width = Dimension.fillToConstraints
                },
            )

            val isButtonEnabled = firstName.value.isNotEmpty() && lastName.value.isNotEmpty()
                    && onboardingViewModel.isPhoneValid.value
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
    PersonalInfoScreen(
        onboardingViewModel = hiltViewModel(),
        onNextClicked = {}
    )
}
