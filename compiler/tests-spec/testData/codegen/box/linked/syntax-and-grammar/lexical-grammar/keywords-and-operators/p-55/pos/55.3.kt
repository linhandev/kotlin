// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 55 -> sentence 55
 * NUMBER: 3
 * DESCRIPTION: SUPER_AT token in super@Middle from three-level class hierarchy
 */
// TESTCASE NUMBER: 1

open class Root {
    open fun step() = 1
}

open class Middle : Root() {
    override fun step() = super@Middle.step() + 2
}

class Leaf : Middle() {
    override fun step() = super@Leaf.step() + 4
}

fun box(): String {
    return if (Leaf().step() == 7) "OK" else "NOK"
}
