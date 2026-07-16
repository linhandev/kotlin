// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: expressions, super-forms -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: unqualified super calls immediate supertype implementation
 */

// TESTCASE NUMBER: 1

open class Base {
    open fun tag(): String = "base"
}

class Leaf : Base() {
    fun read(): String = super.tag()
}

fun box(): String {
    return if (Leaf().read() == "base") "OK" else "NOK"
}
