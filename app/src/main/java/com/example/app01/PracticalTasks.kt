package com.example.app01

data class Book(val title: String, val author: String)

enum class OrderStatus(val description: String) {
    NEW("Нове замовлення"),
    PROCESSING("Обробляється"),
    SHIPPED("Відправлено"),
    DELIVERED("Доставлено")
}

sealed class UserEvent {
    data class Click(val x: Int, val y: Int) : UserEvent()
    data class TextEntered(val text: String) : UserEvent()
    object ScreenOpened : UserEvent()
}

fun handleUserEvent(event: UserEvent) {
    when (event) {
        is UserEvent.Click -> println("Подія: Клік на (${event.x}, ${event.y})")
        is UserEvent.TextEntered -> println("Подія: Введення тексту \"${event.text}\"")
        UserEvent.ScreenOpened -> println("Подія: Екран відкрито")
    }
}

fun runSpecialClassesDemo() {
    println("--- 1. Спеціальні класи ---")
    val book1 = Book("Кобзар", "Тарас Шевченко")
    val book2 = book1.copy(title = "Гайдамаки")
    println("Book 1 (Data Class): $book1")
    println("Book 2 (Copy): $book2")
    val currentStatus = OrderStatus.PROCESSING
    println("Статус замовлення (Enum): ${currentStatus.description}")
    handleUserEvent(UserEvent.Click(150, 250))
}

fun runCollectionsDemo() {
    println("\n--- 2. Колекції ---")
    val students: List<String> = listOf("Іван", "Марія", "Олег")
    println("Список студентів (List): $students")
    val userIds: Set<Int> = setOf(101, 205, 101, 312)
    println("Унікальні ID (Set): $userIds")
    val productPrices: Map<String, Double> = mapOf(
        "Ноутбук" to 1500.0,
        "Миша" to 25.0
    )
    println("Ціна Ноутбука (Map): ${productPrices["Ноутбук"]} грн")
}

data class Product(val name: String, val price: Double)

fun practicalTaskCollections() {
    println("\n--- 3. Практичне завдання ---")
    val products = listOf(
        Product("Телефон", 8500.0),
        Product("Навушники", 1200.0),
        Product("Планшет", 6000.0)
    )
    val minPrice = 5000.0
    println("Продукти, дорожчі за $minPrice грн:")
    products
        .filter { it.price > minPrice }
        .forEach { println("- ${it.name} (${it.price} грн)") }

    val categorizedProducts: Map<String, List<Product>> = mapOf(
        "Гаджети" to listOf(products[0], products[2]),
        "Аксесуари" to listOf(products[1])
    )

    println("Словник продуктів за категоріями:")
    categorizedProducts.forEach { (category, productList) ->
        println("Категорія: $category")
        productList.forEach { product ->
            println("  - ${product.name}")
        }
    }
}

fun main() {
    runSpecialClassesDemo()
    runCollectionsDemo()
    practicalTaskCollections()
}