package com.example.onboardingapp.ui.main

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.onboardingapp.R
import com.example.onboardingapp.ui.utils.components.CommonButton
import com.example.onboardingapp.ui.utils.components.PageTitleText
import com.example.onboardingapp.ui.utils.theme.Dimens
import com.example.onboardingapp.ui.utils.theme.OnboardingAppTheme

@Composable
fun MainScreen(
    mainViewModel: MainViewModel,
    onLogoutClicked: () -> Unit
) {
    OnboardingAppTheme {
        ConstraintLayout(
            modifier = Modifier.fillMaxSize()
        ) {
            LaunchedEffect(rememberCoroutineScope()) {
                mainViewModel.loadUserData()
            }

            val guideStart = createGuidelineFromStart(Dimens.spaceNormal)
            val guideEnd = createGuidelineFromEnd(Dimens.spaceNormal)
            val (
                titleRef,
                buttonRef,
                firstNameRef,
                lastNameRef,
                phoneRef,
            ) = createRefs()

            PageTitleText(
                text = stringResource(id = R.string.your_feed),
                modifier = Modifier.constrainAs(titleRef) {
                    top.linkTo(parent.top, Dimens.spaceExtraLarge)
                    linkTo(guideStart, guideEnd)
                    width = Dimension.fillToConstraints
                },
            )

            Text(
                text = "${stringResource(R.string.common_firstName)}: ${mainViewModel.firstName.value}",
                textAlign = TextAlign.Start,
                modifier = Modifier.constrainAs(firstNameRef) {
                    top.linkTo(titleRef.bottom, Dimens.spaceLarge)
                    linkTo(guideStart, guideEnd)
                    width = Dimension.fillToConstraints
                },
            )
            Text(
                text = "${stringResource(R.string.common_lastName)}: ${mainViewModel.lastName.value}",
                textAlign = TextAlign.Start,
                modifier = Modifier.constrainAs(lastNameRef) {
                    top.linkTo(firstNameRef.bottom, Dimens.spaceLarge)
                    linkTo(guideStart, guideEnd)
                    width = Dimension.fillToConstraints
                },
            )
            Text(
                text = "${stringResource(R.string.common_phoneNumber)}: ${mainViewModel.phone.value}",
                textAlign = TextAlign.Start,
                modifier = Modifier.constrainAs(phoneRef) {
                    top.linkTo(lastNameRef.bottom, Dimens.spaceLarge)
                    linkTo(guideStart, guideEnd)
                    width = Dimension.fillToConstraints
                },
            )

            CommonButton(
                onClick = {
                    mainViewModel.onLogoutClicked { onLogoutClicked.invoke() }
                },
                text = stringResource(id = R.string.common_logout),
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
    MainScreen(
        mainViewModel = hiltViewModel(),
        onLogoutClicked = {},
    )
}
