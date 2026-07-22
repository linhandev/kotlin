// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, classifier-declaration, enum-class-declaration -> paragraph 2 -> sentence 2
 * NUMBER: 1
 * DESCRIPTION: enum class body member function compares entry properties at runtime
 */

// TESTCASE NUMBER: 1
enum class Planet(val mass: Double) {
    EARTH(5.97),
    MARS(0.642);

    fun isHeavierThan(other: Planet): Boolean = mass > other.mass
}

fun box(): String {
    return if (Planet.EARTH.isHeavierThan(Planet.MARS) == true) "OK" else "NOK"
}
