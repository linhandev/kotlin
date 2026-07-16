// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 55 -> sentence 55
 * NUMBER: 1
 * DESCRIPTION: SUPER_AT token in super@Derived calling open override from subclass
 */
// TESTCASE NUMBER: 1

open class Base {
    open fun value() = 1
}

class Derived : Base() {
    override fun value() = super@Derived.value() + 10
}

fun box(): String {
    return if (Derived().value() == 11) "OK" else "NOK"
}
