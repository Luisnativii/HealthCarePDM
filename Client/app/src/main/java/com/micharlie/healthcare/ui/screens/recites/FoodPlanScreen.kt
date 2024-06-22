package com.micharlie.healthcare.ui.screens.recites
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.clickable
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.micharlie.healthcare.ui.navigation.ScreenRoute
import com.micharlie.healthcare.ui.theme.contrasPrimary
import com.micharlie.healthcare.ui.theme.contrast2
import com.micharlie.healthcare.ui.theme.primary
import com.micharlie.healthcare.ui.theme.secondary
import com.micharlie.healthcare.ui.theme.tertiary
import com.micharlie.healthcare.ui.theme.white

@Composable
fun FoodPlanScreen(navController: NavController) {
    val breakfastRecipes = remember { getBreakfastRecipes() }
    val lunchRecipes = remember { getLunchRecipes() }
    val dinnerRecipes = remember { getDinnerRecipes() }

    Surface(color = primary) {
        Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
            val text = buildAnnotatedString {
                withStyle(style = SpanStyle(color = white)) {
                    append("Planes ")
                }
                withStyle(style = SpanStyle(color = contrasPrimary)) {
                    append("Alimenticios")
                }
            }

            Text(
                text = text,
                fontSize = 24.sp,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                LazyColumn {
                    item {
                        MealPlanCard("Desayuno 🥐", breakfastRecipes) {
                            navController.navigate(ScreenRoute.RecipeList.route + "/Desayuno")
                        }
                    }
                    item {
                        MealPlanCard("Almuerzo 🍗", lunchRecipes) {
                            navController.navigate(ScreenRoute.RecipeList.route + "/Almuerzo")
                        }
                    }
                    item {
                        MealPlanCard("Cena 🥖", dinnerRecipes) {
                            navController.navigate(ScreenRoute.RecipeList.route + "/Cena")
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun MealPlanCard(mealType: String, recipes: List<Recipe>, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable { onClick() },
        colors = CardDefaults.cardColors(containerColor = secondary),
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = mealType, fontSize = 20.sp, color = contrast2)
            recipes.forEach { recipe ->
                Text(text = recipe.title, fontSize = 16.sp, color = tertiary)
            }
        }
    }
}

fun getBreakfastRecipes(): List<Recipe> {
    return listOf(
        Recipe(
            "Tostadas con Aguacate",
            listOf("Pan integral", "Aguacate", "Sal"),
            "1. Tostar las rebanadas de pan integral.\n" +
                    "2. Aplastar el aguacate y esparcirlo sobre el pan tostado.\n" +
                    "3. Añadir una pizca de sal al gusto."
        ),
        Recipe(
            "Batido de Frutas",
            listOf("Plátano", "Fresas", "Leche"),
            "1. Cortar el plátano y las fresas.\n" +
                    "2. Colocar todas las frutas en una licuadora.\n" +
                    "3. Añadir leche y licuar hasta obtener una mezcla homogénea."
        ),
        Recipe(
            "Avena con Frutas",
            listOf("Avena", "Leche", "Frutas mixtas", "Miel"),
            "1. Cocinar la avena con la leche según las instrucciones del paquete.\n" +
                    "2. Servir la avena en un bol.\n" +
                    "3. Añadir frutas mixtas por encima.\n" +
                    "4. Rociar con miel al gusto."
        )
    )
}

fun getLunchRecipes(): List<Recipe> {
    return listOf(
        Recipe(
            "Ensalada César",
            listOf("Lechuga", "Pollo", "Aderezo César"),
            "1. Lavar y cortar la lechuga.\n" +
                    "2. Cocinar y cortar el pollo en tiras.\n" +
                    "3. Mezclar la lechuga con el pollo y añadir el aderezo César.\n" +
                    "4. Servir frío."
        ),
        Recipe(
            "Sopa de Verduras",
            listOf("Zanahoria", "Papa", "Apio"),
            "1. Pelar y cortar las zanahorias y las papas.\n" +
                    "2. Cortar el apio en trozos pequeños.\n" +
                    "3. Hervir las verduras en agua hasta que estén tiernas.\n" +
                    "4. Sazonar con sal y pimienta al gusto."
        ),
        Recipe(
            "Tacos de Pollo",
            listOf("Tortillas de maíz", "Pollo desmenuzado", "Cebolla", "Cilantro", "Salsa"),
            "1. Calentar las tortillas de maíz.\n" +
                    "2. Colocar el pollo desmenuzado sobre las tortillas.\n" +
                    "3. Añadir cebolla picada y cilantro.\n" +
                    "4. Servir con salsa al gusto."
        )
    )
}

fun getDinnerRecipes(): List<Recipe> {
    return listOf(
        Recipe(
            "Salmón al Horno",
            listOf("Salmón", "Limón", "Especias"),
            "1. Precalentar el horno a 200°C.\n" +
                    "2. Colocar el salmón en una bandeja para hornear.\n" +
                    "3. Exprimir el limón sobre el salmón y añadir las especias.\n" +
                    "4. Hornear durante 20-25 minutos."
        ),
        Recipe(
            "Pasta con Pesto",
            listOf("Pasta", "Salsa pesto", "Queso parmesano"),
            "1. Cocinar la pasta según las instrucciones del paquete.\n" +
                    "2. Escurrir la pasta y mezclarla con la salsa pesto.\n" +
                    "3. Espolvorear con queso parmesano rallado antes de servir."
        ),
        Recipe(
            "Pollo a la Parrilla",
            listOf("Pechugas de pollo", "Aceite de oliva", "Ajo", "Especias"),
            "1. Marinar las pechugas de pollo con aceite de oliva, ajo y especias.\n" +
                    "2. Precalentar la parrilla a temperatura media-alta.\n" +
                    "3. Cocinar el pollo en la parrilla durante 6-7 minutos por cada lado o hasta que esté bien cocido.\n" +
                    "4. Servir caliente con una guarnición de tu elección."
        )
    )
}
@Preview(showBackground = true)
@Composable
fun PreviewFoodPlanScreen() {
    FoodPlanScreen(navController = rememberNavController())
}