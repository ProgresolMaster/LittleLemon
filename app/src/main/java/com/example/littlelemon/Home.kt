package com.example.littlelemon

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.littlelemon.ui.theme.Typography

@Composable
fun Home(navController: NavController, menuItems: List<MenuItemRoom>) {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Top
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(64.dp)
                .padding(start = 24.dp, end = 24.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "", modifier = Modifier
                    .width(40.dp)
                    .height(40.dp)
            )
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "logo",
                modifier = Modifier
                    .padding(top = 8.dp, bottom = 8.dp)
                    .height(64.dp)
                    .width(256.dp)
            )
            Image(
                painter = painterResource(id = R.drawable.profile),
                contentDescription = "Profile Illustration",
                modifier = Modifier
                    .width(40.dp)
                    .height(40.dp)
                    .clickable {
                        navController.navigate(Profile.route)
                    }
            )
        }

        Hero(menuItems)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Hero(menuItems: List<MenuItemRoom>) {

    var searchPhrase by remember { mutableStateOf("") }

    Column(modifier = Modifier.fillMaxWidth()) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFF495E57))
        ) {
            Text(
                text = "Little Lemon",
                style = Typography.titleLarge,
                color = Color(0xFFF4CE14),
                modifier = Modifier.padding(
                    bottom = 12.dp,
                    top = 12.dp,
                    start = 24.dp,
                    end = 24.dp
                ),
                fontWeight = FontWeight.Bold,
                fontSize = 48.sp
            )
            Row(
                modifier = Modifier
                    .padding(start = 24.dp, end = 24.dp)
                    .fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier
                        .weight(2f)
                        .width(0.dp),
                    horizontalAlignment = Alignment.Start,
                ) {
                    Text(
                        text = "Chicago",
                        style = Typography.titleLarge,
                        color = Color(0xFFFFFFFF),
                        modifier = Modifier.padding(bottom = 16.dp),
                        fontWeight = FontWeight.Bold,
                        fontSize = 36.sp
                    )
                    Text(
                        text = "We are a family owned Mediterranean restaurant, focused on traditional recipes served with a modern twist.",
                        style = Typography.bodySmall,
                        color = Color(0xFFFFFFFF),
                        modifier = Modifier.padding(bottom = 48.dp),
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp
                    )
                }
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .width(0.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.hero_image),
                        contentDescription = "Hero illustration",
                        modifier = Modifier
                            .size(200.dp)
                            .clip(RoundedCornerShape(16.dp)), // Adjust the corner radius as needed
                        contentScale = ContentScale.Crop
                    )
                }
            }

            OutlinedTextField(
                value = searchPhrase,
                onValueChange = { searchPhrase = it },
                placeholder = { Text("Enter Search Phrase") },
                leadingIcon = { Icon(imageVector = Icons.Default.Search, contentDescription = "") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 24.dp, top = 24.dp, bottom = 24.dp, end = 24.dp)
                    .background(Color(0xFFFFFFFF))
            )
        }
        DeliveryOrder(menuItems, searchPhrase)
    }

}

@Composable
fun DeliveryOrder(menuItems: List<MenuItemRoom>, searchPhrase: String) {
    val uniqueCategories = menuItems.distinctBy { it.category }.map { it.category }
    var category by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 40.dp, start = 24.dp, end = 24.dp)
    ) {
        Text(
            text = "ORDER FOR DELIVERY!",
            color = Color(0xFF000000),
            style = Typography.titleLarge,
            fontWeight = FontWeight.Bold
        )
        LazyRow(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            items(
                items = uniqueCategories,
                itemContent = { cat ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(16.dp))
                            .background(Color(0xFFEDEFEE))
                            .clickable {
                                category = cat
                            },
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = cat,
                            modifier = Modifier.padding(10.dp),
                            color = Color(0xFF495E57),
                            style = Typography.bodyLarge,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                }
            )
        }
        val filteredAndSortedMenuItems: List<MenuItemRoom> = if (searchPhrase.isNotEmpty()) {
            if (category.isNotBlank()) {
                menuItems.filter { menuItem ->
                    menuItem.category.contains(
                        category,
                        ignoreCase = true
                    ) && menuItem.title.contains(searchPhrase, ignoreCase = true)
                }
            } else {
                menuItems.filter { menuItem ->
                    menuItem.title.contains(searchPhrase, ignoreCase = true)
                }
            }

        } else {
            menuItems
        }

        if (searchPhrase.isEmpty()) {
            category = ""
        }

        LazyItemList(filteredAndSortedMenuItems)
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun LazyItemList(menuItems: List<MenuItemRoom>) {
    LazyColumn(
        modifier = Modifier
            .fillMaxHeight()
            .padding(top = 20.dp)
    ) {
        items(
            items = menuItems,
            itemContent = { menuItem ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp, top = 16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Column(
                        modifier = Modifier
                            .weight(3f)
                            .width(0.dp),
                    ) {
                        Text(
                            text = menuItem.title,
                            color = Color(0xFF000000),
                            style = Typography.titleLarge,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            modifier = Modifier.padding(top = 12.dp, bottom = 12.dp),
                            text = menuItem.description,
                            color = Color(0xFF495E57),
                            style = Typography.bodyLarge,
                            fontWeight = FontWeight.Normal
                        )
                        Text(
                            modifier = Modifier
                                .padding(bottom = 4.dp),
                            text = "$ %.2f".format(menuItem.price),
                            color = Color(0xFF495E57),
                            style = Typography.bodyLarge,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .width(0.dp)
                            .padding(start = 16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        GlideImage(
                            model = menuItem.image,
                            contentDescription = "Img Resource",
                            modifier = Modifier
                                .size(84.dp)
                                .clip(RoundedCornerShape(16.dp))
                        )
                    }
                }

                Divider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(1.dp)
                        .background(Color.Gray)
                )
            }
        )
    }
}