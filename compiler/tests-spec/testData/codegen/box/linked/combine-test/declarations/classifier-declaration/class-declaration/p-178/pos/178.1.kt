// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 178 -> sentence 178
 * PRIMARY LINKS: inheritance, overriding -> paragraph 178 -> sentence 178
 *                declarations, classifier-declaration, class-declaration, abstract-classes -> paragraph 178 -> sentence 178
 *                inheritance, inheriting -> paragraph 178 -> sentence 178
 * NUMBER: 1
 * DESCRIPTION: an abstract member is implicitly open and must be overridden in a concrete subclass; the inherited concrete member then uses the override in a class declaration
 */

// TESTCASE NUMBER: 1
abstract class Figure {
    abstract fun size(): Int
    open fun label(): String = "figure"
    fun summary(): String = "${label()}:${size()}"
}

class Grid(val side: Int) : Figure() {
    override fun size(): Int = side * side
    override fun label(): String = "grid"
}

// TESTCASE NUMBER: 2
abstract class Animal {
    abstract fun sound(): String
}

abstract class Pet : Animal()

class Dog : Pet() {
    override fun sound(): String = "woof"
}

// TESTCASE NUMBER: 3
abstract class Config {
    abstract val level: Int
    fun doubled(): Int = level * 2
}

class HighConfig : Config() {
    override val level: Int = 5
}

fun box(): String {
    val g = Grid(3)
    if (g.size() != 9) return "NOK: size"
    if (g.summary() != "grid:9") return "NOK: summary"

    val figure: Figure = g
    if (figure.summary() != "grid:9") return "NOK: figure-summary"
    if (figure.label() != "grid") return "NOK: figure-label"

    val dog: Animal = Dog()
    if (dog.sound() != "woof") return "NOK: dog"

    val cfg: Config = HighConfig()
    if (cfg.level != 5) return "NOK: level"
    if (cfg.doubled() != 10) return "NOK: doubled"
    return "OK"
}
