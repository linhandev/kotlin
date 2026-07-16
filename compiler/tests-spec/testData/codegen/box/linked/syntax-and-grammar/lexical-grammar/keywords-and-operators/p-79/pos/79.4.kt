// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 79 -> sentence 79
 * NUMBER: 4
 * DESCRIPTION: SUPER token in three-level inheritance chain super call
 */
// TESTCASE NUMBER: 1

open class Root79 {
    open fun label(): String = "root"
}

open class Middle79 : Root79() {
    override fun label(): String = super.label() + "-mid"
}

class Leaf79 : Middle79() {
    override fun label(): String = super.label() + "-leaf"
}

fun box(): String {
    return if (Leaf79().label() == "root-mid-leaf") "OK" else "NOK"
}
