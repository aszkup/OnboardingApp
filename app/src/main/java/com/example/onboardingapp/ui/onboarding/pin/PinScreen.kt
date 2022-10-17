package com.example.onboardingapp.ui.onboarding.pin

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
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
import com.example.onboardingapp.ui.utils.components.PageTitleText
import com.example.onboardingapp.ui.utils.theme.Dimens
import com.example.onboardingapp.ui.utils.theme.OnboardingAppTheme

const val NAV_PIN = "nav_pin"
const val NAV_CONFIRM_PIN = "nav_confirm_pin"

@Composable
fun PinScreen(
    onboardingViewModel: OnboardingViewModel,
    launchMode: PinLaunchMode,
    onNextClicked: () -> Unit
) {
    OnboardingAppTheme {
        ConstraintLayout(
            modifier = Modifier.fillMaxSize()
        ) {
            LaunchedEffect(rememberCoroutineScope()) {
                onboardingViewModel.observePin()
            }
            val guideStart = createGuidelineFromStart(Dimens.spaceNormal)
            val guideEnd = createGuidelineFromEnd(Dimens.spaceNormal)
            val (
                titleRef,
                pinRef,
                buttonRef,
            ) = createRefs()

            val pin = rememberSaveable {
                if (launchMode == PinLaunchMode.AddPin) {
                    onboardingViewModel.pin
                } else {
                    mutableStateOf("")
                }
            }

            val label = when (launchMode) {
                PinLaunchMode.AddPin -> R.string.enter_pin
                PinLaunchMode.ConfirmPin -> R.string.confirm_pin
                PinLaunchMode.VerifyPin -> R.string.login_with_pin
            }

            PageTitleText(
                text = stringResource(id = label),
                modifier = Modifier.constrainAs(titleRef) {
                    top.linkTo(parent.top, Dimens.spaceExtraLarge)
                    linkTo(guideStart, guideEnd)
                    width = Dimension.fillToConstraints
                },
            )

            OutlinedHidableTextField(
                value = pin,
                label = stringResource(R.string.common_pin),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.constrainAs(pinRef) {
                    top.linkTo(titleRef.bottom, Dimens.spaceLarge)
                    linkTo(guideStart, guideEnd)
                    width = Dimension.fillToConstraints
                },
            )

            val isButtonEnabled = when (launchMode) {
                PinLaunchMode.AddPin -> onboardingViewModel.isPinValid.value
                PinLaunchMode.ConfirmPin -> onboardingViewModel.pin.value == pin.value
                PinLaunchMode.VerifyPin -> TODO()
            }

            val onNextButtonClicked = {
                when (launchMode) {
                    PinLaunchMode.AddPin -> {
                        onNextClicked()
                    }
                    PinLaunchMode.ConfirmPin -> {
                        onboardingViewModel.storeData()
                        onNextClicked()
                    }
                    PinLaunchMode.VerifyPin -> TODO()
                }
            }

            CommonButton(
                isEnabled = isButtonEnabled,
                onClick = onNextButtonClicked,
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
private fun EnterPinPreview() {
    PinScreen(
        onboardingViewModel = hiltViewModel(),
        launchMode = PinLaunchMode.AddPin,
        onNextClicked = {}
    )
}

@Preview(showBackground = true)
@Composable
private fun ConfirmPinPreview() {
    PinScreen(
        onboardingViewModel = hiltViewModel(),
        launchMode = PinLaunchMode.ConfirmPin,
        onNextClicked = {}
    )
}