package com.micharlie.healthcare.ui.screens.recites
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.micharlie.healthcare.ui.theme.contrast1
import com.micharlie.healthcare.ui.theme.contrast2
import com.micharlie.healthcare.ui.theme.primary
import com.micharlie.healthcare.ui.theme.secondary
import com.micharlie.healthcare.ui.theme.tertiary
import com.micharlie.healthcare.ui.theme.white

@Composable
fun RecipeListScreen(navController: NavController, mealType: String) {
    val recipes = when (mealType) {
        "Desayuno" -> getBreakfastRecipes()
        "Almuerzo" -> getLunchRecipes()
        "Cena" -> getDinnerRecipes()
        else -> emptyList()
    }

    Surface(color = primary) {
        Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
            Text(
                text = "Recetas de $mealType",
                fontSize = 24.sp,
                color = contrast1,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            LazyColumn {
                items(recipes.size) { index ->
                    val recipe = recipes[index]
                    RecipeCard(recipe)
                }
            }
        }
    }
}

@Composable
fun RecipeCard(recipe: Recipe) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        colors = CardDefaults.cardColors(containerColor = secondary),
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = recipe.title,
                fontSize = 20.sp,
                color = contrast2,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Text(
                text = "Ingredientes:",
                fontSize = 16.sp,
                color = tertiary,
                modifier = Modifier.padding(bottom = 4.dp)
            )
            recipe.ingredients.forEach { ingredient ->
                Text(
                    text = "- $ingredient",
                    fontSize = 14.sp,
                    color = white,
                    modifier = Modifier.padding(bottom = 2.dp)
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Instrucciones:",
                fontSize = 16.sp,
                color = tertiary,
                modifier = Modifier.padding(bottom = 4.dp)
            )
            recipe.instructions.split("\n").forEach { instruction ->
                Text(
                    text = instruction,
                    fontSize = 14.sp,
                    color = white,
                    modifier = Modifier.padding(bottom = 2.dp)
                )
            }
        }
    }
}
@Preview(showBackground = true)
@Composable
fun PreviewRecipeCard() {
    val recipe = Recipe(
        title = "Tostadas con Aguacate",
        ingredients = listOf("Pan integral", "Aguacate", "Sal"),
        instructions =
        "1. Tostar las rebanadas de pan integral.\n" +
                "2. Aplastar el aguacate y esparcirlo sobre el pan tostado.\n" +
                "3. Añadir una pizca de sal al gusto."


    )
    RecipeCard(recipe)
}