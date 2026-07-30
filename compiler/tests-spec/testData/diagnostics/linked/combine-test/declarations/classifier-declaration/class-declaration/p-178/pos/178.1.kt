// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 178 -> sentence 178
 * PRIMARY LINKS: inheritance, overriding -> paragraph 178 -> sentence 178
 *                declarations, classifier-declaration, class-declaration, abstract-classes -> paragraph 178 -> sentence 178
 *                inheritance, inheriting -> paragraph 178 -> sentence 178
 * NUMBER: 1
 * DESCRIPTION: type inference for overriding abstract members in a concrete subclass in a class declaration
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
abstract class Figure {
    abstract fun size(): Int
    open fun label(): String = "figure"
}

class Grid(val side: Int) : Figure() {
    override fun size(): Int = side * side
    override fun label(): String = "grid"
}

fun case1() {
    val g = Grid(3)
    g checkType { check<Grid>() }
    checkSubtype<Figure>(g)
    g.size() checkType { check<Int>() }

    val figure: Figure = g
    figure.size() checkType { check<Int>() }
    figure.label() checkType { check<String>() }
}

// TESTCASE NUMBER: 2
abstract class Config {
    abstract val level: Int
    fun doubled(): Int = level * 2
}

class HighConfig : Config() {
    override val level: Int = 5
}

fun case2() {
    val cfg: Config = HighConfig()
    cfg.level checkType { check<Int>() }
    cfg.doubled() checkType { check<Int>() }
    checkSubtype<Config>(HighConfig())
}
