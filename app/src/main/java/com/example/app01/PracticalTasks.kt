package com.example.app01

fun main() {
    checkEvenOrOdd()
    calculateSumToN()
    multiplicationTable()
    guessTheNumberGame()
    runSpecialClassesExamples()
    runCollectionsExamples()
    practicalTaskCollections()
}

fun checkEvenOrOdd() {
    println("--- 2.1 Парне чи непарне ---")
    println("Введіть ціле число:")
    val number = readLine()?.toIntOrNull()

    if (number != null) {
        if (number % 2 == 0) {
            println("Число $number парне.")
        } else {
            println("Число $number непарне.")
        }
    } else {
        println("Некоректне введення.")
    }
}

fun calculateSumToN() {
    println("\n--- 2.2 Сума чисел до N ---")
    println("Введіть ціле число N:")
    val inputN = readLine()
    val N = inputN?.toIntOrNull()

    if (N != null && N > 0) {
        var sum = 0
        for (i in 1..N) {
            sum += i
        }
        println("Сума чисел від 1 до $N дорівнює $sum.")
    } else {
        println("Некоректне введення. N має бути цілим числом > 0.")
    }
}

fun multiplicationTable() {
    println("\n--- 2.3 Таблиця множення ---")
    println("Таблиця множення (1-10):")
    for (i in 1..10) {
        for (j in 1..10) {
            print("${i * j}\t")
        }
        println()
    }
}

fun guessTheNumberGame() {
    println("\n--- 2.4 Гра \"Вгадай число\" ---")
    val targetNumber = (1..100).random()
    var userGuess: Int

    do {
        print("Вгадайте число від 1 до 100: ")
        userGuess = readLine()?.toIntOrNull() ?: -1

        when {
            userGuess == -1 -> println("Некоректне введення.")
            userGuess > targetNumber -> println("Забагато! Спробуйте менше.")
            userGuess < targetNumber -> println("Замало! Спробуйте більше.")
            else -> println("Вірно! Ви вгадали число $targetNumber!")
        }
    } while (userGuess != targetNumber)
}

data class Book(val title: String, val author: String)

enum class OrderStatus(val description: String) {
    NEW("Нове замовлення"),
    PROCESSING("Обробляється"),
    DELIVERED("Доставлено")
}

sealed class UserEvent {
    data class Click(val x: Int, val y: Int) : UserEvent()
    data class TextEntered(val text: String) : UserEvent()
    object ScreenOpened : UserEvent()
}

fun runSpecialClassesExamples() {
    println("\n--- 3.1 Спеціальні класи ---")

    val book1 = Book("Лісова пісня", "Леся Українка")
    val book2 = book1.copy(title = "Бояриня")
    println("Book 1: $book1")
    println("Book 2 (копія з новою назвою): $book2")

    val status = OrderStatus.PROCESSING
    println("Поточний статус замовлення: ${status.description}")

    fun handleEvent(event: UserEvent) = when (event) {
        is UserEvent.Click -> println("Подія: Клік на (${event.x}, ${event.y})")
        is UserEvent.TextEntered -> println("Подія: Введення тексту \"${event.text}\"")
        UserEvent.ScreenOpened -> println("Подія: Екран відкрито")
    }
    handleEvent(UserEvent.Click(50, 50))
}

fun runCollectionsExamples() {
    println("\n--- 3.2 Колекції ---")

    val students: List<String> = listOf("Петро", "Олена", "Андрій")
    println("Список студентів: $students")

    val userIds: Set<Int> = setOf(10, 20, 10, 30, 20)
    println("Унікальні ID: $userIds")

    val productPrices: Map<String, Double> = mapOf(
        "Кава" to 50.0,
        "Чай" to 35.0,
        "Сік" to 40.0
    )
    println("Ціна Кави: ${productPrices["Кава"]} грн")
}

data class Product(val name: String, val price: Double)

fun practicalTaskCollections() {
    println("\n--- 3.3 Фінальне практичне завдання ---")

    val products = listOf(
        Product("Телефон", 12000.0),
        Product("Ноутбук", 25000.0),
        Product("Миша", 500.0),
        Product("Килимок", 200.0),
        Product("Монітор", 8000.0)
    )

    val threshold = 7000.0
    println("Продукти, дорожчі за $threshold грн:")
    products
        .filter { it.price > threshold } // Фільтрація
        .forEach { println("- ${it.name} (${it.price} грн)") }

    val categorizedProducts: Map<String, List<Product>> = mapOf(
        "Гаджети" to listOf(products[0], products[1], products[4]),
        "Аксесуари" to listOf(products[2], products[3])
    )

    println("\nПродукти за категоріями:")
    categorizedProducts.forEach { (category, productList) ->
        println("Категорія: $category")
        productList.forEach { product ->
            println("  - ${product.name}")
        }
    }
}