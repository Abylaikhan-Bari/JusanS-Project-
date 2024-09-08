package com.aikei.jusan.presentation.ui.components

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.RowScope
import com.aikei.jusan.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBar(
    title: String,
    navigationIcon: ImageVector? = null,
    actions: @Composable RowScope.() -> Unit = {}
) {
    SmallTopAppBar(
        title = { Text(text = title) },
        navigationIcon = {
            navigationIcon?.let {
                IconButton(onClick = { /* Handle navigation icon click */ }) {
                    Icon(imageVector = it, contentDescription = null)
                }
            }
        },
        actions = actions,
        colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = Color.Transparent
        )
    )
}

@Preview
@Composable
fun TopAppBarPreview() {
    TopAppBar(
        title = "Home",
        navigationIcon = ImageVector.vectorResource(id = R.drawable.ic_home),
        actions = {
            IconButton(onClick = { /* Handle profile icon click */ }) {
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_profile),
                    contentDescription = null
                )
            }
        }
    )
}
