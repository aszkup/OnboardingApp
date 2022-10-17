package com.example.onboardingapp.ui.onboarding.welcome

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.example.onboardingapp.R
import com.example.onboardingapp.ui.utils.components.CommonButton
import com.example.onboardingapp.ui.utils.components.PageTitleText
import com.example.onboardingapp.ui.utils.theme.Dimens
import com.example.onboardingapp.ui.utils.theme.OnboardingAppTheme

const val NAV_WELCOME = "nav_welcome"

@Composable
fun WelcomeScreen(
    onStartClicked: () -> Unit,
) {
    OnboardingAppTheme {
        ConstraintLayout(
            modifier = Modifier.fillMaxSize()
        ) {
            val guideStart = createGuidelineFromStart(Dimens.spaceNormal)
            val guideEnd = createGuidelineFromEnd(Dimens.spaceNormal)
            val (
                titleRef,
                buttonRef,
            ) = createRefs()

            PageTitleText(
                text = stringResource(id = R.string.welcome),
                modifier = Modifier.constrainAs(titleRef) {
                    top.linkTo(parent.top, Dimens.spaceExtraLarge)
                    linkTo(guideStart, guideEnd)
                    width = Dimension.fillToConstraints
                },
            )

            CommonButton(
                onClick = onStartClicked,
                text = stringResource(id = R.string.common_start),
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
    WelcomeScreen(
        onStartClicked = {},
    )
}
