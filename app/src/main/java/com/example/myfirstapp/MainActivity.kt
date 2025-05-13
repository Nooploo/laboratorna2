fun main() {
    print("Введіть кількість працівників: ")
    val employeeCount = readln().toIntOrNull() ?: 0

    val bonuses = mutableListOf<Pair<String, Double>>()

    for (i in 1..employeeCount) {
        println("Працівник №$i")

        // Введення імені
        print("Ім’я: ")
        val inputName = readlnOrNull()
        val name = inputName?.takeIf { it.isNotBlank() } ?: "Unknown"

        // Введення окладу
        val salary: Double = run {
            var result: Double? = null
            while (result == null) {
                try {
                    print("Оклад: ")
                    val value = readln().toDouble()
                    if (value < 0) throw IllegalArgumentException("Оклад не може бути від’ємним.")
                    result = value
                } catch (e: NumberFormatException) {
                    println("Помилка: введено не число.")
                } catch (e: IllegalArgumentException) {
                    println("Помилка: ${e.message}")
                }
            }
            result
        }

        // Введення коефіцієнта бонусу
        val bonusRate: Double = run {
            var result: Double? = null
            while (result == null) {
                try {
                    print("Коефіцієнт бонусу (0.1 - 2.0): ")
                    val value = readln().toDouble()
                    if (value !in 0.1..2.0) throw IllegalArgumentException("Коефіцієнт має бути в межах 0.1–2.0.")
                    result = value
                } catch (e: NumberFormatException) {
                    println("Помилка: введено не число.")
                } catch (e: IllegalArgumentException) {
                    println("Помилка: ${e.message}")
                }
            }
            result
        }

        val bonus = salary * bonusRate
        bonuses.add(name to bonus)
    }

    // Аналіз результатів
    val maxBonus = bonuses.maxByOrNull { it.second }
    val totalBonus = bonuses.sumOf { it.second }
    val excellentCount = bonuses.count { it.second >= 10000 }

    // Виведення результатів
    println("\n📊 Результати бонусного аналізу:")
    println("Найбільший бонус отримає: ${maxBonus?.first} — %.2f".format(maxBonus?.second ?: 0.0))
    println("Загальна сума бонусів: %.2f".format(totalBonus))
    println("Кількість відмінних працівників (бонус ≥ 10 000): $excellentCount")
}
