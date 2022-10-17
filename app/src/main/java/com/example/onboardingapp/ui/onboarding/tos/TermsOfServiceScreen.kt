package com.example.onboardingapp.ui.onboarding.tos

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Checkbox
import androidx.compose.material.CheckboxDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.onboardingapp.R
import com.example.onboardingapp.ui.onboarding.OnboardingViewModel
import com.example.onboardingapp.ui.utils.components.CommonButton
import com.example.onboardingapp.ui.utils.components.PageTitleText
import com.example.onboardingapp.ui.utils.theme.Dimens
import com.example.onboardingapp.ui.utils.theme.OnboardingAppTheme

const val NAV_TOS = "nav_terms_of_service"

@Composable
fun TermsOfServiceScreen(
    onboardingViewModel: OnboardingViewModel,
    onNextClicked: () -> Unit,
) {
    OnboardingAppTheme {
        ConstraintLayout(
            modifier = Modifier.fillMaxSize()
        ) {
            val guideStart = createGuidelineFromStart(Dimens.spaceNormal)
            val guideEnd = createGuidelineFromEnd(Dimens.spaceNormal)
            val (
                tosRef,
                tosContentRef,
                checkboxContainerRef,
                buttonRef,
            ) = createRefs()

            PageTitleText(
                text = stringResource(id = R.string.terms_of_service),
                modifier = Modifier.constrainAs(tosRef) {
                    top.linkTo(parent.top, Dimens.spaceExtraLarge)
                    linkTo(guideStart, guideEnd)
                    width = Dimension.fillToConstraints
                },
            )

            Text(
                modifier = Modifier.constrainAs(tosContentRef) {
                    top.linkTo(tosRef.bottom, Dimens.spaceLarge)
                    linkTo(guideStart, guideEnd)
                    width = Dimension.fillToConstraints
                },
                text = stringResource(R.string.terms_of_service_content),
            )

            val isChecked = rememberSaveable { onboardingViewModel.areTermsAccepted }
            Row(
                modifier = Modifier
                    .constrainAs(checkboxContainerRef) {
                        top.linkTo(tosContentRef.bottom, Dimens.spaceNormal)
                        start.linkTo(guideStart)
                    }
            ) {

                Checkbox(
                    checked = isChecked.value,
                    onCheckedChange = { isChecked.value = it },
                    enabled = true,
                    colors = CheckboxDefaults.colors(Color.Green)
                )
                Text(
                    modifier = Modifier.align(Alignment.CenterVertically),
                    text = stringResource(id = R.string.accept_terms_of_service)
                )
            }

            CommonButton(
                isEnabled = isChecked.value,
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
    TermsOfServiceScreen(
        onboardingViewModel = hiltViewModel(),
        onNextClicked = {},
    )
}
