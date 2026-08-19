// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 132 -> sentence 132
 * PRIMARY LINKS: declarations, classifier-declaration, classifier-initialization -> paragraph 132 -> sentence 132
 *                inheritance, inheriting -> paragraph 132 -> sentence 132
 * NUMBER: 1
 * DESCRIPTION: subclass init block runs after superclass property initializers in class declaration
 */

// TESTCASE NUMBER: 1
open class Base {
    val s = StringBuilder().apply { append("B") }
}

class Child : Base() {
    init {
        s.append("C")
    }
}

open class AlphaBase {
    val s = StringBuilder().apply { append("A") }
}

class AlphaChild : AlphaBase() {
    init {
        s.append("1")
    }
}

open class BetaBase {
    val s = StringBuilder().apply { append("X") }
}

class BetaChild : BetaBase() {
    init {
        s.append("Y")
    }
}

fun viaChild(): String = Child().s.toString()

fun viaAlpha(): String = AlphaChild().s.toString()

fun viaBeta(): String = BetaChild().s.toString()

fun box(): String {
    if (viaChild() != "BC") return "NOK: child"
    if (viaAlpha() != "A1") return "NOK: alpha"
    if (viaBeta() != "XY") return "NOK: beta"
    return "OK"
}
