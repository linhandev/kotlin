// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 183 -> sentence 183
 * PRIMARY LINKS: inheritance, overriding -> paragraph 183 -> sentence 183
 *                expressions, super-forms -> paragraph 183 -> sentence 183
 *                declarations, property-declaration -> paragraph 183 -> sentence 183
 * NUMBER: 1
 * DESCRIPTION: type inference for overridden property getters that call super in a class declaration
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
open class Marker {
    open val label: String get() = "B"
}

class Tagged : Marker() {
    override val label: String get() = super.label + "C"
}

fun case1() {
    val t = Tagged()
    t checkType { check<Tagged>() }
    checkSubtype<Marker>(t)
    t.label checkType { check<String>() }

    val asMarker: Marker = t
    asMarker.label checkType { check<String>() }
}

// TESTCASE NUMBER: 2
open class Prefix {
    open val head: String get() = "P"
    fun wrapped(): String = "<$head>"
}

class Prefixed : Prefix() {
    override val head: String get() = super.head + "X"
}

fun case2() {
    val p = Prefixed()
    p.wrapped() checkType { check<String>() }
    checkSubtype<Prefix>(p)
}
