fun main() {
    print("Enter the number of employees: ")
    val employeeCount = readln().toIntOrNull() ?: 0

    val bonuses = mutableListOf<Pair<String, Double>>()

    for (i in 1..employeeCount) {
        println("\nEmployee #$i")

        print("Name: ")
        val inputName = readlnOrNull()
        val name = inputName?.takeIf { it.isNotBlank() } ?: "Unknown"
// Українською коректно не відображає
        val salary: Double = run {
            var result: Double? = null
            while (result == null) {
                try {
                    print("Salary: ")
                    val value = readln().toDouble()
                    if (value < 0) throw IllegalArgumentException("Salary cannot be negative.")
                    result = value
                } catch (e: NumberFormatException) {
                    println("Error: please enter a valid number.")
                } catch (e: IllegalArgumentException) {
                    println("Error: ${e.message}")
                }
            }
            result
        }

        val bonusRate: Double = run {
            var result: Double? = null
            while (result == null) {
                try {
                    print("Bonus rate (0.1 - 2.0): ")
                    val value = readln().toDouble()
                    if (value !in 0.1..2.0) throw IllegalArgumentException("Rate must be between 0.1 and 2.0.")
                    result = value
                } catch (e: NumberFormatException) {
                    println("Error: please enter a valid number.")
                } catch (e: IllegalArgumentException) {
                    println("Error: ${e.message}")
                }
            }
            result
        }

        val bonus = salary * bonusRate
        bonuses.add(name to bonus)
    }

    val maxBonus = bonuses.maxByOrNull { it.second }
    val totalBonus = bonuses.sumOf { it.second }
    val excellentCount = bonuses.count { it.second >= 10000 }

    println("\n=== Bonus Analysis Results ===")
    println("Top bonus recipient: ${maxBonus?.first} — \$%.2f".format(maxBonus?.second ?: 0.0))
    println("Total bonuses: \$%.2f".format(totalBonus))
    println("Excellent employees (bonus ≥ 10,000): $excellentCount")
}
